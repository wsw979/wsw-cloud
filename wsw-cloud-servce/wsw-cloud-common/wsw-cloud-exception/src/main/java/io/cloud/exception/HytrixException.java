package io.cloud.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * @program: wsw-cloud-servce
 * @description: feign client 避免熔断异常处理
 * @author: wsw
 * @create: 2020-06-22 09:17
 **/
public class HytrixException extends HystrixBadRequestException  {

    private static final long serialVersionUID = -8410969487701925482L;

    public HytrixException(String msg) {
        super(msg);
    }

    public HytrixException(Exception e){
        this(e.getMessage());
    }
}
