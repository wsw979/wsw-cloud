package io.cloud.core.fallback;


import com.alibaba.cloud.sentinel.feign.SentinelContractHolder;
import com.alibaba.cloud.sentinel.feign.SentinelFeign;
import com.alibaba.cloud.sentinel.feign.SentinelInvocationHandler;
import feign.Contract;
import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.Target;
import feign.hystrix.FallbackFactory;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-24 16:47
 **/
public class FunSentinelFeign {

    public static SentinelFeign.Builder builder() {
        return new SentinelFeign.Builder();
    }

    public static final class Builder extends Feign.Builder
            implements ApplicationContextAware {

        private Contract contract = new Contract.Default();
        private ApplicationContext applicationContext;
        private FeignContext feignContext;

        @Override
        public Feign.Builder invocationHandlerFactory(
                InvocationHandlerFactory invocationHandlerFactory) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Feign.Builder contract(Contract contract) {
            this.contract = contract;
            return this;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext)
                throws BeansException {
            this.applicationContext = applicationContext;
            feignContext = this.applicationContext.getBean(FeignContext.class);
        }

        @Override
        public Feign build() {
            super.invocationHandlerFactory(new InvocationHandlerFactory() {
                @SuppressWarnings({"rawtypes", "unchecked"})
                @SneakyThrows
                @Override
                public InvocationHandler create(Target target,
                                                Map<Method, MethodHandler> dispatch) {
                    Object feignClientFactoryBean = Builder.this.applicationContext
                            .getBean("&" + target.type().getName());
                    Class fallback =
                            (Class) getFieldValue(feignClientFactoryBean, "fallback");
                    Class fallbackFactory =
                            (Class) getFieldValue(feignClientFactoryBean, "fallbackFactory");
                    String name = (String) getFieldValue(feignClientFactoryBean, "name");

                    Object fallbackInstance;
                    FallbackFactory fallbackFactoryInstance;
                    Constructor<SentinelInvocationHandler> constructor =
                            SentinelInvocationHandler.class.getDeclaredConstructor(
                                    Target.class,
                                    Map.class,
                                    FallbackFactory.class);
                    constructor.setAccessible(true);
                    if (void.class != fallback) {
                        fallbackInstance = getFromContext(
                                        name,
                                        "fallback",
                                        fallback,
                                        target.type());
                        return constructor.newInstance(
                                target, dispatch,
                                new FallbackFactory.Default<>(fallbackInstance));
                    }
                    if (void.class != fallbackFactory) {
                        fallbackFactoryInstance = (FallbackFactory) getFromContext(
                                name,
                                "fallbackFactory",
                                fallbackFactory,
                                FallbackFactory.class);
                        return constructor.newInstance(
                                target, dispatch,
                                fallbackFactoryInstance);
                    }

                    //默认的FallbackFactory
                    FunFallbackFactory funFallbackFactory = new FunFallbackFactory(target);
                    return constructor.newInstance(target,dispatch,funFallbackFactory);
                }

                @SuppressWarnings({"rawtypes", "unchecked"})
                private Object getFromContext(String name, String type,
                                              Class fallbackType, Class targetType){
                    Object fallbackInstance = feignContext.getInstance(name, fallbackType);
                    if(fallbackInstance == null){
                        throw new IllegalStateException(String.format(
                                "No %s instance of type %s found for feign client %s",
                                type, fallbackType, name));
                    }
                    if(!targetType.isAssignableFrom(fallbackType)){
                        throw new IllegalStateException(String.format(
                                "Incompatible %s instance. Fallback/fallbackFactory of the %s is not assignable",
                                type, fallbackType, targetType, name));
                    }
                    return fallbackInstance;
                }
            });
            super.contract(new SentinelContractHolder(contract));
            return super.build();
        }

        private Object getFieldValue(Object instance, String fieldName) {
            Field field = ReflectionUtils.findField(instance.getClass(), fieldName);
            field.setAccessible(true);
            try {
                return field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
