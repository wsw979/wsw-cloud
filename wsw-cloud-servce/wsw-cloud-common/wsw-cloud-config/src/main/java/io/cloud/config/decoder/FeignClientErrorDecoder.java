package io.cloud.config.decoder;

import com.alibaba.fastjson.JSON;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import io.cloud.exception.InternalException;
import io.cloud.exception.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

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

        try {
            if (response.body() != null) {
                String body = Util.toString(response.body().asReader());
                Result result = JSON.parseObject(body.getBytes("UTF-8"), Result.class);
                return new InternalException(result.getCode(), result.getMsg());
            }
        } catch (Exception var4) {
            log.error(var4.getMessage());
            return new InternalException(var4.getMessage());
        }
        return FeignException.errorStatus(methodKey, response);
    }

}
