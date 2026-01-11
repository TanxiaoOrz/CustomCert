package com.nickzhang.customcert.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nickzhang.customcert.annotation.TableMapper;
import com.nickzhang.customcert.pojo.OriginCertificateApl;

/**
 * @Author: 张骏山
 * @Date: 2026/1/11 21:54
 * @PackageName: com.nickzhang.customcert.mapper
 * @ClassName: OriginCertificateAplMapper
 * @Description: 原产地证书申请Mapper接口
 * @Version: 1.0
 */
@TableMapper("OriginCertificateApl")
public interface OriginCertificateAplMapper extends BaseMapper<OriginCertificateApl> {
}
