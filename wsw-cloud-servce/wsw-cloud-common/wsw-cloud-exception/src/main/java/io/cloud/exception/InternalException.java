package io.cloud.exception;

import io.cloud.exception.base.BaseException;
import io.cloud.exception.status.HttpStatus;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description: 程序异常类
 * @author: wsw
 * @create: 2020-06-19 13:39
 **/
@Data
public class InternalException extends BaseException {

    public InternalException(String msg) {
        super(msg);
    }

    public InternalException(Integer code, String msg) {
        super(code, msg);
    }

    public InternalException(HttpStatus status) {
        super(status.getCode(), status.getMsg());
    }

}
