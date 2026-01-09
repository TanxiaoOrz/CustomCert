package com.nickzhang.customcert.annotation;

import org.apache.ibatis.annotations.Mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 张骏山
 * @Date: 2026/1/9 19:27
 * @PackageName: com.nickzhang.customcert.annotation
 * @ClassName: TableMapper
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableMapper {
    /**
     * 对象类名
     */
    String value();
}
