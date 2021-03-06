package io.cloud.auth.api.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @program: wsw-cloud-servce
 * @description: 用户名/手机号/邮箱 |密码 token构造
 * @author: wsw
 * @create: 2020-06-28 15:10
 **/
public class UsernameAuthenticationToken extends AuthenticationToken {

    public UsernameAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UsernameAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
