package com.yh.hr.info.ver.unit.comm.dto;

public class VerPbResumeInfoDTO {
	/**
     *主键
     **/
	private java.lang.Long resumeOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *工作起始日期
     **/
	private java.util.Date startDate;
	private java.lang.String startDateStr;
    /**
     *工作结束日期
     **/
	private java.util.Date endDate;
	private java.lang.String endDateStr;
    /**
     *所在单位
     **/
	private java.lang.String unit;
    /**
     *从事工作或担任职务
     **/
	private java.lang.String duty;
	private java.lang.String resume;
    /**
     *备注
     **/
	private java.lang.String remark;

	public void setResumeOid(java.lang.Long resumeOid){
		this.resumeOid = resumeOid;
	}

	public java.lang.Long getResumeOid(){
		return this.resumeOid;
	}

	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}

	public void setStartDate(java.util.Date startDate){
		this.startDate = startDate;
	}

	public java.util.Date getStartDate(){
		return this.startDate;
	}

	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}

	public java.util.Date getEndDate(){
		return this.endDate;
	}

	public void setUnit(java.lang.String unit){
		this.unit = unit;
	}

	public java.lang.String getUnit(){
		return this.unit;
	}

	public void setDuty(java.lang.String duty){
		this.duty = duty;
	}

	public java.lang.String getDuty(){
		return this.duty;
	}

	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

	public java.lang.String getRemark(){
		return this.remark;
	}

	public java.lang.String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(java.lang.String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public java.lang.String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(java.lang.String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public java.lang.String getResume() {
		return resume;
	}

	public void setResume(java.lang.String resume) {
		this.resume = resume;
	}

}
