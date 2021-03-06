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

    @ApiModelProperty(value = "公司id")
    private Long orgId;

    @ApiModelProperty(value = "部门id")
    private Long depId;

    @ApiModelProperty(value = "职位id")
    private Long jobId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;
}
