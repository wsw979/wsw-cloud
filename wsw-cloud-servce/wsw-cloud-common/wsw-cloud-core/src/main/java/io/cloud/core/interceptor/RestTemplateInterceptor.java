package io.cloud.core.interceptor;

import cn.hutool.core.util.StrUtil;
import io.cloud.core.constant.TraceConstant;
import io.cloud.data.constant.ConfigConstant;
import io.cloud.data.constant.SecurityConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-22 08:54
 **/
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest httpRequest = attributes.getRequest();
        String header = httpRequest.getHeader(ConfigConstant.TOKEN_HEADER);
        String token = StringUtils.isBlank(StringUtils.substringAfter(header, ConfigConstant.BEARER_TYPE)) ? httpRequest.getParameter(SecurityConstant.ACCESS_TOKEN) : StringUtils.substringAfter(header, ConfigConstant.BEARER_TYPE);
        token = StringUtils.isBlank(httpRequest.getHeader(ConfigConstant.TOKEN_HEADER)) ? token : httpRequest.getHeader(ConfigConstant.TOKEN_HEADER);
        //传递token
        HttpHeaders headers = request.getHeaders();
        headers.add(ConfigConstant.TOKEN_HEADER, token);

        //传递traceId
        String traceId = MDC.get(TraceConstant.LOG_TRACE_ID);
        if (StrUtil.isNotEmpty(traceId)) {
            headers.add(TraceConstant.LOG_TENANT_ID_HEADER, traceId);
        }

        // 保证请求继续执行
        return execution.execute(request, body);
    }

}
