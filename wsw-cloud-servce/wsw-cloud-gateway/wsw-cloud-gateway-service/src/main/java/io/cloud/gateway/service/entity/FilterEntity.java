package io.cloud.gateway.service.entity;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description: 过滤器实体类
 * @author: wsw
 * @create: 2020-06-08 10:34
 **/
@Data
public class FilterEntity {

    /**
     * 过滤器对应的Name
     */
    private String name;

    /**
     * 路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

}
