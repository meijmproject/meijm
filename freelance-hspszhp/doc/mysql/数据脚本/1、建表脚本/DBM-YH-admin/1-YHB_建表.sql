SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yhb_function_res
-- ----------------------------
DROP TABLE IF EXISTS `yhb_function_res`;
CREATE TABLE `yhb_function_res` (
  `RES_ID` bigint(20) NOT NULL,
  `FUNC_ID` bigint(20) NOT NULL,
  KEY `YHB_FUNCTION_RES_F` (`FUNC_ID`),
  KEY `YHB_FUNCTION_RES_R` (`RES_ID`),
  CONSTRAINT `YHB_FUNCTION_RES_F` FOREIGN KEY (`FUNC_ID`) REFERENCES `yhb_functions` (`FUNC_ID`),
  CONSTRAINT `YHB_FUNCTION_RES_R` FOREIGN KEY (`RES_ID`) REFERENCES `yhb_resources` (`RES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhb_functions
-- ----------------------------
DROP TABLE IF EXISTS `yhb_functions`;
CREATE TABLE `yhb_functions` (
  `FUNC_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '功能主键',
  `FUNC_NAME` varchar(80) DEFAULT NULL COMMENT '功能名称',
  `FUNC_DESC` varchar(200) DEFAULT NULL COMMENT '功能描述',
  `SYSTEM_CODE` varchar(10) NOT NULL COMMENT '所属系统代码',
  `ENABLED_FLG` varchar(2) DEFAULT NULL COMMENT '是否可用',
  `FATHER_ID` bigint(20) DEFAULT NULL COMMENT '上级功能ID',
  `ORDER_SEQ` bigint(20) DEFAULT NULL COMMENT '功能显示序号',
  PRIMARY KEY (`FUNC_ID`),
  KEY `YHB_FUNCTIONS_SYS` (`SYSTEM_CODE`),
  CONSTRAINT `YHB_FUNCTIONS_SYS` FOREIGN KEY (`SYSTEM_CODE`) REFERENCES `yhb_sub_system` (`SUB_SYSTEM_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhb_menu_item
-- ----------------------------
DROP TABLE IF EXISTS `yhb_menu_item`;
CREATE TABLE `yhb_menu_item` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单主键',
  `NAME` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `TITLE` varchar(100) DEFAULT NULL COMMENT '菜单KEY',
  `DESCRIPTION` varchar(50) DEFAULT NULL COMMENT '菜单描述',
  `LOCATION` varchar(255) DEFAULT NULL COMMENT '菜单地址',
  `TARGET` varchar(10) DEFAULT NULL,
  `ACTION` varchar(100) DEFAULT NULL,
  `SYSTEM_CODE` varchar(10) NOT NULL COMMENT '菜单所属系统',
  `PARENT_ID` bigint(20) DEFAULT NULL COMMENT '上级菜单ID',
  `PARENT_NAME` varchar(100) DEFAULT NULL COMMENT '上级菜单名称',
  `ORDER_SEQ` bigint(20) DEFAULT NULL COMMENT '菜单显示序号',
  `IMAGE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `YHB_MENU_ITEM_SYS` (`SYSTEM_CODE`),
  CONSTRAINT `YHB_MENU_ITEM_SYS` FOREIGN KEY (`SYSTEM_CODE`) REFERENCES `yhb_sub_system` (`SUB_SYSTEM_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=522 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Table structure for yhb_message_board
-- ----------------------------
DROP TABLE IF EXISTS `yhb_message_board`;
CREATE TABLE `yhb_message_board` (
  `MESSAGE_OID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PUBLISHER` varchar(100) DEFAULT NULL,
  `READER` varchar(200) DEFAULT NULL,
  `TITLE` varchar(200) DEFAULT NULL,
  `CONTENT` varchar(1000) DEFAULT NULL,
  `EFF_DATE` datetime DEFAULT NULL,
  `EXP_DATE` datetime DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `SYSTEM_ID` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`MESSAGE_OID`)
) ENGINE=InnoDB AUTO_INCREMENT=1102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhb_op_log
-- ----------------------------
DROP TABLE IF EXISTS `yhb_op_log`;
CREATE TABLE `yhb_op_log` (
  `LOG_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SYSTEM_CODE` varchar(10) NOT NULL COMMENT '操作子系统ID',
  `USER_ID` varchar(20) DEFAULT NULL COMMENT '操作人ID',
  `USER_NAME` varchar(100) DEFAULT NULL COMMENT '操作人名称',
  `LOG_DATE` datetime DEFAULT NULL COMMENT '操作时间',
  `IP_ADDRESS` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `SYSTEM_NAME` varchar(100) DEFAULT NULL COMMENT '操作子系统名称',
  `FUNCTION_CODE` varchar(100) DEFAULT NULL COMMENT '操作功能代码',
  `FUNCTION_NAME` varchar(200) DEFAULT NULL COMMENT '操作功能名称',
  PRIMARY KEY (`LOG_OID`),
  KEY `YHB_OP_LOG_S` (`SYSTEM_CODE`),
  CONSTRAINT `YHB_OP_LOG_S` FOREIGN KEY (`SYSTEM_CODE`) REFERENCES `yhb_sub_system` (`SUB_SYSTEM_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=3148 DEFAULT CHARSET=utf8 COMMENT='系统操作日志表';

-- ----------------------------
-- Table structure for yhb_resources
-- ----------------------------
DROP TABLE IF EXISTS `yhb_resources`;
CREATE TABLE `yhb_resources` (
  `RES_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源表主键',
  `RES_NAME` varchar(80) DEFAULT NULL COMMENT '资源名称',
  `RES_TYPE` varchar(20) DEFAULT NULL COMMENT '资源类型',
  `RES_VALUE` varchar(200) DEFAULT NULL COMMENT '资源值',
  `RES_DESC` varchar(200) DEFAULT NULL COMMENT '资源描述',
  `SYSTEM_CODE` varchar(10) NOT NULL COMMENT '资源所属系统',
  PRIMARY KEY (`RES_ID`),
  KEY `YHB_RESOURCES_SYS` (`SYSTEM_CODE`),
  CONSTRAINT `YHB_RESOURCES_SYS` FOREIGN KEY (`SYSTEM_CODE`) REFERENCES `yhb_sub_system` (`SUB_SYSTEM_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=205008 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhb_role_data_auth
-- ----------------------------
DROP TABLE IF EXISTS `yhb_role_data_auth`;
CREATE TABLE `yhb_role_data_auth` (
  `ROLE_DATA_AUTH_OID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) NOT NULL,
  `AUTH_CODE` varchar(100) DEFAULT NULL,
  `AUTH_LEVEL` varchar(2) DEFAULT NULL COMMENT '节点权限类型：0、查看权限；1、操作权限',
  `AUTH_TYPE` varchar(2) DEFAULT NULL COMMENT '权限类型：0、单位、；1、地区；2、系统类别；3、单位性质',
  `IS_ONLY_OWN` varchar(2) DEFAULT NULL COMMENT '节点权限范围：Y、只有本节点操作权限；N 本节点及下属节点操作权限',
  PRIMARY KEY (`ROLE_DATA_AUTH_OID`),
  KEY `YHB_ROLE_DATA_AUTH_R` (`ROLE_ID`),
  CONSTRAINT `YHB_ROLE_DATA_AUTH_R` FOREIGN KEY (`ROLE_ID`) REFERENCES `yhb_roles` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2481 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhb_role_function
-- ----------------------------
DROP TABLE IF EXISTS `yhb_role_function`;
CREATE TABLE `yhb_role_function` (
  `ROLE_ID` bigint(20) NOT NULL,
  `FUNC_ID` bigint(20) NOT NULL,
  KEY `YHB_ROLE_FUNCTION_F` (`FUNC_ID`),
  KEY `YHB_ROLE_FUNCTION_R` (`ROLE_ID`),
  CONSTRAINT `YHB_ROLE_FUNCTION_F` FOREIGN KEY (`FUNC_ID`) REFERENCES `yhb_functions` (`FUNC_ID`),
  CONSTRAINT `YHB_ROLE_FUNCTION_R` FOREIGN KEY (`ROLE_ID`) REFERENCES `yhb_roles` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `yhb_role_menu`;
CREATE TABLE `yhb_role_menu` (
  `MENU_CODE` varchar(20) DEFAULT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '创建人代码',
  `CREATED_BY_NAME` varchar(20) DEFAULT NULL COMMENT '创建人名称',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '修改人代码',
  `UPDATED_BY_NAME` varchar(20) DEFAULT NULL COMMENT '修改人名称',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  KEY `YHB_ROLE_MENU_UN` (`MENU_CODE`,`ROLE_ID`),
  KEY `YHB_ROLE_MENU_R` (`ROLE_ID`),
  CONSTRAINT `YHB_ROLE_MENU_R` FOREIGN KEY (`ROLE_ID`) REFERENCES `yhb_roles` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhb_roles
-- ----------------------------
DROP TABLE IF EXISTS `yhb_roles`;
CREATE TABLE `yhb_roles` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色主键',
  `ROLE_NAME` varchar(200) NOT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `ROLE_TYPE` varchar(2) NOT NULL COMMENT '角色类型',
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '创建人代码',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '修改人代码',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='功能数据角色';

-- ----------------------------
-- Table structure for yhb_sub_system
-- ----------------------------
DROP TABLE IF EXISTS `yhb_sub_system`;
CREATE TABLE `yhb_sub_system` (
  `SUB_SYSTEM_CODE` varchar(10) NOT NULL COMMENT '系统名称',
  `SUB_SYSTEM_NAME` varchar(100) DEFAULT NULL COMMENT '子系统名称',
  `SUB_SYSTEM_DESC` varchar(200) DEFAULT NULL COMMENT '子系统描述',
  `SUB_SYSTEM_LOCATION` varchar(100) DEFAULT NULL COMMENT '子系统路径',
  `ORDER_OF_VIEW` bigint(20) DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`SUB_SYSTEM_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='子系统定义表，供系统开发阶段使用，上线后取消此维护';

-- ----------------------------
-- Table structure for yhb_system_position
-- ----------------------------
DROP TABLE IF EXISTS `yhb_system_position`;
CREATE TABLE `yhb_system_position` (
  `SYSTEM_POSITION_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统岗位主键',
  `SYSTEM_POSITION_NAME` varchar(100) DEFAULT NULL COMMENT '系统岗位名称',
  `SYSTEM_POSITION_DESC` varchar(1000) DEFAULT NULL COMMENT '系统岗位描述',
  `DATA_ROLE_ID` bigint(20) DEFAULT NULL COMMENT '数据权限角色ID',
  `FUNCTION_ROLE_ID` bigint(20) DEFAULT NULL COMMENT '功能权限角色ID',
  `DEPT_CODE` varchar(2) DEFAULT NULL COMMENT '岗位管理部门代码',
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  `CREATED_BY_NAME` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '修改人ID',
  `UPDATED_BY_NAME` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`SYSTEM_POSITION_OID`),
  KEY `YHB_SYSTEM_POSITION_DR` (`DATA_ROLE_ID`),
  KEY `YHB_SYSTEM_POSITION_FR` (`FUNCTION_ROLE_ID`),
  CONSTRAINT `YHB_SYSTEM_POSITION_DR` FOREIGN KEY (`DATA_ROLE_ID`) REFERENCES `yhb_roles` (`ROLE_ID`),
  CONSTRAINT `YHB_SYSTEM_POSITION_FR` FOREIGN KEY (`FUNCTION_ROLE_ID`) REFERENCES `yhb_roles` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for yhb_user_agent
-- ----------------------------
DROP TABLE IF EXISTS `yhb_user_agent`;
CREATE TABLE `yhb_user_agent` (
  `USER_AGENT_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户代理主键',
  `SYSTEM_POSITION_OID` bigint(20) NOT NULL,
  `USER_ID` varchar(20) DEFAULT NULL COMMENT '被代理人USER_ID',
  `AGENT_USER_ID` varchar(20) DEFAULT NULL COMMENT '指定代理人USER_ID',
  `EFFECTIVE_DATE` datetime DEFAULT NULL COMMENT '生效日期',
  `EXPIRED_DT` datetime DEFAULT NULL COMMENT '失效日期',
  `IS_ACTIVE` varchar(2) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`USER_AGENT_OID`),
  KEY `YHB_USER_AGENT_SP` (`SYSTEM_POSITION_OID`),
  CONSTRAINT `YHB_USER_AGENT_SP` FOREIGN KEY (`SYSTEM_POSITION_OID`) REFERENCES `yhb_system_position` (`SYSTEM_POSITION_OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户代理';

-- ----------------------------
-- Table structure for yhb_user_org_auth
-- ----------------------------
DROP TABLE IF EXISTS `yhb_user_org_auth`;
CREATE TABLE `yhb_user_org_auth` (
  `USER_ID` varchar(20) NOT NULL,
  `ORG_OID` bigint(20) NOT NULL,
  `ORG_NAME` varchar(1000) NOT NULL COMMENT '内设机构名称',
  `ORG_STATUS` varchar(2) DEFAULT NULL COMMENT '内设机构状态YHRS0101',
  `SYSTEM_POSITION_OID` bigint(20) NOT NULL COMMENT '岗位OID',
  KEY `YHB_USER_ORG_AUTH_SP` (`SYSTEM_POSITION_OID`),
  CONSTRAINT `YHB_USER_ORG_AUTH_SP` FOREIGN KEY (`SYSTEM_POSITION_OID`) REFERENCES `yhb_system_position` (`SYSTEM_POSITION_OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户内设机构权限表';

-- ----------------------------
-- Table structure for yhb_user_sp
-- ----------------------------
DROP TABLE IF EXISTS `yhb_user_sp`;
CREATE TABLE `yhb_user_sp` (
  `SYSTEM_POSITION_OID` bigint(20) NOT NULL COMMENT '系统岗位ID',
  `USER_ID` varchar(20) NOT NULL COMMENT '用户ID',
  `EFFECTIVE_DATE` date DEFAULT NULL COMMENT '生效日期',
  `EXPIRED_DATE` date DEFAULT NULL,
  `CREATED_BY_CODE` varchar(20) DEFAULT NULL,
  `CREATED_BY_NAME` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY_CDOE` varchar(20) DEFAULT NULL,
  `UPDATED_BY_NAME` varchar(100) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  KEY `YHB_USER_SP_SP` (`SYSTEM_POSITION_OID`),
  CONSTRAINT `YHB_USER_SP_SP` FOREIGN KEY (`SYSTEM_POSITION_OID`) REFERENCES `yhb_system_position` (`SYSTEM_POSITION_OID`),
  INDEX `YHB_USER_SP_USER_ID_IDX` (USER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户系统岗位表';

-- ----------------------------
-- Table structure for yhb_user_unit_auth
-- ----------------------------
DROP TABLE IF EXISTS `yhb_user_unit_auth`;
CREATE TABLE `yhb_user_unit_auth` (
  `USER_ID` varchar(20) NOT NULL,
  `UNIT_OID` bigint(20) NOT NULL,
  `UNIT_NAME` varchar(1000) NOT NULL COMMENT '单位名称',
  `UNIT_STATUS` varchar(2) DEFAULT NULL COMMENT '单位状态YHRS0101',
  `UNIT_KIND` varchar(20) DEFAULT NULL COMMENT '单位性质YHRS0090',
  `UNIT_CATEGORY_CODE` varchar(20) DEFAULT NULL COMMENT '单位系统类别YHRS0091',
  `SYSTEM_POSITION_OID` bigint(20) NOT NULL COMMENT '岗位OID',
  KEY `YHB_USER_UNIT_AUTH_SP` (`SYSTEM_POSITION_OID`),
  CONSTRAINT `YHB_USER_UNIT_AUTH_SP` FOREIGN KEY (`SYSTEM_POSITION_OID`) REFERENCES `yhb_system_position` (`SYSTEM_POSITION_OID`),
  INDEX `YHB_USER_SP_USER_ID_IDX` (USER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户单位权限表';

-- ----------------------------
-- Table structure for yhb_users
-- ----------------------------
DROP TABLE IF EXISTS `yhb_users`;
CREATE TABLE `yhb_users` (
  `USER_OID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` varchar(20) DEFAULT NULL COMMENT '用户登陆ID',
  `PASSWORD` varchar(100) DEFAULT NULL COMMENT '登陆密码',
  `USER_NAME` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `USER_SEX` varchar(20) DEFAULT NULL COMMENT '性别',
  `ADDRESS` varchar(100) DEFAULT NULL COMMENT '用户地址',
  `CONTACT_PHONE` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `REGIST_DATE` datetime DEFAULT NULL COMMENT '注册日期',
  `EFFECTIVE_DT` date DEFAULT NULL COMMENT '生效日期',
  `EXPIRED_DATE` date DEFAULT NULL COMMENT '失效日期',
  `USER_TYPE` varchar(20) DEFAULT NULL COMMENT '用户类型',
  `USER_STATUS` varchar(20) DEFAULT NULL COMMENT '用户状态',
  `UNIT_ID` bigint(20) DEFAULT NULL COMMENT '所属单位OID',
  `UNIT_NAME` varchar(1000) DEFAULT NULL COMMENT '所属单位名称',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '所属部门OID',
  `DEPT_NAME` varchar(1000) DEFAULT NULL COMMENT '所属部门名称',
  `UPDATED_BY_CODE` varchar(20) DEFAULT NULL COMMENT '修改人代码',
  `UPDATED_BY_NAME` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `DEFAULT_LOGIN_SYSTEM` varchar(20) DEFAULT NULL COMMENT '默认登录系统',
  PRIMARY KEY (`USER_OID`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for yhb_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `yhb_user_relation`;
CREATE TABLE `yhb_user_relation` (
  `USER_ID` varchar(20) NOT NULL COMMENT '登录账号',
  `REF_OID` decimal(12,2) NOT NULL COMMENT '来源OID',
  `REF_TYPE` varchar(2) NOT NULL COMMENT '来源类型 01：业务人员OID	02：基础人员OID	03：科室OID	04：单位OID',
  PRIMARY KEY (`USER_ID`),
  UNIQUE INDEX `YHB_USER_RELATION_IDX` (`REF_OID`,`REF_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息关系表';