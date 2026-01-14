package com.nickzhang.customcert.controller;

import com.nickzhang.customcert.service.LoggerService;
import com.nickzhang.customcert.service.XmlService;
import com.nickzhang.customcert.xml.XmlActionConsequence;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    org.slf4j.Logger logger = LoggerFactory.getLogger(CertController.class);

    /**
     * 生成凭证
     */
    @GetMapping("/generate")
    @Operation(summary = "生成凭证", description = "根据单据编号生成凭证")
    public String generateCert(
            @Parameter(description = "单据名称", required = true, example = "海关原产地证书 / 出入境检疫单")
            @RequestParam String showName,
            @Parameter(description = "单据编号", required = true, example = "E26MA1YPJ9Y30002")
            @RequestParam String no){
        String mainId = xmlService.getMainIdFromNo(showName,no);
        logger.info("根据{}编号{}查询到主表主键值{}",showName,no,mainId);
        List<XmlActionConsequence> xmlActionConsequences = xmlService.generateXmlText(showName, mainId);
        loggerService.writeXmlLog(xmlActionConsequences);
        return "生成成功";
    }

}
