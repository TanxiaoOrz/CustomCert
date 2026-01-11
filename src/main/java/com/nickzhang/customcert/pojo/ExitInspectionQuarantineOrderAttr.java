package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import lombok.Data;

/**
 * @Author: 张骏山
 * @Date: 2026/1/11 19:54
 * @PackageName: com.nickzhang.customcert.pojo
 * @ClassName: ExitInspectionQuarantineOrderAttr
 * @Description: 出入境检疫申报随附单据
 * @Version: 1.0
 */
@Data
// 精准映射数据库表名 dbo.Dcl_B_Io_Decl_Att，自定义注解维护XML节点和业务属性
@TableName("Dcl_B_Io_Decl_Att")
@Table(xmlName = "ITF_DCL_IO_DECL_ATT", showName = "出入境检疫申报随附单据", belongTo = ExitInspectionQuarantineOrder.class)
public class ExitInspectionQuarantineOrderAttr {

    // ==================== 数据库核心字段（dbo.Dcl_B_Io_Decl_Att）+ XML 节点映射 ====================
    /**
     * 随附单据主键ID
     */
    @TableField("Att_Id")
    @Column(xmlName = "")
    @TableId
    private String attId;

    /**
     * 申报ID（关联主表 Dcl_B_Io_Decl 的 Decl_Id）
     */
    @TableField("Decl_Id")
    @Column(xmlName = "", joinKey = true, joinColumn = "declId")
    private String declId;

    /**
     * 企业申报编号（关联主表 Dcl_B_Io_Decl 的 Ent_Decl_No）
     */
    @TableField("Ent_Decl_No")
    @Column(xmlName = "")
    private String entDeclNo;

    /**
     * 随附单据类别代码（映射XML + 数据库字段）
     */
    @TableField("Att_Doc_Type_Code")
    @Column(xmlName = "AttDocTypeCode")
    private String attDocTypeCode;

    /**
     * 随附单据编号（映射XML + 数据库字段）
     */
    @TableField("Att_Doc_No")
    @Column(xmlName = "AttDocNo")
    private String attDocNo;

    /**
     * 随附单据名称（映射XML + 数据库字段）
     */
    @TableField("Att_Doc_Name")
    @Column(xmlName = "AttDocName")
    private String attDocName;

    /**
     * 随附单据核销货物序号（映射XML + 数据库字段）
     */
    @TableField("Att_Doc_Wrtof_Detail_No")
    @Column(xmlName = "AttDocWrtofDetailNo")
    private String attDocWrtofDetailNo;

    /**
     * 随附单据核销数量（映射XML + 数据库字段）
     */
    @TableField("Att_Doc_Wrtof_Qty")
    @Column(xmlName = "AttDocWrtofQty")
    private String attDocWrtofQty;

    /**
     * 随附单据核销后明细余量（数据库字段 + XML 节点映射）
     */
    @TableField("Att_Doc_DetailLeft")
    @Column(xmlName = "AttDocDetailLeft")
    private String attDocDetailLeft;

    /**
     * 随附单据核销后余量（数据库字段 + XML 节点映射）
     */
    @TableField("Att_Doc_WrtofLeft")
    @Column(xmlName = "AttDocWrtofLeft")
    private String attDocWrtofLeft;

    // ==================== 冗余字段（延续格式规范，无对应XML节点） ====================
    /**
     * 数据创建时间（数据库审计字段，无XML映射）
     */
    @TableField("Create_Time")
    @Column(xmlName = "")
    private String createTime;

    /**
     * 数据更新时间（数据库审计字段，无XML映射）
     */
    @TableField("Update_Time")
    @Column(xmlName = "")
    private String updateTime;
}
