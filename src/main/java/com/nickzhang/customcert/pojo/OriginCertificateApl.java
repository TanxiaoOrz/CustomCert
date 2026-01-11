package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import lombok.Data;

/**
 * @Author: 张骏山
 * @Date: 2026/1/11 16:00
 * @PackageName: com.nickzhang.customcert.pojo
 * @ClassName: OriginCertificate
 * @Description: 原产地证书
 * @Version: 1.0
 */
@Data
@TableName("Ori_B_Apl_Edi ")
@Table(xmlName = "Certificate", dbName = "Ori_B_Apl_Edi ", showName = "原产地证书")
public class OriginCertificateApl {

    /**
     * 主键ID（数据库自增/唯一标识，冗余字段，无对应XML节点）
     */
    @TableField("Id")
    @Column(xmlName = "", dbName = "Apl_Id")
    @TableId
    private String id;


    // ==================== CertificateHead 证书头部节点 ====================
    /**
     * 原产地证编号
     */
    @TableField("Cert_No")
    @Column(xmlName = "CertificateHead/CertNo", dbName = "Cert_No")
    private String certNo;

    /**
     * 申请类型
     */
    @TableField("Apply_Type")
    @Column(xmlName = "CertificateHead/ApplyType", dbName = "Apply_Type")
    private String applyType;

    /**
     * 证书类别
     */
    @TableField("Cert_Status")
    @Column(xmlName = "CertificateHead/CertStatus", dbName = "Cert_Status")
    private String certStatus;

    /**
     * 原产地证书类型
     */
    @TableField("Cert_Type")
    @Column(xmlName = "CertificateHead/CertType", dbName = "Cert_Type")
    private String certType;

    /**
     * 企业编号
     */
    @TableField("Ent_Mgr_No")
    @Column(xmlName = "CertificateHead/EntMgrNo", dbName = "Ent_Mgr_No")
    private String entMgrNo;

    /**
     * 出口商代码
     */
    @TableField("Ciq_Reg_No")
    @Column(xmlName = "CertificateHead/CiqRegNo", dbName = "Ciq_Reg_No")
    private String ciqRegNo;

    /**
     * 录入企业代码
     */
    @TableField("Apl_Reg_No")
    @Column(xmlName = "CertificateHead/AplRegNo", dbName = "Apl_Reg_No")
    private String aplRegNo;

    /**
     * 企业名称（中文）
     */
    @TableField("Etps_Name")
    @Column(xmlName = "CertificateHead/EtpsName", dbName = "Etps_Name")
    private String etpsName;

    /**
     * 申报员姓名
     */
    @TableField("Appl_Name")
    @Column(xmlName = "CertificateHead/ApplName", dbName = "Appl_Name")
    private String applName;

    /**
     * 申报员ID，申报员身份证号
     */
    @TableField("Applicant")
    @Column(xmlName = "CertificateHead/Applicant", dbName = "Applicant")
    private String applicant;

    /**
     * 申报员联系电话
     */
    @TableField("Appl_Tel")
    @Column(xmlName = "CertificateHead/ApplTel", dbName = "Appl_Tel")
    private String applTel;

    /**
     * 签证机构代码
     */
    @TableField("Org_Code")
    @Column(xmlName = "CertificateHead/OrgCode", dbName = "Org_Code")
    private String orgCode;

    /**
     * 领证机构代码
     */
    @TableField("Fetch_Place")
    @Column(xmlName = "CertificateHead/FetchPlace", dbName = "Fetch_Place")
    private String fetchPlace;

    /**
     * 申请地址
     */
    @TableField("Apl_Add")
    @Column(xmlName = "CertificateHead/AplAdd", dbName = "Apl_Add")
    private String aplAdd;

    /**
     * 发票日期
     */
    @TableField("Inv_Date")
    @Column(xmlName = "CertificateHead/InvDate", dbName = "Inv_Date")
    private String invDate;

    /**
     * 发票号
     */
    @TableField("Inv_No")
    @Column(xmlName = "CertificateHead/InvNo", dbName = "Inv_No")
    private String invNo;

    /**
     * 申请日期
     */
    @TableField("Apl_Date")
    @Column(xmlName = "CertificateHead/AplDate", dbName = "Apl_Date")
    private String aplDate;

    /**
     * 进口国/地区
     */
    @TableField("Dest_Country")
    @Column(xmlName = "CertificateHead/DestCountry", dbName = "Dest_Country")
    private String destCountry;

    /**
     * 进口国/地区编码
     */
    @TableField("Dest_Country_Code")
    @Column(xmlName = "CertificateHead/DestCountryCode", dbName = "Dest_Country_Code")
    private String destCountryCode;

    /**
     * 进口国/地区名称
     */
    @TableField("Dest_Country_Name")
    @Column(xmlName = "CertificateHead/DestCountryName", dbName = "Dest_Country_Name")
    private String destCountryName;

    /**
     * 出口商
     */
    @TableField("Exporter")
    @Column(xmlName = "CertificateHead/Exporter", dbName = "Exporter")
    private String exporter;

    /**
     * 收货人
     */
    @TableField("Consignee")
    @Column(xmlName = "CertificateHead/Consignee", dbName = "Consignee")
    private String consignee;

    /**
     * 特殊条款（商品描述）
     */
    @TableField("Goods_Spec_Clause")
    @Column(xmlName = "CertificateHead/GoodsSpecClause", dbName = "Goods_Spec_Clause")
    private String goodsSpecClause;

    /**
     * 唛头
     */
    @TableField("Mark")
    @Column(xmlName = "CertificateHead/Mark", dbName = "Mark")
    private String mark;

    /**
     * 启运港
     */
    @TableField("Load_Port")
    @Column(xmlName = "CertificateHead/LoadPort", dbName = "Load_Port")
    private String loadPort;

    /**
     * 卸货港
     */
    @TableField("Unload_Port")
    @Column(xmlName = "CertificateHead/UnloadPort", dbName = "Unload_Port")
    private String unloadPort;

    /**
     * 运输方式（运输工具）
     */
    @TableField("Trans_Means")
    @Column(xmlName = "CertificateHead/TransMeans", dbName = "Trans_Means")
    private String transMeans;

    /**
     * 运输工具船名/航次
     */
    @TableField("Trans_Name")
    @Column(xmlName = "CertificateHead/TransName", dbName = "Trans_Name")
    private String transName;

    /**
     * 中转国/地区编码
     */
    @TableField("Trans_Country_Code")
    @Column(xmlName = "CertificateHead/TransCountryCode", dbName = "Trans_Country_Code")
    private String transCountryCode;

    /**
     * 中转国/地区名称
     */
    @TableField("Trans_Country_Name")
    @Column(xmlName = "CertificateHead/TransCountryName", dbName = "Trans_Country_Name")
    private String transCountryName;

    /**
     * 转运港
     */
    @TableField("Trans_Port")
    @Column(xmlName = "CertificateHead/TransPort", dbName = "Trans_Port")
    private String transPort;

    /**
     * 目的港
     */
    @TableField("Dest_Port")
    @Column(xmlName = "CertificateHead/DestPort", dbName = "Dest_Port")
    private String destPort;

    /**
     * 运输细节
     */
    @TableField("Trans_Details")
    @Column(xmlName = "CertificateHead/TransDetails", dbName = "Trans_Details")
    private String transDetails;

    /**
     * 出运日期
     */
    @TableField("Intend_Exp_Date")
    @Column(xmlName = "CertificateHead/IntendExpDate", dbName = "Intend_Exp_Date")
    private String intendExpDate;

    /**
     * 贸易方式代码
     */
    @TableField("Trade_Mode_Code")
    @Column(xmlName = "CertificateHead/TradeModeCode", dbName = "Trade_Mode_Code")
    private String tradeModeCode;

    /**
     * FOB值
     */
    @TableField("FOB_Value")
    @Column(xmlName = "CertificateHead/FOBValue", dbName = "FOB_Value")
    private String fobValue;

    /**
     * 总金额
     */
    @TableField("Total_Amt")
    @Column(xmlName = "CertificateHead/TotalAmt", dbName = "Total_Amt")
    private String totalAmt;

    /**
     * 申请书备注
     */
    @TableField("Note")
    @Column(xmlName = "CertificateHead/Note", dbName = "Note")
    private String note;

    /**
     * 合同号
     */
    @TableField("Contract_No")
    @Column(xmlName = "CertificateHead/ContractNo", dbName = "Contract_No")
    private String contractNo;

    /**
     * 信用证号
     */
    @TableField("Lc_No")
    @Column(xmlName = "CertificateHead/LcNo", dbName = "Lc_No")
    private String lcNo;

    /**
     * 发票特殊条款
     */
    @TableField("Spec_Inv_Terms")
    @Column(xmlName = "CertificateHead/SpecInvTerms", dbName = "Spec_Inv_Terms")
    private String specInvTerms;

    /**
     * 价格条款
     */
    @TableField("Price_Terms")
    @Column(xmlName = "CertificateHead/PriceTerms", dbName = "Price_Terms")
    private String priceTerms;

    /**
     * 货币单位
     */
    @TableField("Curr")
    @Column(xmlName = "CertificateHead/Curr", dbName = "Curr")
    private String curr;

    /**
     * 证书备注信息
     */
    @TableField("Remark")
    @Column(xmlName = "CertificateHead/Remark", dbName = "Remark")
    private String remark;

    /**
     * 是否生产商保密
     */
    @TableField("Producer_Sert_Flag")
    @Column(xmlName = "CertificateHead/ProducerSertFlag", dbName = "Producer_Sert_Flag")
    private String producerSertFlag;

    /**
     * 是否展览证书
     */
    @TableField("Exhibit_Flag")
    @Column(xmlName = "CertificateHead/ExhibitFlag", dbName = "Exhibit_Flag")
    private String exhibitFlag;

    /**
     * 是否第三方发票，第三方发票标志
     */
    @TableField("Third_Party_Inv_Flag")
    @Column(xmlName = "CertificateHead/ThirdPartyInvFlag", dbName = "Third_Party_Inv_Flag")
    private String thirdPartyInvFlag;

    /**
     * 出口商电话
     */
    @TableField("Exporter_Tel")
    @Column(xmlName = "CertificateHead/ExporterTel", dbName = "Exporter_Tel")
    private String exporterTel;

    /**
     * 出口商传真
     */
    @TableField("Exporter_Fax")
    @Column(xmlName = "CertificateHead/ExporterFax", dbName = "Exporter_Fax")
    private String exporterFax;

    /**
     * 出口商邮箱
     */
    @TableField("Exporter_Email")
    @Column(xmlName = "CertificateHead/ExporterEmail", dbName = "Exporter_Email")
    private String exporterEmail;

    /**
     * 进口商电话
     */
    @TableField("Consignee_Tel")
    @Column(xmlName = "CertificateHead/ConsigneeTel", dbName = "Consignee_Tel")
    private String consigneeTel;

    /**
     * 进口商传真
     */
    @TableField("Consignee_Fax")
    @Column(xmlName = "CertificateHead/ConsigneeFax", dbName = "Consignee_Fax")
    private String consigneeFax;

    /**
     * 进口商邮箱
     */
    @TableField("Consignee_Email")
    @Column(xmlName = "CertificateHead/ConsigneeEmail", dbName = "Consignee_Email")
    private String consigneeEmail;

    /**
     * 是否预计离港日期标志
     */
    @TableField("Predict_Flag")
    @Column(xmlName = "CertificateHead/PredictFlag", dbName = "Predict_Flag")
    private String predictFlag;

    /**
     * 出口报关日期
     */
    @TableField("Exp_Decl_Date")
    @Column(xmlName = "CertificateHead/ExpDeclDate", dbName = "Exp_Decl_Date")
    private String expDeclDate;

    /**
     * 原产国代码
     */
    @TableField("Ori_Country_Code")
    @Column(xmlName = "CertificateHead/OriCountryCode", dbName = "Ori_Country_Code")
    private String oriCountryCode;

    /**
     * 原产国英文名
     */
    @TableField("Ori_Country")
    @Column(xmlName = "CertificateHead/OriCountry", dbName = "Ori_Country")
    private String oriCountry;

    /**
     * 签发有效日期
     */
    @TableField("Chk_Valid_Date")
    @Column(xmlName = "CertificateHead/ChkValidDate", dbName = "Chk_Valid_Date")
    private String chkValidDate;

    /**
     * 企业联系人
     */
    @TableField("Etps_Conc_Er")
    @Column(xmlName = "CertificateHead/EtpsConcEr", dbName = "Etps_Conc_Er")
    private String etpsConcEr;

    /**
     * 企业联系电话
     */
    @TableField("Etps_Tel")
    @Column(xmlName = "CertificateHead/EtpsTel", dbName = "Etps_Tel")
    private String etpsTel;

    /**
     * 证书货物生产商描述
     */
    @TableField("Producer")
    @Column(xmlName = "CertificateHead/Producer", dbName = "Producer")
    private String producer;

    /**
     * 加工装配工序
     */
    @TableField("Prcs_Assembly")
    @Column(xmlName = "CertificateHead/PrcsAssembly", dbName = "Prcs_Assembly")
    private String prcsAssembly;

    /**
     * 报关单号
     */
    @TableField("Entry_Id")
    @Column(xmlName = "CertificateHead/EntryId", dbName = "Entry_Id")
    private String entryId;

    // ==================== CertificateList/Goods 商品明细节点 ====================
    /**
     * 非货物项
     */
    @TableField("Goods_Item_Flag")
    @Column(xmlName = "CertificateList/Goods/GoodsItemFlag", dbName = "Goods_Item_Flag")
    private String goodsItemFlag;

    /**
     * 商品项目编号
     */
    @TableField("G_No")
    @Column(xmlName = "CertificateList/Goods/GNo", dbName = "G_No")
    private String gNo;

    /**
     * HS编码
     */
    @TableField("HS_Code")
    @Column(xmlName = "CertificateList/Goods/HSCode", dbName = "HS_Code")
    private String hsCode;

    /**
     * 货物名
     */
    @TableField("Goods_Name")
    @Column(xmlName = "CertificateList/Goods/GoodsName", dbName = "Goods_Name")
    private String goodsName;

    /**
     * 货物名称（英文）
     */
    @TableField("Goods_Name_E")
    @Column(xmlName = "CertificateList/Goods/GoodsNameE", dbName = "Goods_Name_E")
    private String goodsNameE;

    /**
     * 包装件数
     */
    @TableField("Pack_Qty")
    @Column(xmlName = "CertificateList/Goods/PackQty", dbName = "Pack_Qty")
    private String packQty;

    /**
     * 包装单位（英文）
     */
    @TableField("Pack_Unit")
    @Column(xmlName = "CertificateList/Goods/PackUnit", dbName = "Pack_Unit")
    private String packUnit;

    /**
     * 标准货物数/重量
     */
    @TableField("Goods_Qty")
    @Column(xmlName = "CertificateList/Goods/GoodsQty", dbName = "Goods_Qty")
    private String goodsQty;

    /**
     * 标准货物单位，数/重量单位（英文）
     */
    @TableField("Goods_Unit_E")
    @Column(xmlName = "CertificateList/Goods/GoodsUnitE", dbName = "Goods_Unit_E")
    private String goodsUnitE;

    /**
     * 标准货物单位，数/重量单位（中文）
     */
    @TableField("Goods_Unit")
    @Column(xmlName = "CertificateList/Goods/GoodsUnit", dbName = "Goods_Unit")
    private String goodsUnit;

    /**
     * 辅助货物数/重量
     */
    @TableField("Goods_Qty_Ref")
    @Column(xmlName = "CertificateList/Goods/GoodsQtyRef", dbName = "Goods_Qty_Ref")
    private String goodsQtyRef;

    /**
     * 辅助货物数/重量单位
     */
    @TableField("Goods_Unit_Ref")
    @Column(xmlName = "CertificateList/Goods/GoodsUnitRef", dbName = "Goods_Unit_Ref")
    private String goodsUnitRef;

    /**
     * 第二辅助货物数/重量
     */
    @TableField("Secd_Goods_Qty_Ref")
    @Column(xmlName = "CertificateList/Goods/SecdGoodsQtyRef", dbName = "Secd_Goods_Qty_Ref")
    private String secdGoodsQtyRef;

    /**
     * 第二辅助货物数/重量单位
     */
    @TableField("Secd_Goods_Unit_Ref")
    @Column(xmlName = "CertificateList/Goods/SecdGoodsUnitRef", dbName = "Secd_Goods_Unit_Ref")
    private String secdGoodsUnitRef;

    /**
     * 毛重
     */
    @TableField("Gross_Wt")
    @Column(xmlName = "CertificateList/Goods/GrossWt", dbName = "Gross_Wt")
    private String grossWt;

    /**
     * 净重
     */
    @TableField("Net_Wt")
    @Column(xmlName = "CertificateList/Goods/NetWt", dbName = "Net_Wt")
    private String netWt;

    /**
     * 重量计量单位
     */
    @TableField("Wt_Unit")
    @Column(xmlName = "CertificateList/Goods/WtUnit", dbName = "Wt_Unit")
    private String wtUnit;

    /**
     * 发票单价
     */
    @TableField("Inv_Price")
    @Column(xmlName = "CertificateList/Goods/InvPrice", dbName = "Inv_Price")
    private String invPrice;

    /**
     * 发票金额
     */
    @TableField("Inv_Value")
    @Column(xmlName = "CertificateList/Goods/InvValue", dbName = "Inv_Value")
    private String invValue;

    /**
     * 商品FOB值
     */
    @TableField("Goods_FOB_Value")
    @Column(xmlName = "CertificateList/Goods/FOBValue", dbName = "Goods_FOB_Value")
    private String goodsFobValue;

    /**
     * 进口成份比例
     */
    @TableField("I_Comp_Prpr")
    @Column(xmlName = "CertificateList/Goods/ICompPrpr", dbName = "I_Comp_Prpr")
    private String iCompPrpr;

    /**
     * 原产地标准
     */
    @TableField("Ori_Criteria")
    @Column(xmlName = "CertificateList/Goods/OriCriteria", dbName = "Ori_Criteria")
    private String oriCriteria;

    /**
     * 原产地标准辅助项
     */
    @TableField("Ori_Criteria_Ref")
    @Column(xmlName = "CertificateList/Goods/OriCriteriaRef", dbName = "Ori_Criteria_Ref")
    private String oriCriteriaRef;

    /**
     * 商品生产商电话
     */
    @TableField("Producer_Tel")
    @Column(xmlName = "CertificateList/Goods/ProducerTel", dbName = "Producer_Tel")
    private String producerTel;

    /**
     * 商品生产商传真
     */
    @TableField("Producer_Fax")
    @Column(xmlName = "CertificateList/Goods/ProducerFax", dbName = "Producer_Fax")
    private String producerFax;

    /**
     * 商品生产商邮箱
     */
    @TableField("Producer_Email")
    @Column(xmlName = "CertificateList/Goods/ProducerEmail", dbName = "Producer_Email")
    private String producerEmail;

    /**
     * 生产企业组织机构代码
     */
    @TableField("Goods_Ciq_Reg_No")
    @Column(xmlName = "CertificateList/Goods/CiqRegNo", dbName = "Goods_Ciq_Reg_No")
    private String goodsCiqRegNo;

    /**
     * 生产企业名称
     */
    @TableField("Prdc_Etps_Name")
    @Column(xmlName = "CertificateList/Goods/PrdcEtpsName", dbName = "Prdc_Etps_Name")
    private String prdcEtpsName;

    /**
     * 生产企业联系人
     */
    @TableField("Prdc_Etps_Conc_Er")
    @Column(xmlName = "CertificateList/Goods/PrdcEtpsConcEr", dbName = "Prdc_Etps_Conc_Er")
    private String prdcEtpsConcEr;

    /**
     * 生产企业联系电话
     */
    @TableField("Prdc_Etps_Tel")
    @Column(xmlName = "CertificateList/Goods/PrdcEtpsTel", dbName = "Prdc_Etps_Tel")
    private String prdcEtpsTel;

    /**
     * 商品生产商保密标识
     */
    @TableField("Goods_Producer_Sert_Flag")
    @Column(xmlName = "CertificateList/Goods/ProducerSertFlag", dbName = "Goods_Producer_Sert_Flag")
    private String goodsProducerSertFlag;

    /**
     * 货物描述
     */
    @TableField("Goods_Desc")
    @Column(xmlName = "CertificateList/Goods/GoodsDesc", dbName = "Goods_Desc")
    private String goodsDesc;

    /**
     * 原产地标准子标准
     */
    @TableField("Ori_Criteria_Sub")
    @Column(xmlName = "CertificateList/Goods/OriCriteriaSub", dbName = "Ori_Criteria_Sub")
    private String oriCriteriaSub;

    /**
     * 协定原产国代码
     */
    @TableField("Goods_Origin_Country")
    @Column(xmlName = "CertificateList/Goods/GoodsOriginCountry", dbName = "Goods_Origin_Country")
    private String goodsOriginCountry;

    /**
     * 协定原产国英文
     */
    @TableField("Goods_Origin_Country_En")
    @Column(xmlName = "CertificateList/Goods/GoodsOriginCountryEn", dbName = "Goods_Origin_Country_En")
    private String goodsOriginCountryEn;

    /**
     * 最高税率标志
     */
    @TableField("Goods_Tax_Rate")
    @Column(xmlName = "CertificateList/Goods/GoodsTaxRate", dbName = "Goods_Tax_Rate")
    private String goodsTaxRate;

    /**
     * 商品发票号
     */
    @TableField("Goods_Inv_No")
    @Column(xmlName = "CertificateList/Goods/InvNo", dbName = "Goods_Inv_No")
    private String goodsInvNo;

    /**
     * 包装类型
     */
    @TableField("Pack_Type")
    @Column(xmlName = "CertificateList/Goods/PackType", dbName = "Pack_Type")
    private String packType;

    // ==================== ModCertificate 证书修改节点 ====================
    /**
     * 原证书号
     */
    @TableField("Old_Cert_No")
    @Column(xmlName = "ModCertificate/OldCertNo", dbName = "Old_Cert_No")
    private String oldCertNo;

    /**
     * 更改/重发原因
     */
    @TableField("Mod_Reason")
    @Column(xmlName = "ModCertificate/ModReason", dbName = "Mod_Reason")
    private String modReason;

    /**
     * 更改栏目
     */
    @TableField("Mod_Colm")
    @Column(xmlName = "ModCertificate/ModColm", dbName = "Mod_Colm")
    private String modColm;

    /**
     * 原有情况描述
     */
    @TableField("Old_Situ_Desc")
    @Column(xmlName = "ModCertificate/OldSituDesc", dbName = "Old_Situ_Desc")
    private String oldSituDesc;

    /**
     * 更改情况描述
     */
    @TableField("Mod_Situ_Desc")
    @Column(xmlName = "ModCertificate/ModSituDesc", dbName = "Mod_Situ_Desc")
    private String modSituDesc;

    /**
     * 原证申请日期
     */
    @TableField("Old_Decl_Date")
    @Column(xmlName = "ModCertificate/OldDeclDate", dbName = "Old_Decl_Date")
    private String oldDeclDate;

    /**
     * 原证签发日期
     */
    @TableField("Old_Issue_Date")
    @Column(xmlName = "ModCertificate/OldIssueDate", dbName = "Old_Issue_Date")
    private String oldIssueDate;

    // ==================== NonpartyCorpList/NonpartyCorp 非缔约方企业节点 ====================
    /**
     * 非缔约方企业序号
     */
    @TableField("Sort_No")
    @Column(xmlName = "NonpartyCorpList/NonpartyCorp/SortNo", dbName = "Sort_No")
    private String sortNo;

    /**
     * 非缔约方企业名称
     */
    @TableField("Ent_Name")
    @Column(xmlName = "NonpartyCorpList/NonpartyCorp/EntName", dbName = "Ent_Name")
    private String entName;

    /**
     * 非缔约方企业地址
     */
    @TableField("Ent_Addr")
    @Column(xmlName = "NonpartyCorpList/NonpartyCorp/EntAddr", dbName = "Ent_Addr")
    private String entAddr;

    /**
     * 非缔约方企业国别/地区代码
     */
    @TableField("Ent_Country_Code")
    @Column(xmlName = "NonpartyCorpList/NonpartyCorp/EntCountryCode", dbName = "Ent_Country_Code")
    private String entCountryCode;

    /**
     * 非缔约方企业国别/地区名称
     */
    @TableField("Ent_Country_Name")
    @Column(xmlName = "NonpartyCorpList/NonpartyCorp/EntCountryName", dbName = "Ent_Country_Name")
    private String entCountryName;

    // ==================== OriInvs/OriInv 原产地发票节点 ====================
    /**
     * 发票对应证书号
     */
    @TableField("Ori_Inv_Cert_No")
    @Column(xmlName = "OriInvs/OriInv/CertNo", dbName = "Ori_Inv_Cert_No")
    private String oriInvCertNo;

    /**
     * 原产地发票号
     */
    @TableField("Ori_Inv_No")
    @Column(xmlName = "OriInvs/OriInv/InvNo", dbName = "Ori_Inv_No")
    private String oriInvNo;

    /**
     * 原产地发票合同号
     */
    @TableField("Ori_Inv_Contract_No")
    @Column(xmlName = "OriInvs/OriInv/ContractNo", dbName = "Ori_Inv_Contract_No")
    private String oriInvContractNo;

    /**
     * 原产地发票信用证号
     */
    @TableField("Ori_Inv_Lc_No")
    @Column(xmlName = "OriInvs/OriInv/LcNo", dbName = "Ori_Inv_Lc_No")
    private String oriInvLcNo;

    /**
     * 原产地发票总金额
     */
    @TableField("Ori_Inv_Value")
    @Column(xmlName = "OriInvs/OriInv/Value", dbName = "Ori_Inv_Value")
    private String oriInvValue;

    /**
     * 原产地发票货币单位
     */
    @TableField("Ori_Inv_Curr")
    @Column(xmlName = "OriInvs/OriInv/Curr", dbName = "Ori_Inv_Curr")
    private String oriInvCurr;

    /**
     * 原产地发票价格条款
     */
    @TableField("Ori_Inv_Price_Clause")
    @Column(xmlName = "OriInvs/OriInv/PriceClause", dbName = "Ori_Inv_Price_Clause")
    private String oriInvPriceClause;

    /**
     * 原产地发票特殊条款
     */
    @TableField("Ori_Inv_Spec_Inv_Terms")
    @Column(xmlName = "OriInvs/OriInv/SpecInvTerms", dbName = "Ori_Inv_Spec_Inv_Terms")
    private String oriInvSpecInvTerms;

    /**
     * 原产地发票日期
     */
    @TableField("Ori_Inv_Date")
    @Column(xmlName = "OriInvs/OriInv/InvDate", dbName = "Ori_Inv_Date")
    private String oriInvDate;

    // ==================== AplPromise 申请企业承诺节点 ====================
    /**
     * 申请企业承诺信息代码
     */
    @TableField("Apl_Promise_Code")
    @Column(xmlName = "AplPromise/AplPromiseCode", dbName = "Apl_Promise_Code")
    private String aplPromiseCode;

    // ==================== 冗余字段（延续原有格式，无对应XML节点） ====================
    /**
     * 数据创建时间（数据库字段，无XML映射）
     */
    @TableField("Create_Time")
    @Column(xmlName = "", dbName = "Create_Time")
    private String createTime;

    /**
     * 数据更新时间（数据库字段，无XML映射）
     */
    @TableField("Update_Time")
    @Column(xmlName = "", dbName = "Update_Time")
    private String updateTime;
}