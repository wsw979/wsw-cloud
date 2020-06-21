//package io.cloud.config;
//
//import cn.hutool.json.JSONUtil;
//import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler;
//import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
//import io.cloud.exception.result.Result;
//import io.cloud.exception.util.R;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Sentinel配置类
// *
// * @author zlt
// * @date 2019/1/22
// * <p>
// * Blog: https://zlt2000.gitee.io
// * Github: https://github.com/zlt2000
// */
//@Configuration
//public class SentinelAutoConfig {
//    /**
//     * 限流、熔断统一处理类
//     */
//    @ConditionalOnClass(HttpServletRequest.class)
//    public static class WebmvcHandler {
//        @Bean
//        public BlockExceptionHandler blockExceptionHandler() {
//            return (request, response, e) -> {
//                response.setStatus(429);
//                Result result = R.error(e.getMessage());
//                response.getWriter().print(JSONUtil.toJsonStr(result));
//            };
//        }
//    }
//
//
//    /**
//     * 限流、熔断统一处理类
//     */
//    @ConditionalOnClass(ServerResponse.class)
//    public static class WebfluxHandler {
//        @Bean
//        public BlockRequestHandler blockRequestHandler() {
//            return (exchange, t) ->
//                    ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .body(BodyInserters.fromObject(R.error(t.getMessage())));
//        }
//    }
//
//}
