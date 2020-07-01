package io.cloud.auth.common.feign;

import io.cloud.auth.common.feign.fallback.UserFeignFallbackFactory;
import io.cloud.data.constant.ServiceConstant;
import io.cloud.exception.result.Result;
import io.cloud.user.common.base.LoginUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-18 14:13
 **/
@FeignClient(value = ServiceConstant.USER_API_PATH, fallbackFactory = UserFeignFallbackFactory.class, decode404 = true)
public interface UserFeign {

    @GetMapping(value = "/appUser/loginUser")
    Result<LoginUserInfo> loginApiUser();

    @GetMapping(value = "/adminUser/loginUser")
    Result<LoginUserInfo> loginAdminUser();
}
