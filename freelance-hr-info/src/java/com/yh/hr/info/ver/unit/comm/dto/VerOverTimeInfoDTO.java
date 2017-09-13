package com.yh.hr.info.ver.unit.comm.dto;

public class VerOverTimeInfoDTO {

	/**
     *主键ID
     **/
	private java.lang.Long overtimeOid;
    /**
     *人员ID
     **/
	private java.lang.Long personOid;
    /**
     *OvertimeType
     **/
	private java.lang.String overtimeType;
    /**
     *开始时间
     **/
	private java.util.Date startDate;
    /**
     *结束时间
     **/
	private java.util.Date endDate;
    /**
     *加班天数
     **/
	private java.lang.Float overtimeDays;
    /**
     *加班事由
     **/
	private java.lang.String reason;
    /**
     *法定假天数
     **/
	private java.lang.Float statutoryHolidayDays;
    /**
     *备注	
     **/
	private java.lang.String remark;
    /**
     *创建人ID
     **/
	private java.lang.String createBy;
    /**
     *创建人名称
     **/
	private java.lang.String createName;
    /**
     *创建时间
     **/
	private java.util.Date createDate;
    /**
     *修改人ID
     **/
	private java.lang.String updateBy;
    /**
     *修改人名称
     **/
	private java.lang.String updateName;
    /**
     *修改时间
     **/
	private java.util.Date updateDate;

	public VerOverTimeInfoDTO() {}

	public java.lang.Long getOvertimeOid() {
		return overtimeOid;
	}

	public void setOvertimeOid(java.lang.Long overtimeOid) {
		this.overtimeOid = overtimeOid;
	}

	public java.lang.Long getPersonOid() {
		return personOid;
	}

	public void setPersonOid(java.lang.Long personOid) {
		this.personOid = personOid;
	}

	public java.lang.String getOvertimeType() {
		return overtimeType;
	}

	public void setOvertimeType(java.lang.String overtimeType) {
		this.overtimeType = overtimeType;
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

	public java.lang.Float getOvertimeDays() {
		return overtimeDays;
	}

	public void setOvertimeDays(java.lang.Float overtimeDays) {
		this.overtimeDays = overtimeDays;
	}

	public java.lang.String getReason() {
		return reason;
	}

	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}

	public java.lang.Float getStatutoryHolidayDays() {
		return statutoryHolidayDays;
	}

	public void setStatutoryHolidayDays(java.lang.Float statutoryHolidayDays) {
		this.statutoryHolidayDays = statutoryHolidayDays;
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
	

}
