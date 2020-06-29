package io.cloud.auth.api.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloud.auth.api.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-29 16:39
 **/
@Slf4j
public class WebLogoutSuccessHandler implements LogoutSuccessHandler, ResponseUtil<String> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("退出成功");
        getResponseWeb(response,objectMapper,"退出成功");
    }

}
