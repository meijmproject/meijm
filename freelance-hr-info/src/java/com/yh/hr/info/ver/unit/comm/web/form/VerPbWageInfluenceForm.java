package com.yh.hr.info.ver.unit.comm.web.form;

import java.text.ParseException;

import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

/**
 * 人员影响工资信息Form
 * @author went
 *
 */
public class VerPbWageInfluenceForm extends ValidatorForm{

	private static final long serialVersionUID = -1788255895263413431L;
	/**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *参加工作时间
     **/
	private java.util.Date startWorkDate;
	private String startWorkDateStr;
    /**
     *参加工作转正时间
     **/
	private java.util.Date passProbationDate;
	private String passProbationDateStr;
    /**
     *工龄起算时间
     **/
	private java.util.Date wageStart;
	private String wageStartStr;
	/**
     *工龄间断年限
     **/
	private java.lang.Integer yearsServiceBroke;
    /**
     *不计工龄大专以上学历年限
     **/
	private java.lang.Integer collegeYearsNoService;
    /**
     *是否参与统发YHRS0003
     **/
	private java.lang.String isInUnionpay;
    /**
     *工资发放标志YHRS0003
     **/
	private java.lang.String stopWageFlag;
    /**
     *工资关系单位
     **/
	private java.lang.Long wageServiceUnit;
	private String wageServiceUnitName;
	
	/**
     *备注
     **/
	private java.lang.String remark;
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
	
	private Integer serviceYears;// 工龄
	private String unitKind;//工资关系单位性质YHRS0090
	private String budgetFormCide;//工资关系单位经费来源
	
	
	public String getWageServiceUnitName() {
		return wageServiceUnitName;
	}
	public void setWageServiceUnitName(String wageServiceUnitName) {
		this.wageServiceUnitName = wageServiceUnitName;
	}
	public java.lang.Long getWageServiceUnit() {
		return wageServiceUnit;
	}
	public void setWageServiceUnit(java.lang.Long wageServiceUnit) {
		this.wageServiceUnit = wageServiceUnit;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public java.util.Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}
	public java.lang.String getUpdatedByCode() {
		return updatedByCode;
	}
	public void setUpdatedByCode(java.lang.String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}
	public java.lang.String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(java.lang.String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getStartWorkDateStr() {
		return startWorkDateStr;
	}
	public void setStartWorkDateStr(String startWorkDateStr) throws ParseException {
		
		this.startWorkDateStr = startWorkDateStr;
		this.startWorkDate = DateUtil.parseDate(startWorkDateStr);
	}
	public String getPassProbationDateStr() {
		return passProbationDateStr;
	}
	public void setPassProbationDateStr(String passProbationDateStr) throws ParseException {
		this.passProbationDateStr = passProbationDateStr;
		this.passProbationDate = DateUtil.parseDate(passProbationDateStr);
	}
    public String getWageStartStr() {
		return wageStartStr;
	}
	public void setWageStartStr(String wageStartStr) throws ParseException {
		this.wageStartStr = wageStartStr;
		this.wageStart = DateUtil.parseDate(wageStartStr);
	}
    public java.lang.String getCreatedByCode() {
		return createdByCode;
	}
	public void setCreatedByCode(java.lang.String createdByCode) {
		this.createdByCode = createdByCode;
	}
	public java.lang.String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(java.lang.String createdByName) {
		this.createdByName = createdByName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

/**
* public JhcPbWInfluence(java.lang.Long personOid) {
*  *       this.personOid = personOid;
**   }
**/
	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}


	public java.util.Date getStartWorkDate(){
		return this.startWorkDate;
	}


	
	


	public java.util.Date getPassProbationDate(){
		return this.passProbationDate;
	}



	public java.util.Date getWageStart(){
		return this.wageStart;
	}

	public void setYearsServiceBroke(java.lang.Integer yearsServiceBroke){
		this.yearsServiceBroke = yearsServiceBroke;
	}

	public void setStartWorkDate(java.util.Date startWorkDate) {
		if (null != startWorkDate) {
			this.startWorkDate = startWorkDate;
			this.startWorkDateStr = DateUtil.formatDate(startWorkDate);
		}
	}
	
	
	public void setPassProbationDate(java.util.Date passProbationDate) {
		if (null != passProbationDate) {
			this.passProbationDate = passProbationDate;
			this.passProbationDateStr = DateUtil.formatDate(passProbationDate);
		}
	}
	public void setWageStart(java.util.Date wageStart) {
		if (null != wageStart) {
			this.wageStart = wageStart;
			this.wageStartStr = DateUtil.formatDate(wageStart);
		}
	}
	public java.lang.Integer getYearsServiceBroke(){
		return this.yearsServiceBroke;
	}

	public void setCollegeYearsNoService(java.lang.Integer collegeYearsNoService){
		this.collegeYearsNoService = collegeYearsNoService;
	}

	public java.lang.Integer getCollegeYearsNoService(){
		return this.collegeYearsNoService;
	}

	public void setIsInUnionpay(java.lang.String isInUnionpay){
		this.isInUnionpay = isInUnionpay;
	}

	public java.lang.String getIsInUnionpay(){
		return this.isInUnionpay;
	}

	public void setStopWageFlag(java.lang.String stopWageFlag){
		this.stopWageFlag = stopWageFlag;
	}

	public java.lang.String getStopWageFlag(){
		return this.stopWageFlag;
	}
	
	public Integer getServiceYears() {
		return serviceYears;
	}
	public void setServiceYears(Integer serviceYears) {
		this.serviceYears = serviceYears;
	}
	public String getUnitKind() {
		return unitKind;
	}
	public void setUnitKind(String unitKind) {
		this.unitKind = unitKind;
	}
	public String getBudgetFormCide() {
		return budgetFormCide;
	}
	public void setBudgetFormCide(String budgetFormCide) {
		this.budgetFormCide = budgetFormCide;
	}
	
}
