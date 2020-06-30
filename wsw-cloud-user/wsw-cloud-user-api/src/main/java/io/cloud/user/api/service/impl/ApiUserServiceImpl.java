package io.cloud.user.api.service.impl;

import io.cloud.data.constant.ConfigConstant;
import io.cloud.user.common.base.LoginUserInfo;
import io.cloud.user.common.entity.ApiUser;
import io.cloud.user.api.mapper.ApiUserMapper;
import io.cloud.user.api.service.IApiUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.user.common.vo.app.ApiUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
    public LoginUserInfo loginApiUser(HttpServletRequest request) {
        String id = request.getHeader(ConfigConstant.USER_ID_HEADER);
        ApiUser apiUser = this.getById(Long.valueOf(id));
        LoginUserInfo baseUser = new LoginUserInfo();
        BeanUtils.copyProperties(apiUser,baseUser);
        return baseUser;
    }

    @Override
    public ApiUserVo getUserByUserName(String userName) {
        return this.baseMapper.getUserByUserName(userName);
    }

    @Override
    public ApiUserVo getUserByPhone(String phone) {
        return this.baseMapper.getUserByPhone(phone);
    }

}
