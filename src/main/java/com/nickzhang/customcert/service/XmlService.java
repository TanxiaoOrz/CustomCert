package com.nickzhang.customcert.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nickzhang.customcert.annotation.Table;
import com.nickzhang.customcert.annotation.TableMapper;
import com.nickzhang.customcert.dto.XmlData;
import com.nickzhang.customcert.dto.XmlProducer;
import com.nickzhang.customcert.mapper.UtilsMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.dom4j.DocumentHelper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

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

    /**
     * 应用上下文
     */
    private final ApplicationContext applicationContext;

     /**
      * 工具mapper
      */
    private final UtilsMapper utilsMapper;

    @DS("pojo")
    public String generateXmlText(String showName, String mainId) {
        log.info("start generate xml, showName: {}, mainId: {}", showName, mainId);
        PoJoEntry poJoEntry = mainPoJoEntryMap.get(showName);
        if (poJoEntry == null) {
            throw new IllegalArgumentException("未找到对应的主POJO类：" + showName);
        }
        XmlProducer xmlProducer = poJoEntry.getXmlProducer();
        XmlData xmlData = xmlProducer.getXmlData(mapperList, mainId);
        log.info("xmlData:\n {}", xmlData);
        String xmlText = xmlProducer.getXmlText(xmlData, utilsMapper);
        log.info("xmlText:\n {}", xmlText);
        return xmlText;
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
            if (belongTo == Object.class) {
                mainPoJoEntryMap.put(table.showName(),new PoJoEntry().setPoJoClass(poJoClass).setTable(table));
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
        log.info("已初始化所有构造器");
        log.info("XmlService初始化完成");
    }

    @Override
    public String toString() {
        return "XmlService{" +
                "mapperList=" + mapperList.toString() + "\n" +
                ", mainPoJoEntryMap=" + mainPoJoEntryMap.toString() + "\n" +
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
                ", xmlProducer=" + xmlProducer + "\n" +
                '}';
    }
}
