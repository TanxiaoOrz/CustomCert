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
    /**
     * 关联主表列名
     */
    private final String  mainTableColumn;
    /**
     * 获取上级数据依据值方法
     */
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

    /**
     * 获取主节点数据
     * @param mappers 所有mapper
     * @param mainId 主节点id
     * @return 主节点数据
     */
    public XmlData getMainData(Map<String, BaseMapper<?>> mappers, String mainId) {
        if (mainTableColumn != null) {
            throw new RuntimeException("明细节点不得直接调用");
        }
        BaseMapper<?> mapper = mappers.get(currentGetterName);
        if (mapper == null) {
            throw new RuntimeException("未找到对应的mapper");
        }
        Object mainData = mapper.selectById(mainId);
        XmlData xmlData = new XmlData(mainData);
        childGetters.values().forEach(
                childGetter -> xmlData.putChildren(childGetter.currentGetterName, childGetter.getDetailData(mappers, mainData))
        );
        return xmlData;
    }

    /**
     * 获取明细节点数据
     * @param mappers 所有mapper
     * @param belongTo 所属主数据
     * @return 明细节点数据
     */
    @SuppressWarnings("unchecked")
    private List<XmlData> getDetailData(Map<String, BaseMapper<?>> mappers, Object belongTo) {
        if (mainTableColumn == null) {
            throw new RuntimeException("主节点不得调用明细节点");
        }
        BaseMapper<?> baseMapper = mappers.get(currentGetterName);
        if (baseMapper == null) {
            throw new RuntimeException("未找到对应的mapper");
        }
        BaseMapper<P> mapper = (BaseMapper<P>) baseMapper;
        List<P> ps;
        try {
            ps = mapper.selectList(new QueryWrapper<P>().eq(mainTableColumn, getMainIdMethod.invoke(belongTo)));
        } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            throw new RuntimeException("当前节点 " + currentGetterName + " 调用子节点索引,抓换父节点id: method=>" + getMainIdMethod.getName() + ",belongTo=>" + belongTo.getClass().getName() + "时出错", e);
        }
        return ps.stream().map(p -> {
            XmlData xmlData = new XmlData(p);
            childGetters.values().forEach(
                    childGetter -> {
                            xmlData.putChildren(childGetter.currentGetterName, childGetter.getDetailData(mappers, p));
                    }
            );
            return xmlData;
        }).toList();
    }

}
