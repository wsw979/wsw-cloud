package io.cloud.auth.api.service.impl;

import com.alibaba.fastjson.JSON;
import io.cloud.auth.api.service.AuthService;
import io.cloud.auth.common.dtl.JwtProperties;
import io.cloud.auth.common.util.JwtUtil;
import io.cloud.auth.common.vo.JwtUserVo;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-01 16:08
 **/
@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private JwtUtil jwtUtil;

    @Override
    public Result getJwt(JwtUserVo vo) {
        vo.setLoginTime(LocalDateTime.now());
        String json = JSON.toJSONString(vo);
        String token = jwtUtil.buildJWT(json, jwtUtil.getJwtProperties().getOverdueTime());
        return R.success(token);
    }

    @Override
    public Result checkJwt(String token) {
        JwtProperties jwt = jwtUtil.getJwtProperties();
        if (!token.startsWith(jwt.getAuthHeader())) {
            throw new ServiceException(HttpStatus.TOKEN_ERROR);
        }
        boolean flag = jwtUtil.checkJWT(token);
        if (!flag) {
            throw new ServiceException(HttpStatus.TOKEN_ERROR);
        }
        JwtUserVo jwtVo = jwtUtil.checkJWT(token, JwtUserVo.class);
        try {
            if (jwt.getIsRefresh()) {
                if (!jwtUtil.checkJWTTime(token)) {
                    String tokenString = JSON.toJSONString(jwtVo);
                    String tokenResult = jwtUtil.buildJWT(tokenString, jwt.getOverdueTime());
                    token = tokenResult;
                }
            }
        } catch (Exception e) {
            log.error("续期失败：" + e);
            e.printStackTrace();
            return R.error(HttpStatus.TOKEN_ERROR);
        }
        return R.success(token);
    }

}
