package io.cloud.user.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-user
 * @description:
 * @author: wsw
 * @create: 2020-06-30 14:59
 **/
@Data
public class LoginUserInfo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像url")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    private String nickname;

}
