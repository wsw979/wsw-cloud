package io.cloud.gateway.common.util;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-14 14:50
 **/
public class NameUtil {

    public static final String GENERATED_NAME_PREFIX = "_genkey_";

    public NameUtil() {
    }

    public static String generateName(int i) {
        return "_genkey_" + i;
    }


}
