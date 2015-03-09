/*
Navicat MySQL Data Transfer

Source Server         : server
Source Server Version : 50534
Source Host           : 10.131.252.156:3306
Source Database       : crowdservice

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2015-03-09 16:04:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for agentinfo
-- ----------------------------
DROP TABLE IF EXISTS `agentinfo`;
CREATE TABLE `agentinfo` (
  `guid` varchar(255) NOT NULL,
  `isOnline` bit(1) NOT NULL,
  `longitude` decimal(64,8) DEFAULT '0.00000000',
  `lastActive` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `capacity` varchar(255) DEFAULT NULL,
  `reputation` decimal(14,2) NOT NULL DEFAULT '0.90',
  `latitude` decimal(64,8) DEFAULT '0.00000000',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for microtask
-- ----------------------------
DROP TABLE IF EXISTS `microtask`;
CREATE TABLE `microtask` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `template` text NOT NULL,
  `consumer` varchar(255) NOT NULL,
  `cost` int(11) DEFAULT '0',
  `state` bit(3) NOT NULL DEFAULT b'0',
  `deadline` int(32) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `compositeService` varchar(255) NOT NULL,
  `crowdService` varchar(255) NOT NULL,
  `resultNum` int(11) NOT NULL DEFAULT '0',
  `longitude` decimal(64,8) NOT NULL,
  `latitude` decimal(64,8) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4` (`consumer`) USING BTREE,
  CONSTRAINT `microtask_ibfk_1` FOREIGN KEY (`consumer`) REFERENCES `agentinfo` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=291 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for workerresponse
-- ----------------------------
DROP TABLE IF EXISTS `workerresponse`;
CREATE TABLE `workerresponse` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `worker` varchar(255) NOT NULL,
  `responseString` text,
  `isAccepted` bit(1) NOT NULL DEFAULT b'0',
  `answerTime` timestamp NULL DEFAULT NULL,
  `taskid` bigint(64) NOT NULL,
  `offer` int(32) NOT NULL DEFAULT '0',
  `isSelected` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK1` (`taskid`) USING BTREE,
  KEY `FK2` (`worker`) USING BTREE,
  CONSTRAINT `workerresponse_ibfk_1` FOREIGN KEY (`taskid`) REFERENCES `microtask` (`id`),
  CONSTRAINT `workerresponse_ibfk_2` FOREIGN KEY (`worker`) REFERENCES `agentinfo` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8;
