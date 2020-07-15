package io.cloud.core.filter;

import cn.hutool.core.util.StrUtil;
import io.cloud.core.constant.TraceConstant;
import io.cloud.core.context.TenantContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
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
@ConditionalOnClass(Filter.class)
public class TenantFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            //优先获取请求参数中的tenantId值
            String tenantId = request.getParameter(TraceConstant.LOG_TRACE_ID);
            if (StrUtil.isEmpty(tenantId)) {
                tenantId = request.getHeader(TraceConstant.LOG_TENANT_ID_HEADER);
            }
            //保存租户id
            if (StrUtil.isNotEmpty(tenantId)) {
                TenantContextHolder.setTenant(tenantId);
            }

            filterChain.doFilter(request, response);
        } finally {
            TenantContextHolder.clear();
        }
    }
}

