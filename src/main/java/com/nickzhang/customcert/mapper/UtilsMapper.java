package com.nickzhang.customcert.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: 张骏山
 * @Date: 2026/1/7 15:04
 * @PackageName: com.nickzhang.customcert.mapper
 * @ClassName: UtilsMapper
 * @Description: 特殊mapper
 * @Version: 1.0
 */
@Mapper
public interface UtilsMapper {

    /**
     * 获取关联浏览字段显示名称
     * @param tableName 关联表数据库名称
     * @param showColumn 关联表显示字段
     * @param idColumn 关联表主键字段 选填 默认id
     * @param idValue 关联表主键值 必填
     * @return 关联浏览字段显示名称
     */
    @Select("select ${showColumn} from ${tableName} where ${idColumn} = #{idValue}")
    String getBrowserShowName(String tableName, String showColumn,String idColumn,String idValue);
    /**
     * 获取关联浏览字段显示名称 关联表主键字段 选填 默认id
     * @param tableName 关联表数据库名称
     * @param showColumn 关联表显示字段
     * @param idValue 关联表主键值 必填
     * @return 关联浏览字段显示名称
     */
    @Select("select ${showColumn} from ${tableName} where id = #{idValue}")
    String getBrowserShowName(String tableName, String showColumn,String idValue);
}
