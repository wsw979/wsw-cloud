package io.cloud.core.filter;

import cn.hutool.core.util.StrUtil;
import io.cloud.core.constant.TraceConstant;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-15 11:06
 **/
@ConditionalOnClass(Filter.class)
public class TraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        try {
            String traceId = request.getHeader(TraceConstant.LOG_TENANT_ID_HEADER);
            if (StrUtil.isNotEmpty(traceId)) {
                MDC.put(TraceConstant.LOG_TRACE_ID, traceId);
            }

            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

}
