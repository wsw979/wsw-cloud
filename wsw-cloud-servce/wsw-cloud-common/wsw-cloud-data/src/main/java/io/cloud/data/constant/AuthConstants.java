package io.cloud.data.constant;

/**
 * @program: wsw-cloud-servce
 * @description: 配置项常量
 * @author: wsw
 * @create: 2020-05-06 20:48
 **/
public interface AuthConstants {

    String TYPE = "POST";

    /**
     * 登录类型
     */
    String LOGIN_TYPE_APP = "app";
    String LOGIN_TYPE_WEB = "web";
    String LOGIN_TYPE_WX = "wx";
    String LOGIN_TYPE_ADMIN = "admin";

    /**
     * 用户名/邮箱/手机号 密码登录地址
     */
    String SECURITY_USERNAME_LOGIN_URL = "/username-login";

    /**
     * 登出URL
     */
    String SECURITY_LOGOUT_URL = "/oauth/remove/token";
}
