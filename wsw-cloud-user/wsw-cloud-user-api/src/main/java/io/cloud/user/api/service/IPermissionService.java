package io.cloud.user.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cloud.user.common.entity.Permission;
import io.cloud.user.common.vo.app.PermissionListVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
public interface IPermissionService extends IService<Permission> {

    List<PermissionListVo> findListByUserId(Long userId);

}
