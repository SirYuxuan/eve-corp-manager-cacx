/*
 Navicat Premium Data Transfer

 Source Server         : 本地Master
 Source Server Type    : MySQL
 Source Server Version : 80100
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80100
 File Encoding         : 65001

 Date: 05/09/2023 08:24:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '配置名称',
  `value` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT '配置值',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '标题',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '备注',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '更新时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=659 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='系统配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (1, 'aliyun.access.key', NULL, '阿里云key', NULL, '2022-12-13 21:48:58', 1, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (2, 'aliyun.access.secret', NULL, '阿里云密钥', NULL, '2022-12-13 21:49:59', 1, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (3, 'aliyun.oss.endpoint', NULL, '阿里云OSS的地域', NULL, '2022-12-13 21:47:14', 1, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (4, 'aliyun.oss.bucket', NULL, '阿里云OSS的bucket', NULL, '2022-12-13 21:48:38', 1, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (5, 'mail.user', NULL, '邮件发送账号', NULL, '2022-12-23 13:52:57', 1, 'Sir丶雨轩', NULL, NULL, NULL);
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (6, 'mail.pass', NULL, '邮件发送密码', NULL, '2022-12-23 13:52:57', 1, 'Sir丶雨轩', NULL, NULL, NULL);
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (7, 'mail.from', NULL, '邮件发送人', NULL, '2022-12-23 13:52:57', 1, 'Sir丶雨轩', NULL, NULL, NULL);
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (8, 'mail.host', NULL, '邮件域名', NULL, '2022-12-23 13:52:57', 1, 'Sir丶雨轩', NULL, NULL, NULL);
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (9, 'mail.port', NULL, '邮件端口', NULL, '2022-12-23 13:52:57', 1, 'Sir丶雨轩', NULL, NULL, NULL);
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (13, 'rsaPrivate', NULL, 'RSA私钥', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (14, 'rsaPublic', NULL, 'RSA公钥', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (20, 'eve.esi.client', NULL, 'EVE ESI ClientID', NULL, '2022-12-23 13:52:57', 1, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (21, 'eve.esi.secret', NULL, 'EVE ESI SecretKey', NULL, '2022-12-13 21:50:47', 1, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (22, 'eve.es.callback', NULL, 'EVE ESI 授权回调', NULL, '2022-12-13 21:51:11', 1, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (264, 'web.site', NULL, '前端地址', NULL, '2022-12-13 21:41:28', 1, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (644, 'eve.main.corp', NULL, 'EVE主军团ID', NULL, '2022-12-13 21:50:25', 1, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (646, 'upload.local.path', NULL, '本地上传保存目录', NULL, NULL, NULL, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (647, 'upload.local.url', NULL, '本地上传保存URL', NULL, NULL, NULL, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (648, 'upload.type', NULL, '上传类型', '上传类型：1=本地文件，2=阿里云', '2022-12-13 21:45:00', 1, 'Sir丶雨轩', '2022-12-13 21:31:21', NULL, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (649, 'seat.cookie', NULL, '联盟通讯Cookie', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (650, 'discord.client.id', NULL, 'Discord客户端ID', NULL, NULL, NULL, NULL, '2023-01-11 11:34:13', 1, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (651, 'discord.public.key', NULL, 'Discord客户端密钥', NULL, NULL, NULL, NULL, '2023-01-11 11:34:42', 1, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (652, 'discord.redirect.uri', NULL, 'discord 授权回调', NULL, '2023-01-11 15:18:22', 1, 'Sir丶雨轩', '2023-01-11 13:04:42', 1, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (653, 'discord.client.secret', NULL, 'Discord客户端密钥', '', NULL, NULL, NULL, '2023-01-11 13:48:26', 1, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (654, 'discord.bot.token', NULL, 'Discord机器人Token', NULL, '2023-01-12 10:37:18', 1, 'Sir丶雨轩', '2023-01-12 09:17:44', 1, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (655, 'discord.corp.guilds', NULL, 'Discord军团频道ID', NULL, NULL, NULL, NULL, '2023-01-12 09:21:47', 1, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (656, 'discord.role.corp.member', NULL, 'Discord军团成员组ID', NULL, NULL, NULL, NULL, '2023-01-12 10:03:22', 1, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (657, 'discord.role.corp.admin', NULL, '军团总监组ID', NULL, NULL, NULL, NULL, '2023-01-12 10:03:49', 1, 'Sir丶雨轩');
INSERT INTO `sys_config` (`id`, `name`, `value`, `title`, `remark`, `update_time`, `update_id`, `update_by`, `create_time`, `create_id`, `create_by`) VALUES (658, 'template.path', NULL, '图片模板目录', NULL, '2023-01-13 16:50:01', 1, 'Sir丶雨轩', '2023-01-13 16:01:16', 1, 'Sir丶雨轩');
COMMIT;

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
  `link_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '外链地址',
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
  `affix` tinyint(1) DEFAULT NULL COMMENT '是否禁止关闭此选项卡',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统-菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (1, '系统设置', 0, 0, 'setting|svg', 0, NULL, NULL, 0, 'system', '/system', 1, 'LAYOUT', 999, '2021-06-23 16:09:11', NULL, NULL, '2022-12-13 09:29:34', 1, 'Sir丶雨轩', NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (2, '用户管理', 1, 1, 'user|svg', 0, NULL, NULL, 0, 'user', 'user', 1, 'sys/user/index', 1, NULL, NULL, NULL, '2022-12-12 12:38:03', 1, 'Sir丶雨轩', NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (3, '菜单管理', 1, 1, 'menu|svg', 0, NULL, NULL, 0, 'menu', 'menu', 1, 'sys/menu/index', 2, NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (4, '角色管理', 1, 1, 'role|svg', 0, NULL, NULL, 0, 'role', 'role', 1, 'sys/role/index', 3, NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (5, '新增', 2, 2, NULL, 0, NULL, NULL, 0, 'user:add', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (6, '编辑', 2, 2, NULL, 0, NULL, NULL, 0, 'user:edit', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (7, '删除', 2, 2, NULL, 0, NULL, NULL, 0, 'user:del', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (8, '新增', 4, 2, NULL, 0, NULL, NULL, 0, 'role:add', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (9, '编辑', 4, 2, NULL, 0, NULL, NULL, 0, 'role:edit', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (10, '删除', 4, 2, NULL, 0, NULL, NULL, 0, 'role:del', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (11, '新增', 3, 2, NULL, 0, NULL, NULL, 0, 'menu:add', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (12, '编辑', 3, 2, NULL, 0, NULL, NULL, 0, 'menu:edit', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (13, '删除', 3, 2, NULL, 0, NULL, NULL, 0, 'menu:del', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (14, '工作台', 0, 1, 'dashboard|svg', 0, NULL, NULL, 0, 'dashboard', 'dashboard', 1, 'dashboard/workbench/index', 1, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (16, '详情', 2, 2, NULL, 0, NULL, NULL, NULL, 'user:details', NULL, 1, NULL, 999, '2022-12-12 12:45:57', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (17, 'ESI授权', 0, 1, 'anquan|svg', 0, NULL, NULL, 0, NULL, 'esi', 1, 'esi/index', 2, '2022-12-12 15:00:41', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (18, 'LP中心', 0, 0, 'lp|svg', 0, NULL, NULL, 0, NULL, '/lp', 1, 'LAYOUT', 4, '2022-12-13 09:30:39', 1, 'Sir丶雨轩', '2022-12-14 17:15:24', 1, 'Sir丶雨轩', NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (19, '发放LP', 18, 1, 'fly|svg', 0, NULL, NULL, 0, NULL, 'send', 1, 'lp/send/index', 1, '2022-12-13 09:36:05', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (20, '我的LP', 18, 1, 'yelp|svg', 0, NULL, NULL, 0, NULL, 'me', 1, 'lp/me/index', 2, '2022-12-13 13:00:12', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (21, '商品管理', 18, 1, 'goods|svg', 0, NULL, NULL, 0, NULL, 'goods', 1, 'lp/goods/index', 3, '2022-12-13 15:40:38', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (23, '系统配置', 1, 1, 'dept|svg', 0, NULL, NULL, 0, NULL, 'config', 1, 'sys/config/index', 4, '2022-12-13 21:08:51', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (24, '订单管理', 18, 1, 'order|svg', 0, NULL, NULL, 0, NULL, 'order', 1, 'lp/order/index', 4, '2022-12-14 09:07:04', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (25, '审批', 24, 2, NULL, 0, NULL, NULL, NULL, 'order:approval', NULL, NULL, NULL, 1, '2022-12-14 09:40:42', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (26, '查看', 24, 2, NULL, 0, NULL, NULL, NULL, 'order:view', NULL, NULL, NULL, 999, '2022-12-14 10:39:38', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (27, '发放记录', 18, 1, 'task|svg', 0, NULL, NULL, 0, NULL, 'log', 1, 'lp/log/index', 5, '2022-12-14 16:21:22', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (28, 'LP商城', 0, 1, 'shop|svg', 0, NULL, NULL, 0, NULL, 'shop', 1, 'lp/shop/index', 3, '2022-12-14 17:21:10', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (30, '发放', 19, 2, NULL, 0, NULL, NULL, NULL, 'lp:send', NULL, NULL, NULL, 1, '2022-12-22 11:17:57', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (31, '常用工具', 0, 0, 'util|svg', 0, NULL, NULL, 0, NULL, '/utils', 1, 'LAYOUT', 5, '2022-12-22 11:42:00', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (32, '留言板', 31, 1, 'liuyan|svg', 0, NULL, NULL, 0, NULL, 'talk', 1, 'utils/talk/index', 4, '2022-12-22 11:44:21', 1, 'Sir丶雨轩', '2022-12-22 11:44:41', 1, 'Sir丶雨轩', NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (33, '补损提交', 31, 1, 'kill|svg', 0, NULL, NULL, 0, NULL, 'srp', 1, 'utils/srp/index', 2, '2022-12-22 13:49:46', 1, 'Sir丶雨轩', '2023-01-11 10:53:16', 1, 'Sir丶雨轩', NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (34, '数据维护', 0, 0, 'database|svg', 0, NULL, NULL, 0, NULL, '/data', 1, 'LAYOUT', 6, '2022-12-24 15:24:29', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (35, '军团成员', 34, 1, 'dynamic-avatar-4|svg', 0, NULL, NULL, 0, NULL, 'member', 1, 'data/member/index', 1, '2022-12-24 15:25:38', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (36, '补损规则', 34, 1, 'rules|svg', 0, NULL, NULL, 0, NULL, 'srpRules', 1, 'data/srp/rules/index', 2, '2022-12-25 11:20:56', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (37, '新增', 36, 2, NULL, 0, NULL, NULL, NULL, 'srpRules:add', NULL, NULL, NULL, 1, '2022-12-25 12:21:00', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (38, '编辑', 36, 2, NULL, 0, NULL, NULL, NULL, 'srpRules:edit', NULL, NULL, NULL, 1, '2022-12-25 12:21:15', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (39, '删除', 36, 2, NULL, 0, NULL, NULL, NULL, 'srpRules:del', NULL, NULL, NULL, 1, '2022-12-25 12:21:27', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (40, '列表', 36, 2, NULL, 0, NULL, NULL, NULL, 'srpRules', NULL, NULL, NULL, 1, '2022-12-25 12:22:38', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (41, '补损黑名单', 34, 1, 'blacklist|svg', 0, NULL, NULL, 0, NULL, 'srpBlacklist', 1, 'data/srp/blacklist/index', 3, '2022-12-25 13:04:45', 1, 'Sir丶雨轩', '2022-12-25 13:07:48', 1, 'Sir丶雨轩', NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (42, '查看', 41, 2, NULL, 0, NULL, NULL, NULL, 'srpBlacklist', NULL, NULL, NULL, 1, '2022-12-25 13:06:11', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (43, '新增', 41, 2, NULL, 0, NULL, NULL, NULL, 'srpBlacklist:add', NULL, NULL, NULL, 1, '2022-12-25 13:06:25', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (44, '编辑', 41, 2, NULL, 0, NULL, NULL, NULL, 'srpBlacklist:edit', NULL, NULL, NULL, 1, '2022-12-25 13:06:36', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (45, '删除', 41, 2, NULL, 0, NULL, NULL, NULL, 'srpBlacklist:del', NULL, NULL, NULL, 1, '2022-12-25 13:06:55', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (46, '补损审批', 34, 1, 'sh|svg', 0, NULL, NULL, 0, NULL, 'srpExamine', 1, 'data/srp/examine/index', 4, '2022-12-25 13:49:43', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (47, '个人信息', 0, 0, 'dynamic-avatar-6|svg', 0, NULL, NULL, 0, NULL, '/account', 1, 'LAYOUT', 7, '2022-12-25 15:09:25', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (48, '钱包流水', 47, 1, 'wallet|svg', 0, NULL, NULL, 0, NULL, 'wallet', 1, 'account/wallet/index', 2, '2022-12-25 15:12:34', 1, 'Sir丶雨轩', '2022-12-26 17:19:00', 1, 'Sir丶雨轩', NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (49, '交易历史', 47, 1, 'order|svg', 0, NULL, NULL, 0, NULL, 'order', 1, 'account/order/index', 3, '2022-12-26 14:26:36', 1, 'Sir丶雨轩', '2022-12-26 17:19:04', 1, 'Sir丶雨轩', NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (50, '数据分析', 47, 1, 'total-sales|svg', 0, NULL, NULL, 0, NULL, 'analysis', 1, 'dashboard/analysis/index', 1, '2022-12-26 17:18:34', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (51, '查看', 50, 2, NULL, 0, NULL, NULL, NULL, 'view', NULL, NULL, NULL, 1, '2022-12-26 20:33:38', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (52, '查询全部', 50, 2, NULL, 0, NULL, NULL, NULL, 'analysis:all', NULL, NULL, NULL, 1, '2022-12-26 20:34:02', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (53, '合同管理', 47, 1, 'menu|svg', 0, NULL, NULL, 0, NULL, 'contract', 1, 'account/contract/index', 4, '2022-12-27 10:46:23', 1, 'Sir丶雨轩', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `name`, `pid`, `type`, `icon`, `is_link`, `frame`, `link_url`, `hidden`, `permission`, `path`, `cache`, `component`, `sort`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`, `affix`) VALUES (54, '加入Discord', 31, 1, 'discord|svg', 1, 0, 'http://discord.hd-eve.com/discord/auth', 0, NULL, 'http://discord.hd-eve.com/discord/auth', 1, NULL, 1, '2023-01-11 10:38:11', 1, 'Sir丶雨轩', '2023-01-11 15:07:46', 1, 'Sir丶雨轩', NULL);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统-角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `remark`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`) VALUES (1, '超级管理员', '系统最高权限', '2022-09-19 14:09:02', NULL, NULL, '2022-12-14 10:19:07', 1, 'Sir丶雨轩');
COMMIT;

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
-- Records of sys_roles_menus
-- ----------------------------
BEGIN;
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 1, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 2, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 3, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 4, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 5, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 6, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 7, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 8, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 9, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 10, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 11, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 12, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 13, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 14, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 16, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 17, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 18, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 19, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 20, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 21, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 23, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 24, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 25, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 26, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 27, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 28, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 30, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 31, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 32, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 33, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 34, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 35, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 36, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 37, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 38, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 39, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 40, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 41, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 42, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 43, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 44, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 45, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 46, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 47, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 48, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 49, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 50, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 51, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 52, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 53, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (1, 54, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (2, 1, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (2, 2, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (2, 3, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (2, 4, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (2, 5, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (2, 7, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (2, 8, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (2, 9, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (2, 10, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (2, 12, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (2, 14, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (3, 14, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (3, 17, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 14, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 17, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 18, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 20, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 24, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 26, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 27, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 28, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 31, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 32, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 33, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 34, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 36, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 40, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 41, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 42, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 47, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 48, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 49, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 50, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 51, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 53, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (4, 54, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (5, 18, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (5, 19, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (5, 30, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 34, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 36, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 37, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 38, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 39, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 40, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 41, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 42, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 43, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 44, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 45, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (6, 46, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (10, 18, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (10, 21, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (10, 24, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (10, 25, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 14, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 17, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 18, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 19, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 20, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 21, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 24, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 25, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 26, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 27, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 28, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 30, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 31, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 32, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 33, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 34, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 35, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 36, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 37, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 38, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 39, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 40, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 41, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 42, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 43, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 44, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 45, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 46, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 47, 1);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 48, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 49, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 50, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 51, 0);
INSERT INTO `sys_roles_menus` (`role_id`, `menu_id`, `virtually`) VALUES (11, 52, 0);
COMMIT;

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
  `qq` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'QQ',
  `city` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '城市',
  `discord_id` bigint DEFAULT NULL COMMENT 'DiscordID 此项不为空代表已经绑定',
  `discord_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Discord昵称',
  `discord_email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Discord邮箱',
  `discord_access_token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Discord认证Token',
  `discord_refresh_token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Discord刷新Token',
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统-用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `nick_name`, `avatar`, `status`, `phone`, `email`, `sex`, `qq`, `city`, `discord_id`, `discord_name`, `discord_email`, `discord_access_token`, `discord_refresh_token`, `discord_expires_in`, `login_time`, `login_ip`, `login_city`, `create_time`, `create_id`, `create_by`, `update_time`, `update_id`, `update_by`) VALUES (1, 'yuxuan', 'e42771916d864d0862d056c27a753f1bf6d788bee35d6fd1:a1c4f1f306aa14be888c62235e2f624083ea01cf3d55133d', 'Sir丶雨轩', 'https://eve-corp-cacx-data.oss-cn-hangzhou.aliyuncs.com/2022/12/23/1671799106093_97860.jpg', 0, '15715312568', '1718018032@qq.com', 2, '1718018032', '济南', 912146081452789830, 'Sir丶雨轩', '1718018032@qq.com', 'i2MP9eZDSWmFGrAWlR0D29xlUx2CRh', 'oXLaRGCRuGyHYFGEJwJZtvrURG25VG', '2023-01-19 10:27:28', '2023-09-01 08:53:58', '1.198.30.98', '河南省郑州市 电信', '2022-09-16 16:02:49', -1, '系统管理员', '2023-09-01 08:53:59', 1, 'Sir丶雨轩');
COMMIT;

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统-用户角色关联表';

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
BEGIN;
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (1, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (1, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (9, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (9, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (10, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (10, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (11, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (11, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (11, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (12, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (12, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (13, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (13, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (14, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (14, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (15, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (16, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (16, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (16, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (17, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (17, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (18, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (18, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (19, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (19, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (19, 5);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (19, 10);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (20, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (20, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (21, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (21, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (22, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (22, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (23, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (23, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (24, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (24, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (25, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (25, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (26, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (26, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (27, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (28, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (28, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (29, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (29, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (30, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (30, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (31, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (31, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (32, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (32, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (33, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (33, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (33, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (34, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (34, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (35, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (35, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (36, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (36, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (37, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (37, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (38, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (38, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (38, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (39, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (39, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (40, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (40, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (40, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (41, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (41, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (41, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (42, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (42, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (43, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (43, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (44, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (44, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (45, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (46, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (46, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (47, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (48, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (48, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (49, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (49, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (50, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (50, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (51, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (51, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (52, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (52, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (53, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (54, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (54, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (55, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (55, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (56, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (56, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (57, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (57, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (58, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (59, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (59, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (59, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (60, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (60, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (61, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (61, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (62, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (63, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (63, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (64, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (64, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (65, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (66, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (66, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (67, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (68, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (68, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (69, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (69, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (69, 5);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (69, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (70, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (70, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (71, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (71, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (72, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (72, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (73, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (73, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (74, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (74, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (75, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (75, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (76, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (76, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (77, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (78, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (79, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (79, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (80, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (81, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (81, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (82, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (83, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (84, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (85, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (85, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (86, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (86, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (86, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (87, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (87, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (88, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (88, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (89, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (89, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (89, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (90, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (91, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (91, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (92, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (92, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (93, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (93, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (94, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (94, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (95, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (95, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (96, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (96, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (97, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (97, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (98, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (99, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (99, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (100, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (100, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (101, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (102, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (102, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (103, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (103, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (104, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (105, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (106, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (106, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (107, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (107, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (108, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (108, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (109, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (110, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (111, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (112, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (113, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (114, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (114, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (115, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (115, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (116, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (116, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (117, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (117, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (118, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (118, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (119, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (119, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (120, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (121, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (121, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (122, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (122, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (123, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (123, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (124, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (124, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (125, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (125, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (126, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (126, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (127, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (127, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (127, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (128, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (128, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (128, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (129, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (129, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (130, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (130, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (131, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (131, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (131, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (132, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (132, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (133, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (133, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (134, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (134, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (135, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (136, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (136, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (137, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (137, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (138, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (138, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (139, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (140, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (141, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (141, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (142, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (142, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (143, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (143, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (143, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (144, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (145, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (146, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (147, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (147, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (148, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (148, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (149, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (149, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (150, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (151, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (151, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (152, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (152, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (153, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (154, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (155, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (155, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (155, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (156, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (157, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (158, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (158, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (159, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (159, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (160, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (161, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (161, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (162, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (162, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (163, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (163, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (164, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (164, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (165, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (165, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (166, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (166, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (167, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (168, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (168, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (169, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (169, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (170, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (170, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (171, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (171, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (172, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (172, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (173, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (173, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (174, 1);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (174, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (174, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (174, 11);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (175, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (175, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (176, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (177, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (177, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (178, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (179, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (179, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (180, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (180, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (181, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (182, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (183, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (183, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (184, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (185, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (185, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (186, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (186, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (187, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (188, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (188, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (189, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (190, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (190, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (191, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (191, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (192, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (193, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (193, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (194, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (194, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (195, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (196, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (196, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (197, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (197, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (198, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (199, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (199, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (200, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (200, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (201, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (201, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (202, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (203, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (203, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (204, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (204, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (205, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (206, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (207, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (207, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (208, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (208, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (209, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (210, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (211, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (212, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (212, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (213, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (213, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (214, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (215, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (215, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (216, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (217, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (218, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (218, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (219, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (219, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (220, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (221, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (222, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (222, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (223, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (223, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (224, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (224, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (225, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (225, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (226, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (226, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (227, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (227, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (228, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (229, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (229, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (230, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (231, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (232, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (232, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (233, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (234, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (234, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (235, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (235, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (236, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (236, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (237, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (237, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (238, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (238, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (239, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (240, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (240, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (241, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (241, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (242, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (242, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (243, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (244, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (245, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (245, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (246, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (247, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (247, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (248, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (248, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (249, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (249, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (250, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (250, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (251, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (252, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (253, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (253, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (254, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (254, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (255, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (256, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (257, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (258, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (258, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (259, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (259, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (260, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (260, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (261, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (261, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (262, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (263, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (264, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (265, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (265, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (266, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (267, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (267, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (268, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (268, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (269, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (269, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (270, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (271, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (272, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (272, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (273, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (274, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (275, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (275, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (276, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (277, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (278, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (278, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (279, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (279, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (280, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (281, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (282, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (282, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (283, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (284, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (285, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (285, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (286, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (286, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (287, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (287, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (288, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (288, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (289, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (290, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (291, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (292, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (293, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (293, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (294, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (294, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (295, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (296, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (297, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (298, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (299, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (300, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (301, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (302, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (303, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (303, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (304, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (305, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (305, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (306, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (306, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (307, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (308, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (309, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (310, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (311, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (312, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (313, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (314, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (314, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (315, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (316, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (316, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (317, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (317, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (318, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (318, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (319, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (320, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (321, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (322, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (322, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (323, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (323, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (324, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (324, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (325, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (325, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (326, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (327, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (327, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (328, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (328, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (329, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (330, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (330, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (331, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (331, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (332, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (332, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (333, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (333, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (334, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (334, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (335, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (335, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (336, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (337, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (338, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (339, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (340, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (340, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (341, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (342, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (343, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (343, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (344, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (345, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (345, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (346, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (346, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (347, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (348, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (348, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (349, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (350, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (351, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (352, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (352, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (353, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (354, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (355, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (356, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (356, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (357, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (357, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (358, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (359, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (359, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (360, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (361, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (361, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (362, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (362, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (363, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (363, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (364, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (365, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (366, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (367, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (368, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (368, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (369, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (370, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3899999, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900000, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900001, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900002, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900003, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900004, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900005, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900005, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900006, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900006, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900007, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900007, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900008, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900009, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900009, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900010, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900011, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900011, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900012, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900013, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900014, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900014, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900015, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900016, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900016, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900017, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900017, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900018, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900019, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900019, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900020, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900021, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900022, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900023, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900023, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900024, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900024, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900025, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900026, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900026, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900027, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900027, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900028, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900028, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900029, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900029, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900030, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900030, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900031, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900031, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900032, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900033, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900034, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900035, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900036, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900036, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900037, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900037, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900038, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900039, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900039, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900040, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900040, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900041, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900042, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900042, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900043, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900044, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900045, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900046, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900046, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900047, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900048, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900049, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900049, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900050, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900050, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900051, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900051, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900052, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900053, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900054, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900055, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900056, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900056, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900057, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900058, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900059, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900059, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900060, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900061, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900062, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900063, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900063, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900064, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900065, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900066, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900067, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900067, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900068, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900068, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900069, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900069, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900070, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900070, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900071, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900071, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900072, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900073, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900074, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900075, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900075, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900076, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900076, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900077, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900078, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900078, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900079, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900079, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900080, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900080, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900081, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900082, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900082, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900083, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900083, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900084, 3);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900084, 4);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (3900085, 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
