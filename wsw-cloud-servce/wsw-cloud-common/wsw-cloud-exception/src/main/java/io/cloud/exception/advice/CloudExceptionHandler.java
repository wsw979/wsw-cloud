package io.cloud.exception.advice;

import feign.RetryableException;
import io.cloud.exception.HytrixException;
import io.cloud.exception.InternalException;
import io.cloud.exception.ServiceException;
import io.cloud.exception.constant.ExceptionConstant;
import io.cloud.exception.result.Result;
import io.cloud.exception.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.NestedServletException;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.UndeclaredThrowableException;
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(Exception e) {
        log.error("系统异常{}", e);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(NullPointerException e) {
        log.error("系统异常{}", e);
        return R.error(HttpStatus.NOT_FOUND.value(), ExceptionConstant.NULL);
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(ServiceException e) {
        log.error("业务异常{}", e);
        return R.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(InternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(InternalException e) {
        log.error("服务异常{}", e);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(HytrixException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(HytrixException e) {
        log.error("服务异常{}", e);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 重试异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RetryableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(RetryableException e) {
        log.error("服务异常{}", e);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionConstant.FAIL);
    }

    /**
     * 下游服务异常，sentinel抛出的熔断
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(UndeclaredThrowableException e) {
        log.error("服务异常{}", e);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionConstant.TIME);
    }

    /**
     * feign 配置了 FallbackFactory 熔断后抛出的包装异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NestedServletException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(NestedServletException e) {
        log.error("服务异常{}", e);
        Throwable rootCause = e.getRootCause();
        Throwable cause = e.getCause().getCause();
        if (cause instanceof HytrixException) {
            HytrixException hytrix = (HytrixException) cause;
            return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), hytrix.getMessage());
        }
        if (cause instanceof InternalException) {
            InternalException internal = (InternalException) cause;
            return R.error(internal.getCode(), internal.getMsg());
        }
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionConstant.ERROR);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(Throwable e) {
        log.error("服务异常{}", e);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHandle(IllegalArgumentException e) {
        log.error("参数异常{}", e);
        return R.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHandle(MissingServletRequestParameterException e) {
        log.error("参数异常{}", e);
        return R.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHandle(MethodArgumentTypeMismatchException e) {
        log.error("参数异常{}", e);
        return R.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHandle(ConstraintViolationException e) {
        log.error("参数异常{}", e);
        return R.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(NoHandlerFoundException e) {
        log.error("系统异常{}", e);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(HttpMessageNotReadableException e) {
        log.error("系统异常{}", e);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result exceptionHandle(HttpRequestMethodNotSupportedException e) {
        log.error("系统异常{}", e);
        return R.error(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result exceptionHandle(HttpMediaTypeNotSupportedException e) {
        log.error("系统异常{}", e);
        return R.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHandle(MethodArgumentNotValidException e) {
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
        return R.error(HttpStatus.BAD_REQUEST.value(), sb.toString());
    }
}
