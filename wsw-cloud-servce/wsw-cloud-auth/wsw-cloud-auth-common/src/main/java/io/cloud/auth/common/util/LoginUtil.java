package io.cloud.auth.common.util;

import io.cloud.auth.common.feign.UserFeign;
import io.cloud.user.common.base.LoginUserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @program: wsw-cloud-servce
 * @description: request token 获取用户 gateway存入user-id上下文
 * 通过用户服务获取 需要改造，区分api和admin
 * @author: wsw
 * @create: 2020-06-30 15:24
 **/
@Component
@AllArgsConstructor
public class LoginUtil {

    private UserFeign userFeign;

    public LoginUserInfo getApiUser() {
        LoginUserInfo data = userFeign.loginApiUser().getData();
        return data;
    }

    public LoginUserInfo getAdminUser() {
        LoginUserInfo data = userFeign.loginAdminUser().getData();
        return data;
    }
}
