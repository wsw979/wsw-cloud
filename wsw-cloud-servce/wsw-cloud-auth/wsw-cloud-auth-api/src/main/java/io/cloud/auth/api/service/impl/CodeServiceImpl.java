package io.cloud.auth.api.service.impl;

import io.cloud.auth.api.service.CodeService;
import io.cloud.auth.common.dtl.ImageCode;
import io.cloud.auth.common.evt.MessageCodeEvt;
import io.cloud.auth.common.util.ImageCodeUtil;
import io.cloud.data.constant.AuthConstants;
import io.cloud.data.constant.CommonConstant;
import io.cloud.exception.result.Result;
import io.cloud.sms.entity.CodeMessage;
import io.cloud.sms.evt.CodeMessageEvt;
import io.cloud.sms.service.ICodeMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 16:17
 **/
@Slf4j
@AllArgsConstructor
@Service
public class CodeServiceImpl implements CodeService {

    private ICodeMessageService codeMessageService;

    @Override
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //生成imageCode对象
        ImageCode imageCode = ImageCodeUtil.createImageCode();
        //将图形验证码存入到session中
        request.getSession().setAttribute(AuthConstants.IMAGE_CODE,imageCode);
        //将生成的图片写到接口的响应中
        ImageIO.write(imageCode.getImage(), CommonConstant.SUFFIX_JPEG,response.getOutputStream());
        //
        log.info("--------------------------" + imageCode.getCode());
    }

    @Override
    public Result getMessageCode(MessageCodeEvt evt) {

        return null;
    }
}
