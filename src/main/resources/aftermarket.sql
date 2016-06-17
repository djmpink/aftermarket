/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : aftermarket

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-06-17 18:16:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for coupons
-- ----------------------------
DROP TABLE IF EXISTS `coupons`;
CREATE TABLE `coupons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL COMMENT '用户id',
  `activityCode` varchar(50) DEFAULT NULL COMMENT '激活码（Q码）',
  `phone` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '现金券状态 1发放 4 已使用 5已废除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `activityTime` datetime DEFAULT NULL COMMENT '激活时间',
  `usedTime` datetime DEFAULT NULL COMMENT '使用时间',
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `verifyTime` datetime DEFAULT NULL,
  `closeTime` datetime DEFAULT NULL,
  `channel` int(11) DEFAULT NULL COMMENT '渠道 1 淘宝 2京东 3大众 4美团 5官方微信',
  `name` varchar(255) DEFAULT NULL COMMENT '卡券名称',
  `type` int(11) DEFAULT NULL COMMENT '卡券类型',
  `description` varchar(255) DEFAULT NULL COMMENT '备注，描述',
  `userName` varchar(255) DEFAULT NULL,
  `purchase` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for coupons
-- ----------------------------
DROP TABLE IF EXISTS `coupons`;
CREATE TABLE `coupons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL COMMENT '用户id',
  `activityCode` varchar(50) DEFAULT NULL COMMENT '激活码（Q码）',
  `phone` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '现金券状态 1发放 4 已使用 5已废除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `activityTime` datetime DEFAULT NULL COMMENT '激活时间',
  `usedTime` datetime DEFAULT NULL COMMENT '使用时间',
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `verifyTime` datetime DEFAULT NULL,
  `closeTime` datetime DEFAULT NULL,
  `channel` int(11) DEFAULT NULL COMMENT '渠道 1 淘宝 2京东 3大众 4美团 5官方微信',
  `name` varchar(255) DEFAULT NULL COMMENT '卡券名称',
  `type` int(11) DEFAULT NULL COMMENT '卡券类型',
  `description` varchar(255) DEFAULT NULL COMMENT '备注，描述',
  `userName` varchar(255) DEFAULT NULL,
  `purchase` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
