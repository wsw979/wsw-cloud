package io.cloud.user.api.service.impl;

import io.cloud.auth.common.login.ApiUser;
import io.cloud.auth.common.util.LoginUser;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
import io.cloud.user.common.entity.Role;
import io.cloud.user.api.mapper.RoleMapper;
import io.cloud.user.api.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.user.common.evt.admin.RoleEvt;
import io.cloud.user.common.vo.app.RoleListVo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<RoleListVo> findListByUserId(Long userId) {
        return this.baseMapper.findListByUserId(userId);
    }

    @Override
    @GlobalTransactional
    public Result testSuccess(RoleEvt evt) {
        ApiUser apiUser = LoginUser.apiUser();
        Role role = new Role();
        BeanUtils.copyProperties(evt,role);
        role.setCreateId(apiUser.getId());
        role.setCreateTime(LocalDateTime.now());
        return this.save(role) ? R.success() : R.error();
    }

    @Override
    @GlobalTransactional
    public Result testError(RoleEvt evt) {
        ApiUser apiUser = LoginUser.apiUser();
        Role role = new Role();
        BeanUtils.copyProperties(evt,role);
        role.setCreateId(apiUser.getId());
        role.setCreateTime(LocalDateTime.now());
        this.save(role);
        throw new ServiceException(HttpStatus.FAIL);
    }
}
