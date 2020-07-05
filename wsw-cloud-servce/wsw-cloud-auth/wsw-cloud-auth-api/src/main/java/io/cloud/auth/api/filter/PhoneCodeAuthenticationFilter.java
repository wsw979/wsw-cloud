package io.cloud.auth.api.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloud.auth.api.token.PhoneCodeAuthenticationToken;
import io.cloud.auth.common.dtl.PhoneCodeDtl;
import io.cloud.auth.common.dtl.UsernameDtl;
import io.cloud.data.constant.AuthConstants;
import io.cloud.data.constant.ConfigConstant;
import io.cloud.data.util.ObjectUtil;
import io.cloud.exception.status.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-04 00:01
 **/
public class PhoneCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private ObjectMapper objectMapper;

    public PhoneCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher(AuthConstants.SECURITY_PHONE_CODE_LOGIN_URL, AuthConstants.TYPE));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(AuthConstants.TYPE)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        AbstractAuthenticationToken authRequest;
        try {
            InputStream is = request.getInputStream();
            PhoneCodeDtl dtl = objectMapper.readValue(is, PhoneCodeDtl.class);
            if (ObjectUtil.checkObjNull(dtl)) {
                throw new AuthenticationServiceException(HttpStatus.PASSWORD_ERROR.getMsg());
            }
            authRequest = new PhoneCodeAuthenticationToken(dtl.getPhone(), dtl.getCode());
            setDetails(request, authRequest);
            //request io 只能消费一次，所以存入request 供后面过滤器使用
            request.setAttribute(ConfigConstant.CLIENT_ID, dtl.getClientId());
            request.setAttribute(ConfigConstant.CLIENT_SECRET, dtl.getClientSecret());
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request,
                            AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainParameter(HttpServletRequest request, String parameter) {
        String result =  request.getParameter(parameter);
        return result == null ? "" : result;
    }

}
