package io.cloud.datasource.data;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-04-29 17:30
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static volatile DynamicDataSource dynamicDataSource;

    public DynamicDataSource() {
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDatasource();
    }

    /**
     * 单例获取对象
     *
     * @return DynamicDataSource
     */
    public static DynamicDataSource getInstance() {
        if (dynamicDataSource == null) {
            synchronized (DynamicDataSource.class) {
                if (dynamicDataSource == null) {
                    dynamicDataSource = new DynamicDataSource();
                }
            }
        }
        return dynamicDataSource;
    }

}
