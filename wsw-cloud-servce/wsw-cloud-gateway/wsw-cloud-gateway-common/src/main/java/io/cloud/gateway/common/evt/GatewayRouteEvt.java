package io.cloud.gateway.common.evt;

import io.cloud.data.group.Save;
import io.cloud.data.group.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-13 17:19
 **/
@Data
public class GatewayRouteEvt {

    @Null(message = "新增时主键为空", groups = Save.class)
    @NotNull(message = "修改时主键不能为空", groups = Update.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    @NotEmpty(message = "服务名称不能为空")
    @ApiModelProperty(value = "服务名称")
    private String serviceId;

    @ApiModelProperty(value = "路由")
    private String serviceUrl;

    @ApiModelProperty(value = "访问路径")
    private String predicates;

    @ApiModelProperty(value = "过滤")
    private String filters;

    @ApiModelProperty(value = "排序")
    private String order;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
