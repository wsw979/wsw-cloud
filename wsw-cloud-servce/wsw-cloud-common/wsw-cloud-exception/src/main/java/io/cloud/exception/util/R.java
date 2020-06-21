package io.cloud.exception.util;

import io.cloud.exception.constant.ExceptionConstant;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @program: wsw-cloud-servce
 * @description: 返回类控制
 * @author: wsw
 * @create: 2020-05-06 20:14
 **/
public class R {

    /**
     * 返回成功，传入返回体具体出參
     *
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(HttpStatus.SUCCESS.getCode());
        result.setMsg(HttpStatus.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 提供给部分不需要出參的接口
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 提供给部分不需要出參的接口
     *
     * @return
     */
    public static <T> Result<List<T>> page(List<T> list) {
        Result<List<T>> result = new Result();
        result.setCode(HttpStatus.SUCCESS.getCode());
        result.setMsg(HttpStatus.SUCCESS.getMsg());
        result.setData(list);
        return result;
    }

    /**
     * 提供给部分不需要出參的接口
     *
     * @return
     */
    public static <T> Result<T> returnData(T data) {
        Result<T> result = new Result();
        result.setCode(HttpStatus.SUCCESS.getCode());
        result.setMsg(HttpStatus.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 返回异常信息，在已知的范围内
     *
     * @param httpStatus
     * @return
     */
    public static Result error(HttpStatus httpStatus) {
        return error(httpStatus.getCode(), httpStatus.getMsg());
    }

    /**
     * 返回异常信息，在已知的范围内
     *
     * @return
     */
    public static Result error() {
        return error(HttpStatus.FAIL);
    }

    /**
     * 返回异常信息，在已知的范围内
     *
     * @param obj
     * @return
     */
    public static Result error(Object obj) {
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getFields();
        Integer code = null;
        String msg = null;
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                if (fields[i].getName().equals(ExceptionConstant.CODE)) {
                    code = (Integer) fields[i].get(obj);
                }
                if (fields[i].getName().equals(ExceptionConstant.MSG)) {
                    msg = (String) fields[i].get(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return error(HttpStatus.ERROR);
            }
        }
        if (code != null && msg != null) {
            return error(code, msg);
        }
        return error(HttpStatus.ERROR);
    }

    /**
     * 自定义错误信息
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 自定义错误信息
     *
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(HttpStatus.FAIL.getCode());
        result.setMsg(msg);
        return result;
    }
}
