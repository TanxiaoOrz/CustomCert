package com.nickzhang.customcert.xml;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: 张骏山
 * @Date: 2026/1/14 16:49
 * @PackageName: com.nickzhang.customcert.xml
 * @ClassName: ClassNoInfo
 * @Description: 类编号信息
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class ClassNoInfo {
    String showName;
    String noColumn;
    String mainColumn;
    String mainTable;
}