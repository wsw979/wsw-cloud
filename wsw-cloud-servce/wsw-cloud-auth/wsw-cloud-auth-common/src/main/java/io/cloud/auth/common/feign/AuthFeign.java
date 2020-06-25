package io.cloud.auth.common.feign;

import io.cloud.data.constant.ServiceConstant;
import io.cloud.exception.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-18 14:13
 **/
@FeignClient(value = ServiceConstant.AUTH_API_PATH)
public interface AuthFeign {

    @GetMapping("/checkAuth/test1")
    Result test1();

    @GetMapping("/checkAuth/test2")
    Result test2();

    @GetMapping("/checkAuth/test3")
    Result test3();
}
