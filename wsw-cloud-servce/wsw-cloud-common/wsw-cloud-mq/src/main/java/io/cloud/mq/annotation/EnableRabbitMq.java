package io.cloud.mq.annotation;

import io.cloud.mq.config.RabbitConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: wsw-cloud-servce
 * @description: 开启MQ
 * @author: wsw
 * @create: 2020-07-02 22:06
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(RabbitConfig.class)
public @interface EnableRabbitMq {
}
