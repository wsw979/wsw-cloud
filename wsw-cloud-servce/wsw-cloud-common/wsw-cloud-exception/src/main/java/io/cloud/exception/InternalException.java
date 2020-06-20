package io.cloud.exception;

import io.cloud.exception.status.HttpStatus;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-19 13:39
 **/
@Data
public class InternalException extends RuntimeException {

    private Integer code;

    private String msg;

    public InternalException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public InternalException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public InternalException(Integer code, String msg, boolean isSuccess) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public InternalException(HttpStatus status) {
        super(status.getMsg());
        this.code = status.getCode();
        this.msg = status.getMsg();
    }

    public InternalException(HttpStatus status,boolean isSuccess) {
        super(status.getMsg());
        this.code = status.getCode();
        this.msg = status.getMsg();
    }

}
