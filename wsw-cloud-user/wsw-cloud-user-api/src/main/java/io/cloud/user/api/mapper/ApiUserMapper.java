package io.cloud.user.api.mapper;

import io.cloud.user.common.entity.ApiUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cloud.user.common.vo.app.ApiUserVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
public interface ApiUserMapper extends BaseMapper<ApiUser> {

    ApiUserVo getUserByUserName(@Param("userName") String userName);

    ApiUserVo getUserByPhone(@Param("phone") String phone);

}
