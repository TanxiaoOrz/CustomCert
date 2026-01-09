package com.nickzhang.customcert.annotation;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 张骏山
 * @Date: 2026/1/5 22:02
 * @PackageName: com.nickzhang.customcert.annotation
 * @ClassName: Table
 * @Description: TODO
 * @Version: 1.0
 */
@TableName
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
    /**
     * 数据库表名称
     */
    @AliasFor(annotation = TableName.class, attribute = "value")
    String dbName() default "";
    /**
     * 对应xml文件中节点
     */
    String xmlName() default "";
    /**
     * 所属主表对应类
     */
    Class<?> belongTo() default Object.class;
     /**
      * 列表显示名称
      */
    String showName() default "";
}
