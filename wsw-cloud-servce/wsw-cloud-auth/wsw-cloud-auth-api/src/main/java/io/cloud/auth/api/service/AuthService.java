package io.cloud.auth.api.service;

import io.cloud.auth.common.vo.JwtUserVo;
import io.cloud.exception.result.Result;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-01 16:08
 **/
public interface AuthService {

    /**
     * 获取token
     *
     * @param vo
     * @return
     */
    Result getJwt(JwtUserVo vo);

    /**
     * 效验token
     *
     * @param token
     * @return
     */
    Result checkJwt(String token);

}
