package io.cloud.log.aspect;

import com.alibaba.fastjson.JSON;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
import io.cloud.log.dtl.CodeMsg;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-02 15:04
 **/
@Slf4j
@Aspect
@Order(5)
@Component
public class LogAspect {

    private static final String BUFFER = "|#|";

    /**
     * controller切入点
     */
    @Pointcut("execution(public * io.cloud..*.controller..*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object aopLog(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("收集日志--开始");
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        //获取请求的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        //请求的参数
        String params = null;
        Object[] args = proceedingJoinPoint.getArgs();
        //将参数所在的数组转换成json
        for (Object obj : args) {
            if (!(obj instanceof HttpServletRequest)
                    && !(obj instanceof HttpServletResponse)) {
                Class<?> clazz = obj.getClass();
                params = this.param(obj);
            }
        }
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String sl = "|#|";
        //开始调用时间
        long start = System.currentTimeMillis();
        try {
            Object result = proceedingJoinPoint.proceed();
            CodeMsg codeMsg = JSON.parseObject(result.toString(), CodeMsg.class);
            long time = System.currentTimeMillis() - start;
            // 记录下请求内容
            log.info("aopLogDqap : " + request.getRequestURL().toString());
            log.info("请求参数 : " + params);
            log.info("请求方法 : " + request.getMethod());
            log.info("IP地址 : " + request.getRemoteAddr());
            log.info("耗时(豪秒) : " + time);
            log.info("返回值 : " + codeMsg.toString());


            log.info("环绕切面--收集日志--结束");
            return result;
        } catch (Throwable throwable) {
            log.error("收集日志--异常", throwable);
        }
        return R.error(HttpStatus.ERROR);
    }

    private String param(Object obj) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("请求参数 { ");
        try {
            Class clazz = (Class) obj.getClass();
            //分页参数默认前面
            Class superclass = clazz.getSuperclass();
            if(superclass != null && !superclass.getName().toLowerCase().equals("java.lang.object")){
                this.buffer(buffer,obj,superclass);
            }
            this.buffer(buffer,obj,clazz);
        } catch (Exception e) {
            log.error("日志收集--获取请求参数异常");
        }
        buffer.delete(buffer.lastIndexOf(BUFFER),buffer.length());
        buffer.append(" }");
        return buffer.toString();
    }

    private void buffer(StringBuffer buffer, Object obj, Class clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            PropertyDescriptor pd = new PropertyDescriptor(fields[i].getName(), clazz);
            Method getMethod = pd.getReadMethod();
            Object fieldValue = ReflectionUtils.invokeMethod(getMethod, obj);
            if (fieldValue instanceof String) {
                if(StringUtils.isNotEmpty(fieldValue.toString())){
                    buffer.append(fieldValue + BUFFER);
                }
            }else {
                if(fieldValue != null){
                    buffer.append(fieldValue + BUFFER);
                }
            }
        }
    }
}
