package io.cloud.auth.api.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloud.auth.api.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 14:06
 **/
@Slf4j
public class WebAccessDeniedHandler implements AccessDeniedHandler, ResponseUtil<String> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.info("权限不足，访问失败！");
        String msg = e.getMessage();
        response.setStatus(500);
        getErrorResponseWeb(response, objectMapper, msg);
    }

}
