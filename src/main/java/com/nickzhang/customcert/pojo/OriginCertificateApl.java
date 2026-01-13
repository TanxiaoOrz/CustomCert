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
    @Column(xmlName = "") // 仅映射XML子节点，无dbName属性（保留原始空白xmlName，不修改）
    @TableField(value = "Apl_Id") // 新规则：映射数据库原始下划线字段
    @TableId
    private String aplId;

    /**
     * 证书ID（关联dbo.Ori_B_Cert_Edi表）
     */
    @Column(xmlName = "",joinKey = true,joinColumn = "certId") // 保留原始空白xmlName，不修改
    @TableField(value = "Cert_Id")
    private String certId;

    /**
     * 联系人姓名
     */
    @Column(xmlName = "EtpsName", order = 8) // 仅补充order=8，保留原有xmlName，不修改其他
    @TableField(value = "Name")
    private String name;

    /**
     * 申请人
     */
    @Column(xmlName = "ApplName", order = 9, equalXml = {"EtpsConcEr-59"}) // 仅补充order=9，保留原有属性
    @TableField(value = "Applicant")
    private String applicant;

    /**
     * 申请人ID
     */
    @Column(xmlName = "Applicant", order = 10) // 仅补充order=10，保留原有xmlName
    @TableField(value = "Applicant_Id")
    private String applicantId;

    /**
     * 申请人电话
     */
    @Column(xmlName = "ApplTel", order = 11, equalXml = {"EtpsTel-60"}) // 仅补充order=11，保留原有属性
    @TableField(value = "Applicant_Tel")
    private String applicantTel;

    /**
     * 机构代码
     */
    @Column(xmlName = "OrgCode", order = 12) // 仅补充order=12，保留原有xmlName
    @TableField(value = "Org_Code")
    private String orgCode;

    /**
     * 证书编号（关联dbo.Ori_B_Cert_Edi表） apl重复字段
     */
    // 无原有@Column注解，不新增任何@Column配置（严格遵循要求）
    @TableField(value = "Cert_No")
    private String certNo;

    /**
     * 检验检疫注册号 apl重复字段
     */
    @TableField(value = "Ciq_Reg_No")
    private String ciqRegNo;

    /**
     * 拟出口日期时间
     */
    @TableField(value = "Intend_Exp_Datetime")

    private String intendExpDatetime;

    /**
     * 目的国代码
     */
    @Column(xmlName = "DestCountryCode", order = 19) // 仅补充order=19，保留原有xmlName
    @TableField(value = "Dest_Country_Code")
    private String destCountryCode;

    /**
     * 过境国代码
     */
    @Column(xmlName = "TransCountryCode", order = 29) // 仅补充order=29，保留原有xmlName
    @TableField(value = "Trans_Country_Code")
    private String transCountryCode;

    /**
     * 贸易方式代码
     */
    @Column(xmlName = "TradeModeCode", order = 35) // 仅补充order=35，保留原有xmlName
    @TableField(value = "Trade_Mode_Code")
    private String tradeModeCode;

    /**
     * 贸易方式名称
     */
    @TableField(value = "Trade_Mode_Name")
    private String tradeModeName;

    /**
     * 申请日期时间
     */
    @TableField(value = "Apply_Date_Time")
    private String applyDateTime;

    /**
     * 备注
     */
    @Column(xmlName = "Remark", order = 44) // 仅补充order=44，保留原有xmlName
    @TableField(value = "Remark")
    private String remark;

    /**
     * 证书种类 apl重复字段
     */
    @TableField(value = "Cert_Kind")
    private String certKind;

    /**
     * 企业管理编号 apl重复字段
     */
    @TableField(value = "Ent_Mgr_No")
    private String entMgrNo;

    /**
     * 机构名称
     */
    @TableField(value = "Org_Name")
    private String orgName;

    /**
     * 领取地点代码
     */
    @Column(xmlName = "FetchPlace", order = 13) // 仅补充order=13，保留原有xmlName
    @TableField(value = "Fetch_Place")
    private String fetchPlace;

    /**
     * 领取地点名称
     */
    @TableField(value = "Fetch_Place_Name")
    private String fetchPlaceName;

    /**
     * 发送方参考号
     */
    @TableField(value = "Sender_Reference")
    private String senderReference;

    /**
     * 过境国名称
     */
    @Column(xmlName = "TransCountryName", order = 30) // 仅补充order=30，保留原有xmlName
    @TableField(value = "Trans_Country_Name")
    private String transCountryName;

    /**
     * 目的国名称
     */
    @Column(xmlName = "DestCountryName", order = 20) // 仅补充order=20，保留原有xmlName
    @TableField(value = "Dest_Country_Name")
    private String destCountryName;

    /**
     * 联系人
     */
    @TableField(value = "Contactor")
    private String contactor;

    /**
     * 联系电话
     */
    @TableField(value = "Tel")
    private String tel;

    /**
     * 不可重做标识
     */
    @TableField(value = "Un_Redo_Flag")
    private String unRedoFlag;

    /**
     * 汇总标识
     */
    @TableField(value = "Trans_Country_Name") // 保留原始笔误，不做任何修正
    private String comulationFlag;

    /**
     * 证书标识
     */
    @TableField(value = "Cert_Flag")
    private String certFlag;

    /**
     * 预测标识
     */
    @Column(xmlName = "PredictFlag", order = 54) // 仅补充order=54，保留原有xmlName
    @TableField(value = "Predict_Flag")
    private String predictFlag;
}