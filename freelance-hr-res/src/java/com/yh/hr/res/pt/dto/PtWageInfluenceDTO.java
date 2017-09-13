package com.yh.hr.res.pt.dto;

import java.util.Date;

public class PtWageInfluenceDTO {
	/**
	 * 人员影响工资信息
	 */
	private java.lang.Long bizPersonOid;//
	private Long personOid;// 
	private java.util.Date startWorkDate;// 参加工作时间
	private java.util.Date passProbationDate;// 参加工作转正时间
	private java.util.Date wageStart;// 工龄起算时间
	private java.lang.Integer yearsServiceBroke;// 工龄间断年限
	private java.lang.Integer collegeYearsNoService;// 不计工龄大专以上学历年限
	private Long wageServiceUnit;// 工资关系单位
	private Date startDateOfWage;// 起薪时间
	private Integer serviceYears;// 工龄
	private java.lang.String isInUnionpay;// 是否参与统发YHRS0003
	private java.lang.String stopWageFlag;// 工资发放标志YHRS0003
	private String remark;
	private String createdByCode;
	private String createdByName;
	private Date createdDate;
	private String updatedByCode;
	private String updatedByName; 
	private Date updatedDate;
	private Date teaNurseStartDate;//教护龄开始时间
	private String teaNurseStartDateStr;
	
//	private String wageStartStr;
//	private String startWorkDateStr;
//	private String passProbationDateStr;
	private String startDateOfWageStr;//起薪时间
	private  String wageServiceUnitName;//工资关系单位名称

	public Long getPersonOid() {
		return personOid;
	}

	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}

	public String getTeaNurseStartDateStr() {
		return teaNurseStartDateStr;
	}

	public void setTeaNurseStartDateStr(String teaNurseStartDateStr) {
		this.teaNurseStartDateStr = teaNurseStartDateStr;
	}

//	public String getWageStartStr() {
//		return wageStartStr;
//	}
//
//	public void setWageStartStr(String wageStartStr) {
//		this.wageStartStr = wageStartStr;
//	}
//
//	public String getStartWorkDateStr() {
//		return startWorkDateStr;
//	}
//
//	public void setStartWorkDateStr(String startWorkDateStr) {
//		this.startWorkDateStr = startWorkDateStr;
//	}
//
//	public String getPassProbationDateStr() {
//		return passProbationDateStr;
//	}
//
//	public void setPassProbationDateStr(String passProbationDateStr) {
//		this.passProbationDateStr = passProbationDateStr;
//	}

	public String getStartDateOfWageStr() {
		return startDateOfWageStr;
	}

	public void setStartDateOfWageStr(String startDateOfWageStr) {
		this.startDateOfWageStr = startDateOfWageStr;
	}

	public Date getTeaNurseStartDate() {
		return teaNurseStartDate;
	}

	public void setTeaNurseStartDate(Date teaNurseStartDate) {
		this.teaNurseStartDate = teaNurseStartDate;
	}

	public java.lang.Long getBizPersonOid() {
		return bizPersonOid;
	}

	public void setBizPersonOid(java.lang.Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}

	public java.util.Date getStartWorkDate() {
		return startWorkDate;
	}

	public void setStartWorkDate(java.util.Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	public java.util.Date getPassProbationDate() {
		return passProbationDate;
	}

	public void setPassProbationDate(java.util.Date passProbationDate) {
		this.passProbationDate = passProbationDate;
	}

	public java.util.Date getWageStart() {
		return wageStart;
	}

	public void setWageStart(java.util.Date wageStart) {
		this.wageStart = wageStart;
	}

	public java.lang.Integer getYearsServiceBroke() {
		return yearsServiceBroke;
	}

	public void setYearsServiceBroke(java.lang.Integer yearsServiceBroke) {
		this.yearsServiceBroke = yearsServiceBroke;
	}

	public java.lang.Integer getCollegeYearsNoService() {
		return collegeYearsNoService;
	}

	public void setCollegeYearsNoService(java.lang.Integer collegeYearsNoService) {
		this.collegeYearsNoService = collegeYearsNoService;
	}

	public java.lang.String getIsInUnionpay() {
		return isInUnionpay;
	}

	public void setIsInUnionpay(java.lang.String isInUnionpay) {
		this.isInUnionpay = isInUnionpay;
	}

	public java.lang.String getStopWageFlag() {
		return stopWageFlag;
	}

	public void setStopWageFlag(java.lang.String stopWageFlag) {
		this.stopWageFlag = stopWageFlag;
	}

	public Long getWageServiceUnit() {
		return wageServiceUnit;
	}

	public void setWageServiceUnit(Long wageServiceUnit) {
		this.wageServiceUnit = wageServiceUnit;
	}

	public Date getStartDateOfWage() {
		return startDateOfWage;
	}

	public void setStartDateOfWage(Date startDateOfWage) {
		this.startDateOfWage = startDateOfWage;
	}

	public Integer getServiceYears() {
		return serviceYears;
	}

	public void setServiceYears(Integer serviceYears) {
		this.serviceYears = serviceYears;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedByCode() {
		return createdByCode;
	}

	public void setCreatedByCode(String createdByCode) {
		this.createdByCode = createdByCode;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedByCode() {
		return updatedByCode;
	}

	public void setUpdatedByCode(String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getWageServiceUnitName() {
		return wageServiceUnitName;
	}

	public void setWageServiceUnitName(String wageServiceUnitName) {
		this.wageServiceUnitName = wageServiceUnitName;
	}

}
