package io.cloud.gateway.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-16 21:50
 **/
@Data
public class GatewayRouteDtlVo {

    @ApiModelProperty(value = "主键")
    private Long id;

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
