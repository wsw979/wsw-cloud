package io.cloud.auth.common.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 09:05
 **/
@Data
public class AdminUser {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "公司id")
    private Long orgId;

    @ApiModelProperty(value = "部门id")
    private Long depId;

    @ApiModelProperty(value = "职位id")
    private Long jobId;

    @ApiModelProperty("登录类型( app , web , wx , admin )")
    private String loginType;

}
