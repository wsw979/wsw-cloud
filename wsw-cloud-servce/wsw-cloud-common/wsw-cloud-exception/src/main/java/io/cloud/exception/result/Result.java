package io.cloud.exception.result;

import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description: 全局返回
 * @author: wsw
 * @create: 2020-05-06 20:11
 **/
@Data
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

}
