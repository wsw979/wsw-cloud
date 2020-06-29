package io.cloud.data.constant;

/**
 * @program: wsw-cloud-servce
 * @description: 配置项常量
 * @author: wsw
 * @create: 2020-05-06 20:48
 **/
public interface AuthConstants {

    String TYPE = "POST";

    String SECURITY_USERNAME_LOGIN_URL = "/username-login";

    /**
     * 登出URL
     */
    String SECURITY_LOGOUT_URL = "/oauth/remove/token";
}
