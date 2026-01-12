package com.nickzhang.customcert.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nickzhang.customcert.annotation.TableMapper;
import com.nickzhang.customcert.pojo.OriginCertificateInvoice;

/**
 * @Author: 张骏山
 * @Date: 2026/1/13 0:48
 * @PackageName: com.nickzhang.customcert.mapper
 * @ClassName: OriginCertificateInvoiceMapper
 * @Description: 原始发票表数据库操作接口
 * @Version: 1.0
 */
@TableMapper("OriginCertificateInvoice")
public interface OriginCertificateInvoiceMapper extends BaseMapper<OriginCertificateInvoice> {
}
