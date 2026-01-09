package com.nickzhang.customcert.dto;

import com.nickzhang.customcert.mapper.UtilsMapper;
import com.nickzhang.customcert.utils.NodeUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2026/1/5 22:19
 * @PackageName: com.nickzhang.customcert.dto
 * @ClassName: XmlProducerDto
 * @Description: 凭证生成节点dto类
 * @Version: 1.0
 */
public class XmlProducerNode {

    /**
     * xml节点类型 直接解析方法文本
     */
    public static final int NODE_TYPE_TEXT = 0;
    /**
     * xml节点类型 关联浏览代理节点
     */
    public static final int NODE_TYPE_BROWSER = 1;
    /**
     * xml节点类型 单元素节点
     */
    public static final int NODE_TYPE_ELEMENT = 2;
    /**
     * xml节点类型 多元素节点列表
     */
    public static final int NODE_TYPE_ELEMENT_LIST = 3;

    @Getter
    private final String xmlNodeName;

    @Getter
    private final int xmlNodeType;

    /**
     * 子节点列表
     */
    @Getter
    private final List<XmlProducerNode> children;
    /**
     * 子节点初始化时的缓存，用于快速查找子节点
     */
    @Getter
    private HashMap<String, XmlProducerNode> cache;

    private final Method getValueMethod;

    private String elementsClassName;

    private String browserTableName;
    private String browserTableMainColumn;
    private String browserTableShowColumn;
    /**
     * 元素节点构造函数 默认赋值NODE_TYPE_ELEMENT
     * @param xmlNodeName xml节点名称
     * @param children 子节点列表
     */
    public XmlProducerNode(String xmlNodeName, @NotNull List<XmlProducerNode> children) {
        this.xmlNodeName = xmlNodeName;
        this.xmlNodeType = NODE_TYPE_ELEMENT;
        this.children = children;
        this.getValueMethod = null;
        this.cache = new HashMap<>();
        for (XmlProducerNode child : children) {
            cache.put(child.xmlNodeName, child);
        }
    }

    /**
     * 元素列表节点构造函数 默认赋值NODE_TYPE_ELEMENT_LIST
     * @param xmlNodeName xml节点名称
     * @param children 子节点列表
     * @param elementsClassName 元素列表类全限定名
     */
    public XmlProducerNode(String xmlNodeName, @NotNull List<XmlProducerNode> children, String elementsClassName) {
        this.xmlNodeName = xmlNodeName;
        this.xmlNodeType = NODE_TYPE_ELEMENT_LIST;
        this.children = children;
        this.getValueMethod = null;
        this.cache = new HashMap<>();
        for (XmlProducerNode child : children) {
            cache.put(child.xmlNodeName, child);
        }
        this.elementsClassName = elementsClassName;
    }


    /**
     * 文本节点构造函数
     * @param xmlNodeName xml节点名称
     * @param getValueMethod 获取节点值的方法
     */
    public XmlProducerNode(String xmlNodeName, Method getValueMethod) {
        this.xmlNodeName = xmlNodeName;
        this.xmlNodeType = NODE_TYPE_TEXT;
        this.getValueMethod = getValueMethod;
        this.children = null;
        this.cache = null;
    }

    /**
     * 关联浏览节点构造函数
     * @param xmlNodeName xml节点名称
     * @param browserTableName 关联表数据库名称
     * @param browserTableMainColumn 关联表主键字段
     * @param browserTableShowColumn 关联表显示字段
     */
    public XmlProducerNode(String xmlNodeName,Method getValueMethod, String browserTableName, String browserTableMainColumn, String browserTableShowColumn) {
        this.xmlNodeName = xmlNodeName;
        this.xmlNodeType = NODE_TYPE_BROWSER;
        this.browserTableName = browserTableName;
        this.browserTableMainColumn = browserTableMainColumn;
        this.browserTableShowColumn = browserTableShowColumn;
        this.getValueMethod = getValueMethod;
        children = null;
    }

    /**
     * 添加子节点
     * @param child 子节点
     * @throws IllegalArgumentException 元素节点才能添加子节点
     */
    public void addChild(XmlProducerNode child, String[] xmlNodeNames) {
        if (cache == null)
            throw new RuntimeException("xml固定后不得添加子节点");
        if (children == null || (xmlNodeType != NODE_TYPE_ELEMENT && xmlNodeType != NODE_TYPE_ELEMENT_LIST)) {
            throw new IllegalArgumentException("元素节点才能添加子节点");
        }
        NodeUtils.addChild(child, xmlNodeNames, children, cache);
    }



    /**
     * 初始化完成,抛弃缓存
     */
    protected void clearCache() {
        cache = null;
        if (children != null)
            children.forEach(XmlProducerNode::clearCache);
    }

    /**
     * 获取 xml节点元素 并添加到belongs节点下
     * @param xmlData xml数据对象
     * @param mapper 数据库映射器
     * @param document xml文档对象
     * @param belongs 父节点元素
     */
    protected void appendChildren(XmlData xmlData, UtilsMapper mapper, Document document, Element belongs) {
        Object currentData = xmlData.getCurrentData();
        switch (xmlNodeType) {
            case NODE_TYPE_TEXT -> {
                Element nodeElement = document.createElement(xmlNodeName);
                String textValue;
                try {
                    String invoke = (String) getValueMethod.invoke(currentData);
                    textValue = invoke==null?"":invoke;
                } catch (IllegalAccessException | InvocationTargetException |IllegalArgumentException e) {
                    throw new RuntimeException("获取" + xmlNodeName + "节点值时出错"+getValueMethod.getName()+" "+currentData.getClass(), e);
                }
                nodeElement.appendChild(document.createTextNode(textValue.equals("")?"":textValue));
                belongs.appendChild(nodeElement);
                return;
            }
            case NODE_TYPE_ELEMENT -> {
                Element nodeElement = document.createElement(xmlNodeName);
                children.forEach(child -> child.appendChildren(xmlData, mapper, document, nodeElement));
                belongs.appendChild(nodeElement);
                return;
            }
            case NODE_TYPE_ELEMENT_LIST -> {
                List<XmlData> xmlDataChildren = xmlData.getChildren(elementsClassName);
                if (xmlDataChildren == null)
                    return;
                xmlDataChildren.forEach(data -> {
                    Element nodeElement = document.createElement(xmlNodeName);
                    children.forEach(child ->
                            child.appendChildren(data, mapper, document, nodeElement)
                    );
                    belongs.appendChild(nodeElement);
                });
                return;
            }
            case NODE_TYPE_BROWSER -> {
                Element nodeElement = document.createElement(xmlNodeName);
                String browserShowName;
                try {
                    browserShowName = mapper.getBrowserShowName(browserTableName, browserTableShowColumn, browserTableMainColumn, getValueMethod.invoke(currentData).toString());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                nodeElement.appendChild(document.createTextNode(browserShowName));
                belongs.appendChild(nodeElement);
                return;
            }
        }
        throw new IllegalArgumentException("未知的xml节点类型");
    }

        @Override
        public String toString() {
            return "XmlProducerNode{" +"\n" +
                    "xmlNodeName='" + xmlNodeName + '\'' + "\n" +
                    ", xmlNodeType=" + xmlNodeType + "\n" +
                    ", getValueMethod=" + getValueMethod + "\n" +
                    ", browserTableName='" + browserTableName + '\'' + "\n" +
                    ", browserTableMainColumn='" + browserTableMainColumn + '\'' + "\n" +
                    ", browserTableShowColumn='" + browserTableShowColumn + '\'' +
                    ", children=" + children +"\n" +
                    '}';
        }
}
