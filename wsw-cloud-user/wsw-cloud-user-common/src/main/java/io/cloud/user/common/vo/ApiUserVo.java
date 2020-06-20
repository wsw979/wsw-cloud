package io.cloud.user.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @program: wsw-cloud-user
 * @description:
 * @author: wsw
 * @create: 2020-06-17 10:38
 **/
@Data
public class ApiUserVo {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "手机号")
    private String mobile;

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

    @ApiModelProperty(value = "是否会员（0否1是）")
    private Integer isVip;

    @ApiModelProperty(value = "会员开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "会员结束时间")
    private LocalDateTime endTime;

}
