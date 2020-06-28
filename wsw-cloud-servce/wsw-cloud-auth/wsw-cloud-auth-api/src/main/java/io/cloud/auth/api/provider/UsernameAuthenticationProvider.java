package io.cloud.auth.api.provider;

import io.cloud.auth.api.token.BaseUserDetail;
import io.cloud.auth.api.token.UsernameAuthenticationToken;
import io.cloud.data.util.MD5Util;
import io.cloud.data.util.StringUtil;
import io.cloud.exception.status.HttpStatus;
import io.cloud.redis.util.RedisStringUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-28 15:07
 **/
@Slf4j
public class UsernameAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Getter
    @Setter
    private UserDetailsService userDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails var1, Authentication authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            log.error("Authentication failed: no credentials provided");
            throw new BadCredentialsException(HttpStatus.AUTH_ERROR.getMsg());
        }
        //获取用户输入的用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        //认证用户
        BaseUserDetail userDetails = (BaseUserDetail) userDetailsService.loadUserByUsername(username);
        if (null == userDetails) {
            log.error("Authentication failed: no credentials provided");
            throw new BadCredentialsException(HttpStatus.AUTH_ERROR.getMsg());
        }
        String passwordSalt = userDetails.getBaseUser().getSalt() + MD5Util.encode(StringUtil.buffer(username, password));
        String md5PasswordSalt = MD5Util.encodeSalt(passwordSalt);
        // 校验不通过 , 和数据库用户 密码比对
        if (!userDetails.getPassword().equals(md5PasswordSalt)) {
            throw new UsernameNotFoundException(HttpStatus.AUTH_ERROR.getMsg());
        }

    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        UsernameAuthenticationToken result = new UsernameAuthenticationToken(principal, authentication);
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    protected UserDetails retrieveUser(String userName, Authentication var2) throws AuthenticationException {
        UserDetails loadedUser;
        try {
            loadedUser = this.getUserDetailsService().loadUserByUsername(userName);
        } catch (UsernameNotFoundException var6) {
            throw var6;
        } catch (Exception var7) {
            throw new InternalAuthenticationServiceException(var7.getMessage(), var7);
        }

        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        } else {
            return loadedUser;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernameAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
