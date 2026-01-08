package com.nickzhang.customcert.dto;

import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import com.nickzhang.customcert.mapper.UtilsMapper;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2026/1/6 13:09
 * @PackageName: com.nickzhang.customcert.dto
 * @ClassName: XmlProducer
 * @Description: xml文件生产类
 * @Version: 1.0
 */
public class XmlProducer {
    private final List<XmlProducerNode> nodes = new ArrayList<>();
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
            if (column != null && column.xmlName() != null && !column.xmlName().isEmpty()) {
                XmlProducerNode node;
                if (column.linkColumn().isEmpty()) { // 文本字段
                    node = new XmlProducerNode(getLastSegmentAfterSlash(column.xmlName()), getGetterMethod(tableClass, field));
                }
               // TODO 关联浏览字段
            }
        });



        return this;
    }

    public String getXmlText (Object o, UtilsMapper utilsMapper) {
        Document document = builder.newDocument(); /* 生成xml文档 */
        Element rootElement = document.createElement(xmlRootName); /* 生成xml根节点 */
        xmlRootAttributes.forEach(rootElement::setAttribute); /* 为xml根节点添加属性 */
        nodes.forEach(node -> rootElement.appendChild(node.getXmlElement( o, utilsMapper,document))); /* 为xml根节点添加子节点 */
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

    /**
     * 根据字段获取对应的 get/is 方法
     * @param clazz 字段所属的类
     * @param field 目标字段
     * @return 对应的 get/is 方法（无则返回 null）
     */
    public static Method getGetterMethod(Class<?> clazz, Field field) {
        if (clazz == null || field == null) {
            return null;
        }

        String fieldName = field.getName();
        Class<?> fieldType = field.getType();
        String methodName;

        // 1. 拼接方法名（区分布尔类型和普通类型）
        if (fieldType == boolean.class) {
            // 布尔类型优先拼接 isXXX，兼容 getXXX
            methodName = "is" + capitalizeFirstLetter(fieldName);
            Method isMethod = null;
            try {
                isMethod = clazz.getMethod(methodName);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("xml生成解析器失败,类 " + clazz.getName() + " 中不存在方法 " + methodName, e);
            }
            return isMethod;
            // 如果 isXXX 不存在，尝试 getXXX
        } else if ( fieldType == Boolean.class) {
            // 布尔类型优先拼接 isXXX，兼容 getXXX
            methodName = "is" + capitalizeFirstLetter(fieldName);
            Method isMethod = null;
            try {
                isMethod = clazz.getMethod(methodName);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("xml生成解析器失败,类 " + clazz.getName() + " 中不存在方法 " + methodName, e);
            }
            return isMethod;
            // 如果 isXXX 不存在，尝试 getXXX
        } else {
            // 普通类型拼接 getXXX
            methodName = "get" + capitalizeFirstLetter(fieldName);
        }

        // 2. 获取方法（无参数）
        return getMethod(clazz, methodName);
    }

    /**
     * 首字母大写（处理字段名首字母小写的情况）
     * @param str 原始字符串
     * @return 首字母大写后的字符串
     */
    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 获取最后一个 '/' 后的文本，无 '/' 则返回原字符串
     * @param input 输入字符串
     * @return 处理后的字符串
     */
    public static String getLastSegmentAfterSlash(String input) {
        // 空值判断，避免空指针异常
        if (input == null) {
            return null;
        }

        // 找到最后一个 '/' 的位置
        int lastSlashIndex = input.lastIndexOf('/');

        // 如果没找到 '/'（索引为 -1），直接返回原字符串
        if (lastSlashIndex == -1) {
            return input;
        }

        // 截取最后一个 '/' 之后的子串
        return input.substring(lastSlashIndex + 1);
    }

}
