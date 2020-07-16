package io.cloud.system.log.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.cloud.data.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wsw
 * @since 2020-07-15
 */
@Data
@Accessors(chain = true)
@TableName("c_system_log")
@ApiModel(value="SystemLog对象", description="")
public class SystemLog {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "全局日志id")
    private Long traceId;

    @ApiModelProperty(value = "是否入口（0否1是）")
    private Integer isEntrance;

    @ApiModelProperty(value = "应用名")
    private String applicationName;

    @ApiModelProperty(value = "类名")
    private String className;

    @ApiModelProperty(value = "方法名")
    private String methodName;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "操作信息")
    private String operation;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
