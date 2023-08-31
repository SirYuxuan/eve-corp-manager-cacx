/*
 Navicat Premium Data Transfer

 Source Server         : 雨轩服务器
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost
 Source Schema         : eve_corp_manager_cacx

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 31/08/2023 17:20:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for corp_account_contract
-- ----------------------------
DROP TABLE IF EXISTS `corp_account_contract`;
CREATE TABLE `corp_account_contract` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contract_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `account_id` bigint DEFAULT NULL COMMENT '角色账号ID',
  `character_id` int DEFAULT NULL COMMENT '角色ID',
  `character_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名',
  `acceptor_id` int DEFAULT NULL COMMENT '接受人ID',
  `acceptor_name` varchar(100) DEFAULT NULL COMMENT '接受人名称',
  `acceptor_type` varchar(255) DEFAULT NULL,
  `assignee_id` int DEFAULT NULL COMMENT '受让人ID',
  `assignee_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '受让人名称',
  `assignee_type` varchar(255) DEFAULT NULL,
  `availability` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '有效范围',
  `buyout` double DEFAULT NULL COMMENT '一口价',
  `collateral` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '快递押金',
  `date_accepted` timestamp NULL DEFAULT NULL COMMENT '接受时间',
  `date_completed` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `date_expired` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `date_issued` timestamp NULL DEFAULT NULL COMMENT '发起时间',
  `days_to_complete` int DEFAULT NULL COMMENT '完成天数',
  `end_location_id` bigint DEFAULT NULL COMMENT '快递目的地',
  `end_location_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '快递目的地名称',
  `for_corporation` tinyint(1) DEFAULT NULL COMMENT '是否是军团合同',
  `issuer_corporation_id` int DEFAULT NULL COMMENT '发起军团ID',
  `issuer_corporation_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发起军团名称',
  `issuer_id` int DEFAULT NULL COMMENT '合同发起人ID',
  `issuer_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '合同发起人名称',
  `price` double DEFAULT NULL COMMENT '合同价格',
  `reward` double DEFAULT NULL COMMENT '快递奖金',
  `start_location_id` bigint DEFAULT NULL COMMENT '快递开始地点ID',
  `start_location_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '快递开始地点名称',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '合同状态',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '合同标题',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '合同类型',
  `volume` double DEFAULT NULL COMMENT '合同体积',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63406 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团成员合同表';

-- ----------------------------
-- Table structure for corp_account_contract_item
-- ----------------------------
DROP TABLE IF EXISTS `corp_account_contract_item`;
CREATE TABLE `corp_account_contract_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_id` bigint DEFAULT NULL,
  `contract_id` int DEFAULT NULL,
  `is_included` tinyint(1) DEFAULT NULL COMMENT '如果合同签发人已将此项目与合同一起提交，则为true；如果isser要求在合同中提供此项目，则为false',
  `is_singleton` tinyint(1) DEFAULT NULL COMMENT '是否独立？',
  `quantity` int DEFAULT NULL COMMENT '物品数量',
  `raw_quantity` int DEFAULT NULL COMMENT '-1表示该项是单例（不可堆叠）。如果项目恰好是蓝图，-1是原件，-2是蓝图副本',
  `record_id` bigint DEFAULT NULL COMMENT '针对这个合同物品的记录ID',
  `type_id` int DEFAULT NULL COMMENT '物品名称',
  `type_name` varchar(200) DEFAULT NULL COMMENT '物品价格',
  `sell_price` double DEFAULT NULL COMMENT '物品SELL价',
  `buy_price` double DEFAULT NULL COMMENT '物品BUY价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1551018 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团成员合同物品表';

-- ----------------------------
-- Table structure for corp_account_kill_mail
-- ----------------------------
DROP TABLE IF EXISTS `corp_account_kill_mail`;
CREATE TABLE `corp_account_kill_mail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `kill_mail_id` int DEFAULT NULL COMMENT '击杀ID',
  `kill_mail_hash` varchar(200) DEFAULT NULL COMMENT '击杀Hash',
  `kill_mail_time` timestamp NULL DEFAULT NULL COMMENT '击杀时间',
  `is_npc` tinyint(1) DEFAULT NULL COMMENT '是否是NPC击毁',
  `solar_system_id` int DEFAULT NULL COMMENT '所在星系ID',
  `solar_system_name` varchar(100) DEFAULT NULL COMMENT '所在星系名称',
  `damage_taken` decimal(20,4) DEFAULT NULL COMMENT '所受损失',
  `ship_type_id` int DEFAULT NULL COMMENT '舰船ID',
  `ship_type_name` varchar(200) DEFAULT NULL COMMENT '舰船名称',
  `character_id` int DEFAULT NULL COMMENT '角色ID',
  `character_name` varchar(200) DEFAULT NULL COMMENT '角色名称',
  `corporation_id` int DEFAULT NULL COMMENT '军团ID',
  `corporation_name` varchar(200) DEFAULT NULL COMMENT '军团名称',
  `alliance_id` int DEFAULT NULL COMMENT '联盟ID',
  `alliance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联盟名称',
  `user_id` bigint DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25466 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团成员KM记录表';

-- ----------------------------
-- Table structure for corp_account_kill_mail_item
-- ----------------------------
DROP TABLE IF EXISTS `corp_account_kill_mail_item`;
CREATE TABLE `corp_account_kill_mail_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `kill_mail_id` bigint DEFAULT NULL COMMENT 'KMID',
  `type_id` int DEFAULT NULL COMMENT '物品类型ID',
  `name` varchar(200) DEFAULT NULL COMMENT '物品名称',
  `num` bigint DEFAULT NULL COMMENT '数量',
  `type` varchar(255) DEFAULT NULL COMMENT '类型，掉落/损毁',
  `price` decimal(20,4) DEFAULT NULL COMMENT '估价',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=244671 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团成员KM记录详细损失表';

-- ----------------------------
-- Table structure for corp_account_order
-- ----------------------------
DROP TABLE IF EXISTS `corp_account_order`;
CREATE TABLE `corp_account_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `account_id` bigint DEFAULT NULL COMMENT '角色账号ID',
  `character_id` int DEFAULT NULL COMMENT '角色ID',
  `character_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名',
  `client_id` int DEFAULT NULL COMMENT '客户ID',
  `client_name` varchar(200) DEFAULT NULL COMMENT '客户名称',
  `client_type` varchar(200) DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL COMMENT '交易时间',
  `is_buy` tinyint(1) DEFAULT NULL COMMENT '是否是购买订单',
  `is_personal` tinyint(1) DEFAULT NULL COMMENT '是否是个人订单',
  `journal_ref_id` bigint DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  `location_name` varchar(200) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `unit_price` double DEFAULT NULL COMMENT '单价',
  `type_id` int DEFAULT NULL,
  `type_name` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=352810 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团成员市场交易订单';

-- ----------------------------
-- Table structure for corp_account_sale_order
-- ----------------------------
DROP TABLE IF EXISTS `corp_account_sale_order`;
CREATE TABLE `corp_account_sale_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `account_id` bigint DEFAULT NULL COMMENT '角色账号ID',
  `character_id` int DEFAULT NULL COMMENT '角色ID',
  `character_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名',
  `duration` int DEFAULT NULL COMMENT '时效',
  `escrow` double DEFAULT NULL,
  `is_buy_order` tinyint(1) DEFAULT NULL COMMENT '是否是购买订单',
  `is_corporation` tinyint(1) DEFAULT NULL COMMENT '是否是军团订单',
  `issued` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `location_id` bigint DEFAULT NULL COMMENT '位置ID',
  `location_name` varchar(255) DEFAULT NULL COMMENT '位置名称',
  `price` decimal(20,4) DEFAULT NULL COMMENT '价格',
  `range` varchar(255) DEFAULT NULL COMMENT '范围',
  `region_id` int DEFAULT NULL COMMENT '区域ID',
  `region_name` varchar(255) DEFAULT NULL COMMENT '区域名称',
  `type_id` int DEFAULT NULL COMMENT '物品ID',
  `type_name` varchar(255) DEFAULT NULL COMMENT '物品名称',
  `volume_remain` int DEFAULT NULL COMMENT '剩余数量',
  `volume_total` int DEFAULT NULL COMMENT '上架数量',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2796473 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团成员市场在售订单';

-- ----------------------------
-- Table structure for corp_account_skill
-- ----------------------------
DROP TABLE IF EXISTS `corp_account_skill`;
CREATE TABLE `corp_account_skill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `account_id` bigint DEFAULT NULL COMMENT '角色账号ID',
  `character_id` int DEFAULT NULL COMMENT '角色ID',
  `character_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名',
  `skill_id` int DEFAULT NULL COMMENT '技能ID',
  `skill_name` varchar(255) DEFAULT NULL COMMENT '技能名称',
  `active_skill_level` int DEFAULT NULL,
  `skill_points_in_skill` bigint DEFAULT NULL,
  `trained_skill_level` int DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=166421279 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团用户技能表';

-- ----------------------------
-- Table structure for corp_account_skill_queue
-- ----------------------------
DROP TABLE IF EXISTS `corp_account_skill_queue`;
CREATE TABLE `corp_account_skill_queue` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `account_id` bigint DEFAULT NULL COMMENT '角色账号ID',
  `character_id` int DEFAULT NULL COMMENT '角色ID',
  `character_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名',
  `skill_id` int DEFAULT NULL COMMENT '技能ID',
  `skill_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '技能名称',
  `finish_date` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `finished_level` int DEFAULT NULL COMMENT '完成等级',
  `level_end_sp` int DEFAULT NULL COMMENT '完成后技能点',
  `level_start_sp` int DEFAULT NULL COMMENT '开始前技能点',
  `queue_position` int DEFAULT NULL COMMENT '队列点数',
  `start_date` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `training_start_sp` int DEFAULT NULL COMMENT '培训开始技能点',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32784916 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团用户技能队列表';

-- ----------------------------
-- Table structure for corp_account_wallet
-- ----------------------------
DROP TABLE IF EXISTS `corp_account_wallet`;
CREATE TABLE `corp_account_wallet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `journal_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `account_id` bigint DEFAULT NULL COMMENT '角色账号ID',
  `character_id` int DEFAULT NULL COMMENT '角色ID',
  `character_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名',
  `amount` decimal(20,4) DEFAULT NULL COMMENT '交易金额',
  `balance` decimal(20,4) DEFAULT NULL COMMENT '余额',
  `context_id` bigint DEFAULT NULL COMMENT '上下文ID',
  `context_id_type` varchar(100) DEFAULT NULL COMMENT '上下文类型',
  `date` timestamp NULL DEFAULT NULL COMMENT '交易时间',
  `first_party_id` int DEFAULT NULL COMMENT '第一方ID',
  `first_party_name` varchar(200) DEFAULT NULL COMMENT '第一方名称',
  `first_party_type` varchar(200) DEFAULT NULL COMMENT '第一方类型',
  `description` varchar(200) DEFAULT NULL COMMENT '交易描述',
  `reason` varchar(500) DEFAULT NULL COMMENT '交易原因',
  `ref_type` varchar(100) DEFAULT NULL COMMENT '交易类型',
  `second_party_id` int DEFAULT NULL COMMENT '第三方ID',
  `second_party_name` varchar(200) DEFAULT NULL COMMENT '第三方名称',
  `second_party_type` varchar(200) DEFAULT NULL COMMENT '第三方类型',
  `tax` decimal(20,4) DEFAULT NULL COMMENT '纳税数额',
  `tax_receiver_id` int DEFAULT NULL COMMENT '税务记录ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130286 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团成员钱包流水';

-- ----------------------------
-- Table structure for corp_lp_goods
-- ----------------------------
DROP TABLE IF EXISTS `corp_lp_goods`;
CREATE TABLE `corp_lp_goods` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品标题',
  `type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品类型',
  `lp` decimal(11,2) DEFAULT NULL COMMENT '所需要的LP',
  `num` int DEFAULT NULL COMMENT '商品总数量',
  `shop_num` int NOT NULL DEFAULT '0' COMMENT '已销售数量',
  `pics` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '商品图片',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='LP商品表';

-- ----------------------------
-- Table structure for corp_lp_goods_order
-- ----------------------------
DROP TABLE IF EXISTS `corp_lp_goods_order`;
CREATE TABLE `corp_lp_goods_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
  `num` int DEFAULT NULL COMMENT '数量',
  `status` tinyint DEFAULT NULL COMMENT '状态1=等待，2=通过，3=拒绝',
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '兑换备注',
  `examine_content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '审批备注',
  `character_id` int DEFAULT NULL,
  `character_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货角色名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='军团LP商品购买记录表';

-- ----------------------------
-- Table structure for corp_lp_log
-- ----------------------------
DROP TABLE IF EXISTS `corp_lp_log`;
CREATE TABLE `corp_lp_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `account_id` bigint DEFAULT NULL COMMENT '角色ID',
  `character_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名',
  `lp` decimal(11,2) DEFAULT NULL COMMENT 'LP数量',
  `source` int DEFAULT NULL COMMENT '1=PAP自动转换,2=手动发放,3=用户转账，4=兑换商品,5=兑换退款,6=物品兑换,7=超网节点',
  `type` int DEFAULT NULL COMMENT 'LP操作，1=支出，2=收入',
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '说明',
  `order_id` bigint DEFAULT NULL COMMENT '兑换商品的日志ID',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `create_id` bigint DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10791 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='军团LP发放记录表';

-- ----------------------------
-- Table structure for corp_member
-- ----------------------------
DROP TABLE IF EXISTS `corp_member`;
CREATE TABLE `corp_member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `character_id` int DEFAULT NULL COMMENT '角色ID',
  `character_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `account_id` bigint DEFAULT NULL COMMENT '账号ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `corp_system` tinyint(1) DEFAULT NULL COMMENT '是否绑定军团系统',
  `seat_system` tinyint(1) DEFAULT NULL COMMENT '是否绑定SEAT系统',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '上次上线时间',
  `not_login_day` int DEFAULT NULL COMMENT '多少天未上线',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8786233 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团成员信息表';

-- ----------------------------
-- Table structure for corp_message_board
-- ----------------------------
DROP TABLE IF EXISTS `corp_message_board`;
CREATE TABLE `corp_message_board` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_id` bigint DEFAULT NULL,
  `character_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名称',
  `character_id` int DEFAULT NULL COMMENT '角色ID',
  `content` longtext COMMENT '动态内容',
  `likes` int DEFAULT NULL COMMENT '点赞数量',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='留言板';

-- ----------------------------
-- Table structure for corp_srp_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `corp_srp_blacklist`;
CREATE TABLE `corp_srp_blacklist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `character_id` bigint DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名字',
  `is_full` tinyint(1) DEFAULT NULL COMMENT '是否连坐其他角色',
  `end_time` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '截至时间',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '拦截原因',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='补损规则黑名单';

-- ----------------------------
-- Table structure for corp_srp_log
-- ----------------------------
DROP TABLE IF EXISTS `corp_srp_log`;
CREATE TABLE `corp_srp_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `kill_mail_id` bigint DEFAULT NULL COMMENT '击毁邮件ID',
  `status` int DEFAULT NULL COMMENT '状态1=待审批，2=已通过，3=已拒绝',
  `content` varchar(200) DEFAULT NULL COMMENT '提交备注',
  `sp_content` varchar(200) DEFAULT NULL COMMENT '审批备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1469 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团补损申请记录';

-- ----------------------------
-- Table structure for corp_srp_rules
-- ----------------------------
DROP TABLE IF EXISTS `corp_srp_rules`;
CREATE TABLE `corp_srp_rules` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ship_id` int DEFAULT NULL COMMENT '舰船ID',
  `ship_name` varchar(100) DEFAULT NULL COMMENT '舰船名称',
  `is_npc` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否支持NPC击杀',
  `join_time` int DEFAULT NULL COMMENT '入团多少天内支持补损',
  `not_srp` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁止补损',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `create_id` bigint DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='军团补损规则表';

-- ----------------------------
-- Table structure for corp_user_account
-- ----------------------------
DROP TABLE IF EXISTS `corp_user_account`;
CREATE TABLE `corp_user_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `character_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名称',
  `character_id` int DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint DEFAULT NULL COMMENT '系统用户ID',
  `is_main` tinyint(1) DEFAULT NULL COMMENT '主角色',
  `access_token` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT 'ESI/Token',
  `access_exp` timestamp NULL DEFAULT NULL COMMENT 'AccessToken过期时间',
  `refresh_token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ESI/Token',
  `corp_id` bigint DEFAULT NULL COMMENT '军团ID',
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '军团名称',
  `alliance_id` bigint DEFAULT NULL COMMENT '联盟ID',
  `alliance_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联盟名称',
  `isk` double NOT NULL DEFAULT '0' COMMENT 'ISK数量',
  `skill` bigint NOT NULL DEFAULT '0' COMMENT '技能点数量',
  `unallocated_sp` int DEFAULT NULL COMMENT '未分配技能点',
  `lp_now` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT 'LP当前数量',
  `lp_total` decimal(22,2) NOT NULL DEFAULT '0.00' COMMENT 'LP总计获得数量',
  `lp_use` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT 'LP已使用数量',
  `join_time` timestamp NULL DEFAULT NULL COMMENT '入团时间(加入主军团的时间)',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=969 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='军团用户角色表';

-- ----------------------------
-- Table structure for discord_bot
-- ----------------------------
DROP TABLE IF EXISTS `discord_bot`;
CREATE TABLE `discord_bot` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bot_id` bigint DEFAULT NULL COMMENT '机器人ID',
  `bot_name` varchar(255) DEFAULT NULL COMMENT '机器人名称',
  `guild_id` bigint DEFAULT NULL COMMENT '频道ID',
  `permissions` int DEFAULT NULL COMMENT '权限',
  `access_token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Discord认证Token',
  `refresh_token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Discord刷新Token',
  `expires_in` timestamp NULL DEFAULT NULL COMMENT 'Discord认证Token过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for eve_address
-- ----------------------------
DROP TABLE IF EXISTS `eve_address`;
CREATE TABLE `eve_address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `name_en` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='EVE地名对照';

-- ----------------------------
-- Table structure for eve_blue_print
-- ----------------------------
DROP TABLE IF EXISTS `eve_blue_print`;
CREATE TABLE `eve_blue_print` (
  `id` int NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '蓝图名称',
  `name_en` varchar(200) DEFAULT NULL COMMENT '蓝图英文名称',
  `max_limit` int DEFAULT NULL COMMENT '最大流程数',
  `copy_time` int DEFAULT NULL COMMENT '复制基础时间',
  `manufacturing_time` int DEFAULT NULL COMMENT '制造基础时间',
  `research_material_time` int DEFAULT NULL COMMENT '材料优化基础时间',
  `research_time_time` int DEFAULT NULL COMMENT '时间优化基础时间',
  `invention_time` int DEFAULT NULL COMMENT '发明基础时间',
  `reaction_time` int DEFAULT NULL COMMENT '反应基础时间',
  `group_id` int DEFAULT NULL COMMENT '分组ID ',
  `group_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分组名称',
  `group_name_en` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分组英文名称',
  `meta_group_id` int DEFAULT NULL COMMENT '元组ID',
  `meta_group_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '元组名称',
  `meta_group_name_en` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '元组英文名称',
  `market_group_id` int DEFAULT NULL COMMENT '市场分组ID',
  `market_group_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市场分组名称',
  `market_group_name_en` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市场分组英文名称',
  `category_id` int DEFAULT NULL COMMENT '分类ID',
  `category_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分类名称',
  `category_name_en` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分类英文名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='EVE-蓝图表';

-- ----------------------------
-- Table structure for eve_blue_print_materials
-- ----------------------------
DROP TABLE IF EXISTS `eve_blue_print_materials`;
CREATE TABLE `eve_blue_print_materials` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blue_print_id` int DEFAULT NULL COMMENT '蓝图ID',
  `type` int DEFAULT NULL COMMENT '类型 1=复制，2=材料优化，3=时间优化，4=发明，5=制造，6=反应',
  `type_id` int DEFAULT NULL COMMENT '类型ID',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型名称',
  `type_name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型英文名称',
  `quantity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '数量',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51383 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='EVE-蓝图材料表';

-- ----------------------------
-- Table structure for eve_blue_print_products
-- ----------------------------
DROP TABLE IF EXISTS `eve_blue_print_products`;
CREATE TABLE `eve_blue_print_products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blue_print_id` int DEFAULT NULL COMMENT '蓝图ID',
  `type` int DEFAULT NULL COMMENT '类型 1=发明产出，2=制造产出，3=反应产出',
  `products_id` int DEFAULT NULL COMMENT '产出物品ID',
  `products_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '产出物品名称',
  `products_name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '产出物品名称英文',
  `products_quantity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '产出物品数量',
  `probability` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '产出概率',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9611 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='EVE-蓝图产出表';

-- ----------------------------
-- Table structure for eve_blue_print_skill
-- ----------------------------
DROP TABLE IF EXISTS `eve_blue_print_skill`;
CREATE TABLE `eve_blue_print_skill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blue_print_id` int DEFAULT NULL COMMENT '蓝图ID',
  `type` int DEFAULT NULL COMMENT '类型 1=复制，2=材料优化，3=时间优化，4=发明，5=制造，6=反应',
  `skills_id` int DEFAULT NULL COMMENT '技能ID',
  `skills_name` varchar(255) DEFAULT NULL COMMENT '技能名称',
  `skills_name_en` varchar(255) DEFAULT NULL COMMENT '技能名称英文',
  `level` int DEFAULT NULL COMMENT '等级',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17911 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='EVE-蓝图技能表';

-- ----------------------------
-- Table structure for eve_category
-- ----------------------------
DROP TABLE IF EXISTS `eve_category`;
CREATE TABLE `eve_category` (
  `id` int NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `name_en` varchar(200) DEFAULT NULL COMMENT '分类英文名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='EVE-分类表';

-- ----------------------------
-- Table structure for eve_group
-- ----------------------------
DROP TABLE IF EXISTS `eve_group`;
CREATE TABLE `eve_group` (
  `id` int DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '分组名称',
  `name_en` varchar(200) DEFAULT NULL COMMENT '分组英文名称',
  `icon_id` int DEFAULT NULL COMMENT '图标ID',
  `category_id` int DEFAULT NULL COMMENT '分类ID',
  `category_name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `category_name_en` varchar(200) DEFAULT NULL COMMENT '分类英文名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='EVE-分组表';

-- ----------------------------
-- Table structure for eve_market_group
-- ----------------------------
DROP TABLE IF EXISTS `eve_market_group`;
CREATE TABLE `eve_market_group` (
  `id` int DEFAULT NULL,
  `pid` int DEFAULT NULL COMMENT '父级ID',
  `name` varchar(200) DEFAULT NULL COMMENT '市场分组名称',
  `name_en` varchar(200) DEFAULT NULL COMMENT '市场分组英文名称',
  `icon_id` int DEFAULT NULL COMMENT '图标ID',
  `description` text COMMENT '描述',
  `description_en` text COMMENT '描述英文',
  `has_type` tinyint(1) DEFAULT NULL COMMENT '是否是类型了',
  `crate_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='EVE-市场分组表';

-- ----------------------------
-- Table structure for eve_meta_group
-- ----------------------------
DROP TABLE IF EXISTS `eve_meta_group`;
CREATE TABLE `eve_meta_group` (
  `id` int DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '元分组名称',
  `name_en` varchar(200) DEFAULT NULL COMMENT '元分组英文名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='EVE-元分组表';

-- ----------------------------
-- Table structure for eve_type
-- ----------------------------
DROP TABLE IF EXISTS `eve_type`;
CREATE TABLE `eve_type` (
  `id` int NOT NULL COMMENT '物品ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '物品名称',
  `name_en` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '物品名称英文',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '物品描述',
  `description_en` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '物品描述英文',
  `group_id` int DEFAULT NULL COMMENT '分组ID',
  `group_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分组名称',
  `group_name_en` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分组名称英文',
  `meta_group_id` int DEFAULT NULL COMMENT '元分组ID',
  `meta_group_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '元分组名称',
  `meta_group_name_en` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '元分组名称英文',
  `market_group_id` int DEFAULT NULL COMMENT '市场分组ID',
  `market_group_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '市场分组名称',
  `market_group_name_en` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '市场分组名称英文',
  `category_id` int DEFAULT NULL COMMENT '分类ID',
  `category_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分类名称',
  `category_name_en` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分类名称中文',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`id`) USING BTREE,
  KEY `groupId` (`group_id`) USING BTREE,
  KEY `market_group_id` (`market_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='EVE所有的物品ID和名称';

-- ----------------------------
-- Table structure for eve_type_alias
-- ----------------------------
DROP TABLE IF EXISTS `eve_type_alias`;
CREATE TABLE `eve_type_alias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `alias` varchar(200) DEFAULT NULL,
  `qq` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_attach
-- ----------------------------
DROP TABLE IF EXISTS `sys_attach`;
CREATE TABLE `sys_attach` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `upload_type` int DEFAULT NULL COMMENT '上传类型',
  `file_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '文件保存目录',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '文件访问URL',
  `file_name` varchar(200) DEFAULT NULL COMMENT '保存的文件名',
  `original_name` varchar(200) DEFAULT NULL COMMENT '原始文件名',
  `md5` varchar(32) DEFAULT NULL COMMENT '文件MD5',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统-附件表';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '配置名称',
  `value` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '配置值',
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=659 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='系统配置表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单名称',
  `pid` bigint DEFAULT NULL COMMENT '父级ID',
  `type` int DEFAULT NULL COMMENT '菜单类型 0=目录,1=菜单,2=按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `is_link` tinyint(1) DEFAULT '0' COMMENT '是否外链',
  `frame` tinyint(1) DEFAULT NULL COMMENT '是否内嵌',
  `link_url` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '外链地址',
  `hidden` tinyint(1) DEFAULT NULL COMMENT '是否可见',
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限字符串',
  `path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
  `cache` tinyint(1) DEFAULT NULL COMMENT '是否缓存',
  `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件地址',
  `sort` int DEFAULT NULL COMMENT '排序号',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统-菜单表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色名',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统-角色表';

-- ----------------------------
-- Table structure for sys_roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_menus`;
CREATE TABLE `sys_roles_menus` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `virtually` tinyint(1) DEFAULT NULL COMMENT '是否是虚拟的',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE,
  KEY `FKc67smp0fqtqvu676ed5lt5yfg` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统-角色菜单关联表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像ID',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '用户状态，0=正常，1=冻结，2=锁定',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `sex` int DEFAULT NULL COMMENT '性别 0=女,1=男,2=未知',
  `qq` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'QQ',
  `city` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '城市',
  `discord_id` bigint DEFAULT NULL COMMENT 'DiscordID 此项不为空代表已经绑定',
  `discord_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Discord昵称',
  `discord_email` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Discord邮箱',
  `discord_access_token` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Discord认证Token',
  `discord_refresh_token` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Discord刷新Token',
  `discord_expires_in` timestamp NULL DEFAULT NULL COMMENT 'Discord认证Token过期时间',
  `login_time` timestamp NULL DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录IP',
  `login_city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录城市',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKloay4nqos33x012wmyx7lxunh` (`avatar`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3900081 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统-用户表';

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统-用户角色关联表';

SET FOREIGN_KEY_CHECKS = 1;
