package io.cloud.data.constant;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-08 09:18
 **/
public interface ConfigConstant {

    /**
     * token请求头名称
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * token前缀
     */
    String BEARER_TYPE = "Bearer ";

    /**
     * 登录类型
     */
    String LOGIN_TYPE = "x-login-header";

    /**
     * 用户信息头
     */
    String USER_HEADER = "x-user-header";

    /**
     * 用户id信息头
     */
    String USER_ID_HEADER = "x-userid-header";

    /**
     * 角色信息头
     */
    String ROLE_HEADER = "x-role-header";

    /**
     * 注册中心元数据 版本号
     */
    String METADATA_VERSION = "version";

    /**
     * 负载均衡策略-版本号 信息头
     */
    String Z_L_T_VERSION = "w-s-w-version";

    /**
     * 是否开启自定义隔离规则
     */
    String CONFIG_RIBBON_ISOLATION_ENABLED = "wsw.ribbon.isolation.enabled";

}
