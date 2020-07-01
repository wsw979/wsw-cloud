package io.cloud.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cloud.sms.entity.CodeMessage;
import io.cloud.sms.evt.CodeMessageEvt;
import io.cloud.sms.properties.CodeProperties;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 16:46
 **/
public interface ICodeMessageService extends IService<CodeMessage> {

    boolean save(CodeMessageEvt evt, CodeProperties codeProperties, String type);
}
