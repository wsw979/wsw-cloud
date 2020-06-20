package io.cloud.datasource.aspect;

import io.cloud.datasource.constant.DataSourceEnum;
import io.cloud.datasource.data.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @program: wsw-cloud-servce
 * @description: 数据源 aop
 * @author: wsw
 * @create: 2020-04-29 17:36
 **/
@Slf4j
@Component
@Aspect
@Order(-100)
public class DataSourceAspect {

    @Pointcut("(" +
            "@annotation(io.cloud.datasource.annotation.Master) && " +
            "!@annotation(io.cloud.datasource.annotation.Slaver) ||" +
            "execution(* io.cloud.*.service..*.update*(..)) ||" +
            "execution(* io.cloud.*.service..*.modify*(..)) ||" +
            "execution(* io.cloud.*.service..*.save*(..)) ||" +
            "execution(* io.cloud.*.service..*.insert*(..)) ||" +
            "execution(* io.cloud.*.service..*.delete*(..))" +
            ")")
    public void pointCutMaster() {
    }

    @Before("pointCutMaster()")
    public void doBeforeMaster(JoinPoint joinPoint) {
        log.info("数据源---" + DataSourceEnum.MASTER);
        DataSourceContextHolder.setDataSource(DataSourceEnum.MASTER);
    }

    @Pointcut("(" +
            "@annotation(io.cloud.datasource.annotation.Slaver) && " +
            "!@annotation(io.cloud.datasource.annotation.Master) ||" +
            "execution(* io.cloud.*.service..*.query*(..)) ||" +
            "execution(* io.cloud.*.service..*.find*(..)) ||" +
            "execution(* io.cloud.*.service..*.get*(..))" +
            ")")
    public void pointCutSlaver() {
    }

    @Before("pointCutSlaver()")
    public void doBeforeSlaver(JoinPoint joinPoint) {
        log.info("数据源---" + DataSourceEnum.SLAVER);
        DataSourceContextHolder.setDataSource(DataSourceEnum.SLAVER);
    }

    @Pointcut("(" +
            "@annotation(io.cloud.datasource.annotation.Slaver) || " +
            "@annotation(io.cloud.datasource.annotation.Master) ||" +
            "execution(* io.cloud.*.service..*.query*(..)) ||" +
            "execution(* io.cloud.*.service..*.get*(..)) ||" +
            "execution(* io.cloud.*.service..*.modify*(..)) ||" +
            "execution(* io.cloud.*.service..*.update*(..)) ||" +
            "execution(* io.cloud.*.service..*.save*(..)) ||" +
            "execution(* io.cloud.*.service..*.insert*(..)) ||" +
            "execution(* io.cloud.*.service..*.delete*(..))" +
            ")")
    public void pointCutClean() {
    }

    @After("pointCutClean()")
    public void clean(JoinPoint joinPoint) {
        log.info("清空数据源---");
        DataSourceContextHolder.cleanDataSource();
    }
}
