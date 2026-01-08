package com.nickzhang.customcert.annotation;

import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @Author: 张骏山
 * @Date: 2026/1/5 21:18
 * @PackageName: com.nickzhang.customcert.annotation
 * @ClassName: Column
 * @Description: 继承TableField注解 传递value=>数据库名称<br/>exists=>是否存在该字段<br/>
 * @Version: 1.0
 */
@TableField
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    @AliasFor(annotation = TableField.class, attribute = "value")
    String dbName() default "";
    @AliasFor(annotation = TableField.class, attribute = "exists")
    boolean exists() default true;
    /**
     * 对应xml文件中字段名称,多级名称用/分割
     * @example: "xmlNode1/xmlNode2/xmlNode3"
     */
    String xmlName() default "";
    /**
     * 是否在列表中显示
     */
    boolean listShow() default false;

     /**
      * 是否在查询中显示
      */
    boolean searchShow() default false;
    /**
     * 是否主表链接字段
     */
    boolean  joinKey() default false;
     /**
      * 对应主表字段数据库名称
      */
    String  joinColumn() default "id";
     /**
      * 是否是关联浏览字段 空置代表直接使用
      * @example: "TableName-NameColumn-MainColumn"
      * @tableName: 关联表数据库名称 必填
      * @showColumn: 关联表显示字段 必填
      * @idColumn: 关联表主键字段 选填 默认id
      */
    String linkColumn() default "";

}
