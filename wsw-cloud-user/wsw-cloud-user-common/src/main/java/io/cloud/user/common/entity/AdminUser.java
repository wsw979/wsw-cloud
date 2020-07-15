package io.cloud.user.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.cloud.data.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN用户
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_admin_user")
@ApiModel(value = "AdminUser对象", description = "ADMIN用户")
public class AdminUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "凭证")
    private String identifier;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "激活时间")
    private LocalDateTime activate;

    @ApiModelProperty(value = "是否有效（0未激活1已激活2已禁用）")
    private Integer isValid;


}
