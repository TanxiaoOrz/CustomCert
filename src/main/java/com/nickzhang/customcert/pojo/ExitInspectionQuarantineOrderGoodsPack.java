package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import lombok.Data;

/**
 * @Author: 张骏山
 * @Date: 2026/1/9 11:32
 * @PackageName: com.nickzhang.customcert.pojo
 * @ClassName: ExitInspectionQuarantineOrderGoodsPack
 * @Description: 出入境检疫单货物包装明细实体类
 * @Version: 1.0
 */
@Data
// 1. 表名映射：添加 MyBatis-Plus @TableName，value 与 @Table.dbName 一致
@TableName("Dcl_B_Io_Decl_Goods_Pack")
// 2. 保留自定义 @Table 注解，维护所有业务属性（xmlName、dbName、belongTo）
@Table(
        xmlName = "ITF_DCL_IO_DECL_GOODS_PACK",
        dbName = "Dcl_B_Io_Decl_Goods_Pack",
        belongTo = ExitInspectionQuarantineOrderGoods.class
)
public class ExitInspectionQuarantineOrderGoodsPack {

    /**
     * 包装ID
     */
    @TableId
    // 3. 字段映射：添加 MyBatis-Plus @TableField，value 与 @Column.dbName 一致
    @TableField("Pack_Id")
    @Column(xmlName = "Pack_Id", dbName = "Pack_Id")
    private String packId;

    /**
     * 货物ID（关联货物明细表）
     */
    @TableField("Goods_Id")
    @Column(xmlName = "Goods_Id", dbName = "Goods_Id", joinKey = true, joinColumn = "goodsId")
    private String goodsId;

    /**
     * 企业申报编号（关联主表）
     */
    @TableField("Ent_Decl_No")
    @Column(xmlName = "Ent_Decl_No", dbName = "Ent_Decl_No")
    private String entDeclNo;

    /**
     * 货物编号（关联货物明细表）
     */
    @TableField("Goods_No")
    @Column(xmlName = "Goods_No", dbName = "Goods_No")
    private String goodsNo;

    /**
     * 辅助包装材料长
     */
    @TableField("Pack_Lenth")
    @Column(xmlName = "PackLenth", dbName = "Pack_Lenth")
    private String packLenth;

    /**
     * 辅助包装材料高
     */
    @TableField("Pack_High")
    @Column(xmlName = "PackHigh", dbName = "Pack_High")
    private String packHigh;

    /**
     * 辅助包装材料宽
     */
    @TableField("Pack_Wide")
    @Column(xmlName = "PackWide", dbName = "Pack_Wide")
    private String packWide;

    /**
     * 辅助包装材料种类代码
     */
    @TableField("Pack_Type_Code")
    @Column(xmlName = "PackTypeCode", dbName = "Pack_Type_Code")
    private String packTypeCode;

    /**
     * 包装种类名称
     */
    @TableField("Pack_Catg_Name")
    @Column(xmlName = "PackCatgName", dbName = "Pack_Catg_Name")
    private String packCatgName;

    /**
     * 包装种类中文简称
     */
    @TableField("Pack_Type_Short")
    @Column(xmlName = "PackTypeShort", dbName = "Pack_Type_Short")
    private String packTypeShort;

    /**
     * 包装件数
     */
    @TableField("Pack_Qty")
    @Column(xmlName = "PackQty", dbName = "Pack_Qty")
    private String packQty;

    /**
     * 是否主要包装
     */
    @TableField("Is_Main_Pack")
    @Column(xmlName = "IsMainPack", dbName = "Is_Main_Pack")
    private String isMainPack;

    /**
     * 包装材料种类
     */
    @TableField("Mat_Type")
    @Column(xmlName = "MatType", dbName = "Mat_Type")
    private String matType;

    /**
     * 加工厂家
     */
    @TableField("Proc_Factory")
    @Column(xmlName = "ProcFactory", dbName = "Proc_Factory")
    private String procFactory;

    /**
     * 配套代码
     */
    @TableField("Matting_Code")
    @Column(xmlName = "Matting_Code", dbName = "Matting_Code")
    private String mattingCode;
}