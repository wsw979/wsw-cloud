package io.cloud.user.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cloud.user.common.base.LoginUserInfo;
import io.cloud.user.common.entity.AdminUser;
import io.cloud.user.common.vo.app.AdminUserVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * ADMIN用户 服务类
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
public interface IAdminUserService extends IService<AdminUser> {

    LoginUserInfo loginAdminUser(HttpServletRequest request);

    AdminUserVo getUserByPhone(String phone);

}
