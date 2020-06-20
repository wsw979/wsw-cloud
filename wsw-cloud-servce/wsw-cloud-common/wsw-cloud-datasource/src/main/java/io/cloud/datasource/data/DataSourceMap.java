package io.cloud.datasource.data;

import io.cloud.datasource.constant.DataSourceEnum;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description: 获取数据源
 * @author: wsw
 * @create: 2020-04-29 17:31
 **/
public class DataSourceMap {

    /**
     * 全局静态存放数据源名称Map
     */
    private static final Map<Object, Object> DATASOURCEMAP = new HashMap<>();


    private DataSourceMap() {
    }

    /**
     * 获取数据源集合
     *
     * @return
     */
    public static Map<Object, Object> getDataSourceMap() {
        return DATASOURCEMAP;
    }

    /**
     * 添加数据源bean进入集合中
     *
     * @param dataSourceEnum
     * @return
     */
    public static boolean setDataSourceBeanName(DataSourceEnum dataSourceEnum, DataSource dataSource) {
        if (dataSource == null) {
            return false;
        }
        return DATASOURCEMAP.put(dataSourceEnum, dataSource) != null;

    }

    /**
     * 删除集合中的某个数据源
     *
     * @param dataSourceBeanName
     * @return
     */
    public static boolean remove(String dataSourceBeanName) {
        if (StringUtils.isBlank(dataSourceBeanName)) {
            return false;
        }
        return DATASOURCEMAP.remove(dataSourceBeanName) == null;
    }

}
