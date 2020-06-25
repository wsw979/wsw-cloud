package io.cloud.core.fallback;

import feign.Target;
import feign.hystrix.FallbackFactory;
import io.cloud.core.fallback.FunFeignFallback;
import lombok.AllArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-24 16:42
 **/
@AllArgsConstructor
public class FunFallbackFactory<T> implements FallbackFactory<T> {

    private final Target<T> target;

    @Override
    @SuppressWarnings("unchecked")
    public T create(Throwable throwable) {
        final Class<T> targetType = target.type();
        final String targetName = target.name();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetType);
        enhancer.setUseCache(true);
        enhancer.setCallback(new FunFeignFallback<>(targetType, targetName, throwable));
        return (T) enhancer.create();
    }

}
