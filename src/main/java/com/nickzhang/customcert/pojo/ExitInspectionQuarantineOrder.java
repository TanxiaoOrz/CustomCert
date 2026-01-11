package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import lombok.Data;

/**
 * @Author: 张骏山
 * @Date: 2026/1/5 22:39
 * @PackageName: com.nickzhang.customcert.pojo
 * @ClassName: ExitInspectionQuarantineOrder
 * @Description: 出入境检疫单
 * @Version: 1.0
 */
@Data
// 1. 表名映射：添加 @TableName，value 赋值为 @Table 的 dbName（保持一致）
@TableName("Dcl_B_Io_Decl")
// 2. 保留自定义 @Table 注解，维护业务属性
@Table(xmlName = "EEntDeclIo", dbName = "Dcl_B_Io_Decl", showName = "出入境检疫单")
public class ExitInspectionQuarantineOrder {

    /**
     * 申报ID
     */
    // 冗余节点：xmlName 置为空
    @TableField("Decl_Id")
    @Column(xmlName = "", dbName = "Decl_Id")
    @TableId
    private String declId;

    /**
     * 企业申报编号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ent_Decl_No")
    @Column(xmlName = "", dbName = "Ent_Decl_No")
    private String entDeclNo;

    /**
     * 申报编号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Decl_No")
    @Column(xmlName = "", dbName = "Decl_No")
    private String declNo;

    /**
     * 申报获取编号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Decl_Get_No")
    @Column(xmlName = "", dbName = "Decl_Get_No")
    private String declGetNo;

    /**
     * 贸易方式代码
     */
    @TableField("Trade_Mode_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/TradeModeCode", dbName = "Trade_Mode_Code")
    private String tradeModeCode;

    /**
     * 合同号
     */
    @TableField("Contract_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/ContractNo", dbName = "Contract_No")
    private String contractNo;

    /**
     * 标记及号码
     */
    @TableField("Mark_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/MarkNo", dbName = "Mark_No")
    private String markNo;

    /**
     * 贸易国别代码
     */
    @TableField("Trade_Country_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqTradeCountryCode", dbName = "Trade_Country_Code")
    private String tradeCountryCode;

    /**
     * 启运国家代码
     */
    @TableField("Desp_Ctry_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqDespCtryCode", dbName = "Desp_Ctry_Code")
    private String despCtryCode;

    /**
     * 运输方式代码
     */
    @TableField("Trans_Mode_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqTrafMode", dbName = "Trans_Mode_Code")
    private String transModeCode;

    /**
     * 运输工具名称
     */
    @TableField("Convynce_Name")
    @Column(xmlName = "ITF_DCL_IO_DECL/TrafName", dbName = "Convynce_Name")
    private String convynceName;

    /**
     * 运输工具号码
     */
    @TableField("Trans_Mean_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/CusVoyageNo", dbName = "Trans_Mean_No")
    private String transMeanNo;

    /**
     * 启运口岸代码
     */
    @TableField("Desp_Port_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/DespPortCode", dbName = "Desp_Port_Code")
    private String despPortCode;

    /**
     * 经停口岸代码
     */
    @TableField("Port_Stop_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/PortStopCode", dbName = "Port_Stop_Code")
    private String portStopCode;

    /**
     * 入境口岸代码
     */
    @TableField("Enty_Port_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqEntyPortCode", dbName = "Enty_Port_Code")
    private String entyPortCode;

    /**
     * 到货日期
     */
    @TableField("Gds_Arvl_Date")
    @Column(xmlName = "ITF_DCL_IO_DECL/GdsArvlDate", dbName = "Gds_Arvl_Date")
    private String gdsArvlDate;

    /**
     * 卸毕日期
     */
    @TableField("Cmpl_Dschrg_Dt")
    @Column(xmlName = "ITF_DCL_IO_DECL/CmplDschrgDt", dbName = "Cmpl_Dschrg_Dt")
    private String cmplDschrgDt;

    /**
     * 存放地点
     */
    @TableField("Goods_Place")
    @Column(xmlName = "ITF_DCL_IO_DECL/GoodsPlace", dbName = "Goods_Place")
    private String goodsPlace;

    /**
     * 目的地代码
     */
    @TableField("Dest_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqDestCode", dbName = "Dest_Code")
    private String destCode;

    /**
     * 索赔截止日期
     */
    @TableField("Counter_Claim")
    @Column(xmlName = "ITF_DCL_IO_DECL/CounterClaim", dbName = "Counter_Claim")
    private String counterClaim;

    /**
     * 提/运单号
     */
    @TableField("Bill_Lad_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/BillNo", dbName = "Bill_Lad_No")
    private String billLadNo;

    /**
     * 提货单号
     */
    @TableField("Delivery_Order")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeliveryOrder", dbName = "Delivery_Order")
    private String deliveryOrder;

    /**
     * 口岸机构
     */
    @TableField("Insp_Org_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/InspOrgCode", dbName = "Insp_Org_Code")
    private String inspOrgCode;

    /**
     * 特殊检验检疫机构代码（预留，对应XML同级节点）
     */
    // 冗余节点：xmlName 置为空
    @TableField("Exc_Insp_Dept_Code")
    @Column(xmlName = "", dbName = "Exc_Insp_Dept_Code")
    private String excInspDeptCode;

    /**
     * 申报海关
     */
    @TableField("Decl_Custm")
    @Column(xmlName = "ITF_DCL_IO_DECL/CustomMaster", dbName = "Decl_Custm")
    private String declCustm;

    /**
     * 特种业务标识
     */
    @TableField("Spec_Decl_Flag")
    @Column(xmlName = "ITF_DCL_IO_DECL/SpecDeclFlag", dbName = "Spec_Decl_Flag")
    private String specDeclFlag;

    /**
     * 目的机构代码
     */
    @TableField("Purp_Org_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/PurpOrgCode", dbName = "Purp_Org_Code")
    private String purpOrgCode;

    /**
     * 关联检验检疫申请号
     */
    @TableField("Correlation_Decl_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/CorrelationDeclNo", dbName = "Correlation_Decl_No")
    private String correlationDeclNo;

    /**
     * 关联理由
     */
    @TableField("Correlation_Reason_Flag")
    @Column(xmlName = "ITF_DCL_IO_DECL/CorrelationReasonFlag", dbName = "Correlation_Reason_Flag")
    private String correlationReasonFlag;

    /**
     * 特殊检验检疫要求
     */
    @TableField("Specl_Insp_Qura_Re")
    @Column(xmlName = "ITF_DCL_IO_DECL/SpeclInspQuraRe", dbName = "Specl_Insp_Qura_Re")
    private String speclInspQuraRe;

    /**
     * 申请单证代码
     */
    @TableField("App_Cert_Code")
    @Column(xmlName = "", dbName = "App_Cert_Code")
    private String appCertCode;

    /**
     * 申请单证名称
     */
    @TableField("App_Cert_Name")
    @Column(xmlName = "ITF_DCL_IO_DECL/AppCertName", dbName = "App_Cert_Name")
    private String appCertName;

    /**
     * 申请单证正本数
     */
    @TableField("Appl_Ori")
    @Column(xmlName = "", dbName = "Appl_Ori")
    private String applOri;

    /**
     * 申请单证副本数
     */
    @TableField("Appl_Copy_Quan")
    @Column(xmlName = "", dbName = "Appl_Copy_Quan")
    private String applCopyQuan;

    /**
     * 海关注册号
     */
    @TableField("Custm_Reg_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/CustmRegNo", dbName = "Custm_Reg_No")
    private String custmRegNo;

    /**
     * 检验检疫申请员证号
     */
    @TableField("Decl_Persn_Cert_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclPersnCertNo", dbName = "Decl_Persn_Cert_No")
    private String declPersnCertNo;

    /**
     * 检验检疫申请员姓名
     */
    @TableField("Decl_Person_Name")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclPersonName", dbName = "Decl_Person_Name")
    private String declPersonName;

    /**
     * 检验检疫申请单位代码
     */
    @TableField("Decl_Reg_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclRegNo", dbName = "Decl_Reg_No")
    private String declRegNo;

    /**
     * 检验检疫申请单位名称
     */
    @TableField("Decl_Reg_Name")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclRegName", dbName = "Decl_Reg_Name")
    private String declRegName;

    /**
     * 检验检疫申请单位联系人
     */
    @TableField("Contactperson")
    @Column(xmlName = "ITF_DCL_IO_DECL/Contactperson", dbName = "Contactperson")
    private String contactperson;

    /**
     * 检验检疫申请联系人电话
     */
    @TableField("Cont_Tel")
    @Column(xmlName = "ITF_DCL_IO_DECL/ContTel", dbName = "Cont_Tel")
    private String contTel;

    /**
     * 收货人代码
     */
    @TableField("Consignee_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeCode", dbName = "Consignee_Code")
    private String consigneeCode;

    /**
     * 收货人名称（中文）
     */
    @TableField("Consignee_Cname")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeCname", dbName = "Consignee_Cname")
    private String consigneeCname;

    /**
     * 收货人名称（外文）
     */
    @TableField("Consignee_Ename")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeEname", dbName = "Consignee_Ename")
    private String consigneeEname;

    /**
     * 收货人地址
     */
    @TableField("Consignee_Addr")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeAddr", dbName = "Consignee_Addr")
    private String consigneeAddr;

    /**
     * 发货人代码
     */
    @TableField("Consignor_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorCode", dbName = "Consignor_Code")
    private String consignorCode;

    /**
     * 发货人名称（中文）
     */
    @TableField("Consignor_Cname")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorCname", dbName = "Consignor_Cname")
    private String consignorCname;

    /**
     * 发货人名称（外文）
     */
    @TableField("Consignor_Ename")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorEname", dbName = "Consignor_Ename")
    private String consignorEname;

    /**
     * 发货人地址
     */
    @TableField("Consignor_Addr")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorAddr", dbName = "Consignor_Addr")
    private String consignorAddr;

    /**
     * 检验检疫申请类别代码
     */
    @TableField("Decl_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclCode", dbName = "Decl_Code")
    private String declCode;

    /**
     * 检验检疫申请申报日期
     */
    @TableField("Decl_Date")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclDate", dbName = "Decl_Date")
    private String declDate;

    /**
     * 特殊通关模式
     */
    @TableField("Spec_Pass_Flag")
    @Column(xmlName = "ITF_DCL_IO_DECL/SpecPassFlag", dbName = "Spec_Pass_Flag")
    private String specPassFlag;

    /**
     * 入境：启运日期 出境：发货日期
     */
    @TableField("Desp_Date")
    @Column(xmlName = "ITF_DCL_IO_DECL/DespDate", dbName = "Desp_Date")
    private String despDate;

    /**
     * 到达口岸代码
     */
    @TableField("Arriv_Port_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/ArrivPortCode", dbName = "Arriv_Port_Code")
    private String arrivPortCode;

    /**
     * 随附单据代码（预留，对应XML随附单据节点）
     */
    @TableField("Atta_Collect_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL_ATT/AttDocTypeCode", dbName = "Atta_Collect_Code")
    private String attaCollectCode;

    /**
     * 随附单据名称串
     */
    @TableField("Atta_Collect_Name")
    @Column(xmlName = "ITF_DCL_IO_DECL/AttaCollectName", dbName = "Atta_Collect_Name")
    private String attaCollectName;

    /**
     * 是否列名货物
     */
    // 冗余节点：xmlName 置为空
    @TableField("Is_List_Good")
    @Column(xmlName = "", dbName = "Is_List_Good")
    private String isListGood;

    /**
     * 是否集装箱
     */
    @TableField("Is_Cont")
    @Column(xmlName = "ITF_DCL_IO_DECL_CONT_DETAIL/LclFlag", dbName = "Is_Cont")
    private String isCont;

    /**
     * 边防检查标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ffj_Flag")
    @Column(xmlName = "", dbName = "Ffj_Flag")
    private String ffjFlag;

    /**
     * 边防检查状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ffj_Status")
    @Column(xmlName = "", dbName = "Ffj_Status")
    private String ffjStatus;

    /**
     * 重发次数
     */
    // 冗余节点：xmlName 置为空
    @TableField("Resend_Num")
    @Column(xmlName = "", dbName = "Resend_Num")
    private String resendNum;

    /**
     * 是否提取
     */
    // 冗余节点：xmlName 置为空
    @TableField("Is_Draw")
    @Column(xmlName = "", dbName = "Is_Draw")
    private String isDraw;

    /**
     * 货物总值（美元）
     */
    @TableField("Total_Val_Us")
    @Column(xmlName = "ITF_DCL_IO_DECL/TotalValUs", dbName = "Total_Val_Us")
    private String totalValUs;

    /**
     * 货物总值（人民币）
     */
    @TableField("Total_Val_Cn")
    @Column(xmlName = "ITF_DCL_IO_DECL/TotalValCn", dbName = "Total_Val_Cn")
    private String totalValCn;

    /**
     * 集装箱适载核销状态
     */
    @TableField("Cont_Cancel_Flag")
    @Column(xmlName = "ITF_DCL_IO_DECL/ContCancelFlag", dbName = "Cont_Cancel_Flag")
    private String contCancelFlag;

    /**
     * 费用处理状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Fee_Handle_State")
    @Column(xmlName = "", dbName = "Fee_Handle_State")
    private String feeHandleState;

    /**
     * 放行状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Rels_State")
    @Column(xmlName = "", dbName = "Rels_State")
    private String relsState;

    /**
     * 内陆口岸标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Flg_Port_Inland")
    @Column(xmlName = "", dbName = "Flg_Port_Inland")
    private String flgPortInland;

    /**
     * 允许转运标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Enable_Trans_Flag")
    @Column(xmlName = "", dbName = "Enable_Trans_Flag")
    private String enableTransFlag;

    /**
     * 处理状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Process_Status")
    @Column(xmlName = "", dbName = "Process_Status")
    private String processStatus;

    /**
     * 处理环节
     */
    // 冗余节点：xmlName 置为空
    @TableField("Process_Link")
    @Column(xmlName = "", dbName = "Process_Link")
    private String processLink;

    /**
     * 情况代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Situation_Code")
    @Column(xmlName = "", dbName = "Situation_Code")
    private String situationCode;

    /**
     * 情况等级
     */
    // 冗余节点：xmlName 置为空
    @TableField("Situation_Level")
    @Column(xmlName = "", dbName = "Situation_Level")
    private String situationLevel;

    /**
     * 检验检疫申请地
     */
    @TableField("Org_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/OrgCode", dbName = "Org_Code")
    private String orgCode;

    /**
     * 证书作废标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Cert_Cancel_Flag")
    @Column(xmlName = "", dbName = "Cert_Cancel_Flag")
    private String certCancelFlag;

    /**
     * 操作代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Oper_Code")
    @Column(xmlName = "", dbName = "Oper_Code")
    private String operCode;

    /**
     * 操作时间
     */
    // 冗余节点：xmlName 置为空
    @TableField("Oper_Time")
    @Column(xmlName = "", dbName = "Oper_Time")
    private String operTime;

    /**
     * 归档标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Falg_Archive")
    @Column(xmlName = "", dbName = "Falg_Archive")
    private String falgArchive;

    /**
     * 单据状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Bill_Status")
    @Column(xmlName = "", dbName = "Bill_Status")
    private String billStatus;

    /**
     * 上传状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Upload_Status")
    @Column(xmlName = "", dbName = "Upload_Status")
    private String uploadStatus;

    /**
     * 模块ID
     */
    // 冗余节点：xmlName 置为空
    @TableField("Module_Id")
    @Column(xmlName = "", dbName = "Module_Id")
    private String moduleId;

    /**
     * 用户ID
     */
    // 冗余节点：xmlName 置为空
    @TableField("User_Id")
    @Column(xmlName = "", dbName = "User_Id")
    private String userId;

    /**
     * 验证代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Verify_Code")
    @Column(xmlName = "", dbName = "Verify_Code")
    private String verifyCode;

    /**
     * 数据来源
     */
    // 冗余节点：xmlName 置为空
    @TableField("Data_Source")
    @Column(xmlName = "", dbName = "Data_Source")
    private String dataSource;

    /**
     * 贸易方式名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Trade_Mode_Name")
    @Column(xmlName = "", dbName = "Trade_Mode_Name")
    private String tradeModeName;

    /**
     * 贸易国别名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Trade_Country_Name")
    @Column(xmlName = "", dbName = "Trade_Country_Name")
    private String tradeCountryName;

    /**
     * 启运国家名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Desp_Ctry_Name")
    @Column(xmlName = "", dbName = "Desp_Ctry_Name")
    private String despCtryName;

    /**
     * 启运口岸名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Desp_Port_Name")
    @Column(xmlName = "", dbName = "Desp_Port_Name")
    private String despPortName;

    /**
     * 经停口岸名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Port_Stop_Name")
    @Column(xmlName = "", dbName = "Port_Stop_Name")
    private String portStopName;

    /**
     * 入境口岸名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Enty_Port_Name")
    @Column(xmlName = "", dbName = "Enty_Port_Name")
    private String entyPortName;

    /**
     * 目的地名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Dest_Name")
    @Column(xmlName = "", dbName = "Dest_Name")
    private String destName;

    /**
     * 口岸机构名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Insp_Org_Name")
    @Column(xmlName = "", dbName = "Insp_Org_Name")
    private String inspOrgName;

    /**
     * 申报海关名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Decl_Custm_Name")
    @Column(xmlName = "", dbName = "Decl_Custm_Name")
    private String declCustmName;

    /**
     * 特种标识内容
     */
    // 冗余节点：xmlName 置为空
    @TableField("Spec_Flag_Content")
    @Column(xmlName = "", dbName = "Spec_Flag_Content")
    private String specFlagContent;

    /**
     * 目的机构名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Purp_Org_Name")
    @Column(xmlName = "", dbName = "Purp_Org_Name")
    private String purpOrgName;

    /**
     * 关联理由内容
     */
    // 冗余节点：xmlName 置为空
    @TableField("Correlation_Reason_Content")
    @Column(xmlName = "", dbName = "Correlation_Reason_Content")
    private String correlationReasonContent;

    /**
     * 申报类型名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Decl_Type_Name")
    @Column(xmlName = "", dbName = "Decl_Type_Name")
    private String declTypeName;

    /**
     * 特殊通关内容
     */
    // 冗余节点：xmlName 置为空
    @TableField("Spec_Pass_Content")
    @Column(xmlName = "", dbName = "Spec_Pass_Content")
    private String specPassContent;

    /**
     * 到达口岸名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Arriv_Port_Name")
    @Column(xmlName = "", dbName = "Arriv_Port_Name")
    private String arrivPortName;

    /**
     * 运输方式名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Trans_Mode_Name")
    @Column(xmlName = "", dbName = "Trans_Mode_Name")
    private String transModeName;

    /**
     * 申请类型
     */
    // 冗余节点：xmlName 置为空
    @TableField("Apl_Kind")
    @Column(xmlName = "", dbName = "Apl_Kind")
    private String aplKind;

    /**
     * 汇总申报ID
     */
    // 冗余节点：xmlName 置为空
    @TableField("Sum_Decl_Id")
    @Column(xmlName = "", dbName = "Sum_Decl_Id")
    private String sumDeclId;

    /**
     * 分运单号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Split_Bill_Lad_No")
    @Column(xmlName = "", dbName = "Split_Bill_Lad_No")
    private String splitBillLadNo;

    /**
     * 机构名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Org_Name")
    @Column(xmlName = "", dbName = "Org_Name")
    private String orgName;

    /**
     * 领证机关
     */
    // 冗余节点：xmlName 置为空
    @TableField("Vsa_Org_Code")
    @Column(xmlName = "", dbName = "Vsa_Org_Code")
    private String vsaOrgCode;

    /**
     * 领证机关名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Vsa_Org_Name")
    @Column(xmlName = "", dbName = "Vsa_Org_Name")
    private String vsaOrgName;

    /**
     * 原集装箱标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Orig_Box_Flag")
    @Column(xmlName = "", dbName = "Orig_Box_Flag")
    private String origBoxFlag;

    /**
     * 打印视图
     */
    // 冗余节点：xmlName 置为空
    @TableField("Print_Views")
    @Column(xmlName = "", dbName = "Print_Views")
    private String printViews;

    /**
     * 不知为何存在的id,数据库里没有值
     */
    @TableField("Goods_Customs_Id")
    @Column(xmlName = "", dbName = "Goods_Customs_Id")
    private String goodsCustomsId;

    /**
     * 检验检疫单据号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Insp_Bill_No")
    @Column(xmlName = "", dbName = "Insp_Bill_No")
    private String inspBillNo;

    /**
     * 出口状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Export_State")
    @Column(xmlName = "", dbName = "Export_State")
    private String exportState;

    /**
     * 响应流程状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Rsp_Flow_State")
    @Column(xmlName = "", dbName = "Rsp_Flow_State")
    private String rspFlowState;

    /**
     * 海关申报关联号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Cus_Decl_Related_No")
    @Column(xmlName = "", dbName = "Cus_Decl_Related_No")
    private String cusDeclRelatedNo;

    /**
     * 是否勾选企业承诺
     */
    @TableField("IsCopPromise")
    @Column(xmlName = "ITF_DCL_IO_DECL/IsCopPromise", dbName = "IsCopPromise")
    private String isCopPromise;

    /**
     * 海关登记号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Cus_Reg_No")
    @Column(xmlName = "", dbName = "Cus_Reg_No")
    private String cusRegNo;

    /**
     * 企业内部编号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Cop_No")
    @Column(xmlName = "", dbName = "Cop_No")
    private String copNo;

    /**
     * 检验检疫申请单位统一社会信用代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("DeclReg_Scc")
    @Column(xmlName = "", dbName = "DeclReg_Scc")
    private String declRegScc;

    /**
     * 发货人统一社会信用代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Consignor_Scc")
    @Column(xmlName = "", dbName = "Consignor_Scc")
    private String consignorScc;
}