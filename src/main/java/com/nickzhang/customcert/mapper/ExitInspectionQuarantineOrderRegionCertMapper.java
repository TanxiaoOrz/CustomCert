package com.nickzhang.customcert.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nickzhang.customcert.annotation.TableMapper;
import com.nickzhang.customcert.pojo.ExitInspectionQuarantineOrderRegionCert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 张骏山
 * @Date: 2026/1/9 19:30
 * @PackageName: com.nickzhang.customcert.mapper
 * @ClassName: ExitInspectionQuarantineOrderRegionCertMapper
 * @Description: 出口检测检疫订单区域证书Mapper
 * @Version: 1.0
 */
@TableMapper("ExitInspectionQuarantineOrderRegionCert")
public interface ExitInspectionQuarantineOrderRegionCertMapper extends BaseMapper<ExitInspectionQuarantineOrderRegionCert> {
}
