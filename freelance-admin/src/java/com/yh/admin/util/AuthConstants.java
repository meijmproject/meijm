package com.yh.admin.util;

import com.yh.platform.core.constant.Constant;

/**
 * 用户授权相关常量
 * @author	zhangqp
 * @version	1.0,	16/08/17
 */
public class AuthConstants {
	// 角色type 功能
	public static final String	ROLE_TYPE_FUNCTION		= "0";

	// 角色type 数据
	public static final String	ROLE_TYPE_DATA			= "1";
	
	// 权限类型
	public static final String	AUTH_LEVEL_SEARCH		= "0";			// 节点权限查看
	public static final String	AUTH_LEVEL_EDIT			= "1";			// 节点权限操作（保留类型，暂未使用）

	// 机构权限节点类型：
	public static final String	AUTH_TYPE_UNIT			= "0";			// 节点为单位
	public static final String	AUTH_TYPE_PERSON		= "1";			// 节点为人员类别
	public static final String	AUTH_TYPE_ORG			= "8";			// 节点为内设机构

	// 通过MODULE 工作流节点设置数据权限
	public static final String	AUTH_TYPE_WORKFlOW		= "4";			// 工作流节点

	/**
	 * 授权节点类型：事项授权
	 */
	public static final String	AUTH_TYPE_ITEMCODE		= "5";			// 事项节点

	/**
	 * 授权节点类型：事项授权
	 */
	public static final String	JG_RY_XXJ				= "JGRYXXJ";	// 机关人员信息集父节点
	/**
	 * 授权节点类型：事项授权
	 */
	public static final String	SY_RY_XXJ				= "SYRYXXJ";	// 事业人员信息集父节点
	
	// 节点权限范围
	public static final String	IS_ONLY_OWN_Y			= Constant.YES;			// Y只有本节点操作权限
	public static final String	IS_ONLY_OWN_N			= Constant.NO;			// N

	/**
	 * 全部权限 （单位节点） 所有单位节点数据权限
	 */
	public static final String ALL_UNIT_AUTH_CODE = "%";

}
