package com.nickzhang.customcert.service;

import com.nickzhang.customcert.xml.XmlActionConsequence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2026/1/13 20:54
 * @PackageName: com.nickzhang.customcert.service
 * @ClassName: LoggerServiceTest
 * @Description: 日志服务测试类
 * @Version: 1.0
 */
@SpringBootTest
public class LoggerServiceTest {

    @Autowired
    private LoggerService loggerService;
    @Autowired
    private XmlService xmlService;

    @Test
    public void writeTest() {
        List<XmlActionConsequence> result = xmlService.generateXmlText("海关原产地证书", "bcf0002b7c604f51967b10791c9bd2cf");
        loggerService.writeXmlLog(result);
    }
}
