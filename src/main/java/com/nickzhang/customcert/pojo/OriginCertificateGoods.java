package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;

/**
 * @Author: 张骏山
 * @Date: 2026/1/11 20:40
 * @PackageName: com.nickzhang.customcert.pojo
 * @ClassName: OriginCertificateGoods
 * @Description: 原产地证书货物明细实体类
 * @Version: 1.0
 */
@Data
@TableName(value = "Ori_B_Cert_Goods") // 新规则：映射数据库完整原始表名
@Table(xmlName = "CertificateList/Goods",
        belongTo = OriginCertificate.class) // xmlName仅保留业务节点，不重复上级CertificateList
public class OriginCertificateGoods {

    /**
     * 货物ID
     */
    @Column(xmlName = "") // 仅映射XML子节点，无dbName属性
    @TableField(value = "Goods_Id") // 新规则：映射数据库原始下划线字段
    @TableId
    private String goodsId;

    /**
     * 证书ID（关联dbo.Ori_B_Cert_Edi表）
     */
    @Column(joinKey = true, joinColumn = "certId")
    @TableField(value = "Cert_Id")
    private String certId;

    /**
     * 证书编号（关联dbo.Ori_B_Cert_Edi表）
     */
    @Column(xmlName = "")
    @TableField(value = "Cert_No")
    private String certNo;

    /**
     * 证书种类
     */
    @Column(xmlName = "CertKind")
    @TableField(value = "Cert_Kind")
    private String certKind;

    /**
     * 货物编号（按该字段排序，6条明细记录）
     */
    @Column(xmlName = "GNo")
    @TableField(value = "Good_No")
    private String goodNo;

    /**
     * 货物描述
     */
    @Column(xmlName = "GoodsDesc")
    @TableField(value = "Goods_Desc")
    private String goodsDesc;

    /**
     * 包装数量
     */
    @Column(xmlName = "PackQty")
    @TableField(value = "Pack_Quantity")
    private String packQuantity;

    /**
     * 包装单位
     */
    @Column(xmlName = "PackUnit")
    @TableField(value = "Pack_Unit")
    private String packUnit;

    /**
     * 货物数量
     */
    @Column(xmlName = "GoodsQty")
    @TableField(value = "Goods_Quantity")
    private String goodsQuantity;

    /**
     * 货物单位
     */
    @Column(xmlName = "GoodsUnit")
    @TableField(value = "Goods_Unit")
    private String goodsUnit;

    /**
     * 单价
     */
    @Column(xmlName = "Price")
    @TableField(value = "Price")
    private String price;

    /**
     * 币种代码
     */
    @Column(xmlName = "Ccy")
    @TableField(value = "Ccy")
    private String ccy;

    /**
     * HS编码
     */
    @Column(xmlName = "HsCode")
    @TableField(value = "Hs_Code")
    private String hsCode;

    /**
     * 原产地标准
     */
    @Column(xmlName = "OriginCriteria")
    @TableField(value = "Origin_Criteria")
    private String originCriteria;

    /**
     * 原产地标准参考
     */
    @Column(xmlName = "OriCriteriaRef")
    @TableField(value = "Ori_Criteria_Ref")
    private String oriCriteriaRef;

    /**
     * FOB价值
     */
    @Column(xmlName = "FOBValue")
    @TableField(value = "Fob_Value")
    private String fobValue;

    /**
     * 货物名称（中文）
     */
    @Column(xmlName = "GoodsName")
    @TableField(value = "Goods_Name")
    private String goodsName;

    /**
     * 货物名称（英文）
     */
    @Column(xmlName = "GoodsNameE")
    @TableField(value = "Goods_Name_E")
    private String goodsNameE;

    /**
     * 毛重
     */
    @Column(xmlName = "GrossWt")
    @TableField(value = "Gross_Weight")
    private String grossWeight;

    /**
     * 净重
     */
    @Column(xmlName = "NetWt")
    @TableField(value = "Net_Weight")
    private String netWeight;

    /**
     * 重量单位
     */
    @Column(xmlName = "WeightUnit")
    @TableField(value = "Weight_Unit")
    private String weightUnit;

    /**
     * 货物数量参考
     */
    @Column(xmlName = "GoodsQtyRef")
    @TableField(value = "Goods_Quantity_Ref")
    private String goodsQuantityRef;

    /**
     * 货物单位参考
     */
    @Column(xmlName = "GoodsUnitRef")
    @TableField(value = "Goods_Unit_Ref")
    private String goodsUnitRef;

    /**
     * 检验检疫注册号
     */
    @Column(xmlName = "CiqRegNo")
    @TableField(value = "Ciq_Reg_No")
    private String ciqRegNo;

    /**
     * 生产商
     */
    @Column(xmlName = "Producer")
    @TableField(value = "Producer")
    private String producer;

    /**
     * 生产厂家
     */
    @Column(xmlName = "Manufacture")
    @TableField(value = "Manufacture")
    private String manufacture;

    /**
     * 联系人
     */
    @Column(xmlName = "Contactor")
    @TableField(value = "Contactor")
    private String contactor;

    /**
     * 联系电话
     */
    @Column(xmlName = "Tel")
    @TableField(value = "Tel")
    private String tel;

    /**
     * 打印编号
     */
    @Column(xmlName = "PrintNo")
    @TableField(value = "Print_No")
    private String printNo;

    /**
     * 发票金额
     */
    @Column(xmlName = "InvValue")
    @TableField(value = "Inv_Value")
    private String invValue;

    /**
     * 申请货物标准
     */
    @Column(xmlName = "AplGoodsCriteria")
    @TableField(value = "Apl_Goods_Criteria")
    private String aplGoodsCriteria;

    /**
     * 进口百分比
     */
    @Column(xmlName = "ImportPercent")
    @TableField(value = "Import_Percent")
    private String importPercent;

    /**
     * 申报编号
     */
    @Column(xmlName = "DeclNo")
    @TableField(value = "Decl_No")
    private String declNo;

    /**
     * 许可证编号
     */
    @Column(xmlName = "LicenseNo")
    @TableField(value = "License_No")
    private String licenseNo;

    /**
     * 生产商传真
     */
    @Column(xmlName = "ProducerFax")
    @TableField(value = "Producer_Fax")
    private String producerFax;

    /**
     * 生产商邮箱
     */
    @Column(xmlName = "ProducerEmail")
    @TableField(value = "Producer_Email")
    private String producerEmail;

    /**
     * 原产地标准子项
     */
    @Column(xmlName = "OriCriteriaSub")
    @TableField(value = "Ori_Criteria_Sub")
    private String oriCriteriaSub;

    /**
     * 货物单位（中文）
     */
    @Column(xmlName = "GoodsUnitCn")
    @TableField(value = "Goods_Unit_Cn")
    private String goodsUnitCn;

    /**
     * 货物原产国（中文）
     */
    @Column(xmlName = "GoodsOriginCountry")
    @TableField(value = "Goods_Origin_Country")
    private String goodsOriginCountry;

    /**
     * 货物原产国（英文）
     */
    @Column(xmlName = "GoodsOriginCountryEn")
    @TableField(value = "Goods_Origin_CountryEn")
    private String goodsOriginCountryEn;

    /**
     * 货物税率
     */
    @Column(xmlName = "GoodsTaxRate")
    @TableField(value = "Goods_TaxRate")
    private String goodsTaxRate;

    /**
     * 发票编号
     */
    @Column(xmlName = "InvNo")
    @TableField(value = "Inv_No")
    private String invNo;

    /**
     * 生产商证书标识
     */
    @Column(xmlName = "ProducerSertFlag")
    @TableField(value = "ProducerSert_Flag")
    private String producerSertFlag;

    /**
     * 原产地标准补充
     */
    @Column(xmlName = "OriginCriteriaReplenish")
    @TableField(value = "Origin_Criteria_Replenish")
    private String originCriteriaReplenish;

    /**
     * 原产地标准单独项
     */
    @Column(xmlName = "OriginCriteriaAlone")
    @TableField(value = "Origin_Criteria_Alone")
    private String originCriteriaAlone;

    /**
     * 是否混合包装
     */
    @Column(xmlName = "PackType")
    @TableField(value = "Is_Mixed_Packing")
    private String isMixedPacking;

    /**
     * 第二货物数量参考
     */
    @Column(xmlName = "SecdGoodsQtyRef")
    @TableField(value = "SecdGoods_QtyRef")
    private String secdGoodsQtyRef;

    /**
     * 第二货物单位参考
     */
    @Column(xmlName = "SecdGoodsUnitRef")
    @TableField(value = "SecdGoods_UnitRef")
    private String secdGoodsUnitRef;

}