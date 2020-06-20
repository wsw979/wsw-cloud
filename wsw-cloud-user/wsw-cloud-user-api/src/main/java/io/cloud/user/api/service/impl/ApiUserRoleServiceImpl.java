package io.cloud.user.api.service.impl;

import io.cloud.user.common.entity.ApiUserRole;
import io.cloud.user.api.mapper.ApiUserRoleMapper;
import io.cloud.user.api.service.IApiUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
@Service
public class ApiUserRoleServiceImpl extends ServiceImpl<ApiUserRoleMapper, ApiUserRole> implements IApiUserRoleService {

}
