package io.cloud.auth.common.util;/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-29 22:36
 **/

import io.cloud.data.util.StringUtil;
import io.cloud.redis.constant.KeyConstant;
import io.cloud.user.common.entity.Permission;
import io.cloud.user.common.vo.app.PermissionListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-29 22:36
 **/
@Component
public class PermissionUtil {

    @Autowired
    private RedisTemplate<String, PermissionListVo> permissionRedisTemplate;

    /**
     * 获取当前用户的权限集合
     * @return
     */
    public List<PermissionListVo> getPermissions(Long userId){
        String key = StringUtil.buffer(KeyConstant.PROJECT_OAUTH_ACCESS, userId);
        long size = permissionRedisTemplate.opsForList().size(key);
        List<PermissionListVo> permissions = permissionRedisTemplate.opsForList().range(key, 0, size);
        return permissions;
    }

}
