package com.nickzhang.customcert.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nickzhang.customcert.annotation.TableMapper;
import com.nickzhang.customcert.pojo.OriginCertificateGoods;

/**
 * @Author: 张骏山
 * @Date: 2026/1/11 21:55
 * @PackageName: com.nickzhang.customcert.mapper
 * @ClassName: OriginCertificateGoodsMapper
 * @Description: 原产地商品数据访问接口
 * @Version: 1.0
 */
@TableMapper("OriginCertificateGoods")
public interface OriginCertificateGoodsMapper extends BaseMapper<OriginCertificateGoods> {
}
