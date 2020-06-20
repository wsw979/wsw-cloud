package io.cloud.user.api.service.impl;

import io.cloud.user.common.entity.ApiMessageCode;
import io.cloud.user.api.mapper.ApiMessageCodeMapper;
import io.cloud.user.api.service.IApiMessageCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wsw
 * @since 2020-06-10
 */
@Service
public class ApiMessageCodeServiceImpl extends ServiceImpl<ApiMessageCodeMapper, ApiMessageCode> implements IApiMessageCodeService {

}
