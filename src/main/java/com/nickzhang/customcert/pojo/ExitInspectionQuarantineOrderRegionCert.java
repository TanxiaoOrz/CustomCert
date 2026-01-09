package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
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
@Table(xmlName = "ITF_DCL_IO_DECL_CERT_TYPE",
        dbName = "Dcl_B_Io_Region_Cert",
        belongTo = ExitInspectionQuarantineOrder.class)
public class ExitInspectionQuarantineOrderRegionCert {

    /**
     * 检疫单证ID
     */
    @TableId
    @Column(xmlName = "Dcl_Cert_Id", dbName = "Dcl_Cert_Id")
    private String dclCertId;

    /**
     * 申报ID（关联主表）
     */
    @Column(xmlName = "Decl_Id", dbName = "Decl_Id",joinKey = true,joinColumn = "Decl_Id")
    private String declId;

    /**
     * 企业申报编号（关联主表）
     */
    @Column(xmlName = "Ent_Decl_No", dbName = "Ent_Decl_No")
    private String entDeclNo;

    /**
     * 申请单证代码
     */
    @Column(xmlName = "AppCertCode", dbName = "App_Cert_Code")
    private String appCertCode;

    /**
     * 申请单证正本数
     */
    @Column(xmlName = "ApplOri", dbName = "Appl_Ori")
    private Integer applOri;

    /**
     * 申请单证副本数
     */
    @Column(xmlName = "ApplCopyQuan", dbName = "Appl_Copy_Quan")
    private Integer applCopyQuan;

    /**
     * 申请单证名称（关联主节点ITF_DCL_IO_DECL）
     */
    @Column(xmlName = "ITF_DCL_IO_DECL/AppCertName", dbName = "App_Cert_Name")
    private String appCertName;
}
