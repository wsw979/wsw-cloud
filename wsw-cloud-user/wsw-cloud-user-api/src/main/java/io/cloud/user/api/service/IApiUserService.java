package io.cloud.user.api.service;

import io.cloud.user.common.entity.ApiUser;
import com.baomidou.mybatisplus.extension.service.IService;
import io.cloud.user.common.vo.app.ApiUserVo;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
public interface IApiUserService extends IService<ApiUser> {

    ApiUserVo getUserByUserName(String userName);

    ApiUserVo getUserByPhone(String phone);

}
