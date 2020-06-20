package io.cloud.gateway.service.handler;

import cn.hutool.core.lang.Assert;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.netflix.client.ClientException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import io.cloud.exception.ServiceException;
import io.cloud.exception.status.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description: 统一异常处理
 * @author: wsw
 * @create: 2020-06-04 23:53
 **/
@Slf4j
@Component
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    /**
     * MessageReader
     */
    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();

    /**
     * MessageWriter
     */
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();

    /**
     * ViewResolvers
     */
    private List<ViewResolver> viewResolvers = Collections.emptyList();

    /**
     * 存储处理异常后的信息
     */
    private ThreadLocal<Map<String, Object>> exceptionHandlerResult = new ThreadLocal<>();

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
        Assert.notNull(messageReaders, "'messageReaders' must not be null");
        this.messageReaders = messageReaders;
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        this.viewResolvers = viewResolvers;
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        Assert.notNull(messageWriters, "'messageWriters' must not be null");
        this.messageWriters = messageWriters;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        Map<String, Object> result = this.getResultMap(ex);
        //错误记录
        ServerHttpRequest request = exchange.getRequest();
        log.error("[全局异常处理]异常请求路径:{},记录异常信息:{},打印异常日志:{}", request.getPath(), ex.getMessage(), ex);
        //参考AbstractErrorWebExceptionHandler
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        exceptionHandlerResult.set(result);
        ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse).route(newRequest)
                .switchIfEmpty(Mono.error(ex))
                .flatMap((handler) -> handler.handle(newRequest))
                .flatMap((response) -> write(exchange, response));

    }

    /**
     * 参考DefaultErrorWebExceptionHandler
     */
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> result = exceptionHandlerResult.get();
        return ServerResponse.status((Integer) result.get("code"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(result.get("msg")));
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    private Mono<? extends Void> write(ServerWebExchange exchange,
                                       ServerResponse response) {
        exchange.getResponse().getHeaders()
                .setContentType(response.headers().getContentType());
        return response.writeTo(exchange, new ResponseContext());
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    private class ResponseContext implements ServerResponse.Context {

        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return GlobalExceptionHandler.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return GlobalExceptionHandler.this.viewResolvers;
        }

    }

    private Map<String, Object> getResultMap(Throwable ex) {
        //封装响应体,此body可修改为自己的jsonBody
        Map<String, Object> result = new HashMap<>(2, 1);
        // 按照异常类型进行处理
        Integer code;
        String body;
        if (ex instanceof NotFoundException) {
            code = HttpStatus.SERVICE_ERROR.getCode();
            body = HttpStatus.SERVICE_ERROR.getMsg();
        } else if (ex.getCause() instanceof ClientException) {
            code = HttpStatus.SERVICE_ERROR.getCode();
            body = HttpStatus.SERVICE_ERROR.getMsg();
        } else if (ex instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) ex;
            code = serviceException.getCode();
            body = serviceException.getMsg();
        } else if (ex instanceof HystrixRuntimeException) {
            code = HttpStatus.SERVICE_ERROR.getCode();
            body = HttpStatus.SERVICE_ERROR.getMsg();
        } else if (ex instanceof FeignException) {
            code = HttpStatus.SERVICE_ERROR.getCode();
            body = HttpStatus.SERVICE_ERROR.getMsg();
        } else if (ex instanceof FlowException) {
            code = HttpStatus.FLOW.getCode();
            body = HttpStatus.FLOW.getMsg();
        } else if (ex instanceof BlockException) {
            code = HttpStatus.SERVICE_TIME_ERROR.getCode();
            body = HttpStatus.SERVICE_TIME_ERROR.getMsg();
        } else if (ex instanceof IOException) {
            code = HttpStatus.SERVICE_ERROR.getCode();
            body = HttpStatus.SERVICE_ERROR.getMsg();
        } else {
            code = HttpStatus.ERROR.getCode();
            body = HttpStatus.ERROR.getMsg();
        }
        result.put("code", code);
        String msg = "{\"code\":" + code + ",\"msg\": \"" + body + "\"}";
        result.put("msg", msg);
        return result;
    }
}
