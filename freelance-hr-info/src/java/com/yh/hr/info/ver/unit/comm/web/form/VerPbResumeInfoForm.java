package com.yh.hr.info.ver.unit.comm.web.form;

import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

public class VerPbResumeInfoForm extends ValidatorForm{
	private static final long serialVersionUID = -7654523555496401898L;
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
    /**
     *备注
     **/
	private java.lang.String remark;
	 /**
     *工作简历
     **/
	private java.lang.String resume;
    /**
     *CreatedByCode
     **/
	private java.lang.String createdByCode;
    /**
     *CreatedByName
     **/
	private java.lang.String createdByName;
    /**
     *CreatedDate
     **/
	private java.util.Date createdDate;
    /**
     *UpdatedByCode
     **/
	private java.lang.String updatedByCode;
    /**
     *UpdatedByName
     **/
	private java.lang.String updatedByName;
    /**
     *UpdatedDate
     **/
	private java.util.Date updatedDate;

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
		if (null != startDate) {
			this.startDate = startDate;
			this.startDateStr = DateUtil.format(startDate,"yyyy-MM");
		}
	}

	public java.util.Date getStartDate(){
		return this.startDate;
	}

	public void setEndDate(java.util.Date endDate){
		if (null != endDate) {
			this.endDate = endDate;
			this.endDateStr = DateUtil.format(endDate, "yyyy-MM");
		}
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

	public void setUpdatedByCode(java.lang.String updatedByCode){
		this.updatedByCode = updatedByCode;
	}

	public java.lang.String getUpdatedByCode(){
		return this.updatedByCode;
	}

	public void setUpdatedByName(java.lang.String updatedByName){
		this.updatedByName = updatedByName;
	}

	public java.lang.String getUpdatedByName(){
		return this.updatedByName;
	}

	public void setUpdatedDate(java.util.Date updatedDate){
		this.updatedDate = updatedDate;
	}

	public java.util.Date getUpdatedDate(){
		return this.updatedDate;
	}

	public java.lang.String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(java.lang.String startDateStr) {
		this.startDateStr = startDateStr;
		this.startDate = DateUtil.parse(startDateStr, "yyyy-MM");
	}

	public java.lang.String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(java.lang.String endDateStr) {
		this.endDateStr = endDateStr;
		this.endDate = DateUtil.parse(endDateStr,"yyyy-MM");
	}

	public java.lang.String getResume() {
		return resume;
	}

	public void setResume(java.lang.String resume) {
		this.resume = resume;
	}

}
