package com.nickzhang.customcert.dto;

import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import com.nickzhang.customcert.mapper.UtilsMapper;
import com.nickzhang.customcert.utils.NodeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.*;

import static com.nickzhang.customcert.annotation.Column.LINK_SEPARATOR;
import static com.nickzhang.customcert.annotation.Column.XNL_SEPARATOR;

/**
 * @Author: 张骏山
 * @Date: 2026/1/6 13:09
 * @PackageName: com.nickzhang.customcert.dto
 * @ClassName: XmlProducer
 * @Description: xml文件生产类
 * @Version: 1.0
 */
public class XmlProducer {
    private final List<XmlProducerNode> children = new ArrayList<>();
    private HashMap<String, XmlProducerNode> cache = new HashMap<>();
    private DocumentBuilder builder ;



    /**
     * xml 文件前缀
     */
    private final String xmlRootTitle;
    /**
     * xml 前缀根节点属性
     */
    private final HashMap<String, String> xmlRootAttributes;
    private final HashMap<String, String> xmlProperties ;

    /**
     * xml 根节点名称
     */
    private String xmlRootName;
    private TransformerFactory factory;

    public XmlProducer(String xmlRootTitle, HashMap<String, String> xmlRootAttributes, HashMap<String, String> xmlProperties) {
        this.xmlRootTitle = xmlRootTitle;
        this.xmlRootAttributes = xmlRootAttributes;
        this.xmlProperties = xmlProperties;
    }

    public XmlProducer() {
        this.xmlRootTitle = "";
        this.xmlRootAttributes = new HashMap<>();
        this.xmlProperties = new HashMap<>();
        xmlRootAttributes.put("xsi:schemaLocation", "http://www.chinaport.gov.cn/ciq DecCiqMessage.xsd");
        xmlRootAttributes.put("xmlns:ds", "http://www.chinaport.gov.cn/ciq");
        xmlRootAttributes.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

        xmlProperties.put(OutputKeys.INDENT, "yes");
        xmlProperties.put(OutputKeys.ENCODING, "UTF-8");
        xmlProperties.put(OutputKeys.VERSION, "1.0");


    }

    /**
     * 初始化xml文件生产类
     * @param tableClass 主表类
     * @param detailClasses 明细表类列表
     * @return 当前xml文件生产类实例
     */
    public XmlProducer initialize(Class<?> tableClass, List<Class<?>> detailClasses) {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            factory = TransformerFactory.newInstance();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("初始化xml文档构建器失败", e);
        }
        Table mainTable = tableClass.getAnnotation(Table.class);
        xmlRootName = mainTable.xmlName();
        Arrays.stream(tableClass.getDeclaredFields()).forEach(field -> {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                String xmlName = column.xmlName();
                if (xmlName != null && !xmlName.isEmpty()) {
                    XmlProducerNode node = getBaseNode(tableClass, field, column, xmlName);
                    NodeUtils.addChild(node, xmlName.split(XNL_SEPARATOR), children, cache);
                }
            }
        });
        detailClasses.forEach(detailClass -> {
            // 处理明细表主数组节点
            String detailXmlPath = detailClass.getAnnotation(Table.class).xmlName();
            String[] detailXmlPathSegments = detailXmlPath.split(XNL_SEPARATOR);
            XmlProducerNode detailNode = new XmlProducerNode(detailXmlPathSegments[detailXmlPathSegments.length - 1], new ArrayList<>());
            NodeUtils.addChild(detailNode, detailXmlPathSegments, children, cache);
            // 处理明细表子节点
            Arrays.stream(detailClass.getDeclaredFields()).forEach(field -> {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    String xmlName = column.xmlName();
                    if (xmlName != null && !xmlName.isEmpty()) {
                        XmlProducerNode node = getBaseNode(detailClass, field, column, xmlName);;
                        detailNode.addChild(node, xmlName.split(XNL_SEPARATOR));
                    }
                }
            });
        });

        // 清理缓存
        cache = null;
        children.forEach(XmlProducerNode::clearCache);

        return this;
    }

    private XmlProducerNode getBaseNode(Class<?> detailClass, Field field, Column column, String xmlName) {
        XmlProducerNode node;
        int depth = NodeUtils.countSlashes(xmlName);
        String linkTableColumn = column.linkTableColumn();
        if (linkTableColumn.isEmpty()) { // 文本字段
            node = new XmlProducerNode(NodeUtils.getLastSegmentAfterSlash(xmlName), NodeUtils.getGetterMethod(detailClass, field));
        } else {
            String linkTableMainColumn;
            String linkTableName;
            String linkTableColumnShow;
            try {
                String[] browserConfig = linkTableColumn.split(LINK_SEPARATOR);
                linkTableName = browserConfig[0];
                linkTableColumnShow = browserConfig[1];
                linkTableMainColumn = browserConfig.length > 2 ? browserConfig[2] : "id";
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RuntimeException("关联浏览字段配置错误，格式为：关联表名-关联表显示字段[-主建id]}", e);
            }
            node = new XmlProducerNode(NodeUtils.getLastSegmentAfterSlash(xmlName), NodeUtils.getGetterMethod(detailClass, field),
                    linkTableName, linkTableMainColumn, linkTableColumnShow);
        }
        return node;
    }

    public String getXmlText(Object o, UtilsMapper utilsMapper) {
        Document document = builder.newDocument(); /* 生成xml文档 */
        Element rootElement = document.createElement(xmlRootName); /* 生成xml根节点 */
        xmlRootAttributes.forEach(rootElement::setAttribute); /* 为xml根节点添加属性 */
        children.forEach(node -> rootElement.appendChild(node.getXmlElement( o, utilsMapper,document))); /* 为xml根节点添加子节点 */
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException("初始化xml文档转换失败", e);
        }

        xmlProperties.forEach(transformer::setOutputProperty); /* 为xml根节点添加属性 */



        DOMSource source = new DOMSource(document);
        // 输出到文件
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException("xml文件生产失败", e);
        }

        return writer.toString();
    }

}
