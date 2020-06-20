package io.cloud.gateway.service.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @program: wsw-cloud-servce
 * @description: * 负载均衡策略Holder
 * @author: wsw
 * @create: 2020-06-05 16:24
 **/
public class LbIsolationContextHolder {
    private static final ThreadLocal<String> VERSION_CONTEXT = new TransmittableThreadLocal<>();

    public static void setVersion(String version) {
        VERSION_CONTEXT.set(version);
    }

    public static String getVersion() {
        return VERSION_CONTEXT.get();
    }

    public static void clear() {
        VERSION_CONTEXT.remove();
    }
}
