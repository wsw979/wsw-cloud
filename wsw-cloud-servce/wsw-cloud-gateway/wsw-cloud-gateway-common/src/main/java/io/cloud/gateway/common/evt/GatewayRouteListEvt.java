package io.cloud.gateway.common.evt;

import io.cloud.data.base.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-13 16:56
 **/
@Data
public class GatewayRouteListEvt extends BasePage {

    @ApiModelProperty(value = "服务名称", required = false)
    private String serviceId;

}
