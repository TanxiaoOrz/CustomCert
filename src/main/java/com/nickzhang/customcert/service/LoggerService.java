package com.nickzhang.customcert.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nickzhang.customcert.log.XmlLog;
import com.nickzhang.customcert.log.XmlLogStatus;
import com.nickzhang.customcert.mapper.XmlLogMapper;
import com.nickzhang.customcert.xml.XmlActionConsequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@PropertySource("classpath:front-end-processor.properties")
public class LoggerService {

    private final Logger logger = LoggerFactory.getLogger(LoggerService.class);

    private final XmlLogMapper xmlLogMapper;

    @Value("${front-end-processor.file-path.consequence}")
    private String consequencePath;
    @Value("${front-end-processor.answer-prefix.success}")
    private String successPrefix;
    @Value("${front-end-processor.answer-prefix.failure}")
    private String failurePrefix;

    /**
     * 写入XML日志
     * @param xmlActionConsequences XML操作结果列表
     * @return 写入的XML日志ID列表
     */
    public List<Long> writeXmlLog(List<XmlActionConsequence> xmlActionConsequences) {

        List<XmlLog> xmlLogs = xmlActionConsequences.stream().map(xmlActionConsequence -> new XmlLog()
                .setTypeName(xmlActionConsequence.getTypeName())
                .setMainId(xmlActionConsequence.getMainId())
                .setInputFile(xmlActionConsequence.getFileName())
                .setInputFileContext(xmlActionConsequence.getContext())
                .setInputDateTime(new Date())
                .setStatus(XmlLogStatus.wait.toString())
                .setFilePathRoot(xmlActionConsequence.getFilePath())
        ).toList();


        xmlLogMapper.insert(xmlLogs);
        xmlLogs.forEach(xmlLog -> logger.info("插入日志数据{}", xmlLog));
        return xmlLogs.stream().map(XmlLog::getId).collect(Collectors.toList());
    }

    /**
     * 获取xml操作结果
     * @param showName 操作对象名称
     * @return 结果数量 {成功, 失败, 待查询}
     */
    public List<Long> checkReturn(String showName) {
        // 按处理状态筛选 待查询
        if (showName == null ||showName.isEmpty()) {
            Long[] rts = new Long[]{0L, 0L, 0L};
            xmlLogMapper.selectList(new LambdaQueryWrapper<XmlLog>()
                    .eq(XmlLog::getStatus, XmlLogStatus.wait.name())
                    .groupBy(XmlLog::getTypeName)
            ).forEach(xmlLog -> {
                List<Long> longs = checkReturn(xmlLog.getTypeName());
                rts[0] += longs.get(0);
                rts[1] += longs.get(1);
                rts[2] += longs.get(2);
            });
            return new ArrayList<>(Arrays.asList(rts));
        }
        List<XmlLog> xmlLogs;

        // 按操作对象名称筛选 空值代
        xmlLogs = xmlLogMapper.selectList(new LambdaQueryWrapper<XmlLog>()
                .eq(XmlLog::getStatus, XmlLogStatus.wait.name())
                .eq(XmlLog::getTypeName, showName)
                .orderByAsc(XmlLog::getInputDateTime));
        logger.info("待查询结果数据{}", xmlLogs);

        if (xmlLogs.isEmpty()) {
            return new ArrayList<>(Arrays.asList(0L, 0L, 0L));
        }

        // 从文件系统加载所有结果文件
        List<Resource> answerFiles = getAnswerFileNames(xmlLogs.get(0).getFilePathRoot());
        Long[] consequence = new Long[]{0L, 0L, 0L};

        xmlLogs.forEach(xmlLog -> {
            switch (getXmlLogStatus(xmlLog, answerFiles)) {
                case success -> consequence[0]++;
                case fail -> consequence[1]++;
                case wait -> consequence[2]++;
            }
        });
        logger.info("查询结果数据成功{}，失败{}，待查询{}", consequence[0], consequence[1], consequence[2]);
        xmlLogMapper.insertOrUpdate(xmlLogs);

        return new ArrayList<>(Arrays.asList(consequence));
    }

    private XmlLogStatus getXmlLogStatus(XmlLog xmlLog, List<Resource> answerFiles) {
        String fileName = xmlLog.getInputFile().replace(".xml", "");
        String excludePatternStr = Pattern.quote(fileName) + "_\\d+_";
        Pattern excludePattern = Pattern.compile(excludePatternStr);
        Optional<Resource> optional = answerFiles.stream()
                .filter(resource -> resource.getFilename().contains(fileName))
                .filter(resource -> !excludePattern.matcher(resource.getFilename()).find())
                .findFirst();
        if (optional.isEmpty()) {
            logger.warn("待查询结果数据{}", xmlLog);
            return XmlLogStatus.wait;
        }

        Resource answerFile = optional.get();
        String fileContent;
        try {
            fileContent = new String(answerFile.getInputStream().readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("读取操作结果文件失败", e);
        }
        if (answerFile.getFilename().startsWith(successPrefix)) {
            xmlLog.setAnswerFileContext(fileContent)
                    .setAnswerFile(answerFile.getFilename())
                    .setStatus(XmlLogStatus.success.toString())
                    .setAnswerDateTime(extractTimeStampFromFileName(answerFile.getFilename()));
            logger.info("成功结果数据{}", xmlLog);
            return XmlLogStatus.success;
        } else if (answerFile.getFilename().startsWith(failurePrefix)) {
            xmlLog.setAnswerFileContext(fileContent)
                    .setAnswerFile(answerFile.getFilename())
                    .setStatus(XmlLogStatus.fail.toString())
                    .setAnswerDateTime(extractTimeStampFromFileName(answerFile.getFilename()));
            logger.info("失败结果数据{}", xmlLog);
            return XmlLogStatus.fail;
        }
        logger.error("文件名格式不符，未提取到有效状态：{}", answerFile.getFilename());
        return XmlLogStatus.wait;
//        throw new RuntimeException("文件名格式不符，未提取到有效状态：" + answerFile.getFilename());
    }


    /**
     * 从完整文件名中提取时间戳
     * @param fullFileName 完整文件名（如prefix_filename_20250511111111000.xml）
     * @return 提取的时间戳（YYYYMMDDhhmmssfff），提取失败返回null
     */
    public static Date extractTimeStampFromFileName(String fullFileName) {
        final Pattern TIME_STAMP_PATTERN = Pattern.compile("_(\\d{17})\\.xml$");
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 1. 判空校验
        if (fullFileName == null || fullFileName.trim().isEmpty()) {
            System.err.println("文件名不能为空");
            return null;
        }

        // 2. 匹配正则表达式
        Matcher matcher = TIME_STAMP_PATTERN.matcher(fullFileName);
        if (matcher.find()) {
            // 3. 提取分组中的17位数字（时间戳）
            String timeStamp = matcher.group(1);
            try {
                return sdf.parse(timeStamp);
            } catch (ParseException e) {
                throw new IllegalArgumentException("时间戳格式错误：" + timeStamp, e);
            }
        } else {
            throw new IllegalArgumentException("文件名格式不符，未提取到有效时间戳：" + fullFileName);
        }
    }

    private List<Resource> getAnswerFileNames(String filePathRoot) {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

        Resource[] resources;
        // 3. 解析目录，获取所有资源对象
        try {
            resources = resourceResolver.getResources("file:"+filePathRoot + consequencePath + "*.xml");
        } catch (IOException e) {
            throw new RuntimeException("获取操作结果文件失败", e);
        }

        return Arrays.stream(resources).filter(Resource::isFile).toList();
    }

    public LoggerService(XmlLogMapper xmlLogMapper) {
        this.xmlLogMapper = xmlLogMapper;
    }
}
