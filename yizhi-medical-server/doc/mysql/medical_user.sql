/*
 Navicat Premium Data Transfer

 Source Server         : centos
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : centos:3306
 Source Schema         : medical_user

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 29/03/2023 02:17:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `certificates_type` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件类型',
  `certificates_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件编号',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别',
  `birthdate` date NULL DEFAULT NULL COMMENT '出生年月',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `is_marry` tinyint NULL DEFAULT NULL COMMENT '是否结婚',
  `province_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省code',
  `city_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市code',
  `district_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区code',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情地址',
  `contacts_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `contacts_certificates_type` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人证件类型',
  `contacts_certificates_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人证件号',
  `contacts_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人手机',
  `card_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '就诊卡号',
  `is_insure` tinyint NULL DEFAULT 0 COMMENT '是否有医保',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0：默认 1：已认证）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '就诊人表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES (1, 14, '刘川', '10', '510181200010146733', 1, '2022-08-01', '15828145845', 0, '110000', '110100', '110105', '北京市市辖区朝阳区111号', '刘川', '10', '510181200010146733', '15828145845', '63453453453453453', 0, 0, '2022-08-24 23:32:28', '2022-08-25 12:23:00', 1);
INSERT INTO `patient` VALUES (8, 14, '刘川', '10', '510181200010146733', 1, '2022-08-01', '15828145845', 0, '130000', '130100', '130101', '66街道22社区', '', '', '', '', NULL, 0, 0, '2022-08-25 12:22:30', '2022-08-25 13:58:35', 0);
INSERT INTO `patient` VALUES (9, 14, '测试2', '10', '510181200010113333', 1, '2022-08-08', '15828145555', 0, '110000', '110100', '110105', '66街道22社区', '测试', '10', '510181200010113333', '15821845845', NULL, 0, 0, '2022-08-25 12:36:24', '2022-08-25 23:40:34', 1);
INSERT INTO `patient` VALUES (10, 14, '测试2', '20', '510181200012222222', 1, '2022-07-31', '15872184222', 0, '110000', '110100', '110105', '红牌楼666号 ', '', '', '', '', NULL, 0, 0, '2022-08-25 12:40:36', '2022-08-25 23:40:36', 1);
INSERT INTO `patient` VALUES (11, 14, '测试3', '10', '510181200011323333', 0, '2022-08-08', '15828145845', 0, '110000', '110100', '110105', '东街2号院', '', '', '', '', NULL, 0, 0, '2022-08-25 12:42:24', '2022-08-25 23:40:39', 1);
INSERT INTO `patient` VALUES (12, 14, '111', '10', '1231112312321', 1, '2022-07-31', '123131', 0, '110000', '110100', '110118', '666号', '', '', '', '', NULL, 0, 0, '2022-08-25 17:21:20', '2022-08-25 23:40:31', 1);
INSERT INTO `patient` VALUES (13, 16, '张三', '10', '5101812131213123123', 1, '2022-07-31', '12312312312', 0, '130000', '130300', '130303', '11111号街区', '', '', '', '', NULL, 0, 0, '2022-08-25 20:22:31', '2022-08-25 20:22:31', 0);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `openid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `certificates_type` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件类型',
  `certificates_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件编号',
  `certificates_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件路径',
  `auth_status` tinyint NOT NULL DEFAULT 0 COMMENT '认证状态（0：未认证 1：认证中 2：认证成功 -1：认证失败）',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态（0：锁定 1：正常）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uk_mobile`(`phone` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (8, NULL, NULL, '15828145555', '', NULL, NULL, NULL, 0, 0, '2022-07-19 07:08:32', '2022-07-19 07:08:32', 0);
INSERT INTO `user_info` VALUES (9, NULL, NULL, '15828145846', '', NULL, NULL, NULL, 0, 0, '2022-07-19 14:57:15', '2022-07-21 18:44:50', 0);
INSERT INTO `user_info` VALUES (10, NULL, NULL, '18354353454', '', NULL, NULL, NULL, 0, 1, '2022-07-19 15:30:12', '2022-07-21 18:44:55', 0);
INSERT INTO `user_info` VALUES (14, 'o3_SC5216sBPWfeSBem-VdUKznwE', 'noob', '15828145845', '刘川', '身份证', '510181200010146733', NULL, 2, 1, '2022-07-21 19:36:50', '2022-08-25 15:21:58', 0);
INSERT INTO `user_info` VALUES (15, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0, '2022-08-25 03:49:32', '2022-08-25 03:49:32', 0);
INSERT INTO `user_info` VALUES (16, NULL, NULL, '18881622272', '张三', '身份证', '510181200010146733', 'https://yygh-liuchuan.oss-cn-chengdu.aliyuncs.com/2022/08/30/125ca752cfef439d9e16e64a72e9b4a82.png', 2, 1, '2022-08-25 18:14:32', '2022-08-25 18:14:32', 0);

-- ----------------------------
-- Table structure for user_login_record
-- ----------------------------
DROP TABLE IF EXISTS `user_login_record`;
CREATE TABLE `user_login_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户登录记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_login_record
-- ----------------------------
INSERT INTO `user_login_record` VALUES (40, 11, '0:0:0:0:0:0:0:1', '2022-07-21 18:44:03', '2022-07-21 18:44:03', 0);
INSERT INTO `user_login_record` VALUES (41, 12, '0:0:0:0:0:0:0:1', '2022-07-21 18:52:06', '2022-07-21 18:52:06', 0);
INSERT INTO `user_login_record` VALUES (42, 13, '0:0:0:0:0:0:0:1', '2022-07-21 19:05:23', '2022-07-21 19:05:23', 0);
INSERT INTO `user_login_record` VALUES (43, 13, '0:0:0:0:0:0:0:1', '2022-07-21 19:21:55', '2022-07-21 19:21:55', 0);
INSERT INTO `user_login_record` VALUES (44, 13, '0:0:0:0:0:0:0:1', '2022-07-21 19:26:22', '2022-07-21 19:26:22', 0);
INSERT INTO `user_login_record` VALUES (45, 13, '0:0:0:0:0:0:0:1', '2022-07-21 19:29:33', '2022-07-21 19:29:33', 0);
INSERT INTO `user_login_record` VALUES (46, 13, '0:0:0:0:0:0:0:1', '2022-07-21 19:33:26', '2022-07-21 19:33:26', 0);
INSERT INTO `user_login_record` VALUES (47, 14, '0:0:0:0:0:0:0:1', '2022-07-21 19:37:19', '2022-07-21 19:37:19', 0);
INSERT INTO `user_login_record` VALUES (48, 14, '0:0:0:0:0:0:0:1', '2022-08-17 17:32:53', '2022-08-17 17:32:53', 0);
INSERT INTO `user_login_record` VALUES (49, 14, '0:0:0:0:0:0:0:1', '2022-08-17 17:32:53', '2022-08-17 17:32:53', 0);
INSERT INTO `user_login_record` VALUES (50, 14, '0:0:0:0:0:0:0:1', '2022-08-24 16:23:05', '2022-08-24 16:23:05', 0);
INSERT INTO `user_login_record` VALUES (51, 14, '0:0:0:0:0:0:0:1', '2022-08-25 03:08:51', '2022-08-25 03:08:51', 0);
INSERT INTO `user_login_record` VALUES (52, 14, '0:0:0:0:0:0:0:1', '2022-08-25 03:08:54', '2022-08-25 03:08:54', 0);
INSERT INTO `user_login_record` VALUES (53, NULL, '0:0:0:0:0:0:0:1', '2022-08-25 03:11:22', '2022-08-25 03:11:22', 0);
INSERT INTO `user_login_record` VALUES (54, 14, '0:0:0:0:0:0:0:1', '2022-08-25 03:11:56', '2022-08-25 03:11:56', 0);
INSERT INTO `user_login_record` VALUES (55, 14, '0:0:0:0:0:0:0:1', '2022-08-25 03:50:22', '2022-08-25 03:50:22', 0);
INSERT INTO `user_login_record` VALUES (56, 14, '0:0:0:0:0:0:0:1', '2022-08-25 11:10:30', '2022-08-25 11:10:30', 0);
INSERT INTO `user_login_record` VALUES (57, 14, '0:0:0:0:0:0:0:1', '2022-08-25 11:12:56', '2022-08-25 11:12:56', 0);
INSERT INTO `user_login_record` VALUES (58, 14, '0:0:0:0:0:0:0:1', '2022-08-25 16:13:59', '2022-08-25 16:13:59', 0);
INSERT INTO `user_login_record` VALUES (59, 14, '0:0:0:0:0:0:0:1', '2022-08-25 17:20:12', '2022-08-25 17:20:12', 0);
INSERT INTO `user_login_record` VALUES (60, 16, '0:0:0:0:0:0:0:1', '2022-08-25 18:14:32', '2022-08-25 18:14:32', 0);
INSERT INTO `user_login_record` VALUES (61, 14, '0:0:0:0:0:0:0:1', '2022-08-25 23:33:58', '2022-08-25 23:33:58', 0);
INSERT INTO `user_login_record` VALUES (62, 14, '0:0:0:0:0:0:0:1', '2022-09-02 15:03:52', '2022-09-02 15:03:52', 0);
INSERT INTO `user_login_record` VALUES (63, 14, '0:0:0:0:0:0:0:1', '2022-09-04 13:52:30', '2022-09-04 13:52:30', 0);
INSERT INTO `user_login_record` VALUES (64, 14, '0:0:0:0:0:0:0:1', '2022-09-05 14:15:00', '2022-09-05 14:15:00', 0);
INSERT INTO `user_login_record` VALUES (65, 16, '0:0:0:0:0:0:0:1', '2022-09-05 14:24:38', '2022-09-05 14:24:38', 0);
INSERT INTO `user_login_record` VALUES (66, 16, '0:0:0:0:0:0:0:1', '2022-09-05 15:16:27', '2022-09-05 15:16:27', 0);
INSERT INTO `user_login_record` VALUES (67, 14, '0:0:0:0:0:0:0:1', '2022-09-06 07:38:59', '2022-09-06 07:38:59', 0);
INSERT INTO `user_login_record` VALUES (68, 14, '0:0:0:0:0:0:0:1', '2022-09-06 14:22:44', '2022-09-06 14:22:44', 0);
INSERT INTO `user_login_record` VALUES (69, 14, '0:0:0:0:0:0:0:1', '2022-09-06 16:04:27', '2022-09-06 16:04:27', 0);
INSERT INTO `user_login_record` VALUES (70, 14, '0:0:0:0:0:0:0:1', '2022-09-08 12:42:34', '2022-09-08 12:42:34', 0);
INSERT INTO `user_login_record` VALUES (71, 14, '0:0:0:0:0:0:0:1', '2022-09-09 00:50:04', '2022-09-09 00:50:04', 0);
INSERT INTO `user_login_record` VALUES (72, 14, '0:0:0:0:0:0:0:1', '2022-09-09 22:09:13', '2022-09-09 22:09:13', 0);
INSERT INTO `user_login_record` VALUES (73, 14, '0:0:0:0:0:0:0:1', '2022-09-10 00:29:25', '2022-09-10 00:29:25', 0);
INSERT INTO `user_login_record` VALUES (74, 14, '0:0:0:0:0:0:0:1', '2022-09-10 12:48:18', '2022-09-10 12:48:18', 0);
INSERT INTO `user_login_record` VALUES (75, 14, '0:0:0:0:0:0:0:1', '2022-09-11 05:38:26', '2022-09-11 05:38:26', 0);

SET FOREIGN_KEY_CHECKS = 1;
