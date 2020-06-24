package io.cloud.auth.common.feign.fallback;

import feign.hystrix.FallbackFactory;
import io.cloud.auth.common.feign.AuthFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-18 14:16
 **/
@Slf4j
@Component
public class AuthFeignFallbackFactory implements FallbackFactory<AuthFeign> {

    @Override
    public AuthFeign create(Throwable throwable) {
        return null;
    }
}
