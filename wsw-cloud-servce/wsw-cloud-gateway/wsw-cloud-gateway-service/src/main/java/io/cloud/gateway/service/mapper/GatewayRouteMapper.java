package io.cloud.gateway.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.cloud.gateway.common.entity.GatewayRoute;
import io.cloud.gateway.common.vo.GatewayRouteListVo;
import org.apache.ibatis.annotations.Param;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-12 10:46
 **/
public interface GatewayRouteMapper extends BaseMapper<GatewayRoute> {

}
