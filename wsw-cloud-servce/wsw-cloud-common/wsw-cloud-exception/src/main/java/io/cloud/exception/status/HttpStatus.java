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
    SERVICE_ERROR(404, "连接超时，稍后重试"),
    SERVICE_TIME_ERROR(300, "服务异常，请稍后重试"),
    FLOW(429, "访问次数过多"),
    PARAM_ERROR(308, "参数异常"),


    /**
     * 权限
     */
    CLIENT_ID(403, "No client with requested id null"),
    PASSWORD_ERROR(400, "账号不存在或密码错误"),
    AUTH_ERROR(403, "认证失败"),
    NOT_AUTH(403, "权限不足"),
    LOGIN_USER_ERROR(500, "用户获取失败，请重新登录或稍后再试"),
    TOKEN_ERROR(400, "登录已失效，请重新登录"),

    /**
     * 业务
     */
    MESSAGE_CODE_ERROR(1001, "短信发送失败"),
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
