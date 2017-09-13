package com.yh.hr.res.pt.bo;

import java.util.Date;

import com.yh.platform.core.bo.BaseBo;

public class PtWageInfluence extends BaseBo{

	/**
	 * 人员影响工资信息
	 */
	private static final long serialVersionUID = 3581727205445542418L;
	private Long bizPersonOid;
	private Long personOid;
	private java.util.Date startWorkDate;//参加工作时间
	private java.util.Date passProbationDate;//参加工作转正时间
	private java.util.Date wageStart;//工龄起算时间
	private Integer yearsServiceBroke;//工龄间断年限
	private Long wageServiceUnit;//工资关系单位
	private Date startDateOfWage;//起薪时间
	private Integer serviceYears;//工龄
	private Integer collegeYearsNoService;//不计工龄大专以上学历年限
	private String isInUnionpay;//是否参与统发YHRS0003
	private String stopWageFlag;//工资发放标志YHRS0003
	private String remark;
	private String createdByCode;
	private String createdByName;
	private Date createdDate;
	private String updatedByCode;
	private String updatedByName; 
	private Date updatedDate;
	private Date teaNurseStartDate;
	
	public Long getPersonOid() {
		return personOid;
	}

	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}

	public Date getTeaNurseStartDate() {
		return teaNurseStartDate;
	}

	public void setTeaNurseStartDate(Date teaNurseStartDate) {
		this.teaNurseStartDate = teaNurseStartDate;
	}

	public PtWageInfluence(){
		
	}

	public Long getBizPersonOid() {
		return bizPersonOid;
	}

	public void setBizPersonOid(Long bizPersonOid) {
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

	public Integer getYearsServiceBroke() {
		return yearsServiceBroke;
	}

	public void setYearsServiceBroke(Integer yearsServiceBroke) {
		this.yearsServiceBroke = yearsServiceBroke;
	}

	public Integer getCollegeYearsNoService() {
		return collegeYearsNoService;
	}

	public void setCollegeYearsNoService(Integer collegeYearsNoService) {
		this.collegeYearsNoService = collegeYearsNoService;
	}

	public String getIsInUnionpay() {
		return isInUnionpay;
	}

	public void setIsInUnionpay(String isInUnionpay) {
		this.isInUnionpay = isInUnionpay;
	}

	public String getStopWageFlag() {
		return stopWageFlag;
	}

	public void setStopWageFlag(String stopWageFlag) {
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

}
