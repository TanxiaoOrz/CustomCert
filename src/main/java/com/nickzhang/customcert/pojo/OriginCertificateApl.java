package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import lombok.Data;

/**
 * @Author: 张骏山
 * @Date: 2026/1/11 20:13
 * @PackageName: com.nickzhang.customcert.pojo
 * @ClassName: OriginCertificateApl
 * @Description: 海关原产地证书申请表(Apl)
 * @Version: 1.0
 */
@Data
@TableName(value = "Ori_B_Apl_Edi") // 新规则：映射数据库完整表名（MyBatis-Plus）
@Table(xmlName = "CertificateHead",
        isDependent = true,
        belongTo = OriginCertificate.class
)
public class OriginCertificateApl {
    /**
     * 申请ID
     */
    @Column(xmlName = "") // 仅映射XML子节点，无dbName属性
    @TableField(value = "Apl_Id") // 新规则：映射数据库原始下划线字段
    @TableId
    private String aplId;

    /**
     * 证书ID（关联dbo.Ori_B_Cert_Edi表）
     */
    @Column(xmlName = "",joinKey = true,joinColumn = "certId")
    @TableField(value = "Cert_Id")
    private String certId;

    /**
     * 证书编号（关联dbo.Ori_B_Cert_Edi表）
     */
    @Column(xmlName = "")
    @TableField(value = "Cert_No")
    private String certNo;

    /**
     * 检验检疫注册号
     */
    @Column(xmlName = "CiqRegNo")
    @TableField(value = "Ciq_Reg_No")
    private String ciqRegNo;

    /**
     * 拟出口日期时间
     */
    @Column(xmlName = "IntendExpDatetime")
    @TableField(value = "Intend_Exp_Datetime")
    private String intendExpDatetime;

    /**
     * 目的国代码
     */
    @Column(xmlName = "DestCountryCode")
    @TableField(value = "Dest_Country_Code")
    private String destCountryCode;

    /**
     * 过境国代码
     */
    @Column(xmlName = "TransCountryCode")
    @TableField(value = "Trans_Country_Code")
    private String transCountryCode;

    /**
     * 贸易方式代码
     */
    @Column(xmlName = "TradeModeCode")
    @TableField(value = "Trade_Mode_Code")
    private String tradeModeCode;

    /**
     * 贸易方式名称
     */
    @Column(xmlName = "TradeModeName")
    @TableField(value = "Trade_Mode_Name")
    private String tradeModeName;

    /**
     * 申请人
     */
    @Column(xmlName = "Applicant")
    @TableField(value = "Applicant")
    private String applicant;

    /**
     * 申请人电话
     */
    @Column(xmlName = "ApplicantTel")
    @TableField(value = "Applicant_Tel")
    private String applicantTel;

    /**
     * 申请日期时间
     */
    @Column(xmlName = "ApplyDateTime")
    @TableField(value = "Apply_Date_Time")
    private String applyDateTime;

    /**
     * 备注
     */
    @Column(xmlName = "Remark")
    @TableField(value = "Remark")
    private String remark;

    /**
     * 证书种类
     */
    @Column(xmlName = "CertKind")
    @TableField(value = "Cert_Kind")
    private String certKind;

    /**
     * 企业管理编号
     */
    @Column(xmlName = "EntMgrNo")
    @TableField(value = "Ent_Mgr_No")
    private String entMgrNo;

    /**
     * 机构代码
     */
    @Column(xmlName = "OrgCode")
    @TableField(value = "Org_Code")
    private String orgCode;

    /**
     * 机构名称
     */
    @Column(xmlName = "OrgName")
    @TableField(value = "Org_Name")
    private String orgName;

    /**
     * 领取地点代码
     */
    @Column(xmlName = "FetchPlace")
    @TableField(value = "Fetch_Place")
    private String fetchPlace;

    /**
     * 领取地点名称
     */
    @Column(xmlName = "FetchPlaceName")
    @TableField(value = "Fetch_Place_Name")
    private String fetchPlaceName;

    /**
     * 申请人ID
     */
    @Column(xmlName = "ApplicantId")
    @TableField(value = "Applicant_Id")
    private String applicantId;

    /**
     * 发送方参考号
     */
    @Column(xmlName = "SenderReference")
    @TableField(value = "Sender_Reference")
    private String senderReference;

    /**
     * 过境国名称
     */
    @Column(xmlName = "TransCountryName")
    @TableField(value = "Trans_Country_Name")
    private String transCountryName;

    /**
     * 目的国名称
     */
    @Column(xmlName = "DestCountryName")
    @TableField(value = "Dest_Country_Name")
    private String destCountryName;

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
     * 联系人姓名
     */
    @Column(xmlName = "Name")
    @TableField(value = "Name")
    private String name;

    /**
     * 不可重做标识
     */
    @Column(xmlName = "UnRedoFlag")
    @TableField(value = "Un_Redo_Flag")
    private String unRedoFlag;

    /**
     * 汇总标识
     */
    @Column(xmlName = "ComulationFlag")
    @TableField(value = "Comulation_Flag")
    private String comulationFlag;

    /**
     * 证书标识
     */
    @Column(xmlName = "CertFlag")
    @TableField(value = "Cert_Flag")
    private String certFlag;

    /**
     * 预测标识
     */
    @Column(xmlName = "PredictFlag")
    @TableField(value = "Predict_Flag")
    private String predictFlag;
}