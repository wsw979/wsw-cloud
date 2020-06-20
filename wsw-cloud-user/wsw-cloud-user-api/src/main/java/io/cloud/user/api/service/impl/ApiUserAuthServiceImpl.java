package io.cloud.user.api.service.impl;

import io.cloud.user.common.entity.ApiUserAuth;
import io.cloud.user.api.mapper.ApiUserAuthMapper;
import io.cloud.user.api.service.IApiUserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户认证方式 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
@Service
public class ApiUserAuthServiceImpl extends ServiceImpl<ApiUserAuthMapper, ApiUserAuth> implements IApiUserAuthService {

}
