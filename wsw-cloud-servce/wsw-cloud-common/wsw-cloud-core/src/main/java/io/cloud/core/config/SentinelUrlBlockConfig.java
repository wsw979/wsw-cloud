package io.cloud.core.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import io.cloud.core.handler.SentinelUrlBlockHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-24 11:02
 **/
@Configuration
public class SentinelUrlBlockConfig {

    @PostConstruct
    @Bean
    public void init() {
        WebCallbackManager.setUrlBlockHandler(new SentinelUrlBlockHandler());
    }

}
