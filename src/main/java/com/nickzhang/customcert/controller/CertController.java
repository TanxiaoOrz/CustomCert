package com.nickzhang.customcert.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nickzhang.customcert.log.XmlLog;
import com.nickzhang.customcert.mapper.ExitInspectionQuarantineOrderMapper;
import com.nickzhang.customcert.mapper.OriginCertificateMapper;
import com.nickzhang.customcert.mapper.XmlLogMapper;
import com.nickzhang.customcert.pojo.ExitInspectionQuarantineOrder;
import com.nickzhang.customcert.pojo.OriginCertificate;
import com.nickzhang.customcert.service.LoggerService;
import com.nickzhang.customcert.service.XmlService;
import com.nickzhang.customcert.xml.XmlActionConsequence;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2026/1/5 22:31
 * @PackageName: com.nickzhang.customcert.controller
 * @ClassName: CertController
 * @Description: 凭证相关操作接口类
 * @Version: 1.0
 */
@RestController
@RequestMapping("/cert")
@Tag(name = "单据管理", description = "单据相关操作接口")
public class CertController {

    @Autowired
    XmlService xmlService;
    @Autowired
    LoggerService loggerService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    OriginCertificateMapper originCertificateMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ExitInspectionQuarantineOrderMapper exitInspectionQuarantineOrderMapper;

    @Autowired
    XmlLogMapper xmlLogMapper;

    org.slf4j.Logger logger = LoggerFactory.getLogger(CertController.class);

    /**
     * 生成凭证
     */
    @PostMapping("/origin/generate")
    @Operation(summary = "生成海关原产地证书", description = "根据单据编号生成海关原产地证书")
    public String generateOriginCertificate(
            @Parameter(description = "单据编号", required = true, example = "E26MA1YPJ9Y30002")
            @RequestParam String no){
        String mainId = xmlService.getMainIdFromNo("海关原产地证书",no);
        logger.info("根据{}编号{}查询到主表主键值{}","海关原产地证书",no,mainId);
        List<XmlActionConsequence> xmlActionConsequences = xmlService.generateXmlText("海关原产地证书", mainId);
        loggerService.writeXmlLog(xmlActionConsequences);
        return "生成成功";
    }

    @GetMapping("/origin/record")
    @Operation(summary = "查询海关原产地证书生成记录", description = "根据起止时间查询海关原产地证书生成记录")
    public List<XmlLog> queryOriginCertificateGenerateRecord(
            @Parameter(description = "开始时间", required = true, example = "2026-01-01")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDateTime,
            @Parameter(description = "结束时间", required = true, example = "2026-01-31")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDateTime,
            @Parameter(description = "是否隐藏输入文件", required = true, example = "true")
            @RequestParam boolean hideInputFile,
            @Parameter(description = "是否隐藏输出文件", required = true, example = "true")
            @RequestParam boolean hideOutputFile

    ){
        loggerService.checkReturn("海关原产地证书");
        List<XmlLog> logList = xmlLogMapper.selectList(new LambdaQueryWrapper<XmlLog>()
                .eq(XmlLog::getTypeName, "海关原产地证书")
                .ge(XmlLog::getInputDateTime, startDateTime)
                .le(XmlLog::getInputDateTime, endDateTime)
                .orderByDesc(XmlLog::getInputDateTime)
        );
        logList.forEach(xmlLog -> xmlLog.setMainId(xmlService.getNoFromId("海关原产地证书",xmlLog.getMainId())));
        if(hideInputFile) logList.forEach(xmlLog -> xmlLog.setInputFileContext(""));
        if(hideOutputFile) logList.forEach(xmlLog -> xmlLog.setAnswerFileContext(""));
        return logList;
    }


    /**
     * 生成凭证
     */
    @PostMapping("/exit/generate")
    @Operation(summary = "生成出入境检疫单", description = "根据单据编号生成出入境检疫单")
    public String generateExitInspectionQuarantineOrder(
            @Parameter(description = "单据编号", required = true, example = "E26MA1YPJ9Y30002")
            @RequestParam String no){
        String mainId = xmlService.getMainIdFromNo("出入境检疫单",no);
        logger.info("根据{}编号{}查询到主表主键值{}","出入境检疫单",no,mainId);
        List<XmlActionConsequence> xmlActionConsequences = xmlService.generateXmlText("出入境检疫单", mainId);
        loggerService.writeXmlLog(xmlActionConsequences);
        return "生成成功";
    }

    @GetMapping("/exit/record")
    @Operation(summary = "查询出口检测检疫订单生成记录", description = "根据起止时间查询出口检测检疫订单生成记录")
    public List<XmlLog> queryExitInspectionQuarantineOrderRecord(
            @Parameter(description = "开始时间", required = true, example = "2026-01-01")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDateTime,
            @Parameter(description = "结束时间", required = true, example = "2026-01-31")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDateTime,
            @Parameter(description = "是否隐藏输入文件", required = true, example = "true")
            @RequestParam boolean hideInputFile,
            @Parameter(description = "是否隐藏输出文件", required = true, example = "true")
            @RequestParam boolean hideOutputFile
    ){
        loggerService.checkReturn("出入境检疫单");
        List<XmlLog> logList = xmlLogMapper.selectList(new LambdaQueryWrapper<XmlLog>()
                .eq(XmlLog::getTypeName, "出入境检疫单")
                .ge(XmlLog::getInputDateTime, startDateTime)
                .le(XmlLog::getInputDateTime, endDateTime)
                .orderByDesc(XmlLog::getInputDateTime)
        );
        logList.forEach(xmlLog -> xmlLog.setMainId(xmlService.getNoFromId("出入境检疫单",xmlLog.getMainId())));
        if(hideInputFile) logList.forEach(xmlLog -> xmlLog.setInputFileContext(""));
        if(hideOutputFile) logList.forEach(xmlLog -> xmlLog.setAnswerFileContext(""));
        return logList;
    }


}
