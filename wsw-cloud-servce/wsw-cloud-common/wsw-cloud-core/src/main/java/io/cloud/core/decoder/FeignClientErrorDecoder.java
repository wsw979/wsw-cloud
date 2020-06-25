package io.cloud.core.decoder;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import io.cloud.exception.InternalException;
import io.cloud.exception.constant.ExceptionConstant;
import io.cloud.exception.result.Result;
import io.cloud.exception.status.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-17 14:26
 **/
@Slf4j
@Configuration
public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = new InternalException(HttpStatus.ERROR);
        ObjectMapper mapper = new ObjectMapper();
        //空属性处理
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
        //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //禁止使用int代表enum的order来反序列化enum
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        try {
            String json = Util.toString(response.body().asReader());
            //非业务异常包装成自定义异常类ServiceException
            if (StringUtils.isNotEmpty(json)) {
                if (json.contains(ExceptionConstant.CODE) && json.contains(ExceptionConstant.MSG)) {
                    Result result = mapper.readValue(json, Result.class);
                    exception = new InternalException(result.getCode(), result.getMsg());
                }
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return exception;
    }

}
