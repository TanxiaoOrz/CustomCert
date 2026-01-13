package com.nickzhang.customcert.xml;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: 张骏山
 * @Date: 2026/1/8 23:05
 * @PackageName: com.nickzhang.customcert.utils
 * @ClassName: NodeUtils
 * @Description: xmlNode操作公用文本处理类
 * @Version: 1.0
 */

@Component
@PropertySource("classpath:front-end-processor.properties")
public class NodeUtils implements InitializingBean {

    private static NodeUtils instance;

    /**
     * 输入文件路径
     */
    @Value("${front-end-processor.file-path.input}")
    private String inputFilePath;
    /**
     * 成功文件路径
     */
    @Value("${front-end-processor.file-path.success}")
    private String successFilePath;
    /**
     * 失败文件路径
     */
    @Value("${front-end-processor.file-path.failure}")
    private String failureFilePath;

    public static String getInputFilePath() {
        return instance.inputFilePath;
    }
     public static String getSuccessFilePath() {
        return instance.successFilePath;
    }
     public static String getFailureFilePath() {
        return instance.failureFilePath;
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

        return getGetterMethod(clazz, fieldName);
    }

    /**
     * 根据字段名获取对应的 get/is 方法
     * @param clazz 字段所属的类
     * @param fieldName 目标字段名
     * @return 对应的 get/is 方法（无则返回 null）
     */
    public static Method getGetterMethod(Class<?> clazz, String fieldName) {
        String methodName;
            // 布尔类型优先拼接 isXXX，兼容 getXXX
            methodName = "is" + capitalizeFirstLetter(fieldName);
            Method method;
            try {
                method = clazz.getMethod(methodName);
            } catch (NoSuchMethodException e) {
                // 布尔类型不存在 isXXX 方法，尝试 getXXX
                methodName = "get" + capitalizeFirstLetter(fieldName);
                try {
                    method = clazz.getMethod(methodName);
                } catch (NoSuchMethodException e1) {
                    throw new RuntimeException("xml生成解析器失败,类 " + clazz.getName() + " 中不存在方法 " + methodName, e1);
                }
            }
            return method;
    }

    /**
     * 首字母大写（处理字段名首字母小写的情况）
     * @param str 原始字符串
     * @return 首字母大写后的字符串
     */
    public static String capitalizeFirstLetter(String str) {
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


    /**
     * 添加子节点到指定的父节点下
     * @param child 待添加的子节点
     * @param xmlNodeNames 子节点的层级路径（数组形式）空数组代表当前节点直接合并
     * @param children 父节点的子节点列表
     * @param cache 节点缓存，用于快速查找已存在的节点
     */
    public static void addChild(XmlProducerNode child, String[] xmlNodeNames, List<XmlProducerNode> children, HashMap<String, XmlProducerNode> cache) {
        // 无层级节点直接添加组
        if (xmlNodeNames.length == 0 && child.getXmlNodeType() == XmlProducerNode.NODE_TYPE_ELEMENT) {
            child.getChildren().forEach(c ->{
                children.add(c);
                cache.put(c.getXmlNodeName(), c);
            });
            return;
        }
        String currentName = xmlNodeNames[0];
        // 单层节点直接添加到子节点列表
        if (xmlNodeNames.length == 1) {
            if (cache.containsKey(currentName)) {
                /*
                  若节点已存在，且为元素节点或元素列表节点，则进行合并操作
                 */
                if (child.getXmlNodeType()==(XmlProducerNode.NODE_TYPE_ELEMENT_LIST) || child.getXmlNodeType()==(XmlProducerNode.NODE_TYPE_ELEMENT)) {
                    XmlProducerNode origin = cache.get(currentName);
                    child.getChildren().forEach(c -> origin.addChild(c,new String[]{c.getXmlNodeName()}));
                }
            }
            else {
                children.add(child);
                cache.put(currentName, child);
            }
        }
        else {
            // 递归添加子节点
            XmlProducerNode xmlProducerNode = cache.get(currentName);
            if (xmlProducerNode == null) {
                xmlProducerNode = new XmlProducerNode(currentName, new ArrayList<>(),child.order);
                cache.put(currentName, xmlProducerNode);
                children.add(xmlProducerNode);
            }
            xmlProducerNode.addChild(child, Arrays.copyOfRange(xmlNodeNames, 1, xmlNodeNames.length));
        }
    }

    /**
     * 判断单个 dom4j.Element 是否为空节点（无有效文本、无有效属性、无有效子元素）
     * @param element 待判断的 DOM4J 元素节点
     * @return true：空节点，false：有效非空节点
     */
    public static boolean isEmptyElement(Element element) {
        if (element == null) {
            return true;
        }

        // 场景1：判断元素自身的文本内容是否有效（去除空白后非空）
        String textContent = element.getTextTrim(); // DOM4J 专属方法，直接去除首尾空白
        String originalText = element.getText();
        boolean hasValidText = originalText != null && !(Pattern.matches("^\\s*$", originalText));
        // 补充：getTextTrim() 直接返回去除空白后的字符串，更简洁的判断方式
        if (!textContent.isEmpty()) {
            hasValidText = true;
        }

        // 场景2：判断元素是否有有效属性（属性值非空白）
        List<Attribute> attributes = element.attributes();
        boolean hasValidAttribute = false;
        for (Attribute attr : attributes) {
            String attrValue = attr.getValue();
            if (attrValue != null && !Pattern.matches("^\\s*$", attrValue)) {
                hasValidAttribute = true;
                break;
            }
        }

        // 场景3：判断元素是否有有效子元素（递归判断，处理嵌套子节点）
        List<Element> childElements = element.elements(); // DOM4J 专属方法，获取所有子元素节点
        boolean hasValidChildElement = false;
        for (Element childElem : childElements) {
            if (!isEmptyElement(childElem)) { // 递归调用，判断子元素是否非空
                hasValidChildElement = true;
                break;
            }
        }

        // 无任何有效内容，视为空节点
        return !hasValidText && !hasValidAttribute && !hasValidChildElement;
    }

    /**
     * 转换日期字符串格式，默认输入格式"yyyy-MM-dd HH:mm:ss.S"，输出格式"yyyy-MM-dd"
     * @param originalDateStr 原始日期字符串
     * @return 转换后的日期字符串，解析失败或参数无效返回null
     */
    public static String convertDateStr(String originalDateStr) {
        return convertDateStr(originalDateStr, "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd");
    }

    /**
     * 通用日期字符串转换方法
     * @param originalDateStr 原始日期字符串
     * @param inputPattern 原始日期字符串对应的格式（例如："yyyy-MM-dd HH:mm:ss.S"）
     * @param outputPattern 目标日期字符串对应的格式（例如："yyyy-MM-dd"）
     * @return 转换后的日期字符串，解析失败或参数无效返回null
     */
    public static String convertDateStr(String originalDateStr, String inputPattern, String outputPattern) {
        // 1. 参数合法性校验
        if (originalDateStr == null || originalDateStr.trim().isEmpty()) {
            System.out.println("错误：原始日期字符串不能为空");
            return null;
        }
        if (inputPattern == null || inputPattern.trim().isEmpty()) {
            System.out.println("错误：输入日期格式不能为空");
            return null;
        }
        if (outputPattern == null || outputPattern.trim().isEmpty()) {
            System.out.println("错误：输出日期格式不能为空");
            return null;
        }

        // 2. 定义格式化器
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputPattern.trim());
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputPattern.trim());

        try {
            // 3. 解析并格式化
            LocalDateTime localDateTime = LocalDateTime.parse(originalDateStr.trim(), inputFormatter);
            return localDateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("错误：日期解析失败，原始字符串格式与输入格式不匹配 originalDateStr= {} inputPattern= {} outputPattern= {} e=" + originalDateStr + inputPattern + outputPattern ,e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        instance = this;
    }
}
