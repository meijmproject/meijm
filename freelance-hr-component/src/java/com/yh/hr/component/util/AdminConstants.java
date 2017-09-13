/**
 * 
 */
package com.yh.hr.component.util;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/08/17
 */

public class AdminConstants {
	// 登录人类别(系统管理员，子系统管理员，单位管理员，普通用户)
	public static final String	OPERATOR_TYPE			= "USER_TYPE";

	// 登录人所属系统的ID
	public static final String	OPERATOR_SYSTEMID		= "OPERATOR_SYSTEMID";

	// 登录人所属单位ID
	public static final String	OPERATOR_UNITID			= "OPERATOR_UNITID";
	public static final String	OPERATOR_PARENT_UNITID	= "OPERATOR_PARENT_UNITID";
	public static final String	OPERATOR_UNIT_NAME		= "OPERATOR_UNIT_NAME";

	
	/** 性别 */
	public static final String	YHRS0001	= "YHRS0001";	// 性别
	public static final String	YHRS9002    = "YHRS9002";  //用户状态
	public static final String	YHRS9001  	= "YHRS9001";   //用户类型
	
	/** 单位性质 */
	public static final String	YHRS0090	= "YHRS0090";	// 单位性质
	/** 系统类别 */
	public static final String	YHRS0091	= "YHRS0091";	// 系统类别
	
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
	
	public static final String ORG_STATUS_NORMAL = "2";
	
	//用户与人员的关系
	public static final String REF_TYPE_01 = "01";
	public static final String REF_TYPE_02 = "02";
	
	
}
