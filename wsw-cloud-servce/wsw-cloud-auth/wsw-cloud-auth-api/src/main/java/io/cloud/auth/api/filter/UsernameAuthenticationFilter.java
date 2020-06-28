package io.cloud.auth.api.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloud.auth.common.dtl.UsernameDtl;
import io.cloud.auth.api.token.UsernameAuthenticationToken;
import io.cloud.data.constant.LoginConstants;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-28 15:46
 **/
public class UsernameAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public UsernameAuthenticationFilter(){
        super(new AntPathRequestMatcher(LoginConstants.SECURITY_USERNAME_LOGIN_URL,LoginConstants.TYPE));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(LoginConstants.TYPE)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        AbstractAuthenticationToken authRequest;
        ObjectMapper mapper = new ObjectMapper();
        try {
            ServletInputStream inputStream = request.getInputStream();
            InputStream is = inputStream;
            UsernameDtl dtl = JSON.parseObject(is, UsernameDtl.class);
            dtl.setPassword(dtl.getPassword().trim());
            authRequest = new UsernameAuthenticationToken(dtl.getUsername(), dtl.getPassword());
            setDetails(request, authRequest);
        }catch (IOException e) {
            e.printStackTrace();
            authRequest = new UsernameAuthenticationToken("", "");
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
