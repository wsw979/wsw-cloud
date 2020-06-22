package io.cloud.auth.common.evt;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-22 16:10
 **/
@Data
public class JwtUserEvt {

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private Long id;

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户登录时间")
    private LocalDateTime loginTime;

}
