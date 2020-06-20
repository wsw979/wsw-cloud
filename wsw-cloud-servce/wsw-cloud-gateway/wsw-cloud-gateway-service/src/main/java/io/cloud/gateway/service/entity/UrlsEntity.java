package io.cloud.gateway.service.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-08 11:11
 **/
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "spring.skip")
public class UrlsEntity {

    @ApiModelProperty("排除的url")
    private String urls;

}
