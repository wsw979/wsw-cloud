package io.cloud.mq.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import io.cloud.exception.ServiceException;
import io.cloud.mq.entity.MqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-03 09:08
 **/
@Slf4j
public abstract class DefaultListener<T extends MqMessage> {

    private static final SerializerMessageConverter SERIALIZER_MESSAGE_CONVERTER = new SerializerMessageConverter();

    private static final String ENCODING = Charset.defaultCharset().name();

    public  static final String PARENT_MESSAGE_CLASS = "io.cloud.mq.entity.MqMessage";

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 业务执行方法
     * @param content
     */
    protected abstract void execute(T content)throws Exception;

    /**
     * 失败执行
     * @param content
     */
    protected abstract void failExecute(T content);

    /**
     * 接收到的消息处理
     * @param message
     * @param channel
     * @throws IOException
     */
    protected void receiveMessage(Message message, Channel channel) throws IOException{
        /**
         * 防止重复消费，可以根据传过来的唯一ID先判断缓存数据库中是否有数据
         * 1、有数据则不消费，直接应答处理
         * 2、缓存没有数据，则进行消费处理数据，处理完后手动应答
         * 3、如果消息处理异常则，可以存入数据库中，手动处理（可以增加短信和邮件提醒功能）
         */
        try{
            T content = getContent(message);
            //已经消费，直接返回
            if(canConsume(content,message.getMessageProperties().getConsumerQueue())){
                log.info(message.getMessageProperties().getConsumerQueue()+"已经消费过");
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }else{
                //消费当前消息
                execute(content);
                log.info(message.getMessageProperties().getConsumerQueue()+"消费成功");
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                //消费成功删除key
                //单个消息控制
                String redisCountKey = "retry" + message.getMessageProperties().getConsumerQueue() + content.getKey();
                //队列控制
                String queueKey = "retry" + message.getMessageProperties().getConsumerQueue();
                redisTemplate.delete(redisCountKey);
                redisTemplate.delete(queueKey);
            }
        }catch (Exception e){
            e.printStackTrace();
            try {
                if(dealFailAck(message,channel)){
                    log.info("回归队列："+message);
                }else{
                    log.error("消费失败："+message);
                    failExecute(getContent(message));
                }
            }catch (Exception e1){
                //扔掉数据
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
                log.error("重试消费失败："+message);
                failExecute(getContent(message));
            }
        }
    }

    /**
     *
     * @param message
     * @return
     */
    private T getContent(Message message) {
        String body = getBodyContentAsString(message);
        Class<T> contentClass = null;
        try {
            contentClass = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        }catch (Exception e){
            throw new ServiceException("缺失泛型");
        }
        if(contentClass!=null&&contentClass.getName().equals(PARENT_MESSAGE_CLASS)){
            throw new ServiceException("请指定相应的消息类型");
        }
        T content = JSONObject.toJavaObject(JSONObject.parseObject(body),contentClass);
        return content;
    }
    /**
     * 获取message的body
     * @param message
     * @return
     */
    private String getBodyContentAsString(Message message) {
        if (message.getBody() == null) {
            return null;
        }
        try {
            String contentType = (message.getMessageProperties() != null) ? message.getMessageProperties().getContentType() : null;
            if (MessageProperties.CONTENT_TYPE_SERIALIZED_OBJECT.equals(contentType)) {
                return SERIALIZER_MESSAGE_CONVERTER.fromMessage(message).toString();
            }
            if (MessageProperties.CONTENT_TYPE_TEXT_PLAIN.equals(contentType)
                    || MessageProperties.CONTENT_TYPE_JSON.equals(contentType)
                    || MessageProperties.CONTENT_TYPE_JSON_ALT.equals(contentType)
                    || MessageProperties.CONTENT_TYPE_XML.equals(contentType)) {
                return new String(message.getBody(), ENCODING);
            }
        }
        catch (Exception e) {
            // ignore
        }
        // Comes out as '[B@....b' (so harmless)
        return message.getBody().toString() + "(byte[" + message.getBody().length + "])";
    }

    /**
     * 失败ACK
     * @param message
     * @param channel
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private Boolean  dealFailAck(Message message,Channel channel) throws IOException, InterruptedException{
        T content = getContent(message);
        //单个消息控制
        String redisCountKey = "retry"+message.getMessageProperties().getConsumerQueue()+content.getKey();
        String retryCount = redisTemplate.opsForValue().get(redisCountKey);
        long basic = 1000L;
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        //队列控制
        String queueKey = "retry"+message.getMessageProperties().getConsumerQueue();
        //没有重试过一次
        if(StringUtils.isBlank(retryCount)){
            if(!redisTemplate.opsForValue().setIfAbsent(queueKey,"lock",25, TimeUnit.SECONDS)) {
                channel.basicNack(deliveryTag,false,true);
                log.info("deliveryTag:"+deliveryTag);
                return true;
            }
            //预防队列太长，造成延迟时间过长
            redisTemplate.opsForValue().setIfAbsent(redisCountKey,"1",5,TimeUnit.MINUTES);
            log.info("开始第一次尝试：");
        }else{
            switch (Integer.valueOf(retryCount)){
                case 1:
                    redisTemplate.opsForValue().set(redisCountKey,"2");
                    log.info("开始第二次尝试：");
                    break;
                case 2:
                    redisTemplate.opsForValue().set(redisCountKey,"3");
                    log.info("开始第三次尝试：");
                    break;
                case 3:
                    redisTemplate.opsForValue().set(redisCountKey,"4");
                    log.info("开始第四次尝试：");
                    break;
                default:
                    //扔掉消息，准备持久化
                    redisTemplate.delete(redisCountKey);
                    redisTemplate.delete(queueKey);
                    channel.basicNack(deliveryTag,false,false);
                    return false;
            }
        }
        channel.basicNack(deliveryTag,false,true);
        return true;
    }

    /**
     * 是否能消费，防止重复消费
     * @param content
     * @param queueName
     * @return
     */
    private Boolean canConsume(T content,String queueName) {
        if(redisTemplate.opsForValue().get(queueName+":"+content.getKey())==null){
            return false;
        }else{
            //存储消费标志
            redisTemplate.opsForValue().set(queueName+":"+content.getKey(),"lock",30, TimeUnit.SECONDS);
            return true;
        }
    }
}
