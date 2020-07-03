package io.cloud.auth.api.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloud.auth.api.token.UsernameAuthenticationToken;
import io.cloud.auth.common.dtl.ImageCode;
import io.cloud.auth.common.dtl.UsernameDtl;
import io.cloud.data.constant.AuthConstants;
import io.cloud.data.constant.ConfigConstant;
import io.cloud.data.util.ObjectUtil;
import io.cloud.exception.status.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @program: wsw-cloud-servce
 * @description: 用户名/手机号/邮箱 |密码 登录过滤器
 * @author: wsw
 * @create: 2020-06-28 15:46
 **/
@Slf4j
public class UsernameAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public UsernameAuthenticationFilter() {
        super(new AntPathRequestMatcher(AuthConstants.SECURITY_USERNAME_LOGIN_URL, AuthConstants.TYPE));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(AuthConstants.TYPE)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        AbstractAuthenticationToken authRequest;
        try {
            InputStream is = request.getInputStream();
            UsernameDtl dtl = objectMapper.readValue(is, UsernameDtl.class);
            if (ObjectUtil.checkObjNull(dtl)) {
                throw new AuthenticationServiceException(HttpStatus.PASSWORD_ERROR.getMsg());
            }
            validate(new ServletWebRequest(request), dtl.getImageCode());
            dtl.setPassword(dtl.getPassword().trim());
            authRequest = new UsernameAuthenticationToken(dtl.getUsername(), dtl.getPassword());
            setDetails(request, authRequest);

            //request io 只能消费一次，所以存入request 供后面过滤器使用
            request.setAttribute(ConfigConstant.CLIENT_ID, dtl.getClientId());
            request.setAttribute(ConfigConstant.CLIENT_SECRET, dtl.getClientSecret());
            request.setAttribute(AuthConstants.IMAGE_CODE, dtl.getImageCode());
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private void validate(ServletWebRequest request, String imageCode) throws AuthenticationException, ServletRequestBindingException {
        //取出session中的验证码对象
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, AuthConstants.IMAGE_CODE);
        //取出表单提交中的验证码信息
        if (codeInSession == null) {
            throw new ServletRequestBindingException("验证码必填");
        }
        if (StringUtils.isBlank(imageCode)) {
            throw new ServletRequestBindingException("验证码必填");
        }
        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(request, AuthConstants.IMAGE_CODE);
            throw new AuthenticationServiceException("验证码失效");
        }
        if (!StringUtils.equals(codeInSession.getCode(), imageCode)) {
            throw new AuthenticationServiceException("验证码不正确");
        }
        //验证通过，清除session中的验证码信息
        sessionStrategy.removeAttribute(request, AuthConstants.IMAGE_CODE);
    }

}
