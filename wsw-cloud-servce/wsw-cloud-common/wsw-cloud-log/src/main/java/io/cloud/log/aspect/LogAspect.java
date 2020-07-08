package io.cloud.log.aspect;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloud.exception.InternalException;
import io.cloud.exception.ServiceException;
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
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

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

    private static final String BUFFER = "&";

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
        //获取请求的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        //请求的参数
        StringBuffer buffer = new StringBuffer();
        buffer.append("{ ");
        Object[] args = proceedingJoinPoint.getArgs();
        //将参数所在的数组转换成json
        for (Object obj : args) {
            if (!(obj instanceof HttpServletRequest)
                    && !(obj instanceof HttpServletResponse)) {
                checkType(obj, buffer);
            }
        }
        if (buffer.toString().contains(BUFFER)) {
            buffer.delete(buffer.lastIndexOf(BUFFER), buffer.length());
        }
        buffer.append(" }");
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //开始调用时间
        long start = System.currentTimeMillis();
        try {
            Object result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();
            long time = end - start;
            CodeMsg codeMsg = new CodeMsg(200, "无返回值");
            if (result != null) {
                BeanUtils.copyProperties(result, codeMsg);
            }
            // 记录下请求内容
            log.info("请求路径: " + request.getRequestURI());
            log.info("请求类名: " + className);
            log.info("请求方法: " + methodName);
            log.info("耗时(秒): " + time);
            log.info("请求参数: " + buffer.toString());
            log.info("返回编码: " + codeMsg.getCode());
            log.info("返回信息: " + codeMsg.getMsg());
            log.info("收集日志--结束");
            return result;
        } catch (Throwable throwable) {
            log.error("收集日志--异常", throwable);
            if (throwable instanceof ServiceException) {
                ServiceException error = (ServiceException) throwable;
                throw new ServiceException(error.getCode(), error.getMsg());
            }
            if(throwable instanceof InternalException){
                InternalException error = (InternalException) throwable;
                throw new InternalException(error.getCode(), error.getMsg());
            }
            throw new ServiceException(HttpStatus.ERROR.getCode(),throwable.getMessage());
        }
    }

    private StringBuffer checkType(Object obj, StringBuffer buffer) {
        Class<?> clazz = obj.getClass();
        boolean flag = true;
        if (clazz == String.class) {
            buffer.append(obj);
        } else if (clazz == Long.class) {
            buffer.append(obj);
        } else if (clazz == Boolean.class) {
            buffer.append(obj);
        } else if (clazz == Byte.class) {
            buffer.append(obj);
        } else if (clazz == Integer.class) {
            buffer.append(obj);
        } else if (clazz == Double.class) {
            buffer.append(obj);
        } else if (clazz == BigDecimal.class) {
            buffer.append(obj);
        } else if (clazz == Short.class) {
            buffer.append(obj);
        } else if (clazz == Float.class) {
            buffer.append(obj);
        } else if (obj instanceof MultipartFile) {
            buffer.append("file");
        } else {
            flag = false;
            param(obj, buffer);
        }
        if (flag) {
            buffer.append(BUFFER);
        }
        return buffer;
    }

    private StringBuffer param(Object obj, StringBuffer buffer) {
        try {
            Class clazz = (Class) obj.getClass();
            //父类参数默认前面
            Class superclass = clazz.getSuperclass();
            if (superclass != null && !superclass.getName().toLowerCase().equals("java.lang.object")) {
                this.buffer(buffer, obj, superclass);
            }
            this.buffer(buffer, obj, clazz);
        } catch (Exception e) {
            log.error("日志收集--获取请求参数异常");
        }
        return buffer;
    }

    private void buffer(StringBuffer buffer, Object obj, Class clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            PropertyDescriptor pd = new PropertyDescriptor(fields[i].getName(), clazz);
            Method getMethod = pd.getReadMethod();
            Object fieldValue = ReflectionUtils.invokeMethod(getMethod, obj);
            if (fieldValue instanceof String) {
                if (StringUtils.isNotEmpty(fieldValue.toString())) {
                    buffer.append(fieldValue).append(BUFFER);
                }
            } else {
                if (fieldValue != null) {
                    buffer.append(fieldValue).append(BUFFER);
                }
            }
        }
    }

}
