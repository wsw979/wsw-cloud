package io.cloud.redis.config;

import io.cloud.redis.data.RedisProperty;
import io.cloud.redis.serializer.FastJson2JsonRedisSerializer;
import io.cloud.redis.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description: Redis 配置类 ，实例模板
 * @author: wsw
 * @create: 2020-05-01 22:42
 **/
@Slf4j
@Configuration
public class RedisConfig {

    @Resource
    private RedisProperty redisProperty;

    /**
     * Jedis配置
     */
    @Primary
    @Bean
    public JedisConnectionFactory JedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperty.getHost());
        redisStandaloneConfiguration.setPort(redisProperty.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperty.getPassword()));
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofMillis(redisProperty.getTimeout()));
        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
        return factory;
    }

    /**
     * 实例化 RedisTemplate 对象
     */
    @Bean
    public RedisTemplate functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 引入自定义序列化
     */
    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":" + method.getName() + ":");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     */
    private void initDomainRedisTemplate(RedisTemplate redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }


    /**
     * 注入封装RedisStringTemplate
     */
    @Bean(name = "redisStringUtil")
    public RedisStringUtil redisStringUtil(RedisTemplate redisTemplate) {
        RedisStringUtil redisUtil = new RedisStringUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

    /**
     * 注入封装RedisListTemplate
     */
    @Bean(name = "redisListUtil")
    public RedisListUtil redisListUtil(RedisTemplate redisTemplate) {
        RedisListUtil redisUtil = new RedisListUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

    /**
     * 注入封装RedisCommonTemplate
     */
    @Bean(name = "redisCommonUtil")
    public RedisCommonUtil redisCommonUtil(RedisTemplate redisTemplate) {
        RedisCommonUtil redisUtil = new RedisCommonUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

    /**
     * 注入封装RedisMapTemplate
     */
    @Bean(name = "redisMapUtil")
    public RedisMapUtil redisMapUtil(RedisTemplate redisTemplate) {
        RedisMapUtil redisUtil = new RedisMapUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

    /**
     * 注入封装RedisSetTemplate
     */
    @Bean(name = "redisSetUtil")
    public RedisSetUtil redisSetUtil(RedisTemplate redisTemplate) {
        RedisSetUtil redisUtil = new RedisSetUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

    /**
     * 注入封装RedisHashTemplate
     */
    @Bean(name = "redisHashUtil")
    public RedisHashUtil redisHashUtil(RedisTemplate redisTemplate) {
        RedisHashUtil redisUtil = new RedisHashUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }
}
