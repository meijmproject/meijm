/**
 * 
 */
package com.yh.admin.util;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/08/17
 */

public class AdminConstants {
	// 登录人类别(系统管理员，子系统管理员，单位管理员，普通用户)
	public static final String	OPERATOR_TYPE			= "USER_TYPE";
	//默认密码
	public static final String	DEFAULT_PASSWORD			= "666666";
	// 登录人所属系统的ID
	public static final String	OPERATOR_SYSTEMID		= "OPERATOR_SYSTEMID";

	// 登录人所属单位ID
	public static final String	OPERATOR_UNITID			= "OPERATOR_UNITID";
	public static final String	OPERATOR_PARENT_UNITID	= "OPERATOR_PARENT_UNITID";
	public static final String	OPERATOR_UNIT_NAME		= "OPERATOR_UNIT_NAME";
	
	/** 标识码 */
	public static final String	YHRS0003	= "YHRS0003";	// 标识码，是否干部、是否退休等
	/** 标识码 - 否 */
	public static final String	YHRS0003_0	= "0";	// 否
	/** 标识码 - 是 */
	public static final String	YHRS0003_1	= "1";	// 是
	
	/** 性别 */
	public static final String	YHRS0001	= "YHRS0001";	// 性别
	public static final String	YHRS9002    = "YHRS9002";  //用户状态
	public static final String	YHRS9001  	= "YHRS9001";   //用户类型
	public static final String	YHRS9001_01  = "01";   //个人用户
	public static final String	YHRS9001_02  = "02";   //单位用户
	public static final String	YHRS9001_03  = "03";   //科室用户
	public static final String	YHRS9001_04  = "04";   //审批用户
	public static final String	YHRS9001_09  = "09";   //管理员
	
	public static final String	YHRS0010  	= "YHRS0010";   //人员类别
	
	
	/** 机构状态 */
	public static final String	YHRS0101	= "YHRS0101";	// 机构状态
	/** 机构状态-新建*/
	public static final String	YHRS0101_1	= "1";			// 机构状态-撤销
	/** 机构状态-正常*/
	public static final String	YHRS0101_2	= "2";			// 机构状态-撤销
	/** 机构状态-撤销*/
	public static final String	YHRS0101_3	= "3";			// 机构状态-撤销
	
	/**
	 * 中国行政区划
	 */
	public static final String	YHRS0006  	="YHRS0006";	//中国行政区划
	/**
	 * 国家和地区代码
	 */
	public static final String	YHRS0006_330800 = "330800";	//浙江衢州市
	
	/** 用户关系来源类型 */
	public static final String YHRS0137 	= "YHRS0137";
	/** 业务人员OID */
	public static final String YHRS0137_01 	= "01";
	/** 基础人员OID */
	public static final String YHRS0137_02 	= "02";
	/** 科室OID */
	public static final String YHRS0137_03 	= "03";
	/** 单位OID */
	public static final String YHRS0137_04 	= "04";
}
