SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yhd_it_function
-- ----------------------------
DROP TABLE IF EXISTS `yhd_it_function`;
CREATE TABLE `yhd_it_function` (
  `FUNCTION_CODE` varchar(50) NOT NULL COMMENT '功能代码',
  `LIBRARY_GROUP_OID` bigint(20) NOT NULL,
  `FUNCTION_NAME` varchar(200) DEFAULT NULL COMMENT '功能名称',
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL,
  `CREATED_BY_NAME` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL,
  `UPDATED_BY_NAME` varchar(100) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`FUNCTION_CODE`),
  KEY `yhd_IT_FUNCTION_G` (`LIBRARY_GROUP_OID`),
  CONSTRAINT `yhd_IT_FUNCTION_G` FOREIGN KEY (`LIBRARY_GROUP_OID`) REFERENCES `yhd_it_library_group` (`LIBRARY_GROUP_OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhd_it_library
-- ----------------------------
DROP TABLE IF EXISTS `yhd_it_library`;
CREATE TABLE `yhd_it_library` (
  `LIBRARY_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `INFO_NAME` varchar(100) DEFAULT NULL COMMENT '查看或修改信息名称',
  `INFO_URL` varchar(100) DEFAULT NULL COMMENT '查看或修改信息URL',
  `IS_ACTIVE` varchar(2) DEFAULT NULL,
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL,
  `CREATED_BY_NAME` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL,
  `UPDATED_BY_NAME` varchar(100) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`LIBRARY_OID`)
) ENGINE=InnoDB AUTO_INCREMENT=1060 DEFAULT CHARSET=utf8 COMMENT='查看、统计、修改信息资源库';

-- ----------------------------
-- Table structure for yhd_it_library_group
-- ----------------------------
DROP TABLE IF EXISTS `yhd_it_library_group`;
CREATE TABLE `yhd_it_library_group` (
  `LIBRARY_GROUP_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PARENT_GROUP_OID` bigint(20) DEFAULT NULL COMMENT '父分组ID',
  `LIBRARY_GROUP_NAME` varchar(100) DEFAULT NULL COMMENT '资源组名称',
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL,
  `CREATED_BY_NAME` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL,
  `UPDATED_BY_NAME` varchar(100) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`LIBRARY_GROUP_OID`)
) ENGINE=InnoDB AUTO_INCREMENT=1031 DEFAULT CHARSET=utf8 COMMENT='查看、统计、修改信息资源组';

-- ----------------------------
-- Table structure for yhd_it_library_group_detail
-- ----------------------------
DROP TABLE IF EXISTS `yhd_it_library_group_detail`;
CREATE TABLE `yhd_it_library_group_detail` (
  `LIBRARY_GROUP_OID` bigint(20) NOT NULL,
  `LIBRARY_OID` bigint(20) NOT NULL,
  `LIBRARY_NAME` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `ORDER_SEQ` bigint(20) DEFAULT NULL,
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL,
  `CREATED_BY_NAME` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL,
  `UPDATED_BY_NAME` varchar(100) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `IS_DEFAULT_SHOW` VARCHAR(2) DEFAULT '0' COMMENT '是否默认显示在主页	0：否	1：是',
  KEY `yhd_IT_LIBRARY_GROUP_DETAIL_G` (`LIBRARY_GROUP_OID`),
  KEY `yhd_IT_LIBRARY_GROUP_DETAIL_L` (`LIBRARY_OID`),
  CONSTRAINT `yhd_IT_LIBRARY_GROUP_DETAIL_G` FOREIGN KEY (`LIBRARY_GROUP_OID`) REFERENCES `yhd_it_library_group` (`LIBRARY_GROUP_OID`),
  CONSTRAINT `yhd_IT_LIBRARY_GROUP_DETAIL_L` FOREIGN KEY (`LIBRARY_OID`) REFERENCES `yhd_it_library` (`LIBRARY_OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
