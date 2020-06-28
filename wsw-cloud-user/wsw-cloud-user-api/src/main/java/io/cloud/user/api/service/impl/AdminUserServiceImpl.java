package io.cloud.user.api.service.impl;

import io.cloud.user.common.entity.AdminUser;
import io.cloud.user.api.mapper.AdminUserMapper;
import io.cloud.user.api.service.IAdminUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.user.common.vo.app.AdminUserVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ADMIN用户 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements IAdminUserService {

    @Override
    public AdminUserVo getUserByPhone(String phone) {
        return this.baseMapper.getUserByPhone(phone);
    }

}
