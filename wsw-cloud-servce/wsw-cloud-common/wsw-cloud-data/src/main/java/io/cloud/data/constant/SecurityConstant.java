package io.cloud.data.constant;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-09 16:45
 **/
public interface SecurityConstant {

    /**
     * sys_oauth_client_details 表的字段，不包括client_id、client_secret
     */
    String CLIENT_FIELDS = "client_id, client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS + " from c_oauth_client_details";

    /**
     * 默认的查询语句
     */
    String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /**
     * 按条件client_id 查询
     */
    String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

    /**
     * 加密
     */
    String MD5 = "MD5";

    /**
     * access_token
     */
    String ACCESS_TOKEN = "access_token";

    /**
     * access_token
     */
    String ACCESS_TOKEN_TIME = "token_time";

    /**
     * access_token
     */
    String REFRESH_TOKEN = "refresh_token";

    /**
     * access_token
     */
    String REFRESH_TOKEN_TIME = "refresh_token_time";

}
