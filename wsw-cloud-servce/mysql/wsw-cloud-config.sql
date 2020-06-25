/*
 Navicat Premium Data Transfer

 Source Server         : master-8001
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:8001
 Source Schema         : wsw-cloud-config

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 25/06/2020 16:08:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_api_message_code
-- ----------------------------
DROP TABLE IF EXISTS `c_api_message_code`;
CREATE TABLE `c_api_message_code`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '验证码',
  `send_type` tinyint(0) NULL DEFAULT NULL COMMENT '类型（1注册2登录3修改密码）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `is_valid` tinyint(0) NULL DEFAULT 1 COMMENT '是否最新（0否1是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '短信' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_api_role
-- ----------------------------
DROP TABLE IF EXISTS `c_api_role`;
CREATE TABLE `c_api_role`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `create_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(0) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_valid` tinyint(0) NULL DEFAULT 1 COMMENT '是否有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_api_role
-- ----------------------------
INSERT INTO `c_api_role` VALUES (1271278964698173442, NULL, NULL, 23232323, 2323232323, '2020-06-12 10:54:24', '2020-06-12 10:54:27', 1);

-- ----------------------------
-- Table structure for c_api_user
-- ----------------------------
DROP TABLE IF EXISTS `c_api_user`;
CREATE TABLE `c_api_user`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(0) NULL DEFAULT NULL COMMENT '性别（1男2女）',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `salt` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `register` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `is_valid` tinyint(0) NULL DEFAULT 1 COMMENT '是否有效（0注销1正常2禁用）',
  `is_vip` tinyint(0) NULL DEFAULT 0 COMMENT '是否会员（0否1是）',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '会员开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '会员结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_api_user
-- ----------------------------
INSERT INTO `c_api_user` VALUES (1271278964698173442, '18683789594', '18683789594@163.com', 1, NULL, '979', '1993-06-17', NULL, NULL, '2020-06-17 09:50:26', 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271278965759332354, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271279221385416706, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271279222559821825, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271279435664044034, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271279436947501058, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271279666916986881, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271279668208832514, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271286172987113474, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271286173976969218, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271286506518118402, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271286507776409601, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271286728401051650, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271286729445433346, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271286947318591490, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271286948472025090, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271323400607326210, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271323401383272449, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271323401634930689, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);
INSERT INTO `c_api_user` VALUES (1271323401676873730, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL);

-- ----------------------------
-- Table structure for c_api_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `c_api_user_auth`;
CREATE TABLE `c_api_user_auth`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `identity_type` tinyint(0) NULL DEFAULT NULL COMMENT '身份类型（1用户名2手机号3邮箱4微信5支付宝）',
  `identifier` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `credential` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权凭证',
  `verified` tinyint(0) NULL DEFAULT NULL COMMENT '是否已经验证（0否1是）',
  `verified_time` datetime(0) NULL DEFAULT NULL COMMENT '验证时间',
  `is_binding` tinyint(0) NULL DEFAULT 1 COMMENT '是否绑定中（0否1是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户认证方式' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_api_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_api_user_role`;
CREATE TABLE `c_api_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '关联角色',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '关联用户表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `c_gateway_route`;
CREATE TABLE `c_gateway_route`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `service_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务名称',
  `service_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由',
  `predicates` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问路径',
  `filters` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '过滤',
  `order` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '排序',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(0) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_valid` tinyint(0) NULL DEFAULT 1 COMMENT '是否有效（0否1是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '动态路由' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_gateway_route
-- ----------------------------
INSERT INTO `c_gateway_route` VALUES (1272152490133098498, 'gateway', 'lb://wsw-cloud-gateway-api', '[{\"args\":{\"_genkey_0\":\"/config/api/gateway/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"3\"},\"name\":\"StripPrefix\"},{\"name\":\"SwaggerHeaderFilter\"}]', '1', '网关管理服务', NULL, NULL, '2020-06-14 21:02:44', NULL, 1);
INSERT INTO `c_gateway_route` VALUES (1272154640808640513, 'auth', 'lb://wsw-cloud-auth-api', '[{\"args\":{\"_genkey_0\":\"/app/api/auth/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"3\"},\"name\":\"StripPrefix\"},{\"name\":\"SwaggerHeaderFilter\"}]', '2', '权限服务', NULL, NULL, '2020-06-14 21:11:16', '2020-06-17 17:18:14', 1);
INSERT INTO `c_gateway_route` VALUES (1274383603682705409, 'user', 'lb://wsw-cloud-user-api', '[{\"args\":{\"_genkey_0\":\"/app/api/user/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"1\"},\"name\":\"StripPrefix\"},{\"name\":\"SwaggerHeaderFilter\"}]', '3', '用户服务', NULL, NULL, '2020-06-21 00:48:23', NULL, 1);

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(0) NULL DEFAULT NULL,
  `refresh_token_validity` int(0) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('edu_pc_teach', NULL, '$2a$10$hdmA.JdfloV27KRzQ3Xjx.rR9ti.UF/shzooCfv2jVT.0ySNmo3Iy', NULL, 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `AUTH_NAME` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `AUTH_CODE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `AUTH_TYPE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限类型  ',
  `STATUS` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态  1：正常 0 封存',
  `REQUEST_URL` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `PARENT_PERMISSION` bigint(0) NULL DEFAULT NULL COMMENT '父权限',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('2020-02-26 14:09:12', 1, '中台系统', 'CENTER', 'SYSTEM', '1', NULL, -1);
INSERT INTO `permission` VALUES ('2020-02-26 14:10:24', 2, '中台-首页', 'CENTEER_INDEX', 'MENU', '1', NULL, 1);
INSERT INTO `permission` VALUES ('2020-02-26 14:11:08', 3, '中台-用户管理', 'CENTEER_USER', 'MENU', '1', NULL, 1);
INSERT INTO `permission` VALUES ('2020-02-26 14:11:50', 4, '中台-用户列表', 'CENTER_USER_LIST', 'BUTTON', '1', '/user/user/list', 3);
INSERT INTO `permission` VALUES ('2020-02-26 14:13:02', 5, '中台-用户添加', 'CENTER_USER_ADD', 'BUTTON', '1', '/user/user/save', 3);
INSERT INTO `permission` VALUES ('2020-02-26 14:16:04', 6, '中台-用户查看', 'CENTER_USER_VIEW', 'BUTTON', '1', '/user/user/*', 3);
INSERT INTO `permission` VALUES ('2020-03-04 15:33:59', 7, 'IM-发送消息', 'IM-SEND-MESSAGE', 'BUTTON', '1', '/im/send', 3);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `ROLE_CODE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `COMMENT` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注说明',
  `STATUS` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态  1：正常 0 封存',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('2020-02-21 15:20:20', 1, '超级管理员', 'SUPERADMIN', '备注', '1');
INSERT INTO `role` VALUES ('2020-02-26 14:41:20', 2, '中台管理员', 'CENTER_ADMIN', '备注', '1');

-- ----------------------------
-- Table structure for role_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_rel`;
CREATE TABLE `role_permission_rel`  (
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_ID` bigint(0) NULL DEFAULT NULL COMMENT '关联角色',
  `PERMISSION_ID` bigint(0) NULL DEFAULT NULL COMMENT '关联权限',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission_rel
-- ----------------------------
INSERT INTO `role_permission_rel` VALUES ('2020-02-21 17:43:33', 1, 1, 1);
INSERT INTO `role_permission_rel` VALUES ('2020-02-25 10:56:24', 2, 1, 2);
INSERT INTO `role_permission_rel` VALUES ('2020-02-26 14:17:47', 3, 1, 3);
INSERT INTO `role_permission_rel` VALUES ('2020-02-26 14:17:57', 4, 1, 4);
INSERT INTO `role_permission_rel` VALUES ('2020-02-26 14:18:09', 5, 1, 5);
INSERT INTO `role_permission_rel` VALUES ('2020-02-26 14:18:18', 6, 1, 6);
INSERT INTO `role_permission_rel` VALUES ('2020-02-21 17:43:33', 7, 2, 1);
INSERT INTO `role_permission_rel` VALUES ('2020-02-25 10:56:24', 8, 2, 2);
INSERT INTO `role_permission_rel` VALUES ('2020-02-26 14:17:47', 9, 2, 3);
INSERT INTO `role_permission_rel` VALUES ('2020-02-26 14:17:57', 10, 2, 4);
INSERT INTO `role_permission_rel` VALUES ('2020-02-26 14:18:09', 11, 2, 5);
INSERT INTO `role_permission_rel` VALUES ('2020-02-26 14:18:18', 12, 2, 6);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `EMAIL` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `TELEPHONE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `PASSWORD` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `HEADER_URL` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `LOGIN_STATUS` tinyint(0) NULL DEFAULT 1 COMMENT '登录状态  1:允许，0：不允许',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `AK_uq_telephone`(`TELEPHONE`) USING BTREE,
  UNIQUE INDEX `AK_uq_email`(`EMAIL`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2020-02-20 11:21:43', 1, '大仙', 'z', NULL, '$2a$10$XS3tc9SAaavYHcbuNLwMqOqRbNccaCuR8sxru2T2o2o/qy.8Ra9sm', 'http://www.baidu.com', 1);
INSERT INTO `user` VALUES ('2020-02-26 16:12:40', 10, '仙哥', NULL, '13252808069', '$2a$10$EiOjQwXe9A0SZqpIOvfdSuFWPNz2x.UL27wcQYGdSpgPkmOGyuc8u', 'https://www.baidu.com', 1);
INSERT INTO `user` VALUES ('2020-03-09 11:29:09', 11, '11', 'zz', NULL, '$2a$10$oLtrq2gG6ZUgvMCtkaIY6e1Jp.n/Dus0UhgiiBV9gpElXYvdO0tC2', NULL, NULL);

-- ----------------------------
-- Table structure for user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_role_rel`;
CREATE TABLE `user_role_rel`  (
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ID` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_ID` bigint(0) NULL DEFAULT NULL COMMENT '关联角色',
  `USER_ID` bigint(0) NULL DEFAULT NULL COMMENT '关联用户表',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_rel
-- ----------------------------
INSERT INTO `user_role_rel` VALUES ('2020-02-21 17:42:56', 1, 1, 1);
INSERT INTO `user_role_rel` VALUES ('2020-02-26 14:41:46', 2, 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
