package io.cloud.auth.common.feign;

import io.cloud.auth.common.feign.fallback.AuthFeignFallbackFactory;
import io.cloud.data.constant.ServiceConstant;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-18 14:13
 **/
@FeignClient(value = ServiceConstant.AUTH_API_PATH, fallbackFactory = AuthFeignFallbackFactory.class)
public interface AuthFeign {

}
