package io.cloud.gateway.api.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.ttl.internal.javassist.NotFoundException;
import io.cloud.gateway.common.dtl.RouteDefinition;
import io.cloud.redis.constant.KeyConstant;
import io.cloud.redis.util.RedisHashUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @program: wsw-cloud-servce
 * @description: 路由动态缓存
 * @author: wsw
 * @create: 2020-06-14 14:43
 **/
@AllArgsConstructor
@Component
public class RedisCacheRoute {

    private RedisHashUtil redisHashUtil;

    public Mono<Void> saveRoute(Mono<RouteDefinition> route) {
        return route
                .flatMap(routeDefinition -> {
                    redisHashUtil.hset(KeyConstant.GATEWAY_KEY_PREFIX, routeDefinition.getId(),
                            JSON.toJSONString(routeDefinition));
                    return Mono.empty();
                });
    }

    public Mono<Void> deleteRoute(Mono<String> routeId) {
        return routeId
                .flatMap(id -> {
                    if (redisHashUtil.hHasKey(KeyConstant.GATEWAY_KEY_PREFIX, id)) {
                        redisHashUtil.hdel(KeyConstant.GATEWAY_KEY_PREFIX, id);
                        return Mono.empty();
                    }
                    return Mono.defer(() -> Mono.error(new NotFoundException("路由文件没有找到: " + routeId)));
                });
    }

}
