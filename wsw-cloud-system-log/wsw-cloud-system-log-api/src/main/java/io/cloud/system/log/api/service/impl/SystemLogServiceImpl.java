package io.cloud.system.log.api.service.impl;

import io.cloud.system.log.common.entity.SystemLog;
import io.cloud.system.log.api.mapper.SystemLogMapper;
import io.cloud.system.log.api.service.ISystemLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wsw
 * @since 2020-07-15
 */
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements ISystemLogService {

}
