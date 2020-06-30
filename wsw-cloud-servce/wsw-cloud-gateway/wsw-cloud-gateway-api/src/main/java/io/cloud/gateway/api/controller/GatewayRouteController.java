package io.cloud.gateway.api.controller;

import io.cloud.auth.common.entity.BaseUser;
import io.cloud.auth.common.feign.UserFeign;
import io.cloud.auth.common.util.LoginUser;
import io.cloud.auth.common.util.LoginUtil;
import io.cloud.data.annotation.WswRestController;
import io.cloud.data.group.Save;
import io.cloud.data.group.Update;
import io.cloud.data.util.NullUtil;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
import io.cloud.gateway.api.cache.RedisCacheRoute;
import io.cloud.gateway.api.service.IGatewayRouteService;
import io.cloud.gateway.common.dtl.RouteDefinition;
import io.cloud.gateway.common.entity.GatewayRoute;
import io.cloud.gateway.common.evt.GatewayRouteEvt;
import io.cloud.gateway.common.evt.GatewayRouteListEvt;
import io.cloud.gateway.common.util.HandleDataUtil;
import io.cloud.gateway.common.vo.GatewayRouteDtlVo;
import io.cloud.gateway.common.vo.GatewayRouteListVo;
import io.cloud.user.common.base.LoginUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: wsw-cloud-servce
 * @description: 前端控制器
 * @author: wsw
 * @create: 2020-06-12 10:38
 **/
@Slf4j
@AllArgsConstructor
@Api(tags = "动态路由")
@WswRestController(path = "/dynamicRoute")
public class GatewayRouteController {

    private IGatewayRouteService gatewayRouteService;

    private RedisCacheRoute redisCacheRoute;

    private LoginUtil loginUtil;

    @GetMapping("/findPageList")
    @ApiOperation(value = "列表", notes = "动态路由列表")
    public Result<List<GatewayRouteListVo>> findPageList(@ApiParam("条件") GatewayRouteListEvt evt) {
        BaseUser user = LoginUser.getUser();
        LoginUserInfo apiUser = loginUtil.getApiUser();
        return gatewayRouteService.findPageList(evt);
    }

    @GetMapping("/findById")
    @ApiOperation(value = "详情", notes = "动态路由详情")
    public Result<GatewayRouteDtlVo> findById(@ApiParam("主键") @RequestParam Long id) {
        return gatewayRouteService.findById(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增", notes = "动态路由新增")
    public Result save(@ApiParam("实体") @Valid @Validated(Save.class) @RequestBody GatewayRouteEvt evt) {
        GatewayRoute gatewayRoute = gatewayRouteService.saveOrUpdate(evt);
        if (NullUtil.isNull(gatewayRoute)) {
            throw new ServiceException(HttpStatus.FAIL);
        }
        RouteDefinition definition = HandleDataUtil.handleData(gatewayRoute);
        redisCacheRoute.saveRoute(Mono.just(definition)).subscribe();
        return R.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "动态路由修改")
    public Result update(@ApiParam("实体") @Valid @Validated(Update.class) @RequestBody GatewayRouteEvt evt) {
        GatewayRoute gatewayNew = gatewayRouteService.saveOrUpdate(evt);
        if (NullUtil.isNull(gatewayNew)) {
            throw new ServiceException(HttpStatus.FAIL);
        }
        RouteDefinition definition = HandleDataUtil.handleData(gatewayNew);
        redisCacheRoute.deleteRoute(Mono.just(definition.getId())).subscribe();
        redisCacheRoute.saveRoute(Mono.just(definition)).subscribe();
        return R.success();
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除", notes = "动态路由删除")
    public Result delete(@ApiParam("主键") @RequestParam Long id) {
        GatewayRoute gateway = gatewayRouteService.delete(id);
        if (NullUtil.isNull(gateway)) {
            throw new ServiceException(HttpStatus.FAIL);
        }
        RouteDefinition definition = HandleDataUtil.handleData(gateway);
        redisCacheRoute.deleteRoute(Mono.just(definition.getId())).subscribe();
        return R.success();
    }
}
