package io.cloud.auth.api.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloud.auth.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-28 17:08
 **/
public class WebLoginFailureHandler implements AuthenticationFailureHandler, ResponseUtil<String> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String msg = exception.getMessage();
        response.setStatus(500);
        getErrorResponseWeb(response,objectMapper,msg);
    }

}
