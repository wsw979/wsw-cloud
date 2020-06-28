package io.cloud.user.api.service.impl;

import io.cloud.user.common.entity.Role;
import io.cloud.user.api.mapper.RoleMapper;
import io.cloud.user.api.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.user.common.vo.app.RoleListVo;
import org.springframework.stereotype.Service;

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

}
