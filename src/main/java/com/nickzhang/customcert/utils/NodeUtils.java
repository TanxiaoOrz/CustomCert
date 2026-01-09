package com.nickzhang.customcert.utils;

import com.nickzhang.customcert.dto.XmlProducerNode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.nickzhang.customcert.annotation.Column.XNL_SEPARATOR;

/**
 * @Author: 张骏山
 * @Date: 2026/1/8 23:05
 * @PackageName: com.nickzhang.customcert.utils
 * @ClassName: NodeUtils
 * @Description: xmlNode操作公用文本处理类
 * @Version: 1.0
 */
public class NodeUtils {




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
        if (fieldType == boolean.class || fieldType == Boolean.class) {
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
        } else {
            // 文本类型优先拼接 getXXX
            methodName = "get" + capitalizeFirstLetter(fieldName);
            Method getMethod;
            try {
                getMethod = clazz.getMethod(methodName);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("xml生成解析器失败,类 " + clazz.getName() + " 中不存在方法 " + methodName, e);
            }
            return getMethod;
        }
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
     * 计算字符串中 '/' 符号的数量
     * @param input 输入字符串
     * @return XNL_SEPARATOR 符号的数量
     */
    public static int countSlashes(String input) {
        if (input == null) {
            return 0;
        }

        if (input.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        int subLength = XNL_SEPARATOR.length();
        while ((index = input.indexOf(XNL_SEPARATOR, index)) != -1) {
            count++;
            // 从匹配位置的下一个字符继续查找（避免重叠）
            index += subLength;
        }
        return count;
    }


    public static void addChild(XmlProducerNode child, String[] xmlNodeNames, List<XmlProducerNode> children, HashMap<String, XmlProducerNode> cache) {
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
                xmlProducerNode = new XmlProducerNode(currentName, new ArrayList<>());
                cache.put(currentName, xmlProducerNode);
                children.add(xmlProducerNode);
            }
            xmlProducerNode.addChild(child, Arrays.copyOfRange(xmlNodeNames, 1, xmlNodeNames.length));
        }
    }
}
