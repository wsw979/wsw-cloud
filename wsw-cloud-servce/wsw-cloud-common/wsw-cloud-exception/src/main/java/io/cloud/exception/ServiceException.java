package io.cloud.exception;

import io.cloud.exception.base.BaseException;
import io.cloud.exception.status.HttpStatus;
import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description: 业务异常类
 * @author: wsw
 * @create: 2020-05-06 20:43
 **/
@Data
public class ServiceException extends BaseException {

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(Integer code, String msg) {
        super(code, msg);
    }

    public ServiceException(HttpStatus status) {
        super(status.getCode(), status.getMsg());
    }

}
