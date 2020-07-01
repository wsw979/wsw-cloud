package io.cloud.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cloud.sms.entity.CodeMessage;
import io.cloud.sms.evt.CodeMessageEvt;
import io.cloud.sms.mapper.CodeMessageMapper;
import io.cloud.sms.service.ICodeMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 16:46
 **/
@Service
public class CodeMessageServiceImpl extends ServiceImpl<CodeMessageMapper, CodeMessage> implements ICodeMessageService {

    @Override
    public String save(CodeMessageEvt evt) {
        CodeMessage codeMessage = new CodeMessage();
        BeanUtils.copyProperties(evt,codeMessage);
        codeMessage.setCreateTime(LocalDateTime.now());
        boolean save = this.save(codeMessage);
        return null;
    }

}
