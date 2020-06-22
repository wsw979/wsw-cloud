//package io.cloud.gateway.service.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//import org.springframework.web.util.pattern.PathPatternParser;
//
///**
// * @program: wsw-cloud-servce
// * @description: 跨域配置
// * @author: wsw
// * @create: 2020-06-05 00:01
// **/
//@Configuration
//public class CorsConfig {
//
//    private static final String ALL = "*";
//
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    @Bean
//    public CorsWebFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(Boolean.TRUE);
//        config.addAllowedMethod(ALL);
//        config.addAllowedOrigin(ALL);
//        config.addAllowedHeader(ALL);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
//        source.registerCorsConfiguration("/**", config);
//        return new CorsWebFilter(source);
//    }
//}
