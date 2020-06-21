package io.cloud.core.annotation;

import io.cloud.core.config.FeignInterceptorConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: wsw-cloud-servce
 * @description: 开启feign拦截器传递数据给下游服务，包含基础数据和http的相关数据
 * @author: wsw
 * @create: 2020-06-01 15:53
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({FeignInterceptorConfig.class})
public @interface EnableFeignInterceptor {

}
