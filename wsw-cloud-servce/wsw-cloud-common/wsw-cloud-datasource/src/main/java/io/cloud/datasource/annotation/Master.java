package io.cloud.datasource.annotation;

import java.lang.annotation.*;

/**
 * @program: wsw-cloud-servce
 * @description: 数据源--主
 * @author: wsw
 * @create: 2020-04-29 18:05
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Master {
}
