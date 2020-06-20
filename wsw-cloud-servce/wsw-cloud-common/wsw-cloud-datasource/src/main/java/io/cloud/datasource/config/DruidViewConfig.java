package io.cloud.datasource.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description: druid 控制页面
 * @author: wsw
 * @create: 2020-04-29 17:38
 **/
@Configuration
@ConditionalOnProperty(name = "druid.config.enable", havingValue = "true")
public class DruidViewConfig {

    @Resource
    private Environment environment;

    private final Map<String, String> paramMap = new HashMap<>(7);

    @Bean("startViewServlet")
    public ServletRegistrationBean startViewServlet() {
        getDruidConfigProperties();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //Ip白名单,多个在value中用逗号隔开，不配置默认所有
        servletRegistrationBean.addInitParameter("allow", paramMap.get("allow"));
        //ip 黑名单，这里黑名单优先级大于白名单
        servletRegistrationBean.addInitParameter("deny", paramMap.get("deny"));
        //控制台管理
        servletRegistrationBean.addInitParameter("loginUsername", paramMap.get("loginUsername"));
        servletRegistrationBean.addInitParameter("loginPassword", paramMap.get("loginPassword"));
        //是否可以点击页面的重置按钮
        servletRegistrationBean.addInitParameter("resetEnable", paramMap.get("resetEnable"));
        return servletRegistrationBean;
    }

    @Bean("statBeanFilter")
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns(paramMap.get("urlPatterns"));
        //忽略过滤的格式
        filterRegistrationBean.addInitParameter("exclusions", paramMap.get("exclusions"));
        return filterRegistrationBean;
    }

    private void getDruidConfigProperties() {
        if (StringUtils.isEmpty(environment.getProperty("druid.config.allow"))) {
            paramMap.put("allow", "");
        } else {
            paramMap.put("allow", environment.getProperty("druid.config.allow"));
        }

        if (StringUtils.isEmpty(environment.getProperty("druid.config.deny"))) {
            paramMap.put("deny", "");
        } else {
            paramMap.put("deny", environment.getProperty("druid.config.deny"));
        }

        if (StringUtils.isEmpty(environment.getProperty("druid.config.loginUsername"))) {
            paramMap.put("loginUsername", "admin");
        } else {
            paramMap.put("loginUsername", environment.getProperty("druid.config.loginUsername"));
        }

        if (StringUtils.isEmpty(environment.getProperty("druid.config.loginPassword"))) {
            paramMap.put("loginPassword", "admin123");
        } else {
            paramMap.put("loginPassword", environment.getProperty("druid.config.loginPassword"));
        }

        if (StringUtils.isEmpty(environment.getProperty("druid.config.resetEnable"))) {
            paramMap.put("resetEnable", "false");
        } else {
            paramMap.put("resetEnable", environment.getProperty("druid.config.resetEnable"));
        }

        if (StringUtils.isEmpty(environment.getProperty("druid.config.urlPatterns"))) {
            paramMap.put("urlPatterns", "/*");
        } else {
            paramMap.put("urlPatterns", environment.getProperty("druid.config.urlPatterns"));
        }

        if (StringUtils.isEmpty(environment.getProperty("druid.config.exclusions"))) {
            paramMap.put("exclusions", "*.js,*.gif,*.jpg,*.png ,*.css,*.ico,/druid/*");
        } else {
            paramMap.put("exclusions", environment.getProperty("druid.config.exclusions"));
        }
    }

}
