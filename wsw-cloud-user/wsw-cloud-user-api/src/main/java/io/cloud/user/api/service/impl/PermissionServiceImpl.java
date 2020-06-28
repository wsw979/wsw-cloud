package io.cloud.user.api.service.impl;

import io.cloud.user.common.entity.Permission;
import io.cloud.user.api.mapper.PermissionMapper;
import io.cloud.user.api.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.user.common.vo.app.PermissionListVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<PermissionListVo> findListByUserId(Long userId) {
        return this.baseMapper.findListByUserId(userId);
    }

}
