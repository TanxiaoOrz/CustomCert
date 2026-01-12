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
    public void generateExitInspectionQuarantineOrder() {
        String result = xmlService.generateXmlText("出入境检疫单", "e21133f71ace487a80a8a4f54659adcc");
        assert result.equals("生成成功");
    }

    @Test
    public void generateOriginCertificateGoods() {
        String result = xmlService.generateXmlText("海关原产地证书", "b8fa1e85b9fa491691c5dc98709b36d7");
        assert result.equals("生成成功");
    }

    @Test
    public void generateOriginCertificateGoods2() {
        String result = xmlService.generateXmlText("海关原产地证书", "0b76d190c71845169297d1bdd8b272b8\n");
        assert result.equals("生成成功");
    }


}