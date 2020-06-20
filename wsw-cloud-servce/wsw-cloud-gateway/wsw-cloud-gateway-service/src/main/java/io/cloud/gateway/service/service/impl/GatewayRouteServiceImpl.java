package io.cloud.gateway.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.data.enums.NumEnum;
import io.cloud.data.util.NullUtil;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
import io.cloud.gateway.common.entity.GatewayRoute;
import io.cloud.gateway.common.evt.GatewayRouteEvt;
import io.cloud.gateway.common.evt.GatewayRouteListEvt;
import io.cloud.gateway.common.vo.GatewayRouteDtlVo;
import io.cloud.gateway.common.vo.GatewayRouteListVo;
import io.cloud.gateway.service.mapper.GatewayRouteMapper;
import io.cloud.gateway.service.service.IGatewayRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
