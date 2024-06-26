/*
 Navicat Premium Data Transfer

 Source Server         : centos
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : centos:3306
 Source Schema         : medical_hospital_manage

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 29/03/2023 02:17:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hospital_setting
-- ----------------------------
DROP TABLE IF EXISTS `hospital_setting`;
CREATE TABLE `hospital_setting`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `hospital_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院编号',
  `sign_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名秘钥',
  `api_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统一挂号平台api地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '医院设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hospital_setting
-- ----------------------------
INSERT INTO `hospital_setting` VALUES (1, '1000_0', 'ba79d09c7203b003041e5bb63e7601a2', 'http://yizhi-medical-server:8200', '2023-03-28 16:31:49', '2023-03-28 16:47:05', 0);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `schedule_id` bigint NULL DEFAULT NULL COMMENT '排班id',
  `patient_id` bigint NULL DEFAULT NULL COMMENT '就诊人id',
  `number` int NULL DEFAULT NULL COMMENT '预约号序',
  `fetch_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建议取号时间',
  `fetch_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '取号地点',
  `amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '医事服务费',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `quit_time` datetime NULL DEFAULT NULL COMMENT '退号时间',
  `order_status` tinyint NULL DEFAULT NULL COMMENT '订单状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (23, 1, 1, 24, '1 14:00前', '一楼9号窗口', 100, NULL, '2022-09-10 10:18:44', -1, '2022-09-10 02:10:42', '2022-09-10 02:10:42', 0);
INSERT INTO `order_info` VALUES (24, 1, 1, 25, '1 14:00前', '一楼9号窗口', 100, '2022-09-10 10:22:44', '2022-09-10 10:22:54', -1, '2022-09-10 02:22:17', '2022-09-10 02:22:17', 0);
INSERT INTO `order_info` VALUES (25, 1, 1, 26, '1 14:00前', '一楼9号窗口', 100, '2022-09-10 10:28:00', '2022-09-10 10:28:13', -1, '2022-09-10 02:27:30', '2022-09-10 02:27:30', 0);
INSERT INTO `order_info` VALUES (26, 1, 1, 27, '1 14:00前', '一楼9号窗口', 100, NULL, '2022-09-10 22:35:01', -1, '2022-09-10 02:28:57', '2022-09-10 02:28:57', 0);
INSERT INTO `order_info` VALUES (27, 1, 1, 28, '1 14:00前', '一楼9号窗口', 100, '2022-09-10 18:00:02', NULL, 1, '2022-09-10 09:59:43', '2022-09-10 09:59:43', 0);
INSERT INTO `order_info` VALUES (28, 1, 1, 29, '1 14:00前', '一楼9号窗口', 100, '2022-09-10 20:48:55', NULL, 1, '2022-09-10 12:48:40', '2022-09-10 12:48:40', 0);
INSERT INTO `order_info` VALUES (29, 1, 1, 30, '1 14:00前', '一楼9号窗口', 100, '2022-09-11 13:38:53', NULL, 1, '2022-09-11 05:38:35', '2022-09-11 05:38:35', 0);

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
  `id` bigint NOT NULL DEFAULT 0 COMMENT '编号',
  `hospital_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院编号',
  `depart_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室编号',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `doctor_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医生名称',
  `skill` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '擅长技能',
  `work_date` date NULL DEFAULT NULL COMMENT '安排日期',
  `work_time` tinyint NULL DEFAULT 0 COMMENT '安排时间（0：上午 1：下午）',
  `reserved_number` int NULL DEFAULT 0 COMMENT '可预约数',
  `available_number` int NULL DEFAULT 0 COMMENT '剩余预约数',
  `amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '挂号费',
  `status` tinyint NULL DEFAULT NULL COMMENT '排班状态（-1：停诊 0：停约 1：可约）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '医生日程安排表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES (1, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2020-12-13', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:01:49', 0);
INSERT INTO `schedule` VALUES (2, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2020-12-22', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:01:51', 0);
INSERT INTO `schedule` VALUES (3, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2020-12-22', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:01:57', 0);
INSERT INTO `schedule` VALUES (4, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2020-12-14', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:01:57', 0);
INSERT INTO `schedule` VALUES (5, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2020-12-23', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:01:57', 0);
INSERT INTO `schedule` VALUES (6, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2020-12-23', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:01:57', 0);
INSERT INTO `schedule` VALUES (7, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2020-12-15', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:01:57', 0);
INSERT INTO `schedule` VALUES (8, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2020-12-24', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:01:57', 0);
INSERT INTO `schedule` VALUES (9, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2020-12-24', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:01:57', 0);
INSERT INTO `schedule` VALUES (10, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2020-12-16', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (11, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2020-12-25', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (12, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2020-12-25', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (13, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2020-12-17', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (14, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2020-12-26', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (15, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2020-12-26', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (16, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2020-12-18', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (17, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2020-12-27', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (18, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2020-12-27', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (19, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2020-12-19', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (20, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2020-12-28', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (21, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2020-12-28', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (22, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2020-12-20', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (23, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2020-12-29', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (24, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2020-12-29', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (25, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2020-12-21', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (26, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2020-12-30', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (27, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2020-12-30', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (29, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2020-12-30', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (30, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2020-12-30', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (31, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-01', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (32, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-01', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (33, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-01', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (34, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2021-01-02', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (35, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-02', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (36, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-02', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (37, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2021-01-03', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (38, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-03', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (39, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-03', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (40, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2021-01-04', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (41, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-04', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (42, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-04', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (43, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2021-01-05', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (44, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-05', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (45, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-05', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (46, '1000_0', '200040878', '医师', '邵迎红', '内分泌科常见病。', '2021-01-06', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-05 12:03:26', 0);
INSERT INTO `schedule` VALUES (47, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-06', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (48, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-06', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (49, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-07', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (50, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-07', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (51, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-07', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (52, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-08', 0, 33, 22, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (53, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-08', 0, 40, 6, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (54, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-08', 1, 27, 10, 100, 1, '2022-09-04 15:16:12', '2022-09-04 15:16:12', 0);
INSERT INTO `schedule` VALUES (55, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-09', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (56, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-09', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (57, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-09', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (58, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-10', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (59, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-10', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (60, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-10', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (61, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-11', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (62, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-11', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (63, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-11', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (64, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-12', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (65, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-12', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (66, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-12', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (67, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-13', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (68, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-13', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (69, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-13', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (70, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-14', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (71, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-14', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (72, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-14', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (73, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-15', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (74, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-15', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (75, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-15', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (76, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-16', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (77, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-16', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (78, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-16', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (79, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-17', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (80, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-17', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (81, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-17', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (82, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-18', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (83, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-18', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (84, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-18', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (85, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-19', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (86, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-19', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (87, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-19', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (88, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-20', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (89, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-20', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (90, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-20', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (91, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-21', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (92, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-21', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (93, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-21', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (94, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-22', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (95, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-22', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (96, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-22', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (97, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-23', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (98, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-23', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (99, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-23', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (100, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-24', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (101, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-24', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (102, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-24', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (103, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-25', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (104, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-25', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (105, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-25', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (106, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-26', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (107, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-26', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (108, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-26', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (109, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-27', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (110, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-27', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (111, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-27', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (112, '1000_0', '200040878', '医师', '', '内分泌科常见病。', '2021-01-28', 0, 33, 22, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (113, '1000_0', '200040878', '副主任医师', '裴育', '骨质疏松和骨代谢疾病、糖尿病、甲状腺疾病。', '2021-01-28', 0, 40, 6, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);
INSERT INTO `schedule` VALUES (114, '1000_0', '200040878', '副主任医师', '邵迎红', '内分泌与代谢性疾病。', '2021-01-28', 1, 27, 10, 100, 1, '2022-09-04 15:16:13', '2022-09-04 15:16:13', 0);

SET FOREIGN_KEY_CHECKS = 1;
