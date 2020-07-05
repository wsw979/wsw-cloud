package io.cloud.auth.api.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-03 23:47
 **/
public class PhoneCodeAuthenticationToken extends AuthenticationToken {

    public PhoneCodeAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public PhoneCodeAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
