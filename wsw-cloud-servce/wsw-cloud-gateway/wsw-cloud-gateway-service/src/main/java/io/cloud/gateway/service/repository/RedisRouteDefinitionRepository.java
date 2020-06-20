package io.cloud.gateway.service.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.ttl.internal.javassist.NotFoundException;
import io.cloud.redis.constant.KeyConstant;
import io.cloud.redis.util.RedisHashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: wsw-cloud-servce
 * @description: 自定义gateway路由
 * @author: wsw
 * @create: 2020-06-13 11:47
 **/
@Slf4j
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    @Autowired
    private RedisHashUtil redisHashUtil;

    /**
     * 自定义路由，服务从此处加载路由信息转发
     *
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();
        redisHashUtil.hValues(KeyConstant.GATEWAY_KEY_PREFIX).stream().forEach(routeDefinition -> {
            routeDefinitions.add(JSON.parseObject(routeDefinition.toString(), RouteDefinition.class));
        });
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route
                .flatMap(routeDefinition -> {
                    redisHashUtil.hset(KeyConstant.GATEWAY_KEY_PREFIX, routeDefinition.getId(),
                            JSON.toJSONString(routeDefinition));
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if (redisHashUtil.hHasKey(KeyConstant.GATEWAY_KEY_PREFIX, id)) {
                redisHashUtil.hdel(KeyConstant.GATEWAY_KEY_PREFIX, id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("路由文件没有找到: " + routeId)));
        });
    }

}
