package io.cloud.auth.api.code;

import io.cloud.auth.common.evt.MessageCodeEvt;
import io.cloud.exception.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-07-01 16:17
 **/
public interface CodeService {

    void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException;

    Result getMessageCode(MessageCodeEvt evt);

}
