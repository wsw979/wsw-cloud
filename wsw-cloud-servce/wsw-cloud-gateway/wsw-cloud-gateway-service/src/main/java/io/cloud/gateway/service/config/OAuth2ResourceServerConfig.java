package io.cloud.gateway.service.config;/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-29 21:33
 **/

import io.cloud.core.filter.SecurityCorsFilter;
import io.cloud.gateway.service.filter.CorsFilter;
import io.cloud.gateway.service.filter.ReactiveRequestContextFilter;
import io.cloud.gateway.service.manager.WebfluxReactiveAuthorizationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @program: wsw-cloud-servce
 * @description: 资源服务器配置
 * @author: wsw
 * @create: 2020-06-29 21:33
 **/
@EnableWebFluxSecurity
public class OAuth2ResourceServerConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.cors().and().csrf().disable().authorizeExchange().anyExchange().access(reactiveAuthorizationManager());
        http.addFilterAt(new CorsFilter(), SecurityWebFiltersOrder.SECURITY_CONTEXT_SERVER_WEB_EXCHANGE);
        http.addFilterAt(new ReactiveRequestContextFilter(), SecurityWebFiltersOrder.SECURITY_CONTEXT_SERVER_WEB_EXCHANGE);
//        http.oauth2ResourceServer().jwt();
        return http.build();
    }

    /**
     * 注入授权管理器
     * @return
     */
    @Bean
    public ReactiveAuthorizationManager reactiveAuthorizationManager(){
        WebfluxReactiveAuthorizationManager webfluxReactiveAuthorizationManager = new WebfluxReactiveAuthorizationManager();
        return webfluxReactiveAuthorizationManager;
    }

}
