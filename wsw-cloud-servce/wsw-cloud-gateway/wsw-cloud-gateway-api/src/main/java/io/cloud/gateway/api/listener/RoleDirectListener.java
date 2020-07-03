package io.cloud.gateway.api.listener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import io.cloud.datasource.primary.IdGenerator;
import io.cloud.exception.util.R;
import io.cloud.gateway.api.cache.Role;
import io.cloud.mq.config.RabbitConfig;
import io.cloud.mq.listener.DefaultListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: wsw-cloud-user
 * @description:
 * @author: wsw
 * @create: 2020-07-03 11:20
 **/
@Slf4j
@Component
public class RoleDirectListener extends DefaultListener<Role> {

    @Override
    protected void execute(Role content) throws Exception {
        log.info("执行内容"+content.toString());
    }

    @Override
    protected void failExecute(Role content) {
        log.info("失败处理"+content.toString());
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    @Override
    protected void receiveMessage(Message message, Channel channel) throws IOException {
        super.receiveMessage(message,channel);
    }

}
