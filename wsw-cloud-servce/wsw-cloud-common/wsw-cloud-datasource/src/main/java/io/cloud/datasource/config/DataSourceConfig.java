package io.cloud.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.cloud.datasource.constant.DataSourceEnum;
import io.cloud.datasource.constant.MybatisPlusEnum;
import io.cloud.datasource.data.DataSourceMap;
import io.cloud.datasource.data.DynamicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @program: wsw-cloud-servce
 * @description: 数据源
 * @author: wsw
 * @create: 2020-04-29 17:26
 **/
@Configuration
public class DataSourceConfig {

    @Resource
    private PaginationInterceptor paginationInterceptor;

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
    @Primary
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源
     */
    @Bean(name = "dynamic")
    public DataSource multipleDataSource(@Qualifier("master") DataSource master) {
        //设置默认数据源
        DynamicDataSource.getInstance().setDefaultTargetDataSource(master);
        DataSourceMap.getDataSourceMap().put(DataSourceEnum.MASTER, master);
        DynamicDataSource.getInstance().setTargetDataSources(DataSourceMap.getDataSourceMap());
        return DynamicDataSource.getInstance();
    }

    @Bean(name = "transactionManager")
    @Order
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamic") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        //这里进行动态数据源重新设置
        DynamicDataSource.getInstance().setTargetDataSources(DataSourceMap.getDataSourceMap());
        //注入resolvedDataSources中
        DynamicDataSource.getInstance().afterPropertiesSet();
        return transactionManager;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamic") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage(MybatisPlusEnum.TYPE_ALIASES_PACKAGE);
        //扫描XML
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactory.setMapperLocations(resolver.getResources(MybatisPlusEnum.MAPPER_LOCATIONS));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        //去除plus banner
        configuration.getGlobalConfig().setBanner(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                //添加分页功能
                paginationInterceptor
        });
        return sqlSessionFactory.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(value = "sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

}
