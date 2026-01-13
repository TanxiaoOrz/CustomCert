package com.nickzhang.customcert.service;

import com.nickzhang.customcert.mapper.XmlLogMapper;
import com.nickzhang.customcert.po.XmlLog;
import com.nickzhang.customcert.xml.XmlActionConsequence;
import org.apache.ibatis.executor.BatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2026/1/5 22:20
 * @PackageName: com.nickzhang.customcert.service
 * @ClassName: LoggerService
 * @Description: 日志业务读取操作相关类
 * @Version: 1.0
 */
@Service
public class LoggerService {

    private final Logger logger = LoggerFactory.getLogger(LoggerService.class);

    private XmlLogMapper xmlLogMapper;

    /**
     * 写入XML日志
     * @param xmlActionConsequences XML操作结果列表
     * @return 写入的XML日志ID列表
     */
    public List<Long> writeXmlLog(List<XmlActionConsequence> xmlActionConsequences) {

        List<XmlLog> xmlLogs = xmlActionConsequences.stream().map(xmlActionConsequence -> new XmlLog()
                .setTypeName(xmlActionConsequence.getFileName())
                .setMainId(xmlActionConsequence.getMainId())
                .setInputFile(xmlActionConsequence.getFileName())
                .setInputFileContext(xmlActionConsequence.getContext())
                .setStatus(xmlActionConsequence.isSuccess() ? "成功" : "失败")
                .setAnswerFile(xmlActionConsequence.getFileName())
                .setAnswerFileContext(xmlActionConsequence.getContext())).toList();


        xmlLogMapper.insert(xmlLogs);
        xmlLogs.forEach(xmlLog -> logger.info("插入日志数据{}", xmlLog));
        return xmlLogs.stream().map(XmlLog::getId).collect(Collectors.toList());
    }

    public LoggerService(XmlLogMapper xmlLogMapper) {
        this.xmlLogMapper = xmlLogMapper;
    }
}
