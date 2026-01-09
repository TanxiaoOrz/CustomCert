package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
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
@Table(xmlName = "ITF_DCL_IO_DECL_GOODS_PACK",
        dbName = "Dcl_B_Io_Decl_Goods_Pack",
        belongTo = ExitInspectionQuarantineOrderGoods.class)
public class ExitInspectionQuarantineOrderGoodsPack {

    /**
     * 包装ID
     */
    @Column(xmlName = "Pack_Id", dbName = "Pack_Id")
    @TableId
    private String packId;

    /**
     * 货物ID（关联货物明细表）
     */
    @Column(xmlName = "Goods_Id", dbName = "Goods_Id",joinKey = true,joinColumn = "Decl_Id")
    private String goodsId;

    /**
     * 企业申报编号（关联主表）
     */
    @Column(xmlName = "Ent_Decl_No", dbName = "Ent_Decl_No")
    private String entDeclNo;

    /**
     * 货物编号（关联货物明细表）
     */
    @Column(xmlName = "Goods_No", dbName = "Goods_No")
    private String goodsNo;

    /**
     * 辅助包装材料长
     */
    @Column(xmlName = "PackLenth", dbName = "Pack_Lenth")
    private String packLenth;

    /**
     * 辅助包装材料高
     */
    @Column(xmlName = "PackHigh", dbName = "Pack_High")
    private String packHigh;

    /**
     * 辅助包装材料宽
     */
    @Column(xmlName = "PackWide", dbName = "Pack_Wide")
    private String packWide;

    /**
     * 辅助包装材料种类代码
     */
    @Column(xmlName = "PackTypeCode", dbName = "Pack_Type_Code")
    private String packTypeCode;

    /**
     * 包装种类名称
     */
    @Column(xmlName = "PackCatgName", dbName = "Pack_Catg_Name")
    private String packCatgName;

    /**
     * 包装种类中文简称
     */
    @Column(xmlName = "PackTypeShort", dbName = "Pack_Type_Short")
    private String packTypeShort;

    /**
     * 包装件数
     */
    @Column(xmlName = "PackQty", dbName = "Pack_Qty")
    private Integer packQty;

    /**
     * 是否主要包装
     */
    @Column(xmlName = "IsMainPack", dbName = "Is_Main_Pack")
    private String isMainPack;

    /**
     * 包装材料种类
     */
    @Column(xmlName = "MatType", dbName = "Mat_Type")
    private String matType;

    /**
     * 加工厂家
     */
    @Column(xmlName = "ProcFactory", dbName = "Proc_Factory")
    private String procFactory;

    /**
     * 配套代码
     */
    @Column(xmlName = "Matting_Code", dbName = "Matting_Code")
    private String mattingCode;
}