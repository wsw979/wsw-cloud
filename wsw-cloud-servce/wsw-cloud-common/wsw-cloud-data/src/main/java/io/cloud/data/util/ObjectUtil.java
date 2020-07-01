package io.cloud.data.util;

import java.util.Optional;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-29 10:30
 **/
public class ObjectUtil extends cn.hutool.core.util.ObjectUtil {

    /**
     * obj check not null
     *
     * @param obj
     * @return
     */
    public static boolean checkObjNotNull(Object obj) {
        Optional<Object> optional = Optional.ofNullable(obj);
        if (optional.isPresent()) {
            return true;
        }
        return false;
    }

    /**
     * obj check not null
     *
     * @param obj
     * @return
     */
    public static boolean checkObjNull(Object obj) {
        Optional<Object> optional = Optional.ofNullable(obj);
        if (!optional.isPresent()) {
            return true;
        }
        return false;
    }
}
