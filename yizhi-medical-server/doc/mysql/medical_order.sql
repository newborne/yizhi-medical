/*
 Navicat Premium Data Transfer

 Source Server         : centos
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : centos:3306
 Source Schema         : medical_order

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 29/03/2023 02:17:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NULL DEFAULT NULL,
  `out_trade_no` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单交易号',
  `hoscode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院编号',
  `hosname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院名称',
  `depcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室编号',
  `depname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室名称',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医生职称',
  `hos_schedule_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排班编号（医院自己的排班主键）',
  `reserve_date` date NULL DEFAULT NULL COMMENT '安排日期',
  `reserve_time` tinyint NULL DEFAULT 0 COMMENT '安排时间（0：上午 1：下午）',
  `patient_id` bigint NULL DEFAULT NULL COMMENT '就诊人id',
  `patient_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '就诊人名称',
  `patient_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '就诊人手机',
  `hos_record_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约记录唯一标识（医院预约记录主键）',
  `number` int NULL DEFAULT NULL COMMENT '预约号序',
  `fetch_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建议取号时间',
  `fetch_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '取号地点',
  `amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '医事服务费',
  `quit_time` datetime NULL DEFAULT NULL COMMENT '退号时间',
  `order_status` tinyint NULL DEFAULT NULL COMMENT '订单状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_out_trade_no`(`out_trade_no` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_hoscode`(`hoscode` ASC) USING BTREE,
  INDEX `idx_hos_schedule_id`(`hos_schedule_id` ASC) USING BTREE,
  INDEX `idx_hos_record_id`(`hos_record_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (72, 14, '166277584275583', '1000_0', '北京协和医院', '200040878', '多发性硬化专科门诊', '副主任医师', '631602533f5a1734cd72f98c', '2022-09-10', 1, 8, '刘川', '15828145845', '23', 24, '2022-09-1009:00前', '一层114窗口', 100, '2022-09-11 00:00:00', -1, '2022-09-10 02:10:42', '2022-09-10 02:10:42', 0);
INSERT INTO `order_info` VALUES (73, 14, '166277653713657', '1000_0', '北京协和医院', '200040878', '多发性硬化专科门诊', '副主任医师', '631602533f5a1734cd72f98c', '2022-09-10', 1, 8, '刘川', '15828145845', '24', 25, '2022-09-1009:00前', '一层114窗口', 100, '2022-09-11 00:00:00', -1, '2022-09-10 02:22:17', '2022-09-10 02:22:17', 0);
INSERT INTO `order_info` VALUES (74, 14, '166277685023688', '1000_0', '北京协和医院', '200040878', '多发性硬化专科门诊', '副主任医师', '631602533f5a1734cd72f98c', '2022-09-10', 1, 8, '刘川', '15828145845', '25', 26, '2022-09-1009:00前', '一层114窗口', 100, '2022-09-11 00:00:00', -1, '2022-09-10 02:27:30', '2022-09-10 02:27:30', 0);
INSERT INTO `order_info` VALUES (75, 14, '166277693732462', '1000_0', '北京协和医院', '200040878', '多发性硬化专科门诊', '副主任医师', '631602533f5a1734cd72f98c', '2022-09-10', 1, 8, '刘川', '15828145845', '26', 27, '2022-09-1009:00前', '一层114窗口', 100, '2022-09-11 00:00:00', -1, '2022-09-10 02:28:57', '2022-09-10 02:28:57', 0);
INSERT INTO `order_info` VALUES (76, 14, '166280398302451', '1000_0', '北京协和医院', '200040878', '多发性硬化专科门诊', '副主任医师', '631602533f5a1734cd72f98c', '2022-09-13', 1, 8, '刘川', '15828145845', '27', 28, '2022-09-1309:00前', '一层114窗口', 100, '2022-09-14 00:00:00', 1, '2022-09-10 09:59:42', '2022-09-10 09:59:43', 0);
INSERT INTO `order_info` VALUES (77, 14, '166281411954566', '1000_0', '北京协和医院', '200040878', '多发性硬化专科门诊', '副主任医师', '631602533f5a1734cd72f98c', '2022-09-13', 1, 8, '刘川', '15828145845', '28', 29, '2022-09-1309:00前', '一层114窗口', 100, '2022-09-14 00:00:00', 1, '2022-09-10 12:48:40', '2022-09-10 12:48:40', 0);
INSERT INTO `order_info` VALUES (78, 14, '166287471475732', '1000_0', '北京协和医院', '200040878', '多发性硬化专科门诊', '副主任医师', '631602533f5a1734cd72f98c', '2022-09-13', 1, 8, '刘川', '15828145845', '29', 30, '2022-09-1309:00前', '一层114窗口', 100, '2022-09-14 00:00:00', 1, '2022-09-11 05:38:35', '2022-09-11 05:38:35', 0);

-- ----------------------------
-- Table structure for payment_info
-- ----------------------------
DROP TABLE IF EXISTS `payment_info`;
CREATE TABLE `payment_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `out_trade_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对外业务编号',
  `order_id` bigint NULL DEFAULT NULL COMMENT '订单id',
  `payment_type` tinyint(1) NULL DEFAULT NULL COMMENT '支付类型（微信 支付宝）',
  `trade_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易编号',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `subject` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易内容',
  `payment_status` tinyint NULL DEFAULT NULL COMMENT '支付状态',
  `callback_time` datetime NULL DEFAULT NULL COMMENT '回调时间',
  `callback_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_out_trade_no`(`out_trade_no` ASC) USING BTREE,
  INDEX `idx_order_id`(`order_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_info
-- ----------------------------
INSERT INTO `payment_info` VALUES (10, '166276977780679', 68, 2, '4200001564202209108917707422', 100.00, '2022-09-10|北京协和医院|多发性硬化专科门诊|副主任医师', 2, '2022-09-10 08:30:04', '{transaction_id=4200001564202209108917707422, nonce_str=Da9fTpOEhQ1sk2l8, trade_state=SUCCESS, bank_type=OTHERS, openid=oHwsHuDT9-LpQl-GDERTTzDB4vIM, sign=36C59FC9879AE9EA6CAFDC7D5259FF46, return_msg=OK, fee_type=CNY, mch_id=1558950191, cash_fee=1, out_trade_no=166276977780679, cash_fee_type=CNY, appid=wx74862e0dfcf69954, total_fee=1, trade_state_desc=支付成功, trade_type=NATIVE, result_code=SUCCESS, attach=, time_end=20220910083002, is_subscribe=N, return_code=SUCCESS}', '2022-09-10 08:29:42', '2022-09-10 00:30:03', 0);
INSERT INTO `payment_info` VALUES (11, '166277050447048', 69, 2, '4200001542202209100998482019', 100.00, '2022-09-10|北京协和医院|多发性硬化专科门诊|副主任医师', 2, '2022-09-10 08:42:19', '{transaction_id=4200001542202209100998482019, nonce_str=6VmmHYJH2Mgz9hmR, trade_state=SUCCESS, bank_type=OTHERS, openid=oHwsHuDT9-LpQl-GDERTTzDB4vIM, sign=94CE5BDE560701BCE94D7663632EA370, return_msg=OK, fee_type=CNY, mch_id=1558950191, cash_fee=1, out_trade_no=166277050447048, cash_fee_type=CNY, appid=wx74862e0dfcf69954, total_fee=1, trade_state_desc=支付成功, trade_type=NATIVE, result_code=SUCCESS, attach=, time_end=20220910084218, is_subscribe=N, return_code=SUCCESS}', '2022-09-10 08:41:48', '2022-09-10 00:42:19', 0);
INSERT INTO `payment_info` VALUES (12, '166277176463992', 70, 2, '4200001572202209105652783970', 100.00, '2022-09-10|北京协和医院|多发性硬化专科门诊|副主任医师', 2, '2022-09-10 09:03:11', '{transaction_id=4200001572202209105652783970, nonce_str=2kmeFRwMihhR1u1r, trade_state=SUCCESS, bank_type=OTHERS, openid=oHwsHuDT9-LpQl-GDERTTzDB4vIM, sign=E40A1680BF0A874DF81385C2920D12DF, return_msg=OK, fee_type=CNY, mch_id=1558950191, cash_fee=1, out_trade_no=166277176463992, cash_fee_type=CNY, appid=wx74862e0dfcf69954, total_fee=1, trade_state_desc=支付成功, trade_type=NATIVE, result_code=SUCCESS, attach=, time_end=20220910090311, is_subscribe=N, return_code=SUCCESS}', '2022-09-10 09:02:49', '2022-09-10 01:03:10', 0);
INSERT INTO `payment_info` VALUES (13, '166277262566031', 71, 2, '4200001585202209103690145817', 100.00, '2022-09-10|北京协和医院|多发性硬化专科门诊|副主任医师', 2, '2022-09-10 09:17:40', '{transaction_id=4200001585202209103690145817, nonce_str=oAkmRGzKGGBakcwI, trade_state=SUCCESS, bank_type=OTHERS, openid=oHwsHuDT9-LpQl-GDERTTzDB4vIM, sign=F952512556B3C908750232409188BF0B, return_msg=OK, fee_type=CNY, mch_id=1558950191, cash_fee=1, out_trade_no=166277262566031, cash_fee_type=CNY, appid=wx74862e0dfcf69954, total_fee=1, trade_state_desc=支付成功, trade_type=NATIVE, result_code=SUCCESS, attach=, time_end=20220910091738, is_subscribe=N, return_code=SUCCESS}', '2022-09-10 09:17:21', '2022-09-10 01:17:39', 0);
INSERT INTO `payment_info` VALUES (14, '166277653713657', 73, 2, '4200001569202209101083700360', 100.00, '2022-09-10|北京协和医院|多发性硬化专科门诊|副主任医师', 2, '2022-09-10 10:22:44', '{transaction_id=4200001569202209101083700360, nonce_str=tJ2RBSMoqLwHDBP8, trade_state=SUCCESS, bank_type=OTHERS, openid=oHwsHuDT9-LpQl-GDERTTzDB4vIM, sign=E97A6566E7F07906BB9BBB079B675923, return_msg=OK, fee_type=CNY, mch_id=1558950191, cash_fee=1, out_trade_no=166277653713657, cash_fee_type=CNY, appid=wx74862e0dfcf69954, total_fee=1, trade_state_desc=支付成功, trade_type=NATIVE, result_code=SUCCESS, attach=, time_end=20220910102241, is_subscribe=N, return_code=SUCCESS}', '2022-09-10 10:22:19', '2022-09-10 02:22:43', 0);
INSERT INTO `payment_info` VALUES (15, '166277685023688', 74, 2, '4200001580202209105082263057', 100.00, '2022-09-10|北京协和医院|多发性硬化专科门诊|副主任医师', 2, '2022-09-10 10:28:00', '{transaction_id=4200001580202209105082263057, nonce_str=ZkJK9moDRu26WCCK, trade_state=SUCCESS, bank_type=OTHERS, openid=oHwsHuDT9-LpQl-GDERTTzDB4vIM, sign=F11FBDCA353669A64E368F3987AAD3E2, return_msg=OK, fee_type=CNY, mch_id=1558950191, cash_fee=1, out_trade_no=166277685023688, cash_fee_type=CNY, appid=wx74862e0dfcf69954, total_fee=1, trade_state_desc=支付成功, trade_type=NATIVE, result_code=SUCCESS, attach=, time_end=20220910102758, is_subscribe=N, return_code=SUCCESS}', '2022-09-10 10:27:41', '2022-09-10 02:28:00', 0);
INSERT INTO `payment_info` VALUES (16, '166280398302451', 76, 2, '4200001587202209105899842240', 100.00, '2022-09-13|北京协和医院|多发性硬化专科门诊|副主任医师', 2, '2022-09-10 18:00:02', '{transaction_id=4200001587202209105899842240, nonce_str=6gHZUPkTWkz5L8uH, trade_state=SUCCESS, bank_type=OTHERS, openid=oHwsHuDT9-LpQl-GDERTTzDB4vIM, sign=36D2997DD1B182579781CE2D5C4B7230, return_msg=OK, fee_type=CNY, mch_id=1558950191, cash_fee=1, out_trade_no=166280398302451, cash_fee_type=CNY, appid=wx74862e0dfcf69954, total_fee=1, trade_state_desc=支付成功, trade_type=NATIVE, result_code=SUCCESS, attach=, time_end=20220910184642, is_subscribe=N, return_code=SUCCESS}', '2022-09-10 17:59:46', '2022-09-10 10:00:01', 0);
INSERT INTO `payment_info` VALUES (17, '166281411954566', 77, 2, '4200001570202209102895413578', 100.00, '2022-09-13|北京协和医院|多发性硬化专科门诊|副主任医师', 2, '2022-09-10 20:48:55', '{transaction_id=4200001570202209102895413578, nonce_str=ua6iBoiANyd6CEEm, trade_state=SUCCESS, bank_type=OTHERS, openid=oHwsHuDT9-LpQl-GDERTTzDB4vIM, sign=9181003568BCC7A887B47D203445BE68, return_msg=OK, fee_type=CNY, mch_id=1558950191, cash_fee=1, out_trade_no=166281411954566, cash_fee_type=CNY, appid=wx74862e0dfcf69954, total_fee=1, trade_state_desc=支付成功, trade_type=NATIVE, result_code=SUCCESS, attach=, time_end=20220910204854, is_subscribe=N, return_code=SUCCESS}', '2022-09-10 20:48:42', '2022-09-10 12:48:54', 0);
INSERT INTO `payment_info` VALUES (18, '166287471475732', 78, 2, '4200001569202209115528811284', 100.00, '2022-09-13|北京协和医院|多发性硬化专科门诊|副主任医师', 2, '2022-09-11 13:38:53', '{transaction_id=4200001569202209115528811284, nonce_str=kfOBjsh8u7VoBFcn, trade_state=SUCCESS, bank_type=OTHERS, openid=oHwsHuDT9-LpQl-GDERTTzDB4vIM, sign=49CF58446C8E2E64D86C51260ACD82BE, return_msg=OK, fee_type=CNY, mch_id=1558950191, cash_fee=1, out_trade_no=166287471475732, cash_fee_type=CNY, appid=wx74862e0dfcf69954, total_fee=1, trade_state_desc=支付成功, trade_type=NATIVE, result_code=SUCCESS, attach=, time_end=20220911133855, is_subscribe=N, return_code=SUCCESS}', '2022-09-11 13:38:40', '2022-09-11 05:38:53', 0);

-- ----------------------------
-- Table structure for refund_info
-- ----------------------------
DROP TABLE IF EXISTS `refund_info`;
CREATE TABLE `refund_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `out_trade_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对外业务编号',
  `order_id` bigint NULL DEFAULT NULL COMMENT '订单编号',
  `payment_type` tinyint NULL DEFAULT NULL COMMENT '支付类型（微信 支付宝）',
  `trade_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易编号',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '退款金额',
  `subject` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易内容',
  `refund_status` tinyint NULL DEFAULT NULL COMMENT '退款状态',
  `callback_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调信息',
  `callback_time` datetime NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_out_trade_no`(`out_trade_no` ASC) USING BTREE,
  INDEX `idx_order_id`(`order_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '退款信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of refund_info
-- ----------------------------
INSERT INTO `refund_info` VALUES (1, '166277653713657', 73, 2, '50301903082022091024734822526', 100.00, '2022-09-10|北京协和医院|多发性硬化专科门诊|副主任医师', 2, '{\"transaction_id\":\"4200001569202209101083700360\",\"nonce_str\":\"FUfbT45tigY7Y5ba\",\"out_refund_no\":\"tk166277653713657\",\"sign\":\"98F14A3C70E944D1915FE324D529D79D\",\"return_msg\":\"OK\",\"mch_id\":\"1558950191\",\"refund_id\":\"50301903082022091024734822526\",\"cash_fee\":\"1\",\"out_trade_no\":\"166277653713657\",\"coupon_refund_fee\":\"0\",\"refund_channel\":\"\",\"appid\":\"wx74862e0dfcf69954\",\"refund_fee\":\"1\",\"total_fee\":\"1\",\"result_code\":\"SUCCESS\",\"coupon_refund_count\":\"0\",\"cash_refund_fee\":\"1\",\"return_code\":\"SUCCESS\"}', '2022-09-10 10:22:55', '2022-09-10 10:22:54', '2022-09-10 02:22:55', 0);
INSERT INTO `refund_info` VALUES (2, '166277685023688', 74, 2, '50301702892022091024737182890', 100.00, '2022-09-10|北京协和医院|多发性硬化专科门诊|副主任医师', 2, '{\"transaction_id\":\"4200001580202209105082263057\",\"nonce_str\":\"kywQqfxi5fOVMS1D\",\"out_refund_no\":\"tk166277685023688\",\"sign\":\"8D1D09951E207BD9DD6755D19851BAAB\",\"return_msg\":\"OK\",\"mch_id\":\"1558950191\",\"refund_id\":\"50301702892022091024737182890\",\"cash_fee\":\"1\",\"out_trade_no\":\"166277685023688\",\"coupon_refund_fee\":\"0\",\"refund_channel\":\"\",\"appid\":\"wx74862e0dfcf69954\",\"refund_fee\":\"1\",\"total_fee\":\"1\",\"result_code\":\"SUCCESS\",\"coupon_refund_count\":\"0\",\"cash_refund_fee\":\"1\",\"return_code\":\"SUCCESS\"}', '2022-09-10 10:28:14', '2022-09-10 10:28:13', '2022-09-10 02:28:13', 0);

SET FOREIGN_KEY_CHECKS = 1;
