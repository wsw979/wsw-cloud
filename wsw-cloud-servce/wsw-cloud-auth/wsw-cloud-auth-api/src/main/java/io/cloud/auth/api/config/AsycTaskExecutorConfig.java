package io.cloud.auth.api.config;

import io.cloud.core.config.DefaultAsycTaskConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @program: wsw-cloud-servce
 * @description: 线程池配置、启用异步
 * @author: wsw
 * @create: 2020-06-21 00:19
 **/
@Configuration
public class AsycTaskExecutorConfig extends DefaultAsycTaskConfig {
}
