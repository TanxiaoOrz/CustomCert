package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
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
@Table(xmlName = "ITF_DCL_IO_DECL_GOODS", dbName = "Dcl_B_Io_Decl_Goods",belongTo = ExitInspectionQuarantineOrder.class)
public class ExitInspectionQuarantineOrderGoods {

    /**
     * 货物ID
     */
    @TableId
    @Column(xmlName = "Goods_Id", dbName = "Goods_Id")
    private String goodsId;

    /**
     * 申报ID
     */

    @Column(xmlName = "Decl_Id", dbName = "Decl_Id",joinKey = true,joinColumn = "Decl_Id")
    private String declId;

    /**
     * 企业申报编号
     */
    @Column(xmlName = "Ent_Decl_No", dbName = "Ent_Decl_No")
    private String entDeclNo;

    /**
     * 货物编号
     */
    @Column(xmlName = "Goods_No", dbName = "Goods_No")
    private String goodsNo;

    /**
     * HS编号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL_GOODS_CONT/ProdHsCode", dbName = "Prod_Hs_Code")
    private String prodHsCode;

    /**
     * HS编码描述
     */
    @Column(xmlName = "HsCodeDesc", dbName = "Hs_Code_Desc")
    private String hsCodeDesc;

    /**
     * 申报货物名称（中文）
     */
    @Column(xmlName = "ITF_DCL_IO_DECL_GOODS_LIMIT_VN/ProdCnnm", dbName = "Decl_Goods_Cname")
    private String declGoodsCname;

    /**
     * 申报货物名称（外文）
     */
    @Column(xmlName = "DeclGoodsEname", dbName = "Decl_Goods_Ename")
    private String declGoodsEname;

    /**
     * 货物规格
     */
    @Column(xmlName = "GoodsSpec", dbName = "Goods_Spec")
    private String goodsSpec;

    /**
     * 货物型号
     */
    @Column(xmlName = "GoodsModel", dbName = "Goods_Model")
    private String goodsModel;

    /**
     * 原产地区代码
     */
    @Column(xmlName = "OrigPlaceCode", dbName = "Orig_Place_Code")
    private String origPlaceCode;

    /**
     * 原产国代码
     */
    @Column(xmlName = "CiqOriginCountry", dbName = "Ori_Ctry_Code")
    private String oriCtryCode;

    /**
     * 货物品牌
     */
    @Column(xmlName = "GoodsBrand", dbName = "Goods_Brand")
    private String goodsBrand;

    /**
     * 检验检疫类别
     */
    @Column(xmlName = "InspType", dbName = "Insp_Type")
    private String inspType;

    /**
     * 重量
     */
    @Column(xmlName = "CiqWeight", dbName = "Weight")
    private String weight;

    /**
     * 重量计量单位
     */
    @Column(xmlName = "CiqWtMeasUnit", dbName = "Wt_Meas_Unit")
    private String wtMeasUnit;

    /**
     * 标准重量
     */
    @Column(xmlName = "StdWeight", dbName = "Std_Weight")
    private String stdWeight;

    /**
     * 标准重量单位代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL_GOODS_CONT/CiqWtUnitCode", dbName = "Std_Weight_Unit_Code")
    private String stdWeightUnitCode;

    /**
     * 数量
     */
    @Column(xmlName = "CiqQty", dbName = "Qty")
    private String qty;

    /**
     * 数量计量单位
     */
    @Column(xmlName = "CiqQtyMeasUnit", dbName = "Qty_Meas_Unit")
    private String qtyMeasUnit;

    /**
     * 标准数量
     */
    @Column(xmlName = "Qty1", dbName = "Std_Qty")
    private String stdQty;

    /**
     * 标准数量单位代码
     */
    @Column(xmlName = "Unit1", dbName = "Std_Qty_Unit_Code")
    private String stdQtyUnitCode;

    /**
     * 币种代码
     */
    @Column(xmlName = "CiqCurr", dbName = "Currency")
    private String currency;

    /**
     * 单价
     */
    @Column(xmlName = "PricePerUnit", dbName = "Price_Per_Unit")
    private String pricePerUnit;

    /**
     * 货物总值
     */
    @Column(xmlName = "GoodsTotalVal", dbName = "Goods_Total_Val")
    private String goodsTotalVal;

    /**
     * 货物总值（美元）
     */
    @Column(xmlName = "Total_Val_Us", dbName = "Total_Val_Us")
    private String totalValUs;

    /**
     * 货物总值（人民币）
     */
    @Column(xmlName = "Total_Val_Cn", dbName = "Total_Val_Cn")
    private String totalValCn;

    /**
     * 监管类别名称
     */
    @Column(xmlName = "CiqCode", dbName = "Ciq_Code")
    private String ciqCode;

    /**
     * 监管类别名称（全称）
     */
    @Column(xmlName = "Ciq_Name", dbName = "Ciq_Name")
    private String ciqName;

    /**
     * 检验检疫分类代码
     */
    @Column(xmlName = "Ciq_Classify_Code", dbName = "Ciq_Classify_Code")
    private String ciqClassifyCode;

    /**
     * 检验检疫分类名称
     */
    @Column(xmlName = "Ciq_Classify_Name", dbName = "Ciq_Classify_Name")
    private String ciqClassifyName;

    /**
     * 情况代码
     */
    @Column(xmlName = "Situation_Code", dbName = "Situation_Code")
    private String situationCode;

    /**
     * 情况等级
     */
    @Column(xmlName = "Situation_Level", dbName = "Situation_Level")
    private String situationLevel;

    /**
     * 用途代码
     */
    @Column(xmlName = "Purpose", dbName = "Purpose")
    private String purpose;

    /**
     * 生产单位统一社会信用代码
     */
    @Column(xmlName = "MnufctrRegNo", dbName = "Mnufctr_Reg_No")
    private String mnufctrRegNo;

    /**
     * 生产日期
     */
    @Column(xmlName = "ProduceDate", dbName = "Produce_Date")
    private String produceDate;

    /**
     * 生产批号
     */
    @Column(xmlName = "ProdBatchNo", dbName = "Prod_Batch_No")
    private String prodBatchNo;

    /**
     * 产品有效期
     */
    @Column(xmlName = "ProdValidDt", dbName = "Prod_Valid_Dt")
    private String prodValidDt;

    /**
     * 产品保质期
     */
    @Column(xmlName = "ProdQgp", dbName = "Prod_Qgp")
    private String prodQgp;

    /**
     * 货物属性代码
     */
    @Column(xmlName = "GoodsAttr", dbName = "Goods_Attr")
    private String goodsAttr;

    /**
     * 货物属性名称
     */
    @Column(xmlName = "Goods_Attr_Name", dbName = "Goods_Attr_Name")
    private String goodsAttrName;

    /**
     * 成份/原料/组份
     */
    @Column(xmlName = "Stuff", dbName = "Stuff")
    private String stuff;

    /**
     * UN编码
     */
    @Column(xmlName = "UnCode", dbName = "Un_Code")
    private String unCode;

    /**
     * 危险货物名称
     */
    @Column(xmlName = "DangName", dbName = "Dang_Name")
    private String dangName;

    /**
     * 危包类别
     */
    @Column(xmlName = "PackType", dbName = "Pack_Type")
    private String packType;

    /**
     * 危包规格
     */
    @Column(xmlName = "PackSpec", dbName = "Pack_Spec")
    private String packSpec;

    /**
     * 海关监管条件
     */
    @Column(xmlName = "Custm_Spv_Cond", dbName = "Custm_Spv_Cond")
    private String custmSpvCond;

    /**
     * 产品标签图片
     */
    @Column(xmlName = "Prod_Tag_Pic", dbName = "Prod_Tag_Pic")
    private String prodTagPic;

    /**
     * 是否列名货物
     */
    @Column(xmlName = "Is_List_Goods", dbName = "Is_List_Goods")
    private String isListGoods;

    /**
     * 舱单号
     */
    @Column(xmlName = "Cabin_No", dbName = "Cabin_No")
    private String cabinNo;

    /**
     * 车皮号
     */
    @Column(xmlName = "Wagon_No", dbName = "Wagon_No")
    private String wagonNo;

    /**
     * 备用一
     */
    @Column(xmlName = "By1", dbName = "By1")
    private String by1;

    /**
     * 备用二
     */
    @Column(xmlName = "By2", dbName = "By2")
    private String by2;

    /**
     * 境外生产企业名称
     */
    @Column(xmlName = "EngManEntCnm", dbName = "Eng_Man_Ent_Cnm")
    private String engManEntCnm;

    /**
     * 汇率
     */
    @Column(xmlName = "Rate", dbName = "Rate")
    private String rate;

    /**
     * 生产单位名称
     */
    @Column(xmlName = "MnufctrRegName", dbName = "Mnufctr_Reg_Name")
    private String mnufctrRegName;

    /**
     * 原产地区名称
     */
    @Column(xmlName = "Orig_Place_Name", dbName = "Orig_Place_Name")
    private String origPlaceName;

    /**
     * 原产国名称
     */
    @Column(xmlName = "Ori_Ctry_Name", dbName = "Ori_Ctry_Name")
    private String oriCtryName;

    /**
     * 重量单位名称
     */
    @Column(xmlName = "Wt_Unit_Name", dbName = "Wt_Unit_Name")
    private String wtUnitName;

    /**
     * 标准重量单位名称
     */
    @Column(xmlName = "Std_Weight_Unit_Name", dbName = "Std_Weight_Unit_Name")
    private String stdWeightUnitName;

    /**
     * 数量单位名称
     */
    @Column(xmlName = "Qty_Unit_Name", dbName = "Qty_Unit_Name")
    private String qtyUnitName;

    /**
     * 标准数量单位名称
     */
    @Column(xmlName = "Std_Qty_Unit_Name", dbName = "Std_Qty_Unit_Name")
    private String stdQtyUnitName;

    /**
     * 币种名称
     */
    @Column(xmlName = "Currency_Name", dbName = "Currency_Name")
    private String currencyName;

    /**
     * 用途名称
     */
    @Column(xmlName = "Purpose_Name", dbName = "Purpose_Name")
    private String purposeName;

    /**
     * 非危险化学品标识
     */
    @Column(xmlName = "NoDangerFlag", dbName = "No_Dang_Flag")
    private String noDangFlag;

    /**
     * 合同序号
     */
    @Column(xmlName = "Contract_Seq_No", dbName = "Contract_Seq_No")
    private String contractSeqNo;

    /**
     * HS标准量计量单位类型
     */
    @Column(xmlName = "StdUnitTypeCode", dbName = "Std_UnitType_Code")
    private String stdUnitTypeCode;

    /**
     * 监管要求
     */
    @Column(xmlName = "SupvDmd", dbName = "SupvDmd")
    private String supvDmd;

}
