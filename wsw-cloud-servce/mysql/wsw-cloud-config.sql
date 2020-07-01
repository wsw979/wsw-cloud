/*
 Navicat Premium Data Transfer

 Source Server         : master
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:8001
 Source Schema         : wsw-cloud-config

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 01/07/2020 21:32:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_admin_staff
-- ----------------------------
DROP TABLE IF EXISTS `c_admin_staff`;
CREATE TABLE `c_admin_staff`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `org_id` bigint(0) NULL DEFAULT NULL COMMENT '公司id',
  `dep_id` bigint(0) NULL DEFAULT NULL COMMENT '部门id',
  `job_id` bigint(0) NULL DEFAULT NULL COMMENT '职位id',
  `user_name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `job_number` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工号',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `create_id` int(0) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` int(0) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_valid` tinyint(0) NULL DEFAULT NULL COMMENT '是否有效（0否1是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_admin_staff
-- ----------------------------

-- ----------------------------
-- Table structure for c_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `c_admin_user`;
CREATE TABLE `c_admin_user`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `credential` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权凭证',
  `salt` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `activate` datetime(0) NULL DEFAULT NULL COMMENT '激活时间',
  `create_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(0) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_valid` tinyint(0) NULL DEFAULT 1 COMMENT '是否有效（0未激活1已激活2已禁用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'ADMIN用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_admin_user
-- ----------------------------

-- ----------------------------
-- Table structure for c_api_user
-- ----------------------------
DROP TABLE IF EXISTS `c_api_user`;
CREATE TABLE `c_api_user`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(0) NULL DEFAULT NULL COMMENT '性别（1男2女）',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `salt` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `register` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `is_valid` tinyint(0) NULL DEFAULT 1 COMMENT '是否有效（0注销1正常2禁用）',
  `is_vip` tinyint(0) NULL DEFAULT 0 COMMENT '是否会员（0否1是）',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '会员开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '会员结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'APP用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_api_user
-- ----------------------------
INSERT INTO `c_api_user` VALUES (1271278964698173442, '979', '18683789594', '18683789594@163.com', 1, NULL, '979', '1993-06-17', NULL, 'HyopmV', '2020-06-17 09:50:26', 1, 1, NULL, NULL);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户认证方式' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_api_user_auth
-- ----------------------------
INSERT INTO `c_api_user_auth` VALUES (1271323401676873730, 1271278964698173442, 3, '18683789594@163.com', 'lpNBHXz4JOnoPhv0Pt3glw==', 1, '2020-06-27 21:39:30', 1);
INSERT INTO `c_api_user_auth` VALUES (1271323401676873731, 1271278964698173442, 2, '18683789594', 'lpNBHXz4JOnoPhv0Pt3glw==', 1, '2020-06-27 21:39:30', 1);
INSERT INTO `c_api_user_auth` VALUES (1271323401676873732, 1271278964698173442, 1, 'wu979', 'lpNBHXz4JOnoPhv0Pt3glw==', 1, '2020-06-27 21:39:30', 1);

-- ----------------------------
-- Table structure for c_code_message
-- ----------------------------
DROP TABLE IF EXISTS `c_code_message`;
CREATE TABLE `c_code_message`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `sms_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '验证码',
  `sms_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sms_email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱号',
  `sms_num_type` tinyint(0) NULL DEFAULT NULL COMMENT '验证号码类型（1手机2邮箱）',
  `sms_type` tinyint(0) NULL DEFAULT NULL COMMENT '验证码类型（1登录2注册3修改密码）',
  `sms_source` tinyint(0) NULL DEFAULT NULL COMMENT '来源（app,web,wx,admin）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '短信验证码' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_code_message
-- ----------------------------
INSERT INTO `c_code_message` VALUES (1278318321985486849, '096504', '18683789594', NULL, 1, 1, 1, '2020-07-01 13:23:32');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '动态路由' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_gateway_route
-- ----------------------------
INSERT INTO `c_gateway_route` VALUES (1272152490133098498, 'gateway', 'lb://wsw-cloud-gateway-api', '[{\"args\":{\"_genkey_0\":\"/gateway/api/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"2\"},\"name\":\"StripPrefix\"},{\"name\":\"SwaggerHeaderFilter\"}]', '1', '网关管理服务', NULL, NULL, '2020-06-14 21:02:44', NULL, 1);
INSERT INTO `c_gateway_route` VALUES (1272154640808640513, 'auth', 'lb://wsw-cloud-auth-api', '[{\"args\":{\"_genkey_0\":\"/auth/api/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"2\"},\"name\":\"StripPrefix\"},{\"name\":\"SwaggerHeaderFilter\"}]', '2', '权限服务', NULL, NULL, '2020-06-14 21:11:16', '2020-06-25 14:42:40', 1);
INSERT INTO `c_gateway_route` VALUES (1274383603682705409, 'user', 'lb://wsw-cloud-user-api', '[{\"args\":{\"_genkey_0\":\"/app/user/api/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"3\"},\"name\":\"StripPrefix\"},{\"name\":\"SwaggerHeaderFilter\"}]', '3', '用户服务', NULL, NULL, '2020-06-21 00:48:23', NULL, 1);

-- ----------------------------
-- Table structure for c_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `c_oauth_client_details`;
CREATE TABLE `c_oauth_client_details`  (
  `client_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(0) NULL DEFAULT NULL,
  `refresh_token_validity` int(0) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_oauth_client_details
-- ----------------------------
INSERT INTO `c_oauth_client_details` VALUES ('wsw-app', '$2a$10$xHouIhBbzK5djjNqnI8/4.G7N.1jgP3ji8abY2Lh.QYi/DxFhSJOy', NULL, NULL, 'password', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for c_permission
-- ----------------------------
DROP TABLE IF EXISTS `c_permission`;
CREATE TABLE `c_permission`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `auth_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `auth_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `auth_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限类型（SYSTEM，MENU，BUTTON）',
  `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父级权限',
  `create_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(0) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_valid` tinyint(1) NULL DEFAULT 1 COMMENT '是否有效（0否1是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_permission
-- ----------------------------
INSERT INTO `c_permission` VALUES (1271278564298171307, '中台系统', 'CENTER', 'SYSTEM', NULL, -1, 1, 1, '2020-06-30 10:10:23', NULL, 1);
INSERT INTO `c_permission` VALUES (1271278564298171308, '中台-网关列表', 'CENTER_GATEWAY_LIST', 'MENU', '/gateway/api/dynamicRoute/findPageList', 1271278564298171307, 1, 1, '2020-06-30 10:12:24', NULL, 1);

-- ----------------------------
-- Table structure for c_role
-- ----------------------------
DROP TABLE IF EXISTS `c_role`;
CREATE TABLE `c_role`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `create_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(0) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_valid` tinyint(0) NULL DEFAULT 1 COMMENT '是否有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_role
-- ----------------------------
INSERT INTO `c_role` VALUES (1271278965678291039, '非会员用户', 'ROLE_USER', 23232323, 2323232323, '2020-06-12 10:54:24', '2020-06-12 10:54:27', 1);

-- ----------------------------
-- Table structure for c_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `c_role_permission`;
CREATE TABLE `c_role_permission`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(0) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_role_permission
-- ----------------------------
INSERT INTO `c_role_permission` VALUES (1271278965678295687, 1271278964698173442, 1271278965678291039, 1271278564298171307);
INSERT INTO `c_role_permission` VALUES (1271278965678295688, 1271278964698173442, 1271278965678291039, 1271278564298171308);

-- ----------------------------
-- Table structure for c_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_user_role`;
CREATE TABLE `c_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '关联角色',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '关联用户表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1271278965657684931 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of c_user_role
-- ----------------------------
INSERT INTO `c_user_role` VALUES (1271278965657684911, 1271278965678291039, 1271278964698173442);

SET FOREIGN_KEY_CHECKS = 1;
