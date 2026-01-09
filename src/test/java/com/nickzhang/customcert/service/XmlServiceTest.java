package com.nickzhang.customcert.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: 张骏山
 * @Date: 2026/1/10 0:59
 * @PackageName: com.nickzhang.customcert.service
 * @ClassName: XmlServiceTest
 * @Description: XmlService测试类
 * @Version: 1.0
 */
@SpringBootTest
public class XmlServiceTest {

    @Autowired
    private XmlService xmlService;

//    @Test
//    public void init() {
//        System.out.println("xmlService.toString() = " + xmlService.toString());
//    }

    @Test
    public void generateXml() {
        String result = xmlService.generateXmlText("出入境检疫单", "e21133f71ace487a80a8a4f54659adcc");
        System.out.println("result = " + result);
    }

}