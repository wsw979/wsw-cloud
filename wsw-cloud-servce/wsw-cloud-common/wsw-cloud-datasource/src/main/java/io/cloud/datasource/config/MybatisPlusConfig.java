package io.cloud.datasource.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: wsw-cloud-servce
 * @description: mybatis 分页 + mapper
 * @author: wsw
 * @create: 2020-04-29 17:25
 **/
@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
@MapperScan(basePackages = "io.cloud.**.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean(name = "paginationInterceptor")
    public PaginationInterceptor paginationInterceptor() {
        //开启PageHelper
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

}
