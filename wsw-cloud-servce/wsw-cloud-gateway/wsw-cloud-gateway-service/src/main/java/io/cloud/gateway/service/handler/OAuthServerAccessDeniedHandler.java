package io.cloud.gateway.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloud.exception.result.Result;
import io.cloud.exception.util.R;
import io.cloud.gateway.service.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @program: wsw-cloud-servce
 * @description: 资源无权限
 * @author: wsw
 * @create: 2020-06-30 09:36
 **/
public class OAuthServerAccessDeniedHandler implements ServerAccessDeniedHandler, ResponseUtil<String> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException e) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        Result error = R.error(HttpStatus.UNAUTHORIZED.value(), "无权限访问！");
        return getErrorResponse(exchange,objectMapper,error);
    }
}
