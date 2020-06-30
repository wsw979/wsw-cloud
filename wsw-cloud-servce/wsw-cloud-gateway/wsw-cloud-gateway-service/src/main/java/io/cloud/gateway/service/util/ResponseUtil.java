package io.cloud.gateway.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.util.R;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description: 响应工具类
 * @author: wsw
 * @create: 2020-06-28 16:17
 **/
public interface ResponseUtil<T> {

    /**
     * webflux方式返回
     * @param exchange
     * @param objectMapper
     * @param data
     * @return
     */
    default Mono<Void> getResponse(ServerWebExchange exchange, ObjectMapper objectMapper, T data){
        ServerHttpResponse response = exchange.getResponse();
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        DataBuffer bodyDataBuffer = null;
        try {
            bodyDataBuffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(R.success(data)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("转换返回结果错误");
        }
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    /**
     * webflux方式返回
     * @param exchange
     * @param objectMapper
     * @param data
     * @return
     */
    default Mono<Void> getErrorResponse(ServerWebExchange exchange, ObjectMapper objectMapper, Result data){
        ServerHttpResponse response = exchange.getResponse();
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        DataBuffer bodyDataBuffer = null;
        try {
            bodyDataBuffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException("转换返回结果错误");
        }
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    /**
     * webflux方式返回
     * @param exchange
     * @param objectMapper
     * @param data
     * @return
     */
    default Mono<Void> getErrorResponse(ServerWebExchange exchange, ObjectMapper objectMapper, String data){
        Result result = new Result();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMsg(data);
        return getErrorResponse(exchange,objectMapper,result);
    }
}
