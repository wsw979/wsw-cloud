package io.cloud.auth.api.handler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.cloud.auth.common.entity.TokenEntity;
import io.cloud.auth.common.util.TokenUtil;
import io.cloud.data.constant.ConfigConstant;
import io.cloud.data.constant.SecurityConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: wsw-cloud-servce
 * @description: 退出
 * @author: wsw
 * @create: 2020-06-29 16:39
 **/
@Slf4j
public class WebLogoutHandler implements LogoutHandler {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 获取Token
        String accessToken = request.getHeader(ConfigConstant.TOKEN_HEADER);
        String loginType = request.getHeader(ConfigConstant.LOGIN_TYPE);
        accessToken = accessToken.replace("Bearer ", "");
        String id = null;
        if (accessToken != null) {
            DecodedJWT jwt = JWT.decode(accessToken);
            id = String.valueOf(jwt.getClaims().get(ConfigConstant.USER_HEADER).asMap().get("id"));
        }
        tokenUtil.logout(id,loginType);
    }

}
