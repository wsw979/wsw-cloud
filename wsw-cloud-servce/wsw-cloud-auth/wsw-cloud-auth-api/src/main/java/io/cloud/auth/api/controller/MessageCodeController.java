package io.cloud.auth.api.controller;

import io.cloud.auth.common.evt.MessageCodeEvt;
import io.cloud.data.annotation.WswRestController;
import io.cloud.exception.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @program: wsw-cloud-servce
 * @description: 获取验证码
 * @author: wsw
 * @create: 2020-06-30 17:05
 **/
@Slf4j
@AllArgsConstructor
@Api(tags = "验证码")
@WswRestController(path = "/messageCode")
public class MessageCodeController {

    @PostMapping("/codeByPhone")
    @ApiOperation(value = "获取验证码", tags = "根据手机号获取验证码")
    public Result getMessageCode(@ApiParam("验证码") @Valid @RequestBody MessageCodeEvt evt){
        return null;
    }

}
