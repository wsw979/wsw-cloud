package io.cloud.user.common.vo.app;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-user
 * @description:
 * @author: wsw
 * @create: 2020-06-28 10:03
 **/
@Data
public class PermissionListVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "权限名称")
    private String authName;

    @ApiModelProperty(value = "权限编码")
    private String authCode;

    @ApiModelProperty(value = "权限类型")
    private String authType;

    @ApiModelProperty(value = "请求路径")
    private String requestUrl;

}
