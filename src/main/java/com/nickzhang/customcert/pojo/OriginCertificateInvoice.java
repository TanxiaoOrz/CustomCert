package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import lombok.Data;

/**
 * @Author: 张骏山
 * @Date: 2026/1/13 0:40
 * @PackageName: com.nickzhang.customcert.pojo
 * @ClassName: OriginCertificateInvoice
 * @Description: 海关原产地发票表
 * @Version: 1.0
 */
@Data
@TableName(value = "Ori_B_Invoice") // 新规则：映射数据库完整表名（MyBatis-Plus）
@Table(xmlName = "CertificateHead",
        isDependent = true,
        belongTo = OriginCertificate.class
)
public class OriginCertificateInvoice {

    /**
     * 发票ID（主键）
     */
    @TableField(value = "Invoice_Id")
    @TableId
    private String invoiceId;

    /**
     * 证书ID（关联dbo.Ori_B_Cert_Edi表）
     */
    @Column(xmlName = "",joinKey = true,joinColumn = "certId")
    @TableField(value = "Cert_Id")
    private String certId;


    /**
     * 关联证书编号（关联核心证书表）
     */
    @TableField(value = "Cert_No")
    private String certNo;

    /**
     * 总金额（对应XML核心字段）
     */
    @Column(xmlName = "TotalAmt", order = 37)
    @TableField(value = "Inv_Sum")
    private String invSum;

    /**
     * 合同编号（对应XML核心字段）
     */
    @Column(xmlName = "ContractNo", order = 39)
    @TableField(value = "Contract_No")
    private String contractNo;

    /**
     * 信用证编号（对应XML核心字段）
     */
    @Column(xmlName = "LcNo", order = 40)
    @TableField(value = "Credit_No")
    private String creditNo;

    /**
     * 特殊发票条款（对应XML核心字段）
     */
    @Column(xmlName = "SpecInvTerms", order = 41)
    @TableField(value = "Inv_Spec")
    private String invSpec;

    /**
     * 价格条款（对应XML核心字段）
     */
    @Column(xmlName = "PriceTerms", order = 42)
    @TableField(value = "Price_Item")
    private String priceItem;

    /**
     * 货币代码（对应XML核心字段，默认值USD）
     */
    @Column(xmlName = "Curr", order = 43,  linkTableColumn = "Ccl_C_Map_Curr-C_Scode-Q_Code")
    @TableField(value = "Ccy")
    private String ccy;

    /**
     * 发票编号（与核心证书表关联）
     */
    @Column(xmlName = "InvNo", order = 16)
    @TableField(value = "Inv_No")
    private String invNo;

    /**
     * 发票日期（与核心证书表关联）
     */
    @Column(xmlName = "InvDate", order = 15)
    @TableField(value = "Inv_Date")
    private String invDate;

}
