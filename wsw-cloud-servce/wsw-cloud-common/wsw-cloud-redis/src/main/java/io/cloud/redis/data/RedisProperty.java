package io.cloud.redis.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: wsw-cloud-servce
 * @description: 资源文件
 * @author: wsw
 * @create: 2020-05-01 22:20
 **/
@Data
@Component
public class RedisProperty {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password:#{null}}")
    private String password;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.timeout}")
    private Integer timeout;

    @Value("${spring.redis.lettuce.pool.maxIdle}")
    private Integer maxIdle;

    @Value("${spring.redis.lettuce.pool.maxTotal}")
    private Integer maxTotal;

    @Value("${spring.redis.lettuce.pool.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${spring.redis.lettuce.pool.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${spring.redis.lettuce.pool.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${spring.redis.lettuce.pool.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${spring.redis.lettuce.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.redis.lettuce.pool.testWhileIdle}")
    private boolean testWhileIdle;

}
