package io.cloud.gateway.api.controller;

import io.cloud.data.annotation.WswRestController;
import io.cloud.data.group.Save;
import io.cloud.exception.result.Result;
import io.cloud.gateway.api.service.IGatewayRouteService;
import io.cloud.gateway.common.entity.GatewayRoute;
import io.cloud.gateway.common.evt.GatewayRouteEvt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-07 14:24
 **/
@Slf4j
@AllArgsConstructor
@Api(tags = "事务测试")
@WswRestController(path = "/seataTest")
public class SeataTestController {

    private IGatewayRouteService gatewayRouteService;

    @PostMapping("/testSuccess")
    @ApiOperation(value = "新增-成功", notes = "调用USER事务测试成功")
    public Result testSuccess(@ApiParam("实体") @Valid @Validated(Save.class) @RequestBody GatewayRouteEvt evt) {
        return gatewayRouteService.testSuccess(evt);
    }

    @PostMapping("/testError")
    @ApiOperation(value = "新增-错误", notes = "调用USER事务测试回滚")
    public Result testError(@ApiParam("实体") @Valid @Validated(Save.class) @RequestBody GatewayRouteEvt evt) {
        return gatewayRouteService.testError(evt);
    }

    @PostMapping("/testLocal")
    @ApiOperation(value = "新增-本地", notes = "本队事务测试回滚")
    public Result testLocal(@ApiParam("实体") @Valid @Validated(Save.class) @RequestBody GatewayRouteEvt evt) {
        return gatewayRouteService.testLocal(evt);
    }

}
