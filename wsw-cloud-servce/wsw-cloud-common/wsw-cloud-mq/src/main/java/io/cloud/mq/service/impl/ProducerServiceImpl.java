package io.cloud.mq.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.cloud.mq.entity.MqMessage;
import io.cloud.mq.service.ProducerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-02 22:09
 **/
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void sendMsg (MqMessage content, String exchangeName, String routingKey){
        Message message = MessageBuilder.withBody(JSONObject.toJSONString(content).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setCorrelationId(content.getKey())
                .build();
        if(StringUtils.isNotBlank(content.getExpiration())){
            message = MessageBuilder.fromMessage(message).setExpiration(content.getExpiration()).build();
        }
        CorrelationData data = new CorrelationData(content.getKey());
        //存储到redis
        redisTemplate.opsForValue().set(data.getId(),JSONObject.toJSONString(content));
        rabbitTemplate.convertAndSend(exchangeName,routingKey,message,data);
    }

}
