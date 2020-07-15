package io.cloud.user.api.controller;


import io.cloud.data.annotation.WswRestController;
import io.cloud.exception.result.Result;
import io.cloud.exception.util.R;
import io.cloud.user.api.service.IPermissionService;
import io.cloud.user.common.vo.app.PermissionListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
@Slf4j
@AllArgsConstructor
@Api(tags = "权限")
@WswRestController(path = "/permission")
public class PermissionController {

    private IPermissionService permissionService;

    @GetMapping("/findListByUserId/{userId}")
    @ApiOperation(value = "获取用户权限", tags = "根据用户ID获取全部权限")
    public Result<List<PermissionListVo>> findListByUserId(@PathVariable("userId") Long userId) {
        return R.success(permissionService.findListByUserId(userId));
    }

}
