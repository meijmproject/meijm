SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yha_cfg_properties
-- ----------------------------
DROP TABLE IF EXISTS `yha_cfg_properties`;
CREATE TABLE `yha_cfg_properties` (
  `CFG_PROPERTIES_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CFG_PROPERTIES_TYPE` varchar(2) NOT NULL COMMENT '所属模块代码\r\n             1：业务办理\r\n             2：信息管理（校核）（单位、人员\r\n             3：工资管理\r\n             4：岗位管理\r\n             5：系统管理\r\n             6：报表\r\n             99：其他',
  `CFG_PROPERTIES_CODE` varchar(50) NOT NULL COMMENT '配置项的KEY值',
  `CFG_PROPERTIES_VALUE` varchar(1000) NOT NULL COMMENT '配置项的VALUE值',
  `CFG_PROPERTIES_DESC` varchar(1000) DEFAULT NULL COMMENT '配置项的描述',
  `IS_ACTIVE` varchar(2) NOT NULL COMMENT '是否激活',
  `CREATED_BY_CODE` varchar(50) DEFAULT NULL COMMENT '创建人CODE',
  `CREATED_BY_NAME` varchar(30) DEFAULT NULL COMMENT '创建人姓名',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`CFG_PROPERTIES_OID`),
  KEY `yha_CFG_PROPERTIES_UQ` (`CFG_PROPERTIES_TYPE`,`CFG_PROPERTIES_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='系统配置表';
