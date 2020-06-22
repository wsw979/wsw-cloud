package io.cloud.core.async;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description: 传递RequestAttributes
 * @author: wsw
 * @create: 2020-06-11 20:35
 **/
public class ContextCopyingDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        try {
			RequestAttributes context = RequestContextHolder.currentRequestAttributes();
			Map<String,String> previous = MDC.getCopyOfContextMap();
			SecurityContext securityContext = SecurityContextHolder.getContext();// 1
			return () -> {
			    try {
			    	if(previous==null){
			    		MDC.clear();
			    	}else{
			    		MDC.setContextMap(previous);
			    	}
			    	
			        RequestContextHolder.setRequestAttributes(context);
			        SecurityContextHolder.setContext(securityContext);// 2
			        runnable.run();
			    } finally {
			        RequestContextHolder.resetRequestAttributes();
			        // 清除操作
			        SecurityContextHolder.clearContext();// 3

			        if(previous==null){
			    		MDC.clear();
			    	}else{
			    		MDC.setContextMap(previous);
			    	}
			    }
			};
		} catch (IllegalStateException e) {
			return runnable;
		}
    }
}
