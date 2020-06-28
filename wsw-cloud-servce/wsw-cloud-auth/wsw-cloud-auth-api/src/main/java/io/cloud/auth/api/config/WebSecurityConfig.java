package io.cloud.auth.api.config;

import io.cloud.auth.api.filter.UsernameAuthenticationFilter;
import io.cloud.auth.api.handler.WebLoginAuthSuccessHandler;
import io.cloud.auth.api.handler.WebLoginFailureHandler;
import io.cloud.auth.api.provider.UsernameAuthenticationProvider;
import io.cloud.auth.api.service.UsernameUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-27 20:17
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UsernameUserDetailService usernameUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http
            .authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll().and()
            .addFilterBefore(usernameAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests().antMatchers("/oauth/**").permitAll().and()
            .authorizeRequests().antMatchers("/logout/**").permitAll().and()
            .authorizeRequests().antMatchers("/pub-key/jwt.json").permitAll().and()
            .authorizeRequests().antMatchers("/js/**","/favicon.ico").permitAll().and()
            .authorizeRequests().antMatchers("/v2/api-docs/**","/webjars/**","/swagger-resources/**","/*.html").permitAll().and()
            // 其余所有请求全部需要鉴权认证
            .authorizeRequests().anyRequest().authenticated();
    }

    /**
     * 用户验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernameAuthenticationProvider());
    }

    @Bean
    public UsernameAuthenticationProvider usernameAuthenticationProvider(){
        UsernameAuthenticationProvider provider = new UsernameAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(usernameUserDetailService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    /**
     * 用户名/手机/邮箱 密码登录
     * @return
     */
    @Bean
    public UsernameAuthenticationFilter usernameAuthenticationFilter(){
        UsernameAuthenticationFilter filter = new UsernameAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationSuccessHandler(loginSuccessAuth());
        filter.setAuthenticationFailureHandler(loginFailure());
        return filter;
    }

    /**
     * 重新实例化bean
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public WebLoginAuthSuccessHandler loginSuccessAuth(){
        WebLoginAuthSuccessHandler myLoginAuthSuccessHandler = new WebLoginAuthSuccessHandler();
        return myLoginAuthSuccessHandler;
    }

    @Bean
    public WebLoginFailureHandler loginFailure(){
        WebLoginFailureHandler myLoginFailureHandler = new WebLoginFailureHandler();
        return myLoginFailureHandler;
    }
}