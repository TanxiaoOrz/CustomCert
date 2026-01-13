package com.nickzhang.customcert.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import lombok.Data;

/**
 * @Author: 张骏山
 * @Date: 2026/1/13 23:27
 * @PackageName: com.nickzhang.customcert.pojo
 * @ClassName: OriginCertificateNonPartyCorp
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@TableName(value = "Ori_B_Nonparty_Corp") // 新规则：映射数据库完整原始表名
@Table(xmlName = "NonpartyCorpList/NonpartyCorp",
        belongTo = OriginCertificateApl.class,
        order = 65
)
public class OriginCertificateNonPartyCorp {

    @TableId
    @TableField(value = "Nonparty_Id")
    private String nonpartyId;

    /**
     * 证书ID（关联dbo.Ori_B_Cert_Apl表）
     */
    @Column(joinKey = true, joinColumn = "aplId") // 保留原始配置，不新增order（无对应XML节点）
    @TableField(value = "Apl_Id")
    private String aplId;

    /**
     * 排序号
     */
    @Column(xmlName = "SortNo", order = 1) // 匹配XML节点，排序第1
    @TableField(value = "Sort_No")
    private String sortNo;

    /**
     * 企业名称
     */
    @Column(xmlName = "EntName", order = 2) // 匹配XML节点，排序第2
    @TableField(value = "Ent_Name")
    private String entName;

    /**
     * 企业地址
     */
    @Column(xmlName = "EntAddr", order = 3) // 匹配XML节点，排序第3
    @TableField(value = "Ent_Addr")
    private String entAddr;

    /**
     * 国家/地区代码
     */
    @Column(xmlName = "EntCountryCode", order = 4) // 匹配XML节点，排序第4
    @TableField(value = "Country_Code")
    private String entCountryCode;

    /**
     * 国家/地区名称
     */
    @Column(xmlName = "EntCountryName", order = 5) // 匹配XML节点，排序第5
    @TableField(value = "Country_Name")
    private String entCountryName;




}
