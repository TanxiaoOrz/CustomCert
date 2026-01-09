package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
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
@Table(xmlName = "EEntDeclIo", dbName = "Dcl_B_Io_Decl", showName = "出入境检疫单")
public class ExitInspectionQuarantineOrder {

    /**
     * 申报ID
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Decl_Id", dbName = "Decl_Id")
    @TableId
    private String declId;

    /**
     * 企业申报编号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Ent_Decl_No", dbName = "Ent_Decl_No")
    private String entDeclNo;

    /**
     * 申报编号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Decl_No", dbName = "Decl_No")
    private String declNo;

    /**
     * 申报获取编号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Decl_Get_No", dbName = "Decl_Get_No")
    private String declGetNo;

    /**
     * 贸易方式代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/TradeModeCode", dbName = "Trade_Mode_Code")
    private String tradeModeCode;

    /**
     * 合同号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ContractNo", dbName = "Contract_No")
    private String contractNo;

    /**
     * 标记及号码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/MarkNo", dbName = "Mark_No")
    private String markNo;

    /**
     * 贸易国别代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqTradeCountryCode", dbName = "Trade_Country_Code")
    private String tradeCountryCode;

    /**
     * 启运国家代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqDespCtryCode", dbName = "Desp_Ctry_Code")
    private String despCtryCode;

    /**
     * 运输方式代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqTrafMode", dbName = "Trans_Mode_Code")
    private String transModeCode;

    /**
     * 运输工具名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/TrafName", dbName = "Convynce_Name")
    private String convynceName;

    /**
     * 运输工具号码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CusVoyageNo", dbName = "Trans_Mean_No")
    private String transMeanNo;

    /**
     * 启运口岸代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/DespPortCode", dbName = "Desp_Port_Code")
    private String despPortCode;

    /**
     * 经停口岸代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/PortStopCode", dbName = "Port_Stop_Code")
    private String portStopCode;

    /**
     * 入境口岸代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqEntyPortCode", dbName = "Enty_Port_Code")
    private String entyPortCode;

    /**
     * 到货日期
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/GdsArvlDate", dbName = "Gds_Arvl_Date")
    private String gdsArvlDate;

    /**
     * 卸毕日期
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CmplDschrgDt", dbName = "Cmpl_Dschrg_Dt")
    private String cmplDschrgDt;

    /**
     * 存放地点
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/GoodsPlace", dbName = "Goods_Place")
    private String goodsPlace;

    /**
     * 目的地代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqDestCode", dbName = "Dest_Code")
    private String destCode;

    /**
     * 索赔截止日期
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CounterClaim", dbName = "Counter_Claim")
    private String counterClaim;

    /**
     * 提/运单号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/BillNo", dbName = "Bill_Lad_No")
    private String billLadNo;

    /**
     * 提货单号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/DeliveryOrder", dbName = "Delivery_Order")
    private String deliveryOrder;

    /**
     * 口岸机构
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/InspOrgCode", dbName = "Insp_Org_Code")
    private String inspOrgCode;

    /**
     * 特殊检验检疫机构代码（预留，对应XML同级节点）
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Exc_Insp_Dept_Code", dbName = "Exc_Insp_Dept_Code")
    private String excInspDeptCode;

    /**
     * 申报海关
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CustomMaster", dbName = "Decl_Custm")
    private String declCustm;

    /**
     * 特种业务标识
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/SpecDeclFlag", dbName = "Spec_Decl_Flag")
    private String specDeclFlag;

    /**
     * 目的机构代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/PurpOrgCode", dbName = "Purp_Org_Code")
    private String purpOrgCode;

    /**
     * 关联检验检疫申请号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CorrelationDeclNo", dbName = "Correlation_Decl_No")
    private String correlationDeclNo;

    /**
     * 关联理由
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CorrelationReasonFlag", dbName = "Correlation_Reason_Flag")
    private String correlationReasonFlag;

    /**
     * 特殊检验检疫要求
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/SpeclInspQuraRe", dbName = "Specl_Insp_Qura_Re")
    private String speclInspQuraRe;

    /**
     * 申请单证代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL_CERT_TYPE/AppCertCode", dbName = "App_Cert_Code")
    private String appCertCode;

    /**
     * 申请单证名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/AppCertName", dbName = "App_Cert_Name")
    private String appCertName;

    /**
     * 申请单证正本数
     */
    @Column(xmlName = "ITF_DCL_IO_DECL_CERT_TYPE/ApplOri", dbName = "Appl_Ori")
    private Integer applOri;

    /**
     * 申请单证副本数
     */
    @Column(xmlName = "ITF_DCL_IO_DECL_CERT_TYPE/ApplCopyQuan", dbName = "Appl_Copy_Quan")
    private Integer applCopyQuan;

    /**
     * 海关注册号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CustmRegNo", dbName = "Custm_Reg_No")
    private String custmRegNo;

    /**
     * 检验检疫申请员证号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclPersnCertNo", dbName = "Decl_Persn_Cert_No")
    private String declPersnCertNo;

    /**
     * 检验检疫申请员姓名
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclPersonName", dbName = "Decl_Person_Name")
    private String declPersonName;

    /**
     * 检验检疫申请单位代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclRegNo", dbName = "Decl_Reg_No")
    private String declRegNo;

    /**
     * 检验检疫申请单位名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclRegName", dbName = "Decl_Reg_Name")
    private String declRegName;

    /**
     * 检验检疫申请单位联系人
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Contactperson", dbName = "Contactperson")
    private String contactperson;

    /**
     * 检验检疫申请联系人电话
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ContTel", dbName = "Cont_Tel")
    private String contTel;

    /**
     * 收货人代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeCode", dbName = "Consignee_Code")
    private String consigneeCode;

    /**
     * 收货人名称（中文）
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeCname", dbName = "Consignee_Cname")
    private String consigneeCname;

    /**
     * 收货人名称（外文）
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeEname", dbName = "Consignee_Ename")
    private String consigneeEname;

    /**
     * 收货人地址
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeAddr", dbName = "Consignee_Addr")
    private String consigneeAddr;

    /**
     * 发货人代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorCode", dbName = "Consignor_Code")
    private String consignorCode;

    /**
     * 发货人名称（中文）
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorCname", dbName = "Consignor_Cname")
    private String consignorCname;

    /**
     * 发货人名称（外文）
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorEname", dbName = "Consignor_Ename")
    private String consignorEname;

    /**
     * 发货人地址
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorAddr", dbName = "Consignor_Addr")
    private String consignorAddr;

    /**
     * 检验检疫申请类别代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclCode", dbName = "Decl_Code")
    private String declCode;

    /**
     * 检验检疫申请申报日期
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclDate", dbName = "Decl_Date")
    private String declDate;

    /**
     * 特殊通关模式
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/SpecPassFlag", dbName = "Spec_Pass_Flag")
    private String specPassFlag;

    /**
     * 入境：启运日期 出境：发货日期
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/DespDate", dbName = "Desp_Date")
    private String despDate;

    /**
     * 到达口岸代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ArrivPortCode", dbName = "Arriv_Port_Code")
    private String arrivPortCode;

    /**
     * 随附单据代码（预留，对应XML随附单据节点）
     */
    @Column(xmlName = "ITF_DCL_IO_DECL_ATT/AttDocTypeCode", dbName = "Atta_Collect_Code")
    private String attaCollectCode;

    /**
     * 随附单据名称串
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/AttaCollectName", dbName = "Atta_Collect_Name")
    private String attaCollectName;

    /**
     * 是否列名货物
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Is_List_Good", dbName = "Is_List_Good")
    private String isListGood;

    /**
     * 是否集装箱
     */
    @Column(xmlName = "ITF_DCL_IO_DECL_CONT_DETAIL/LclFlag", dbName = "Is_Cont")
    private String isCont;

    /**
     * 边防检查标识
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Ffj_Flag", dbName = "Ffj_Flag")
    private String ffjFlag;

    /**
     * 边防检查状态
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Ffj_Status", dbName = "Ffj_Status")
    private String ffjStatus;

    /**
     * 重发次数
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Resend_Num", dbName = "Resend_Num")
    private Integer resendNum;

    /**
     * 是否提取
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Is_Draw", dbName = "Is_Draw")
    private String isDraw;

    /**
     * 货物总值（美元）
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/TotalValUs", dbName = "Total_Val_Us")
    private String totalValUs;

    /**
     * 货物总值（人民币）
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/TotalValCn", dbName = "Total_Val_Cn")
    private String totalValCn;

    /**
     * 集装箱适载核销状态
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ContCancelFlag", dbName = "Cont_Cancel_Flag")
    private String contCancelFlag;

    /**
     * 费用处理状态
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Fee_Handle_State", dbName = "Fee_Handle_State")
    private String feeHandleState;

    /**
     * 放行状态
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Rels_State", dbName = "Rels_State")
    private String relsState;

    /**
     * 内陆口岸标识
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Flg_Port_Inland", dbName = "Flg_Port_Inland")
    private String flgPortInland;

    /**
     * 允许转运标识
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Enable_Trans_Flag", dbName = "Enable_Trans_Flag")
    private String enableTransFlag;

    /**
     * 处理状态
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Process_Status", dbName = "Process_Status")
    private String processStatus;

    /**
     * 处理环节
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Process_Link", dbName = "Process_Link")
    private String processLink;

    /**
     * 情况代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Situation_Code", dbName = "Situation_Code")
    private String situationCode;

    /**
     * 情况等级
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Situation_Level", dbName = "Situation_Level")
    private String situationLevel;

    /**
     * 检验检疫申请地
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/OrgCode", dbName = "Org_Code")
    private String orgCode;

    /**
     * 证书作废标识
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Cert_Cancel_Flag", dbName = "Cert_Cancel_Flag")
    private String certCancelFlag;

    /**
     * 操作代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Oper_Code", dbName = "Oper_Code")
    private String operCode;

    /**
     * 操作时间
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Oper_Time", dbName = "Oper_Time")
    private String operTime;

    /**
     * 归档标识
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Falg_Archive", dbName = "Falg_Archive")
    private String falgArchive;

    /**
     * 单据状态
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Bill_Status", dbName = "Bill_Status")
    private String billStatus;

    /**
     * 上传状态
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Upload_Status", dbName = "Upload_Status")
    private String uploadStatus;

    /**
     * 模块ID
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Module_Id", dbName = "Module_Id")
    private String moduleId;

    /**
     * 用户ID
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/User_Id", dbName = "User_Id")
    private String userId;

    /**
     * 验证代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Verify_Code", dbName = "Verify_Code")
    private String verifyCode;

    /**
     * 数据来源
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Data_Source", dbName = "Data_Source")
    private String dataSource;

    /**
     * 贸易方式名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Trade_Mode_Name", dbName = "Trade_Mode_Name")
    private String tradeModeName;

    /**
     * 贸易国别名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Trade_Country_Name", dbName = "Trade_Country_Name")
    private String tradeCountryName;

    /**
     * 启运国家名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Desp_Ctry_Name", dbName = "Desp_Ctry_Name")
    private String despCtryName;

    /**
     * 启运口岸名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Desp_Port_Name", dbName = "Desp_Port_Name")
    private String despPortName;

    /**
     * 经停口岸名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Port_Stop_Name", dbName = "Port_Stop_Name")
    private String portStopName;

    /**
     * 入境口岸名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Enty_Port_Name", dbName = "Enty_Port_Name")
    private String entyPortName;

    /**
     * 目的地名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Dest_Name", dbName = "Dest_Name")
    private String destName;

    /**
     * 口岸机构名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Insp_Org_Name", dbName = "Insp_Org_Name")
    private String inspOrgName;

    /**
     * 申报海关名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Decl_Custm_Name", dbName = "Decl_Custm_Name")
    private String declCustmName;

    /**
     * 特种标识内容
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Spec_Flag_Content", dbName = "Spec_Flag_Content")
    private String specFlagContent;

    /**
     * 目的机构名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Purp_Org_Name", dbName = "Purp_Org_Name")
    private String purpOrgName;

    /**
     * 关联理由内容
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Correlation_Reason_Content", dbName = "Correlation_Reason_Content")
    private String correlationReasonContent;

    /**
     * 申报类型名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Decl_Type_Name", dbName = "Decl_Type_Name")
    private String declTypeName;

    /**
     * 特殊通关内容
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Spec_Pass_Content", dbName = "Spec_Pass_Content")
    private String specPassContent;

    /**
     * 到达口岸名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Arriv_Port_Name", dbName = "Arriv_Port_Name")
    private String arrivPortName;

    /**
     * 运输方式名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Trans_Mode_Name", dbName = "Trans_Mode_Name")
    private String transModeName;

    /**
     * 申请类型
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Apl_Kind", dbName = "Apl_Kind")
    private String aplKind;

    /**
     * 汇总申报ID
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Sum_Decl_Id", dbName = "Sum_Decl_Id")
    private String sumDeclId;

    /**
     * 分运单号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/SplitBillLadNo", dbName = "Split_Bill_Lad_No")
    private String splitBillLadNo;

    /**
     * 机构名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Org_Name", dbName = "Org_Name")
    private String orgName;

    /**
     * 领证机关
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/VsaOrgCode", dbName = "Vsa_Org_Code")
    private String vsaOrgCode;

    /**
     * 领证机关名称
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Vsa_Org_Name", dbName = "Vsa_Org_Name")
    private String vsaOrgName;

    /**
     * 原集装箱标识
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/OrigBoxFlag", dbName = "Orig_Box_Flag")
    private String origBoxFlag;

    /**
     * 打印视图
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Print_Views", dbName = "Print_Views")
    private String printViews;

    /**
     * 货物海关ID
     */
    @Column(xmlName = "ITF_DCL_IO_DECL_GOODS/GNo", dbName = "Goods_Customs_Id")
    private String goodsCustomsId;

    /**
     * 检验检疫单据号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Insp_Bill_No", dbName = "Insp_Bill_No")
    private String inspBillNo;

    /**
     * 出口状态
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Export_State", dbName = "Export_State")
    private String exportState;

    /**
     * 响应流程状态
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Rsp_Flow_State", dbName = "Rsp_Flow_State")
    private String rspFlowState;

    /**
     * 海关申报关联号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Cus_Decl_Related_No", dbName = "Cus_Decl_Related_No")
    private String cusDeclRelatedNo;

    /**
     * 是否勾选企业承诺
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/IsCopPromise", dbName = "IsCopPromise")
    private String isCopPromise;

    /**
     * 海关登记号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/Cus_Reg_No", dbName = "Cus_Reg_No")
    private String cusRegNo;

    /**
     * 企业内部编号
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/CopNo", dbName = "Cop_No")
    private String copNo;

    /**
     * 检验检疫申请单位统一社会信用代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclRegScc", dbName = "DeclReg_Scc")
    private String declRegScc;

    /**
     * 发货人统一社会信用代码
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorScc", dbName = "Consignor_Scc")
    private String consignorScc;

}
