package io.cloud.auth.common.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.cloud.auth.common.entity.BaseUser;
import io.cloud.auth.common.login.AdminUser;
import io.cloud.auth.common.login.ApiUser;
import io.cloud.data.constant.ConfigConstant;
import io.cloud.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description: request token 获取用户
 * @author: wsw
 * @create: 2020-06-30 14:48
 **/
@Slf4j
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 8111538085325839834L;

    /**
     * 获取ADMIN用户
     *
     * @return
     */
    public static AdminUser adminUser() {
        AdminUser adminUser = new AdminUser();
        BaseUser user = getUser();
        BeanUtils.copyProperties(user, adminUser);
        return adminUser;
    }

    /**
     * 获取APP用户
     *
     * @return
     */
    public static ApiUser apiUser() {
        ApiUser apiUser = new ApiUser();
        BaseUser user = getUser();
        BeanUtils.copyProperties(user, apiUser);
        return apiUser;
    }

    /**
     * 根据token获取用户
     *
     * @param token
     * @return
     */
    public static BaseUser getBaseUserByToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Map<String, Object> map = jwt.getClaim(ConfigConstant.USER_HEADER).asMap();
            return JSONObject.parseObject(JSONObject.toJSONString(map)).toJavaObject(BaseUser.class);
        } catch (Exception e) {
            log.error("解析token失败:" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    private static BaseUser getUser() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (attributes == null) {
            throw new ServiceException("获取不到当前请求");
        }
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader(ConfigConstant.TOKEN_HEADER);
        token = token.replace("Bearer ", "");
        return getBaseUserByToken(token);
    }
}
