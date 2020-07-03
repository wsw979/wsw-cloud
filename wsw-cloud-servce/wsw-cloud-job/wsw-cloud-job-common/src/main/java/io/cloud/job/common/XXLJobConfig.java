package io.cloud.job.common;

import io.cloud.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "xxl.job", value = "enable", havingValue = "true")
public class XXLJobConfig {

    @Value("${xxl.job.accessToken:#{null}}")
    private String accessToken;

    @Value("${xxl.job.admin.addresses:#{null}}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname:#{null}}")
    private String appName;

    @Value("${xxl.job.executor.ip:#{null}}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private Integer port;

    @Value("${xxl.job.executor.logPath}")
    private String logPath;

    @Value("${xxl.job.executor.logRetentionDays}")
    private Integer logRetentionDays;


    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appName);
        if(StringUtils.isEmpty(ip)){
            ip = getServerIp();
        }
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }

    /**
     * 获取本地IP
     * @return
     */
    private String getServerIp(){
        try {
            String ip =  InetAddress.getLocalHost().getHostAddress();
            log.info("Ip："+ip);
            return ip;
        }catch (Exception e){
            return null;
        }
    }
}
