package io.cloud.user.api;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import io.cloud.config.annotation.EnableFeignInterceptor;
import io.cloud.data.annotation.WswSpringCloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

/**
 * @program: wsw-cloud-servce
 * @description: 用户
 * @author: wsw
 * @create: 2020-05-12 17:37
 **/
@WswSpringCloud
@EnableFeignInterceptor
@SpringCloudApplication
public class UserApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }

    // 注解支持的配置Bean
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
