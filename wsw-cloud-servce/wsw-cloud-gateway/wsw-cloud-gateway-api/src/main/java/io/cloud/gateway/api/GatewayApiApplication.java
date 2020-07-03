package io.cloud.gateway.api;

import io.cloud.core.annotation.EnableFeignInterceptor;
import io.cloud.data.annotation.WswSpringCloud;
import io.cloud.mq.annotation.EnableRabbitMq;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @program: wsw-cloud-servce
 * @description: 动态路由
 * @author: wsw
 * @create: 2020-05-12 17:37
 **/
@WswSpringCloud
@EnableRabbitMq
@EnableFeignInterceptor
@SpringCloudApplication
public class GatewayApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApiApplication.class, args);
    }


}
