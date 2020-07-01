package io.cloud.sms.evt;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 16:53
 **/
@Data
public class CodeMessageEvt {

    @ApiModelProperty(value = "验证码")
    private String smsCode;

    @ApiModelProperty(value = "手机号码")
    private String smsPhone;

    @ApiModelProperty(value = "邮箱号码")
    private String smsEmail;

    @ApiModelProperty(value = "验证号码类型（1手机2邮箱）")
    private Integer smsNumType;

    @ApiModelProperty(value = "验证码类型（1登录2注册3修改密码）")
    private Integer smsType;

    @ApiModelProperty(value = "来源（1app2web3wx4admin）")
    private Integer smsSource;
}
