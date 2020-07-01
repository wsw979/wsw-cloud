package io.cloud.auth.api.code.impl;

import io.cloud.auth.api.code.CodeService;
import io.cloud.auth.common.dtl.ImageCode;
import io.cloud.auth.common.evt.MessageCodeEvt;
import io.cloud.auth.common.util.ImageCodeUtil;
import io.cloud.data.constant.AuthConstants;
import io.cloud.data.constant.CommonConstant;
import io.cloud.data.enums.NumEnum;
import io.cloud.data.util.RandomUtil;
import io.cloud.exception.result.Result;
import io.cloud.exception.util.R;
import io.cloud.redis.constant.KeyConstant;
import io.cloud.redis.util.RedisStringUtil;
import io.cloud.sms.entity.CodeMessage;
import io.cloud.sms.enums.CodeEnum;
import io.cloud.sms.evt.CodeMessageEvt;
import io.cloud.sms.properties.CodeProperties;
import io.cloud.sms.service.ICodeMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    private CodeProperties codeProperties;
    private RedisStringUtil redisStringUtil;


    @Override
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //生成imageCode对象
        ImageCode imageCode = ImageCodeUtil.createImageCode();
        //将图形验证码存入到session中
        request.getSession().setAttribute(AuthConstants.IMAGE_CODE, imageCode);
        //将生成的图片写到接口的响应中
        ImageIO.write(imageCode.getImage(), CommonConstant.SUFFIX_JPEG, response.getOutputStream());
        //
        log.info("--------------------------" + imageCode.getCode());
    }

    @Override
    public Result getMessageCode(MessageCodeEvt evt) {
        boolean flag = false;
        String code = RandomUtil.generateNumber(6);
        if (evt.getSmsNumType().equals(NumEnum.ONE.getK())) {
            //手机号
            CodeMessageEvt codeMessageEvt = new CodeMessageEvt();
            BeanUtils.copyProperties(evt, codeMessageEvt);
            codeMessageEvt.setSmsCode(code);
            codeMessageEvt.setSmsType(NumEnum.ONE.getK());
            boolean save = codeMessageService.save(codeMessageEvt, codeProperties, CodeEnum.LOGIN);
            if (!save) {
                CodeMessage codeMessage = new CodeMessage();
                BeanUtils.copyProperties(evt, codeMessage);
                codeMessageService.save(codeMessage);
            }
            String key = KeyConstant.CODE_MESSAGE + CodeEnum.LOGIN + ":" + evt.getSmsPhone() + ":" + evt.getSmsSource();
            flag = redisStringUtil.set(key, code, codeProperties.getOverdueTime(), TimeUnit.SECONDS);
        } else {
            //邮箱
        }
        return flag ? R.success() : R.error();
    }

}
