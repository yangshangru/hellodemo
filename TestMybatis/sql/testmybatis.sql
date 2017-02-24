/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : testmybatis

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-02-24 14:53:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `rs_order`
-- ----------------------------
DROP TABLE IF EXISTS `rs_order`;
CREATE TABLE `rs_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(100) NOT NULL,
  `price` varchar(10) NOT NULL DEFAULT '0',
  `user_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rs_order
-- ----------------------------
INSERT INTO `rs_order` VALUES ('1', 'BN001', '100', '1');
INSERT INTO `rs_order` VALUES ('2', 'BN001', '100', '1');
INSERT INTO `rs_order` VALUES ('3', 'BN001', '100', '1');
INSERT INTO `rs_order` VALUES ('4', 'BN001', '100', '1');
INSERT INTO `rs_order` VALUES ('5', 'BN001', '100', '1');
INSERT INTO `rs_order` VALUES ('6', 'BN001', '100', '1');

-- ----------------------------
-- Table structure for `rs_user`
-- ----------------------------
DROP TABLE IF EXISTS `rs_user`;
CREATE TABLE `rs_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '',
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rs_user
-- ----------------------------
INSERT INTO `rs_user` VALUES ('1', '张三', '江苏省南京市');
INSERT INTO `rs_user` VALUES ('2', '张三', '江苏省南京市');
