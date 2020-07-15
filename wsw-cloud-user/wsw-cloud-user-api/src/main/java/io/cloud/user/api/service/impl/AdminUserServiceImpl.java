package io.cloud.user.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.data.constant.ConfigConstant;
import io.cloud.user.api.mapper.AdminUserMapper;
import io.cloud.user.api.service.IAdminUserService;
import io.cloud.user.common.base.LoginUserInfo;
import io.cloud.user.common.entity.AdminUser;
import io.cloud.user.common.vo.app.AdminUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
    public LoginUserInfo loginAdminUser(HttpServletRequest request) {
        String id = request.getHeader(ConfigConstant.USER_ID_HEADER);
        AdminUser adminUser = this.getById(Long.valueOf(id));
        LoginUserInfo baseUser = new LoginUserInfo();
        BeanUtils.copyProperties(adminUser, baseUser);
        return baseUser;
    }

    @Override
    public AdminUserVo getUserByPhone(String phone) {
        return this.baseMapper.getUserByPhone(phone);
    }

}
