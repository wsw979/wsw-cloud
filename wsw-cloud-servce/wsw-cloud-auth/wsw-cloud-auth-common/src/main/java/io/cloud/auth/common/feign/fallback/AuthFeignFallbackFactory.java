package io.cloud.auth.common.feign.fallback;

import com.netflix.client.ClientException;
import feign.Request;
import feign.RetryableException;
import feign.hystrix.FallbackFactory;
import io.cloud.auth.common.feign.AuthFeign;
import io.cloud.auth.common.vo.JwtUserVo;
import io.cloud.exception.InternalException;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
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
        return new AuthFeign() {
            @Override
            public Result select1() {
                log.error("服务降级{}", throwable);
                if (throwable instanceof InternalException) {
                    InternalException exception = (InternalException) throwable;
                    throw new InternalException(exception.getCode(), exception.getMsg());
                } else if (throwable instanceof RetryableException) {
                    throw new RetryableException(throwable.getMessage(), Request.HttpMethod.GET, throwable, new Date());
                } else {
                    throw new RuntimeException(throwable.getMessage());
                }
            }

            @Override
            public Result select2() {
                log.error("服务降级{}", throwable);
                if (throwable instanceof InternalException) {
                    InternalException exception = (InternalException) throwable;
                    throw new InternalException(exception.getCode(), exception.getMsg());
                } else if (throwable instanceof RetryableException) {
                    throw new InternalException(HttpStatus.SERVICE_ERROR);
                } else if (throwable.getCause() instanceof ClientException) {
                    throw new InternalException(HttpStatus.SERVICE_ERROR);
                } else {
                    throw new InternalException(HttpStatus.ERROR);
                }
            }

            @Override
            public Result select3() {
                log.error("服务降级{}", throwable);
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
