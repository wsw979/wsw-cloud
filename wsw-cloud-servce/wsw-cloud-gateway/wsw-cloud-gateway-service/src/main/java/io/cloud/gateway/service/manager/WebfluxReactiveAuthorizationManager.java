package io.cloud.gateway.service.manager;

import io.cloud.auth.common.entity.TokenEntity;
import io.cloud.gateway.service.properties.AuthProperties;
import io.cloud.user.common.vo.app.PermissionListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: wsw-cloud-servce
 * @description: 核心配置，gateway鉴权
 * @author: wsw
 * @create: 2020-06-29 17:17
 **/
public class WebfluxReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Resource
    private AuthProperties authProperties;

    @Autowired
    private RedisTemplate<String, TokenEntity> redisTemplate;

    @Autowired
    private RedisTemplate<String, PermissionListVo> permissionRedisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        return null;
    }

    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return null;
    }
}
