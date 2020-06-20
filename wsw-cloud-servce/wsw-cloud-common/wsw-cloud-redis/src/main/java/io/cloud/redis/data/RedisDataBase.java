package io.cloud.redis.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: wsw-cloud-servce
 * @description: 资源文件
 * @author: wsw
 * @create: 2020-05-06 14:52
 **/
@Data
@Component
public class RedisDataBase {

    @Value("${spring.redis.database}")
    private Integer dataBase;

}
