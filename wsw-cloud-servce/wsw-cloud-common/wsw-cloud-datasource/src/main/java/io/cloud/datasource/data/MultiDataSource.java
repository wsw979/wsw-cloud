package io.cloud.datasource.data;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import io.cloud.datasource.constant.DataSourceEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @program: wsw-cloud-servce
 * @description: 多数据源配置
 * @author: wsw
 * @create: 2020-04-29 17:43
 **/
@Configuration
public class MultiDataSource {

    @Bean("slaver")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.slaver")
    public DataSource slaver() {
        //创建数据源并放入动态池
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        DataSourceMap.setDataSourceBeanName(DataSourceEnum.SLAVER, dataSource);
        return dataSource;
    }

}
