package io.cloud.auth.common.feign.fallback;

import feign.hystrix.FallbackFactory;
import io.cloud.auth.common.entity.BaseUser;
import io.cloud.auth.common.feign.UserFeign;
import io.cloud.auth.common.util.LoginUser;
import io.cloud.exception.InternalException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
import io.cloud.user.common.base.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-18 14:16
 **/
@Slf4j
@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserFeign> {

    @Override
    public UserFeign create(Throwable throwable) {
        return new UserFeign() {
            @Override
            public Result<LoginUserInfo> loginApiUser() {
                LoginUserInfo loginUserInfo = loginUserInfo();
                return R.success(loginUserInfo);
            }

            @Override
            public Result<LoginUserInfo> loginAdminUser() {
                LoginUserInfo loginUserInfo = loginUserInfo();
                return R.success(loginUserInfo);
            }
        };
    }

    private LoginUserInfo loginUserInfo(){
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        BaseUser user = LoginUser.getUser();
        if (user == null) {
            throw new InternalException(HttpStatus.LOGIN_USER_ERROR);
        }
        BeanUtils.copyProperties(user, loginUserInfo);
        return loginUserInfo;
    }
}
