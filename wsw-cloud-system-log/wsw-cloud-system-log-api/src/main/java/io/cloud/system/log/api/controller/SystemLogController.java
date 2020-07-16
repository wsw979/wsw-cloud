package io.cloud.system.log.api.controller;


import io.cloud.data.annotation.WswRestController;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wsw
 * @since 2020-07-15
 */
@Slf4j
@AllArgsConstructor
@Api(tags = "系统操作日志")
@WswRestController(path = "/systemLog")
public class SystemLogController {

}
