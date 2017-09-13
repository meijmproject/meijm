package com.yh.hr.res.pt.dto;

import java.util.Date;
/**
 * @description 退役军人信息DTO
 * @author wuxq
 * @created 2016年11月21日
 * @version 1.0
 */
public class PtExserviceInfoDTO{

	private Long bizPersonOid;
    /**
     *入伍地YHRS0006
     **/
	private String militaryInPlace;
    /**
     *入伍日期
     **/
	private Date militaryInDate;
    /**
     *部队名称
     **/
	private String militaryName;
    /**
     *原部别
     **/
	private String formerOfficalLevel;
    /**
     *退役类别YHRS0067
     **/
	private String retireType;
    /**
     *批准退役日期
     **/
	private Date exserviceApprovalDate;
    /**
     *批准退役单位
     **/
	private String exserviceApprovalUnit;
    /**
     *退役前军队职务（军官职务)YHRS0066
     **/
	private String retireExserviceDuty;
    /**
     *退役前任军职时间
     **/
	private Date retireExserviceDate;
    /**
     *退役前军衔YHRS0056
     **/
	private String rankBeforeExserviceCode;
    /**
     *授军衔时间
     **/
	private Date rankGrantDate;
    /**
     *退役前军队职务级别YHRS0062
     **/
	private String levelBeforeExserviceCode;
    /**
     *退役前军队专业技术等级YHRS0065
     **/
	private String techLevelInMilitaryCode;
    /**
     *文职非专业技术等级YHRS0064
     **/
	private String profNotTechGrade;
    /**
     *文职专业技术等级YHRS0063
     **/
	private String specialityTechGrade;
    /**
     *任军队技术职务时间
     **/
	private Date profTechYears;
	/**
     *退役时职务工资
     **/
	private java.lang.Integer retirementDutyWage;
	 /**
     *退役时军衔工资
     **/
	private java.lang.Integer retirementRankWage;
	 /**
     *退役时军龄工资
     **/
	private java.lang.Integer retirementServiceWage;
    /**
     *CreatedByCode
     **/
	private String createdByCode;
    /**
     *CreatedByName
     **/
	private String createdByName;
    /**
     *CreatedDate
     **/
	private Date createdDate;
    /**
     *UpdatedByCode
     **/
	private String updatedByCode;
    /**
     *UpdatedByName
     **/
	private String updatedByName;
    /**
     *UpdatedDate
     **/
	private Date updatedDate;

	public PtExserviceInfoDTO() {
		
	}

	public Long getBizPersonOid() {
		return bizPersonOid;
	}

	public void setBizPersonOid(Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}

	public void setMilitaryInPlace(String militaryInPlace){
		this.militaryInPlace = militaryInPlace;
	}

	public String getMilitaryInPlace(){
		return this.militaryInPlace;
	}

	public void setMilitaryInDate(Date militaryInDate){
		this.militaryInDate = militaryInDate;
	}

	public Date getMilitaryInDate(){
		return this.militaryInDate;
	}

	public void setMilitaryName(String militaryName){
		this.militaryName = militaryName;
	}

	public String getMilitaryName(){
		return this.militaryName;
	}

	public void setFormerOfficalLevel(String formerOfficalLevel){
		this.formerOfficalLevel = formerOfficalLevel;
	}

	public String getFormerOfficalLevel(){
		return this.formerOfficalLevel;
	}

	public void setRetireType(String retireType){
		this.retireType = retireType;
	}

	public String getRetireType(){
		return this.retireType;
	}

	public void setExserviceApprovalDate(Date exserviceApprovalDate){
		this.exserviceApprovalDate = exserviceApprovalDate;
	}

	public Date getExserviceApprovalDate(){
		return this.exserviceApprovalDate;
	}

	public void setExserviceApprovalUnit(String exserviceApprovalUnit){
		this.exserviceApprovalUnit = exserviceApprovalUnit;
	}

	public String getExserviceApprovalUnit(){
		return this.exserviceApprovalUnit;
	}

	public void setRetireExserviceDuty(String retireExserviceDuty){
		this.retireExserviceDuty = retireExserviceDuty;
	}

	public String getRetireExserviceDuty(){
		return this.retireExserviceDuty;
	}

	public void setRetireExserviceDate(Date retireExserviceDate){
		this.retireExserviceDate = retireExserviceDate;
	}

	public Date getRetireExserviceDate(){
		return this.retireExserviceDate;
	}

	public void setRankBeforeExserviceCode(String rankBeforeExserviceCode){
		this.rankBeforeExserviceCode = rankBeforeExserviceCode;
	}

	public String getRankBeforeExserviceCode(){
		return this.rankBeforeExserviceCode;
	}

	public void setRankGrantDate(Date rankGrantDate){
		this.rankGrantDate = rankGrantDate;
	}

	public Date getRankGrantDate(){
		return this.rankGrantDate;
	}

	public void setLevelBeforeExserviceCode(String levelBeforeExserviceCode){
		this.levelBeforeExserviceCode = levelBeforeExserviceCode;
	}

	public String getLevelBeforeExserviceCode(){
		return this.levelBeforeExserviceCode;
	}

	public void setTechLevelInMilitaryCode(String techLevelInMilitaryCode){
		this.techLevelInMilitaryCode = techLevelInMilitaryCode;
	}

	public String getTechLevelInMilitaryCode(){
		return this.techLevelInMilitaryCode;
	}

	public void setProfNotTechGrade(String profNotTechGrade){
		this.profNotTechGrade = profNotTechGrade;
	}

	public String getProfNotTechGrade(){
		return this.profNotTechGrade;
	}

	public void setSpecialityTechGrade(String specialityTechGrade){
		this.specialityTechGrade = specialityTechGrade;
	}

	public String getSpecialityTechGrade(){
		return this.specialityTechGrade;
	}

	public void setProfTechYears(Date profTechYears){
		this.profTechYears = profTechYears;
	}

	public Date getProfTechYears(){
		return this.profTechYears;
	}


	public java.lang.Integer getRetirementDutyWage() {
		return retirementDutyWage;
	}

	public void setRetirementDutyWage(java.lang.Integer retirementDutyWage) {
		this.retirementDutyWage = retirementDutyWage;
	}

	public java.lang.Integer getRetirementRankWage() {
		return retirementRankWage;
	}

	public void setRetirementRankWage(java.lang.Integer retirementRankWage) {
		this.retirementRankWage = retirementRankWage;
	}

	public java.lang.Integer getRetirementServiceWage() {
		return retirementServiceWage;
	}

	public void setRetirementServiceWage(java.lang.Integer retirementServiceWage) {
		this.retirementServiceWage = retirementServiceWage;
	}

	public void setCreatedByCode(String createdByCode){
		this.createdByCode = createdByCode;
	}

	public String getCreatedByCode(){
		return this.createdByCode;
	}

	public void setCreatedByName(String createdByName){
		this.createdByName = createdByName;
	}

	public String getCreatedByName(){
		return this.createdByName;
	}

	public void setCreatedDate(Date createdDate){
		this.createdDate = createdDate;
	}

	public Date getCreatedDate(){
		return this.createdDate;
	}

	public void setUpdatedByCode(String updatedByCode){
		this.updatedByCode = updatedByCode;
	}

	public String getUpdatedByCode(){
		return this.updatedByCode;
	}

	public void setUpdatedByName(String updatedByName){
		this.updatedByName = updatedByName;
	}

	public String getUpdatedByName(){
		return this.updatedByName;
	}

	public void setUpdatedDate(Date updatedDate){
		this.updatedDate = updatedDate;
	}

	public Date getUpdatedDate(){
		return this.updatedDate;
	}

}
