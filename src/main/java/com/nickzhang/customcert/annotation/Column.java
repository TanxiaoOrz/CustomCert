package com.nickzhang.customcert.annotation;

import com.baomidou.mybatisplus.annotation.TableField;
import com.nickzhang.customcert.data.ColumnType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 张骏山
 * @Date: 2026/1/5 21:18
 * @PackageName: com.nickzhang.customcert.annotation
 * @ClassName: Column
 * @Description: 继承TableField注解 传递value=>数据库名称<br/>exists=>是否存在该字段<br/>
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

    public final static String LINK_SEPARATOR = "-";
    public final static String XNL_SEPARATOR = "/";

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
//     /**
//      * 固定范围器, 用于固定文本选项时使用
//      *  暂时不用 后续优化时使用
//      */
//    String[] selectors() default {};


    /**
     * 是否主表链接字段
     */
    boolean  joinKey() default false;
     /**
      * 对应主表字段属性名称
      */
    String  joinColumn() default "id";
     /**
      * 是否是关联浏览字段 空置代表直接使用
      * @example: "TableName-NameColumn-MainColumn"
      * @tableName: 关联表数据库名称 必填
      * @showColumn: 关联表显示字段 必填
      * @idColumn: 关联表主键字段 选填 默认id
      */
    String linkTableColumn() default "";
     /**
      * 显示顺序 xml中升序
      */
    int order() default 999;
     /**
      * 默认值, 一般配合TableField.exist = false 使用
      */
    String defaultValue() default "";
     /**
      * 相同取值xml节点名称数组, 用于不同节点取值逻辑相同时使用
      * @example: {"xmlNode1-order1","xmlNode2-order2"}
      */
    String[] equalXml() default {};
    /**
     * 列表显示名称
     */
    String showName() default "";

    /**
     * 是否是编号字段
     */
    boolean isNo() default false;

    /**
     * 字段类型
     */
    ColumnType fieldType() default ColumnType.String;
}
