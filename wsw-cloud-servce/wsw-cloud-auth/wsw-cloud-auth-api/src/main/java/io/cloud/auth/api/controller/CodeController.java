package io.cloud.auth.api.controller;

import io.cloud.auth.api.code.CodeService;
import io.cloud.auth.common.evt.MessageCodeEvt;
import io.cloud.data.annotation.WswRestController;
import io.cloud.exception.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-30 17:22
 **/
@Slf4j
@AllArgsConstructor
@Api(tags = "验证码")
@WswRestController(path = "/auth")
public class CodeController {

    private CodeService codeService;

    @GetMapping(value = "/imageCode")
    @ApiOperation(value = "图形验证码", tags = "获取图形验证码")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        codeService.imageCode(request, response);
    }


    @PostMapping(value = "/messageCode")
    @ApiOperation(value = "短信验证码", tags = "根据手机号/邮箱获取验证码")
    public Result getMessageCode(@ApiParam("验证码") @Valid @RequestBody MessageCodeEvt evt) {
        return codeService.getMessageCode(evt);
    }
}
