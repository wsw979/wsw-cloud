package io.cloud.sms.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 17:33
 **/
@Data
public class Sms {

    @ApiModelProperty(value = "秘钥key")
    private String accessKey;

    @ApiModelProperty(value = "秘钥")
    private String accessSecret;

    @ApiModelProperty(value = "签名名称")
    private String signName;

    @ApiModelProperty(value = "模板code")
    private String templateCode;

}
