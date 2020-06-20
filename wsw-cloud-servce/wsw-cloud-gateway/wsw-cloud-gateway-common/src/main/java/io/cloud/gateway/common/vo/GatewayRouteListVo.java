package io.cloud.gateway.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-13 16:54
 **/
@Data
public class GatewayRouteListVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "服务名称")
    private String serviceId;

    @ApiModelProperty(value = "路由")
    private String serviceUrl;

    @ApiModelProperty(value = "访问路径")
    private String predicates;

    @ApiModelProperty(value = "过滤")
    private String filters;

    @TableField("`order`")
    @ApiModelProperty(value = "排序")
    private String order;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
