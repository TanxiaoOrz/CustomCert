package com.nickzhang.customcert.dto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 张骏山
 * @Date: 2026/1/9 17:28
 * @PackageName: com.nickzhang.customcert.dto
 * @ClassName: XmlDataGetter
 * @Description: data数据获取规则索引类
 * @Version: 1.0
 */
public class XmlDataGetter<P> {

    private final HashMap<String, XmlDataGetter<?>> childGetters = new HashMap<>();
    private final String currentGetterName;
    private final String  mainTableColumn;
    private Method getMainIdMethod;

    /**
     * 主节点构造函数
     * @param currentGetterName 当前节点索引名称
     */
    public XmlDataGetter (String currentGetterName) {
        this.currentGetterName = currentGetterName;
        this.mainTableColumn = null;
    }

    /**
     * 明细节点构造函数
     * @param currentGetterName 当前节点索引名称
     * @param mainTableColumn 主表关联列名
     */
    public XmlDataGetter (String currentGetterName, String mainTableColumn, Method getMainIdMethod) {
        this.currentGetterName = currentGetterName;
        this.mainTableColumn = mainTableColumn;
        this.getMainIdMethod = getMainIdMethod;
    }

    /**
     * 添加子节点索引
     * @param getterName 子节点索引名称
     * @param xmlDataGetter 子节点索引
     */
    public void putChildGetter(String getterName, XmlDataGetter<?> xmlDataGetter) {
        childGetters.put(getterName, xmlDataGetter);
    }

    public XmlData getData(Map<String, BaseMapper<?>> mappers, String mainId) {
        if (mainTableColumn != null) {
            throw new RuntimeException("明细节点不得直接调用");
        }
        BaseMapper<?> mapper = mappers.get(currentGetterName);
        if (mapper == null) {
            throw new RuntimeException("未找到对应的mapper");
        }
        XmlData xmlData = new XmlData(mapper.selectById(mainId));
        childGetters.values().forEach(
                childGetter -> xmlData.putChildren(childGetter.currentGetterName, childGetter.getDetailData(mappers, mainId))
        );
        return xmlData;
    }

    @SuppressWarnings("unchecked")
    private List<XmlData> getDetailData(Map<String, BaseMapper<?>> mappers, String mainTableId) {
        if (mainTableColumn == null) {
            throw new RuntimeException("主节点不得调用明细节点");
        }
        BaseMapper<?> baseMapper = mappers.get(currentGetterName);
        if (baseMapper == null) {
            throw new RuntimeException("未找到对应的mapper");
        }
        BaseMapper<P> mapper = (BaseMapper<P>) baseMapper;
        List<P> ps = mapper.selectList(new QueryWrapper<P>().eq(mainTableColumn, mainTableId));
        return ps.stream().map(p -> {
            XmlData xmlData = new XmlData(p);
            childGetters.values().forEach(
                    childGetter -> {
                        try {
                            xmlData.putChildren(childGetter.currentGetterName, childGetter.getDetailData(mappers, (String) getMainIdMethod.invoke(p)));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("调用子节点索引,抓换父节点id" + getMainIdMethod.getName() + "时出错", e);
                        }
                    }
            );
            return xmlData;
        }).toList();
    }

}
