SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yhc_cf_query_condition
-- ----------------------------
DROP TABLE IF EXISTS `yhc_cf_query_condition`;
CREATE TABLE `yhc_cf_query_condition` (
  `QUERY_CONDITION_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `FUNCTION_CODE` varchar(20) DEFAULT NULL COMMENT '功能码，标识是那个功能设置的条件',
  `CONDITION_NAME` varchar(200) DEFAULT NULL COMMENT '条件显示名称',
  `CONDITION_CODE` varchar(50) DEFAULT NULL COMMENT '查询条件名称对应别名，如姓名-name；性别-sexCode',
  `DATABASE_FIELD` varchar(50) DEFAULT NULL COMMENT '数据库对应字段',
  `CONDITION_TYPE` varchar(20) DEFAULT NULL COMMENT '条件类型；input-输入型；date-日期型；select-下拉框选择型；selectModel-字典弹出框型；selectUnit-选择单位；selectOrg-选择内设机构；',
  `DIC_TYPE` varchar(20) DEFAULT NULL COMMENT '字典选项对应的字典类型码；如性别-YHRS0001',
  `ORDER_NO` bigint(20) DEFAULT NULL COMMENT '排序号',
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `CREATED_BY_NAME` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '修改人ID',
  `UPDATED_BY_NAME` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`QUERY_CONDITION_OID`)
) ENGINE=InnoDB AUTO_INCREMENT=2064 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhc_cf_query_condition_sym
-- ----------------------------
DROP TABLE IF EXISTS `yhc_cf_query_condition_sym`;
CREATE TABLE `yhc_cf_query_condition_sym` (
  `QUERY_CONDITION_OID` bigint(20) NOT NULL COMMENT '查询条件OID',
  `SYMBOL_OID` bigint(20) NOT NULL COMMENT '查询条件符号OID',
  KEY `YHC_CF_QUERY_CONDITION_FK` (`QUERY_CONDITION_OID`),
  KEY `YHC_CF_QUERY__SYMBOL_FK` (`SYMBOL_OID`),
  CONSTRAINT `YHC_CF_QUERY_CONDITION_FK` FOREIGN KEY (`QUERY_CONDITION_OID`) REFERENCES `yhc_cf_query_condition` (`QUERY_CONDITION_OID`),
  CONSTRAINT `YHC_CF_QUERY__SYMBOL_FK` FOREIGN KEY (`SYMBOL_OID`) REFERENCES `yhc_cf_query_symbol` (`SYMBOL_OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhc_cf_query_symbol
-- ----------------------------
DROP TABLE IF EXISTS `yhc_cf_query_symbol`;
CREATE TABLE `yhc_cf_query_symbol` (
  `SYMBOL_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SYMBOL_NAME` varchar(100) DEFAULT NULL COMMENT '符号名称',
  `SYMBOL_VALUE` varchar(20) DEFAULT NULL COMMENT '符号值',
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `CREATED_BY_NAME` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '修改人ID',
  `UPDATED_BY_NAME` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`SYMBOL_OID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhc_cf_show_field_order
-- ----------------------------
DROP TABLE IF EXISTS `yhc_cf_show_field_order`;
CREATE TABLE `yhc_cf_show_field_order` (
  `FIELD_ORDER_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USER_ID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `RESULT_OID` bigint(20) NOT NULL COMMENT '配置表ID',
  `FIELD_ORDER` bigint(20) DEFAULT NULL COMMENT '是否显示；1-显示；0-不显示',
  `IS_SHOW` varchar(20) DEFAULT NULL,
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `CREATED_BY_NAME` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '修改人ID',
  `UPDATED_BY_NAME` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`FIELD_ORDER_OID`),
  KEY `YHC_CF_SHOW_FIELD_ORDER_FK` (`RESULT_OID`),
  CONSTRAINT `YHC_CF_SHOW_FIELD_ORDER_FK` FOREIGN KEY (`RESULT_OID`) REFERENCES `yhc_cf_show_result_set` (`RESULT_OID`)
) ENGINE=InnoDB AUTO_INCREMENT=2212 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhc_cf_show_result_order
-- ----------------------------
DROP TABLE IF EXISTS `yhc_cf_show_result_order`;
CREATE TABLE `yhc_cf_show_result_order` (
  `RESULT_ORDER_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USER_ID` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `RESULT_OID` bigint(20) NOT NULL COMMENT '配置表ID',
  `RESULT_ORDER` varchar(20) DEFAULT NULL COMMENT '结果集排序，按字段的升序或降序排列结果',
  `FIELD_ORDER` bigint(20) DEFAULT NULL COMMENT '字段排序',
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `CREATED_BY_NAME` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '修改人ID',
  `UPDATED_BY_NAME` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`RESULT_ORDER_OID`),
  KEY `YHC_CF_SHOW_RESULT_ORDER_FK` (`RESULT_OID`),
  CONSTRAINT `YHC_CF_SHOW_RESULT_ORDER_FK` FOREIGN KEY (`RESULT_OID`) REFERENCES `yhc_cf_show_result_set` (`RESULT_OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhc_cf_show_result_set
-- ----------------------------
DROP TABLE IF EXISTS `yhc_cf_show_result_set`;
CREATE TABLE `yhc_cf_show_result_set` (
  `RESULT_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `FUNCTION_CODE` varchar(20) DEFAULT NULL COMMENT '功能码',
  `LABEL_NAME` varchar(100) DEFAULT NULL COMMENT '显示名称',
  `LABEL_VALUE` varchar(50) DEFAULT NULL COMMENT '显示值',
  `LABEL_WIDTH` bigint(20) DEFAULT NULL COMMENT '显示宽度',
  `DATABASE_FIELD` varchar(50) DEFAULT NULL COMMENT '数据库表对应字段',
  `ORDRR_NO` bigint(20) DEFAULT NULL COMMENT '排序号',
  `IS_SHOW` VARCHAR(2) DEFAULT '1' COMMENT '是否默认显示 0：否	1：是',
  PRIMARY KEY (`RESULT_OID`)
) ENGINE=InnoDB AUTO_INCREMENT=2045 DEFAULT CHARSET=utf8;

