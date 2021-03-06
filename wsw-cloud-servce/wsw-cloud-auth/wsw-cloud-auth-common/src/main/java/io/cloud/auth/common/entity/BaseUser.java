package io.cloud.auth.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: wsw-cloud-servce
 * @description: 登录用户
 * @author: wsw
 * @create: 2020-06-27 23:31
 **/
@Data
public class BaseUser implements Serializable {
    private static final long serialVersionUID = 8651145199613760899L;

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

    @ApiModelProperty("登录类型( app , web , wx , admin )")
    private String loginType;

    @ApiModelProperty(value = "公司id")
    private Long orgId;

    @ApiModelProperty(value = "部门id")
    private Long depId;

    @ApiModelProperty(value = "职位id")
    private Long jobId;

}
