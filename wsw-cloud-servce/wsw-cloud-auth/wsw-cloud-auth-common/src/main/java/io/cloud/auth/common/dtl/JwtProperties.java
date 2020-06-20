package io.cloud.auth.common.dtl;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-01 16:16
 **/
@Data
@Component
@ConfigurationProperties(prefix = "jwt.config")
public class JwtProperties {

    @ApiModelProperty(value = "请求头")
    private String header;

    @ApiModelProperty(value = "头")
    private String authHeader;

    @ApiModelProperty(value = "秘钥")
    private String secretKey;

    @ApiModelProperty(value = "过期时间（秒）")
    private Integer overdueTime;

    @ApiModelProperty(value = "刷新时间（秒）")
    private Integer refreshTime;

    @ApiModelProperty(value = "是否允许自动刷新")
    private Boolean isRefresh;

}
