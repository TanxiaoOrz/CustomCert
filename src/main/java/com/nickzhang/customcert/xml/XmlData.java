package com.nickzhang.customcert.xml;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2026/1/9 14:37
 * @PackageName: com.nickzhang.customcert.dto
 * @ClassName: XmlData
 * @Description: xml数据存储结构类
 * @Version: 1.0
 */
public class XmlData {
    /**
     * 当前xml数据
     */
    @Getter
    private final Object currentData;

    /**
     * 子节点
     */
    private HashMap<String, List<XmlData>> children;


    public XmlData(Object currentData) {
        this.currentData = currentData;
    }

    public List<XmlData> getChildren(String className) {
        return children.get(className);
    }

    public void putChildren(String className, List<XmlData> xmlDatas) {
        if (children == null) {
            children = new HashMap<>();
        }
        this.children.put(className, xmlDatas);
    }
}
