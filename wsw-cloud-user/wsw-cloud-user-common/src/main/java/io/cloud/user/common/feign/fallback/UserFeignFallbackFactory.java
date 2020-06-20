package io.cloud.user.common.feign.fallback;

import cn.hutool.core.map.MapUtil;
import feign.hystrix.FallbackFactory;
import io.cloud.exception.ServiceException;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
import io.cloud.user.common.feign.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: wsw-cloud-user
 * @description:
 * @author: wsw
 * @create: 2020-06-17 10:18
 **/
@Slf4j
@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserFeign> {

    @Override
    public UserFeign create(Throwable throwable) {
        return (id) -> {
            log.error("服务降级{}搜索异常:{}",id, throwable);
            throw new ServiceException(HttpStatus.SERVICE_ERROR);
        };

    }

}
