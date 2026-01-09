package com.nickzhang.customcert.service;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.nickzhang.customcert.annotation.Table;
import com.nickzhang.customcert.annotation.TableMapper;
import com.nickzhang.customcert.dto.XmlProducer;
import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final HashMap<String, Mapper<?>> mapperList = new HashMap<>();
    private final HashMap<String, PoJoEntry> mainPoJoEntryMap = new HashMap<>();


    private final ApplicationContext applicationContext;

    public XmlService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    @Override
    public void afterPropertiesSet() {
        log.info("开始初始化XmlService");
        Map<String, Object> mappers = applicationContext.getBeansWithAnnotation(TableMapper.class);
        HashMap<String, List<Class<?>>> pojoListMap = new HashMap<>();
        mappers.forEach((key, value) -> {
            String pojoClassName = value.getClass().getAnnotation(TableMapper.class).value();
            mapperList.put(pojoClassName, (Mapper<?>) value);
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
}

@Data
@Accessors(chain = true)
class PoJoEntry {
    Class<?> poJoClass;
    XmlProducer xmlProducer;
    Table table;
}
