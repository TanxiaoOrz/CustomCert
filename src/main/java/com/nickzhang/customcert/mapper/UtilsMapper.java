package com.nickzhang.customcert.mapper;

import com.nickzhang.customcert.xml.ClassNoInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
     * 获取对应主表主键的关联子表主键值
     * @param mainTable 主表数据库名称
     * @param belongTable 子表数据库名称
     * @param mainTableMainId 主表主键字段
     * @param mainLinkColumn 主表链接字段
     * @param belongTableMainColumn 子表主键字段
     * @param belongLinkColumn 子表链接字段
     * @param mainId 主表主键值
     * @return 关联子表主键值列表
     */
    @Select("select ${belongTable}.${belongTableMainColumn} from ${belongTable} right join ${mainTable} on ${belongTable}.${belongLinkColumn} = ${mainTable}.${mainLinkColumn} where ${mainTable}.${mainLinkColumn} = #{mainId}")
    List<String> getBelongTableIds(String mainTable, String belongTable, String mainTableMainId, String mainLinkColumn, String belongTableMainColumn, String belongLinkColumn, String mainId);

     /**
      * 根据编号获取主表主键值
      * @param classNoInfo 编号值
      * @return 主表主键值
      */
     @Select("select ${classNoInfo.mainColumn} from ${classNoInfo.mainTable} where ${classNoInfo.noColumn} = #{no}")
     String getIdFromNo(ClassNoInfo classNoInfo,String no);
     /**
      * 根据主键值获取编号
      * @param classNoInfo 编号值
      * @param id 主表主键值
      * @return 编号
      */
     @Select("select ${classNoInfo.noColumn} from ${classNoInfo.mainTable} where ${classNoInfo.mainColumn} = #{id}")
     String getNoFormId(ClassNoInfo classNoInfo, String id);
}
