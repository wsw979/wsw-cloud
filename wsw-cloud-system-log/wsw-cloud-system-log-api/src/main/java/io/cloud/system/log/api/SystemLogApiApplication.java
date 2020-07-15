package io.cloud.system.log.api;

import io.cloud.core.annotation.EnableFeignInterceptor;
import io.cloud.data.annotation.WswSpringCloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @program: wsw-cloud-servce
 * @description: 系统日志
 * @author: wsw
 * @create: 2020-07-15 17:37
 **/
@WswSpringCloud
@EnableFeignInterceptor
@SpringCloudApplication
public class SystemLogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemLogApiApplication.class, args);
    }

}
