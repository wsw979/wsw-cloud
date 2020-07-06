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

 Date: 06/07/2020 17:37:39
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'seata业务XID日志表' ROW_FORMAT = Dynamic;

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
INSERT INTO `xxl_job_group` VALUES (1, 'xxl-job-executor-sample', '示例执行器', 1, 'http://127.0.0.1:7010,http://127.0.0.1:7011');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
INSERT INTO `xxl_job_info` VALUES (1, 1, '0/5 * * * * ? ', '测试任务1', '2018-11-03 22:21:31', '2020-07-02 15:24:40', 'XXL', '', 'ROUND', 'demoJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '', 0, 0, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 223 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log
-- ----------------------------
INSERT INTO `xxl_job_log` VALUES (1, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:24:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (2, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:24:45', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (3, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:24:50', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (4, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:24:55', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (5, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:00', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (6, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:05', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (7, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:10', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (8, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:15', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (9, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:20', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (10, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:25', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (11, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:30', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (12, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (13, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (14, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:45', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (15, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:50', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (16, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:25:55', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (17, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:00', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (18, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:05', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (19, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:10', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (20, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:15', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (21, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:20', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (22, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:25', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (23, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:30', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (24, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (25, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (26, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:45', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (27, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:50', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (28, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:26:55', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (29, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:00', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (30, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:05', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (31, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:10', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (32, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:15', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (33, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:20', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (34, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:25', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (35, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:30', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (36, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (37, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (38, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:45', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (39, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:50', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (40, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:27:55', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (41, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:00', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (42, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:05', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (43, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:10', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (44, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:15', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (45, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:20', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (46, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:25', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (47, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:30', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (48, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (49, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (50, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:45', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (51, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:50', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (52, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:28:55', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (53, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:00', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (54, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:05', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (55, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:10', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (56, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:15', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (57, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:20', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (58, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:25', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (59, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:30', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (60, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (61, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (62, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:45', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (63, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:50', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (64, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:29:55', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (65, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:00', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (66, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:05', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (67, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:10', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (68, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:15', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (69, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:20', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (70, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:25', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (71, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:30', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (72, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (73, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (74, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:45', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (75, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:50', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (76, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:30:55', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (77, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:00', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (78, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:05', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (79, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:10', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (80, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:15', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (81, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:20', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (82, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:25', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (83, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:30', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (84, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (85, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (86, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:45', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (87, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:50', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (88, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:35:55', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (89, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:36:00', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (90, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:36:05', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (91, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:36:10', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (92, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:36:15', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (93, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:36:20', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (94, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:36:25', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (95, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:43:20', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (96, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:43:25', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (97, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:43:30', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (98, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:43:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (99, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:43:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (100, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:43:45', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (101, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:43:50', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (102, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:43:55', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (103, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:00', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (104, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:05', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (105, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:10', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (106, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:15', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (107, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:20', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (108, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:25', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (109, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:30', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (110, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (111, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (112, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:45', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (113, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:50', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (114, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:44:55', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (115, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:45:00', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (116, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:45:05', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (117, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:45:10', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (118, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:45:15', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (119, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:45:20', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (120, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:45:25', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (121, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:45:30', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (122, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:45:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (123, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:46:45', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (124, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:46:50', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (125, 1, 1, '127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:47:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7011<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7011/run), for url : 127.0.0.1:7011/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (126, 1, 1, '127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:47:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1:7010, 127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1:7010/run), for url : 127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (127, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', 'd', NULL, 0, '2020-07-02 14:49:16', 200, '任务触发类型：手动触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:49:16', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (128, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', 'd', NULL, 0, '2020-07-02 14:50:04', 200, '任务触发类型：手动触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:50:11', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (129, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:52:35', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:52:37', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (130, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:52:40', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:52:40', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (131, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:52:45', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:52:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (132, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:52:50', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:52:50', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (133, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:52:55', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:52:55', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (134, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:00', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:53:00', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (135, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:05', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:53:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (136, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:10', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:53:10', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (137, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:15', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:53:15', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (138, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:20', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:53:20', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (139, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:25', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:53:25', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (140, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:30', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:53:30', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (141, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:35', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:53:35', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (142, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:40', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:53:40', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (143, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:45', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:53:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (144, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:50', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:53:50', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (145, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:53:55', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:53:55', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (146, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:00', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:54:00', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (147, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:05', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:54:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (148, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:10', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:54:10', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (149, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:15', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:54:15', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (150, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:20', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:54:20', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (151, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:25', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:54:25', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (152, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:30', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:54:30', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (153, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:35', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:54:35', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (154, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:40', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:54:40', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (155, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:45', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:54:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (156, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:50', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:54:50', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (157, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:54:55', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:54:55', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (158, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:00', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:55:00', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (159, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:05', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:55:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (160, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:10', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:55:10', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (161, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:15', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:55:15', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (162, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:20', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:55:20', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (163, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:25', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:55:25', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (164, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:30', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:55:30', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (165, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:35', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:55:35', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (166, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:40', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:55:40', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (167, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:45', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:55:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (168, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:50', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:55:50', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (169, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:55:55', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:55:55', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (170, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:00', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:56:00', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (171, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:05', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:56:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (172, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:10', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:56:10', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (173, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:15', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:56:15', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (174, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:20', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:56:20', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (175, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:25', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:56:25', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (176, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:30', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:56:30', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (177, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:35', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:56:35', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (178, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:40', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:56:40', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (179, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:45', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:56:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (180, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:50', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:56:50', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (181, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:56:55', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:56:55', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (182, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:00', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:57:00', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (183, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:05', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:57:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (184, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:10', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:57:10', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (185, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:15', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:57:15', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (186, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:20', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:57:20', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (187, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:25', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:57:25', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (188, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:30', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:57:30', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (189, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:35', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:57:35', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (190, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:40', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:57:40', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (191, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:45', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:57:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (192, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:50', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:57:50', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (193, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:57:55', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:57:55', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (194, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:00', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:58:00', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (195, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:05', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:58:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (196, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:10', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:58:10', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (197, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:15', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:58:15', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (198, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:20', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:58:20', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (199, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:25', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:58:25', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (200, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:30', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:58:30', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (201, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:35', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:58:35', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (202, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:40', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:58:40', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (203, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:45', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:58:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (204, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:50', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:58:50', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (205, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:58:55', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:58:55', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (206, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:00', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:59:00', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (207, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:05', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:59:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (208, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:10', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:59:10', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (209, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:15', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:59:15', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (210, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:20', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:59:20', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (211, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:25', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:59:25', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (212, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:30', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:59:30', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (213, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:35', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:59:35', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (214, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:40', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:59:40', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (215, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:45', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:59:45', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (216, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:50', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 14:59:50', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (217, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 14:59:55', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 14:59:55', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (218, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 15:00:00', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：200<br>msg：null', '2020-07-02 15:00:00', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (219, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 15:00:05', 200, '任务触发类型：Cron触发<br>调度机器：10.43.194.79<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：200<br>msg：null', '2020-07-02 15:00:05', 200, '', 0);
INSERT INTO `xxl_job_log` VALUES (220, 1, 1, NULL, NULL, NULL, NULL, 0, '2020-07-02 15:00:10', 0, NULL, NULL, 0, NULL, 0);
INSERT INTO `xxl_job_log` VALUES (221, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 15:24:30', 500, '任务触发类型：Cron触发<br>调度机器：10.43.168.95<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(Connection refused: connect), for url : http://127.0.0.1:7010/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (222, 1, 1, 'http://127.0.0.1:7011', 'demoJobHandler', '', NULL, 0, '2020-07-02 15:24:35', 500, '任务触发类型：Cron触发<br>调度机器：10.43.168.95<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7011<br>code：500<br>msg：xxl-rpc remoting error(Connection refused: connect), for url : http://127.0.0.1:7011/run', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_log` VALUES (223, 1, 1, 'http://127.0.0.1:7010', 'demoJobHandler', '', NULL, 0, '2020-07-02 15:24:40', 500, '任务触发类型：Cron触发<br>调度机器：10.43.168.95<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://127.0.0.1:7010, http://127.0.0.1:7011]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://127.0.0.1:7010<br>code：500<br>msg：xxl-rpc remoting error(Connection refused: connect), for url : http://127.0.0.1:7010/run', NULL, 0, NULL, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
