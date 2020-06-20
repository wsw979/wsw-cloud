package io.cloud.user.api.service.impl;

import io.cloud.user.common.entity.ApiUser;
import io.cloud.user.api.mapper.ApiUserMapper;
import io.cloud.user.api.service.IApiUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}