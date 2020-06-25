package io.cloud.gateway.service.config;

import io.cloud.gateway.service.handler.HystrixFallbackHandler;
import io.cloud.gateway.service.handler.SwaggerResourceHandler;
import io.cloud.gateway.service.handler.SwaggerSecurityHandler;
import io.cloud.gateway.service.handler.SwaggerUiHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-22 14:18
 **/
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {

    private HystrixFallbackHandler hystrixFallbackHandler;
    private SwaggerResourceHandler swaggerResourceHandler;
    private SwaggerSecurityHandler swaggerSecurityHandler;
    private SwaggerUiHandler swaggerUiHandler;


    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.GET("/defaultfallback")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler)
                .andRoute(RequestPredicates.GET("/swagger-resources")
                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerResourceHandler)
                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui")
                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/security")
                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);
    }

}
