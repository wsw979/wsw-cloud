package io.cloud.gateway.service.entity;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description: 路由断言实体类
 * @author: wsw
 * @create: 2020-06-08 10:34
 **/
@Data
public class PredicateEntity {

    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

}
