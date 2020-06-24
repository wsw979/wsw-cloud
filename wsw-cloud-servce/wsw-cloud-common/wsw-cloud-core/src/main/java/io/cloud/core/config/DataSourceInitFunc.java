//package io.cloud.core.config;
//
//import com.alibaba.cloud.sentinel.SentinelProperties;
//import com.alibaba.cloud.sentinel.datasource.config.DataSourcePropertiesConfiguration;
//import com.alibaba.cloud.sentinel.datasource.config.NacosDataSourceProperties;
//import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
//import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Stream;
//
///**
// * @program: wsw-cloud-servce
// * @description: nacos读取 阿里哨兵规则
// * @author: wsw
// * @create: 2020-06-11 20:35
// **/
//@Slf4j
//@Configuration
//public class DataSourceInitFunc {
//
//
//    @Resource
//    private SentinelProperties sentinelProperties;
//
//    @Bean
//    public DataSourceInitFunc init() throws Exception {
//        Stream<Map.Entry<String, DataSourcePropertiesConfiguration>> entryStream = sentinelProperties.getDatasource().entrySet().stream().filter(map -> map.getValue().getNacos() != null);
//        entryStream.forEach(map -> {
//            NacosDataSourceProperties nacos = map.getValue().getNacos();
//            ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(nacos.getServerAddr(), nacos.getGroupId(), nacos.getDataId(),
//                    source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
//                    }));
//            FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
//        });
//        return new DataSourceInitFunc();
//    }
//
//}
