package io.cloud.auth.api.controller;

import io.cloud.auth.api.service.AuthService;
import io.cloud.auth.common.vo.JwtUserVo;
import io.cloud.data.annotation.WswRestController;
import io.cloud.exception.ServiceException;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import io.cloud.exception.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-05-12 22:24
 **/
@Slf4j
@AllArgsConstructor
@Api(tags = "权限鉴定")
@WswRestController(path = "/checkAuth")
public class AuthController {

    private AuthService authService;

    @ApiOperation(value = "获取令牌", notes = "根据用户信息获取令牌")
    @PostMapping("/getJwt")
    public Result getJwt(@ApiParam(value = "vo", name = "用户信息") @Valid @RequestBody JwtUserVo vo) {
        return authService.getJwt(vo);
    }

    @ApiOperation(value = "效验令牌", notes = "效验用户令牌")
    @GetMapping("/checkJwt")
    public Result checkJwt(@RequestParam String token, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        return authService.checkJwt(token);
    }

    @GetMapping("/select1")
    public Result select1() {
        System.out.println("select1 -------------------");
        throw new ServiceException(HttpStatus.FAIL, true);
    }

    @GetMapping("/select2")
    public Result select2() {
        System.out.println("select2 -------------------");
        try {
            Thread.sleep(3500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.success();
    }

    @GetMapping("/select3")
    public Result select3() {
        System.out.println("select3 -------------------");
        throw new ServiceException(HttpStatus.FAIL, true);
    }
}
