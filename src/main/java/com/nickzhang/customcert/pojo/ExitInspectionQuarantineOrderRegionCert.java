package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import lombok.Data;

/**
 * @Author: 张骏山
 * @Date: 2026/1/9 11:06
 * @PackageName: com.nickzhang.customcert.pojo
 * @ClassName: ExitInspectionQuarantineOrderRegionCert
 * @Description: 出入境检疫单申请单证明细实体类
 * @Version: 1.0
 */
@Data
// 1. 表名映射：添加 MyBatis-Plus @TableName，value 与 @Table.dbName 一致
@TableName("Dcl_B_Io_Region_Cert")
// 2. 保留自定义 @Table 注解，维护所有业务属性（xmlName、dbName、belongTo）
@Table(
        xmlName = "ITF_DCL_IO_DECL_CERT_TYPE",
        dbName = "Dcl_B_Io_Region_Cert",
        belongTo = ExitInspectionQuarantineOrder.class
)
public class ExitInspectionQuarantineOrderRegionCert {

    /**
     * 检疫单证ID
     */
    @TableId
    // 3. 字段映射：添加 MyBatis-Plus @TableField，value 与 @Column.dbName 一致
    @TableField("Dcl_Cert_Id")
    @Column(xmlName = "Dcl_Cert_Id", dbName = "Dcl_Cert_Id")
    private String dclCertId;

    /**
     * 申报ID（关联主表）
     */
    @TableField("Decl_Id")
    @Column(xmlName = "Decl_Id", dbName = "Decl_Id", joinKey = true, joinColumn = "declId")
    private String declId;

    /**
     * 企业申报编号（关联主表）
     */
    @TableField("Ent_Decl_No")
    @Column(xmlName = "Ent_Decl_No", dbName = "Ent_Decl_No")
    private String entDeclNo;

    /**
     * 申请单证代码
     */
    @TableField("App_Cert_Code")
    @Column(xmlName = "", dbName = "App_Cert_Code")
    private String appCertCode;

    /**
     * 申请单证正本数
     */
    @TableField("Appl_Ori")
    @Column(xmlName = "ApplOri", dbName = "Appl_Ori")
    private String applOri;

    /**
     * 申请单证副本数
     */
    @TableField("Appl_Copy_Quan")
    @Column(xmlName = "ApplCopyQuan", dbName = "Appl_Copy_Quan")
    private String applCopyQuan;

    /**
     * 申请单证名称（关联主节点ITF_DCL_IO_DECL）
     */
    @TableField("App_Cert_Name")
    @Column(xmlName = "ITF_DCL_IO_DECL/AppCertName", dbName = "App_Cert_Name")
    private String appCertName;
}