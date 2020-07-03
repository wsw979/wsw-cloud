package io.cloud.log.dtl;

import lombok.Data;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-02 17:07
 **/
@Data
public class CodeMsg {

    private Integer code;

    private String msg;


    public CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
