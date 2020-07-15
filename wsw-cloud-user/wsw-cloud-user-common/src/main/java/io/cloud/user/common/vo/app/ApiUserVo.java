package io.cloud.user.common.vo.app;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-user
 * @description:
 * @author: wsw
 * @create: 2020-06-17 10:38
 **/
@Data
public class ApiUserVo {

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

    @ApiModelProperty(value = "授权凭证")
    private String credential;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "是否有效（0注销1正常2禁用）")
    private Integer isValid;

}
