package io.cloud.job.admin.handler;

import groovy.util.logging.Slf4j;
import io.cloud.job.core.biz.model.ReturnT;
import io.cloud.job.core.handler.annotation.XxlJob;
import io.cloud.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-09 09:11
 **/
@Component
public class JobHandler {

    Logger log = LoggerFactory.getLogger(JobHandler.class);

    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {

        XxlJobLogger.log("Fuck !!!!!!!!!!!!!");
        log.info("Fuck !!!!!!!!!!!!!");
        return ReturnT.SUCCESS;
    }


}
