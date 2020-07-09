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

 Date: 09/07/2020 18:44:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for branch_table
-- ----------------------------
DROP TABLE IF EXISTS `branch_table`;
CREATE TABLE `branch_table`  (
  `branch_id` bigint(0) NOT NULL,
  `xid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `transaction_id` bigint(0) NULL DEFAULT NULL,
  `resource_group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `branch_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(0) NULL DEFAULT NULL,
  `client_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `application_data` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(6) NULL DEFAULT NULL,
  `gmt_modified` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`branch_id`) USING BTREE,
  INDEX `idx_xid`(`xid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分支事务表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'ADMIN用户' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'APP用户' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户认证方式' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '短信验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_code_message
-- ----------------------------
INSERT INTO `c_code_message` VALUES (1278318321985486849, '096504', '18683789594', NULL, 1, 1, 1, '2020-07-01 13:23:32');
INSERT INTO `c_code_message` VALUES (1279968461929574402, NULL, '18683789594', NULL, 1, NULL, NULL, NULL);

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_permission
-- ----------------------------
INSERT INTO `c_permission` VALUES (1271278564298171307, '中台系统', 'CENTER', 'SYSTEM', NULL, -1, 1, 1, '2020-06-30 10:10:23', NULL, 1);
INSERT INTO `c_permission` VALUES (1271278564298171308, '中台-网关列表', 'CENTER_GATEWAY_LIST', 'MENU', '/gateway/api/dynamicRoute/findPageList', 1271278564298171307, 1, 1, '2020-06-30 10:12:24', NULL, 1);
INSERT INTO `c_permission` VALUES (1271278564298171309, '中台-测试成功', 'CENTER_GATEWAY_TEST1', 'BUTTON', '/gateway/api/seataTest/testSuccess', 1271278564298171307, 1, 1, '2020-06-30 10:12:24', NULL, 1);
INSERT INTO `c_permission` VALUES (1271278564298171310, '中台-测试失败', 'CENTER_GATEWAY_TEST2', 'BUTTON', '/gateway/api/seataTest/testError', 1271278564298171307, 1, 1, '2020-06-30 10:12:24', NULL, 1);
INSERT INTO `c_permission` VALUES (1271278564298171311, '中台-本地失败', 'CENTER_GATEWAY_TEST3', 'BUTTON', '/gateway/api/seataTest/testLocal', 1271278564298171307, 1, 1, '2020-06-30 10:12:24', NULL, 1);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_role_permission
-- ----------------------------
INSERT INTO `c_role_permission` VALUES (1271278965678295687, 1271278964698173442, 1271278965678291039, 1271278564298171307);
INSERT INTO `c_role_permission` VALUES (1271278965678295688, 1271278964698173442, 1271278965678291039, 1271278564298171308);
INSERT INTO `c_role_permission` VALUES (1271278965678295689, 1271278964698173442, 1271278965678291039, 1271278564298171309);
INSERT INTO `c_role_permission` VALUES (1271278965678295690, 1271278964698173442, 1271278965678291039, 1271278564298171310);
INSERT INTO `c_role_permission` VALUES (1271278965678295691, 1271278964698173442, 1271278965678291039, 1271278564298171311);

-- ----------------------------
-- Table structure for c_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_user_role`;
CREATE TABLE `c_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '关联角色',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '关联用户表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1271278965657684931 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_user_role
-- ----------------------------
INSERT INTO `c_user_role` VALUES (1271278965657684911, 1271278965678291039, 1271278964698173442);

-- ----------------------------
-- Table structure for global_table
-- ----------------------------
DROP TABLE IF EXISTS `global_table`;
CREATE TABLE `global_table`  (
  `xid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `transaction_id` bigint(0) NULL DEFAULT NULL,
  `status` tinyint(0) NOT NULL,
  `application_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `transaction_service_group` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `transaction_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `timeout` int(0) NULL DEFAULT NULL,
  `begin_time` bigint(0) NULL DEFAULT NULL,
  `application_data` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`xid`) USING BTREE,
  INDEX `idx_gmt_modified_status`(`gmt_modified`, `status`) USING BTREE,
  INDEX `idx_transaction_id`(`transaction_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '全局事务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lock_table
-- ----------------------------
DROP TABLE IF EXISTS `lock_table`;
CREATE TABLE `lock_table`  (
  `row_key` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `xid` varchar(96) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `transaction_id` bigint(0) NULL DEFAULT NULL,
  `branch_id` bigint(0) NOT NULL,
  `resource_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `table_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pk` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`row_key`) USING BTREE,
  INDEX `idx_branch_id`(`branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '全局锁表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(0) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(0) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'seata业务XID日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
INSERT INTO `undo_log` VALUES (5, 2016294033, '192.168.1.6:8091:2016294031', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:17:04', '2020-07-07 15:17:04', NULL);
INSERT INTO `undo_log` VALUES (10, 2016294054, '192.168.1.6:8091:2016294052', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:23:15', '2020-07-07 15:23:15', NULL);
INSERT INTO `undo_log` VALUES (13, 2016294064, '192.168.1.6:8091:2016294062', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:26:20', '2020-07-07 15:26:20', NULL);
INSERT INTO `undo_log` VALUES (16, 2016294075, '192.168.1.6:8091:2016294073', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:27:04', '2020-07-07 15:27:04', NULL);
INSERT INTO `undo_log` VALUES (19, 2016294084, '192.168.1.6:8091:2016294082', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:29:32', '2020-07-07 15:29:32', NULL);
INSERT INTO `undo_log` VALUES (22, 2016294093, '192.168.1.6:8091:2016294091', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:30:07', '2020-07-07 15:30:07', NULL);
INSERT INTO `undo_log` VALUES (25, 2016294106, '192.168.1.6:8091:2016294104', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:31:14', '2020-07-07 15:31:14', NULL);
INSERT INTO `undo_log` VALUES (28, 2016294117, '192.168.1.6:8091:2016294115', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:31:27', '2020-07-07 15:31:27', NULL);
INSERT INTO `undo_log` VALUES (31, 2016294139, '192.168.1.6:8091:2016294124', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:46:16', '2020-07-07 15:46:16', NULL);
INSERT INTO `undo_log` VALUES (33, 2016294136, '192.168.1.6:8091:2016294124', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:46:16', '2020-07-07 15:46:16', NULL);
INSERT INTO `undo_log` VALUES (36, 2016294155, '192.168.1.6:8091:2016294153', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:47:10', '2020-07-07 15:47:10', NULL);
INSERT INTO `undo_log` VALUES (39, 2016294165, '192.168.1.6:8091:2016294163', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 15:48:32', '2020-07-07 15:48:32', NULL);
INSERT INTO `undo_log` VALUES (42, 2016294175, '192.168.1.6:8091:2016294172', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 16:05:30', '2020-07-07 16:05:30', NULL);
INSERT INTO `undo_log` VALUES (45, 2016294184, '192.168.1.6:8091:2016294182', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 16:05:45', '2020-07-07 16:05:45', NULL);
INSERT INTO `undo_log` VALUES (48, 2016294192, '192.168.1.6:8091:2016294190', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 16:06:10', '2020-07-07 16:06:10', NULL);
INSERT INTO `undo_log` VALUES (53, 2016294205, '192.168.1.6:8091:2016294198', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 16:06:44', '2020-07-07 16:06:44', NULL);
INSERT INTO `undo_log` VALUES (54, 2016294200, '192.168.1.6:8091:2016294198', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 16:06:45', '2020-07-07 16:06:45', NULL);
INSERT INTO `undo_log` VALUES (59, 2016294223, '192.168.1.6:8091:2016294221', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 16:09:51', '2020-07-07 16:09:51', NULL);
INSERT INTO `undo_log` VALUES (60, 2016294216, '192.168.1.6:8091:2016294214', 'serializer=jackson', 0x7B7D, 1, '2020-07-07 16:09:52', '2020-07-07 16:09:52', NULL);

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '执行器名称',
  `address_type` tinyint(0) NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
INSERT INTO `xxl_job_group` VALUES (1, 'xxl-job-executor-sample', '示例执行器', 0, NULL);

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `job_group` int(0) NOT NULL COMMENT '执行器主键ID',
  `job_cron` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务执行CRON',
  `job_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报警邮件',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(0) NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(0) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '上次调度时间',
  `trigger_next_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '下次调度时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
INSERT INTO `xxl_job_info` VALUES (6, 1, '* * 1-2 * * ?', '描述', '2020-07-09 18:31:50', '2020-07-09 18:31:50', '979', '', 'FIRST', 'ap-job-handler', 'param', 'SERIAL_EXECUTION', 20, 3, 'BEAN', '', 'GLUE代码初始化', '2020-07-09 18:31:50', '', 0, 0, 0);

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock`  (
  `lock_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
INSERT INTO `xxl_job_lock` VALUES ('schedule_lock');

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `job_group` int(0) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(0) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(0) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(0) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '调度-日志',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(0) NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '执行-日志',
  `alarm_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time`) USING BTREE,
  INDEX `I_handle_code`(`handle_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 252 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(0) NOT NULL DEFAULT 0 COMMENT '运行中-日志数量',
  `suc_count` int(0) NOT NULL DEFAULT 0 COMMENT '执行成功-日志数量',
  `fail_count` int(0) NOT NULL DEFAULT 0 COMMENT '执行失败-日志数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_trigger_day`(`trigger_day`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------
INSERT INTO `xxl_job_log_report` VALUES (1, '2020-07-01 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (2, '2020-06-30 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (3, '2020-06-29 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (4, '2020-07-02 00:00:00', 1, 93, 129);
INSERT INTO `xxl_job_log_report` VALUES (5, '2020-07-03 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (6, '2020-07-06 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (7, '2020-07-05 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (8, '2020-07-04 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (9, '2020-07-07 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (10, '2020-07-08 00:00:00', 0, 0, 0);
INSERT INTO `xxl_job_log_report` VALUES (11, '2020-07-09 00:00:00', 0, 0, 0);

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `job_id` int(0) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `i_g_k_v`(`registry_group`, `registry_key`, `registry_value`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------
INSERT INTO `xxl_job_registry` VALUES (38, 'EXECUTOR', 'ap-xxl-job-executor-sample', 'http://10.43.168.95:7020/', '2020-07-09 18:41:48');

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `role` tinyint(0) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
INSERT INTO `xxl_job_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
