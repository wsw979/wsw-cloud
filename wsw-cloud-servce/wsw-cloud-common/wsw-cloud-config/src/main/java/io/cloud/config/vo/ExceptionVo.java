package io.cloud.config.vo;

import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-18 15:10
 **/
@Data
public class ExceptionVo {

    private Long timestamp;

    private Integer status;

    private String exception;

    private String message;

    private String path;

    private String error;

}
