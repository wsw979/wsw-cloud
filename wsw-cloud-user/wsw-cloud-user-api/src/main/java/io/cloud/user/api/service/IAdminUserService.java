package io.cloud.user.api.service;

import io.cloud.user.common.entity.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;
import io.cloud.user.common.vo.app.AdminUserVo;

/**
 * <p>
 * ADMIN用户 服务类
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
public interface IAdminUserService extends IService<AdminUser> {

    AdminUserVo getUserByPhone(String phone);

}
