package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author: 张骏山
 * @Date: 2026/1/11 20:53
 * @PackageName: com.nickzhang.customcert.pojo
 * @ClassName: OriginCertificateFile
 * @Description: 海关原产地附件实体类
 * @Version: 1.0
 */
@Data
@TableName(value = "Ori_B_DocumentAttach") // 新规则：映射数据库完整原始表名
@Table(xmlName = "File",
        belongTo = OriginCertificate.class,
        dividedFile = true,
        showName = "海关原产地附件",
        filePath = "CooFile/"
) // xmlName指定为XML核心节点File
public class OriginCertificateFile {

    /**
     * 原产地附件ID（数据表主键，XML无对应节点）
     */
    @Column(xmlName = "") // 数据表独有字段，XML无对应，保留字段名格式
    @TableField(value = "Ori_Attach_Id") // 新规则：映射数据库原始下划线字段
    @TableId
    private String oriAttachId;

    /**
     * 证书ID（关联dbo.Ori_B_Cert_Edi表）
     */
    @Column(xmlName = "", joinKey = true, joinColumn = "certId") // 数据表关联字段，XML无对应，保留字段名格式
    @TableField(value = "Cert_Id")
    private String certId;

    /**
     * 证书编号（关联dbo.Ori_B_Cert_Edi表，对应XML节点CertNo）
     */
    @Column(xmlName = "CertNo", order = 1) // 精准匹配XML节点CertNo，不重复@Table的xmlName（File）
    @TableField(value = "Cert_No")
    private String certNo;

    /**
     * 证书类型（对应XML节点CertType）
     */
    @Column(xmlName = "CertType", order = 2) // 精准匹配XML节点CertType
    @TableField(value = "Cert_Type")
    private String certType;

    /**
     * 录入企业代码（对应XML节点AplRegNo）
     */
    @Column(xmlName = "AplRegNo", order = 3) // 精准匹配XML节点AplRegNo
    @TableField(value = "Apl_Reg_No")
    private String aplRegNo;

    /**
     * 出口商代码（对应XML节点CiqRegNo）
     */
    @Column(xmlName = "CiqRegNo", order = 4) // 精准匹配XML节点CiqRegNo
    @TableField(value = "Ciq_Reg_No")
    private String ciqRegNo;

    /**
     * 附件名字（对应XML节点FileName）
     */
    @Column(xmlName = "FileName", order = 6) // 精准匹配XML节点FileName
    @TableField(value = "File_Name")
    private String fileName;

    /**
     * 获取附件名字（对应XML节点FileName）
     * 优化：移除文件名中的空格，避免XML解析错误
     */
    public String getFileName() {
        return fileName.replace(" ", "");
    }

    /**
     * 附件类型（对应XML节点FileType）
     */
    @Column(xmlName = "FileType", order = 5) // 精准匹配XML节点FileType
    @TableField(value = "File_Type")
    private String fileType;

    /**
     * 附件格式（对应XML节点DocType）
     */
    @Column(xmlName = "DocType", order = 7) // 精准匹配XML节点DocType
    @TableField(value = "Doc_Type")
    private String docType;

    /**
     * base64编码后的附件（对应XML节点FileContent）
     */
    @Column(xmlName = "FileContent", order = 8) // 精准匹配XML节点FileContent
    @TableField(value = "File_Content")
    private String fileContent;

    /**
     * 是否是缓审（对应XML节点IsDelay）
     */
    @Column(xmlName = "IsDelay", order = 9) // 精准匹配XML节点IsDelay
    @TableField(value = "is_Delay")
    private String isDelay;
}