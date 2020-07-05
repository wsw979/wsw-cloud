package io.cloud.auth.api.service;

import io.cloud.user.common.feign.UserServiceFeign;
import io.cloud.user.common.vo.app.AdminUserVo;
import io.cloud.user.common.vo.app.ApiUserVo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 21:28
 **/
@Service
public class PhoneCodeUserDetailService extends BaseUserDetailService {

    @Override
    protected ApiUserVo getUser(String phone, String clientId) {
        ApiUserVo user = userServiceFeign.getAppUserByPhone(phone).getData();
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    @Override
    protected AdminUserVo getAdminUser(String userName, String clientId) {
        return null;
    }
}
