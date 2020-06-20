package io.cloud.data.base;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program: wsw-cloud-servce
 * @description: 全局父类， 公共字段
 * @author: wsw
 * @create: 2020-05-02 10:52
 **/
@Data
public class BaseEntity {

    @ApiModelProperty(value = "创建人id")
    private Long createId;

    @ApiModelProperty(value = "修改人id")
    private Long updateId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

}
