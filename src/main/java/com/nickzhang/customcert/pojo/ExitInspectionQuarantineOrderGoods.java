package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import lombok.Data;

/**
 * @Author: 张骏山
 * @Date: 2026/1/5 22:43
 * @PackageName: com.nickzhang.customcert.pojo
 * @ClassName: ExitInspectionQuarantineOrderGoods
 * @Description: 出入境检疫单商品明细
 * @Version: 1.0
 */
@Data
// 1. 表名映射：添加 @TableName，value 与 @Table.dbName 保持一致
@TableName("Dcl_B_Io_Decl_Goods")
// 2. 保留自定义 @Table 注解，维护业务属性（xmlName、dbName、belongTo）
@Table(
        xmlName = "ITF_DCL_IO_DECL_GOODS",
        belongTo = ExitInspectionQuarantineOrder.class
)
public class ExitInspectionQuarantineOrderGoods {

    /**
     * 货物ID
     */
    @TableId
    // 冗余节点：xmlName 置为空
    @TableField("Goods_Id")
    @Column(xmlName = "")
    private String goodsId;

    /**
     * 申报ID
     */
    // 冗余节点：xmlName 置为空（仅用于关联，不生成XML节点）
    @TableField("Decl_Id")
    @Column(xmlName = "", joinKey = true, joinColumn = "declId")
    private String declId;

    /**
     * 企业申报编号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ent_Decl_No")
    @Column(xmlName = "")
    private String entDeclNo;

    /**
     * 货物编号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Goods_No")
    @Column(xmlName = "")
    private String goodsNo;

    /**
     * HS编号
     */
    @TableField("Prod_Hs_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL_GOODS_CONT/ProdHsCode")
    private String prodHsCode;

    /**
     * HS编码描述
     */
    // 冗余节点：xmlName 置为空
    @TableField("Hs_Code_Desc")
    @Column(xmlName = "")
    private String hsCodeDesc;

    /**
     * 申报货物名称（中文）
     */
    @TableField("Decl_Goods_Cname")
    @Column(xmlName = "ITF_DCL_IO_DECL_GOODS_LIMIT/ITF_DCL_IO_DECL_GOODS_LIMIT_VN/ProdCnnm")
    private String declGoodsCname;

    /**
     * 申报货物名称（外文）
     */
    // 冗余节点：xmlName 置为空
    @TableField("Decl_Goods_Ename")
    @Column(xmlName = "")
    private String declGoodsEname;

    /**
     * 货物规格
     */
    // 冗余节点：xmlName 置为空
    @TableField("Goods_Spec")
    @Column(xmlName = "")
    private String goodsSpec;

    /**
     * 货物型号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Goods_Model")
    @Column(xmlName = "")
    private String goodsModel;

    /**
     * 原产地区代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Orig_Place_Code")
    @Column(xmlName = "")
    private String origPlaceCode;

    /**
     * 原产国代码
     */
    @TableField("Ori_Ctry_Code")
    @Column(xmlName = "CiqOriginCountry")
    private String oriCtryCode;

    /**
     * 货物品牌
     */
    // 冗余节点：xmlName 置为空
    @TableField("Goods_Brand")
    @Column(xmlName = "")
    private String goodsBrand;

    /**
     * 检验检疫类别
     */
    // 冗余节点：xmlName 置为空
    @TableField("Insp_Type")
    @Column(xmlName = "")
    private String inspType;

    /**
     * 重量
     */
    @TableField("Weight")
    @Column(xmlName = "CiqWeight")
    private String weight;

    /**
     * 重量计量单位
     */
    @TableField("Wt_Meas_Unit")
    @Column(xmlName = "CiqWtMeasUnit")
    private String wtMeasUnit;

    /**
     * 标准重量
     */
    @TableField("Std_Weight")
    @Column(xmlName = "StdWeight")
    private String stdWeight;

    /**
     * 标准重量单位代码
     */
    @TableField("Std_Weight_Unit_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL_GOODS_CONT/CiqWtUnitCode")
    private String stdWeightUnitCode;

    /**
     * 数量
     */
    @TableField("Qty")
    @Column(xmlName = "CiqQty")
    private String qty;

    /**
     * 数量计量单位
     */
    @TableField("Qty_Meas_Unit")
    @Column(xmlName = "CiqQtyMeasUnit")
    private String qtyMeasUnit;

    /**
     * 标准数量
     */
    @TableField("Std_Qty")
    @Column(xmlName = "Qty1")
    private String stdQty;

    /**
     * 标准数量单位代码
     */
    @TableField("Std_Qty_Unit_Code")
    @Column(xmlName = "Unit1")
    private String stdQtyUnitCode;

    /**
     * 币种代码
     */
    @TableField("Currency")
    @Column(xmlName = "CiqCurr", linkTableColumn = "Ccl_C_Map_Curr-C_Scode-Q_Code")
    private String currency;

    /**
     * 单价
     */
    // 冗余节点：xmlName 置为空
    @TableField("Price_Per_Unit")
    @Column(xmlName = "")
    private String pricePerUnit;

    /**
     * 货物总值
     */
    @TableField("Goods_Total_Val")
    @Column(xmlName = "GoodsTotalVal")
    private String goodsTotalVal;

    /**
     * 货物总值（美元）
     */
    // 冗余节点：xmlName 置为空
    @TableField("Total_Val_Us")
    @Column(xmlName = "")
    private String totalValUs;

    /**
     * 货物总值（人民币）
     */
    // 冗余节点：xmlName 置为空
    @TableField("Total_Val_Cn")
    @Column(xmlName = "")
    private String totalValCn;

    /**
     * 监管类别名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ciq_Code")
    @Column(xmlName = "")
    private String ciqCode;

    /**
     * 监管类别名称（全称）
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ciq_Name")
    @Column(xmlName = "")
    private String ciqName;

    /**
     * 检验检疫分类代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ciq_Classify_Code")
    @Column(xmlName = "")
    private String ciqClassifyCode;

    /**
     * 检验检疫分类名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ciq_Classify_Name")
    @Column(xmlName = "")
    private String ciqClassifyName;

    /**
     * 情况代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Situation_Code")
    @Column(xmlName = "")
    private String situationCode;

    /**
     * 情况等级
     */
    // 冗余节点：xmlName 置为空
    @TableField("Situation_Level")
    @Column(xmlName = "")
    private String situationLevel;

    /**
     * 用途代码
     */
    @TableField("Purpose")
    @Column(xmlName = "Purpose")
    private String purpose;

    /**
     * 生产单位统一社会信用代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Mnufctr_Reg_No")
    @Column(xmlName = "")
    private String mnufctrRegNo;

    /**
     * 生产日期
     */
    // 冗余节点：xmlName 置为空
    @TableField("Produce_Date")
    @Column(xmlName = "")
    private String produceDate;

    /**
     * 生产批号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Prod_Batch_No")
    @Column(xmlName = "")
    private String prodBatchNo;

    /**
     * 产品有效期
     */
    // 冗余节点：xmlName 置为空
    @TableField("Prod_Valid_Dt")
    @Column(xmlName = "")
    private String prodValidDt;

    /**
     * 产品保质期
     */
    // 冗余节点：xmlName 置为空
    @TableField("Prod_Qgp")
    @Column(xmlName = "")
    private String prodQgp;

    /**
     * 货物属性代码
     */
    @TableField("Goods_Attr")
    @Column(xmlName = "GoodsAttr")
    private String goodsAttr;

    /**
     * 货物属性名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Goods_Attr_Name")
    @Column(xmlName = "")
    private String goodsAttrName;

    /**
     * 成份/原料/组份
     */
    // 冗余节点：xmlName 置为空
    @TableField("Stuff")
    @Column(xmlName = "")
    private String stuff;

    /**
     * UN编码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Un_Code")
    @Column(xmlName = "")
    private String unCode;

    /**
     * 危险货物名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Dang_Name")
    @Column(xmlName = "")
    private String dangName;

    /**
     * 危包类别
     */
    // 冗余节点：xmlName 置为空
    @TableField("Pack_Type")
    @Column(xmlName = "")
    private String packType;

    /**
     * 危包规格
     */
    // 冗余节点：xmlName 置为空
    @TableField("Pack_Spec")
    @Column(xmlName = "")
    private String packSpec;

    /**
     * 海关监管条件
     */
    // 冗余节点：xmlName 置为空
    @TableField("Custm_Spv_Cond")
    @Column(xmlName = "")
    private String custmSpvCond;

    /**
     * 产品标签图片
     */
    // 冗余节点：xmlName 置为空
    @TableField("Prod_Tag_Pic")
    @Column(xmlName = "")
    private String prodTagPic;

    /**
     * 是否列名货物
     */
    // 冗余节点：xmlName 置为空
    @TableField("Is_List_Goods")
    @Column(xmlName = "")
    private String isListGoods;

    /**
     * 舱单号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Cabin_No")
    @Column(xmlName = "")
    private String cabinNo;

    /**
     * 车皮号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Wagon_No")
    @Column(xmlName = "")
    private String wagonNo;

    /**
     * 备用一
     */
    // 冗余节点：xmlName 置为空
    @TableField("By1")
    @Column(xmlName = "")
    private String by1;

    /**
     * 备用二
     */
    // 冗余节点：xmlName 置为空
    @TableField("By2")
    @Column(xmlName = "")
    private String by2;

    /**
     * 境外生产企业名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Eng_Man_Ent_Cnm")
    @Column(xmlName = "")
    private String engManEntCnm;

    /**
     * 汇率
     */
    // 冗余节点：xmlName 置为空
    @TableField("Rate")
    @Column(xmlName = "")
    private String rate;

    /**
     * 生产单位名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Mnufctr_Reg_Name")
    @Column(xmlName = "")
    private String mnufctrRegName;

    /**
     * 原产地区名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Orig_Place_Name")
    @Column(xmlName = "")
    private String origPlaceName;

    /**
     * 原产国名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ori_Ctry_Name")
    @Column(xmlName = "")
    private String oriCtryName;

    /**
     * 重量单位名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Wt_Unit_Name")
    @Column(xmlName = "")
    private String wtUnitName;

    /**
     * 标准重量单位名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Std_Weight_Unit_Name")
    @Column(xmlName = "")
    private String stdWeightUnitName;

    /**
     * 数量单位名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Qty_Unit_Name")
    @Column(xmlName = "")
    private String qtyUnitName;

    /**
     * 标准数量单位名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Std_Qty_Unit_Name")
    @Column(xmlName = "")
    private String stdQtyUnitName;

    /**
     * 币种名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Currency_Name")
    @Column(xmlName = "")
    private String currencyName;

    /**
     * 用途名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Purpose_Name")
    @Column(xmlName = "")
    private String purposeName;

    /**
     * 非危险化学品标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("No_Dang_Flag")
    @Column(xmlName = "")
    private String noDangFlag;

    /**
     * 合同序号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Contract_Seq_No")
    @Column(xmlName = "")
    private String contractSeqNo;

    /**
     * HS标准量计量单位类型
     */
    // 冗余节点：xmlName 置为空
    @TableField("Std_UnitType_Code")
    @Column(xmlName = "")
    private String stdUnitTypeCode;

    /**
     * 监管要求
     */
    // 冗余节点：xmlName 置为空
    @TableField("SupvDmd")
    @Column(xmlName = "")
    private String supvDmd;
}