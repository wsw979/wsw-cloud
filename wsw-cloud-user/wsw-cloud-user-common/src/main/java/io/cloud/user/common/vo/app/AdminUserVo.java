package io.cloud.user.common.vo.app;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-user
 * @description:
 * @author: wsw
 * @create: 2020-06-28 10:40
 **/
@Data
public class AdminUserVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "授权凭证")
    private String credential;

    @ApiModelProperty(value = "盐值")
    private String salt;

}
