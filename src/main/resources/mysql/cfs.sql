/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : cfs

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-13 19:09:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` decimal(19,0) NOT NULL,
  `log_type` varchar(40) NOT NULL,
  `log_content` varchar(200) NOT NULL,
  `user_id` decimal(19,0) NOT NULL,
  `user_date` datetime NOT NULL,
  `user_ip` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` decimal(19,0) NOT NULL,
  `msg_content` text,
  `receive_user_id` decimal(19,0) NOT NULL,
  `send_user_id` decimal(19,0) NOT NULL,
  `send_time` datetime NOT NULL,
  `send_user_ip` varchar(15) NOT NULL,
  `is_read` tinyint(1) unsigned NOT NULL COMMENT '0-unread, 1-read',
  `read_time` datetime DEFAULT NULL,
  `read_ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `is_read` (`is_read`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` decimal(19,0) NOT NULL,
  `resource_name` varchar(40) NOT NULL,
  `resource_description` text,
  `resource_tag` varchar(200) DEFAULT NULL,
  `resource_type` tinyint(4) unsigned NOT NULL COMMENT '0-图片；1-文档；2-音频；3-视频',
  `resource_download_count` int(11) unsigned NOT NULL DEFAULT '0',
  `resource_browse_count` int(11) unsigned NOT NULL DEFAULT '0',
  `resource_server_path` varchar(200) NOT NULL,
  `user_id` decimal(19,0) NOT NULL,
  `verify_type` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '0-正在审核，1-审核通过，2-审核不通过',
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource_browse
-- ----------------------------
DROP TABLE IF EXISTS `resource_browse`;
CREATE TABLE `resource_browse` (
  `resource_id` decimal(19,0) NOT NULL,
  `browse_time` datetime DEFAULT NULL,
  `user_id` decimal(19,0) NOT NULL,
  PRIMARY KEY (`resource_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource_comment
-- ----------------------------
DROP TABLE IF EXISTS `resource_comment`;
CREATE TABLE `resource_comment` (
  `id` decimal(19,0) NOT NULL,
  `comment_content` text,
  `user_id` decimal(19,0) NOT NULL,
  `resource_id` decimal(19,0) NOT NULL,
  `comment_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource_download
-- ----------------------------
DROP TABLE IF EXISTS `resource_download`;
CREATE TABLE `resource_download` (
  `resource_id` decimal(19,0) NOT NULL,
  `download_time` datetime NOT NULL,
  `user_id` decimal(19,0) NOT NULL,
  PRIMARY KEY (`resource_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource_score
-- ----------------------------
DROP TABLE IF EXISTS `resource_score`;
CREATE TABLE `resource_score` (
  `user_id` decimal(19,0) NOT NULL,
  `resource_id` decimal(19,0) NOT NULL,
  `rating` tinyint(1) NOT NULL COMMENT '5分制',
  `rating_time` datetime NOT NULL,
  `score_flag` tinyint(2) NOT NULL COMMENT '0代表评分，1代表下载评分，2代表浏览评分，3代表下载和浏览评分',
  PRIMARY KEY (`user_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` decimal(19,0) NOT NULL,
  `rolename` varchar(40) NOT NULL,
  `gmt_creat` datetime NOT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` decimal(19,0) NOT NULL,
  `role_id` decimal(19,0) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` char(32) NOT NULL,
  `realname` varchar(40) DEFAULT NULL,
  `user_idcard` char(18) DEFAULT NULL,
  `user_mobile` varchar(11) DEFAULT NULL,
  `user_email` varchar(32) DEFAULT NULL,
  `random_code` char(32) DEFAULT NULL,
  `gender` tinyint(1) unsigned DEFAULT NULL COMMENT '0-male; 1-female',
  `tag` varchar(40) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
