package io.cloud.auth.common.dtl;

import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
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

    private String clientId;

    private String clientSecret;

}
