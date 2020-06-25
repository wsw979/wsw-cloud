package io.cloud.gateway.service.filter;

import io.cloud.core.constant.ConfigConstant;
import io.cloud.data.util.StringUtil;
import io.cloud.exception.InternalException;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import io.cloud.gateway.service.entity.UrlsEntity;
import io.cloud.gateway.service.feign.AuthFeign;
import io.cloud.gateway.service.swagger.SwaggerAggProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @program: wsw-cloud-servce
 * @description: jwt过滤器
 * @author: wsw
 * @create: 2020-06-03 15:39
 **/
@Component
public class JwtTokenFilter implements GlobalFilter, Ordered {

    @Resource
    private UrlsEntity urlsEntity;

    @Resource
    private AuthFeign authFeign;

    @Resource
    private SwaggerAggProperties swaggerAggProperties;

    /**
     * 过滤器
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String url = request.getURI().getPath();
        //跳过不需要验证的路径
        if (null != urlsEntity.getUrls()) {
            if (Arrays.asList(urlsEntity.getUrls()).contains(url)) {
                return chain.filter(exchange);
            }
            if (url.contains(swaggerAggProperties.getApiDocsPath())) {
                return chain.filter(exchange);
            }
        }
        String token = request.getHeaders().getFirst(ConfigConstant.TOKEN_HEADER);
        if (StringUtil.isEmpty(token)) {
            throw new ServiceException(HttpStatus.TOKEN_ERROR);
        }
        Result result = authFeign.checkJwt(token);
        if (!result.getCode().equals(HttpStatus.SUCCESS.getCode())) {
            throw new InternalException(HttpStatus.TOKEN_ERROR);
        }
        //定义新的消息头
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());
        headers.remove(ConfigConstant.TOKEN_HEADER);
        headers.set(ConfigConstant.TOKEN_HEADER, result.getData().toString());

        request = new ServerHttpRequestDecorator(request) {
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(headers);
                return httpHeaders;
            }
        };
        response.getHeaders().add(ConfigConstant.TOKEN_HEADER, result.getData().toString());
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return -201;
    }

}
