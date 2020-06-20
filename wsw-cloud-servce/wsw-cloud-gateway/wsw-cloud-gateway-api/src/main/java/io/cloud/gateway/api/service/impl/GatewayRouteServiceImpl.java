package io.cloud.gateway.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.data.enums.NumEnum;
import io.cloud.data.util.NullUtil;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
import io.cloud.gateway.api.mapper.GatewayRouteMapper;
import io.cloud.gateway.api.service.IGatewayRouteService;
import io.cloud.gateway.common.entity.GatewayRoute;
import io.cloud.gateway.common.evt.GatewayRouteEvt;
import io.cloud.gateway.common.evt.GatewayRouteListEvt;
import io.cloud.gateway.common.vo.GatewayRouteDtlVo;
import io.cloud.gateway.common.vo.GatewayRouteListVo;
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
    public Result<List<GatewayRouteListVo>> findPageList(GatewayRouteListEvt evt) {
        Page<GatewayRoute> page = new Page<>(evt.getPageIndex(), evt.getPageSize());
        IPage<GatewayRouteListVo> pageList = this.baseMapper.findPageList(page, evt.getServiceId());
        return R.page(pageList);
    }

    @Override
    public Result<GatewayRouteDtlVo> findById(Long id) {
        if (NullUtil.isNull(id)) {
            throw new ServiceException(HttpStatus.PARAM_ERROR);
        }
        GatewayRouteDtlVo vo = new GatewayRouteDtlVo();
        GatewayRoute gatewayRoute = this.getById(id);
        BeanUtils.copyProperties(gatewayRoute, vo);
        return R.success(vo);
    }

    @Override
    public GatewayRoute saveOrUpdate(GatewayRouteEvt evt) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(evt, gatewayRoute);
        LocalDateTime now = LocalDateTime.now();
        if (NullUtil.isNull(gatewayRoute.getId())) {
            gatewayRoute.setCreateTime(now);
        } else {
            gatewayRoute.setUpdateTime(now);
        }
        return this.saveOrUpdate(gatewayRoute) ? gatewayRoute : null;
    }

    @Override
    public GatewayRoute delete(Long id) {
        if (NullUtil.isNull(id)) {
            throw new ServiceException(HttpStatus.PARAM_ERROR);
        }
        GatewayRoute gatewayRoute = this.getById(id);
        gatewayRoute.setIsValid(NumEnum.ZERO.getK());
        return this.updateById(gatewayRoute) ? gatewayRoute : null;
    }
}
