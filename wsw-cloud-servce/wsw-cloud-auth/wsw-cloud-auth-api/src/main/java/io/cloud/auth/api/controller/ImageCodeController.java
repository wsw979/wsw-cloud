package io.cloud.auth.api.controller;

import io.cloud.auth.common.dtl.ImageCode;
import io.cloud.auth.common.util.ImageCodeUtil;
import io.cloud.data.annotation.WswRestController;
import io.cloud.data.constant.AuthConstants;
import io.cloud.data.constant.CommonConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-30 17:22
 **/
@Slf4j
@AllArgsConstructor
@Api(tags = "图形验证码")
@WswRestController(path = "/imageCode")
public class ImageCodeController {

    @GetMapping(value="/imageCode")
    @ApiOperation(value = "图形验证码", tags = "获取图形验证码")
    public void imageCode(HttpServletRequest request , HttpServletResponse response, HttpSession session) throws IOException {
        //生成imageCode对象
        ImageCode imageCode = ImageCodeUtil.createImageCode();
        //将图形验证码存入到session中
        request.getSession().setAttribute(AuthConstants.IMAGE_CODE,imageCode);
        // 将生成的图片写到接口的响应中
        ImageIO.write(imageCode.getImage(), CommonConstant.SUFFIX_JPEG,response.getOutputStream());
    }

}
