package com.yh.hr.res.gt.bo;


import com.yh.platform.core.bo.BaseBo;

public class GtSetPlan extends BaseBo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3777221792451427431L;
	
	private java.lang.Long      gtSetPlanOid;
	private java.lang.Long      unitOid;
	private java.lang.Long      taskOid;
	private java.lang.String    gwSetPlanName;
	private java.lang.String    unitName;
	private java.lang.String    remark;
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date  	updatedDate;	//UpdatedDate
	
	public java.lang.Long getGtSetPlanOid() {
		return gtSetPlanOid;
	}
	public java.lang.Long getUnitOid() {
		return unitOid;
	}
	public java.lang.Long getTaskOid() {
		return taskOid;
	}
	public java.lang.String getGwSetPlanName() {
		return gwSetPlanName;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public java.lang.String getCreatedByCode() {
		return createdByCode;
	}
	public java.lang.String getCreatedByName() {
		return createdByName;
	}
	public java.util.Date getCreatedDate() {
		return createdDate;
	}
	public java.lang.String getUpdatedByCode() {
		return updatedByCode;
	}
	public java.lang.String getUpdatedByName() {
		return updatedByName;
	}
	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}
	public void setGtSetPlanOid(java.lang.Long gtSetPlanOid) {
		this.gtSetPlanOid = gtSetPlanOid;
	}
	public void setUnitOid(java.lang.Long unitOid) {
		this.unitOid = unitOid;
	}
	public void setTaskOid(java.lang.Long taskOid) {
		this.taskOid = taskOid;
	}
	public void setGwSetPlanName(java.lang.String gwSetPlanName) {
		this.gwSetPlanName = gwSetPlanName;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public void setCreatedByCode(java.lang.String createdByCode) {
		this.createdByCode = createdByCode;
	}
	public void setCreatedByName(java.lang.String createdByName) {
		this.createdByName = createdByName;
	}
	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setUpdatedByCode(java.lang.String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}
	public void setUpdatedByName(java.lang.String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public java.lang.String getUnitName() {
		return unitName;
	}
	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}
	
}
