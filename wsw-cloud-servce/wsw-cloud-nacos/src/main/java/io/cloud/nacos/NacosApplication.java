package io.cloud.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 单机运行， 开发调试用， 线上下载最新脚本运行
 */
@SpringBootApplication(scanBasePackages = {"com.alibaba.nacos", "io.cloud.nacos"})
@ServletComponentScan
@EnableScheduling
public class NacosApplication {

    public static void main(String[] args) {
        System.setProperty("nacos.standalone", "true");
        SpringApplication.run(NacosApplication.class, args);
    }

}
