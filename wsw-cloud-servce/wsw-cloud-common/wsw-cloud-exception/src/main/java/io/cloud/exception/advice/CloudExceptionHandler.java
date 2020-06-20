package io.cloud.exception.advice;

import io.cloud.exception.InternalException;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;

import java.util.List;

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
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(Exception e) {
        log.error("系统异常{}", e);
        return R.error(e.getMessage());
    }

    /**
     * 业务
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public Result exceptionHandle(ServiceException e) {
        log.error("业务异常{}", e);
        return R.error(e.getCode(), e.getMsg());
    }

    /**
     * Feign 链路异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NestedServletException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(NestedServletException e) {
        InternalException internalException = (InternalException) e.getCause().getCause();
        log.error("服务异常{}", e);
        return R.error(internalException.getCode(), internalException.getMessage());
    }

    /**
     * 参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public Result exceptionHandle(MissingServletRequestParameterException e) {
        log.error("参数异常{}", e);
        return R.error(HttpStatus.PARAM_ERROR.getCode(), HttpStatus.PARAM_ERROR.getMsg());
    }


    /**
     * 404
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ClassNotFoundException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public Result exceptionHandler(ClassNotFoundException e) {
        log.error("业务异常: {}", e);
        return R.error(HttpStatus.NOT_FOUND.getCode(), HttpStatus.NOT_FOUND.getMsg());
    }

    /**
     * valid 入参校验
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public Result exceptionHandler(MethodArgumentNotValidException e) {
        //打印校验住的所有的错误信息
        StringBuilder sb = new StringBuilder("参数错误：");
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> {
                sb.append("[" + error.getDefaultMessage()).append("!]，");
            });
        }
        sb.deleteCharAt(sb.length() - 1);
        log.error(sb.toString());
        return R.error(HttpStatus.PARAM_ERROR.getCode(), sb.toString());
    }
}
