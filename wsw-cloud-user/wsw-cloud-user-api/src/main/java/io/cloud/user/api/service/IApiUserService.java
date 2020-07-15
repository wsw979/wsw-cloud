package io.cloud.user.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cloud.user.common.base.LoginUserInfo;
import io.cloud.user.common.entity.ApiUser;
import io.cloud.user.common.vo.app.ApiUserVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
public interface IApiUserService extends IService<ApiUser> {

    LoginUserInfo loginApiUser(HttpServletRequest request);

    ApiUserVo getUserByUserName(String userName);

    ApiUserVo getUserByPhone(String phone);

}
