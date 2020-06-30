package io.cloud.auth.common.evt;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-30 17:07
 **/
@Data
public class MessageCodeEvt {

    @NotEmpty(message = "手机号必填")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @NotEmpty(message = "图形验证码必填")
    @ApiModelProperty(value = "图形验证码", required = true)
    private String verificationCode;

    @NotNull(message = "来源必填")
    @ApiModelProperty(value = "来源（1登录2注册3修改密码）", required = true)
    private Integer codeType;

}
