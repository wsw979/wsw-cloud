package io.cloud.user.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cloud.exception.result.Result;
import io.cloud.user.common.entity.Role;
import io.cloud.user.common.evt.admin.RoleEvt;
import io.cloud.user.common.vo.app.RoleListVo;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
public interface IRoleService extends IService<Role> {

    List<RoleListVo> findListByUserId(Long userId);

    Result testSuccess(RoleEvt evt);

    Result testError(RoleEvt evt);
}
