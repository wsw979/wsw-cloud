package io.cloud.core.filter;

import io.cloud.core.constant.TraceConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description: 此处理方式可以不用aop强行设置traceid，摆脱日志traceid强制使用log注解
 * @author: wsw
 * @create: 2020-06-08 09:18
 **/
@Order(Ordered.HIGHEST_PRECEDENCE + 8)
@ConditionalOnClass(WebMvcConfigurer.class)
public class TraceContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //请求头传入存在以请求头传入的为准，不然以X-B3-TraceId为
        String app_trace_id = StringUtils.defaultString(request.getHeader(TraceConstant.HTTP_HEADER_TRACE_ID), MDC.get(TraceConstant.LOG_B3_TRACEID));
        //未经过HandlerInterceptor的设置
        if (StringUtils.isBlank(MDC.get(TraceConstant.LOG_TRACE_ID))) {
            //但是有请求头，重新设置
            if (StringUtils.isNotEmpty(app_trace_id)) {
                MDC.put(TraceConstant.LOG_TRACE_ID, app_trace_id);
            }
        }
        filterChain.doFilter(request, response);
    }
}

