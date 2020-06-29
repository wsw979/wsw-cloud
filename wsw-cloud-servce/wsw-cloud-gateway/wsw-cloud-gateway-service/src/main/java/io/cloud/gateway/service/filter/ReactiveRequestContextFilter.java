package io.cloud.gateway.service.filter;/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-29 21:38
 **/

import io.cloud.gateway.service.holder.ReactiveRequestContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-29 21:38
 **/
public class ReactiveRequestContextFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange).subscriberContext(ctx -> ReactiveRequestContextHolder.put(ctx, exchange));
    }

}
