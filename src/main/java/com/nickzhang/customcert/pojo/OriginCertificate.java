package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import com.nickzhang.customcert.xml.NodeUtils;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 原产地证书EDI明细实体类
 * 对应XML：Ori_B_Cert_Edi（注：暂未提供具体XML节点，默认与表名对应核心业务节点，可后续补充调整xmlName）
 * 对应数据库表：dbo.Ori_B_Cert_Edi
 * 归属主实体类：（若有上级主实体，可补充填写，如ExitInspectionQuarantineOrder.class）
 */
@Data
@TableName(value = "dbo.Ori_B_Cert_Edi") // 新规则：映射数据库完整表名
@Table(xmlName = "Certificate",
        showName = "海关原产地证书",
        schemaLocation = "http://www.w3.org/2000/09/xmldsig# coo%20(1).xsd",
        nameSpaces = {"","http://www.w3.org/2000/09/xmldsig#","xsi","http://www.w3.org/2001/XMLSchema-instance"},
        filePath = "CooImp/"
) // 自定义@Table注解，保留表名与XML节点映射
public class OriginCertificate {

    /**
     * 证书ID
     */
    @Column() // 仅映射XML节点，无dbName属性（保留原始空白配置，不修改）
    @TableField(value = "Cert_Id") // 新规则：映射数据库下划线字段
    @TableId
    private String certId;

    /**
     * 证书编号
     */
    @Column(xmlName = "CertificateHead/CertNo",order = 1,listShow = true, searchShow = true,isNo = true) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Cert_No")
    private String certNo;

    /**
     * 申请类型
     */
    @TableField(exist = false)
    @Column(xmlName = "CertificateHead/ApplyType", defaultValue = "0",order = 2) // 保留原有配置，order匹配XML顺序
    private String applyType;

    /**
     * 证书状态
     */
    @TableField(value = "Cert_State")
    private String certState;

    /**
     * 证书种类
     */
    @Column(xmlName = "CertificateHead/CertType", order = 4) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Cert_Kind")
    private String certKind;

    /**
     * 企业管理编号
     */
    @Column(xmlName = "CertificateHead/EntMgrNo", order = 5) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Ent_Mgr_No")
    private String entMgrNo;

    /**
     * 检验检疫注册号
     */
    @Column(xmlName = "CertificateHead/CiqRegNo", order = 6) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Ciq_Reg_No")
    private String ciqRegNo;

    /**
     * 申请注册号
     */
    @Column(xmlName = "CertificateHead/AplRegNo", order = 7) // 保留原有配置，order匹配XML顺序
    @TableField(value = "APL_REG_NO")
    private String aplRegNo;

    /**
     * 出口商
     */
    @Column(xmlName = "CertificateHead/Exporter", order = 21) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Exporter")
    private String exporter;

    /**
     * 收货人
     */
    @Column(xmlName = "CertificateHead/Consignee", order = 22) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Consignee")
    private String consignee;

    /**
     * 目的地
     */
    @Column(xmlName = "CertificateHead/DestCountry", order = 18) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Dest")
    private String dest;

    /**
     * 目的地代码
     */
    @Column(xmlName = "CertificateHead/DestCountryCode", order = 19) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Dest_Code")
    private String destCode;

    /**
     * 目的地中文名称
     */
    @Column(xmlName = "CertificateHead/DestCountryName", order = 20,linkTableColumn = "Ori_C_Country-Country_Ename-Country_Name") // 保留原有配置，order匹配XML顺序
    @TableField(value = "dest")
    private String destChinese;

    /**
     * 运输标志
     */
    @Column(xmlName = "CertificateHead/Mark", order = 24) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Mark")
    private String mark;

    /**
     * 发票编号
     */
    @Column(xmlName = "CertificateHead/InvNo", order = 16) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Inv_No")
    private String invNo;

    /**
     * 申请地址
     */
    @Column(xmlName = "CertificateHead/AplAdd", order = 14) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Apl_Addr")
    private String aplAddr;

    /**
     * 申请地址代码
     */
    @TableField(value = "Apl_Addr_Code")
    private String aplAddrCode;

    /**
     * 审核日期
     */
    @TableField(value = "Check_Date")
    private String checkDate;

    /**
     * 申报机构
     */
    @TableField(value = "Office_Declare")
    private String officeDeclare;

    /**
     * 旧证书编号
     */
    @TableField(value = "Old_Cert_No")
    private String oldCertNo;

    /**
     * 申请日期
     */
    @Column(xmlName = "CertificateHead/AplDate", order = 17) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Apply_Date")
    private String applyDate;

     /**
     * 获取转换后的申请日期 调整时间格式
     * @return 转换后的申请日期，解析失败或参数无效返回null
     */
    @SuppressWarnings("unused")
    public String getApplyDate() {
        return NodeUtils.convertDateStr(applyDate);
    }

    /**
     * 发票日期
     */
    @Column(xmlName = "CertificateHead/InvDate", order = 15) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Inv_Date")
    private String invDate;
    /**
     * 获取转换后的发票日期 调整时间格式
     * @return 转换后的发票日期，解析失败或参数无效返回null
     */
    @SuppressWarnings("unused")
    public String getInvDate() {
        return NodeUtils.convertDateStr(invDate);
    }

    /**
     * 旧证书日期
     */
    @TableField(value = "Old_Cert_Date")
    private String oldCertDate;

    /**
     * 货物规格条款
     */
    @Column(xmlName = "CertificateHead/GoodsSpecClause", order = 23) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Goods_Spec_Clause")
    private String goodsSpecClause;

    /**
     * 条形码
     */
    @TableField(value = "Bar_Code")
    private String barCode;

    /**
     * 原产国代码
     */
    @Column(xmlName = "CertificateHead/OriCountryCode", order = 56) // 补充order=56，匹配XML节点顺序
    @TableField(value = "Ori_Country_Code")
    private String oriCountryCode;

    /**
     * 原产国
     */
    @Column(xmlName = "CertificateHead/OriCountry", order = 57) // 补充order=57，匹配XML节点顺序
    @TableField(value = "Ori_Country")
    private String oriCountry;

    /**
     * 装货港
     */
    @Column(xmlName = "CertificateHead/LoadPort", order = 25) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Load_Port")
    private String loadPort;

    /**
     * 卸货港
     */
    @Column(xmlName = "CertificateHead/UnloadPort", order = 26) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Unload_Port")
    private String unloadPort;

    /**
     * 目的港
     */
    @Column(xmlName = "CertificateHead/DestPort", order = 32) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Dest_Port")
    private String destPort;

    /**
     * 运输详情
     */
    @Column(xmlName = "CertificateHead/TransDetails", order = 33) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Trans_Details")
    private String transDetails;

    /**
     * 运输名称
     */
    @Column(xmlName = "CertificateHead/TransName", order = 28) // 补充order=28，匹配XML节点顺序（修正原始order冲突，保留原始配置风格）
    @TableField(value = "Trans_Name")
    private String transName;

    /**
     * 申请书备注
     */
    @Column(xmlName = "CertificateHead/Note", order = 38) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Remark")
    private String remark;

    /**
     * 证书备注信息
     */
    @Column(xmlName = "CertificateHead/Remark", order = 44) // 补充order=44，匹配XML节点顺序（修正原始order冲突）
    @TableField(value = "remark")
    private String remark2;

    /**
     * 机构英文名称
     */
    @TableField(value = "Org_Name_E")
    private String orgNameE;

    /**
     * 审核地址
     */
    @TableField(value = "Check_Address")
    private String checkAddress;

    /**
     * 英文地址 冗余字段
     */
    @TableField(value = "Address_En")
    private String addressEn;

    /**
     * 机构电话
     */
    @TableField(value = "Org_Tel")
    private String orgTel;

    /**
     * 机构传真
     */
    @TableField(value = "Org_Fax")
    private String orgFax;

    /**
     * 指纹发送状态
     */
    @TableField(value = "Fingerprint_Send")
    private String fingerprintSend;

    /**
     * 指纹接收状态
     */
    @TableField(value = "Fingerprint_Receive")
    private String fingerprintReceive;

    /**
     * 指纹打印状态
     */
    @TableField(value = "Fingerprint_Print")
    private String fingerprintPrint;

    /**
     * 有效标识
     */
    @TableField(value = "Valid_Flag")
    private String validFlag;

    /**
     * 锁定标识
     */
    @TableField(value = "Lock_Flag")
    private String lockFlag;

    /**
     * 生产商
     */
    @Column(xmlName = "CertificateHead/Producer", order = 61) // 补充order=61，匹配XML节点顺序
    @TableField(value = "Producer")
    private String producer;

    /**
     * 拟出口日期
     */
    @Column(xmlName = "CertificateHead/IntendExpDate", order = 34) // 补充order=34，匹配XML节点顺序（修正原始order冲突）
    @TableField(value = "Intend_Exp_Date")
    private String intendExpDate;
    /**
     * 获取转换后的拟出口日期 调整时间格式
     * @return 转换后的拟出口日期，解析失败或参数无效返回null
     */
    @SuppressWarnings("unused")
    public String getIntendExpDate() {
        return NodeUtils.convertDateStr(intendExpDate);
    }

    /**
     * 展品标识
     */
    @Column(xmlName = "CertificateHead/ExhibitFlag", order = 46) // 补充order=46，匹配XML节点顺序
    @TableField(value = "Exhibit_Flag")
    private String exhibitFlag;

    /**
     * 第三方发票标识
     */
    @Column(xmlName = "CertificateHead/ThirdPartyInvFlag", order = 47) // 补充order=47，匹配XML节点顺序
    @TableField(value = "Third_Inv_Flag")
    private String thirdInvFlag;

    /**
     * 补发标识
     */
    @Column(xmlName = "CertificateHead/CertStatus", order = 3) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Reissue_Flag")
    private String reissueFlag;

    /**
     * 获取补发标识 处理转换逻辑
     * @return 补发标识
     */
    @SuppressWarnings("unused")
    public String getReissueFlag() {
        if ("1".equals(reissueFlag)) {
            return "2";
        } else {
            return "0";
        }
    }

    /**
     * 出口商电话
     */
    @Column(xmlName = "CertificateHead/ExporterTel", order = 48) // 补充order=48，匹配XML节点顺序
    @TableField(value = "Exporter_Tel")
    private String exporterTel;

    /**
     * 出口商传真
     */
    @Column(xmlName = "CertificateHead/ExporterFax", order = 49) // 补充order=49，匹配XML节点顺序
    @TableField(value = "Exporter_Fax")
    private String exporterFax;

    /**
     * 出口商邮箱
     */
    @Column(xmlName = "CertificateHead/ExporterEmail", order = 50) // 补充order=50，匹配XML节点顺序
    @TableField(value = "Exporter_Email")
    private String exporterEmail;

    /**
     * 收货人电话
     */
    @Column(xmlName = "CertificateHead/ConsigneeTel", order = 51) // 补充order=51，匹配XML节点顺序
    @TableField(value = "Consignee_Tel")
    private String consigneeTel;

    /**
     * 收货人传真
     */
    @Column(xmlName = "CertificateHead/ConsigneeFax", order = 52) // 补充order=52，匹配XML节点顺序
    @TableField(value = "Consignee_Fax")
    private String consigneeFax;

    /**
     * 收货人邮箱
     */
    @Column(xmlName = "CertificateHead/ConsigneeEmail", order = 53) // 补充order=53，匹配XML节点顺序
    @TableField(value = "Consignee_Email")
    private String consigneeEmail;

    /**
     * 最终审核日期
     */
    @TableField(value = "End_Check_Date")
    private String endCheckDate;

    /**
     * 指纹版本
     */
    @TableField(value = "Fingerprint_Version")
    private String fingerprintVersion;

    /**
     * 发送时间
     */
    @TableField(value = "Send_Time")
    private String sendTime;

    /**
     * 算法版本
     */
    @TableField(value = "Arithmetic_Version")
    private String arithmeticVersion;

    /**
     * 打印视图
     */
    @TableField(value = "Print_Views")
    private String printViews;

    /**
     * 企业申报编号
     */
    @TableField(value = "Ent_Declare_No")
    private String entDeclareNo;

    /**
     * 模块ID
     */
    @TableField(value = "Module_Id")
    private String moduleId;

    /**
     * 单据状态
     */
    @TableField(value = "Bill_Status")
    private String billStatus;

    /**
     * 上传状态
     */
    @TableField(value = "Upload_Status")
    private String uploadStatus;

    /**
     * 用户ID
     */
    @TableField(value = "User_Id")
    private String userId;

    /**
     * 操作时间
     */
    @TableField(value = "Oper_Time")
    private String operTime;

    /**
     * 数据来源
     */
    @TableField(value = "Data_Source")
    private String dataSource;

    /**
     * 验证码
     */
    @TableField(value = "Verify_Code")
    private String verifyCode;

    /**
     * 出口申报日期
     */
    @Column(xmlName = "CertificateHead/ExpDeclDate", order = 55) // 补充order=55，匹配XML节点顺序
    @TableField(value = "Exp_Decl_Date")
    private String expDeclDate;

     /**
     * 获取转换后的出口申报日期 调整时间格式
     * @return 转换后的出口申报日期，解析失败或参数无效返回null
     */
    @SuppressWarnings("unused")
    public String getExpDeclDate() {
        return NodeUtils.convertDateStr(expDeclDate);
    }

    /**
     * 出口状态
     */
    @TableField(value = "Export_State")
    private String exportState;

    /**
     * 三证合一注册号
     */
    @TableField(value = "Three_Corp_Reg_No")
    private String threeCorpRegNo;

    /**
     * 加工装配信息
     */
    @Column(xmlName = "CertificateHead/PrcsAssembly", order = 62) // 补充order=62，匹配XML节点顺序
    @TableField(value = "PrcsAssembly")
    private String prcsAssembly;

    /**
     * 运输方式
     */
    @Column(xmlName = "CertificateHead/TransPort", order = 31) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Trans_Port")
    private String transPort;

    /**
     * 录入ID
     */
    @Column(xmlName = "CertificateHead/EntryId", order = 63) // 补充order=63，匹配XML节点顺序
    @TableField(value = "Entry_Id")
    private String entryId;

    /**
     * FOB价值
     */
    @Column(xmlName = "CertificateHead/FOBValue", order = 36) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Fob_Value")
    private String fobValue;

    /**
     * 总金额
     */
    @TableField(value = "Total_Amt")
    private String totalAmt;

    /**
     * 运输工具
     */
    @Column(xmlName = "CertificateHead/TransMeans", order = 27) // 保留原有配置，order匹配XML顺序
    @TableField(value = "Trans_Means")
    private String transMeans;

    /**
     * 审核有效日期
     */
    @Column(xmlName = "CertificateHead/ChkValidDate", order = 58) // 补充order=58，匹配XML节点顺序
    @TableField(value = "Chk_Valid_Date")
    private String chkValidDate;

     /**
     * 获取转换后的审核有效日期 调整时间格式
     * @return 转换后的审核有效日期，解析失败或参数无效返回null
     */
    @SuppressWarnings("unused")
    public String getChkValidDate() {
        return NodeUtils.convertDateStr(chkValidDate);
    }

    /**
     * 申请承诺代码
     */
    @Column(xmlName = "AplPromise/AplPromiseCode", order = 76) // 无对应XML节点顺序，保留原有配置，不补充order
    @TableField(value = "Apl_Promise_Code")
    private String aplPromiseCode;

    /**
     * 生产企业是否有保密证书
     */
    @Column(xmlName = "CertificateHead/ProducerSertFlag", order = 45) // 补充order=45，匹配XML节点顺序
    @TableField(exist = false)
    private String producerSertFlag;
}