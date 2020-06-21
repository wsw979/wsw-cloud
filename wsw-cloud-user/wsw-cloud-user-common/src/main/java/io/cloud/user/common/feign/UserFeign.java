package io.cloud.user.common.feign;

import io.cloud.data.constant.ServiceConstant;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.user.common.entity.ApiUser;
import io.cloud.user.common.feign.fallback.UserFeignFallbackFactory;
import io.cloud.user.common.vo.ApiUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: wsw-cloud-user
 * @description:
 * @author: wsw
 * @create: 2020-06-17 09:39
 **/
@FeignClient(value = ServiceConstant.USER_API_PATH, fallbackFactory = UserFeignFallbackFactory.class, decode404 = true)
public interface UserFeign {

    /**
     * 获取用户
     * @param id
     * @return
     */
    @GetMapping("/api/user/getUserInfo")
    Result<ApiUserVo> getUserInfo(@RequestParam(value = "id") Long id) throws ServiceException;

}
