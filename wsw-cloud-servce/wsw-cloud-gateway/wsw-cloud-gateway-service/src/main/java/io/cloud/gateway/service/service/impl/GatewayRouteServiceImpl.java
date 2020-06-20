package io.cloud.gateway.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.data.enums.NumEnum;
import io.cloud.gateway.common.entity.GatewayRoute;
import io.cloud.gateway.service.mapper.GatewayRouteMapper;
import io.cloud.gateway.service.service.IGatewayRouteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-12 10:45
 **/
@Service
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements IGatewayRouteService {

    @Override
    public List<GatewayRoute> findList() {
        LambdaQueryWrapper<GatewayRoute> wrapper = new QueryWrapper<GatewayRoute>().lambda().eq(GatewayRoute::getIsValid, NumEnum.ONE.getK());
        return this.list(wrapper);
    }

}
