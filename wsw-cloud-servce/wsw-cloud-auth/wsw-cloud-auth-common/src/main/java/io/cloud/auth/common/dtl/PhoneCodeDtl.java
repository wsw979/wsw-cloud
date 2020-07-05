package io.cloud.auth.common.dtl;

import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-04 00:07
 **/
@Data
public class PhoneCodeDtl extends BaseClient{

    /**
     * 手机号
     */
    private String phone;

    /**
     * 短信验证码
     */
    private String code;

}
