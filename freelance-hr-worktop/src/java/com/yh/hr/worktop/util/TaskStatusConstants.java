/**
 * 
 */
package com.yh.hr.worktop.util;

/**
 * 业务状态、在办部门常量
 * 
 * @author zhangqp
 * @version 1.0, 16/10/19
 */

public class TaskStatusConstants {
	
	/**
	 * 迁移数据标示常量：migration
	 * */
	public static final String	MIGRATION			= "migration";	
	
	/***************************************************************************
	 * 在办部门
	 **************************************************************************/
	public static final String	DEPT_CODE_DW			= "10";		// 部门：单位
	public static final String	DEPT_CODE_KS			= "11";		// 部门：科室
	public static final String	DEPT_CODE_GR			= "12";		// 部门：个人
	public static final String	DEPT_CODE_KZR			= "13";		// 部门：科主任
	public static final String	DEPT_CODE_HSZ			= "14";		// 部门：护士长
	public static final String	DEPT_CODE_GH			= "15";		// 部门：工会
	public static final String	DEPT_CODE_KYS			= "16";		// 部门：教研室
	public static final String	DEPT_CODE_YWK			= "17";		// 部门：医务科
	public static final String	DEPT_CODE_HLB			= "18";		// 部门：护理部
	public static final String	DEPT_CODE_RSK			= "19";		// 部门：人事科
	public static final String	DEPT_CODE_KJK			= "20";		// 部门：科教科
	public static final String	DEPT_CODE_FGYLD			= "21";		// 部门：分管院领导
	public static final String	DEPT_CODE_YZ			= "22";		// 部门：院长
	
	/***************************************************************************
	 * 单岗管理业务状态
	 **************************************************************************/
	public static final String	BIZ_STATUS_WSB			 = "U104";	// 未上报***********业务回到单位业务环节，尚未发起业务上报********进入单位上报环节
	public static final String	SY_BIZ_STATUS_WSB		 = "SU104";	// 未上报****业务申报进入该环节*******
	public static final String	SY_BIZ_STATUS_YSB		 = "SU105";	// 已上报****业务申报上报业务*******
	public static final String	SY_BIZ_STATUS_ZZYW		 = "SU107";	// 终止业务****业务申报终止业务*******
	public static final String	SY_BIZ_STATUS_CHYW		 = "SU111";	// 撤回业务****业务申报撤回业务*******
	public static final String	SY_BIZ_STATUS_DYWSP		 = "SU219";	// 待业务审批****业务审批进入该环节*******
	public static final String	SY_BIZ_STATUS_SPTY		 = "SU220";	// 审批同意****业务审批审批同意*******
	public static final String	SY_BIZ_STATUS_SPBTY		 = "SU221";	// 审批不同意****业务审批审批不同意*******
	public static final String	SY_BIZ_STATUS_YWBJ		 = "SU999";	// 业务办结****业务办结*******
	public static final String	BIZ_STATUS_TH			 = "U204";	// 业务退回***********审核部门将业务退回至单位********首办处室审核的一种结果，离开首办处室审核环节时
	
	/***************************************************************************
	 * 单岗管理业务管理版业务状态
	 **************************************************************************/
	public static final String	SY_AUDIT_STATUS_WSB		 = "SM104";	// 未上报****业务申报进入该环节*******
	public static final String	SY_AUDIT_STATUS_YSB		 = "SM105";	// 已上报****业务申报上报业务*******
	public static final String	SY_AUDIT_STATUS_ZZYW	 = "SM111";	// 终止业务****业务申报终止业务*******
	public static final String	SY_AUDIT_STATUS_CHYW	 = "SM107";	// 撤回业务****业务申报撤回业务*******
	public static final String	SY_AUDIT_STATUS_DYWSP	 = "SM230";	// 待业务审批****业务审批进入该环节*******
	public static final String	SY_AUDIT_STATUS_SPTY	 = "SM231";	// 审批同意****业务审批审批同意*******
	public static final String	SY_AUDIT_STATUS_SPBTY	 = "SM232";	// 审批不同意****业务审批审批不同意*******
	public static final String	SY_AUDIT_STATUS_YWBJ	 = "SM999";	// 业务办结****业务办结*******
	public static final String	AUDIT_STATUS_TH			 = "M204";	// 业务退回*************单位上报的业务信息有误时，首办处室退回单位********首办处室退回已受理业务时

	/***************************************************************************
	 * 个人版业务状态
	 **************************************************************************/
	public static final String	GR_BIZ_STATUS_WSB		 = "P104";	// 未上报****业务申报进入该环节*******
	public static final String	GR_BIZ_STATUS_YSB		 = "P105";	// 已上报****业务申报上报业务*******
	public static final String	GR_BIZ_STATUS_ZZYW		 = "P106";	// 终止业务****业务申报终止业务*******
	public static final String	GR_BIZ_STATUS_CHYW		 = "P107";	// 撤回业务****业务申报撤回业务*******
	public static final String	GR_BIZ_STATUS_DSH		 = "P210";	// 待审核****科室审核进入该环节*******
	public static final String	GR_BIZ_STATUS_SHTY		 = "P212";	// 审核同意****科室审核审核同意*******
	public static final String	GR_BIZ_STATUS_SHBTY		 = "P213";	// 审核不同意****科室审核审核同意*******
	public static final String	GR_BIZ_STATUS_DSP		 = "P220";	// 待审批****业务审批进入该环节*******
	public static final String	GR_BIZ_STATUS_SPTY		 = "P221";	// 审批同意****业务审批审批同意*******
	public static final String	GR_BIZ_STATUS_SPBTY		 = "P222";	// 审批不同意****业务审批审批不同意*******
	public static final String	GR_BIZ_STATUS_YWTH	 	 = "P299";	// 业务退回****退回业务*******
	public static final String	GR_BIZ_STATUS_YWBJ		 = "P999";	// 业务办结****业务办结*******

	/***************************************************************************
	 * 科室版业务状态
	 **************************************************************************/
	public static final String	KS_BIZ_STATUS_WSB		 = "O104";	// 未上报****业务申报进入该环节*******
	public static final String	KS_BIZ_STATUS_YSB		 = "O105";	// 已上报****业务申报上报业务*******
	public static final String	KS_BIZ_STATUS_ZZYW		 = "O106";	// 终止业务****业务申报终止业务*******
	public static final String	KS_BIZ_STATUS_CHYW		 = "O107";	// 撤回业务****业务申报撤回业务*******
	public static final String	KS_BIZ_STATUS_DSH		 = "O210";	// 待审核****科室审核进入该环节*******
	public static final String	KS_BIZ_STATUS_SHTY		 = "O212";	// 审核同意****科室审核审核同意*******
	public static final String	KS_BIZ_STATUS_SHBTY		 = "O213";	// 审核不同意****科室审核审核同意*******
	public static final String	KS_BIZ_STATUS_DSP		 = "O220";	// 待审批****业务审批进入该环节*******
	public static final String	KS_BIZ_STATUS_SPTY		 = "O221";	// 审批同意****业务审批审批同意*******
	public static final String	KS_BIZ_STATUS_SPBTY		 = "O222";	// 审批不同意****业务审批审批不同意*******
	public static final String	KS_BIZ_STATUS_YWTH	 	 = "O299";	// 业务退回****退回业务*******
	public static final String	KS_BIZ_STATUS_YWBJ		 = "O999";	// 业务办结****业务办结*******
	
	/***************************************************************************
	 * 管理版业务状态
	 **************************************************************************/
	public static final String	GL_AUDIT_STATUS_WSB		 = "M104";	// 未上报****业务申报进入该环节*******
	public static final String	GL_AUDIT_STATUS_YSB		 = "M105";	// 已上报****业务申报上报业务*******
	public static final String	GL_AUDIT_STATUS_ZZYW	 = "M106";	// 终止业务****业务申报终止业务*******
	public static final String	GL_AUDIT_STATUS_CHYW	 = "M107";	// 撤回业务****业务申报撤回业务*******
	public static final String	GL_AUDIT_STATUS_DSH	 	 = "M210";	// 待审核****科室审核进入该环节*******
	public static final String	GL_AUDIT_STATUS_SHTY	 = "M211";	// 审核同意****科室审核审核同意*******
	public static final String	GL_AUDIT_STATUS_SHBTY	 = "M212";	// 审核不同意****科室审核审核不同意*******
	public static final String	GL_AUDIT_STATUS_DSP	 	 = "M220";	// 待审批****业务审批进入该环节*******
	public static final String	GL_AUDIT_STATUS_SPTY	 = "M221";	// 审批同意****业务审批审批同意*******
	public static final String	GL_AUDIT_STATUS_SPBTY	 = "M222";	// 审批不同意****业务审批审批不同意*******
	public static final String	GL_AUDIT_STATUS_YWTH	 = "M299";	// 业务退回****退回业务*******
	public static final String	GL_AUDIT_STATUS_YWBJ	 = "M999";	// 业务办结****业务办结*******

}
