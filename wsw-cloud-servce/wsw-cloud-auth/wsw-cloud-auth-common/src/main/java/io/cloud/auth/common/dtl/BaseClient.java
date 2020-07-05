package io.cloud.auth.common.dtl;

import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-04 00:06
 **/
@Data
public class BaseClient {

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端秘钥
     */
    private String clientSecret;

}
