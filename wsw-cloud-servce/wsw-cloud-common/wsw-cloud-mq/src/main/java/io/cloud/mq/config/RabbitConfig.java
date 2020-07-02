package io.cloud.mq.config;

import io.cloud.mq.service.ProducerService;
import io.cloud.mq.service.impl.ProducerServiceImpl;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

/**
 * @program: wsw-cloud-servce
 * @description: mq config
 * @author: wsw
 * @create: 2020-07-02 22:06
 * direct直连模型
 * fanout无路由模式，使用场景广播消息
 * topic 模糊路由模式，适用业务分组
 * fanout>direct>topic 这里是多消费模式，topic和fanout都能实现，通过性能对比选择fanout  11>10>6
 **/
public class RabbitConfig {

    /**
     * 初始化相关配置
     * @return
     */
    @Bean
    public RabbitTemplateConfig rabbitTemplateConfig(){
        RabbitTemplateConfig rabbitTemplateConfig = new RabbitTemplateConfig();
        return rabbitTemplateConfig;
    }

    /**
     * 提供者
     * @return
     */
    @Bean
    public ProducerService producerService(){
        ProducerServiceImpl producerService = new ProducerServiceImpl();
        return producerService;
    }

    /**
     * 队列名
     */
    public static final String QUEUE = "wsw.q";
    /**
     * 交换机
     */
    public static final String EXCHANGE = "wsw.ex";
    /**
     * 路由器
     */
    public static final String ROUTINGKEY = "wsw.rk";

    @Bean
    public DirectExchange testDirectExchange(){
        DirectExchange directExchange = new DirectExchange(EXCHANGE);
        return directExchange;
    }

    @Bean
    public Queue queue(){
        Queue queue = new Queue(QUEUE);
        return queue;
    }
    /**
     * 绑定
     * @return
     */
    @Bean
    public Binding bindingTest() {
        return BindingBuilder.bind(queue()).to(testDirectExchange()).with(ROUTINGKEY);
    }

    /**
     * websocket的交换机
     */
    public static final String WEBSOCKET_EX =  "websocket.ex";

    /**
     * websocket交换机
     * @return
     */
    @Bean
    public FanoutExchange websocketFanoutExchange(){
        FanoutExchange fanoutExchange = new FanoutExchange(WEBSOCKET_EX);
        return fanoutExchange;
    }
}

