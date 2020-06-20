package io.cloud.data.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-05-06 21:17
 **/
@Data
public class BasePage {

    @ApiModelProperty(value = "当前页码")
    private Integer pageIndex = 1;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 10;

}
