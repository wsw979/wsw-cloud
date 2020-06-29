package io.cloud.auth.api.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloud.core.filter.HttpHelper;
import io.cloud.auth.common.dtl.UsernameDtl;
import io.cloud.auth.api.token.UsernameAuthenticationToken;
import io.cloud.data.constant.ConfigConstant;
import io.cloud.data.constant.AuthConstants;
import io.cloud.data.util.ObjectUtil;
import io.cloud.exception.ServiceException;
import io.cloud.exception.status.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-28 15:46
 **/
@Slf4j
public class UsernameAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public UsernameAuthenticationFilter() {
        super(new AntPathRequestMatcher(AuthConstants.SECURITY_USERNAME_LOGIN_URL, AuthConstants.TYPE));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(AuthConstants.TYPE)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        AbstractAuthenticationToken authRequest;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String body = HttpHelper.getBodyString(request);
            UsernameDtl dtl = mapper.readValue(body, UsernameDtl.class);
            if (ObjectUtil.checkObjNull(dtl)) {
                throw new ServiceException(HttpStatus.PASSWORD_ERROR);
            }
            dtl.setPassword(dtl.getPassword().trim());
            authRequest = new UsernameAuthenticationToken(dtl.getUsername(), dtl.getPassword());
            setDetails(request, authRequest);
            request.setAttribute(ConfigConstant.CLIENT_ID, dtl.getClientId());
            request.setAttribute(ConfigConstant.CLIENT_SECRET, dtl.getClientSecret());
        } catch (IOException e) {
            e.printStackTrace();
            authRequest = new UsernameAuthenticationToken("", "");
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
