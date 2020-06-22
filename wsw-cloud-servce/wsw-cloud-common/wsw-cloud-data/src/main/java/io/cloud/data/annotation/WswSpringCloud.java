package io.cloud.data.annotation;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.AliasFor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.*;

/**
 * @program: wsw-cloud-servce
 * @description: 常用自定义注解 -多合一
 * @author: wsw
 * @create: 2020-05-06 15:35
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableAspectJAutoProxy
@EnableFeignClients
@EnableScheduling
@EnableAsync
@ComponentScan
public @interface WswSpringCloud {


    @AliasFor(
            annotation = ComponentScan.class,
            attribute = "basePackages"
    )
    String[] scanBasePackages() default {"io.cloud.**"};

    @AliasFor(
            annotation = EnableFeignClients.class,
            attribute = "basePackages"
    )
    String[] basePackages() default {"io.cloud.**"};
}
