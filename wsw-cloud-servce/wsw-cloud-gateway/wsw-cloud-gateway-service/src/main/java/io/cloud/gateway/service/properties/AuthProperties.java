package io.cloud.gateway.service.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.springframework.context.annotation.ScopedProxyMode.DEFAULT;

/**
 * <p>
 *  网关权限配置项
 * </p>
 *
 * @author: Haifeng
 * @date: 2020-05-03
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "gateway.oauth")
@RefreshScope(proxyMode = DEFAULT)
public class AuthProperties {
    /**
     * 不需要鉴权的资源
     */
    private List<String> ignoredList;

    /**
     * 微服务前缀
     */
    private List<String> servicePrefix;

}
