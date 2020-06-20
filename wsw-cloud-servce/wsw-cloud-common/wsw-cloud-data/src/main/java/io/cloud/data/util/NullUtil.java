package io.cloud.data.util;

/**
 * @program: wsw-cloud-servce
 * @description: null 工具类
 * @author: wsw
 * @create: 2020-06-13 17:30
 **/
public class NullUtil {

    /**
     * 等于 null
     * @param obj 任意参数
     * @return
     */
    public static boolean isNull(Object obj) {
        if (null == obj) {
            return true;
        }
        return false;
    }

    /**
     * 不等于 null
     * @param obj 任意参数
     * @return
     */
    public static boolean isNotNull(Object obj) {
        if (null == obj) {
            return false;
        }
        return true;
    }
}
