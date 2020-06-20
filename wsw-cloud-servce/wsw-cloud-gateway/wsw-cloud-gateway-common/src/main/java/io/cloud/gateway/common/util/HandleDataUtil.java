package io.cloud.gateway.common.util;

import com.alibaba.fastjson.JSON;
import io.cloud.data.constant.CommonConstant;
import io.cloud.gateway.common.dtl.FilterDefinition;
import io.cloud.gateway.common.dtl.PredicateDefinition;
import io.cloud.gateway.common.dtl.RouteDefinition;
import io.cloud.gateway.common.entity.GatewayRoute;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-14 01:36
 **/
public class HandleDataUtil {

    /**
     * 路由数据转换公共方法
     *
     * @param gatewayRoute
     * @return
     */
    public static RouteDefinition handleData(GatewayRoute gatewayRoute) {
        RouteDefinition definition = new RouteDefinition();
        URI uri = null;
        if (gatewayRoute.getServiceUrl().startsWith(CommonConstant.URL_PREFIX_HTTP)) {
            //http地址
            uri = UriComponentsBuilder.fromHttpUrl(gatewayRoute.getServiceUrl()).build().toUri();
        } else {
            //注册中心
            uri = UriComponentsBuilder.fromUriString(gatewayRoute.getServiceUrl()).build().toUri();
        }
        definition.setId(gatewayRoute.getServiceId());
        definition.setPredicates(JSON.parseArray(gatewayRoute.getPredicates(), PredicateDefinition.class));
        definition.setFilters(JSON.parseArray(gatewayRoute.getFilters(), FilterDefinition.class));
        definition.setUri(uri);
        definition.setOrder(Integer.parseInt(gatewayRoute.getOrder()));
        return definition;
    }

}
