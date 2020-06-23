package io.cloud.core.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: wsw-cloud-servce
 * @description: 哨兵AOP ， 使用自定义资源使用注解
 * @author: wsw
 * @create: 2020-06-19 09:10
 **/
@Configuration
public class SentinelAspectConfig {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
