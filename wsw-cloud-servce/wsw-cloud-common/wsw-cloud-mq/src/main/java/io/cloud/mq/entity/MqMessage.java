package io.cloud.mq.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-02 22:08
 **/
@Data
public class MqMessage implements Serializable {

    /**
     * 消息ID
     */
    protected String key = UUID.randomUUID().toString();
    /**
     * 单个消息过期时间
     */
    protected String expiration;

}
