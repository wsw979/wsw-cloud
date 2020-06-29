package io.cloud.auth.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-28 16:52
 **/
@Data
public class TokenEntity implements Serializable {

    /**
     * 唯一标识
     */
    private String id;
    /**
     * token
     */
    private String token;
    /**
     * 失效事件
     */
    private LocalDateTime invalidDate;

}
