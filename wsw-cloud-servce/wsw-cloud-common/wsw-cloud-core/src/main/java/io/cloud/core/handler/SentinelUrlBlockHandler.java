package io.cloud.core.handler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import io.cloud.exception.result.Result;
import io.cloud.exception.util.R;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: wsw-cloud-servce
 * @description: 下流服务全局捕获限流，熔断
 * @author: wsw
 * @create: 2020-06-12 15:53
 **/
public class SentinelUrlBlockHandler implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Result result = null;
        if (e instanceof FlowException) {
            System.out.println("接口被限流了");
            result = R.error(-1, "接口被限流了");
        } else if (e instanceof DegradeException) {
            System.out.println("接口被降级了");
            result = R.error(-2, "接口被降级了");
        } else if (e instanceof ParamFlowException) {
            System.out.println("接口被热点限流了");
            result = R.error(-2, "接口被热点限流了");
        } else if (e instanceof AuthorityException) {
            System.out.println("接口被授权规则限制访问了");
            result = R.error(-2, "接口被授权规则限制访问了");
        } else if (e instanceof SystemBlockException) {
            System.out.println("接口被系统规则限制了了");
            result = R.error(-2, "接口被系统规则限制了了");
        }
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }

}
