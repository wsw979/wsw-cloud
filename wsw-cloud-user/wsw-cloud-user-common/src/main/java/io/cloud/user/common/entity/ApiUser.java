package io.cloud.user.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
 * 用户
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
@Data
@Accessors(chain = true)
@TableName("c_api_user")
@ApiModel(value="ApiUser对象", description="用户")
public class ApiUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别（1男2女）")
    private Integer sex;

    @ApiModelProperty(value = "头像url")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "个人简介")
    private String introduction;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "注册时间")
    private LocalDateTime register;

    @ApiModelProperty(value = "是否有效（0注销1正常2禁用）")
    private Integer isValid;

    @ApiModelProperty(value = "是否会员（0否1是）")
    private Integer isVip;

    @ApiModelProperty(value = "会员开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "会员结束时间")
    private LocalDateTime endTime;

}
