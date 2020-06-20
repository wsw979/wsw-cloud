package io.cloud.exception;

import io.cloud.exception.status.HttpStatus;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description: 自定义异常类
 * @author: wsw
 * @create: 2020-05-06 20:43
 **/
@Data
public class ServiceException extends RuntimeException {

    private Integer code;

    private String msg;

    private boolean isSuccess;

    public ServiceException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.isSuccess = true;
    }

    public ServiceException(Integer code, String msg, boolean isSuccess) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.isSuccess = isSuccess;
    }

    public ServiceException(HttpStatus status) {
        super(status.getMsg());
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.isSuccess = true;
    }

    public ServiceException(HttpStatus status,boolean isSuccess) {
        super(status.getMsg());
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.isSuccess = isSuccess;
    }
}
