package io.cloud.user.api.controller;


import io.cloud.data.annotation.WswRestController;
import io.cloud.exception.result.Result;
import io.cloud.exception.util.R;
import io.cloud.user.api.service.IRoleService;
import io.cloud.user.common.evt.admin.RoleEvt;
import io.cloud.user.common.vo.app.RoleListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
@Slf4j
@AllArgsConstructor
@Api(tags = "角色")
@WswRestController(path = "/role")
public class RoleController {

    private IRoleService roleService;

    @GetMapping("/findListByUserId/{userId}")
    @ApiOperation(value = "获取用户角色", tags = "根据用户ID获取全部角色")
    public Result<List<RoleListVo>> findListByUserId(@PathVariable("userId") Long userId) {
        return R.success(roleService.findListByUserId(userId));
    }

    @PostMapping("/testSuccess")
    @ApiOperation(value = "测试新增角色", tags = "正确")
    public Result testSuccess(@ApiParam("实体") @Valid @RequestBody RoleEvt evt) {
        return roleService.testSuccess(evt);
    }

    @PostMapping("/testError")
    @ApiOperation(value = "测试新增角色", tags = "错误")
    public Result testError(@ApiParam("实体") @Valid @RequestBody RoleEvt evt) {
        return roleService.testError(evt);
    }

}
