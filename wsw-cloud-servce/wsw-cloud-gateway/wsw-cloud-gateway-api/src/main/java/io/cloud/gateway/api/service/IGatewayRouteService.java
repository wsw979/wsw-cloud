package io.cloud.gateway.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cloud.exception.result.Result;
import io.cloud.gateway.common.entity.GatewayRoute;
import io.cloud.gateway.common.evt.GatewayRouteEvt;
import io.cloud.gateway.common.evt.GatewayRouteListEvt;
import io.cloud.gateway.common.vo.GatewayRouteDtlVo;
import io.cloud.gateway.common.vo.GatewayRouteListVo;

import java.util.List;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-12 10:45
 **/
public interface IGatewayRouteService extends IService<GatewayRoute> {

    Result<List<GatewayRouteListVo>> findPageList(GatewayRouteListEvt evt);

    Result<GatewayRouteDtlVo> findById(Long id);

    GatewayRoute saveOrUpdate(GatewayRouteEvt evt);

    GatewayRoute delete(Long id);

    Result testSuccess(GatewayRouteEvt evt);

    Result testError(GatewayRouteEvt evt);

    Result testLocal(GatewayRouteEvt evt);

}
