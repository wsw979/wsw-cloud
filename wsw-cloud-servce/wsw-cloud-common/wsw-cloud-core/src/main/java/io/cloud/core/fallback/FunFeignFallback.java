package io.cloud.core.fallback;

import feign.FeignException;
import io.cloud.exception.HytrixException;
import io.cloud.exception.InternalException;
import io.cloud.exception.status.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-24 16:26
 **/
@Slf4j
@AllArgsConstructor
public class FunFeignFallback<T> implements MethodInterceptor {

    private final Class<T> targetType;
    private final String targetName;
    private final Throwable cause;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String errorMessage = cause.getMessage();
        log.error("FunFeignFallback:[{}.{}] serviceId:[{}] message:[{}]", targetType.getName(), method.getName(), targetName, errorMessage);
        if (!(cause instanceof FeignException)) {
            if (cause instanceof InternalException) {
                InternalException exception = (InternalException) cause;
                throw new InternalException(exception.getCode(), exception.getMessage());
            } else {
                throw new HytrixException(HttpStatus.SERVICE_ERROR.getMsg());
            }
        }
        throw new HytrixException(HttpStatus.SERVICE_ERROR.getMsg());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FunFeignFallback<?> that = (FunFeignFallback<?>) obj;
        return targetType.equals(that.targetType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(targetType);
    }
}
