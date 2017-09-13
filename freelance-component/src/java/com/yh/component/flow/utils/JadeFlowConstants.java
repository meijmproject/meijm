package com.yh.component.flow.utils;


/** 
 *@description		业务流程常量类
 *@author           liuhw
 *@created          2016-08-25
 *@version          1.0
 */
public class JadeFlowConstants 
{
	public static final String YES = "Y";
	public static final String NO = "N";
	
	public static final String LOWER_YES = "y";
	public static final String LOWER_NO = "n";
	
	/**
	 * 流程表达式Key之公务员登记跳转分支__yesorno
	 */
	public static final String	FLOW_EXPRESSION_KEY_YERORNO					= "_yesorno";
	
	
	/**
	 * 开启工作流异常提示信息增加前缀
	 */
	public static final String START_FLOW_EXCEPTION_PRE = "开启业务流程异常：";
	
	/**
	 * 逻辑删除工作流异常提示信息增加前缀
	 */
	public static final String DELETE_FLOW_EXCEPTION_PRE = "逻辑删除业务流程异常：";
	
	/**
	 * 推动工作流异常提示信息增加前缀
	 */
	public static final String COMPLETESTEP_FLOW_EXCEPTION_PRE = "业务流程异常：";
	
	/**
	 * 工作流没有找到匹配的流转方向
	 */
	public static final String JWF_1007 = "JWF_1007";
	/**
     * 查询类别 待办/已办/所有/初始
     */
	public static final String QUERY_TYPE_0 = "0";	// 初始
	public static final String QUERY_TYPE_1 = "1";	// 待办
	public static final String QUERY_TYPE_2 = "2";	// 已办
	
	
	
}
