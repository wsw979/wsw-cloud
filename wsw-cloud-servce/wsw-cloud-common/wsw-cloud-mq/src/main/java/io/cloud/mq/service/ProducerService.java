package io.cloud.mq.service;

import io.cloud.mq.entity.MqMessage;

/**
 * @program: wsw-cloud-servce
 * @description: 发送消息
 * @author: wsw
 * @create: 2020-07-02 22:08
 **/
public interface ProducerService {

    /**
     * 发送消息
     *
     * @param content
     * @param exchangeName
     * @param routingKey
     */
    void sendMsg(MqMessage content, String exchangeName, String routingKey);

}
