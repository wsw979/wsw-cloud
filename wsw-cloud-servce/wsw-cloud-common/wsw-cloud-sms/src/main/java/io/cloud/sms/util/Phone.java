package io.cloud.sms.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 17:42
 **/
@Data
public class Phone {

    @ApiModelProperty(value = "手机号码")
    private String smsPhone;

    @ApiModelProperty(value = "验证码")
    private String smsCode;

}
