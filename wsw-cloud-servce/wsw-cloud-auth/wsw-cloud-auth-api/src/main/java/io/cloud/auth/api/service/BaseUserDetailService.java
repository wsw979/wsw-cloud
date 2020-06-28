package io.cloud.auth.api.service;

import io.cloud.auth.common.entity.BaseUser;
import io.cloud.auth.api.token.BaseUserDetail;
import io.cloud.data.constant.ConfigConstant;
import io.cloud.data.enums.NumEnum;
import io.cloud.data.util.StringUtil;
import io.cloud.exception.ServiceException;
import io.cloud.exception.status.HttpStatus;
import io.cloud.redis.constant.KeyConstant;
import io.cloud.redis.util.RedisCommonUtil;
import io.cloud.redis.util.RedisListUtil;
import io.cloud.user.common.feign.UserServiceFeign;
import io.cloud.user.common.vo.app.AdminUserVo;
import io.cloud.user.common.vo.app.ApiUserVo;
import io.cloud.user.common.vo.app.PermissionListVo;
import io.cloud.user.common.vo.app.RoleListVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-27 23:26
 **/
@Slf4j
public abstract class BaseUserDetailService implements UserDetailsService {

    @Resource
    protected UserServiceFeign userServiceFeign;

    @Autowired
    private RedisListUtil redisListUtil;

    @Autowired
    private RedisCommonUtil redisCommonUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (attributes == null) {
            throw new ServiceException(HttpStatus.AUTH_ERROR);
        }
        HttpServletRequest request = attributes.getRequest();
        String clientId = request.getParameter("client_id");
        String LoginHeader = request.getHeader(ConfigConstant.LOGIN_TYPE);
        List<GrantedAuthority> authorities = new ArrayList<>();
        Long id;
        String userName;
        String credential;
        List<RoleListVo> roleList;
        List<PermissionListVo> permissionList;
        BaseUser baseUser = new BaseUser();
        if (LoginHeader.equals(NumEnum.ONE.getV())) {
            ApiUserVo apiUser = getUser(username, clientId);
            id = apiUser.getId();
            userName = StringUtils.isBlank(apiUser.getMobile()) ? StringUtils.isBlank(apiUser.getEmail()) ? apiUser.getUserName() : apiUser.getEmail() : apiUser.getMobile();
            credential = apiUser.getCredential();
            roleList = userServiceFeign.findRoleList(id).getData();
            BeanUtils.copyProperties(apiUser, baseUser);
        } else {
            AdminUserVo adminUser = getAdminUser(username, clientId);
            id = adminUser.getId();
            userName = adminUser.getMobile();
            credential = adminUser.getCredential();
            roleList = userServiceFeign.findRoleList(id).getData();
            if (roleList.size() < 1) {
                throw new ServiceException("账号正在审核，请联系管理员");
            }
            BeanUtils.copyProperties(adminUser, baseUser);
        }
        //添加角色
        authorities.addAll(roleList.stream().map(r -> new SimpleGrantedAuthority(r.getRoleCode())).collect(Collectors.toList()));
        permissionList = userServiceFeign.findPermissionList(id).getData();
        storePermission(permissionList, id);
        //返回带有用户权限信息的User
        User user = new User(userName, credential, true, true, true, true, authorities);
        return new BaseUserDetail(baseUser,user);
    }

    /**
     * 获取用户
     *
     * @param userName
     * @return
     */
    protected abstract ApiUserVo getUser(String userName, String clientId);

    /**
     * 获取用户
     *
     * @param userName
     * @return
     */
    protected abstract AdminUserVo getAdminUser(String userName, String clientId);

    /**
     * 添加权限到redis
     *
     * @param permissionList
     * @param userId
     */
    private void storePermission(List<PermissionListVo> permissionList, Long userId) {
        String key = StringUtil.buffer(KeyConstant.PROJECT_OAUTH_ACCESS, userId);
        //删除之前的权限
        redisCommonUtil.del(key);
        //添加当前权限
        redisListUtil.lSet(key, permissionList);
    }

}
