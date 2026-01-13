package com.nickzhang.customcert.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nickzhang.customcert.annotation.TableMapper;
import com.nickzhang.customcert.pojo.OriginCertificateNonPartyCorp;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 张骏山
 * @Date: 2026/1/13 23:27
 * @PackageName: com.nickzhang.customcert.mapper
 * @ClassName: OriginCertificateNonPartyCorpMapper
 * @Description: TODO
 * @Version: 1.0
 */
@TableMapper("OriginCertificateNonPartyCorp")
public interface OriginCertificateNonPartyCorpMapper extends BaseMapper<OriginCertificateNonPartyCorp> {
}
