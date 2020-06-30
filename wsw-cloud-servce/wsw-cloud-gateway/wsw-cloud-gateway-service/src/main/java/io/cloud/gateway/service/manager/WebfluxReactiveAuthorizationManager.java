package io.cloud.gateway.service.manager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.cloud.auth.common.entity.BaseUser;
import io.cloud.auth.common.entity.TokenEntity;
import io.cloud.auth.common.properties.AuthServerProperties;
import io.cloud.auth.common.util.PermissionUtil;
import io.cloud.auth.common.util.TokenUtil;
import io.cloud.data.constant.ConfigConstant;
import io.cloud.gateway.service.properties.AuthProperties;
import io.cloud.user.common.vo.app.PermissionListVo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: wsw-cloud-servce
 * @description: 核心配置，gateway资源服务器鉴权
 * @author: wsw
 * @create: 2020-06-29 17:17
 **/
public class WebfluxReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final AntPathMatcher matcher = new AntPathMatcher();

    @Resource
    private AuthServerProperties authServerProperties;

    @Resource
    private AuthProperties authProperties;

    @Autowired
    private PermissionUtil permissionUtil;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        ServerWebExchange exchange = authorizationContext.getExchange();
        //获取请求
        ServerHttpRequest request = exchange.getRequest();
        //判断当前是否有接口权限
        String url = request.getPath().value();
        String httpMethod = request.getMethod().name();
        //OPTIONS请求直接放过
        if (HttpMethod.OPTIONS.name().equals(httpMethod)) {
            return Mono.just(new AuthorizationDecision(true));
        }
        //跳过不需要验证的路径
        Iterator<String> iterator = authProperties.getIgnoredList().iterator();
        while (iterator.hasNext()) {
            String trim = iterator.next().trim();
            if (matcher.match(trim, url)) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        //需要进行权限验证的
        return
                //过滤验证成功的
                authentication.filter(a -> a.isAuthenticated())
                //转换成Flux
                .flatMapIterable(a -> {
                    Jwt jwtValue = null;
                    if(a.getPrincipal() instanceof Jwt){
                        jwtValue = (Jwt)a.getPrincipal();
                    }
                    JSONObject tokenInfo = JSONObject.parseObject(JSONObject.toJSONString(jwtValue.getClaims()));
                    BaseUser baseUser = tokenInfo.getJSONObject(ConfigConstant.USER_HEADER).toJavaObject(BaseUser.class);
                    //存储当前数据
                    List<AuthUser> authUsers = new ArrayList<>();
                    JSONArray array = tokenInfo.getJSONArray("authorities");
                    for (int i = 0;i<array.size();i++){
                        AuthUser authUser = new AuthUser();
                        authUser.setBaseUser(baseUser);
                        authUser.setAuthority(array.get(i).toString());
                        authUsers.add(authUser);
                    }
                    return authUsers;
                })
                //转成成权限名称，检测权限是否匹配
                .any(c-> {
                    //获取当前用户
                    BaseUser baseUser = c.getBaseUser();
                    //判断当前携带的Token是否有效
                    String token = request.getHeaders().getFirst(ConfigConstant.TOKEN_HEADER).replace("Bearer ","");
                    if(!tokenUtil.judgeTokenValid(String.valueOf(baseUser.getId()),baseUser.getLoginType(),token,authServerProperties)){
                        return false;
                    }
                    //获取当前权限
                    String authority = c.getAuthority();
                    //通过当前权限码查询可以请求的地址
                    List<PermissionListVo> permissions = permissionUtil.getPermissions(baseUser.getId());
                    permissions = permissions.stream().filter(permission -> StringUtils.isNotBlank(permission.getRequestUrl())).collect(Collectors.toList());
                    //请求URl匹配，放行
                    if(permissions.stream().anyMatch(permission -> matcher.match(permission.getRequestUrl(),url))){
                        request.mutate().header(ConfigConstant.USER_ID_HEADER, String.valueOf(baseUser.getId())).build();
                        return true;
                    }
                    return false;
                })
                .map(hasAuthority ->  new AuthorizationDecision(hasAuthority)).defaultIfEmpty(new AuthorizationDecision(false));
    }


    /**
     * 构造对象
     */
    @Data
    private class AuthUser {
        private String authority;

        private BaseUser baseUser;
    }
}
