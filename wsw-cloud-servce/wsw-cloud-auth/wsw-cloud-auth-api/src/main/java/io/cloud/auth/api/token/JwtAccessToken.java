package io.cloud.auth.api.token;

import com.alibaba.fastjson.JSONObject;
import io.cloud.auth.common.entity.BaseUser;
import io.cloud.data.constant.ConfigConstant;
import io.cloud.data.util.JsonUtil;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-28 17:01
 **/
public class JwtAccessToken extends JwtAccessTokenConverter {

    /**
     * 生成token
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        // 设置额外用户信息
        if(authentication.getPrincipal() instanceof BaseUserDetail) {
            BaseUser baseUser = ((BaseUserDetail) authentication.getPrincipal()).getBaseUser();
            // 将用户信息添加到token额外信息中
            defaultOAuth2AccessToken.getAdditionalInformation().put(ConfigConstant.USER_HEADER, JSONObject.parseObject(JSONObject.toJSONString(baseUser)));
        }
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }

    /**
     * 解析token
     * @param value
     * @param map
     * @return
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map){
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        return oauth2AccessToken;
    }

    private void convertData(OAuth2AccessToken accessToken,  Map<String, ?> map) {
        accessToken.getAdditionalInformation().put(ConfigConstant.USER_HEADER,convertUserData(map.get(ConfigConstant.USER_HEADER)));
    }

    private BaseUser convertUserData(Object map) {
        String json = JsonUtil.deserializer(map);
        BaseUser user = JsonUtil.serializable(json, BaseUser.class);
        return user;
    }

}
