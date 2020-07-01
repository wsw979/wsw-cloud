package io.cloud.sms.dtl;/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 19:30
 **/

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 19:30
 **/
@Data
public class Sign {

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "签名名称")
    private String signName;

    @ApiModelProperty(value = "模板code")
    private String templateCode;

}
