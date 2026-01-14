package com.nickzhang.customcert.utils;

import com.nickzhang.customcert.xml.NodeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: 张骏山
 * @Date: 2026/1/14 15:14
 * @PackageName: com.nickzhang.customcert.utils
 * @ClassName: ClassUtils
 * @Description: class相关操作工具类
 * @Version: 1.0
 */
public class ClassUtils {
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
            methodName = "is" + NodeUtils.capitalizeFirstLetter(fieldName);
            Method method;
            try {
                method = clazz.getMethod(methodName);
            } catch (NoSuchMethodException e) {
                // 布尔类型不存在 isXXX 方法，尝试 getXXX
                methodName = "get" + NodeUtils.capitalizeFirstLetter(fieldName);
                try {
                    method = clazz.getMethod(methodName);
                } catch (NoSuchMethodException e1) {
                    throw new RuntimeException("xml生成解析器失败,类 " + clazz.getName() + " 中不存在方法 " + methodName, e1);
                }
            }
            return method;
    }
}
