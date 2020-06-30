package io.cloud.auth.api.filter;

import io.cloud.auth.common.dtl.ImageCode;
import io.cloud.data.constant.AuthConstants;
import io.cloud.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-30 17:26
 **/
public class ValidateCodeFilter extends OncePerRequestFilter {

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String url = request.getRequestURI();
        //只有当处理登录功能时才执行
        if (StringUtils.equals(AuthConstants.SECURITY_USERNAME_LOGIN_URL,url) && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            validate(new ServletWebRequest(request));
        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws AuthenticationException, ServletRequestBindingException {
        //取出session中的验证码对象
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, AuthConstants.IMAGE_CODE);
        //取出表单提交中的验证码信息
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ServiceException("验证码必填");
        }
        if (codeInSession == null) {
            throw new ServiceException("验证码必填");
        }
        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(request, AuthConstants.IMAGE_CODE);
            throw new ServiceException("验证码失效");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ServiceException("验证码不正确");
        }
        //验证通过，清除session中的验证码信息
        sessionStrategy.removeAttribute(request, AuthConstants.IMAGE_CODE);
    }
}
