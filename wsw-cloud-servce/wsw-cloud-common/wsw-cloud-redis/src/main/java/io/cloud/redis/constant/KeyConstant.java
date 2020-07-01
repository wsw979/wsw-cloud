package io.cloud.redis.constant;

/**
 * @program: wsw-cloud-servce
 * @description: 缓存前缀
 * @author: wsw
 * @create: 2020-06-05 15:09
 **/
public interface KeyConstant {

    /**
     * 分布式锁 - redis版本
     */
    String LOCK_KEY_PREFIX = "LOCK_KEY:";

    /**
     * 网关动态路由
     */
    String GATEWAY_KEY_PREFIX = "gateway:routes";

    /**
     * oauth key
     */
    String PROJECT_OAUTH_ACCESS = "oauth:permission:";

    /**
     * token key
     */
    String USER_TOKEN = "oauth:token:";

    /**
     * message
     */
    String CODE_MESSAGE = "message:";
}
