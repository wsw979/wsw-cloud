package io.cloud.gateway.service.holder;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * @program: wsw-cloud-servce
 * @description: RequestContext传递
 * @author: wsw
 * @create: 2020-06-29 21:38
 **/
public class ReactiveRequestContextHolder {

    private static final Class<ServerWebExchange> CONTEXT_KEY = ServerWebExchange.class;

    public static Mono<ServerWebExchange> getExchange() {
        return Mono.subscriberContext()
                .filter(c -> c.hasKey(CONTEXT_KEY))
                .flatMap(c -> Mono.just(c.get(CONTEXT_KEY)));
    }

    public static Mono<ServerHttpRequest> getRequest() {
        return ReactiveRequestContextHolder.getExchange()
                .map(ServerWebExchange::getRequest);
    }

    public static Context put(Context context, ServerWebExchange exchange) {
        return context.put(CONTEXT_KEY, exchange);
    }

}
