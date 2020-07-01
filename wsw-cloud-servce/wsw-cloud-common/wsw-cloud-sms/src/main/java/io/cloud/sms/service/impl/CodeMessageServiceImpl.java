package io.cloud.sms.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.exception.ServiceException;
import io.cloud.exception.status.HttpStatus;
import io.cloud.sms.dtl.Sign;
import io.cloud.sms.entity.CodeMessage;
import io.cloud.sms.enums.CodeEnum;
import io.cloud.sms.evt.CodeMessageEvt;
import io.cloud.sms.mapper.CodeMessageMapper;
import io.cloud.sms.properties.CodeProperties;
import io.cloud.sms.service.ICodeMessageService;
import io.cloud.sms.util.Phone;
import io.cloud.sms.util.Sms;
import io.cloud.sms.util.SmsUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 16:46
 **/
@Service
public class CodeMessageServiceImpl extends ServiceImpl<CodeMessageMapper, CodeMessage> implements ICodeMessageService {

    @Override
    public boolean save(CodeMessageEvt evt, CodeProperties codeProperties, String type) {
        List<Sign> templateList = codeProperties.getTemplateList();
        Optional<Sign> first = templateList.stream().filter(template -> template.getType().equals(type)).findFirst();
        if (!first.isPresent()) {
            throw new ServiceException(HttpStatus.MESSAGE_CODE_ERROR);
        }
        Sign sign = first.get();

        Sms sms = new Sms();
        BeanUtils.copyProperties(sign, sms);
        sms.setAccessKey(codeProperties.getAccessKey());
        sms.setAccessSecret(codeProperties.getAccessSecret());

        Phone phone = new Phone();
        BeanUtils.copyProperties(evt, phone);
        SendSmsResponse sendSmsResponse = SmsUtil.sendSms(phone, sms);
        if (sendSmsResponse == null || !sendSmsResponse.getCode().equals(CodeEnum.CODE)) {
            throw new ServiceException(HttpStatus.MESSAGE_CODE_ERROR);
        }

        CodeMessage codeMessage = new CodeMessage();
        BeanUtils.copyProperties(evt, codeMessage);
        codeMessage.setCreateTime(LocalDateTime.now());
        return this.save(codeMessage);
    }

}
