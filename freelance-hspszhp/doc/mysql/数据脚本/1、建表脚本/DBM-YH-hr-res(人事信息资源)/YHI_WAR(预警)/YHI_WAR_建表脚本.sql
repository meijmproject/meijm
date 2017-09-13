SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yhi_war_config_info
-- ----------------------------
DROP TABLE IF EXISTS `yhi_war_config_info`;
CREATE TABLE `yhi_war_config_info` (
  `WAR_CONFIG_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'WAR_CONFIG_OID',
  `ITEM_CODE` varchar(20) DEFAULT NULL COMMENT '预警事项代码',
  `ITEM_NAME` varchar(100) DEFAULT NULL COMMENT '预警事项名称',
  `WARNING_DAYS` bigint(20) DEFAULT NULL COMMENT '预警天数',
  `WARNING_BEAN` varchar(200) DEFAULT NULL COMMENT '预警执行BEAN',
  `WARNING_URL` varchar(200) DEFAULT NULL COMMENT '预警操作路径',
  `WARNING_FLAG` varchar(10) DEFAULT NULL COMMENT 'Y:执行预警；N:不预警',
  `CREATED_BY` varchar(20) DEFAULT NULL COMMENT '创建人',
  `CREATED_TS` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(20) DEFAULT NULL COMMENT '修改人',
  `UPDATED_TS` datetime DEFAULT NULL COMMENT '修改时间',
  `SYSTEM_ID` varchar(20) DEFAULT NULL COMMENT '所属系统',
  PRIMARY KEY (`WAR_CONFIG_OID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='SEQ_YHI_WAR_CONFIG_INFO';
