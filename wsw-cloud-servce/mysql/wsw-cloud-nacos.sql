/*
 Navicat Premium Data Transfer

 Source Server         : master
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:8001
 Source Schema         : wsw-cloud-nacos

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 01/07/2020 23:49:13
*/
CREATE DATABASE IF NOT EXISTS `wsw-cloud-nacos` default CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
use `wsw-cloud-nacos`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'application.yml', 'DEFAULT_GROUP', 'spring:\r\n  mvc:\r\n    servlet:\r\n      #启动时立即初始化 dispatcherServlet，数值表示延迟多久进行自动初始化dispatcherServlet，0是立即初始化\r\n      load-on-startup: 0\r\n  aop:\r\n    proxy-target-class: true\r\n  autoconfigure: \r\n    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \"*\"\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n\r\n#日志\r\nlogging:\r\n  path: classpath*:logback-spring.xml\r\n  #心跳级别提升至 warn\r\n  level:\r\n    com:\r\n      alibaba:\r\n        nacos:\r\n          client:\r\n            naming: warn\r\n\r\n# feign 配置\r\nfeign:\r\n  hystrix:\r\n    enabled: false\r\n  sentinel:\r\n    enabled: true\r\n  okhttp:\r\n    enabled: true\r\n  httpclient:\r\n    enabled: false\r\n  client:\r\n    config:\r\n      default:\r\n        connectTimeout: 3000\r\n        readTimeout: 3000\r\n  compression:\r\n    request:\r\n      #开启请求压缩\r\n      enabled: true\r\n      #设置压缩的数据类型\r\n      mime-types: text/html,application/xml,application/json\r\n      #设置触发压缩的大小下限\r\n      min-request-size: 2048\r\n    response:\r\n      enabled: true\r\n\r\n# hystrix 配置\r\n# hystrix:\r\n#   command:\r\n#     default:\r\n#       execution:\r\n#         timeout:\r\n#           enabled: true\r\n#         isolation:\r\n#           strategy: SEMAPHORE\r\n#           thread:\r\n#             timeoutInMilliseconds: 3000\r\n\r\n\r\n#请求处理的超时时间\r\nribbon:\r\n  eager-load:\r\n    #开启饥饿加载 解决第一次feign调用失败的问题\r\n    enabled: true             \r\n  ReadTimeout: 3000\r\n  ConnectTimeout: 2000\r\n\r\nwsw:\r\n  ribbon:\r\n    isolation:\r\n      enabled: true', '3c686488d8986c73b1fb5cbd332e25a9', '2020-04-28 13:03:49', '2020-06-24 01:57:14', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (2, 'redis-config.yml', 'DEFAULT_GROUP', 'spring:\r\n  #redis 配置\r\n  redis:\r\n    #ip\r\n    host: localhost\r\n    #密码\r\n    password:\r\n    #端口\r\n    port: 6379\r\n    #客户端超时时间单位是毫秒 默认是2000\r\n    timeout: 10000\r\n    lettuce:\r\n      pool:\r\n        #最大空闲数\r\n        maxIdle: 300\r\n        #连接池的最大数据库连接数。设为0表示无限制,如果是jedis 2.4以后用redis.maxTotal\r\n        #redis.maxActive=600\r\n        #控制一个pool可分配多少个jedis实例,用来替换上面的redis.maxActive,如果是jedis 2.4以后用该属性\r\n        maxTotal: 1000\r\n        #最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。\r\n        maxWaitMillis: 1000\r\n        #连接的最小空闲时间 默认1800000毫秒(30分钟)\r\n        minEvictableIdleTimeMillis: 300000\r\n        #每次释放连接的最大数目,默认3\r\n        numTestsPerEvictionRun: 1024\r\n        #逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1\r\n        timeBetweenEvictionRunsMillis: 30000\r\n        #是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个\r\n        testOnBorrow: true\r\n        #在空闲时检查有效性, 默认false\r\n        testWhileIdle: true', '5dbf97b196ced9672bb3bcfd8aed7c5b', '2020-06-11 14:40:22', '2020-06-11 14:40:55', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (3, 'datasource-config.yml', 'DEFAULT_GROUP', 'spring:\r\n  # 数据源基础配置\r\n  datasource:\r\n     dynamic:\r\n        datasource:\r\n          #druid\r\n          druid:\r\n            #以下均为默认值\r\n            #数据源配置 wall表示防火墙\r\n            filters: stat,wall,log4j2\r\n            #初始线程数，最小活跃线程，最大活跃线程\r\n            initial-size: 1\r\n            min-idle: 1\r\n            max-active: 5\r\n            # 连接等待时间单位毫秒\r\n            max-wait: 60000\r\n            #配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n            time-between-eviction-runs-millis: 60000\r\n            #配置一个连接在池中最小生存的时间，单位是毫秒\r\n            min-evictable-idle-time-millis: 300000\r\n            validation-query: SELECT 1 FROM DUAL\r\n            test-while-idle: true\r\n            test-on-borrow: false\r\n            test-on-return: false\r\n            #打开PSCache，并且指定每个连接上PSCache的大小\r\n            pool-prepared-statements: false\r\n            max-pool-prepared-statement-per-connection-size: 20\r\n            #通过connectProperties属性来打开mergeSql功能；慢SQL记录\r\n            connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000\r\n\r\ndruid:\r\n  config:\r\n    #是否开启druid监控com.silankeji.mybatisplus\r\n    enable: true\r\n    #白名单 默认所有可访问\r\n    allow: 127.0.0.1\r\n    #黑名单 默认为空\r\n    #deny: ip\r\n    #账号 默认admin\r\n    loginUsername: wsw\r\n    #密码 默认admin123\r\n    loginPassword: wsw-979\r\n    #是否允许点击页面的重置按钮，默认false\r\n    resetEnable: false\r\n    #过滤url 默认 /*\r\n    urlPatterns: /*\r\n    #不过滤的资源,注意*打头必须加双引号，下面值为默认值\r\n    exclusions: \"*.js,*.gif,*.jpg,*.png ,*.css,*.ico,/druid/*\"', '0559de040f83b26b441aa4b7721c45dd', '2020-06-11 14:41:29', '2020-06-11 14:42:00', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (4, 'mybatis-plus.yml', 'DEFAULT_GROUP', '#mybatis-plus\r\nmybatis-plus:\r\n  global-config:\r\n    #uuid\r\n    id-type: 3\r\n    #数据中心id\r\n    datacenterId: 1\r\n    #机器 ID 部分(影响雪花ID)\r\n    workerId: 1\r\n    #刷新mapper 调试神器\r\n    refresh-mapper: true\r\n    #驼峰下划线转换\r\n    db-column-underline: true\r\n    banner: false\r\n  configuration:\r\n    #配置返回数据库(column下划线命名&&返回java实体是驼峰命名)\r\n    #自动匹配无需as（没开启这个，SQL需要写as： select user_id as userId）\r\n    map-underscore-to-camel-case: true\r\n    cache-enabled: false', '87421d5b81ac1fecca210fa1b598e564', '2020-06-11 14:42:46', '2020-06-19 17:13:37', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (5, 'sentinel-config.yml', 'DEFAULT_GROUP', 'spring:\r\n  cloud:\r\n    sentinel:\r\n      #心跳\r\n      eager: true\r\n      #控制台地址\r\n      transport:\r\n        dashboard: ${spring.cloud.client.ip-address}:8888\r\n        #控制台交互端口\r\n        port: 8731\r\n      #数据源\r\n      datasource:\r\n        #自定义名称\r\n        #限流\r\n        flow:\r\n          #配置文件名称，服务名称-类型\r\n          nacos:\r\n            server-addr: ${spring.cloud.client.ip-address}:8848\r\n            data-id: ${spring.application.name}-flow-rules\r\n            group-id: SENTINEL_GROUP\r\n            rule-type: flow\r\n        degrade:\r\n          nacos:\r\n            server-addr: ${spring.cloud.client.ip-address}:8848\r\n            data-id: ${spring.application.name}-degrade-rules\r\n            group-id: SENTINEL_GROUP\r\n            rule-type: degrade', 'e849232cff3ddef8adaa7a0832fe3961', '2020-06-12 06:14:21', '2020-06-24 06:20:40', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (6, 'jwt-config.yml', 'DEFAULT_GROUP', 'wsw:\r\n  auth:\r\n    server:\r\n      max-client: 3\r\n      token-valid: 14400\r\n      force: false\r\n      start-refresh: false\r\n      refresh-token-valid: 14400\r\n      key-path: classpath:/config-service.jks\r\n      alias: wu979Cloud\r\n      secret: wusiweiCloud979\r\n      password: wusiweiCloud979\r\n      manual-refresh-token-valid: 600', '7ad9b0ed4f69345a7938caddbef0f58d', '2020-06-13 02:47:28', '2020-06-30 00:53:01', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (7, 'swagger-config.yml', 'DEFAULT_GROUP', 'wsw:\r\n  swagger-agg:\r\n    api-docs-path: \"/v2/api-docs\"\r\n    swagger-version: \"2.0\"\r\n    generate-routes: user,gateway', 'fac328f3acd997d815bb720d92b7ff70', '2020-06-14 11:17:52', '2020-06-30 08:49:56', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (8, 'exclude-url.yml', 'DEFAULT_GROUP', 'gateway:\r\n  oauth: \r\n    ignored-list:\r\n      - \"/auth/**\"\r\n      - \"/webjars/**\"\r\n      - \"/swagger-resources/**\"\r\n      - \"/**/v2/api-docs\"\r\n      - \"/**/v2/api-docs-ext\"\r\n      - \"/**/doc.html\"\r\n    service-prefix:\r\n      - \"/auth\"\r\n      - \"/gateway\"\r\n      - \"/app\"\r\n      - \"/admin\"', 'b413aa45af393b5df64959fae4c6abb7', '2020-06-25 10:34:49', '2020-06-30 08:47:11', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (9, 'aliyun-code.yml', 'DEFAULT_GROUP', 'ali:\r\n  message:\r\n    overdue-time: 300\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    template-list:\r\n      - {\"type\":\"LOGIN\",\"signName\":\"吴刮墨刀商城\",\"templateCode\":\"SMS_194610875\"}\r\n      - {\"type\":\"REGISTER\",\"signName\":\"吴刮墨刀商城\",\"templateCode\":\"SMS_194610896\"}\r\n      - {\"type\":\"MODIFY\",\"signName\":\"吴刮墨刀商城\",\"templateCode\":\"SMS_194885016\"}\r\n', 'dc1fd5b4e68b3df39f22bc3917704cb6', '2020-07-01 11:14:13', '2020-07-01 13:10:31', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (10, 'wsw-cloud-gateway-service-dev.yml', 'DEV_GROUP', 'spring:\r\n  redis:\r\n    database: 0\r\n  skip:\r\n    urls: /app/api/auth/checkAuth/getJwt\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          enabled: false\r\n          lower-case-service-id: true\r\n    sentinel:\r\n      transport:\r\n        port: 8731\r\n    # 数据源\r\n  datasource:\r\n    dynamic:\r\n      primary: master\r\n      datasource:\r\n        master:\r\n          username: root\r\n          password: root\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${spring.cloud.client.ip-address}:8001/wsw-cloud-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=GMT%2B8\r\n        slaver:\r\n          username: root\r\n          password: root\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${spring.cloud.client.ip-address}:8002/wsw-cloud-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=GMT%2B8', 'ed9ba1194e5f4631a645084a683d4e3c', '2020-06-25 10:35:53', '2020-06-25 10:45:35', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (11, 'wsw-cloud-gateway-api-dev.yml', 'DEV_GROUP', 'spring:\r\n  cloud:\r\n    sentinel:\r\n      transport:\r\n        port: 8732\r\n  redis:\r\n    database: 0\r\n  # 数据源\r\n  datasource:\r\n    dynamic:\r\n      primary: master\r\n      datasource:\r\n        master:\r\n          username: root\r\n          password: root\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${spring.cloud.client.ip-address}:8001/wsw-cloud-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=UTC\r\n        slaver:\r\n          username: root\r\n          password: root\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${spring.cloud.client.ip-address}:8002/wsw-cloud-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=UTC\r\n', 'a09497b80358511934cea545a2ebd65a', '2020-06-25 10:36:33', '2020-06-25 10:36:33', NULL, '0:0:0:0:0:0:0:1', '', '', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (12, 'wsw-cloud-auth-api-dev.yml', 'DEV_GROUP', 'spring:\r\n  cloud:\r\n    sentinel:\r\n      transport:\r\n        port: 8733\r\n  redis:\r\n    database: 0\r\n  # 数据源\r\n  datasource:\r\n    dynamic:\r\n      primary: master\r\n      datasource:\r\n        master:\r\n          username: root\r\n          password: root\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${spring.cloud.client.ip-address}:8001/wsw-cloud-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=UTC\r\n        slaver:\r\n          username: root\r\n          password: root\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${spring.cloud.client.ip-address}:8002/wsw-cloud-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=UTC', '1d960200e5a17f66c1d12f532feda717', '2020-06-25 10:37:16', '2020-06-28 09:28:08', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (13, 'wsw-cloud-user-api-dev.yml', 'DEV_GROUP', 'spring:\r\n  cloud:\r\n    sentinel:\r\n      transport:\r\n        port: 8734\r\n  redis:\r\n    database: 0\r\n  # 数据源\r\n  datasource:\r\n    dynamic:\r\n      primary: master\r\n      datasource:\r\n        master:\r\n          username: root\r\n          password: root\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${spring.cloud.client.ip-address}:8001/wsw-cloud-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=UTC\r\n        slaver:\r\n          username: root\r\n          password: root\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          url: jdbc:mysql://${spring.cloud.client.ip-address}:8002/wsw-cloud-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=UTC\r\n', '34bde6e28b358fb13f9b2f8c09ed7f35', '2020-06-30 08:12:27', '2020-06-30 08:12:27', NULL, '0:0:0:0:0:0:0:1', '', '', NULL, NULL, NULL, 'yaml', NULL);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(0) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(0) UNSIGNED NOT NULL,
  `nid` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (8, 1, 'exclude-url.yml', 'DEFAULT_GROUP', '', 'gateway:\r\n  oauth: \r\n    ignored-list:\r\n      - \"/auth/**\"\r\n      - \"/**/v2/api-docs\"\r\n      - \"/**/v2/api-docs-ext\"\r\n      - \"/**/doc.html\"\r\n      - \"/webjars/**\"\r\n    service-prefix:\r\n      - \"/auth\"\r\n      - \"/gateway\"\r\n      - \"/app\"\r\n      - \"/admin\"', '2fb392e3e9845b28a2d716bdc1e1852d', '2010-05-05 00:00:00', '2020-06-30 08:39:54', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (8, 2, 'exclude-url.yml', 'DEFAULT_GROUP', '', 'gateway:\r\n  oauth: \r\n    ignored-list:\r\n      - \"/auth/**\"\r\n      - \"/**/v2/api-docs\"\r\n      - \"/**/v2/api-docs-ext\"\r\n      - \"/**/doc.html\"\r\n    service-prefix:\r\n      - \"/auth\"\r\n      - \"/gateway\"\r\n      - \"/app\"\r\n      - \"/admin\"', 'f6e350a151554b23575b3bd491e52a57', '2010-05-05 00:00:00', '2020-06-30 08:40:27', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (8, 3, 'exclude-url.yml', 'DEFAULT_GROUP', '', 'gateway:\r\n  oauth: \r\n    ignored-list:\r\n      - \"/auth/**\"\r\n      - \"/webjars/**\"\r\n      - \"/**/v2/api-docs\"\r\n      - \"/**/v2/api-docs-ext\"\r\n      - \"/**/doc.html\"\r\n    service-prefix:\r\n      - \"/auth\"\r\n      - \"/gateway\"\r\n      - \"/app\"\r\n      - \"/admin\"', 'e14a8f4ad92bf9415070b43b725c0969', '2010-05-05 00:00:00', '2020-06-30 08:47:00', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (8, 4, 'exclude-url.yml', 'DEFAULT_GROUP', '', 'gateway:\r\n  oauth: \r\n    ignored-list:\r\n      - \"/auth/**\"\r\n      - \"/webjars/**\"\r\n      - \"/**/v2/api-docs\"\r\n      - \"/**/v2/api-docs-ext\"\r\n      - \"/**/doc.html\"\r\n      - \"/swagger-resources/**\"\r\n    service-prefix:\r\n      - \"/auth\"\r\n      - \"/gateway\"\r\n      - \"/app\"\r\n      - \"/admin\"', 'bb868ac72293fbeea27253d3452dd9e0', '2010-05-05 00:00:00', '2020-06-30 08:47:11', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (7, 5, 'swagger-config.yml', 'DEFAULT_GROUP', '', 'wsw:\r\n  swagger-agg:\r\n    api-docs-path: \"/v2/api-docs\"\r\n    swagger-version: \"2.0\"\r\n    generate-routes: auth,gateway', '9b3803d176c36aa2cd8f44f8b5c16a7f', '2010-05-05 00:00:00', '2020-06-30 08:49:56', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 6, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'aliyun:\r\n  message:\r\n    access-key:\r\n    access-secret:\r\n    login:\r\n        sign-name:\r\n        template-code:\r\n    register:\r\n        sign-name:\r\n        template-code:\r\n    modify-password:\r\n        sign-name:\r\n        template-code:', '2637144ed19a803024ae1d4ad7376b23', '2010-05-05 00:00:00', '2020-07-01 11:14:13', NULL, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (9, 7, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'aliyun:\r\n  message:\r\n    access-key:\r\n    access-secret:\r\n    login:\r\n        sign-name:\r\n        template-code:\r\n    register:\r\n        sign-name:\r\n        template-code:\r\n    modify-password:\r\n        sign-name:\r\n        template-code:', '2637144ed19a803024ae1d4ad7376b23', '2010-05-05 00:00:00', '2020-07-01 11:26:21', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 8, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'aliyun:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    login:\r\n        sign-name: \"吴刮墨刀商城\"\r\n        template-code: SMS_194610875\r\n    register:\r\n        sign-name: \"吴刮墨刀商城\"\r\n        template-code:\r\n    modify-password:\r\n        sign-name: \"吴刮墨刀商城\"\r\n        template-code:', '198c9ecfea913d40f2d09634c30d3aa1', '2010-05-05 00:00:00', '2020-07-01 11:45:47', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 9, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'aliyun:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    login: {\"signName\":\"吴刮墨刀商城\",\"templateCode\":\"SMS_194610875\"}\r\n    register:\r\n        sign-name: \"吴刮墨刀商城\"\r\n        template-code:\r\n    modify-password:\r\n        sign-name: \"吴刮墨刀商城\"\r\n        template-code:', 'cee64e056aae8f49c6ef69128ab06197', '2010-05-05 00:00:00', '2020-07-01 11:51:30', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 10, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'aliyun:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    login: \"signName:吴刮墨刀商城,templateCode:SMS_194610875\"\r\n    register:\r\n        sign-name: \"吴刮墨刀商城\"\r\n        template-code:\r\n    modify-password:\r\n        sign-name: \"吴刮墨刀商城\"\r\n        template-code:', 'f7f07d3484d4cf54036481ba4ae0192d', '2010-05-05 00:00:00', '2020-07-01 11:51:36', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 11, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'aliyun:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    login: \"signName:吴刮墨刀商城,templateCode:SMS_194610875\"', 'debedc3dcd3ab14d871fe444d6052030', '2010-05-05 00:00:00', '2020-07-01 11:56:04', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 12, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'aliyun:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    login: \"signName:吴刮墨刀商城,templateCode:SMS_194610875\"\r\n    register: \"signName:吴刮墨刀商城,templateCode:SMS_194610896\"\r\n    modifyPassword: \"signName:吴刮墨刀商城,templateCode:SMS_194885016\"', '764476f5d5b64c04995528b4c7adbc75', '2010-05-05 00:00:00', '2020-07-01 11:58:11', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 13, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'aliyun:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    template-list:\r\n      - \"type:login,signName:吴刮墨刀商城,templateCode:SMS_194610875\"\r\n      - \"type:register,signName:吴刮墨刀商城,templateCode:SMS_194610896\"\r\n      - \"type:modify,signName:吴刮墨刀商城,templateCode:SMS_194885016\"', 'bc7845df8d386bd494fcb1d58c9bfe33', '2010-05-05 00:00:00', '2020-07-01 12:00:13', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 14, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'ali:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    template-list:\r\n      - \"type:login,signName:吴刮墨刀商城,templateCode:SMS_194610875\"\r\n      - \"type:register,signName:吴刮墨刀商城,templateCode:SMS_194610896\"\r\n      - \"type:modify,signName:吴刮墨刀商城,templateCode:SMS_194885016\"', '150d7060914b65c8ead53acb6808626a', '2010-05-05 00:00:00', '2020-07-01 12:02:39', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 15, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'ali:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    template-list:\r\n      - \"type:LOGIN,signName:吴刮墨刀商城,templateCode:SMS_194610875\"\r\n      - \"type:REGISTER,signName:吴刮墨刀商城,templateCode:SMS_194610896\"\r\n      - \"type:MODIFY,signName:吴刮墨刀商城,templateCode:SMS_194885016\"', 'b4b83ef88dccbef4f3a757fe71bf847f', '2010-05-05 00:00:00', '2020-07-01 12:34:15', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 16, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'ali:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    template-list:\r\n      - \"{\"type\":\"LOGIN\",\"signName\":\"吴刮墨刀商城\",\"templateCode\":\"SMS_194610875\"}\"\r\n', 'c350cf48f9ee7489f444241f17fbfcf0', '2010-05-05 00:00:00', '2020-07-01 12:36:08', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 17, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'ali:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    template-list:\r\n      - {\"type\":\"LOGIN\",\"signName\":\"吴刮墨刀商城\",\"templateCode\":\"SMS_194610875\"}\r\n', 'f101bff01db0d95ada8752dea78232cc', '2010-05-05 00:00:00', '2020-07-01 12:36:15', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 18, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'ali:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    template-list:\r\n      - {\"type\":\"LOGIN\",\"signName\":\"吴刮墨刀商城\",\"templateCode\":\"SMS_194610875\"}\r\n', 'f101bff01db0d95ada8752dea78232cc', '2010-05-05 00:00:00', '2020-07-01 12:39:59', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (9, 19, 'aliyun-code.yml', 'DEFAULT_GROUP', '', 'ali:\r\n  message:\r\n    access-key: LTAI4GBnbf8tXiHgmjuCNnNc\r\n    access-secret: rTZzoDN0mPUrEZtXkttWgoIWps6R4y\r\n    template-list:\r\n      - {\"type\":\"LOGIN\",\"signName\":\"吴刮墨刀商城\",\"templateCode\":\"SMS_194610875\"}\r\n      - {\"type\":\"REGISTER\",\"signName\":\"吴刮墨刀商城\",\"templateCode\":\"SMS_194610896\"}\r\n      - {\"type\":\"MODIFY\",\"signName\":\"吴刮墨刀商城\",\"templateCode\":\"SMS_194885016\"}\r\n', 'dd8c0e97689b6bcb78bcae596adc8eee', '2010-05-05 00:00:00', '2020-07-01 13:10:31', NULL, '0:0:0:0:0:0:0:1', 'U', '');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('wsw', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('wsw', '$2a$10$aaic5mTnN8wOfYQX35TTMOkgdAasg8pidOPn0TtvlHtnp3CpmVpqa', 1);

SET FOREIGN_KEY_CHECKS = 1;
