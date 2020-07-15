package io.cloud.user.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cloud.user.common.entity.AdminUser;
import io.cloud.user.common.vo.app.AdminUserVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * ADMIN用户 Mapper 接口
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
public interface AdminUserMapper extends BaseMapper<AdminUser> {

    AdminUserVo getUserByPhone(@Param("phone") String phone);
}
