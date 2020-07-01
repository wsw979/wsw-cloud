package io.cloud.auth.api;

import io.cloud.core.annotation.EnableFeignInterceptor;
import io.cloud.data.annotation.WswSpringCloud;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @program: wsw-cloud-servce
 * @description: 权限
 * @author: wsw
 * @create: 2020-05-12 17:37
 **/
@WswSpringCloud
@EnableFeignInterceptor
@SpringCloudApplication
public class AuthApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApiApplication.class);
    }

}
