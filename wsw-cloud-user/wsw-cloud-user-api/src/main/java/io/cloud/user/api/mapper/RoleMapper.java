package io.cloud.user.api.mapper;

import io.cloud.user.common.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cloud.user.common.vo.app.RoleListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<RoleListVo> findListByUserId(@Param("userId") Long userId);

}
