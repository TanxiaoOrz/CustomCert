package com.nickzhang.customcert.xml;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2026/1/13 16:44
 * @PackageName: com.nickzhang.customcert.xml
 * @ClassName: XmlActionConsequence
 * @Description: xml操作结果类
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class XmlActionConsequence {
    private String typeName;
    private String filePath;
    private String fileName;
    private String context;
    private String mainId;
    private Date dateTime;
    private boolean success;
}
