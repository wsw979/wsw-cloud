package io.cloud.user.api.service.impl;

import io.cloud.user.common.entity.ApiRole;
import io.cloud.user.api.mapper.ApiRoleMapper;
import io.cloud.user.api.service.IApiRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
@Service
public class ApiRoleServiceImpl extends ServiceImpl<ApiRoleMapper, ApiRole> implements IApiRoleService {

}
