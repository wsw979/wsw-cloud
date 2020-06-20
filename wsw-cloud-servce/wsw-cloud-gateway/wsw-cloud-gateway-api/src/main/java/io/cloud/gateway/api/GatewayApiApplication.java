package io.cloud.gateway.api;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.cloud.config.annotation.EnableFeignInterceptor;
import io.cloud.data.annotation.WswSpringCloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Properties;

/**
 * @program: wsw-cloud-servce
 * @description: 动态路由
 * @author: wsw
 * @create: 2020-05-12 17:37
 **/
@WswSpringCloud
@EnableFeignInterceptor
@SpringCloudApplication
public class GatewayApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApiApplication.class, args);
    }


}
