package io.cloud.auth.common.dtl;

import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description: 登录用户JSON接收对象
 * @author: wsw
 * @create: 2020-06-28 15:50
 **/
@Data
public class UsernameDtl {

    /**
     * 用户名/手机号/邮箱
     */
    private String username;

    /**
     * 凭证或验证
     */
    private String password;

    /**
     * 图形验证码
     */
    private String imageCode;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端秘钥
     */
    private String clientSecret;

}
