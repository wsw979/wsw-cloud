package io.cloud.user.common.evt.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-user
 * @description:
 * @author: wsw
 * @create: 2020-07-07 14:33
 **/
@Data
public class RoleEvt {

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色标识")
    private String roleCode;

}
