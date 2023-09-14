/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : clkj-frame

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 03/01/2023 18:10:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history`  (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `script` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `checksum` int(11) NULL DEFAULT NULL,
  `installed_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `installed_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`) USING BTREE,
  INDEX `flyway_schema_history_s_idx`(`success`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history` VALUES (1, '1', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', NULL, 'root', '2021-04-15 12:01:18', 0, 1);

-- ----------------------------
-- Table structure for msg_message
-- ----------------------------
DROP TABLE IF EXISTS `msg_message`;
CREATE TABLE `msg_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(1) NOT NULL COMMENT '1:系统消息  2：个人消息',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '个人信息才有',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `is_read` int(1) NOT NULL DEFAULT 0 COMMENT '0:未读,1:已读',
  `is_del` int(2) NOT NULL DEFAULT 0 COMMENT '0正常，1删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `message_status`(`is_read`, `user_id`, `type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of msg_message
-- ----------------------------
INSERT INTO `msg_message` VALUES (1, 1, 0, '666', '7777', 2, '2020-10-19 11:15:18', 0, 1);
INSERT INTO `msg_message` VALUES (2, 2, 2, '666', '6666', 2, '2020-10-19 17:50:25', 0, 1);
INSERT INTO `msg_message` VALUES (3, 1, 0, '测试', '测试aaa', NULL, '2020-12-15 14:01:08', 0, 0);

-- ----------------------------
-- Table structure for msg_message_user
-- ----------------------------
DROP TABLE IF EXISTS `msg_message_user`;
CREATE TABLE `msg_message_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `is_read` int(2) NOT NULL DEFAULT 0 COMMENT '已读状态 0:未读,1:已读',
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `message_id`(`message_id`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统消息已读未读表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of msg_message_user
-- ----------------------------
INSERT INTO `msg_message_user` VALUES (1, 1, 1, 1, '2020-10-19 17:47:37');

-- ----------------------------
-- Table structure for msg_sms_code
-- ----------------------------
DROP TABLE IF EXISTS `msg_sms_code`;
CREATE TABLE `msg_sms_code`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `type` int(10) NOT NULL COMMENT '类型(1注册,2修改密码)',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `uuid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `expiry_time` datetime(0) NOT NULL COMMENT '过期时间',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '0:未发送,1成功，2失败',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uuid`(`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '短信验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for opt_about_us
-- ----------------------------
DROP TABLE IF EXISTS `opt_about_us`;
CREATE TABLE `opt_about_us`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `contacts` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `qq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `postcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `qrcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '二维码地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '关于我们' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of opt_about_us
-- ----------------------------
INSERT INTO `opt_about_us` VALUES (1, '6667', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for opt_advertisement
-- ----------------------------
DROP TABLE IF EXISTS `opt_advertisement`;
CREATE TABLE `opt_advertisement`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `position` int(2) NULL DEFAULT NULL COMMENT '显示位置 1 首页 2启动页',
  `navigation_id` bigint(20) NULL DEFAULT NULL COMMENT '操作id(跳转板块id)',
  `image` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告图片',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `jump_type` int(2) NULL DEFAULT NULL COMMENT '跳转类型 1 内部跳转 2 外部跳转 3 详情',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_del` int(2) NULL DEFAULT 0 COMMENT '0 正常 1 删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '广告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of opt_advertisement
-- ----------------------------
INSERT INTO `opt_advertisement` VALUES (1, 1, NULL, '0202006111805405748.png', '11', '111', NULL, '2020-06-11 18:05:48', 1, '2020-10-15 15:15:35', 1, '', 1);
INSERT INTO `opt_advertisement` VALUES (2, 1, NULL, '0202006111805405748.png', '12', '111', NULL, '2020-06-11 18:05:48', 1, '2020-10-19 09:54:33', 1, '', 1);

-- ----------------------------
-- Table structure for opt_feedback
-- ----------------------------
DROP TABLE IF EXISTS `opt_feedback`;
CREATE TABLE `opt_feedback`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户id',
  `operate_id` bigint(20) NULL DEFAULT NULL COMMENT '操作id(反馈某个商品或其他)',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人手机号',
  `is_read` int(2) NOT NULL DEFAULT 0 COMMENT '管理员是否已读',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0 未回复 1已回复',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '意见反馈' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for opt_hot_line
-- ----------------------------
DROP TABLE IF EXISTS `opt_hot_line`;
CREATE TABLE `opt_hot_line`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `qrcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for opt_navigations
-- ----------------------------
DROP TABLE IF EXISTS `opt_navigations`;
CREATE TABLE `opt_navigations`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `navigation_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '导航路径',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `status` int(10) NULL DEFAULT NULL COMMENT '是否隐藏(1隐藏 2 不隐藏)',
  `sort_num` bigint(20) NULL DEFAULT NULL COMMENT '排序编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '首页导航表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for opt_partner
-- ----------------------------
DROP TABLE IF EXISTS `opt_partner`;
CREATE TABLE `opt_partner`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `contacts` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `qq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `postcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `qrcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '二维码地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '关于我们' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for opt_terms
-- ----------------------------
DROP TABLE IF EXISTS `opt_terms`;
CREATE TABLE `opt_terms`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) NULL DEFAULT NULL COMMENT '协议类型',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `is_del` int(2) NULL DEFAULT 0 COMMENT '0 正常 1 删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '协议规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of opt_terms
-- ----------------------------
INSERT INTO `opt_terms` VALUES (1, 1, '测试quill', '', '<p>// Import22 the BlockEmbed blot.</p><p>var BlockEmbed = Quill.import(\'blots/block/embed\');</p><p><br></p><p>// Create a new format based off the BlockEmbed.</p><p>class Footer extends BlockEmbed {</p><p><br></p><p>// Handle the creation of the new Footer format.</p><p>// The value will be the HTML that is embedded.</p><p>// By default, the toolbar will show a prompt window to get the value.</p><p>static create(value) {</p><p><br></p><p>// Create the node using the BlockEmbed\'s create method.</p><p>var node = super.create(value);</p><p><br></p><p>// Set the srcdoc attribute to equal the value which will be your html.</p><p>node.setAttribute(\'srcdoc\', value);</p><p><br></p><p>// Add a few other iframe fixes.</p><p>node.setAttribute(\'frameborder\', \'0\');</p><p>node.setAttribute(\'allowfullscreen\', true);</p><p>node.setAttribute(\'width\', \'100%\');</p><p>return node;</p><p>}</p><p><br></p><p>// return the srcdoc attribute to represent the Footer\'s value in quill.</p><p>static value(node) {</p><p>return node.getAttribute(\'srcdoc\');</p>', '2022-06-02 18:17:31', 1, '2022-09-16 16:37:16', 1, 0);

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('ClkjScheduler', 'TASK_1', 'DEFAULT', '0 0/1 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('ClkjScheduler', 'TASK_1', 'DEFAULT', NULL, 'com.clkj.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002D636F6D2E636C6B6A2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000018576916C007874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('ClkjScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('ClkjScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('ClkjScheduler', 'computer061672738276736', 1672740605131, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('ClkjScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', NULL, 1672740660000, 1672740600000, 5, 'WAITING', 'CRON', 1672733477000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002D636F6D2E636C6B6A2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000018576916C007874000D3020302F31202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740004636C6B6A74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES (1, 'testTask', 'clkj', '0 0/1 * * * ?', 0, '参数测试', '2023-01-03 15:38:40');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES (1, 1, 'testTask', 'clkj', 0, NULL, 0, '2023-01-03 17:57:00');
INSERT INTO `schedule_job_log` VALUES (2, 1, 'testTask', 'clkj', 0, NULL, 0, '2023-01-03 17:58:00');
INSERT INTO `schedule_job_log` VALUES (3, 1, 'testTask', 'clkj', 0, NULL, 1, '2023-01-03 17:59:00');
INSERT INTO `schedule_job_log` VALUES (4, 1, 'testTask', 'clkj', 0, NULL, 0, '2023-01-03 18:00:00');
INSERT INTO `schedule_job_log` VALUES (5, 1, 'testTask', 'clkj', 0, NULL, 0, '2023-01-03 18:01:00');
INSERT INTO `schedule_job_log` VALUES (6, 1, 'testTask', 'clkj', 0, NULL, 1, '2023-01-03 18:02:00');
INSERT INTO `schedule_job_log` VALUES (7, 1, 'testTask', 'clkj', 0, NULL, 0, '2023-01-03 18:03:00');
INSERT INTO `schedule_job_log` VALUES (8, 1, 'testTask', 'clkj', 0, NULL, 0, '2023-01-03 18:04:00');
INSERT INTO `schedule_job_log` VALUES (9, 1, 'testTask', 'clkj', 0, NULL, 1, '2023-01-03 18:05:00');
INSERT INTO `schedule_job_log` VALUES (10, 1, 'testTask', 'clkj', 0, NULL, 0, '2023-01-03 18:06:00');
INSERT INTO `schedule_job_log` VALUES (11, 1, 'testTask', 'clkj', 0, NULL, 0, '2023-01-03 18:07:00');
INSERT INTO `schedule_job_log` VALUES (12, 1, 'testTask', 'clkj', 0, NULL, 1, '2023-01-03 18:08:00');
INSERT INTO `schedule_job_log` VALUES (13, 1, 'testTask', 'clkj', 0, NULL, 0, '2023-01-03 18:09:00');
INSERT INTO `schedule_job_log` VALUES (14, 1, 'testTask', 'clkj', 0, NULL, 1, '2023-01-03 18:10:00');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `param_key`(`param_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', 0, '云存储配置信息');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '保存配置', 'com.clkj.modules.sys.controller.SysConfigController.save()', '[{\"id\":2,\"paramKey\":\"男\",\"paramValue\":\"1\",\"remark\":\"\"}]', 495, '127.0.0.1', '2020-01-15 16:54:35');
INSERT INTO `sys_log` VALUES (2, 'admin', '保存角色', 'com.clkj.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[1,2,3,8,9,10,11,4,13,14,15,16,5,6,7,12,17,18,19,-666666],\"createTime\":\"Jan 16, 2020 9:14:57 AM\"}]', 197, '127.0.0.1', '2020-01-16 09:14:58');
INSERT INTO `sys_log` VALUES (3, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[1,2,3,8,9,10,11,4,13,14,15,16,5,6,7,12,17,18,19,-666666]}]', 37, '127.0.0.1', '2020-01-16 09:16:55');
INSERT INTO `sys_log` VALUES (4, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[1,2,3,8,9,10,11,4,13,14,15,16,5,6,7,12,17,18,19,-666666]}]', 38944, '127.0.0.1', '2020-01-16 09:34:29');
INSERT INTO `sys_log` VALUES (5, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[1,2,3,8,9,10,11,4,13,14,15,16,5,6,7,12,17,18,19,-666666]}]', 30, '127.0.0.1', '2020-01-16 09:34:59');
INSERT INTO `sys_log` VALUES (6, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[1,2,3,8,9,10,11,4,13,14,15,16,5,6,7,12,17,18,19,-666666]}]', 15238, '127.0.0.1', '2020-01-16 09:35:36');
INSERT INTO `sys_log` VALUES (7, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,-666666]}]', 31, '127.0.0.1', '2020-01-16 09:51:01');
INSERT INTO `sys_log` VALUES (8, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[3,8,1,2,7]}]', 20, '127.0.0.1', '2020-01-16 10:14:25');
INSERT INTO `sys_log` VALUES (9, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[3,8,19,1,2,7]}]', 19, '127.0.0.1', '2020-01-16 10:15:18');
INSERT INTO `sys_log` VALUES (10, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[3,8,12,13,14,15,16,19,1,2,7]}]', 138, '127.0.0.1', '2020-01-16 11:43:19');
INSERT INTO `sys_log` VALUES (11, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19]}]', 46, '127.0.0.1', '2020-01-16 11:43:26');
INSERT INTO `sys_log` VALUES (12, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19]}]', 36, '127.0.0.1', '2020-01-16 11:44:54');
INSERT INTO `sys_log` VALUES (13, 'admin', '保存菜单', 'com.clkj.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":20,\"parentId\":0,\"name\":\"用户\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"admin\",\"orderNum\":0}]', 62, '127.0.0.1', '2020-06-04 10:49:41');
INSERT INTO `sys_log` VALUES (14, 'admin', '保存菜单', 'com.clkj.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":21,\"parentId\":20,\"name\":\"用户管理\",\"url\":\"uc/user\",\"perms\":\"uc:user:list\",\"type\":1,\"icon\":\"admin\",\"orderNum\":0}]', 45, '127.0.0.1', '2020-06-04 10:52:25');
INSERT INTO `sys_log` VALUES (15, 'admin', '保存菜单', 'com.clkj.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":22,\"parentId\":20,\"name\":\"实名认证\",\"url\":\"uc/certification\",\"perms\":\"uc:certification:list\",\"type\":1,\"icon\":\"log\",\"orderNum\":0}]', 43, '127.0.0.1', '2020-06-04 10:53:28');
INSERT INTO `sys_log` VALUES (16, 'admin', '保存菜单', 'com.clkj.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":23,\"parentId\":20,\"name\":\"用户token\",\"url\":\"uc/usertoken\",\"perms\":\"uc:usertoken:list\",\"type\":1,\"icon\":\"mudedi\",\"orderNum\":0}]', 59, '127.0.0.1', '2020-06-04 10:54:16');
INSERT INTO `sys_log` VALUES (17, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"666\",\"password\":\"73a0e58d18b15d2ad15a8e2a47f83fa74a3adfe37a0f3a9de67fb24ad673d09b\",\"salt\":\"d7IzSGfDFpeO3broiyr0\",\"email\":\"666@163.com\",\"mobile\":\"18888888888\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Oct 16, 2020 3:14:32 PM\"}]', 160, '192.168.0.95', '2020-10-16 15:14:32');
INSERT INTO `sys_log` VALUES (18, 'admin', '修改用户', 'com.clkj.modules.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"6668\",\"password\":\"73a0e58d18b15d2ad15a8e2a47f83fa74a3adfe37a0f3a9de67fb24ad673d09b\",\"salt\":\"d7IzSGfDFpeO3broiyr0\",\"email\":\"666@163.com\",\"mobile\":\"18888888888\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 43, '192.168.0.95', '2020-10-16 15:16:02');
INSERT INTO `sys_log` VALUES (19, 'admin', '保存角色', 'com.clkj.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"小管理\",\"remark\":\"666\",\"createUserId\":1,\"menuIdList\":[20,31,32,33,34,35,105,36,37,38,39,40],\"createTime\":\"Oct 16, 2020 3:17:05 PM\"}]', 77, '192.168.0.95', '2020-10-16 15:17:05');
INSERT INTO `sys_log` VALUES (20, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":2,\"roleName\":\"小管理2\",\"remark\":\"666\",\"createUserId\":1,\"menuIdList\":[20,31,32,33,34,35,105,36,37,38,39,40]}]', 73, '192.168.0.95', '2020-10-16 15:17:12');
INSERT INTO `sys_log` VALUES (21, 'admin', '删除角色', 'com.clkj.modules.sys.controller.SysRoleController.delete()', '[[2]]', 45, '192.168.0.95', '2020-10-16 15:17:20');
INSERT INTO `sys_log` VALUES (22, 'admin', '删除用户', 'com.clkj.modules.sys.controller.SysUserController.delete()', '[[2]]', 43, '192.168.0.95', '2020-10-16 15:21:23');
INSERT INTO `sys_log` VALUES (23, 'admin', '修改菜单', 'com.clkj.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":31,\"parentId\":20,\"name\":\"用户\",\"url\":\"uc/user/user\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', 44, '192.168.0.95', '2020-10-16 15:22:55');
INSERT INTO `sys_log` VALUES (24, 'admin', '修改菜单', 'com.clkj.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":68,\"parentId\":67,\"name\":\"消息\",\"url\":\"msg/message/message\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', 34, '192.168.0.95', '2020-10-16 15:23:24');
INSERT INTO `sys_log` VALUES (25, 'admin', '修改菜单', 'com.clkj.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":73,\"parentId\":67,\"name\":\"短信验证码\",\"url\":\"msg/smscode/smscode\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', 37, '192.168.0.95', '2020-10-16 15:35:24');
INSERT INTO `sys_log` VALUES (26, 'admin', '修改菜单', 'com.clkj.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":73,\"parentId\":67,\"name\":\"短信验证码\",\"url\":\"msg/smscode/smscode\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', 43, '192.168.0.95', '2020-10-16 15:39:00');
INSERT INTO `sys_log` VALUES (27, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":3,\"username\":\"yangliu\",\"password\":\"67c39bc11d4e7f85b5f20954dac1fa19e97e361ecd23de2979d3f85b14d2fa7a\",\"salt\":\"YYKZGzSUpb1wv8NVwhni\",\"email\":\"123456@qq.com\",\"mobile\":\"15102301123\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Oct 16, 2020 3:45:33 PM\"}]', 212, '127.0.0.1', '2020-10-16 15:45:33');
INSERT INTO `sys_log` VALUES (28, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":4,\"username\":\"wy\",\"password\":\"c7202ed00a50df2f209b60ab031025c423c6047ec0372212317582691e58679e\",\"salt\":\"7Wq5nv4QbnR0pIH3XgGh\",\"email\":\"666@163.com\",\"mobile\":\"18883784845\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Oct 16, 2020 3:46:26 PM\"}]', 60, '192.168.0.95', '2020-10-16 15:46:26');
INSERT INTO `sys_log` VALUES (29, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[20,31,32,33,34,35,105,36,37,38,39,40,67,68,69,70,71,72,73,74,75,76,77,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,62,63,64,65,66,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,18]}]', 172, '192.168.0.95', '2020-10-16 15:46:38');
INSERT INTO `sys_log` VALUES (30, 'admin', '保存配置', 'com.clkj.modules.sys.controller.SysConfigController.save()', '[{\"id\":3,\"paramKey\":\"TIME\",\"paramValue\":\"上班时间\",\"remark\":\"8:30\"}]', 746, '192.168.0.95', '2020-10-16 15:52:59');
INSERT INTO `sys_log` VALUES (31, 'admin', '修改配置', 'com.clkj.modules.sys.controller.SysConfigController.update()', '[{\"id\":3,\"paramKey\":\"TIME\",\"paramValue\":\"8:30\",\"remark\":\"上班时间\"}]', 95, '192.168.0.95', '2020-10-16 15:53:12');
INSERT INTO `sys_log` VALUES (32, 'admin', '修改配置', 'com.clkj.modules.sys.controller.SysConfigController.update()', '[{\"id\":3,\"paramKey\":\"TIME\",\"paramValue\":\"8:30\",\"remark\":\"上班时间\"}]', 38, '192.168.0.95', '2020-10-16 15:53:13');
INSERT INTO `sys_log` VALUES (33, 'admin', '修改配置', 'com.clkj.modules.sys.controller.SysConfigController.update()', '[{\"id\":3,\"paramKey\":\"TIME\",\"paramValue\":\"8:35\",\"remark\":\"上班时间\"}]', 44, '192.168.0.95', '2020-10-16 15:53:25');
INSERT INTO `sys_log` VALUES (34, 'admin', '修改用户', 'com.clkj.modules.sys.controller.SysUserController.update()', '[{\"userId\":4,\"username\":\"wy\",\"password\":\"98323322244edf17d4d4eca9594b5d38ac98e0b32ab8be2732ad1cdf2059ff3b\",\"salt\":\"7Wq5nv4QbnR0pIH3XgGh\",\"email\":\"666@163.com\",\"mobile\":\"18883784845\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 93, '127.0.0.1', '2020-10-16 16:03:09');
INSERT INTO `sys_log` VALUES (35, 'admin', '删除用户', 'com.clkj.modules.sys.controller.SysUserController.delete()', '[[4]]', 33, '127.0.0.1', '2020-10-16 16:06:01');
INSERT INTO `sys_log` VALUES (36, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":5,\"username\":\"wangyu\",\"password\":\"0d89d1723a40b17e9705319e1907105ccc5acddd8de7e1f258fe910b497ae124\",\"salt\":\"E3v4a4bLluOO1XwpZjbh\",\"email\":\"6666@163.com\",\"mobile\":\"18883784845\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Oct 16, 2020 4:06:24 PM\"}]', 55, '127.0.0.1', '2020-10-16 16:06:24');
INSERT INTO `sys_log` VALUES (37, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":6,\"username\":\"666\",\"password\":\"bc43aa60fa06a74ee6508e2b9fe2cf1ecd80337a548ef9c9b2334657efde8556\",\"salt\":\"AzwFS7JI78tg4fxrfnXp\",\"email\":\"6666@qq.com\",\"mobile\":\"18888888888\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Oct 16, 2020 4:33:26 PM\"}]', 28731, '127.0.0.1', '2020-10-16 16:33:26');
INSERT INTO `sys_log` VALUES (38, 'admin', '删除用户', 'com.clkj.modules.sys.controller.SysUserController.delete()', '[[6]]', 50, '127.0.0.1', '2020-10-16 16:35:04');
INSERT INTO `sys_log` VALUES (39, 'admin', '保存角色', 'com.clkj.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":3,\"roleName\":\"222\",\"remark\":\"22\",\"createUserId\":1,\"menuIdList\":[],\"createTime\":\"Oct 16, 2020 5:29:44 PM\"}]', 162, '127.0.0.1', '2020-10-16 17:29:44');
INSERT INTO `sys_log` VALUES (40, 'admin', '删除角色', 'com.clkj.modules.sys.controller.SysRoleController.delete()', '[[3]]', 56, '127.0.0.1', '2020-10-16 17:29:48');
INSERT INTO `sys_log` VALUES (41, 'admin', '删除配置', 'com.clkj.modules.sys.controller.SysConfigController.delete()', '[[3]]', 621, '127.0.0.1', '2020-10-16 17:30:30');
INSERT INTO `sys_log` VALUES (42, 'admin', '保存角色', 'com.clkj.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":4,\"roleName\":\"777\",\"remark\":\"666\",\"createUserId\":1,\"menuIdList\":[],\"createTime\":\"Oct 19, 2020 9:20:28 AM\"}]', 146, '192.168.0.95', '2020-10-19 09:20:28');
INSERT INTO `sys_log` VALUES (43, 'admin', '删除角色', 'com.clkj.modules.sys.controller.SysRoleController.delete()', '[[4]]', 74, '192.168.0.95', '2020-10-19 09:20:32');
INSERT INTO `sys_log` VALUES (44, 'admin', '保存角色', 'com.clkj.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":5,\"roleName\":\"66\",\"remark\":\"666\",\"createUserId\":1,\"menuIdList\":[],\"createTime\":\"Oct 19, 2020 9:31:19 AM\"}]', 52, '192.168.0.95', '2020-10-19 09:31:19');
INSERT INTO `sys_log` VALUES (45, 'admin', '删除角色', 'com.clkj.modules.sys.controller.SysRoleController.delete()', '[[5]]', 47, '192.168.0.95', '2020-10-19 09:32:11');
INSERT INTO `sys_log` VALUES (46, 'wangyu', '删除消息', 'com.clkj.modules.msg.controller.MessageController.delete()', '[[2]]', 40, '192.168.0.95', '2020-10-20 09:28:38');
INSERT INTO `sys_log` VALUES (47, 'wangyu', '删除消息', 'com.clkj.modules.msg.controller.MessageController.delete()', '[[1]]', 41, '192.168.0.95', '2020-10-20 09:28:42');
INSERT INTO `sys_log` VALUES (48, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[20,31,32,33,34,35,105,36,37,38,39,40,67,68,69,70,71,72,73,74,75,76,77,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,62,63,64,65,66]}]', 224, '192.168.0.95', '2020-10-20 09:45:13');
INSERT INTO `sys_log` VALUES (49, 'admin', '修改角色', 'com.clkj.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"管理员\",\"remark\":\"管理员\",\"createUserId\":1,\"menuIdList\":[20,31,32,33,34,35,105,36,37,38,39,40,67,68,69,70,71,72,73,74,75,76,77,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,62,63,64,65,66,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,18]}]', 158, '192.168.0.95', '2020-10-20 09:47:56');
INSERT INTO `sys_log` VALUES (50, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":8,\"username\":\"yu\",\"password\":\"cdd83e530bd4e223f2f67a91e6b194c71512576d3770c6bfa2bc5db61dbad59a\",\"salt\":\"jNotTIvt7C9tFGYhAgAC\",\"email\":\"291337219@qq.com\",\"mobile\":\"13452134544\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Nov 11, 2020 10:46:23 AM\"}]', 306, '127.0.0.1', '2020-11-11 10:46:23');
INSERT INTO `sys_log` VALUES (51, 'admin', '修改用户', 'com.clkj.modules.sys.controller.SysUserController.update()', '[{\"userId\":8,\"username\":\"yu\",\"password\":\"cdd83e530bd4e223f2f67a91e6b194c71512576d3770c6bfa2bc5db61dbad59a\",\"salt\":\"jNotTIvt7C9tFGYhAgAC\",\"email\":\"291337219@qq.com\",\"mobile\":\"13452134544\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 356, '192.168.0.147', '2020-11-25 11:37:35');
INSERT INTO `sys_log` VALUES (52, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":9,\"username\":\"yong\",\"password\":\"df2a9cb2a9b0ecd4c285481d61fa9a32acecb3fb2a816d8a800c8c4f32724f49\",\"salt\":\"xCVl4ZDN4ZKwPxndwUqN\",\"email\":\"15766666666@163.com\",\"mobile\":\"15766666666\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Nov 25, 2020 2:25:45 PM\"}]', 815, '192.168.0.6', '2020-11-25 14:25:46');
INSERT INTO `sys_log` VALUES (53, 'admin', '修改菜单', 'com.clkj.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":31,\"parentId\":20,\"name\":\"用户\",\"url\":\"uc/user/index\",\"type\":1,\"icon\":\"config\",\"orderNum\":6}]', 50, '192.168.0.6', '2020-11-25 14:26:20');
INSERT INTO `sys_log` VALUES (54, 'admin', '删除配置', 'com.clkj.modules.sys.controller.SysConfigController.delete()', '[[2]]', 1564, '192.168.0.6', '2020-11-25 14:26:33');
INSERT INTO `sys_log` VALUES (55, 'admin', '重置密码', 'com.clkj.modules.uc.controller.UcUserController.restPwd()', '[1]', 54, '127.0.0.1', '2020-12-15 14:00:23');
INSERT INTO `sys_log` VALUES (56, 'admin', '重置密码', 'com.clkj.modules.uc.controller.UcUserController.restPwd()', '[1]', 114, '127.0.0.1', '2020-12-15 14:00:27');
INSERT INTO `sys_log` VALUES (57, 'admin', '新增消息', 'com.clkj.modules.msg.controller.MsgMessageController.save()', '[{\"id\":3,\"type\":1,\"userId\":0,\"userIds\":[],\"title\":\"测试\",\"content\":\"测试aaa\",\"createTime\":\"Dec 15, 2020 2:01:08 PM\"}]', 41, '127.0.0.1', '2020-12-15 14:01:08');
INSERT INTO `sys_log` VALUES (58, 'lgx', '保存菜单', 'com.clkj.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":106,\"parentId\":1,\"name\":\"部门管理\",\"url\":\"sys/department/index\",\"perms\":\"\",\"type\":1,\"icon\":\"shouye\",\"orderNum\":0}]', 152, '192.168.3.102', '2021-04-15 16:07:57');
INSERT INTO `sys_log` VALUES (59, 'lgx', '保存菜单', 'com.clkj.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":107,\"parentId\":106,\"name\":\"查看\",\"perms\":\"sys:department:list,sys:department:info\",\"type\":2,\"orderNum\":0}]', 43, '192.168.3.102', '2021-04-15 16:10:28');
INSERT INTO `sys_log` VALUES (60, 'lgx', '保存菜单', 'com.clkj.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":108,\"parentId\":106,\"name\":\"新增\",\"perms\":\"sys:department:save,sys:department:select\",\"type\":2,\"orderNum\":0}]', 49, '192.168.3.102', '2021-04-15 16:11:18');
INSERT INTO `sys_log` VALUES (61, 'lgx', '保存菜单', 'com.clkj.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":109,\"parentId\":106,\"name\":\"修改\",\"perms\":\"sys:department:update,sys:department:select\",\"type\":2,\"orderNum\":0}]', 85, '192.168.3.102', '2021-04-15 16:12:03');
INSERT INTO `sys_log` VALUES (62, 'lgx', '保存菜单', 'com.clkj.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":110,\"parentId\":106,\"name\":\"删除\",\"perms\":\"sys:department:delete\",\"type\":2,\"orderNum\":0}]', 70, '192.168.3.102', '2021-04-15 16:12:36');
INSERT INTO `sys_log` VALUES (63, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":11,\"username\":\"qqq\",\"password\":\"bb197296d0b5e2256abf1ef6f360586ab6af0395bb79158d9d8473df1c901920\",\"salt\":\"57wuvXlCkoafQxINVTNz\",\"email\":\"123123@qq.com\",\"mobile\":\"12312312312\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Apr 16, 2021 3:38:34 PM\"}]', 843, '192.168.0.102', '2021-04-16 15:38:35');
INSERT INTO `sys_log` VALUES (64, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":15,\"username\":\"eee\",\"password\":\"a5b30b058cb5ff796e3ffa390e490eab50949e51c24df597947db35b5f6584de\",\"salt\":\"M9yYZaUSUHzjdacgxWCh\",\"email\":\"123ss123@qq.com\",\"mobile\":\"12341234123\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Apr 16, 2021 3:43:11 PM\",\"departmentId\":14}]', 52, '192.168.0.102', '2021-04-16 15:43:11');
INSERT INTO `sys_log` VALUES (65, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":16,\"username\":\"eeww\",\"password\":\"eab5679ba5edc7d8ef4711d13749f2c7f3cde7c197c2f224ce06570df17d2b5b\",\"salt\":\"XNKekKM9oqBSy4QKTquT\",\"email\":\"sdsdasd@qq.com\",\"mobile\":\"17227277722\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Apr 16, 2021 3:43:38 PM\",\"departmentId\":9}]', 50, '192.168.0.102', '2021-04-16 15:43:38');
INSERT INTO `sys_log` VALUES (66, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":18,\"username\":\"rerererer\",\"password\":\"7eb85733e8dc0e462983ec5c520986cf71ce9ad51e29ed3af8c1ecf9282219e9\",\"salt\":\"XiIOqPvrv3jLQZgmcR7e\",\"email\":\"sdsdasd@qq.com\",\"mobile\":\"12323233223\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Apr 16, 2021 3:44:21 PM\",\"departmentId\":9}]', 60, '192.168.0.102', '2021-04-16 15:44:21');
INSERT INTO `sys_log` VALUES (67, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":19,\"username\":\"kllklk\",\"password\":\"01ef7b3f4d13fc4d6355595111b4bd249683b3201f962d0ec410116659204a19\",\"salt\":\"brQWaskf3qGnLQnBXuXe\",\"email\":\"jsdhsdh@qq.com\",\"mobile\":\"13231452134\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Apr 16, 2021 3:44:49 PM\",\"departmentId\":13}]', 87, '192.168.0.102', '2021-04-16 15:44:49');
INSERT INTO `sys_log` VALUES (68, 'admin', '保存用户', 'com.clkj.modules.sys.controller.SysUserController.save()', '[{\"userId\":20,\"username\":\"asd\",\"password\":\"cf48a4a849b82a92e4b61a14ef7ea31de53ed213f0f5538278f11f117e8c1d5c\",\"salt\":\"I5JlEVZrt14FDNgzRamo\",\"email\":\"qweqwe@qq.com\",\"mobile\":\"14231231231\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Apr 16, 2021 4:48:55 PM\",\"departmentId\":7}]', 235, '192.168.0.102', '2021-04-16 16:48:55');
INSERT INTO `sys_log` VALUES (69, 'admin', '删除用户', 'com.clkj.modules.sys.controller.SysUserController.delete()', '[[10]]', 102, '192.168.0.102', '2021-04-19 11:28:02');
INSERT INTO `sys_log` VALUES (70, 'admin', '删除用户', 'com.clkj.modules.sys.controller.SysUserController.delete()', '[[19]]', 33, '192.168.0.102', '2021-04-19 11:28:54');
INSERT INTO `sys_log` VALUES (71, 'admin', '修改用户', 'com.clkj.modules.sys.controller.SysUserController.update()', '[{\"userId\":18,\"username\":\"rerererer\",\"password\":\"7eb85733e8dc0e462983ec5c520986cf71ce9ad51e29ed3af8c1ecf9282219e9\",\"salt\":\"XiIOqPvrv3jLQZgmcR7e\",\"email\":\"sdsdasd@qq.com\",\"mobile\":\"12323233223\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"departmentId\":7}]', 890, '192.168.0.102', '2021-04-19 18:29:20');
INSERT INTO `sys_log` VALUES (72, 'admin', '修改用户', 'com.clkj.modules.sys.controller.SysUserController.update()', '[{\"userId\":18,\"username\":\"rerererer\",\"password\":\"7eb85733e8dc0e462983ec5c520986cf71ce9ad51e29ed3af8c1ecf9282219e9\",\"salt\":\"XiIOqPvrv3jLQZgmcR7e\",\"email\":\"sdsdasd@qq.com\",\"mobile\":\"12323233223\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"departmentId\":1}]', 89, '192.168.0.102', '2021-04-19 18:30:38');
INSERT INTO `sys_log` VALUES (73, 'admin', '修改用户', 'com.clkj.modules.sys.controller.SysUserController.update()', '[{\"userId\":8,\"username\":\"yu\",\"password\":\"be65790f5f1d41d33e4089709f409caeae5e09c5e13ad48d2d3bbeef05676ea5\",\"salt\":\"jNotTIvt7C9tFGYhAgAC\",\"email\":\"291337219@qq.com\",\"mobile\":\"13452134544\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"departmentId\":3}]', 320, '127.0.0.1', '2021-04-21 15:46:48');
INSERT INTO `sys_log` VALUES (74, 'admin', '修改协议', 'com.clkj.modules.opt.controller.OptTermsController.update()', '[{\"id\":1,\"type\":1,\"title\":\"测试quill\",\"icon\":\"\",\"content\":\"\\u003cp\\u003e\\u003cspan style\\u003d\\\"color: rgb(77, 77, 77);\\\"\\u003e记录一下最近工作遇到的问题，需求就是在vue-quil-editor富文本自定义撤销和还原功能:undo、redo\\u003c/span\\u003e\\u003c/p\\u003e\",\"updateTime\":\"Jun 2, 2022 6:22:54 PM\",\"updateBy\":1}]', 33, '127.0.0.1', '2022-06-02 18:22:54');
INSERT INTO `sys_log` VALUES (75, 'admin', '修改协议', 'com.clkj.modules.opt.controller.OptTermsController.update()', '[{\"id\":1,\"type\":1,\"title\":\"测试quill\",\"icon\":\"\",\"content\":\"\\u003cp\\u003e\\u003cspan style\\u003d\\\"color: rgb(77, 77, 77);\\\"\\u003e记录一下最近工作遇到的问题，需求就是在vue-quil-editor富文本自定义撤销和还原功能:undo、redo\\u003c/span\\u003e\\u003c/p\\u003e\\u003cp\\u003e\\u003cbr\\u003e\\u003c/p\\u003e\",\"updateTime\":\"Sep 15, 2022 3:52:47 PM\",\"updateBy\":1}]', 36, '127.0.0.1', '2022-09-15 15:52:48');
INSERT INTO `sys_log` VALUES (76, 'admin', '修改协议', 'com.clkj.modules.opt.controller.OptTermsController.update()', '[{\"id\":1,\"type\":1,\"title\":\"测试quill\",\"icon\":\"\",\"content\":\"\\u003cp\\u003e\\u003cspan style\\u003d\\\"color: rgb(77, 77, 77);\\\"\\u003e记录一下最近工作遇到的问题，需求就是在vue-quil-editor富文本自定义撤销和还原功能:undo、redo！！\\u003c/span\\u003e\\u003c/p\\u003e\",\"updateTime\":\"Sep 16, 2022 2:18:20 PM\",\"updateBy\":1}]', 60, '127.0.0.1', '2022-09-16 14:18:21');
INSERT INTO `sys_log` VALUES (77, 'admin', '修改协议', 'com.clkj.modules.opt.controller.OptTermsController.update()', '[{\"id\":1,\"type\":1,\"title\":\"测试quill\",\"icon\":\"\",\"content\":\"\\u003cp\\u003e\\u003cspan style\\u003d\\\"color: rgb(77, 77, 77);\\\"\\u003e记录一下最近工作遇到的问题，需求就是在vue-quil-editor富文本自定义撤销和还原功能:undo、redo！\\u003c/span\\u003e\\u003c/p\\u003e\",\"updateTime\":\"Sep 16, 2022 2:18:31 PM\",\"updateBy\":1}]', 36, '127.0.0.1', '2022-09-16 14:18:32');
INSERT INTO `sys_log` VALUES (78, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 144, '127.0.0.1', '2023-01-03 16:40:21');
INSERT INTO `sys_log` VALUES (79, 'admin', '暂停定时任务', 'com.clkj.modules.job.controller.ScheduleJobController.pause()', '[[1]]', 25, '127.0.0.1', '2023-01-03 16:40:51');
INSERT INTO `sys_log` VALUES (80, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 36, '127.0.0.1', '2023-01-03 16:41:47');
INSERT INTO `sys_log` VALUES (81, 'admin', '修改定时任务', 'com.clkj.modules.job.controller.ScheduleJobController.update()', '[{\"jobId\":1,\"beanName\":\"testTask\",\"params\":\"clkj\",\"cronExpression\":\"0 0/1 * * * ?\",\"status\":1,\"remark\":\"参数测试\"}]', 261, '127.0.0.1', '2023-01-03 16:45:58');
INSERT INTO `sys_log` VALUES (82, 'admin', '恢复定时任务', 'com.clkj.modules.job.controller.ScheduleJobController.resume()', '[[1]]', 41, '127.0.0.1', '2023-01-03 16:46:05');
INSERT INTO `sys_log` VALUES (83, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 10578, '127.0.0.1', '2023-01-03 17:01:08');
INSERT INTO `sys_log` VALUES (84, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 3959, '127.0.0.1', '2023-01-03 17:03:21');
INSERT INTO `sys_log` VALUES (85, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 52, '127.0.0.1', '2023-01-03 17:04:32');
INSERT INTO `sys_log` VALUES (86, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 92, '127.0.0.1', '2023-01-03 17:04:39');
INSERT INTO `sys_log` VALUES (87, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 45, '127.0.0.1', '2023-01-03 17:05:46');
INSERT INTO `sys_log` VALUES (88, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 38, '127.0.0.1', '2023-01-03 17:06:16');
INSERT INTO `sys_log` VALUES (89, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 45, '127.0.0.1', '2023-01-03 17:06:25');
INSERT INTO `sys_log` VALUES (90, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 58, '127.0.0.1', '2023-01-03 17:15:35');
INSERT INTO `sys_log` VALUES (91, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 239, '127.0.0.1', '2023-01-03 17:18:31');
INSERT INTO `sys_log` VALUES (92, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 44, '127.0.0.1', '2023-01-03 17:18:58');
INSERT INTO `sys_log` VALUES (93, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 31, '127.0.0.1', '2023-01-03 17:19:22');
INSERT INTO `sys_log` VALUES (94, 'admin', '立即执行任务', 'com.clkj.modules.job.controller.ScheduleJobController.run()', '[[1]]', 33, '127.0.0.1', '2023-01-03 17:19:31');
INSERT INTO `sys_log` VALUES (95, 'admin', '暂停定时任务', 'com.clkj.modules.job.controller.ScheduleJobController.pause()', '[[1]]', 71, '127.0.0.1', '2023-01-03 17:34:18');
INSERT INTO `sys_log` VALUES (96, 'admin', '恢复定时任务', 'com.clkj.modules.job.controller.ScheduleJobController.resume()', '[[1]]', 61, '127.0.0.1', '2023-01-03 17:49:53');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 800601 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (200000, 0, 'menu.user', NULL, NULL, 0, 'admin', 200000);
INSERT INTO `sys_menu` VALUES (200100, 200000, '用户', 'uc/user/index', NULL, 1, 'config', 100);
INSERT INTO `sys_menu` VALUES (200101, 200100, '查看', NULL, 'uc:user:list,uc:user:info', 2, NULL, 1);
INSERT INTO `sys_menu` VALUES (200102, 200100, '新增', NULL, 'uc:user:save', 2, NULL, 2);
INSERT INTO `sys_menu` VALUES (200103, 200100, '修改', NULL, 'uc:user:update,uc:user:updateStatus', 2, NULL, 3);
INSERT INTO `sys_menu` VALUES (200104, 200100, '删除', NULL, 'uc:user:delete', 2, NULL, 4);
INSERT INTO `sys_menu` VALUES (200105, 200100, '重置密码', NULL, 'uc:user:restPwd', 2, NULL, 5);
INSERT INTO `sys_menu` VALUES (200200, 200000, '实名认证', 'uc/certification/index', NULL, 1, 'config', 200);
INSERT INTO `sys_menu` VALUES (200201, 200200, '查看', NULL, 'uc:certification:list,uc:certification:info', 2, NULL, 1);
INSERT INTO `sys_menu` VALUES (200202, 200200, '新增', NULL, 'uc:certification:save', 2, NULL, 2);
INSERT INTO `sys_menu` VALUES (200203, 200200, '修改', NULL, 'uc:certification:update', 2, NULL, 3);
INSERT INTO `sys_menu` VALUES (200204, 200200, '删除', NULL, 'uc:certification:delete', 2, NULL, 4);
INSERT INTO `sys_menu` VALUES (400000, 0, 'menu.message', NULL, NULL, 0, 'duanxin', 400000);
INSERT INTO `sys_menu` VALUES (400100, 400000, '消息', 'msg/message/index', NULL, 1, 'config', 100);
INSERT INTO `sys_menu` VALUES (400101, 400100, '查看', NULL, 'msg:message:list,msg:message:info', 2, NULL, 1);
INSERT INTO `sys_menu` VALUES (400102, 400100, '新增', NULL, 'msg:message:save', 2, NULL, 2);
INSERT INTO `sys_menu` VALUES (400103, 400100, '修改', NULL, 'msg:message:update', 2, NULL, 3);
INSERT INTO `sys_menu` VALUES (400104, 400100, '删除', NULL, 'msg:message:delete', 2, NULL, 4);
INSERT INTO `sys_menu` VALUES (500000, 0, 'menu.operate', NULL, NULL, 0, 'zonghe', 500000);
INSERT INTO `sys_menu` VALUES (500100, 500000, '关于我们', 'opt/aboutus/add-or-update', NULL, 1, 'config', 100);
INSERT INTO `sys_menu` VALUES (500101, 500100, '查看', NULL, 'opt:aboutus:list,opt:aboutus:info', 2, NULL, 1);
INSERT INTO `sys_menu` VALUES (500102, 500100, '新增', NULL, 'opt:aboutus:save', 2, NULL, 2);
INSERT INTO `sys_menu` VALUES (500103, 500100, '修改', NULL, 'opt:aboutus:update', 2, NULL, 3);
INSERT INTO `sys_menu` VALUES (500104, 500100, '删除', NULL, 'opt:aboutus:delete', 2, NULL, 4);
INSERT INTO `sys_menu` VALUES (500200, 500000, '广告', 'opt/advertisement/index', NULL, 1, 'config', 200);
INSERT INTO `sys_menu` VALUES (500201, 500200, '查看', NULL, 'opt:advertisement:list,opt:advertisement:info', 2, NULL, 1);
INSERT INTO `sys_menu` VALUES (500202, 500200, '新增', NULL, 'opt:advertisement:save', 2, NULL, 2);
INSERT INTO `sys_menu` VALUES (500203, 500200, '修改', NULL, 'opt:advertisement:update', 2, NULL, 3);
INSERT INTO `sys_menu` VALUES (500204, 500200, '删除', NULL, 'opt:advertisement:delete', 2, NULL, 4);
INSERT INTO `sys_menu` VALUES (500300, 500000, '意见反馈', 'opt/feedback/index', NULL, 1, 'config', 300);
INSERT INTO `sys_menu` VALUES (500301, 500300, '查看', NULL, 'opt:feedback:list,opt:feedback:info', 2, NULL, 1);
INSERT INTO `sys_menu` VALUES (500302, 500300, '新增', NULL, 'opt:feedback:save', 2, NULL, 2);
INSERT INTO `sys_menu` VALUES (500303, 500300, '修改', NULL, 'opt:feedback:update', 2, NULL, 3);
INSERT INTO `sys_menu` VALUES (500304, 500300, '删除', NULL, 'opt:feedback:delete', 2, NULL, 4);
INSERT INTO `sys_menu` VALUES (500400, 500000, '协议规则', 'opt/terms/index', NULL, 1, 'config', 400);
INSERT INTO `sys_menu` VALUES (500401, 500400, '查看', NULL, 'opt:terms:list,opt:terms:info', 2, NULL, 1);
INSERT INTO `sys_menu` VALUES (500402, 500400, '新增', NULL, 'opt:terms:save', 2, NULL, 2);
INSERT INTO `sys_menu` VALUES (500403, 500400, '修改', NULL, 'opt:terms:update', 2, NULL, 3);
INSERT INTO `sys_menu` VALUES (500404, 500400, '删除', NULL, 'opt:terms:delete', 2, NULL, 4);
INSERT INTO `sys_menu` VALUES (800000, 0, 'menu.system', NULL, NULL, 0, 'system', 800000);
INSERT INTO `sys_menu` VALUES (800100, 800000, 'submenu.system.sysuser', 'sys/user/index', NULL, 1, 'admin', 100);
INSERT INTO `sys_menu` VALUES (800101, 800100, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 1);
INSERT INTO `sys_menu` VALUES (800102, 800100, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 2);
INSERT INTO `sys_menu` VALUES (800103, 800100, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 3);
INSERT INTO `sys_menu` VALUES (800104, 800100, '删除', NULL, 'sys:user:delete', 2, NULL, 4);
INSERT INTO `sys_menu` VALUES (800200, 800000, 'submenu.system.role', 'sys/role/index', NULL, 1, 'role', 200);
INSERT INTO `sys_menu` VALUES (800201, 800200, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 1);
INSERT INTO `sys_menu` VALUES (800202, 800200, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 2);
INSERT INTO `sys_menu` VALUES (800203, 800200, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 3);
INSERT INTO `sys_menu` VALUES (800204, 800200, '删除', NULL, 'sys:role:delete', 2, NULL, 4);
INSERT INTO `sys_menu` VALUES (800300, 800000, 'submenu.system.menu', 'sys/menu/index', NULL, 1, 'menu', 300);
INSERT INTO `sys_menu` VALUES (800301, 800300, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 1);
INSERT INTO `sys_menu` VALUES (800302, 800300, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 2);
INSERT INTO `sys_menu` VALUES (800303, 800300, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 3);
INSERT INTO `sys_menu` VALUES (800304, 800300, '删除', NULL, 'sys:menu:delete', 2, NULL, 4);
INSERT INTO `sys_menu` VALUES (800400, 800000, '定时任务', 'job/schedule', NULL, 1, 'job', 400);
INSERT INTO `sys_menu` VALUES (800401, 800400, '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 1);
INSERT INTO `sys_menu` VALUES (800402, 800400, '新增', NULL, 'sys:schedule:save', 2, NULL, 2);
INSERT INTO `sys_menu` VALUES (800403, 800400, '修改', NULL, 'sys:schedule:update', 2, NULL, 3);
INSERT INTO `sys_menu` VALUES (800404, 800400, '删除', NULL, 'sys:schedule:delete', 2, NULL, 4);
INSERT INTO `sys_menu` VALUES (800405, 800400, '暂停', NULL, 'sys:schedule:pause', 2, NULL, 5);
INSERT INTO `sys_menu` VALUES (800406, 800400, '恢复', NULL, 'sys:schedule:resume', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (800407, 800400, '立即执行', NULL, 'sys:schedule:run', 2, NULL, 7);
INSERT INTO `sys_menu` VALUES (800408, 800400, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 8);
INSERT INTO `sys_menu` VALUES (800500, 800000, 'submenu.system.config', 'sys/config/index', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 500);
INSERT INTO `sys_menu` VALUES (800600, 800000, 'submenu.system.log', 'sys/log/index', 'sys:log:list', 1, 'log', 600);
INSERT INTO `sys_menu` VALUES (800700, 800000, 'submenu.system.upload', 'oss/oss', 'sys:oss:all', 1, 'oss', 700);

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件上传' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '管理员', 1, '2020-01-16 09:14:58');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 214 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (151, 1, 20);
INSERT INTO `sys_role_menu` VALUES (152, 1, 31);
INSERT INTO `sys_role_menu` VALUES (153, 1, 32);
INSERT INTO `sys_role_menu` VALUES (154, 1, 33);
INSERT INTO `sys_role_menu` VALUES (155, 1, 34);
INSERT INTO `sys_role_menu` VALUES (156, 1, 35);
INSERT INTO `sys_role_menu` VALUES (157, 1, 105);
INSERT INTO `sys_role_menu` VALUES (158, 1, 36);
INSERT INTO `sys_role_menu` VALUES (159, 1, 37);
INSERT INTO `sys_role_menu` VALUES (160, 1, 38);
INSERT INTO `sys_role_menu` VALUES (161, 1, 39);
INSERT INTO `sys_role_menu` VALUES (162, 1, 40);
INSERT INTO `sys_role_menu` VALUES (163, 1, 67);
INSERT INTO `sys_role_menu` VALUES (164, 1, 68);
INSERT INTO `sys_role_menu` VALUES (165, 1, 69);
INSERT INTO `sys_role_menu` VALUES (166, 1, 70);
INSERT INTO `sys_role_menu` VALUES (167, 1, 71);
INSERT INTO `sys_role_menu` VALUES (168, 1, 72);
INSERT INTO `sys_role_menu` VALUES (169, 1, 73);
INSERT INTO `sys_role_menu` VALUES (170, 1, 74);
INSERT INTO `sys_role_menu` VALUES (171, 1, 75);
INSERT INTO `sys_role_menu` VALUES (172, 1, 76);
INSERT INTO `sys_role_menu` VALUES (173, 1, 77);
INSERT INTO `sys_role_menu` VALUES (174, 1, 41);
INSERT INTO `sys_role_menu` VALUES (175, 1, 42);
INSERT INTO `sys_role_menu` VALUES (176, 1, 43);
INSERT INTO `sys_role_menu` VALUES (177, 1, 44);
INSERT INTO `sys_role_menu` VALUES (178, 1, 45);
INSERT INTO `sys_role_menu` VALUES (179, 1, 46);
INSERT INTO `sys_role_menu` VALUES (180, 1, 47);
INSERT INTO `sys_role_menu` VALUES (181, 1, 48);
INSERT INTO `sys_role_menu` VALUES (182, 1, 49);
INSERT INTO `sys_role_menu` VALUES (183, 1, 50);
INSERT INTO `sys_role_menu` VALUES (184, 1, 51);
INSERT INTO `sys_role_menu` VALUES (185, 1, 52);
INSERT INTO `sys_role_menu` VALUES (186, 1, 53);
INSERT INTO `sys_role_menu` VALUES (187, 1, 54);
INSERT INTO `sys_role_menu` VALUES (188, 1, 55);
INSERT INTO `sys_role_menu` VALUES (189, 1, 56);
INSERT INTO `sys_role_menu` VALUES (190, 1, 62);
INSERT INTO `sys_role_menu` VALUES (191, 1, 63);
INSERT INTO `sys_role_menu` VALUES (192, 1, 64);
INSERT INTO `sys_role_menu` VALUES (193, 1, 65);
INSERT INTO `sys_role_menu` VALUES (194, 1, 66);
INSERT INTO `sys_role_menu` VALUES (195, 1, 1);
INSERT INTO `sys_role_menu` VALUES (196, 1, 2);
INSERT INTO `sys_role_menu` VALUES (197, 1, 3);
INSERT INTO `sys_role_menu` VALUES (198, 1, 4);
INSERT INTO `sys_role_menu` VALUES (199, 1, 5);
INSERT INTO `sys_role_menu` VALUES (200, 1, 6);
INSERT INTO `sys_role_menu` VALUES (201, 1, 7);
INSERT INTO `sys_role_menu` VALUES (202, 1, 8);
INSERT INTO `sys_role_menu` VALUES (203, 1, 9);
INSERT INTO `sys_role_menu` VALUES (204, 1, 10);
INSERT INTO `sys_role_menu` VALUES (205, 1, 11);
INSERT INTO `sys_role_menu` VALUES (206, 1, 12);
INSERT INTO `sys_role_menu` VALUES (207, 1, 13);
INSERT INTO `sys_role_menu` VALUES (208, 1, 14);
INSERT INTO `sys_role_menu` VALUES (209, 1, 15);
INSERT INTO `sys_role_menu` VALUES (210, 1, 16);
INSERT INTO `sys_role_menu` VALUES (211, 1, 17);
INSERT INTO `sys_role_menu` VALUES (212, 1, 19);
INSERT INTO `sys_role_menu` VALUES (213, 1, 18);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `lang` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认语言',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `mobile`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '044c978168f3a04df062b89ddcf80c2fd2cc1f1e935d6f3d482cc0e71227279e', 'YzcmCZNvbXocrsz9dm8e', 'root@clkj.com', '13612345678', 'zh-CN', 1, 1, '2016-11-11 11:11:11');
INSERT INTO `sys_user` VALUES (3, 'yangliu', '67c39bc11d4e7f85b5f20954dac1fa19e97e361ecd23de2979d3f85b14d2fa7a', 'YYKZGzSUpb1wv8NVwhni', '123456@qq.com', '15102301123', NULL, 1, 1, '2020-10-16 15:45:33');
INSERT INTO `sys_user` VALUES (5, 'wangyu', '0d89d1723a40b17e9705319e1907105ccc5acddd8de7e1f258fe910b497ae124', 'E3v4a4bLluOO1XwpZjbh', '6666@163.com', '18883784845', NULL, 1, 1, '2020-10-16 16:06:24');
INSERT INTO `sys_user` VALUES (8, 'yu', 'be65790f5f1d41d33e4089709f409caeae5e09c5e13ad48d2d3bbeef05676ea5', 'jNotTIvt7C9tFGYhAgAC', '291337219@qq.com', '13452134544', NULL, 1, 1, '2020-11-11 10:46:23');
INSERT INTO `sys_user` VALUES (9, 'yong', 'df2a9cb2a9b0ecd4c285481d61fa9a32acecb3fb2a816d8a800c8c4f32724f49', 'xCVl4ZDN4ZKwPxndwUqN', '15766666666@163.com', '15766666666', NULL, 1, 1, '2020-11-25 14:25:45');
INSERT INTO `sys_user` VALUES (11, 'qqq', 'bb197296d0b5e2256abf1ef6f360586ab6af0395bb79158d9d8473df1c901920', '57wuvXlCkoafQxINVTNz', '123123@qq.com', '12312312312', NULL, 1, 1, '2021-04-16 15:38:34');
INSERT INTO `sys_user` VALUES (15, 'eee', 'a5b30b058cb5ff796e3ffa390e490eab50949e51c24df597947db35b5f6584de', 'M9yYZaUSUHzjdacgxWCh', '123ss123@qq.com', '12341234123', NULL, 1, 1, '2021-04-16 15:43:11');
INSERT INTO `sys_user` VALUES (16, 'eeww', 'eab5679ba5edc7d8ef4711d13749f2c7f3cde7c197c2f224ce06570df17d2b5b', 'XNKekKM9oqBSy4QKTquT', 'sdsdasd@qq.com', '17227277722', NULL, 1, 1, '2021-04-16 15:43:38');
INSERT INTO `sys_user` VALUES (18, 'rerererer', '7eb85733e8dc0e462983ec5c520986cf71ce9ad51e29ed3af8c1ecf9282219e9', 'XiIOqPvrv3jLQZgmcR7e', 'sdsdasd@qq.com', '12323233223', NULL, 1, 1, '2021-04-16 15:44:21');
INSERT INTO `sys_user` VALUES (20, 'asd', 'cf48a4a849b82a92e4b61a14ef7ea31de53ed213f0f5538278f11f117e8c1d5c', 'I5JlEVZrt14FDNgzRamo', 'qweqwe@qq.com', '14231231231', NULL, 1, 1, '2021-04-16 16:48:55');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (2, 2, 1);
INSERT INTO `sys_user_role` VALUES (3, 3, 1);
INSERT INTO `sys_user_role` VALUES (5, 4, 1);
INSERT INTO `sys_user_role` VALUES (6, 5, 1);
INSERT INTO `sys_user_role` VALUES (7, 6, 1);
INSERT INTO `sys_user_role` VALUES (10, 9, 1);
INSERT INTO `sys_user_role` VALUES (11, 10, 1);
INSERT INTO `sys_user_role` VALUES (12, 8, 1);

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, 'd80c7d5ea82f130fc71dec33a13ecc41', '2023-01-04 04:29:38', '2023-01-03 16:29:38');
INSERT INTO `sys_user_token` VALUES (3, '3f526bb3e8d830848edefb45c89cba09', '2020-10-19 23:18:18', '2020-10-19 11:18:18');
INSERT INTO `sys_user_token` VALUES (4, '7d39d742f2d2c4a08fca2a2879972b27', '2020-10-17 04:03:24', '2020-10-16 16:03:24');
INSERT INTO `sys_user_token` VALUES (5, '8c4f0bed3ca119ce6c0ad9d9354a7a25', '2020-10-20 21:51:23', '2020-10-20 09:51:23');
INSERT INTO `sys_user_token` VALUES (6, '95224d3a4792789fbbe757f02fd3dac8', '2020-10-17 04:34:31', '2020-10-16 16:34:31');
INSERT INTO `sys_user_token` VALUES (8, '3de78e6571395e3bbeb715bf109afc0c', '2020-11-25 23:37:52', '2020-11-25 11:37:52');
INSERT INTO `sys_user_token` VALUES (10, '4d455196be2da9b874142bb8abae43fb', '2021-04-16 03:26:34', '2021-04-15 15:26:34');

-- ----------------------------
-- Table structure for uc_certification
-- ----------------------------
DROP TABLE IF EXISTS `uc_certification`;
CREATE TABLE `uc_certification`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '实名认证ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '实名认证会员',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `id_type` int(2) NULL DEFAULT NULL COMMENT '证件类型 1:身份证,2:待定',
  `id_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件号',
  `id_cart_face` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件正面',
  `id_cart_back` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件反面',
  `failure_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '失败原因',
  `status` int(2) NULL DEFAULT NULL COMMENT '实名认证状态（0未审核   1审核通过   2审核失败）',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `business_license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '营业执照',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE,
  UNIQUE INDEX `id_type`(`id_type`, `id_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '实名认证' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for uc_user
-- ----------------------------
DROP TABLE IF EXISTS `uc_user`;
CREATE TABLE `uc_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `certification_status` int(2) NOT NULL DEFAULT -1 COMMENT '实名认证状态,-1:未提交,0:审核中,1:通过,2:失败',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '0:正常,1:禁用',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `mobile`(`mobile`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of uc_user
-- ----------------------------
INSERT INTO `uc_user` VALUES (1, 'mark', '28a80ebd6b708df733ef9d70a3660697df2ef3b106d22c84dd319d813a2226ef', '9d813a2226ef', NULL, '13612345678', NULL, NULL, NULL, -1, '2017-03-23 22:37:41', 0, NULL, NULL);

-- ----------------------------
-- Table structure for uc_user_statistics
-- ----------------------------
DROP TABLE IF EXISTS `uc_user_statistics`;
CREATE TABLE `uc_user_statistics`  (
  `user_id` bigint(20) NOT NULL,
  `help_times` int(11) NOT NULL DEFAULT 0 COMMENT '求救次数',
  `total_help_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '总求救金额',
  `rescue_times` int(11) NOT NULL DEFAULT 0 COMMENT '施救次数',
  `total_rescue_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '总施救金额',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户求救施救统计' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for uc_user_token
-- ----------------------------
DROP TABLE IF EXISTS `uc_user_token`;
CREATE TABLE `uc_user_token`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户token',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Function structure for func_calcDistance
-- ----------------------------
DROP FUNCTION IF EXISTS `func_calcDistance`;
delimiter ;;
CREATE FUNCTION `func_calcDistance`(origLng DECIMAL(20,8), -- 目的地经度
    origLat DECIMAL(20,8), -- 目的地纬度
    lng DECIMAL(20,8), -- 当前所在地点经度
    lat DECIMAL(20,8))
 RETURNS double
BEGIN
      DECLARE result DOUBLE DEFAULT 0;

      SET result = round(6378.138*2*asin(sqrt(pow(sin(
        (origLat*pi()/180-lng*pi()/180)/2),2)+cos(origLat*pi()/180)*cos(lat*pi()/180)*
        pow(sin( (origLng*pi()/180-lng*pi()/180)/2),2)))*1000);

      RETURN result;

 END
;;
delimiter ;

-- ----------------------------
-- Function structure for getCategoryChildrenIds
-- ----------------------------
DROP FUNCTION IF EXISTS `getCategoryChildrenIds`;
delimiter ;;
CREATE FUNCTION `getCategoryChildrenIds`(pid INT)
 RETURNS varchar(4000) CHARSET utf8
BEGIN
	DECLARE
		oTemp VARCHAR ( 4000 ) DEFAULT NULL;
	DECLARE
		oTempChild VARCHAR ( 4000 );

	SET oTempChild = CAST( pid AS CHAR );
	WHILE
			oTempChild IS NOT NULL DO

			SET oTemp = CONCAT_WS( ',', oTemp, oTempChild );
		SELECT
			GROUP_CONCAT( id ) INTO oTempChild
		FROM
			comm_category
		WHERE
			FIND_IN_SET( parent_id, oTempChild );
	END WHILE;
	RETURN oTemp;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for getCategroyParentIds
-- ----------------------------
DROP FUNCTION IF EXISTS `getCategroyParentIds`;
delimiter ;;
CREATE FUNCTION `getCategroyParentIds`(root_id BIGINT)
 RETURNS varchar(500) CHARSET utf8
BEGIN
	DECLARE pid INT;
	DECLARE str VARCHAR(500);
	SET pid = root_id;
	SET str = CAST(root_id as CHAR);
  WHILE pid > 0 DO
		SET pid = (SELECT parent_id FROM comm_category WHERE pid=id);
		IF pid>0 THEN
			SET str = CONCAT_WS(',', pid, str);
		END IF;
	END WHILE;
RETURN str;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for getCategroyParentNames
-- ----------------------------
DROP FUNCTION IF EXISTS `getCategroyParentNames`;
delimiter ;;
CREATE FUNCTION `getCategroyParentNames`(root_id bigint, split varchar(10))
 RETURNS varchar(500) CHARSET utf8
BEGIN
	DECLARE pid INT;
	DECLARE str VARCHAR(500) DEFAULT NULL;
	SET pid = root_id;
  WHILE pid > 0 DO
    SELECT parent_id,CONCAT_WS(split,name,str) into pid,str FROM comm_category WHERE pid=id;
  END WHILE;
RETURN str;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
