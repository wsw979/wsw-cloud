package io.cloud.exception;

import lombok.Data;

import java.util.Date;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-19 13:45
 **/
@Data
public class ExceptionInfo {

    private String timestamp;
    private Integer status;
    private String error;
    private String exception;
    private String message;
    private String path;

}
