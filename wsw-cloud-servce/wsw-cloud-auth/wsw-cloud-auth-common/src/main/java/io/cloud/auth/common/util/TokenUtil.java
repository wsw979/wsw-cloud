package io.cloud.auth.common.util;

import io.cloud.auth.common.entity.TokenEntity;
import io.cloud.auth.common.properties.AuthServerProperties;
import io.cloud.data.constant.AuthConstants;
import io.cloud.data.util.StringUtil;
import io.cloud.redis.constant.KeyConstant;
import io.cloud.redis.util.RedisCommonUtil;
import io.cloud.redis.util.RedisStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: wsw-cloud-servce
 * @description: token控制工具类
 * @author: wsw
 * @create: 2020-06-29 22:36
 **/
@Slf4j
@Component
public class TokenUtil implements Serializable {

    private static final long serialVersionUID = 8617969696670516L;

    @Autowired
    private RedisCommonUtil redisCommonUtil;

    @Autowired
    private RedisStringUtil redisStringUtil;

    /**
     * 存储token
     *
     * @param key
     * @param token
     * @return
     */
    public Boolean pushToken(String key, String loginType, String token, Date invalid) {
        String id = StringUtil.buffer(KeyConstant.USER_TOKEN, loginType, key);
        LocalDateTime invalidDate = invalid.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setInvalidDate(invalidDate);
        tokenEntity.setToken(token);
        redisStringUtil.set(id, tokenEntity);
        if (loginType.equals(AuthConstants.LOGIN_TYPE_WEB) || loginType.equals(AuthConstants.LOGIN_TYPE_ADMIN)) {
            int invalidSecond = invalidDate.getSecond() - LocalDateTime.now().getSecond();
            redisCommonUtil.expire(id, invalidSecond, TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * 判断token是否有效
     *
     * @param key
     * @param token
     * @return true 有效 false: 无效
     */
    public Boolean judgeTokenValid(String key, String loginType, String token, AuthServerProperties properties) {
        String id = StringUtil.buffer(KeyConstant.USER_TOKEN, loginType, key);
        TokenEntity tokenEntity = (TokenEntity) redisStringUtil.get(id);
        if (tokenEntity == null) {
            return false;
        }
        if (!token.equals(token)) {
            return false;
        }
        if (!properties.getStartRefresh()) {
            LocalDateTime invalidDate = tokenEntity.getInvalidDate();
            LocalDateTime now = LocalDateTime.now();
            long l = invalidDate.toInstant(ZoneOffset.of("+8")).toEpochMilli();
            long l1 = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
            long l2 = l - l1;
            long diffTime = l2 / 1000;
            if (diffTime < properties.getManualRefreshTokenValid()) {
                LocalDateTime localDateTime = now.plusSeconds(properties.getTokenValid());
                tokenEntity.setInvalidDate(localDateTime);
                redisCommonUtil.expire(id, localDateTime.getSecond(), TimeUnit.SECONDS);
            }
        }
        return true;
    }

    /**
     * 登出
     *
     * @param key
     */
    public void logout(String key, String loginType) {
        String id = StringUtil.buffer(KeyConstant.USER_TOKEN, loginType, key);
        redisCommonUtil.del(id);
    }
}
