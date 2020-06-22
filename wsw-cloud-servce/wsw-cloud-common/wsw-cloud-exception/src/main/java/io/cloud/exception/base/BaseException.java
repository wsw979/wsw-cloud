package io.cloud.exception.base;

import io.cloud.exception.status.HttpStatus;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-22 09:13
 **/
@Data
public class BaseException extends RuntimeException {

    private Integer code;

    private String msg;


    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(HttpStatus status) {
        super(status.getMsg());
        this.code = status.getCode();
        this.msg = status.getMsg();
    }

}
