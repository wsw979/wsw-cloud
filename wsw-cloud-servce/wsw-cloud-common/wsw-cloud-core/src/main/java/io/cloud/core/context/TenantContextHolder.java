package io.cloud.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 日志链路追踪
 *
 * @author zlt
 * @date 2019/8/5
 */
public class TenantContextHolder {
    /**
     * 支持父子线程之间的数据传递
     */
    private static final ThreadLocal<String> CONTEXT = new TransmittableThreadLocal<>();

    public static void setTenant(String tenant) {
        CONTEXT.set(tenant);
    }

    public static String getTenant() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
