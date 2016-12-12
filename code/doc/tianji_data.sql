/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : tianji_data

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-11 22:40:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for si_fund_company_info
-- ----------------------------
DROP TABLE IF EXISTS `si_fund_company_info`;
CREATE TABLE `si_fund_company_info` (
  `id` varchar(10) NOT NULL,
  `company_id` varchar(10) NOT NULL COMMENT '公司id',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司中文全称',
  `company_short_name` varchar(80) DEFAULT NULL COMMENT '公司中文简称',
  `register_number` varchar(20) DEFAULT NULL COMMENT '备案号',
  `registered_capital` decimal(22,6) DEFAULT NULL COMMENT '公司注册资本，<量纲：万元>',
  `city` varchar(100) DEFAULT NULL COMMENT '所在地区',
  `establish_date` date DEFAULT NULL COMMENT '公司成立日期',
  `key_person` varchar(10) DEFAULT NULL COMMENT '核心人物,存ID',
  `key_person_name` varchar(50) DEFAULT NULL,
  `company_asset_size` tinyint(4) DEFAULT NULL COMMENT '管理规模,公司规模:1-1~10亿,2-10~20亿,3-20~50亿,4-50以上',
  `ret_incep_average` decimal(22,6) DEFAULT NULL COMMENT '累计平均收益',
  `ret_ytd_average` decimal(22,6) DEFAULT NULL COMMENT '今年平均收益',
  `pro_product_count` bigint(20) DEFAULT NULL COMMENT '盈利产品数',
  `rep_product` varchar(10) DEFAULT NULL COMMENT '代表产品,存ID',
  `rep_product_name` varchar(255) DEFAULT NULL,
  `nav` decimal(22,6) DEFAULT NULL COMMENT '最新净值',
  `price_date` date DEFAULT NULL COMMENT '净值日期',
  `inception_date` date DEFAULT NULL COMMENT '成立日期',
  `ret_incep` decimal(22,6) DEFAULT NULL COMMENT '累计收益',
  `ret_ytd` decimal(22,6) DEFAULT NULL COMMENT '今年收益',
  `company_profile` text COMMENT '公司简介',
  `philosopy` text COMMENT '投资理念',
  `team_profile` text COMMENT '投研团队简介',
  `logo` varchar(255) DEFAULT NULL,
  `company_address` text,
  `seo_description` varchar(500) DEFAULT NULL,
  `seo_title` varchar(500) DEFAULT NULL,
  `seo_keywords` varchar(500) DEFAULT NULL,
  `key_person_profile` text,
  `key_person_logo` varchar(255) DEFAULT NULL,
  `up_down` tinyint(1) DEFAULT NULL,
  `rep_product_short_name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阳光私募公司表';

-- ----------------------------
-- Table structure for si_fund_his_nav
-- ----------------------------
DROP TABLE IF EXISTS `si_fund_his_nav`;
CREATE TABLE `si_fund_his_nav` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fund_id` varchar(10) DEFAULT NULL COMMENT '基金ID',
  `price_date` date DEFAULT NULL COMMENT '净值日期',
  `nav` decimal(22,6) DEFAULT NULL COMMENT '单位净值',
  `cumulative_nav` decimal(22,6) DEFAULT NULL COMMENT '累计净值',
  `rise_fall` decimal(22,6) DEFAULT NULL COMMENT '涨跌幅,',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fund_id_price_date_index` (`fund_id`,`price_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2505261 DEFAULT CHARSET=utf8 COMMENT='阳光私募详情历史净值表';

-- ----------------------------
-- Table structure for si_fund_his_performance
-- ----------------------------
DROP TABLE IF EXISTS `si_fund_his_performance`;
CREATE TABLE `si_fund_his_performance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fund_id` varchar(10) DEFAULT NULL COMMENT '基金ID',
  `ret_type` tinyint(2) DEFAULT NULL COMMENT '时间段,存今年以来ytd、近一月1m、近三月3m、近六月6m、近一年1y、近三年3y、近五年5y、成立以来incep',
  `fund_performance` decimal(22,6) DEFAULT NULL COMMENT '基金业绩',
  `three_csi` decimal(22,6) DEFAULT NULL COMMENT '沪深300',
  `average` decimal(10,0) DEFAULT NULL COMMENT '同类平均,还不清楚字段',
  `absrank` int(11) DEFAULT NULL COMMENT '同类排名',
  `four_ranking` int(11) DEFAULT NULL COMMENT '四分位排名',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `si_fund_id_ret_type_index` (`fund_id`,`ret_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1292474 DEFAULT CHARSET=utf8 COMMENT='阳光私募详情历史平均业绩表';

-- ----------------------------
-- Table structure for si_fund_rank
-- ----------------------------
DROP TABLE IF EXISTS `si_fund_rank`;
CREATE TABLE `si_fund_rank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `year` int(11) DEFAULT NULL,
  `fund_Id` varchar(20) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `avgret` decimal(22,2) DEFAULT NULL,
  `retained` decimal(22,2) DEFAULT NULL,
  `hs300` decimal(22,2) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1755 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for si_fund_riskadjret_stats
-- ----------------------------
DROP TABLE IF EXISTS `si_fund_riskadjret_stats`;
CREATE TABLE `si_fund_riskadjret_stats` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fund_id` varchar(10) DEFAULT NULL COMMENT '基金id',
  `type` tinyint(2) DEFAULT NULL COMMENT '基金id',
  `1y` decimal(22,6) DEFAULT NULL COMMENT '近一年',
  `2y` decimal(22,6) DEFAULT NULL COMMENT '近2年',
  `3y` decimal(22,6) DEFAULT NULL COMMENT '近3年',
  `5y` decimal(22,6) DEFAULT NULL COMMENT '近5年',
  `all` decimal(22,6) DEFAULT NULL COMMENT '近成立以来年',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=615077 DEFAULT CHARSET=utf8 COMMENT='基金风险调整收益指标表';

-- ----------------------------
-- Table structure for si_his_market
-- ----------------------------
DROP TABLE IF EXISTS `si_his_market`;
CREATE TABLE `si_his_market` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT '编码',
  `price_date` date DEFAULT NULL COMMENT '日期',
  `dclose` decimal(20,6) DEFAULT NULL COMMENT '收盘价格',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=211268 DEFAULT CHARSET=utf8 COMMENT='指数历史表';

-- ----------------------------
-- Table structure for si_sunshine_private_fund
-- ----------------------------
DROP TABLE IF EXISTS `si_sunshine_private_fund`;
CREATE TABLE `si_sunshine_private_fund` (
  `id` varchar(10) NOT NULL COMMENT 'ID',
  `fund_id` varchar(20) DEFAULT NULL,
  `fund_name` varchar(255) DEFAULT NULL COMMENT '基金产品完整的称呼',
  `fund_short_name` varchar(80) DEFAULT NULL COMMENT '基金产品简单的称呼',
  `nav` decimal(22,6) DEFAULT NULL COMMENT '当前基金净资产与基金总份额的比值',
  `ret_1y` decimal(22,6) DEFAULT NULL COMMENT '基金最近1年的累计收益',
  `cumulative_nav_withdrawal` decimal(22,6) DEFAULT NULL COMMENT '现在的每一份的价格加上其他分红或者派出的收益的综合，一般是从发行之初开始算到现在的累计',
  `inception_date` date DEFAULT NULL COMMENT '基金的正式运作日期',
  `6m` decimal(22,6) DEFAULT NULL COMMENT '基金最近6个月的累计收益',
  `ret_ytd` decimal(22,6) DEFAULT NULL COMMENT '近一年收益',
  `run_time` varchar(20) DEFAULT NULL COMMENT '运行时间,根据成立日期计算',
  `ret_incep` decimal(22,6) DEFAULT NULL COMMENT '基金从发行之日起至今的累计收益，包括了分给私募的提成',
  `fund_manager_id` varchar(10) DEFAULT NULL COMMENT '基金经理id',
  `fund_manager_name` varchar(50) DEFAULT NULL COMMENT '基金经理名称',
  `head_portrait` varchar(100) DEFAULT NULL COMMENT '基金经理头像',
  `advisor_id` varchar(10) DEFAULT NULL COMMENT '由pvo_company_info获取ID，再获取 company_name到页面',
  `advisor_name` varchar(100) DEFAULT NULL COMMENT '基金公司名称',
  `strategy` int(11) DEFAULT NULL COMMENT '融智策略分类，1-股票策略，2-宏观策略，3-管理期货，4-事件驱动，5-相对价值策略，6-债券策略，7-组合基金，8-复合策略，-1-其它策略',
  `fund_status` int(11) DEFAULT NULL COMMENT '基金运行状态：1-募集中、2-开放运行、3-封闭运行、4-提前清算、5-到期清算、6-发行失败、7-更换管理人、-1-其他',
  `lockup_period` int(11) DEFAULT NULL COMMENT '封闭期，单位：月，-1：不确定，0：无封闭期',
  `min_investment_share` decimal(22,6) DEFAULT NULL COMMENT '最低认购额,<量纲：万> 券商资管产品量纲为<万元>',
  `subscription_fee` decimal(22,6) DEFAULT NULL COMMENT '最高认购费，量纲<%>',
  `management_fee` decimal(22,6) DEFAULT NULL COMMENT '管理费，量纲<%>',
  `redemption_fee` decimal(22,6) DEFAULT NULL COMMENT '最高赎回费，量纲<%>',
  `performance_fee` decimal(22,6) DEFAULT NULL COMMENT '最高绩效费，量纲<%>',
  `price_date` date DEFAULT NULL COMMENT '净值日期',
  `close` decimal(22,6) DEFAULT NULL COMMENT '沪深300收盘价涨跌幅',
  `dchange` decimal(22,6) DEFAULT NULL COMMENT '复权净值',
  `cumulative_nav` decimal(22,6) DEFAULT NULL COMMENT '复权净值',
  `isvalid` int(11) DEFAULT NULL COMMENT '是否有效图',
  `top_status` int(11) DEFAULT NULL COMMENT '是否置顶',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `open_day` varchar(255) DEFAULT NULL,
  `tag` varchar(500) DEFAULT NULL,
  `total_order` int(11) DEFAULT NULL,
  `this_year_order` int(11) DEFAULT NULL,
  `last_year_order` int(11) DEFAULT NULL,
  `seo_keywords` varchar(255) DEFAULT NULL,
  `seo_description` varchar(255) DEFAULT NULL,
  `seo_title` varchar(255) DEFAULT NULL,
  `advisor_profile` text,
  `custodian_id` varchar(64) DEFAULT NULL,
  `custodian_name` varchar(255) DEFAULT NULL,
  `fund_manager_logo` varchar(255) DEFAULT NULL,
  `fund_manager_profile` text,
  `fund_type` varchar(255) DEFAULT NULL,
  `up_down` tinyint(1) DEFAULT NULL,
  `sub_strategy` tinyint(2) DEFAULT NULL,
  `advisor_short_name` varchar(255) DEFAULT NULL,
  `custodian_short_name` varchar(255) DEFAULT NULL,
  `initial_unit_value` decimal(22,6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='为阳光私募基金，这个的大部分数据都是由排排网的表中读取过来';

-- ----------------------------
-- Table structure for sys_idtable
-- ----------------------------
DROP TABLE IF EXISTS `sys_idtable`;
CREATE TABLE `sys_idtable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `keyId` varchar(255) DEFAULT NULL,
  `keyvalue` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
