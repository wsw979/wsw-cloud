package io.cloud.user.api;

import io.cloud.core.annotation.EnableFeignInterceptor;
import io.cloud.data.annotation.WswSpringCloud;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

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

}
