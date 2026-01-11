package com.nickzhang.customcert.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nickzhang.customcert.annotation.TableMapper;
import com.nickzhang.customcert.pojo.OriginCertificateFile;

/**
 * @Author: 张骏山
 * @Date: 2026/1/11 21:54
 * @PackageName: com.nickzhang.customcert.mapper
 * @ClassName: OriginCertificateFileMapper
 * @Description: 原产地附件Mapper接口
 * @Version: 1.0
 */
@TableMapper("OriginCertificateFile")
public interface OriginCertificateFileMapper extends BaseMapper<OriginCertificateFile> {
}
