/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : conference

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2020-05-10 14:24:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` int(5) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `status` int(1) NOT NULL,
  `comm` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='admin';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '1234', '0', null);

-- ----------------------------
-- Table structure for `approve`
-- ----------------------------
DROP TABLE IF EXISTS `approve`;
CREATE TABLE `approve` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `tel` varchar(11) DEFAULT '',
  `cname` varchar(20) NOT NULL,
  `aname` varchar(20) DEFAULT NULL,
  `time` date NOT NULL,
  `money` double DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `dealtime` datetime NOT NULL,
  `comm` varchar(50) DEFAULT NULL,
  `usedid` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='approve';

-- ----------------------------
-- Records of approve
-- ----------------------------
INSERT INTO `approve` VALUES ('49', 'zhangsan', '13225963990', '7', 'admin', '2020-02-18', '160', '已完成', '2020-02-16 19:09:06', null, '18');
INSERT INTO `approve` VALUES ('50', 'zhangsan', '13225963990', '12', 'admin', '2020-02-19', '123', '已完成', '2020-02-16 19:12:09', null, '19');
INSERT INTO `approve` VALUES ('51', 'zhangsan', '13225963990', '6', 'admin', '2020-02-19', '213', '待使用', '2020-02-16 19:12:49', null, '20');
INSERT INTO `approve` VALUES ('52', 'zhangsan', '13225963990', '6', 'admin', '2020-02-20', '213', '待使用', '2020-02-16 19:13:44', null, '21');

-- ----------------------------
-- Table structure for `confer_infor`
-- ----------------------------
DROP TABLE IF EXISTS `confer_infor`;
CREATE TABLE `confer_infor` (
  `cid` int(10) NOT NULL AUTO_INCREMENT,
  `conferName` varchar(30) NOT NULL,
  `size` int(4) NOT NULL,
  `price` double NOT NULL,
  `peoCount` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `people` varchar(20) NOT NULL,
  `tel` varchar(11) NOT NULL,
  `comm` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='confer_infor';

-- ----------------------------
-- Records of confer_infor
-- ----------------------------
INSERT INTO `confer_infor` VALUES ('6', '6', '123', '213', '51-100', '福州小别院', '1231', '13132311534', '又大又漂亮');
INSERT INTO `confer_infor` VALUES ('7', '12', '123', '123', '0-10', '123', '123', '123', '');
INSERT INTO `confer_infor` VALUES ('8', '8', '500', '3020', '21-50', '123456', '44', '12345678910', '111');
INSERT INTO `confer_infor` VALUES ('9', '会议室1', '80', '499', '11-20', '翔安区小别院', '张飒', '15659988137', '很美丽的小别院，别有风情');
INSERT INTO `confer_infor` VALUES ('10', '会议室2', '130', '200', '11-20', '杏林西路万豪酒店', '吴', '12345678977', '便宜又方便');
INSERT INTO `confer_infor` VALUES ('11', '会议2', '12', '22', '0-10', 'SASCAFAlse', 'AA', '12345678910', '');
INSERT INTO `confer_infor` VALUES ('12', 'hui1', '500', '300', '11-20', 'shanghai', 'xioz', '15659988137', '');
INSERT INTO `confer_infor` VALUES ('13', 'hui2', '5000', '500', '11-20', '12312', '23123', '21312', 'aaa');
INSERT INTO `confer_infor` VALUES ('14', '会议室5', '50', '300', '0-10', '福州小仓山', '符', '13576545348', '可以先来看看');
INSERT INTO `confer_infor` VALUES ('15', 'confer3', '56', '99.99', '0-10', '123456', '令', '15823499431', '123');
INSERT INTO `confer_infor` VALUES ('24', '会议室3', '30', '200', '11-20', '厦门市海沧区', '李先生', '15659988137', '欢迎');
INSERT INTO `confer_infor` VALUES ('25', '会议室4', '300', '1000', '21-50', '集美区地铁站附近', '王先生', '15659988137', '12345');
INSERT INTO `confer_infor` VALUES ('26', 'confer1', '55', '200', '51-100', '集美区北大街', '李先生', '13123174842', '222');
INSERT INTO `confer_infor` VALUES ('27', 'confer10', '50', '199', '11-20', '集美区凤弈花园13B2020号', '王先生', '13865675861', '暂无');

-- ----------------------------
-- Table structure for `deal`
-- ----------------------------
DROP TABLE IF EXISTS `deal`;
CREATE TABLE `deal` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `cid` int(10) NOT NULL,
  `time` date NOT NULL,
  `money` double NOT NULL,
  `comm` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='deal';

-- ----------------------------
-- Records of deal
-- ----------------------------

-- ----------------------------
-- Table structure for `discuss`
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) DEFAULT NULL,
  `aname` varchar(20) DEFAULT NULL,
  `usay` varchar(100) DEFAULT NULL,
  `asay` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='discuss';

-- ----------------------------
-- Records of discuss
-- ----------------------------
INSERT INTO `discuss` VALUES ('19', 'zhangsan', 'admin', '界面不怎么样', '谢谢你的宝贵意见，我们会尽快维护的。');
INSERT INTO `discuss` VALUES ('20', 'zs', 'admin', '11223344', '挺好');
INSERT INTO `discuss` VALUES ('21', 'zs', null, '有待改良', null);

-- ----------------------------
-- Table structure for `fav`
-- ----------------------------
DROP TABLE IF EXISTS `fav`;
CREATE TABLE `fav` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `fname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='fav';

-- ----------------------------
-- Records of fav
-- ----------------------------
INSERT INTO `fav` VALUES ('44', '1', '打球');
INSERT INTO `fav` VALUES ('45', '1', '跑步');
INSERT INTO `fav` VALUES ('46', '1', '爬山');
INSERT INTO `fav` VALUES ('50', '15', '打球');

-- ----------------------------
-- Table structure for `favorite`
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='favorite';

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES ('1', '打球');
INSERT INTO `favorite` VALUES ('2', '跑步');
INSERT INTO `favorite` VALUES ('3', '爬山');
INSERT INTO `favorite` VALUES ('4', '看电影');

-- ----------------------------
-- Table structure for `img`
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img` (
  `imgid` int(20) NOT NULL AUTO_INCREMENT,
  `cid` int(10) NOT NULL,
  `path` varchar(100) NOT NULL,
  PRIMARY KEY (`imgid`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COMMENT='img';

-- ----------------------------
-- Records of img
-- ----------------------------
INSERT INTO `img` VALUES ('79', '6', '2edf5ed557084a60a463217c24ed727c.jpg');
INSERT INTO `img` VALUES ('80', '6', '8d70a49f001b44bea07e5cb5fc7e74f7.jpg');
INSERT INTO `img` VALUES ('81', '7', '6e813127bd4b46b584015a5390d8a21c.jpg');
INSERT INTO `img` VALUES ('82', '7', '53132dc8f75640579db382f56415fdfe.jpg');
INSERT INTO `img` VALUES ('83', '8', '2192a743c01f439e85e775f2481dcd64.jpg');
INSERT INTO `img` VALUES ('84', '8', '6ab37b70a00d4d52be0c7e6317cec6fb.jpg');
INSERT INTO `img` VALUES ('85', '9', 'ddf76531e1b047ddb58677289c18f2f5.jpg');
INSERT INTO `img` VALUES ('86', '9', '96e811ab00d14eb7a538eb59244e4533.jpg');
INSERT INTO `img` VALUES ('87', '10', '054ef5e7b98547dab4ee29b6f7a7449b.jpg');
INSERT INTO `img` VALUES ('88', '10', '75c1f667ab614e4391929babb3877a33.jpg');
INSERT INTO `img` VALUES ('89', '11', '223199b9ebcb4a248117eb705631472c.jpg');
INSERT INTO `img` VALUES ('90', '11', 'eb3d6acbae504e2c988dd3ecd2280c69.jpg');
INSERT INTO `img` VALUES ('91', '12', '9dd67bebd141424a87870ef4c16bf296.jpg');
INSERT INTO `img` VALUES ('92', '12', 'b85dc03b817f48aa8caf73e11c14e698.jpg');
INSERT INTO `img` VALUES ('93', '13', '6d6022ea29724f87b8c6b8af42730824.jpg');
INSERT INTO `img` VALUES ('94', '13', '297b28024fe841cca35cafde2c72cd4a.jpg');
INSERT INTO `img` VALUES ('95', '14', 'd318e770f3b14ccd81293b0629b37725.jpg');
INSERT INTO `img` VALUES ('96', '14', '03ce362fb98c4ea3aa0cbaac7ecf0e90.jpg');
INSERT INTO `img` VALUES ('97', '24', '8544b5c7581d4fabb00f12e119fd5481.jpg');
INSERT INTO `img` VALUES ('98', '24', '3af8377dd53e4331a0a8ff50721c7742.jpg');
INSERT INTO `img` VALUES ('99', '15', '6c4369815965402d8c5a9ea8a4371b09.jpg');
INSERT INTO `img` VALUES ('100', '25', '532bcae4198b49ce89dc9d67e0dab4e1.jpg');
INSERT INTO `img` VALUES ('101', '25', 'dcc3932553864905af68d9e73c922bfd.jpg');
INSERT INTO `img` VALUES ('102', '26', '12ab465140284e7f8ad1eac298726e25.jpg');
INSERT INTO `img` VALUES ('103', '26', 'a878144706f6409da00b277b56383d18.jpg');
INSERT INTO `img` VALUES ('104', '27', '2c06a96fbaae4c25b96653f2e3b28ae5.jpg');
INSERT INTO `img` VALUES ('105', '27', 'e0d9a4d085f948089728af1fe2818a60.jpg');
INSERT INTO `img` VALUES ('106', '27', '06d61b47c2ff4c0f87f693685c6651c5.jpg');

-- ----------------------------
-- Table structure for `peonum`
-- ----------------------------
DROP TABLE IF EXISTS `peonum`;
CREATE TABLE `peonum` (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `peoCount` varchar(20) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='peoNum';

-- ----------------------------
-- Records of peonum
-- ----------------------------
INSERT INTO `peonum` VALUES ('1', '0-10');
INSERT INTO `peonum` VALUES ('2', '11-20');
INSERT INTO `peonum` VALUES ('3', '21-50');
INSERT INTO `peonum` VALUES ('4', '51-100');
INSERT INTO `peonum` VALUES ('5', '101-150');
INSERT INTO `peonum` VALUES ('6', '151-200');

-- ----------------------------
-- Table structure for `record`
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `cid` int(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `time` date NOT NULL,
  `status` varchar(10) NOT NULL,
  `moeny` double NOT NULL,
  `comm` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='record';

-- ----------------------------
-- Records of record
-- ----------------------------

-- ----------------------------
-- Table structure for `used`
-- ----------------------------
DROP TABLE IF EXISTS `used`;
CREATE TABLE `used` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `cid` int(30) DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of used
-- ----------------------------
INSERT INTO `used` VALUES ('13', '8', '2020-02-19');
INSERT INTO `used` VALUES ('17', '12', '2020-02-19');
INSERT INTO `used` VALUES ('18', '5', '2020-02-18');
INSERT INTO `used` VALUES ('19', '7', '2020-02-19');
INSERT INTO `used` VALUES ('20', '6', '2020-02-19');
INSERT INTO `used` VALUES ('21', '6', '2020-02-20');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `sex` int(1) DEFAULT NULL,
  `tel` varchar(11) DEFAULT '',
  `money` double NOT NULL DEFAULT '0',
  `status` int(1) NOT NULL,
  `birth` varchar(20) DEFAULT '',
  `address` varchar(50) DEFAULT '',
  `comm` varchar(50) DEFAULT '',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='user';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zs', '123', '1', '15659988137', '498', '0', '2020-01-11', '星星大厦E座1204', '123456');
INSERT INTO `user` VALUES ('15', 'zhangsan', '123', '0', '13225963990', '1191', '0', '2020-02-14', '星星大厦E座1204', '  暂无');
INSERT INTO `user` VALUES ('23', 'listenerST', 'adminA.?1', '0', '13225963991', '0', '0', null, null, ' ');
INSERT INTO `user` VALUES ('24', 'listenerST1', 'adminA.?1', '0', '13225963992', '0', '0', null, null, ' ');
