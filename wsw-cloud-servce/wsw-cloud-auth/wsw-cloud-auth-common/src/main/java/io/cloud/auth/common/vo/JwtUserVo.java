package io.cloud.auth.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-01 10:32
 **/
@Data
public class JwtUserVo {

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private Long id;

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    @NotNull(message = "用户头像不能为空")
    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @NotNull(message = "用户昵称不能为空")
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @NotNull(message = "用户登录时间不能为空")
    @ApiModelProperty(value = "用户登录时间")
    private LocalDateTime loginTime;

}
