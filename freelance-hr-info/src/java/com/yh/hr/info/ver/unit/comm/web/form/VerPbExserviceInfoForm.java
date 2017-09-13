package com.yh.hr.info.ver.unit.comm.web.form;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;
/**
 * 退役军人信息Form
 * @author zhengdr
 *
 * 时间:2016-8-15下午04:53:24
 */
public class VerPbExserviceInfoForm extends ValidatorForm{
	private static final long serialVersionUID = 1163739724520444678L;
	/**
     *PersonOid
     **/
	private Long personOid;
    /**
     *入伍地YHRS0006
     **/
	private String militaryInPlace;
    /**
     *入伍日期
     **/
	private Date militaryInDate;
	private String	militaryInDateStr;
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
	private String	exserviceApprovalDateStr;
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
	private String retireExserviceDateStr;
    /**
     *退役前军衔YHRS0056
     **/
	private String rankBeforeExserviceCode;
    /**
     *授军衔时间
     **/
	private Date rankGrantDate;
	private String rankGrantDateStr;
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
	private String profTechYearsStr;
    /**
     *退役时职务工资档次
     **/
	private Integer retirementWageLevel;
   /* *//**
     *CreatedByCode
     **//*
	private String createdByCode;
    *//**
     *CreatedByName
     **//*
	private String createdByName;
    *//**
     *CreatedDate
     **//*
	private Date createdDate;
    *//**
     *UpdatedByCode
     **//*
	private String updatedByCode;
    *//**
     *UpdatedByName
     **//*
	private String updatedByName;
    *//**
     *UpdatedDate
     **//*
	private Date updatedDate;*/

	public VerPbExserviceInfoForm() {
		
	}
/**
* public JhcPbExserviceInfo(Long personOid) {
*  *       this.personOid = personOid;
**   }
**/
	public void setPersonOid(Long personOid){
		this.personOid = personOid;
	}

	public Long getPersonOid(){
		return this.personOid;
	}

	public void setMilitaryInPlace(String militaryInPlace){
		this.militaryInPlace = militaryInPlace;
	}

	public String getMilitaryInPlace(){
		return this.militaryInPlace;
	}

	public void setMilitaryInDate(Date militaryInDate){
		if (null != militaryInDate) {
			this.militaryInDate = militaryInDate;
			this.militaryInDateStr = DateUtil.formatDate(militaryInDate);
		}
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
		if (null != exserviceApprovalDate) {
			this.exserviceApprovalDate = exserviceApprovalDate;
			this.exserviceApprovalDateStr = DateUtil.formatDate(exserviceApprovalDate);
		}
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
		if (null != retireExserviceDate) {
			this.retireExserviceDate = retireExserviceDate;
			this.retireExserviceDateStr = DateUtil.formatDate(retireExserviceDate);
		}
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
		if (null != rankGrantDate) {
			this.rankGrantDate = rankGrantDate;
			this.rankGrantDateStr = DateUtil.formatDate(rankGrantDate);
		}
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
		if (null != profTechYears) {
			this.profTechYears = profTechYears;
			this.profTechYearsStr = DateUtil.formatDate(profTechYears);
		}
	}

	public Date getProfTechYears(){
		return this.profTechYears;
	}

	public void setRetirementWageLevel(Integer retirementWageLevel){
		this.retirementWageLevel = retirementWageLevel;
	}

	public Integer getRetirementWageLevel(){
		return this.retirementWageLevel;
	}
	
	public String getMilitaryInDateStr() {
		return militaryInDateStr;
	}

	public void setMilitaryInDateStr(String militaryInDateStr) {
		this.militaryInDateStr = militaryInDateStr;
		this.militaryInDate = DateUtil.parseDate(militaryInDateStr);
	}

	public String getExserviceApprovalDateStr() {
		return exserviceApprovalDateStr;
	}

	public void setExserviceApprovalDateStr(String exserviceApprovalDateStr) {
		this.exserviceApprovalDateStr = exserviceApprovalDateStr;
		this.exserviceApprovalDate = DateUtil.parseDate(exserviceApprovalDateStr);
	}

	public String getRetireExserviceDateStr() {
		return retireExserviceDateStr;
	}

	public void setRetireExserviceDateStr(String retireExserviceDateStr) {
		this.retireExserviceDateStr = retireExserviceDateStr;
		this.retireExserviceDate = DateUtil.parseDate(retireExserviceDateStr);
	}

	public String getRankGrantDateStr() {
		return rankGrantDateStr;
	}

	public void setRankGrantDateStr(String rankGrantDateStr) {
		this.rankGrantDateStr = rankGrantDateStr;
		this.rankGrantDate = DateUtil.parseDate(rankGrantDateStr);

	}

	public String getProfTechYearsStr() {
		return profTechYearsStr;
	}

	public void setProfTechYearsStr(String profTechYearsStr) {
		this.profTechYearsStr = profTechYearsStr;
		this.profTechYears = DateUtil.parseDate(profTechYearsStr);
	}

	
/*	public void setCreatedByCode(String createdByCode){
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
	}*/

}
