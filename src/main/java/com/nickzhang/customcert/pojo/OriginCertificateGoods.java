package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.OrderBy;
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
        belongTo = OriginCertificate.class,
        order = 64
) // xmlName仅保留业务节点，不重复上级CertificateList
public class OriginCertificateGoods {

    /**
     * 货物ID
     */
    @Column(xmlName = "") // 仅映射XML子节点，无dbName属性（保留原始空白配置，不修改）
    @TableField(value = "Goods_Id") // 新规则：映射数据库原始下划线字段
    @TableId
    private String goodsId;

    /**
     * 证书ID（关联dbo.Ori_B_Cert_Edi表）
     */
    @Column(joinKey = true, joinColumn = "certId") // 保留原始配置，不新增order（无对应XML节点）
    @TableField(value = "Cert_Id")
    private String certId;

    /**
     * 证书编号（关联dbo.Ori_B_Cert_Edi表）
     */
    @Column(xmlName = "") // 保留原始空白配置，不修改
    @TableField(value = "Cert_No")
    @OrderBy(asc = true)
    private String certNo;

    /**
     * 证书种类
     */
    @TableField(value = "Cert_Kind")
    private String certKind;

    /**
     * 货物编号（按该字段排序，6条明细记录）
     */
    @Column(xmlName = "GNo", order = 2) // 补充order=2，匹配XML节点顺序
    @TableField(value = "Good_No")
    private String goodNo;

    /**
     * 货物描述
     */
    @Column(xmlName = "GoodsDesc", order = 33) // 补充order=33，匹配XML节点顺序
    @TableField(value = "Goods_Desc")
    private String goodsDesc;

    /**
     * 包装数量
     */
    @Column(xmlName = "PackQty", order = 6) // 补充order=6，匹配XML节点顺序
    @TableField(value = "Pack_Quantity")
    private String packQuantity;

    /**
     * 包装单位
     */
    @Column(xmlName = "PackUnit", order = 7) // 补充order=7，匹配XML节点顺序
    @TableField(value = "Pack_Unit")
    private String packUnit;

    /**
     * 货物数量
     */
    @Column(xmlName = "GoodsQty", order = 8) // 补充order=8，匹配XML节点顺序
    @TableField(value = "Goods_Quantity")
    private String goodsQuantity;

    /**
     * 货物单位（英文）
     */
    @Column(xmlName = "GoodsUnitE", order = 9) // 补充order=9，匹配XML节点顺序
    @TableField(value = "Goods_Unit")
    private String goodsUnit;

    /**
     * 单价
     */
    @TableField(value = "Price")
    private String price;

    /**
     * 币种代码
     */
    @TableField(value = "Ccy")
    private String ccy;

    /**
     * HS编码
     */
    @Column(xmlName = "HSCode", order = 3) // 补充order=3，匹配XML节点顺序
    @TableField(value = "Hs_Code")
    private String hsCode;

    /**
     * 原产地标准
     */
    @Column(xmlName = "OriCriteria", order = 22) // 补充order=22，匹配XML节点顺序
    @TableField(value = "Origin_Criteria")
    private String originCriteria;

    /**
     * 原产地标准参考
     */
    @Column(xmlName = "OriCriteriaRef", order = 23) // 补充order=23，匹配XML节点顺序
    @TableField(value = "Ori_Criteria_Ref")
    private String oriCriteriaRef;

    /**
     * FOB价值
     */
    @Column(xmlName = "FOBValue", order = 20) // 补充order=20，匹配XML节点顺序
    @TableField(value = "Fob_Value")
    private String fobValue;

    /**
     * 货物名称（中文）
     */
    @Column(xmlName = "GoodsName", order = 4) // 补充order=4，匹配XML节点顺序
    @TableField(value = "Goods_Name")
    private String goodsName;

    /**
     * 货物名称（英文）
     */
    @Column(xmlName = "GoodsNameE", order = 5) // 补充order=5，匹配XML节点顺序
    @TableField(value = "Goods_Name_E")
    private String goodsNameE;

    /**
     * 毛重
     */
    @Column(xmlName = "GrossWt", order = 15) // 补充order=15，匹配XML节点顺序
    @TableField(value = "Gross_Weight")
    private String grossWeight;

    /**
     * 净重
     */
    @Column(xmlName = "NetWt", order = 16) // 补充order=16，匹配XML节点顺序
    @TableField(value = "Net_Weight")
    private String netWeight;

    /**
     * 重量单位
     */
    @Column(xmlName = "WtUnit", order = 17) // 补充order=17，匹配XML节点顺序（保留原始xmlName大小写，不修正）
    @TableField(value = "Weight_Unit")
    private String weightUnit;

    /**
     * 货物数量参考
     */
    @Column(xmlName = "GoodsQtyRef", order = 11) // 补充order=11，匹配XML节点顺序
    @TableField(value = "Goods_Quantity_Ref")
    private String goodsQuantityRef;

    /**
     * 货物单位参考
     */
    @Column(xmlName = "GoodsUnitRef", order = 12) // 补充order=12，匹配XML节点顺序
    @TableField(value = "Goods_Unit_Ref")
    private String goodsUnitRef;

    /**
     * 检验检疫注册号
     */
    @Column(xmlName = "CiqRegNo", order = 28) // 补充order=28，匹配XML节点顺序
    @TableField(value = "Ciq_Reg_No")
    private String ciqRegNo;

    /**
     * 生产商
     */
    @Column(xmlName = "Producer", order = 24) // 补充order=24，匹配XML节点顺序
    @TableField(value = "Producer")
    private String producer;

    /**
     * 生产厂家
     */
    @TableField(value = "Manufacture")
    private String manufacture;

    /**
     * 联系人
     */
    @TableField(value = "Contactor")
    private String contactor;

    /**
     * 生产商电话
     */
    @Column(xmlName = "PrdcEtpsTel", order = 31) // 补充order=31，匹配XML节点顺序
    @TableField(value = "Tel")
    private String tel;

    /**
     * 打印编号
     */
    @TableField(value = "Print_No")
    private String printNo;

    /**
     * 发票金额
     */
    @Column(xmlName = "InvValue", order = 19) // 补充order=19，匹配XML节点顺序
    @TableField(value = "Inv_Value")
    private String invValue;

    /**
     * 申请货物标准
     */
    @TableField(value = "Apl_Goods_Criteria")
    private String aplGoodsCriteria;

    /**
     * 进口百分比
     */
    @Column(xmlName = "ICompPrpr", order = 21) // 补充order=21，匹配XML节点顺序（对应进口成份比例）
    @TableField(value = "Import_Percent")
    private String importPercent;

    /**
     * 申报编号
     */
    @TableField(value = "Decl_No")
    private String declNo;

    /**
     * 许可证编号
     */
    @TableField(value = "License_No")
    private String licenseNo;

    /**
     * 生产商传真
     */
    @Column(xmlName = "ProducerFax", order = 26) // 补充order=26，匹配XML节点顺序
    @TableField(value = "Producer_Fax")
    private String producerFax;

    /**
     * 生产商邮箱
     */
    @Column(xmlName = "ProducerEmail", order = 27) // 补充order=27，匹配XML节点顺序
    @TableField(value = "Producer_Email")
    private String producerEmail;

    /**
     * 原产地标准子项
     */
    @Column(xmlName = "OriCriteriaSub", order = 34) // 补充order=34，匹配XML节点顺序
    @TableField(value = "Ori_Criteria_Sub")
    private String oriCriteriaSub;

    /**
     * 货物单位（中文）
     */
    @Column(xmlName = "GoodsUnit", order = 10) // 补充order=10，匹配XML节点顺序
    @TableField(value = "Goods_Unit_Cn")
    private String goodsUnitCn;

    /**
     * 货物原产国（中文）
     */
    @Column(xmlName = "GoodsOriginCountry", order = 35) // 补充order=35，匹配XML节点顺序
    @TableField(value = "Goods_Origin_Country")
    private String goodsOriginCountry;

    /**
     * 货物原产国（英文）
     */
    @Column(xmlName = "GoodsOriginCountryEn", order = 36) // 补充order=36，匹配XML节点顺序
    @TableField(value = "Goods_Origin_CountryEn")
    private String goodsOriginCountryEn;

    /**
     * 货物税率
     */
    @Column(xmlName = "GoodsTaxRate", order = 37) // 补充order=37，匹配XML节点顺序
    @TableField(value = "Goods_TaxRate")
    private String goodsTaxRate;

    /**
     * 发票编号
     */
    @Column(xmlName = "InvNo", order = 38) // 补充order=38，匹配XML节点顺序
    @TableField(value = "Inv_No")
    private String invNo;

    /**
     * 生产商证书标识
     */
    @Column(xmlName = "ProducerSertFlag", order = 32) // 补充order=32，匹配XML节点顺序
    @TableField(value = "ProducerSert_Flag")
    private String producerSertFlag;

    /**
     * 原产地标准补充
     */
    @TableField(value = "Origin_Criteria_Replenish")
    private String originCriteriaReplenish;

    /**
     * 原产地标准单独项
     */
    @TableField(value = "Origin_Criteria_Alone")
    private String originCriteriaAlone;

    /**
     * 是否混合包装
     */
    @Column(xmlName = "PackType", order = 39) // 补充order=39，匹配XML节点顺序
    @TableField(value = "Is_Mixed_Packing")
    private String isMixedPacking;

    /**
     * 第二货物数量参考
     */
    @Column(xmlName = "SecdGoodsQtyRef", order = 13) // 补充order=13，匹配XML节点顺序
    @TableField(value = "SecdGoods_QtyRef")
    private String secdGoodsQtyRef;

    /**
     * 第二货物单位参考
     */
    @Column(xmlName = "SecdGoodsUnitRef", order = 14) // 补充order=14，匹配XML节点顺序
    @TableField(value = "SecdGoods_UnitRef")
    private String secdGoodsUnitRef;

    /**
     * 是否货物项标识 Y N
     */
    @Column(xmlName = "GoodsItemFlag", order = 1) // 补充order=1，匹配XML节点顺序
    @TableField(exist = false)
    private String goodsItemFlag;

    public String getGoodsItemFlag() {
        if (hsCode == null || hsCode.isEmpty()) {
            return "Y";
        }
        return "N";
    }

    /**
     * 发票单价（InvPrice） 缺失的非必填字段
     */
    @Column(xmlName = "InvPrice", order = 18) // 补充order=18，匹配XML节点顺序
    @TableField(exist = false)
    private String invPrice;

    @SuppressWarnings("unused")
    public String getInvPrice() {
        if (invPrice == null || invPrice.isEmpty()) {
            return "";
        }
        return invPrice;
    }

    /**
     * 生产商电话 缺失的非必填字段
     */
    @Column(xmlName = "ProducerTel", order = 25) // 补充order=25，匹配XML节点顺序
    @TableField(exist = false)
    private String producerTel;

    @SuppressWarnings("unused")
    public String getProducerTel() {
        if (producerTel == null || producerTel.isEmpty()) {
            return "";
        }
        return producerTel;
    }
}