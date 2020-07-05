package io.cloud.auth.api.provider;

import io.cloud.auth.api.token.BaseUserDetail;
import io.cloud.auth.api.token.PhoneCodeAuthenticationToken;
import io.cloud.exception.status.HttpStatus;
import io.cloud.redis.constant.KeyConstant;
import io.cloud.redis.util.RedisStringUtil;
import io.cloud.sms.enums.CodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @create: 2020-07-03 23:48
 **/
@Slf4j
public class PhoneCodeAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Getter
    @Setter
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisStringUtil redisStringUtil;

    @Override
    protected void additionalAuthenticationChecks(UserDetails var1, Authentication authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            log.error("Authentication failed: no credentials provided");
            throw new BadCredentialsException(HttpStatus.AUTH_ERROR.getMsg());
        }
        //获取用户输入的用户名和密码
        String phone = authentication.getName();
        String code = authentication.getCredentials().toString();
        //认证用户
        BaseUserDetail userDetails = (BaseUserDetail) userDetailsService.loadUserByUsername(phone);
        if (null == userDetails) {
            log.error("Authentication failed: no credentials provided");
            throw new UsernameNotFoundException(HttpStatus.AUTH_ERROR.getMsg());
        }
        String key = KeyConstant.CODE_MESSAGE + CodeEnum.LOGIN + ":" + userDetails.getBaseUser().getLoginType() + ":" + phone;
        String redisCode = (String) redisStringUtil.get(key);
        if (StringUtils.isEmpty(redisCode)) {
            log.info("未获取redis存储的key:" + key);
            throw new UsernameNotFoundException("验证码无效或已过期");
        }
        if(!StringUtils.equals(code,redisCode)){
            log.info("验证码不正确:" + key);
            throw new UsernameNotFoundException("验证码不正确");
        }
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        PhoneCodeAuthenticationToken result = new PhoneCodeAuthenticationToken(principal, authentication, user.getAuthorities());
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
        return PhoneCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
