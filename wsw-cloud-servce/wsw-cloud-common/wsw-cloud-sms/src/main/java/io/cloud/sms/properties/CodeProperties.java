package io.cloud.sms.properties;/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 19:27
 **/

import io.cloud.sms.dtl.Sign;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.context.annotation.ScopedProxyMode.DEFAULT;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 19:27
 **/
@Data
@Component
@ConfigurationProperties(prefix = "ali.message")
@RefreshScope(proxyMode = DEFAULT)
public class CodeProperties {

    @ApiModelProperty(value = "过期时间（秒）")
    private Integer overdueTime;

    @ApiModelProperty(value = "秘钥id")
    private String accessKey;

    @ApiModelProperty(value = "秘钥")
    private String accessSecret;

    @ApiModelProperty(value = "模板列表")
    private List<Sign> templateList;

}
