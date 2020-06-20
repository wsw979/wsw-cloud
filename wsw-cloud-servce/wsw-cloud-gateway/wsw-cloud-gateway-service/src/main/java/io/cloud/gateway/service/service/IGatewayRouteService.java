package io.cloud.gateway.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cloud.gateway.common.entity.GatewayRoute;

import java.util.List;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-12 10:45
 **/
public interface IGatewayRouteService extends IService<GatewayRoute> {

    List<GatewayRoute> findList();

}
