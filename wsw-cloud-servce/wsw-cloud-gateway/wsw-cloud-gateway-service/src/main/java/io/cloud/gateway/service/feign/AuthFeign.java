package io.cloud.gateway.service.feign;

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
 * @create: 2020-06-01 15:53
 **/
@FeignClient(value = ServiceConstant.AUTH_API_PATH)
@RequestMapping(value = "/checkAuth")
public interface AuthFeign {

    /**
     * 获取令牌
     * @param vo
     * @return
     */
    @PostMapping("/getJwt")
    public Result getJwt(@RequestBody JwtUserVo vo) throws ServiceException;

    /**
     * 效验令牌
     * @param token
     * @return
     */
    @GetMapping("/checkJwt")
    public Result checkJwt(@RequestParam(value = "token",required = false) String token) throws ServiceException;

}
