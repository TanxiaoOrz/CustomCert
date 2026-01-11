package com.nickzhang.customcert.dto;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import com.nickzhang.customcert.mapper.UtilsMapper;
import com.nickzhang.customcert.utils.NodeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

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
 * @Description: xml文件生产类（基于 dom4j 实现）
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

    private String schemaLocation;

    /**
     * xml 文件属性（dom4j 格式化配置，替代原 Transformer 配置）
     */
    private final HashMap<String, String> xmlProperties;

    /**
     * xml 根节点名称
     */
    private String xmlRootName;

    private XmlDataGetter<?> xmlDataGetter;

    private final String operType;

    // 替换：移除 org.w3c.dom 的构建器/转换器，使用 dom4j 的格式化配置
    private OutputFormat outputFormat;

    public XmlProducer(HashMap<String, String> xmlRootAttributes, HashMap<String, String> xmlProperties, String operType) {
        this.xmlRootAttributes = xmlRootAttributes;
        this.xmlProperties = xmlProperties;
        this.operType = operType;
    }

    /**
     * 无参构造函数
     */
    public XmlProducer() {
        this.xmlRootAttributes = new HashMap<>();
        this.xmlProperties = new HashMap<>();
        schemaLocation = "http://www.chinaport.gov.cn/ciq DecCiqMessage.xsd";
        xmlRootAttributes.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        xmlRootAttributes.put("", "http://www.chinaport.gov.cn/ciq");
//        xmlRootAttributes.put("xmlns:", "http://www.chinaport.gov.cn/ciq");
//        xmlRootAttributes.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

        // 替换：dom4j 格式化配置（对应原 Transformer 输出属性）
        xmlProperties.put("INDENT", "yes");
        xmlProperties.put("ENCODING", "UTF-8");
        xmlProperties.put("VERSION", "1.0");

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
        // 替换：初始化 dom4j 输出格式（无需 ParserConfigurationException 捕获）
        initDom4jOutputFormat();

        Table mainTable = tableClass.getAnnotation(Table.class);
        xmlRootName = mainTable.xmlName();
        xmlDataGetter = new XmlDataGetter<>(tableClass.getName());
        Arrays.stream(tableClass.getDeclaredFields()).forEach(field -> translateNode(tableClass, field, children, childNodeCache));

        List<Class<?>> detailClasses = subClasses.get(tableClass.getName());
        if (detailClasses != null) {
            detailClasses.forEach(detailClass -> translateTable(detailClass, subClasses, xmlDataGetter));
        }

        // 清理缓存
        childNodeCache = null;
        children.forEach(XmlProducerNode::clearCache);

        return this;
    }

    /**
     * 初始化 dom4j 输出格式（核心：配置 XML 序列化规则）
     */
    private void initDom4jOutputFormat() {
        // 1. 创建格式化对象（美观格式化/紧凑格式化二选一）
        outputFormat = OutputFormat.createPrettyPrint(); // 带缩进、换行的美观格式
        // outputFormat = OutputFormat.createCompactFormat(); // 无缩进、无换行的紧凑格式

        // 2. 从 xmlProperties 加载配置（对应原 Transformer 输出属性）
        if (xmlProperties != null) {
            // 编码配置
            String encoding = xmlProperties.get("ENCODING");
            if (encoding != null && !encoding.isEmpty()) {
                outputFormat.setEncoding(encoding);
            } else {
                outputFormat.setEncoding("UTF-8"); // 默认 UTF-8
            }

            // 缩进配置（仅对 PrettyPrint 生效）
            if ("yes".equalsIgnoreCase(xmlProperties.get("INDENT"))) {
                outputFormat.setIndent(true);
                outputFormat.setIndent("  "); // 缩进 2 个空格
                outputFormat.setNewlines(true); // 开启换行
            }

            // 可选：强制空节点展开为 <tag></tag>（避免自闭合标签 <tag/>）
            outputFormat.setExpandEmptyElements(true);
        }
    }

    private void translateTable(Class<?> tableClass, HashMap<String, List<Class<?>>> subClasses, XmlDataGetter<?> xmlDataGetter) {
        // 处理子表主节点
        Table table = tableClass.getAnnotation(Table.class);
        String detailXmlPath = table.xmlName();
        boolean isDependent = table.isDependent();
        String[] detailXmlPathSegments = detailXmlPath.split(XNL_SEPARATOR);
        XmlProducerNode detailNode ;
        if (isDependent)
            detailNode = new XmlProducerNode(detailXmlPathSegments[detailXmlPathSegments.length - 1], new ArrayList<>()); // 非独立从表创建元素节点缓存,后续合并使用
        else
            detailNode = new XmlProducerNode(detailXmlPathSegments[detailXmlPathSegments.length - 1], new ArrayList<>(), tableClass.getName()); // 独立表创建数组节点(兼容单行)

        // 处理明细表子节点
        List<Field> fields = Arrays.asList(tableClass.getDeclaredFields());
        fields.stream().map(field -> translateNode(tableClass, field, detailNode.getChildren(), detailNode.getCache())) // 生成子节点
                .filter(Objects::nonNull).filter(nodes->isDependent).forEach(nodes->nodes.tobeDependentBelongClass(tableClass.getName())); // 若从属子表 注入类型名
        List<Class<?>> detailClasses = subClasses.get(tableClass.getName());

        // 将子表节点进行添加
        NodeUtils.addChild(detailNode, detailXmlPathSegments, children, childNodeCache);


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
                    mainIdGetterMethod.set(NodeUtils.getGetterMethod(table.belongTo(), field.getAnnotation(Column.class).joinColumn()));
                    mainIdFieldName.set(field.getAnnotation(Column.class).dbName());
                },
                () -> {throw new RuntimeException("明细表" + tableClass.getName() + "无链接主键字段");}
        );

        // 处理明细表子数组节点
        XmlDataGetter<?> detailXmlDataGetter = new XmlDataGetter<>(tableClass.getName(), mainIdFieldName.get(), mainIdGetterMethod.get());
        xmlDataGetter.putChildGetter(tableClass.getName(), detailXmlDataGetter);
        if (detailClasses != null) {
            detailClasses.forEach(detailClass -> translateTable(detailClass, subClasses, detailXmlDataGetter));
        }
    }

    private XmlProducerNode translateNode(Class<?> tableClass, Field field, List<XmlProducerNode> children, HashMap<String, XmlProducerNode> childNodeCache) {
        Column column = field.getAnnotation(Column.class);
        if (column != null) {
            String xmlName = column.xmlName();
            if (xmlName != null && !xmlName.isEmpty()) {
                XmlProducerNode node = getBaseNode(tableClass, field, column, xmlName);
                NodeUtils.addChild(node, xmlName.split(XNL_SEPARATOR), children, childNodeCache);
                return node;
            }
        }
        return null;
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

    public XmlData getXmlData(Map<String, BaseMapper<?>> mappers, String mainId) {
        return xmlDataGetter.getMainData(mappers, mainId);
    }

    /**
     * 核心替换：使用 dom4j 生成 XML 字符串（替代 org.w3c.dom 的 Transformer 逻辑）
     */
    public String getXmlText(XmlData xmlData, UtilsMapper utilsMapper) {
        // 1. 构建 dom4j Document（替代 org.w3c.dom.Document）
        Document document = DocumentHelper.createDocument();

        // 2. 创建根节点（替代 document.createElement + appendChild）
        Element rootElement = document.addElement(xmlRootName);


        // 4. 追加子节点（需确保 XmlProducerNode.appendChildren 内部也适配 dom4j）
        children.forEach(node -> node.appendChildren(xmlData, utilsMapper, rootElement));

        // 5. 添加 OperType 节点（替换 setTextContent 为 setText）
        rootElement.addElement("OperType").setText(operType);

        // 3. 给根节点添加属性（方法名一致，逻辑不变）
        xmlRootAttributes.entrySet().stream().map(entry -> DocumentHelper.createNamespace(entry.getKey(), entry.getValue())).forEach(rootElement::add);
//        xmlRootAttributes.forEach(rootElement::addAttribute);
        rootElement.addAttribute("xsi:schemaLocation", schemaLocation);

        // 6. dom4j 序列化 XML（替代 Transformer + DOMSource + StreamResult）
        try (StringWriter writer = new StringWriter()) {
            XMLWriter xmlWriter = new XMLWriter(writer, outputFormat);
            xmlWriter.write(document);
            return writer.toString().replace(" xmlns=\"\"", "");
        } catch (Exception e) {
            throw new RuntimeException("xml文件生产失败", e);
        }
    }

    @Override
    public String toString() {
        return "XmlProducer{" +
                "xmlRootName='" + xmlRootName + '\'' + "\n" +
                ", xmlRootAttributes=" + xmlRootAttributes + "\n" +
                ", children=" + children.toString() + "\n" +
                ", xmlProperties=" + xmlProperties + "\n" +
                ", operType='" + operType + '\'' + "\n" +
                '}';
    }
}