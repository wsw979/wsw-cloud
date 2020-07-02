package io.cloud.gateway.api.handler;

import io.cloud.job.core.biz.model.ReturnT;
import io.cloud.job.core.handler.IJobHandler;
import io.cloud.job.core.handler.annotation.XxlJob;
import io.cloud.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program: wsw-cloud-servce
 * @description: xxl 定时任务调度
 * @author: wsw
 * @create: 2020-07-02 14:00
 **/
@Slf4j
@Component
public class JobHandler {

    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        XxlJobLogger.log("Fuck !!!!!!!!!!!!!");
        log.info("Fuck !!!!!!!!!!!!!");
        return ReturnT.SUCCESS;
    }

}
