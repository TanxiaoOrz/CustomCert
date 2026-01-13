package com.nickzhang.customcert.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2026/1/13 16:51
 * @PackageName: com.nickzhang.customcert.po
 * @ClassName: XmlLogs
 * @Description: xml对象实体类
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
@TableName(value = "Xml_Log")
public class XmlLog {

    /**
     * 主键ID
     * 优化1：遵循Java驼峰命名，改为小写id
     * 优化2：主键类型改为Long（对应数据库BIGINT自增，最常用）
     * 若数据库主键是VARCHAR类型，可改为String + IdType.ASSIGN_UUID/IdType.INPUT
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 类型展示名称
     */
    private String typeName;



    /**
     * 主ID
     */
    private String mainId;

    /**
     * 输入文件名称
     */
    private String inputFile;

    /**
     * 输入文件内容
     */
    private String inputFileContext;

    /**
     * 输入时间
     * 优化：添加日期格式化注解，解决映射和序列化问题
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inputDateTime;

    /**
     * 处理状态
     */
    private String status;

    /**
     * 结果文件名称
     */
    private String answerFile;

    /**
     * 结果文件内容
     */
    private String answerFileContext;
     /**
     * 结果时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date answerDateTime;
}
