package io.cloud.auth.api.service;

import io.cloud.exception.ServiceException;
import io.cloud.user.common.vo.app.AdminUserVo;
import io.cloud.user.common.vo.app.ApiUserVo;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-28 14:01
 **/
@Service
public class UsernameUserDetailService extends BaseUserDetailService {

    @Override
    protected ApiUserVo getUser(String userName, String clientId) {
        ApiUserVo user = userServiceFeign.getUserByUserName(userName).getData();
        if(user == null){
            throw new ServiceException("用户不存在");
        }
        return user;
    }

    @Override
    protected AdminUserVo getAdminUser(String userName, String clientId) {
        AdminUserVo user = userServiceFeign.getAdminUserByPhone(userName).getData();
        if(user == null){
            throw new ServiceException("用户不存在");
        }
        return user;
    }
}
