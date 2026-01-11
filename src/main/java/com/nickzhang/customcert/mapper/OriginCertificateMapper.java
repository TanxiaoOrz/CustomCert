package com.nickzhang.customcert.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nickzhang.customcert.annotation.TableMapper;
import com.nickzhang.customcert.pojo.OriginCertificate;

/**
 * @Author: 张骏山
 * @Date: 2026/1/11 21:53
 * @PackageName: com.nickzhang.customcert.mapper
 * @ClassName: OriginCertificateMapper
 * @Description: 原产地证书数据访问接口
 * @Version: 1.0
 */
@TableMapper("OriginCertificate")
public interface OriginCertificateMapper extends BaseMapper<OriginCertificate> {
}
