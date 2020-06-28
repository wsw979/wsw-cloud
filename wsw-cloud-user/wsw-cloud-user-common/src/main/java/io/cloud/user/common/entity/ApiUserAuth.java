package io.cloud.user.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import io.cloud.data.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户认证方式
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_api_user_auth")
@ApiModel(value="ApiUserAuth对象", description="用户认证方式")
public class ApiUserAuth extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "身份类型（1用户名2手机号3邮箱4微信5支付宝）")
    private Integer identityType;

    @ApiModelProperty(value = "唯一标识")
    private String identifier;

    @ApiModelProperty(value = "授权凭证")
    private String credential;

    @ApiModelProperty(value = "是否已经验证（0否1是）")
    private Integer verified;

    @ApiModelProperty(value = "验证时间")
    private LocalDateTime verifiedTime;

    @ApiModelProperty(value = "是否绑定中（0否1是）")
    private Integer isBinding;


}
