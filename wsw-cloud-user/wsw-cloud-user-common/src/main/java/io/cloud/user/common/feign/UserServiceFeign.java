package io.cloud.user.common.feign;

import io.cloud.data.constant.ServiceConstant;
import io.cloud.exception.result.Result;
import io.cloud.user.common.evt.admin.RoleEvt;
import io.cloud.user.common.vo.app.AdminUserVo;
import io.cloud.user.common.vo.app.ApiUserVo;
import io.cloud.user.common.vo.app.PermissionListVo;
import io.cloud.user.common.vo.app.RoleListVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: wsw-cloud-user
 * @description:
 * @author: wsw
 * @create: 2020-06-27 21:34
 **/
@FeignClient(value = ServiceConstant.USER_API_PATH)
public interface UserServiceFeign {

    @GetMapping("/appUser/userName")
    Result<ApiUserVo> getUserByUserName(@RequestParam("userName") String userName);

    @GetMapping("/appUser/phone/{phone}")
    Result<ApiUserVo> getAppUserByPhone(@PathVariable("phone") String phone);

    @GetMapping("/adminUser/phone/{phone}")
    Result<AdminUserVo> getAdminUserByPhone(@PathVariable("phone") String phone);

    @GetMapping("/role/findListByUserId/{userId}")
    Result<List<RoleListVo>> findRoleList(@PathVariable("userId") Long userId);

    @GetMapping("/permission/findListByUserId/{userId}")
    Result<List<PermissionListVo>> findPermissionList(@PathVariable("userId") Long userId);

    @PostMapping("/role/testSuccess")
    Result testSuccess(@RequestBody RoleEvt evt);

    @PostMapping("/role/testError")
    Result testError(@RequestBody RoleEvt evt);
}
