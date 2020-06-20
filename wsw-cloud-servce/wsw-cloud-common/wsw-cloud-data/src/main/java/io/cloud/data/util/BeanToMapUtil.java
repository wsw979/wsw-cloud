package io.cloud.data.util;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-05-12 17:45
 **/
@Slf4j
public class BeanToMapUtil {

    /**
     * * 实体类转map
     * * @param obj
     * * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    if (null != getter) {
                        Object value = getter.invoke(obj);
                        if (null == value) {
                            paramMap.put(key, null);
                        } else {
                            paramMap.put(key, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("objectToMap Error {}", e);
        }
        return paramMap;
    }

    /**
     * * 实体类转map
     * * @param obj
     * * @return
     */
    public static Map<String, String> objectToStringMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, String> paramMap = new HashMap<String, String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    String value = (String) getter.invoke(obj);
                    if (null == value) {
                        paramMap.put(key, null);
                    } else {
                        paramMap.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            log.error("objectToMap Error {}", e);
        }
        return paramMap;
    }


    /**
     * * map 转实体类
     * * @param clazz
     * * @param map
     * * @param <T>
     * * @return
     */
    public static <T> T mapToBean(Class<T> clazz, Map<String, Object> map) {
        T obj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            obj = clazz.newInstance(); // 创建 JavaBean 对象
            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);
                    if ("".equals(value)) {
                        value = null;
                    }
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }
        } catch (IllegalAccessException e) {
            log.error("convertMapToBean 实例化JavaBean失败 Error{}", e);
        } catch (IntrospectionException e) {
            log.error("convertMapToBean 分析类属性失败 Error{}", e);
        } catch (IllegalArgumentException e) {
            log.error("convertMapToBean 映射错误 Error{}", e);
        } catch (InstantiationException e) {
            log.error("convertMapToBean 实例化 JavaBean 失败 Error{}", e);
        } catch (InvocationTargetException e) {
            log.error("convertMapToBean字段映射失败 Error{}", e);
        } catch (Exception e) {
            log.error("convertMapToBean Error{}", e);
        }
        return (T) obj;
    }

    /**
     * * map通过反射转化为实体
     * * @param obj 实体对象
     * * @param map map集合
     * * @return
     */
    public static Object MapToModel(Map<String, Object> map, Object o) throws Exception {
        if (!map.isEmpty()) {
            for (String k : map.keySet()) {
                Object v = null;
                if (!k.isEmpty()) {
                    v = map.get(k);
                }
                Field[] fields = null;
                fields = o.getClass().getDeclaredFields();
                String clzName = o.getClass().getSimpleName();
                for (Field field : fields) {
                    int mod = field.getModifiers();
                    if (field.getName().toUpperCase().equals(k.toUpperCase())) {
                        field.setAccessible(true);
                        //region--进行类型判断
                        String type = field.getType().toString();
                        if (type.endsWith("String")) {
                            if (v != null) {
                                v = v.toString();
                            } else {
                                v = "";
                            }
                        }
                        if (type.endsWith("Date")) {
                            v = new Date(v.toString());
                        }
                        if (type.endsWith("Boolean")) {
                            v = Boolean.getBoolean(v.toString());
                        }
                        if (type.endsWith("int")) {
                            v = new Integer(v.toString());
                        }
                        if (type.endsWith("Long")) {
                            v = new Long(v.toString());
                        }
                        field.set(o, v);
                    }
                }
            }
        }
        return o;
    }

    /**
     * * 实体对象转成Map
     * * @param obj 实体对象
     * * @return
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * * Map转成实体对象
     * * @param map map实体对象包含属性
     * * @param clazz 实体对象类型
     * * @return
     */
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
