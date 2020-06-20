package io.cloud.gateway.service.handler;

import com.alibaba.fastjson.JSON;
import io.cloud.data.constant.CommonConstant;
import io.cloud.gateway.common.entity.GatewayRoute;
import io.cloud.gateway.service.repository.RedisRouteDefinitionRepository;
import io.cloud.gateway.service.service.IGatewayRouteService;
import io.cloud.redis.constant.KeyConstant;
import io.cloud.redis.util.RedisCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description: 动态路由
 * @author: wsw
 * @create: 2020-06-13 11:50
 **/
@Slf4j
@Service
public class GatewayServiceHandler implements ApplicationEventPublisherAware, ApplicationRunner {

    @Autowired
    private RedisRouteDefinitionRepository routeDefinitionWriter;

    @Autowired
    private IGatewayRouteService gatewayRouteService;

    @Autowired
    private RedisCommonUtil redisCommonUtil;

    private ApplicationEventPublisher publisher;

    /**
     * ApplicationRunner
     * 初始化加载路由配置信息到redis
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        redisCommonUtil.del(KeyConstant.GATEWAY_KEY_PREFIX);
        List<GatewayRoute> gatewayRouteList = gatewayRouteService.findList();
        gatewayRouteList.forEach(gatewayRoute -> {
            RouteDefinition definition= this.handleData(gatewayRoute);
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        });
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 路由数据转换公共方法
     * @param gatewayRoute
     * @return
     */
    private RouteDefinition handleData(GatewayRoute gatewayRoute){
        RouteDefinition definition = new RouteDefinition();
        URI uri = null;
        if(gatewayRoute.getServiceUrl().startsWith(CommonConstant.URL_PREFIX_HTTP)){
            //http地址
            uri = UriComponentsBuilder.fromHttpUrl(gatewayRoute.getServiceUrl()).build().toUri();
        }else{
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
