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
@Table(xmlName = "EEntDeclIo", showName = "出入境检疫单")
public class ExitInspectionQuarantineOrder {

    /**
     * 申报ID
     */
    // 冗余节点：xmlName 置为空
    @TableField("Decl_Id")
    @Column(xmlName = "")
    @TableId
    private String declId;

    /**
     * 企业申报编号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ent_Decl_No")
    @Column(xmlName = "")
    private String entDeclNo;

    /**
     * 申报编号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Decl_No")
    @Column(xmlName = "")
    private String declNo;

    /**
     * 申报获取编号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Decl_Get_No")
    @Column(xmlName = "")
    private String declGetNo;

    /**
     * 贸易方式代码
     */
    @TableField("Trade_Mode_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/TradeModeCode")
    private String tradeModeCode;

    /**
     * 合同号
     */
    @TableField("Contract_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/ContractNo")
    private String contractNo;

    /**
     * 标记及号码
     */
    @TableField("Mark_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/MarkNo")
    private String markNo;

    /**
     * 贸易国别代码
     */
    @TableField("Trade_Country_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqTradeCountryCode")
    private String tradeCountryCode;

    /**
     * 启运国家代码
     */
    @TableField("Desp_Ctry_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqDespCtryCode")
    private String despCtryCode;

    /**
     * 运输方式代码
     */
    @TableField("Trans_Mode_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqTrafMode")
    private String transModeCode;

    /**
     * 运输工具名称
     */
    @TableField("Convynce_Name")
    @Column(xmlName = "ITF_DCL_IO_DECL/TrafName")
    private String convynceName;

    /**
     * 运输工具号码
     */
    @TableField("Trans_Mean_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/CusVoyageNo")
    private String transMeanNo;

    /**
     * 启运口岸代码
     */
    @TableField("Desp_Port_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/DespPortCode")
    private String despPortCode;

    /**
     * 经停口岸代码
     */
    @TableField("Port_Stop_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/PortStopCode")
    private String portStopCode;

    /**
     * 入境口岸代码
     */
    @TableField("Enty_Port_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqEntyPortCode")
    private String entyPortCode;

    /**
     * 到货日期
     */
    @TableField("Gds_Arvl_Date")
    @Column(xmlName = "ITF_DCL_IO_DECL/GdsArvlDate")
    private String gdsArvlDate;

    /**
     * 卸毕日期
     */
    @TableField("Cmpl_Dschrg_Dt")
    @Column(xmlName = "ITF_DCL_IO_DECL/CmplDschrgDt")
    private String cmplDschrgDt;

    /**
     * 存放地点
     */
    @TableField("Goods_Place")
    @Column(xmlName = "ITF_DCL_IO_DECL/GoodsPlace")
    private String goodsPlace;

    /**
     * 目的地代码
     */
    @TableField("Dest_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/CiqDestCode")
    private String destCode;

    /**
     * 索赔截止日期
     */
    @TableField("Counter_Claim")
    @Column(xmlName = "ITF_DCL_IO_DECL/CounterClaim")
    private String counterClaim;

    /**
     * 提/运单号
     */
    @TableField("Bill_Lad_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/BillNo")
    private String billLadNo;

    /**
     * 提货单号
     */
    @TableField("Delivery_Order")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeliveryOrder")
    private String deliveryOrder;

    /**
     * 口岸机构
     */
    @TableField("Insp_Org_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/InspOrgCode")
    private String inspOrgCode;

    /**
     * 特殊检验检疫机构代码（预留，对应XML同级节点）
     */
    // 冗余节点：xmlName 置为空
    @TableField("Exc_Insp_Dept_Code")
    @Column(xmlName = "")
    private String excInspDeptCode;

    /**
     * 申报海关
     */
    @TableField("Decl_Custm")
    @Column(xmlName = "ITF_DCL_IO_DECL/CustomMaster")
    private String declCustm;

    /**
     * 特种业务标识
     */
    @TableField("Spec_Decl_Flag")
    @Column(xmlName = "ITF_DCL_IO_DECL/SpecDeclFlag")
    private String specDeclFlag;

    /**
     * 目的机构代码
     */
    @TableField("Purp_Org_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/PurpOrgCode")
    private String purpOrgCode;

    /**
     * 关联检验检疫申请号
     */
    @TableField("Correlation_Decl_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/CorrelationDeclNo")
    private String correlationDeclNo;

    /**
     * 关联理由
     */
    @TableField("Correlation_Reason_Flag")
    @Column(xmlName = "ITF_DCL_IO_DECL/CorrelationReasonFlag")
    private String correlationReasonFlag;

    /**
     * 特殊检验检疫要求
     */
    @TableField("Specl_Insp_Qura_Re")
    @Column(xmlName = "ITF_DCL_IO_DECL/SpeclInspQuraRe")
    private String speclInspQuraRe;

    /**
     * 申请单证代码
     */
    @TableField("App_Cert_Code")
    @Column(xmlName = "")
    private String appCertCode;

    /**
     * 申请单证名称
     */
    @TableField("App_Cert_Name")
    @Column(xmlName = "ITF_DCL_IO_DECL/AppCertName")
    private String appCertName;

    /**
     * 申请单证正本数
     */
    @TableField("Appl_Ori")
    @Column(xmlName = "")
    private String applOri;

    /**
     * 申请单证副本数
     */
    @TableField("Appl_Copy_Quan")
    @Column(xmlName = "")
    private String applCopyQuan;

    /**
     * 海关注册号
     */
    @TableField("Custm_Reg_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/CustmRegNo")
    private String custmRegNo;

    /**
     * 检验检疫申请员证号
     */
    @TableField("Decl_Persn_Cert_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclPersnCertNo")
    private String declPersnCertNo;

    /**
     * 检验检疫申请员姓名
     */
    @TableField("Decl_Person_Name")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclPersonName")
    private String declPersonName;

    /**
     * 检验检疫申请单位代码
     */
    @TableField("Decl_Reg_No")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclRegNo")
    private String declRegNo;

    /**
     * 检验检疫申请单位名称
     */
    @TableField("Decl_Reg_Name")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclRegName")
    private String declRegName;

    /**
     * 检验检疫申请单位联系人
     */
    @TableField("Contactperson")
    @Column(xmlName = "ITF_DCL_IO_DECL/Contactperson")
    private String contactperson;

    /**
     * 检验检疫申请联系人电话
     */
    @TableField("Cont_Tel")
    @Column(xmlName = "ITF_DCL_IO_DECL/ContTel")
    private String contTel;

    /**
     * 收货人代码
     */
    @TableField("Consignee_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeCode")
    private String consigneeCode;

    /**
     * 收货人名称（中文）
     */
    @TableField("Consignee_Cname")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeCname")
    private String consigneeCname;

    /**
     * 收货人名称（外文）
     */
    @TableField("Consignee_Ename")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeEname")
    private String consigneeEname;

    /**
     * 收货人地址
     */
    @TableField("Consignee_Addr")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsigneeAddr")
    private String consigneeAddr;

    /**
     * 发货人代码
     */
    @TableField("Consignor_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorCode")
    private String consignorCode;

    /**
     * 发货人名称（中文）
     */
    @TableField("Consignor_Cname")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorCname")
    private String consignorCname;

    /**
     * 发货人名称（外文）
     */
    @TableField("Consignor_Ename")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorEname")
    private String consignorEname;

    /**
     * 发货人地址
     */
    @TableField("Consignor_Addr")
    @Column(xmlName = "ITF_DCL_IO_DECL/ConsignorAddr")
    private String consignorAddr;

    /**
     * 检验检疫申请类别代码
     */
    @TableField("Decl_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclCode")
    private String declCode;

    /**
     * 检验检疫申请申报日期
     */
    @TableField("Decl_Date")
    @Column(xmlName = "ITF_DCL_IO_DECL/DeclDate")
    private String declDate;

    /**
     * 特殊通关模式
     */
    @TableField("Spec_Pass_Flag")
    @Column(xmlName = "ITF_DCL_IO_DECL/SpecPassFlag")
    private String specPassFlag;

    /**
     * 入境：启运日期 出境：发货日期
     */
    @TableField("Desp_Date")
    @Column(xmlName = "ITF_DCL_IO_DECL/DespDate")
    private String despDate;

    /**
     * 到达口岸代码
     */
    @TableField("Arriv_Port_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/ArrivPortCode")
    private String arrivPortCode;

    /**
     * 随附单据代码（预留，对应XML随附单据节点）
     */
    // 冗余节点: 用于关联查询子表
    @TableField("Atta_Collect_Code")
    @Column(xmlName = "")
    private String attaCollectCode;

    /**
     * 随附单据名称串
     */
    @TableField("Atta_Collect_Name")
    @Column(xmlName = "ITF_DCL_IO_DECL/AttaCollectName")
    private String attaCollectName;

    /**
     * 是否列名货物
     */
    // 冗余节点：xmlName 置为空
    @TableField("Is_List_Good")
    @Column(xmlName = "")
    private String isListGood;

    /**
     * 是否集装箱
     */
    @TableField("Is_Cont")
    @Column(xmlName = "ITF_DCL_IO_DECL_CONT_DETAIL/LclFlag")
    private String isCont;

    /**
     * 边防检查标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ffj_Flag")
    @Column(xmlName = "")
    private String ffjFlag;

    /**
     * 边防检查状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Ffj_Status")
    @Column(xmlName = "")
    private String ffjStatus;

    /**
     * 重发次数
     */
    // 冗余节点：xmlName 置为空
    @TableField("Resend_Num")
    @Column(xmlName = "")
    private String resendNum;

    /**
     * 是否提取
     */
    // 冗余节点：xmlName 置为空
    @TableField("Is_Draw")
    @Column(xmlName = "")
    private String isDraw;

    /**
     * 货物总值（美元）
     */
    @TableField("Total_Val_Us")
    @Column(xmlName = "ITF_DCL_IO_DECL/TotalValUs")
    private String totalValUs;

    /**
     * 货物总值（人民币）
     */
    @TableField("Total_Val_Cn")
    @Column(xmlName = "ITF_DCL_IO_DECL/TotalValCn")
    private String totalValCn;

    /**
     * 集装箱适载核销状态
     */
    @TableField("Cont_Cancel_Flag")
    @Column(xmlName = "ITF_DCL_IO_DECL/ContCancelFlag")
    private String contCancelFlag;

    /**
     * 费用处理状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Fee_Handle_State")
    @Column(xmlName = "")
    private String feeHandleState;

    /**
     * 放行状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Rels_State")
    @Column(xmlName = "")
    private String relsState;

    /**
     * 内陆口岸标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Flg_Port_Inland")
    @Column(xmlName = "")
    private String flgPortInland;

    /**
     * 允许转运标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Enable_Trans_Flag")
    @Column(xmlName = "")
    private String enableTransFlag;

    /**
     * 处理状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Process_Status")
    @Column(xmlName = "")
    private String processStatus;

    /**
     * 处理环节
     */
    // 冗余节点：xmlName 置为空
    @TableField("Process_Link")
    @Column(xmlName = "")
    private String processLink;

    /**
     * 情况代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Situation_Code")
    @Column(xmlName = "")
    private String situationCode;

    /**
     * 情况等级
     */
    // 冗余节点：xmlName 置为空
    @TableField("Situation_Level")
    @Column(xmlName = "")
    private String situationLevel;

    /**
     * 检验检疫申请地
     */
    @TableField("Org_Code")
    @Column(xmlName = "ITF_DCL_IO_DECL/OrgCode")
    private String orgCode;

    /**
     * 证书作废标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Cert_Cancel_Flag")
    @Column(xmlName = "")
    private String certCancelFlag;

    /**
     * 操作代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Oper_Code")
    @Column(xmlName = "")
    private String operCode;

    /**
     * 操作时间
     */
    // 冗余节点：xmlName 置为空
    @TableField("Oper_Time")
    @Column(xmlName = "")
    private String operTime;

    /**
     * 归档标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Falg_Archive")
    @Column(xmlName = "")
    private String falgArchive;

    /**
     * 单据状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Bill_Status")
    @Column(xmlName = "")
    private String billStatus;

    /**
     * 上传状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Upload_Status")
    @Column(xmlName = "")
    private String uploadStatus;

    /**
     * 模块ID
     */
    // 冗余节点：xmlName 置为空
    @TableField("Module_Id")
    @Column(xmlName = "")
    private String moduleId;

    /**
     * 用户ID
     */
    // 冗余节点：xmlName 置为空
    @TableField("User_Id")
    @Column(xmlName = "")
    private String userId;

    /**
     * 验证代码
     */
    // 冗余节点：xmlName 置为空
    @TableField("Verify_Code")
    @Column(xmlName = "")
    private String verifyCode;

    /**
     * 数据来源
     */
    // 冗余节点：xmlName 置为空
    @TableField("Data_Source")
    @Column(xmlName = "")
    private String dataSource;

    /**
     * 贸易方式名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Trade_Mode_Name")
    @Column(xmlName = "")
    private String tradeModeName;

    /**
     * 贸易国别名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Trade_Country_Name")
    @Column(xmlName = "")
    private String tradeCountryName;

    /**
     * 启运国家名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Desp_Ctry_Name")
    @Column(xmlName = "")
    private String despCtryName;

    /**
     * 启运口岸名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Desp_Port_Name")
    @Column(xmlName = "")
    private String despPortName;

    /**
     * 经停口岸名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Port_Stop_Name")
    @Column(xmlName = "")
    private String portStopName;

    /**
     * 入境口岸名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Enty_Port_Name")
    @Column(xmlName = "")
    private String entyPortName;

    /**
     * 目的地名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Dest_Name")
    @Column(xmlName = "")
    private String destName;

    /**
     * 口岸机构名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Insp_Org_Name")
    @Column(xmlName = "")
    private String inspOrgName;

    /**
     * 申报海关名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Decl_Custm_Name")
    @Column(xmlName = "")
    private String declCustmName;

    /**
     * 特种标识内容
     */
    // 冗余节点：xmlName 置为空
    @TableField("Spec_Flag_Content")
    @Column(xmlName = "")
    private String specFlagContent;

    /**
     * 目的机构名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Purp_Org_Name")
    @Column(xmlName = "")
    private String purpOrgName;

    /**
     * 关联理由内容
     */
    // 冗余节点：xmlName 置为空
    @TableField("Correlation_Reason_Content")
    @Column(xmlName = "")
    private String correlationReasonContent;

    /**
     * 申报类型名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Decl_Type_Name")
    @Column(xmlName = "")
    private String declTypeName;

    /**
     * 特殊通关内容
     */
    // 冗余节点：xmlName 置为空
    @TableField("Spec_Pass_Content")
    @Column(xmlName = "")
    private String specPassContent;

    /**
     * 到达口岸名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Arriv_Port_Name")
    @Column(xmlName = "")
    private String arrivPortName;

    /**
     * 运输方式名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Trans_Mode_Name")
    @Column(xmlName = "")
    private String transModeName;

    /**
     * 申请类型
     */
    // 冗余节点：xmlName 置为空
    @TableField("Apl_Kind")
    @Column(xmlName = "")
    private String aplKind;

    /**
     * 汇总申报ID
     */
    // 冗余节点：xmlName 置为空
    @TableField("Sum_Decl_Id")
    @Column(xmlName = "")
    private String sumDeclId;

    /**
     * 分运单号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Split_Bill_Lad_No")
    @Column(xmlName = "")
    private String splitBillLadNo;

    /**
     * 机构名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Org_Name")
    @Column(xmlName = "")
    private String orgName;

    /**
     * 领证机关
     */
    // 冗余节点：xmlName 置为空
    @TableField("Vsa_Org_Code")
    @Column(xmlName = "vsaOrgCode")
    private String vsaOrgCode;

    /**
     * 领证机关名称
     */
    // 冗余节点：xmlName 置为空
    @TableField("Vsa_Org_Name")
    @Column(xmlName = "")
    private String vsaOrgName;

    /**
     * 原集装箱标识
     */
    // 冗余节点：xmlName 置为空
    @TableField("Orig_Box_Flag")
    @Column(xmlName = "OrigBoxFlag")
    private String origBoxFlag;

    /**
     * 打印视图
     */
    // 冗余节点：xmlName 置为空
    @TableField("Print_Views")
    @Column(xmlName = "")
    private String printViews;

    /**
     * 不知为何存在的id,数据库里没有值
     */
    @TableField("Goods_Customs_Id")
    @Column(xmlName = "")
    private String goodsCustomsId;

    /**
     * 检验检疫单据号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Insp_Bill_No")
    @Column(xmlName = "")
    private String inspBillNo;

    /**
     * 出口状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Export_State")
    @Column(xmlName = "")
    private String exportState;

    /**
     * 响应流程状态
     */
    // 冗余节点：xmlName 置为空
    @TableField("Rsp_Flow_State")
    @Column(xmlName = "")
    private String rspFlowState;

    /**
     * 海关申报关联号
     */
    // 冗余节点：xmlName 置为空
    @TableField("Cus_Decl_Related_No")
    @Column(xmlName = "")
    private String cusDeclRelatedNo;

    /**
     * 是否勾选企业承诺
     */
    @TableField("IsCopPromise")
    @Column(xmlName = "ITF_DCL_IO_DECL/IsCopPromise")
    private String isCopPromise;

    /**
     * 海关登记号
     */
    @TableField("Cus_Reg_No")
    @Column(xmlName = "")
    private String cusRegNo;

    /**
     * 企业内部编号
     */
    @TableField("Cop_No")
    @Column(xmlName = "CopNo")
    private String copNo;

    /**
     * 检验检疫申请单位统一社会信用代码
     */
    @TableField("DeclReg_Scc")
    @Column(xmlName = "DeclRegScc")
    private String declRegScc;

    /**
     * 发货人统一社会信用代码
     */
    @TableField("Consignor_Scc")
    @Column(xmlName = "consignorScc")
    private String consignorScc;
}