package io.cloud.gateway.service;

import io.cloud.core.annotation.EnableFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"io.cloud.**"})
@EnableFeignInterceptor
@EnableAutoConfiguration(exclude = {ReactiveUserDetailsServiceAutoConfiguration.class})
@SpringCloudApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
