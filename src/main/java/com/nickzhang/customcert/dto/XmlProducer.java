package com.nickzhang.customcert.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2026/1/6 13:09
 * @PackageName: com.nickzhang.customcert.dto
 * @ClassName: XmlProducer
 * @Description: TODO
 * @Version: 1.0
 */
public class XmlProducer {
    private final List<XmlProducerNode> nodes = new ArrayList<>();

    /**
     * xml 前缀根节点名称
     */
    private final String xmlRootTitle;
    /**
     * xml 前缀根节点属性
     */
    private final HashMap<String, String> xmlRootAttributes;

    public XmlProducer(String xmlRootTitle, HashMap<String, String> xmlRootAttributes) {
        this.xmlRootTitle = xmlRootTitle;
        this.xmlRootAttributes = xmlRootAttributes;
    }

    public XmlProducer() {
        this.xmlRootTitle = "";
        this.xmlRootAttributes = new HashMap<>();
        xmlRootAttributes.put("xsi:schemaLocation", "http://www.chinaport.gov.cn/ciq DecCiqMessage.xsd");
        xmlRootAttributes.put("xmlns:ds", "http://www.chinaport.gov.cn/ciq");
        xmlRootAttributes.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
    }

    public XmlProducer initialize(Class<?> tableClass, Class<?>[] detailClasses) {

        return this;
    }




}
