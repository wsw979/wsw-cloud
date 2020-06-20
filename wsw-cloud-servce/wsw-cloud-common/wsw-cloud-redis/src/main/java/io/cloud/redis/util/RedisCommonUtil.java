package io.cloud.redis.util;

import io.cloud.redis.config.RedisTemplate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * @program: wsw-cloud-servce
 * @description: Redis Common 公共方法
 * @author: wsw
 * @create: 2020-05-01 23:08
 **/
@Lazy
@Component
public class RedisCommonUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    @Getter
    @Setter
    @Value("${spring.redis.database}")
    private Integer dataBase;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        return expire(key, time, dataBase);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key     键
     * @param time    时间(秒)
     * @param indexDB 库
     * @return
     */
    public boolean expire(String key, long time, int indexDB) {
        try {
            if (time > 0) {
                redisTemplate.indexdb.set(indexDB);
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return getExpire(key, dataBase);
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key     键 不能为null
     * @param indexDB 库
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key, int indexDB) {
        redisTemplate.indexdb.set(indexDB);
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        return hasKey(key, dataBase);
    }

    /**
     * 判断key是否存在
     *
     * @param key     键
     * @param indexDB 库
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key, int indexDB) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        del(dataBase, key);
    }

    /**
     * 删除缓存
     *
     * @param key     可以传一个值 或多个
     * @param indexDB 库
     */
    @SuppressWarnings("unchecked")
    public void del(int indexDB, String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

}



