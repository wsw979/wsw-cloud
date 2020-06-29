package io.cloud.core.config;

import io.cloud.core.filter.HttpServletRequestReplacedFilter;
import io.cloud.core.filter.TraceContextFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-08 09:18
 **/
@Configuration
@SuppressWarnings("all")
@ConditionalOnClass(WebMvcConfigurer.class)
public class TraceFilterConfig {

    @Bean
    public FilterRegistrationBean requestContextRepositoryFilterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TraceContextFilter());
        registration.setFilter(new HttpServletRequestReplacedFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("httpServletRequestReplacedFilter");
        registration.setOrder(1);
        return registration;
    }
}
