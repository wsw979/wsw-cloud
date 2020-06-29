package io.cloud.gateway.service.filter;/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-29 21:35
 **/

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @program: wsw-cloud-servce
 * @description: 跨域配置
 * @author: wsw
 * @create: 2020-06-29 21:35
 **/
public class CorsFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange ctx, WebFilterChain chain) {
        ServerHttpRequest request = ctx.getRequest();
        if (CorsUtils.isCorsRequest(request)) {
            ServerHttpResponse response = ctx.getResponse();
            HttpHeaders headers = response.getHeaders();
            headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "false");
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
            headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
            if (request.getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }
        }
        return chain.filter(ctx);
    }

}
