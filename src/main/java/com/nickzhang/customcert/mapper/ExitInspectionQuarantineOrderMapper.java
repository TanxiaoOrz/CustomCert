package com.nickzhang.customcert.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nickzhang.customcert.annotation.TableMapper;
import com.nickzhang.customcert.pojo.ExitInspectionQuarantineOrder;

/**
 * @Author: 张骏山
 * @Date: 2026/1/9 19:25
 * @PackageName: com.nickzhang.customcert.mapper
 * @ClassName: ExitInspectionQuarantineOrderMapper
 * @Description: 出口检测检疫订单Mapper
 * @Version: 1.0
 */
@TableMapper("ExitInspectionQuarantineOrder")
public interface ExitInspectionQuarantineOrderMapper extends BaseMapper<ExitInspectionQuarantineOrder> {
}
