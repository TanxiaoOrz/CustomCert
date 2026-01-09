package com.nickzhang.customcert.dto;

import com.baomidou.mybatisplus.annotation.TableId;
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
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
    /**
     * 子节点列表
     */
    private final List<XmlProducerNode> children = new ArrayList<>();
    /**
     * 子节点索引缓存
     */
    private HashMap<String, XmlProducerNode> childNodeCache = new HashMap<>();

    /**
     * xml 前缀根节点属性
     */
    private final HashMap<String, String> xmlRootAttributes;
    /**
     * xml 文件属性
     */
    private final HashMap<String, String> xmlProperties;

    /**
     * xml 根节点名称
     */
    private String xmlRootName;

    private XmlDataGetter<?> xmlDataGetter;

    private final String operType;

    private TransformerFactory factory;
    private DocumentBuilder builder;

    public XmlProducer(HashMap<String, String> xmlRootAttributes, HashMap<String, String> xmlProperties, String operType) {
        this.xmlRootAttributes = xmlRootAttributes;
        this.xmlProperties = xmlProperties;
        this.operType = operType;
    }

    public XmlProducer() {
        this.xmlRootAttributes = new HashMap<>();
        this.xmlProperties = new HashMap<>();
        xmlRootAttributes.put("xsi:schemaLocation", "http://www.chinaport.gov.cn/ciq DecCiqMessage.xsd");
        xmlRootAttributes.put("xmlns:ds", "http://www.chinaport.gov.cn/ciq");
        xmlRootAttributes.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

        xmlProperties.put(OutputKeys.INDENT, "yes");
        xmlProperties.put(OutputKeys.ENCODING, "UTF-8");
        xmlProperties.put(OutputKeys.VERSION, "1.0");

        operType = "G";
    }

    /**
     * 初始化xml文件生产类
     *
     * @param tableClass 主表类
     * @param subClasses 子类索引列表
     * @return 当前xml文件生产类实例
     */
    public XmlProducer initialize(Class<?> tableClass, HashMap<String, List<Class<?>>> subClasses) {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            factory = TransformerFactory.newInstance();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("初始化xml文档构建器失败", e);
        }
        Table mainTable = tableClass.getAnnotation(Table.class);
        xmlRootName = mainTable.xmlName();
        xmlDataGetter = new XmlDataGetter<>(tableClass.getName());
        Arrays.stream(tableClass.getDeclaredFields()).forEach(field -> translateNode(tableClass, field));


        List<Class<?>> detailClasses = subClasses.get(tableClass.getName());

        detailClasses.forEach(detailClass -> translateTable(detailClass, subClasses, xmlDataGetter));

        // 清理缓存
        childNodeCache = null;
        children.forEach(XmlProducerNode::clearCache);

        return this;
    }

    private void translateTable(Class<?> table, HashMap<String, List<Class<?>>> subClasses, XmlDataGetter<?> xmlDataGetter) {
        // 处理明细表主数组节点
        String detailXmlPath = table.getAnnotation(Table.class).xmlName();
        String[] detailXmlPathSegments = detailXmlPath.split(XNL_SEPARATOR);
        XmlProducerNode detailNode = new XmlProducerNode(detailXmlPathSegments[detailXmlPathSegments.length - 1], new ArrayList<>(), table.getName());
        NodeUtils.addChild(detailNode, detailXmlPathSegments, children, childNodeCache);
        // 处理明细表子节点
        List<Field> fields = Arrays.asList(table.getDeclaredFields());
        fields.forEach(field -> translateNode(table, field));
        List<Class<?>> detailClasses = subClasses.get(table.getName());
        // 构造数据获取器
        AtomicReference<Method> mainIdGetterMethod = new AtomicReference<>();
        AtomicReference<String> mainIdFieldName = new AtomicReference<>();
        fields.stream().filter(field -> {
                    Column annotation = (field).getAnnotation(Column.class);
                    if (annotation == null) {
                        return false;
                    }
                    return annotation.joinKey();
                }).findFirst().ifPresentOrElse(
                        field -> {
                            mainIdGetterMethod.set(NodeUtils.getGetterMethod(table, field));
                            mainIdFieldName.set(field.getAnnotation(Column.class).joinColumn());
                        },
                        () -> {throw new RuntimeException("明细表" + table.getName() + "无链接主键字段");}
                );


        // 处理明细表子数组节点
        XmlDataGetter<?> detailXmlDataGetter = new XmlDataGetter<>(table.getName(), mainIdFieldName.get(), mainIdGetterMethod.get());
        xmlDataGetter.putChildGetter(table.getName(), detailXmlDataGetter);
        if (detailClasses != null) {
            detailClasses.forEach(detailClass -> translateTable(detailClass, subClasses, detailXmlDataGetter));
        }

    }

    private void translateNode(Class<?> tableClass, Field field) {
        Column column = field.getAnnotation(Column.class);
        if (column != null) {
            String xmlName = column.xmlName();
            if (xmlName != null && !xmlName.isEmpty()) {
                XmlProducerNode node = getBaseNode(tableClass, field, column, xmlName);
                NodeUtils.addChild(node, xmlName.split(XNL_SEPARATOR), children, childNodeCache);
            }
        }
    }

    private XmlProducerNode getBaseNode(Class<?> detailClass, Field field, Column column, String xmlName) {
        XmlProducerNode node;
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

    public String getXmlText(XmlData xmlData, UtilsMapper utilsMapper) {
        Document document = builder.newDocument(); /* 生成xml文档 */
        Element rootElement = document.createElement(xmlRootName); /* 生成xml根节点 */
        xmlRootAttributes.forEach(rootElement::setAttribute); /* 为xml根节点添加属性 */
        document.appendChild(rootElement);
        children.forEach(node -> node.appendChildren(xmlData, utilsMapper, document, rootElement)); /* 为xml根节点添加子节点 */
        rootElement.appendChild(document.createElement("OperType")).setTextContent(operType);
        Transformer transformer;
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
