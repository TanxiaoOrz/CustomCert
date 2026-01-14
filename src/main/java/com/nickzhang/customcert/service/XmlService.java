package com.nickzhang.customcert.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.annotation.Table;
import com.nickzhang.customcert.annotation.TableMapper;
import com.nickzhang.customcert.mapper.UtilsMapper;
import com.nickzhang.customcert.xml.ClassNoInfo;
import com.nickzhang.customcert.xml.XmlActionConsequence;
import com.nickzhang.customcert.xml.XmlData;
import com.nickzhang.customcert.xml.XmlProducer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author: 张骏山
 * @Date: 2026/1/5 22:18
 * @PackageName: com.nickzhang.customcert.service
 * @ClassName: XmlService
 * @Description: Xml生成 结果读取相关工作业务类
 * @Version: 1.0
 */
@Service
public class XmlService  implements InitializingBean {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(XmlService.class);

    private static final String BASE_POJO_PACKAGE = "com.nickzhang.customcert.pojo";

    private final HashMap<String, BaseMapper<?>> mapperList = new HashMap<>();
    private final HashMap<String, PoJoEntry> mainPoJoEntryMap = new HashMap<>();
    private final HashMap<String, List<SubPoJoEntry>> belongDividedPojoEntryMap = new HashMap<>();

    private final HashMap<String, ClassNoInfo> getIdFromNoMap = new HashMap<>();

    /**
     * 应用上下文
     */
    private final ApplicationContext applicationContext;

     /**
      * 工具mapper
      */
    private final UtilsMapper utilsMapper;

    /**
     * 生成xml文件
     * @param showName 展示名称
     * @param mainId 主表主键值
     * @return 生成的文件名称列表 第一个为生成结果 成功为"生成成功" 失败为"生成失败"
     */
    @DS("pojo")
    public List<XmlActionConsequence> generateXmlText(String showName, String mainId) {
        log.info("start generate xml, showName: {}, mainId: {}", showName, mainId);
        List<XmlActionConsequence> fileNameList = new ArrayList<>();
        // 处理主文件
        {
            PoJoEntry poJoEntry = mainPoJoEntryMap.get(showName);
            if (poJoEntry == null) {
                throw new IllegalArgumentException("未找到对应的主POJO类：" + showName);
            }
            XmlProducer xmlProducer = poJoEntry.getXmlProducer();
            XmlActionConsequence xmlText = generateXmlText(xmlProducer, mainId);
            fileNameList.add(xmlText);
        }

        // 处理从属子文件
        List<SubPoJoEntry> poJoEntries = belongDividedPojoEntryMap.get(showName);
        if (poJoEntries != null) {
            for (SubPoJoEntry entry : poJoEntries) {
                List<String> belongTableIds =
                        utilsMapper.getBelongTableIds(entry.mainTable,entry.belongTable
                                , entry.mainTableMainColumn,entry.mainLinkColumn
                                , entry.belongTableMainColumn,entry.belongLinkColumn
                                , mainId);
                if (belongTableIds.isEmpty()||belongTableIds.contains(null)) {
                    continue;
                }
                for (String belongTableId : belongTableIds) {
                    XmlActionConsequence xmlText = generateXmlText(entry.getXmlProducer(), belongTableId);
                    fileNameList.add(xmlText);
                }
            }
        }
        return fileNameList;
    }

    public String getMainIdFromNo(String showName,String no) {
        return utilsMapper.getIdFromNo(getIdFromNoMap.get(showName),no);
    }


    /**
     * 生成xml文本
     * @param xmlProducer xml生产者
     * @param mainId 主表主键值
     * @return 生成的文件名称
     */
    private XmlActionConsequence generateXmlText(XmlProducer xmlProducer,String mainId) {
        XmlData xmlData = xmlProducer.getXmlData(mapperList, mainId);
        XmlActionConsequence consequence = xmlProducer.getXmlText(xmlData, utilsMapper);
        String typeName = xmlProducer.getTypeName();
        log.info("xmlType=>{}, mainId=>{},fileName=>{}, xmlText:\n {}", typeName, mainId, consequence.getFileName(), consequence.getContext());
//        int insertMainId = xmlLogMapper.insert(
//                new XmlLog().setTypeName(typeName)
//                        .setMainId(mainId)
//                        .setInputFile(consequence.getFileName())
//                        .setInputFileContext(consequence.getContext())
//        );
//        log.info("记录日志insertMainId=>{}", insertMainId);
        return consequence;
    }




    public XmlService(ApplicationContext applicationContext, UtilsMapper utilsMapper) {
        this.applicationContext = applicationContext;
        this.utilsMapper = utilsMapper;
    }


    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void afterPropertiesSet() {
        log.info("开始初始化XmlService");
        Map<String, Object> mappers = applicationContext.getBeansWithAnnotation(TableMapper.class);
        HashMap<String, List<Class<?>>> pojoListMap = new HashMap<>();
        mappers.forEach((key, value) -> {
            Class<?>[] interfaces = value.getClass().getInterfaces();
            Class<?> mapperClass = Arrays.stream(interfaces).filter(inter -> inter.isAnnotationPresent(TableMapper.class)).findFirst().get();
            TableMapper tableMapper = mapperClass.getAnnotation(TableMapper.class);
            String pojoClassName = tableMapper.value();
            mapperList.put(BASE_POJO_PACKAGE + "." + pojoClassName,   (BaseMapper<?>)  value);
            Class<?> poJoClass;
            try {
                poJoClass = Class.forName(BASE_POJO_PACKAGE + "." + pojoClassName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("未找到对应的POJO类：" + pojoClassName);
            }
            Table table = poJoClass.getAnnotation(Table.class);
            Class<?> belongTo = table.belongTo();
            if (belongTo == Object.class ) {
                mainPoJoEntryMap.put(table.showName(),new PoJoEntry().setPoJoClass(poJoClass).setTable(table));
                // 记录编号字段
                getClassNoinfo(poJoClass, table);
            } else if (table.dividedFile()) { // 新增从属独立文件逻辑
                SubPoJoEntry poJoEntry = new SubPoJoEntry();
                poJoEntry.setPoJoClass(poJoClass).setTable(table);

                List<SubPoJoEntry> poJoEntries = belongDividedPojoEntryMap.computeIfAbsent(belongTo.getAnnotation(Table.class).showName(), k -> new ArrayList<>());
                poJoEntries.add(poJoEntry);
                mainPoJoEntryMap.put(table.showName(),poJoEntry);

                // 组装链接内容
                String mainTable = belongTo.getAnnotation(TableName.class).value();
                String subTable = poJoClass.getAnnotation(TableName.class).value();
                String mainTableMainColumn = Arrays.stream(belongTo.getDeclaredFields()).filter(field -> field.isAnnotationPresent(TableId.class)).findFirst().get().getAnnotation(TableField.class).value();
                String subTableMainColumn = Arrays.stream(poJoClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(TableId.class)).findFirst().get().getAnnotation(TableField.class).value();
                Field subJoinColumnField = Arrays.stream(poJoClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Column.class)).filter(field -> field.getAnnotation(Column.class).joinKey()).findFirst().get();
                String mainJoinColumn;
                String subJoinColumn = subJoinColumnField.getAnnotation(TableField.class).value();
                String mainJoinColumnField = subJoinColumnField.getAnnotation(Column.class).joinColumn();
                try {
                     mainJoinColumn = belongTo.getDeclaredField(mainJoinColumnField).getAnnotation(TableField.class).value();
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException("sub类 %s 关联字段 %s 查找 主类 %s 关联字段 %s 未找到对应的关联字段：%s"
                            .formatted(poJoClass.getSimpleName(), subJoinColumn, belongTo.getSimpleName(), mainJoinColumnField, mainJoinColumnField)
                    ,e);
                }


                poJoEntry.setMainTable(mainTable)
                        .setMainTableMainColumn(mainTableMainColumn)
                        .setMainLinkColumn(mainJoinColumn)
                        .setBelongTable(subTable)
                        .setBelongLinkColumn(subJoinColumn)
                        .setBelongTableMainColumn(subTableMainColumn);

                getClassNoinfo(poJoClass, table);

            } else {
                List<Class<?>> pojoClasses = pojoListMap.computeIfAbsent(belongTo.getName(), k -> new ArrayList<>());
                pojoClasses.add(poJoClass);
            }
        });
        log.info("已初始化{}个Mapper", mapperList.size());
        log.info("已初始化{}个主POJO类", mainPoJoEntryMap.size());
        log.info("已初始化{}个从POJO类", pojoListMap.size());
        log.info("开始初始化构造器");
        mainPoJoEntryMap.forEach((key, entry) -> {
            entry.setXmlProducer(new XmlProducer().initialize(entry.getPoJoClass(), pojoListMap));
            log.info("已初始化{}的构造器", entry.getPoJoClass().getSimpleName());
        });
//        belongDividedPojoEntryMap.forEach((key, entries) -> entries.forEach(entry -> {
//            entry.setXmlProducer(new XmlProducer().initialize(entry.getPoJoClass(), pojoListMap));
//            log.info("已初始化{}的构造器", entry.getPoJoClass().getSimpleName());
//        }));
        log.info("已初始化所有构造器");
        log.info("XmlService初始化完成");
    }

    private void getClassNoinfo(Class<?> poJoClass, Table table) {
        try {
            Field noField = Arrays.stream(poJoClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Column.class)).filter(field -> field.getAnnotation(Column.class).isNo()).findFirst().get();
            Field mainField = Arrays.stream(poJoClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(TableId.class)).findFirst().get();
            getIdFromNoMap.put(table.showName(),
                    new ClassNoInfo().setShowName(table.showName())
                            .setMainColumn(mainField.getAnnotation(TableField.class).value())
                            .setNoColumn(noField.getAnnotation(TableField.class).value())
                            .setMainTable(poJoClass.getAnnotation(TableName.class).value())
            );
        } catch (Exception e) {
            log.error("获取类{}的证书编号字段失败",poJoClass.getSimpleName(),e);
        }

    }

    @Override
    public String toString() {
        return "XmlService{" +
                "mapperList=" + mapperList + "\n" +
                ", mainPoJoEntryMap=" + mainPoJoEntryMap + "\n" +
                ", belongDividedPojoEntryMap=" + belongDividedPojoEntryMap + "\n" +
                '}';
    }
}

@Data
@Accessors(chain = true)
class PoJoEntry {
    Class<?> poJoClass;
    XmlProducer xmlProducer;
    Table table;

    @Override
    public String toString() {
        return "PoJoEntry{" +
                "poJoClass=" + poJoClass + "\n" +
                ", xmlProducer=" + (xmlProducer==null?"null":"exist") + "\n" +
                '}';
    }
}

@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
class SubPoJoEntry extends PoJoEntry {

    String mainTable;
    String mainTableMainColumn;
    String mainLinkColumn;
    String belongTable;
    String belongLinkColumn;
    String belongTableMainColumn;

    @Override
    public String toString() {
        return "PoJoEntry{" +
                "poJoClass=" + poJoClass + "\n" +
                ", xmlProducer=" + (xmlProducer==null?"null":"exist") + "\n" +
                ", mainTable=" + mainTable + "\n" +
                ", mainTableMainColumn=" + mainTableMainColumn + "\n" +
                ", mainLinkColumn=" + mainLinkColumn + "\n" +
                ", belongTable=" + belongTable + "\n" +
                ", belongLinkColumn=" + belongLinkColumn + "\n" +
                ", belongTableMainColumn=" + belongTableMainColumn + "\n" +
                '}';
    }
}


