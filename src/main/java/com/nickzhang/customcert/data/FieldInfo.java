package com.nickzhang.customcert.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;

/**
 * @Author: 张骏山
 * @Date: 2026/1/14 15:29
 * @PackageName: com.nickzhang.customcert.data
 * @ClassName: FieldInfo
 * @Description: 字段信息类
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
class FieldInfo {
    private  String name;
    private  String showName;
    private Method getterMethod;
    private ColumnType fieldType;
    private boolean canSearch;
}
