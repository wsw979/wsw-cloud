package io.cloud.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 16:39
 **/
@Data
@Accessors(chain = true)
@TableName("c_code_message")
@ApiModel(value = "CodeMessage对象", description = "短信验证码")
public class CodeMessage {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "验证码")
    private String smsCode;

    @ApiModelProperty(value = "手机号码")
    private String smsPhone;

    @ApiModelProperty(value = "邮箱号码")
    private String smsEmail;

    @ApiModelProperty(value = "验证号码类型（1手机2邮箱）")
    private Integer smsNumType;

    @ApiModelProperty(value = "验证码类型（1登录2注册3修改密码）")
    private Integer smsType;

    @ApiModelProperty(value = "来源（1app2web3wx4admin）")
    private Integer smsSource;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
