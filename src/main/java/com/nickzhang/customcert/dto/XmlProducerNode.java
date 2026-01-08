package com.nickzhang.customcert.dto;

import com.nickzhang.customcert.mapper.UtilsMapper;
import com.nickzhang.customcert.utils.NodeUtils;
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
     * xml节点类型 元素节点
     */
    public static final int NODE_TYPE_ELEMENT = 1;
    /**
     * xml节点类型 关联浏览代理节点
     */
    public static final int NODE_TYPE_BROWSER = 2;

    private final String xmlNodeName;

    private final int xmlNodeType;

    /**
     * 子节点列表
     */
    private final List<XmlProducerNode> children;
    /**
     * 子节点初始化时的缓存，用于快速查找子节点
     */
    private HashMap<String, XmlProducerNode> cache;

    private final Method getValueMethod;

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
     * 元素节点构造函数 默认赋值NODE_TYPE_ELEMENT
     * @param xmlNodeName xml节点名称
     * @param children 子节点列表
     */
    public XmlProducerNode(String xmlNodeName, List<XmlProducerNode> children, HashMap<String, XmlProducerNode> cache) {
        this.xmlNodeName = xmlNodeName;
        this.xmlNodeType = NODE_TYPE_ELEMENT;
        this.children = children;
        this.getValueMethod = null;
        this.cache = cache;
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
        if (children == null || xmlNodeType != NODE_TYPE_ELEMENT) {
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
     * 获取 xml节点元素
     * @param object 数据对象
     * @param mapper 数据库映射器
     * @param document xml文档对象
     * @return xml节点元素
     */
    protected Element getXmlElement(Object object, UtilsMapper mapper, Document document) {
        Element nodeElement = document.createElement(xmlNodeName);
        switch (xmlNodeType) {
            case NODE_TYPE_TEXT:
                String textValue = null;
                try {
                    textValue = (String) getValueMethod.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                nodeElement.appendChild(document.createTextNode(textValue));
                return null;
            case NODE_TYPE_ELEMENT:
                children.forEach(child -> nodeElement.appendChild(child.getXmlElement(object, mapper, document)));
                return nodeElement;
            case NODE_TYPE_BROWSER:
                String browserShowName = null;
                try {
                    browserShowName = mapper.getBrowserShowName(browserTableName, browserTableShowColumn, browserTableMainColumn, getValueMethod.invoke(object).toString());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                nodeElement.appendChild(document.createTextNode(browserShowName));
                return null;
        }
        throw new IllegalArgumentException("未知的xml节点类型");
    }


}
