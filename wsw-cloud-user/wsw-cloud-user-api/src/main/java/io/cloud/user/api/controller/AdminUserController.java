package io.cloud.user.api.controller;


import io.cloud.data.annotation.WswRestController;
import io.cloud.exception.result.Result;
import io.cloud.exception.util.R;
import io.cloud.user.api.service.IAdminUserService;
import io.cloud.user.common.base.LoginUserInfo;
import io.cloud.user.common.vo.app.AdminUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * ADMIN用户 前端控制器
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
@Slf4j
@AllArgsConstructor
@Api(tags = "ADMIN用户")
@WswRestController(path = "/adminUser")
public class AdminUserController {

    private IAdminUserService adminUserService;

    private HttpServletRequest request;

    @GetMapping(value = "/loginUser")
    @ApiOperation(value = "登录用户", tags = "根据ID获取登录用户")
    public Result<LoginUserInfo> loginAdminUser() {
        return R.success(adminUserService.loginAdminUser(request));
    }

    @GetMapping("/phone/{phone}")
    @ApiOperation(value = "根据手机号获取用户", tags = "根据手机号获取用户")
    public Result<AdminUserVo> getUserByPhone(@PathVariable("phone") String phone) {
        return R.success(adminUserService.getUserByPhone(phone));
    }

}
