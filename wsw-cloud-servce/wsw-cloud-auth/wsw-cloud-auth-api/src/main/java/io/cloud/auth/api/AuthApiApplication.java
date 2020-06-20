package io.cloud.auth.api;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import io.cloud.config.annotation.EnableFeignInterceptor;
import io.cloud.data.annotation.WswSpringCloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

import java.util.Properties;

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
