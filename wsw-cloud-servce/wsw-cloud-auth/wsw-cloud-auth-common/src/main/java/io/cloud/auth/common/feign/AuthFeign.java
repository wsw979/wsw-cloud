package io.cloud.auth.common.feign;

import io.cloud.auth.common.feign.fallback.AuthFeignFallbackFactory;
import io.cloud.auth.common.vo.JwtUserVo;
import io.cloud.data.constant.ServiceConstant;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-18 14:13
 **/
@FeignClient(value = ServiceConstant.AUTH_API_PATH, fallbackFactory = AuthFeignFallbackFactory.class)
@RequestMapping(value = "/checkAuth")
public interface AuthFeign {

    /**
     * 获取用户
     */
    @GetMapping("/select1")
    Result select1();

    /**
     * 获取用户
     */
    @GetMapping("/select2")
    Result select2();


    /**
     * 获取用户
     */
    @GetMapping("/select3")
    Result select3();
}
