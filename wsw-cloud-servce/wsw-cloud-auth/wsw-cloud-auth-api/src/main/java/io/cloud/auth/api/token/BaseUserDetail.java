package io.cloud.auth.api.token;

import io.cloud.auth.common.entity.BaseUser;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @program: wsw-cloud-servce
 * @description: 登录用户详情
 * @author: wsw
 * @create: 2020-06-27 23:31
 **/
public class BaseUserDetail implements UserDetails, CredentialsContainer {
    private static final long serialVersionUID = 1L;
    /**
     * 用户
     */
    private final BaseUser baseUser;
    /**
     * security 对象
     */
    private final User user;
    /**
     * 盐值
     */
    private String salt;

    public BaseUserDetail(BaseUser baseUser, User user) {
        this.baseUser = baseUser;
        this.user = user;
    }

    public BaseUserDetail(BaseUser baseUser, User user, String salt) {
        this.baseUser = baseUser;
        this.user = user;
        this.salt = salt;
    }

    @Override
    public void eraseCredentials() {
        user.eraseCredentials();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public BaseUser getBaseUser() {
        return baseUser;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
