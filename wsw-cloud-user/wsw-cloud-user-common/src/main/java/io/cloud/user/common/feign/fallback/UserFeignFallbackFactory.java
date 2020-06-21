package io.cloud.user.common.feign.fallback;

import feign.Request;
import feign.RetryableException;
import feign.hystrix.FallbackFactory;
import io.cloud.exception.InternalException;
import io.cloud.exception.result.Result;
import io.cloud.user.common.feign.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: wsw-cloud-user
 * @description:
 * @author: wsw
 * @create: 2020-06-17 10:18
 **/
@Slf4j
@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserFeign> {

    @Override
    public UserFeign create(Throwable throwable) {
        return new UserFeign() {
            @Override
            public Result getUserInfo(Long id) {
                log.error("获取用户失败{}服务降级{}",id,throwable);
                if (throwable instanceof InternalException) {
                    InternalException exception = (InternalException) throwable;
                    throw new InternalException(exception.getCode(), exception.getMsg());
                } else if (throwable instanceof RetryableException) {
                    throw new RetryableException(throwable.getMessage(), Request.HttpMethod.GET, throwable, new Date());
                } else {
                    throw new RuntimeException(throwable.getMessage());
                }
            }
        };
    }

}
