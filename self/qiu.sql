/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : qiu

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 29/02/2024 14:44:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '测试商品' COMMENT '商品名',
  `goods_price` decimal(10, 0) NULL DEFAULT NULL COMMENT '商品价格',
  `goods_shopname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '测试店铺' COMMENT '商品店铺',
  `goods_stock` int NULL DEFAULT 0 COMMENT '商品库存',
  `goods_sale` int NULL DEFAULT 0 COMMENT '商品销量',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '吹风机', 20, '测试店铺1', 99, 50);
INSERT INTO `goods` VALUES (2, '扩音器', 40, '测试店铺2', 14, 12);
INSERT INTO `goods` VALUES (3, '冰箱', 20, '测试店铺1', 99, 42);
INSERT INTO `goods` VALUES (4, '窗帘', 40, '测试店铺2', 14, 12);
INSERT INTO `goods` VALUES (5, '测试商品3', 60, '测试店铺3', 124, 55);
INSERT INTO `goods` VALUES (6, '测试商品3', 60, '测试店铺3', 124, 55);
INSERT INTO `goods` VALUES (7, '测试商品', 20, '测试店铺1', 99, 42);
INSERT INTO `goods` VALUES (8, '测试商品', 40, '测试店铺2', 14, 12);
INSERT INTO `goods` VALUES (9, '测试商品3', 60, '测试店铺3', 124, 55);
INSERT INTO `goods` VALUES (10, '测试商品', 20, '测试店铺1', 99, 42);
INSERT INTO `goods` VALUES (11, '测试商品', 40, '测试店铺2', 14, 12);
INSERT INTO `goods` VALUES (12, '测试商品3', 60, '测试店铺3', 124, 55);
INSERT INTO `goods` VALUES (13, '测试商品', 20, '测试店铺1', 99, 42);
INSERT INTO `goods` VALUES (14, '测试商品', 40, '测试店铺2', 14, 12);
INSERT INTO `goods` VALUES (15, '测试商品3', 60, '测试店铺3', 124, 55);
INSERT INTO `goods` VALUES (16, '测试商品', 20, '测试店铺1', 99, 42);
INSERT INTO `goods` VALUES (17, '测试商品', 40, '测试店铺2', 14, 12);
INSERT INTO `goods` VALUES (18, '测试商品3', 60, '测试店铺3', 124, 55);
INSERT INTO `goods` VALUES (19, '测试商品', 20, '测试店铺1', 99, 42);
INSERT INTO `goods` VALUES (20, '测试商品', 40, '测试店铺2', 14, 12);
INSERT INTO `goods` VALUES (21, '测试商品9', 60, '测试店铺3', 124, 55);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `cate_name` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名',
  `cate_id` int NULL DEFAULT NULL COMMENT '菜单id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `parent_cate` int NULL DEFAULT 0
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('首页', 1, 2, 0);
INSERT INTO `menu` VALUES ('用户管理', 2, 1, 0);
INSERT INTO `menu` VALUES ('用户列表', 3, 1, 2);
INSERT INTO `menu` VALUES ('用户编辑', 4, 1, 2);
INSERT INTO `menu` VALUES ('商品管理', 5, 2, 0);
INSERT INTO `menu` VALUES ('商品列表', 6, 2, 5);
INSERT INTO `menu` VALUES ('商品编辑', 7, 2, 5);
INSERT INTO `menu` VALUES ('库存预警', 8, 2, 0);
INSERT INTO `menu` VALUES ('返回登录', 9, 2, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'user' COMMENT '账号',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '无名用户' COMMENT '姓名',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '123456' COMMENT '密码',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `sex` int NULL DEFAULT 0 COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `role_id` int NULL DEFAULT NULL COMMENT '角色 0超级管理员 1管理员 2普通账号',
  `isValid` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'y' COMMENT '是否有效 y有效 其他无效',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'token',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '超级管理员', '123456', 21, 1, '13457742548', 0, 'y', 'eyJhbGciOiJIUzI1NiJ9.eyJuaWNrbmFtZSI6Iui2hee6p-euoeeQhuWRmCIsImlkIjoxLCJ1c2VybmFtZSI6ImFkbWluIiwiZXhwIjoxNzA5MTQ3NTAyfQ.va9ejd2jKYPG6a5eROZvlMbC6MSfLxoZ9VpPKqSlkeU', NULL);
INSERT INTO `user` VALUES (2, 'admin2', '另一个超级管理员', '123456', 18, 0, '13488888888', 0, 'y', NULL, NULL);
INSERT INTO `user` VALUES (3, 'user5', '修改', '123456', 18, 0, '13477777777', 2, 'n', 'eyJhbGciOiJIUzI1NiJ9.eyJuaWNrbmFtZSI6IuS_ruaUuSIsImlkIjozLCJ1c2VybmFtZSI6InVzZXI1IiwiZXhwIjoxNzA5MTQzNTg5fQ.siaEPXKFJ4bVSam9lR0C-7hNhI8ayJGq1VwHgMStnL8', NULL);
INSERT INTO `user` VALUES (4, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (5, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (6, 'user', '用户6', '123456', 19, 0, '888888888', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (7, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (8, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (9, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (10, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (11, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (12, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (13, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (14, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (15, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (16, 'user3', '用户', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (17, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (18, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (19, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (20, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (21, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (22, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (23, 'user4', '用户23', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (24, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (25, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (26, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (27, 'user3', '用户', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (30, 'test', '测试添加', '123456', 18, 0, '41844111874', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (31, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (32, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (33, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (34, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (35, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (36, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (38, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (39, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (42, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (43, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (44, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (45, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (46, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (47, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (48, 'user', '无名用户', '123456', NULL, 0, '', 1, 'n', NULL, NULL);
INSERT INTO `user` VALUES (49, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (50, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);
INSERT INTO `user` VALUES (51, 'user4', '用户1', '123456', 18, 0, '13477777777', 2, 'y', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
