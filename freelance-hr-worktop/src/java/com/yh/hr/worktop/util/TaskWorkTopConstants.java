package com.yh.hr.worktop.util;

/**
 * 流程工作台常量类
 * 
 * @author liuhw 2016-9-1
 */
public class TaskWorkTopConstants {
	/** 流程表达式value - y */
	public static final String	FLOW_EXP_Y								= "y";
	/** 流程表达式value - n */
	public static final String	FLOW_EXP_N								= "n";
	
	public static final String	FLOW_EXP_T								= "t";

	/**
	 * 流程表达式Key之待单位上报环节 _report
	 */
	public static final String	FLOW_EXPRESSION_KEY_REPORT			= "_report";
	/**
	 * 流程表达式Key之业务终止环节 _interrupt
	 */
	public static final String	FLOW_EXPRESSION_KEY_INTERRUPT		= "_interrupt";
	/**
	 * 流程表达式Key之 业务撤回 _bizrecall
	 */
	public static final String	FLOW_EXPRESSION_KEY_BIZRECALL		= "_bizrecall";
	/**
	 * 流程表达式Key之待审核环节 _check
	 */
	public static final String	FLOW_EXPRESSION_KEY_CHECK			= "_check";
	/**
	 * 流程表达式Value之待审核环节 -同意
	 */
	public static final String	FLOW_EXPRESSION_VALUE_CHECK_Y		= FLOW_EXP_Y;
	/**
	 * 流程表达式Value之待审核环节 -不同意
	 */
	public static final String	FLOW_EXPRESSION_VALUE_CHECK_N		= FLOW_EXP_N;
	/**
	 * 流程表达式value之待审核环节 -退回
	 */
	public static final String	FLOW_EXPRESSION_VALUE_CHECK_T		= "t";
	/**
	 * 流程表达式value之待单位上报环节-上报
	 */
	public static final String	FLOW_EXPRESSION_VALUE_REPORT_Y		= FLOW_EXP_Y;
	/**
	 * 流程表达式value之业务终止环节 -终止
	 */
	public static final String	FLOW_EXPRESSION_VALUE_INTERRUPT_N	= FLOW_EXP_N;
	/**
	 * 流程表达式value之 业务撤回 _unitcheck
	 */
	public static final String	FLOW_EXPRESSION_VALUE_BIZRECALL_Y	= FLOW_EXP_Y;

	/**
	 * 审核意见（写入BT_LOG）
	 */
	public static final String FLOW_DATA_TASK_REMARK 				= "_remark";

}
