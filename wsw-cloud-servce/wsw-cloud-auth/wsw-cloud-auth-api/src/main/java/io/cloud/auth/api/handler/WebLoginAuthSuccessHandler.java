package io.cloud.auth.api.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloud.auth.api.properties.AuthServerProperties;
import io.cloud.auth.api.token.BaseUserDetail;
import io.cloud.auth.api.util.ResponseUtil;
import io.cloud.core.filter.HttpHelper;
import io.cloud.auth.common.entity.TokenEntity;
import io.cloud.auth.common.util.TokenUtil;
import io.cloud.data.constant.CommonConstant;
import io.cloud.data.constant.ConfigConstant;
import io.cloud.data.constant.SecurityConstant;
import io.cloud.exception.ServiceException;
import io.cloud.exception.status.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-28 16:16
 **/
@Slf4j
public class WebLoginAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements ResponseUtil<Map> {

    @Autowired
    private ClientDetailsService jdbcClientDetailsService;

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenStore authTokenStore;

    @Autowired
    private TokenEnhancer tokenEnhancer;

    @Autowired
    private AuthServerProperties authServerProperties;

    @Autowired
    private RedisTemplate<String, TokenEntity> tokenEntityRedisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        try {
            Map<String, String> result = createToken(request, authentication);
            getResponseWeb(response, objectMapper, result);
            logger.info("登录成功");
        } catch (Exception e) {
            logger.info(e.getMessage(),e);
            getErrorResponseWeb(response, objectMapper, e.getMessage());
        }
    }

    /**
     * 创建token
     *
     * @param request
     * @param authentication
     */
    private Map<String, String> createToken(HttpServletRequest request, Authentication authentication) {
        String loginType = request.getHeader(ConfigConstant.LOGIN_TYPE);
        String clientId = request.getAttribute(ConfigConstant.CLIENT_ID).toString();
        String clientSecret = request.getAttribute(ConfigConstant.CLIENT_SECRET).toString();
        ClientDetails clientDetails;
        try {
            clientDetails = jdbcClientDetailsService.loadClientByClientId(clientId);
            if (null == clientDetails) {
                throw new UnapprovedClientAuthenticationException("clientId不存在" + clientId);
            }
        } catch (NoSuchClientException e) {
            throw new NoSuchClientException(HttpStatus.CLIENT_ID.getMsg());
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.AUTH_ERROR.getMsg());
        }

        //密码工具 比较secret是否相等
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配" + clientId);
        }
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "password");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        defaultTokenServices.setTokenStore(authTokenStore);
        defaultTokenServices.setAccessTokenValiditySeconds(authServerProperties.getTokenValid());
        //开启刷新功能
        if (authServerProperties.getStartRefresh()) {
            defaultTokenServices.setRefreshTokenValiditySeconds(authServerProperties.getRefreshTokenValid());
        }
        OAuth2AccessToken token = defaultTokenServices.createAccessToken(oAuth2Authentication);
        SimpleDateFormat sdf = new SimpleDateFormat(CommonConstant.DATETIME_FORMAT);
        Map<String, String> result = new HashMap<>();
        result.put(SecurityConstant.ACCESS_TOKEN, ConfigConstant.BEARER_TYPE + token.getValue());
        result.put(SecurityConstant.ACCESS_TOKEN_TIME, sdf.format(token.getExpiration()));
        //开启刷新功能
        if (authServerProperties.getStartRefresh()) {
            //获取刷新Token
            DefaultExpiringOAuth2RefreshToken refreshToken = (DefaultExpiringOAuth2RefreshToken) token.getRefreshToken();
            result.put(SecurityConstant.REFRESH_TOKEN, ConfigConstant.BEARER_TYPE + refreshToken.getValue());
            result.put(SecurityConstant.REFRESH_TOKEN_TIME, sdf.format(refreshToken.getExpiration()));
        }
        //判断token的和方法性
        String id = String.valueOf(((BaseUserDetail) authentication.getPrincipal()).getBaseUser().getId());
        if (!TokenUtil.pushToken(id, loginType, tokenEntityRedisTemplate, token.getValue(), token.getExpiration(), authServerProperties.getMaxClient())) {
            throw new ServiceException("登录限制，同时登录人数过多");
        }
        return result;
    }
}
