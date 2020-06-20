package io.cloud.exception.advice;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.FeignException;
import feign.RetryableException;
import io.cloud.exception.InternalException;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletResponse;
import java.net.SocketTimeoutException;

/**
 * @program: wsw-cloud-servce
 * @description: 全局异常处理器
 * @author: wsw
 * @create: 2020-05-06 20:09
 **/
@Slf4j
@RestControllerAdvice
public class CloudExceptionHandler {

    /**
     * 全局
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandle(HttpServletResponse response, Exception e) {
        response.setStatus(HttpStatus.ERROR.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        log.error("系统异常{}",e);
        return R.error(e.getMessage());
    }

    /**
     * 业务
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    public Result exceptionHandle(HttpServletResponse response, ServiceException e) {
        response.setStatus(HttpStatus.ERROR.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        log.error("业务异常{}",e);
        return R.error(e.getCode(),e.getMsg());
    }

    /**
     * Feign 链路异常
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(value = NestedServletException.class)
    public Result exceptionHandle(HttpServletResponse response, NestedServletException e) {
        response.setStatus(HttpStatus.ERROR.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        InternalException internalException = (InternalException) e.getCause().getCause();
        log.error("服务异常{}",e);
        return R.error(internalException.getCode(),internalException.getMessage());
    }

    /**
     * 参数异常
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result exceptionHandle(HttpServletResponse response, MissingServletRequestParameterException e){
        response.setStatus(HttpStatus.ERROR.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        log.error("参数异常{}",e);
        return R.error(HttpStatus.PARAM_ERROR.getCode(),HttpStatus.PARAM_ERROR.getMsg());
    }


}
