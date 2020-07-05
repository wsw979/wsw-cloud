package io.cloud.auth.common.evt;

import io.cloud.data.enums.NumEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-30 17:07
 **/
@Data
public class MessageCodeEvt {

    @ApiModelProperty(value = "手机号码")
    private String smsPhone;

    @ApiModelProperty(value = "邮箱号码")
    private String smsEmail;

    @NotNull(message = "验证号码类型必填")
    @ApiModelProperty(value = "验证号码类型（1手机2邮箱）", required = true)
    private Integer smsNumType;

    @NotNull(message = "来源必填")
    @ApiModelProperty(value = "来源（1app2web3wx4admin）", required = true)
    private String smsSource;

    @AssertTrue(message = "手机号必填")
    private boolean isSmsPhone() {
        if (this.smsNumType.equals(NumEnum.ONE.getK())) {
            return StringUtils.isNotEmpty(this.smsPhone);
        }
        return true;
    }

    @AssertTrue(message = "邮箱号必填")
    private boolean isSmsEmail() {
        if (this.smsNumType.equals(NumEnum.TWO.getK())) {
            return StringUtils.isNotEmpty(this.smsEmail);
        }
        return true;
    }

}
