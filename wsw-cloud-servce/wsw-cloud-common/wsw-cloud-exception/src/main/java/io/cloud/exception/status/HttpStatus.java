package io.cloud.exception.status;

/**
 * @program: wsw-cloud-servce
 * @description: Http Code
 * @author: wsw
 * @create: 2020-05-06 20:15
 **/
public enum HttpStatus {


    /**
     * 系统
     */
    ERROR(500, "系统异常"),
    SUCCESS(200, "操作成功"),
    FAIL(100, "操作失败"),
    NOT_FOUND(404, "资源不存在"),
    SERVICE_ERROR(404,"服务开小差了！"),
    SERVICE_TIME_ERROR(300,"服务异常，请稍后重试！"),
    FLOW(429,"访问次数过多"),
    PARAM_ERROR(308,"参数异常"),


    /**
     * 权限
     */
    NOT_AUTH(403, "权限不足"),
    TOKEN_ERROR(400, "登录已失效，请重新登录"),
    ;

    private Integer code;

    private String msg;

    HttpStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}