package io.cloud.gateway.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.data.enums.NumEnum;
import io.cloud.data.util.NullUtil;
import io.cloud.exception.InternalException;
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
import io.cloud.user.common.evt.admin.RoleEvt;
import io.cloud.user.common.feign.UserServiceFeign;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements IGatewayRouteService {

    private UserServiceFeign userServiceFeign;

    @Override
    public Result<List<GatewayRouteListVo>> findPageList(GatewayRouteListEvt evt) {
        Page<GatewayRoute> page = new Page<>(evt.getPageIndex(), evt.getPageSize());
        IPage<GatewayRouteListVo> pageList = this.baseMapper.findPageList(page, evt.getServiceId());
        return R.page(pageList.getRecords());
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

    @Override
    @GlobalTransactional(rollbackFor = {Exception.class, ServiceException.class, InternalException.class})
    public Result testSuccess(GatewayRouteEvt evt) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(evt, gatewayRoute);
        gatewayRoute.setCreateTime(LocalDateTime.now());
        boolean save = this.save(gatewayRoute);
        if (save) {
            RoleEvt roleEvt = new RoleEvt();
            roleEvt.setRoleName("test");
            roleEvt.setRoleCode("test");
            return userServiceFeign.testSuccess(roleEvt);
        }
        return R.error();
    }

    @Override
    @GlobalTransactional(rollbackFor = {Exception.class, ServiceException.class, InternalException.class})
    public Result testError(GatewayRouteEvt evt) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(evt, gatewayRoute);
        gatewayRoute.setCreateTime(LocalDateTime.now());
        boolean save = this.save(gatewayRoute);
        if (save) {
            RoleEvt roleEvt = new RoleEvt();
            roleEvt.setRoleName("test");
            roleEvt.setRoleCode("test");
            Result result = userServiceFeign.testError(roleEvt);
            if (!result.getCode().equals(HttpStatus.SUCCESS.getCode())) {
                throw new ServiceException(result.getCode(),result.getMsg());
            }
            return result;
        }
        return R.error();
    }

    @Override
    @GlobalTransactional(rollbackFor = {Exception.class, ServiceException.class, InternalException.class})
    public Result testLocal(GatewayRouteEvt evt) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(evt, gatewayRoute);
        gatewayRoute.setCreateTime(LocalDateTime.now());
        boolean save = this.save(gatewayRoute);
        if (save) {
            GatewayRoute gatewayRoute1 = new GatewayRoute();
            BeanUtils.copyProperties(evt, gatewayRoute1);
            gatewayRoute.setCreateTime(LocalDateTime.now());
            this.save(gatewayRoute1);
            throw new ServiceException(HttpStatus.FAIL);
        }
        return R.error();
    }
}
