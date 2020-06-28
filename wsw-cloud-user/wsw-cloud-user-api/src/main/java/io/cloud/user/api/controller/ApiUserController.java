package io.cloud.user.api.controller;


import io.cloud.data.annotation.WswRestController;
import io.cloud.exception.result.Result;
import io.cloud.exception.util.R;
import io.cloud.user.api.service.IApiUserService;
import io.cloud.user.common.vo.app.ApiUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
@Slf4j
@AllArgsConstructor
@Api(tags = "APP用户")
@WswRestController(path = "/appUser")
public class ApiUserController {

    private IApiUserService apiUserService;

    @GetMapping(value = "/userName")
    @ApiOperation(value = "获取用户", tags = "根据用户名/邮箱/手机号获取用户")
    public Result<ApiUserVo> getUserByUserName(@RequestParam("userName") String userName){
        return R.success(apiUserService.getUserByUserName(userName));
    }

    @GetMapping(value = "/phone/{phone}")
    @ApiOperation(value = "根据账户名或手机号获取用户", tags = "根据账户名或手机号获取用户")
    public Result<ApiUserVo> getUserByPhone(@PathVariable("phone") String phone){
        return R.success(apiUserService.getUserByPhone(phone));
    }

}
