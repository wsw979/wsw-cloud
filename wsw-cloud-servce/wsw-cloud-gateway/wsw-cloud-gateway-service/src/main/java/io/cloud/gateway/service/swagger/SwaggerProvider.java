package io.cloud.gateway.service.swagger;

import com.alibaba.fastjson.JSON;
import io.cloud.data.util.BeanUtil;
import io.cloud.redis.constant.KeyConstant;
import io.cloud.redis.util.RedisHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: wsw-cloud-servce
 * @description: swagger聚合
 * @author: wsw
 * @create: 2020-06-04 23:53
 **/
@Component
@EnableConfigurationProperties(SwaggerAggProperties.class)
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    @Resource
    private SwaggerAggProperties swaggerAggProperties;
    @Autowired
    private RedisHashUtil redisHashUtil;

    public SwaggerProvider(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
        this.routeLocator = routeLocator;
        this.gatewayProperties = gatewayProperties;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        Set<String> routes = new HashSet<>();
        //取出Spring Cloud Gateway中的route
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        //获取缓存的节点，只获取有效的route节点
        List<Object> obejcts = redisHashUtil.hValues(KeyConstant.GATEWAY_KEY_PREFIX);
        List<RouteDefinition> routeDefinitionList = JSON.parseArray(obejcts.toString(),RouteDefinition.class);
        routeDefinitionList.stream().filter(
                routeDefinition -> (
                        routes.contains(routeDefinition.getId()) && swaggerAggProperties.isShow(routeDefinition.getId())
                )
        ).forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                .forEach(predicateDefinition -> resources.add(
                    swaggerResource(
                            routeDefinition.getId(),
                            predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", swaggerAggProperties.getApiDocsPath())
                        )
                    )
                )
        );
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(swaggerAggProperties.getSwaggerVersion());
        return swaggerResource;
    }

}