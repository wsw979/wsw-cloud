package io.cloud.datasource.primary;

import com.baomidou.mybatisplus.core.toolkit.Sequence;

/**
 * @program: wsw-cloud-servce
 * @description: 分布式id生成器
 * @author: wsw
 * @create: 2020-06-05 15:15
 **/
public class IdGenerator {

    private static Sequence WORKER = new Sequence();

    public static long getId() {
        return WORKER.nextId();
    }

    public static String getIdStr() {
        return String.valueOf(WORKER.nextId());
    }

}
