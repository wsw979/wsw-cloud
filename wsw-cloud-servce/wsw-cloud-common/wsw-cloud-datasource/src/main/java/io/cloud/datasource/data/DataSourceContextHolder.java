package io.cloud.datasource.data;

import io.cloud.datasource.constant.DataSourceEnum;

/**
 * @program: wsw-cloud-servce
 * @description: 线程安全的数据源
 * @author: wsw
 * @create: 2020-04-29 17:31
 **/
public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceEnum> CONTEXT_HOLDER = new ThreadLocal<DataSourceEnum>();

    public static void setDataSource(DataSourceEnum dataSourceEnum) {
        CONTEXT_HOLDER.set(dataSourceEnum);
    }

    public static DataSourceEnum getDatasource() {
        return CONTEXT_HOLDER.get();
    }

    public static void cleanDataSource() {
        CONTEXT_HOLDER.remove();
    }

}
