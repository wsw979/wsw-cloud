package io.cloud.user.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cloud.user.common.entity.Permission;
import io.cloud.user.common.vo.app.PermissionListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<PermissionListVo> findListByUserId(@Param("userId") Long userId);

}
