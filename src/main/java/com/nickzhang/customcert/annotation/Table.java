package com.nickzhang.customcert.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 张骏山
 * @Date: 2026/1/5 22:02
 * @PackageName: com.nickzhang.customcert.annotation
 * @ClassName: Table
 * @Description: 自定义xml生成类table注解
 * @Version: 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
    /**
     * 对应xml文件中节点,多节点使用${Column.XNL_SEPARATOR}分开
     */
    String xmlName() default "";
    /**
     * 所属主表对应类
     * default 时是为主表
     */
    Class<?> belongTo() default Object.class;
    /**
     * 是否为依赖于主表节点的连表
     * false 代表拥有独立节点的子表
     * 需要belongTo()指定主表 default 时是为主表
     */
    boolean isDependent() default false;

    /**
     * 是否为独立文件
     */
    boolean dividedFile() default false;
     /**
      * 列表显示名称
      */
    String showName() default "";

     /**
      * xml文件中schemaLocation属性值
      */
    String schemaLocation() default "";
     /**
      * xml文件中根节点属性
      * 目标格式: "xmlns:${前缀}=${uri}"
      * 输入格式: "前缀1,uri1,前缀2,uri2"
      *
      */
    String[] nameSpaces() default {};

     /**
      * 节点在xml文件中的顺序
      */
    int order() default 999;

}
