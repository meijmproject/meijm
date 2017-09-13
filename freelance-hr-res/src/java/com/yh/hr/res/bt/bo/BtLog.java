/**
 * This file is generated by  code generation tool version 0.2 ^_^
 * Created at 2016-10-19
**/
package com.yh.hr.res.bt.bo;

import com.yh.platform.core.bo.BaseBo;

public class BtLog extends BaseBo {
	private static final long serialVersionUID = -6494887000157735027L;

	private java.lang.Long  	taskLogOid;	//日志主键
	private java.lang.Long  	taskOid;	//TaskOid
	private java.lang.String	bizStatusCode;	//业务岗状态代码
	private java.lang.String	bizStatusName;	//业务岗状态名称
	private java.lang.String	auditStatusCode;	//审核岗状态代码
	private java.lang.String	auditStatusName;	//审核岗状态名称
	private java.lang.String	processDeptCode;	//在办部门
	private java.lang.String	processDeptName;	//在办部门名称
	private java.lang.String	remark;	//办理意见
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate

	public BtLog() {
		
	}

	public void setTaskLogOid(java.lang.Long taskLogOid){
		this.taskLogOid = taskLogOid;
	}

	public java.lang.Long getTaskLogOid(){
		return this.taskLogOid;
	}

	public void setTaskOid(java.lang.Long taskOid){
		this.taskOid = taskOid;
	}

	public java.lang.Long getTaskOid(){
		return this.taskOid;
	}

	public void setBizStatusCode(java.lang.String bizStatusCode){
		this.bizStatusCode = bizStatusCode;
	}

	public java.lang.String getBizStatusCode(){
		return this.bizStatusCode;
	}

	public void setBizStatusName(java.lang.String bizStatusName){
		this.bizStatusName = bizStatusName;
	}

	public java.lang.String getBizStatusName(){
		return this.bizStatusName;
	}

	public void setAuditStatusCode(java.lang.String auditStatusCode){
		this.auditStatusCode = auditStatusCode;
	}

	public java.lang.String getAuditStatusCode(){
		return this.auditStatusCode;
	}

	public void setAuditStatusName(java.lang.String auditStatusName){
		this.auditStatusName = auditStatusName;
	}

	public java.lang.String getAuditStatusName(){
		return this.auditStatusName;
	}

	public void setProcessDeptCode(java.lang.String processDeptCode){
		this.processDeptCode = processDeptCode;
	}

	public java.lang.String getProcessDeptCode(){
		return this.processDeptCode;
	}

	public void setProcessDeptName(java.lang.String processDeptName){
		this.processDeptName = processDeptName;
	}

	public java.lang.String getProcessDeptName(){
		return this.processDeptName;
	}

	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

	public java.lang.String getRemark(){
		return this.remark;
	}

	public void setCreatedByCode(java.lang.String createdByCode){
		this.createdByCode = createdByCode;
	}

	public java.lang.String getCreatedByCode(){
		return this.createdByCode;
	}

	public void setCreatedByName(java.lang.String createdByName){
		this.createdByName = createdByName;
	}

	public java.lang.String getCreatedByName(){
		return this.createdByName;
	}

	public void setCreatedDate(java.util.Date createdDate){
		this.createdDate = createdDate;
	}

	public java.util.Date getCreatedDate(){
		return this.createdDate;
	}

}
