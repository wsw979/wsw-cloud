package io.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-11 08:39
 **/
@Configuration
public class RequestContextConfig {

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
