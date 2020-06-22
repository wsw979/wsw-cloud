package io.cloud.auth.common.feign.fallback;

import com.netflix.client.ClientException;
import feign.Request;
import feign.RetryableException;
import feign.hystrix.FallbackFactory;
import io.cloud.auth.common.evt.JwtUserEvt;
import io.cloud.auth.common.feign.AuthFeign;
import io.cloud.auth.common.vo.JwtUserVo;
import io.cloud.exception.HytrixException;
import io.cloud.exception.InternalException;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
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
            public Result select1(JwtUserEvt evt) {
                log.error("对象{}服务降级{}", evt,throwable);
                if (throwable instanceof InternalException) {
                    InternalException exception = (InternalException) throwable;
                    throw new InternalException(exception.getCode(), exception.getMsg());
                }  else {
                    throw new HytrixException(HttpStatus.SERVICE_ERROR.getMsg());
                }
            }

            @Override
            public Result select2() {
                log.error("服务降级{}", throwable);
                if (throwable instanceof InternalException) {
                    InternalException exception = (InternalException) throwable;
                    throw new InternalException(exception.getCode(), exception.getMsg());
                }  else {
                    throw new HytrixException(HttpStatus.SERVICE_ERROR.getMsg());
                }
            }

            @Override
            public Result select3() {
                log.error("服务降级{}", throwable);
                if (throwable instanceof InternalException) {
                    InternalException exception = (InternalException) throwable;
                    throw new InternalException(exception.getCode(), exception.getMsg());
                }  else {
                    throw new HytrixException(HttpStatus.SERVICE_ERROR.getMsg());
                }
            }
        };
    }
}
