package com.yh.hr.res.pb.bo;

import com.yh.platform.core.bo.BaseBo;

public class PbDutyInfo extends BaseBo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5930349264097583982L;
	
	
	private java.lang.Long dutyOid;
	private java.lang.Long personOid; //人员ID
	private java.lang.String dutyStatus; //在聘状态
	private java.lang.String dutyName; //职务名称
	private java.lang.Long deptOid; //任职部门
	private java.util.Date startDate; //任职时间
	private java.util.Date endDate; //离任时间
	private java.lang.String remark; //备注
	private java.lang.String createBy; //创建人ID
	private java.lang.String createName; //创建人名称
	private java.util.Date createDate; //创建时间
	private java.lang.String updateBy; //修改人ID
	private java.lang.String updateName; //修改人名称
	private java.util.Date updateDate; //修改时间
	
	/**
	 * 是否主要职务
	 */
	private java.lang.String isMainDutyInfo;
	
	
	public java.lang.Long getDutyOid() {
		return dutyOid;
	}
	public void setDutyOid(java.lang.Long dutyOid) {
		this.dutyOid = dutyOid;
	}
	public java.lang.Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(java.lang.Long personOid) {
		this.personOid = personOid;
	}
	public java.lang.String getDutyStatus() {
		return dutyStatus;
	}
	public void setDutyStatus(java.lang.String dutyStatus) {
		this.dutyStatus = dutyStatus;
	}
	public java.lang.String getDutyName() {
		return dutyName;
	}
	public void setDutyName(java.lang.String dutyName) {
		this.dutyName = dutyName;
	}
	public java.lang.Long getDeptOid() {
		return deptOid;
	}
	public void setDeptOid(java.lang.Long deptOid) {
		this.deptOid = deptOid;
	}
	public java.util.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	public java.util.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public java.lang.String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}
	public java.lang.String getCreateName() {
		return createName;
	}
	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.lang.String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}
	public java.lang.String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}
	public java.util.Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	public void setIsMainDutyInfo(java.lang.String isMainDutyInfo) {
		this.isMainDutyInfo = isMainDutyInfo;
	}
	public java.lang.String getIsMainDutyInfo() {
		return isMainDutyInfo;
	}

}
