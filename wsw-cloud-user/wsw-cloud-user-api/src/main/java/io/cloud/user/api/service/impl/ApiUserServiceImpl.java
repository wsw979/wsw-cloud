package io.cloud.user.api.service.impl;

import io.cloud.user.common.entity.ApiUser;
import io.cloud.user.api.mapper.ApiUserMapper;
import io.cloud.user.api.service.IApiUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.user.common.vo.app.ApiUserVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
@Service
public class ApiUserServiceImpl extends ServiceImpl<ApiUserMapper, ApiUser> implements IApiUserService {

    @Override
    public ApiUserVo getUserByUserName(String userName) {
        return this.baseMapper.getUserByUserName(userName);
    }

    @Override
    public ApiUserVo getUserByPhone(String phone) {
        return this.baseMapper.getUserByPhone(phone);
    }

}
