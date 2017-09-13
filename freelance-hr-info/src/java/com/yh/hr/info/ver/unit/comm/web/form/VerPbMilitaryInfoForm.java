package com.yh.hr.info.ver.unit.comm.web.form;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;
/**
 * 军队任职历史Form
 * @author zhengdr
 *
 * 时间:2016-8-15下午03:41:43
 */
public class VerPbMilitaryInfoForm extends ValidatorForm{
	private static final long serialVersionUID = 8189843299057684574L;
	/**
     *主键
     **/
	private Long militaryOid;
    /**
     *PersonOid
     **/
	private Long personOid;
    /**
     *军队职务YHRS0066
     **/
	private String militaryDuty;
    /**
     *军队职级YHRS0062
     **/
	private String militaryDutyGrade;
    /**
     *军队文职专业技术级别YHRS0063
     **/
	private String militaryTechCivilLevel;
    /**
     *军队非专业技术文职级别YHRS0064
     **/
	private String militaryBontechCivilLevel;
    /**
     *军队专业技术等级YHRS0065
     **/
	private String militaryTechGrade;
    /**
     *任军职时间
     **/
	private Date seviceBeginDate;
	private String seviceBeginDateStr;
    /**
     *任军职结束时间
     **/
	private Date serveEndDate;
    private String serveEndDateStr;
    /**
     *是否领导职务YHRS0003
     **/
	private String isLeadershPosition;
    /**
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

	public VerPbMilitaryInfoForm() {
		
	}
/**
* public JhcPbMilitaryInfo(""Long militaryOid) {
*  *       this.militaryOid = militaryOid;
**   }
**/
	public void setMilitaryOid(Long militaryOid){
		this.militaryOid = militaryOid;
	}

	public Long getMilitaryOid(){
		return this.militaryOid;
	}

	public void setPersonOid(Long personOid){
		this.personOid = personOid;
	}

	public Long getPersonOid(){
		return this.personOid;
	}

	public void setMilitaryDuty(String militaryDuty){
		this.militaryDuty = militaryDuty;
	}

	public String getMilitaryDuty(){
		return this.militaryDuty;
	}

	public void setMilitaryDutyGrade(String militaryDutyGrade){
		this.militaryDutyGrade = militaryDutyGrade;
	}

	public String getMilitaryDutyGrade(){
		return this.militaryDutyGrade;
	}

	public void setMilitaryTechCivilLevel(String militaryTechCivilLevel){
		this.militaryTechCivilLevel = militaryTechCivilLevel;
	}

	public String getMilitaryTechCivilLevel(){
		return this.militaryTechCivilLevel;
	}

	public void setMilitaryBontechCivilLevel(String militaryBontechCivilLevel){
		this.militaryBontechCivilLevel = militaryBontechCivilLevel;
	}

	public String getMilitaryBontechCivilLevel(){
		return this.militaryBontechCivilLevel;
	}

	public void setMilitaryTechGrade(String militaryTechGrade){
		this.militaryTechGrade = militaryTechGrade;
	}

	public String getMilitaryTechGrade(){
		return this.militaryTechGrade;
	}

	public void setSeviceBeginDate(Date seviceBeginDate){
		if (null != seviceBeginDate) {
			this.seviceBeginDate = seviceBeginDate;
			this.seviceBeginDateStr = DateUtil.formatDate(seviceBeginDate);
		}
	}

	public Date getSeviceBeginDate(){
		return this.seviceBeginDate;
	}

    //格式化
	public void setServeEndDate(Date serveEndDate){
		if (null != serveEndDate) {
			this.serveEndDate = serveEndDate;
			this.serveEndDateStr = DateUtil.formatDate(serveEndDate);
		}
	}

	public Date getServeEndDate(){
		return this.serveEndDate;
	}

	public void setIsLeadershPosition(String isLeadershPosition){
		this.isLeadershPosition = isLeadershPosition;
	}

	public String getIsLeadershPosition(){
		return this.isLeadershPosition;
	}
	public String getSeviceBeginDateStr() {
		return seviceBeginDateStr;
	}
	public void setSeviceBeginDateStr(String seviceBeginDateStr) {
		
		this.seviceBeginDateStr = seviceBeginDateStr;
		this.seviceBeginDate = DateUtil.parseDate(seviceBeginDateStr);
	}
	public String getServeEndDateStr() {
		return serveEndDateStr;
	}
	public void setServeEndDateStr(String serveEndDateStr) {
		this.serveEndDateStr = serveEndDateStr;
		this.serveEndDate = DateUtil.parseDate(serveEndDateStr);
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

	public void setCreatedDate(java.util.Date createdDate){
		this.createdDate = createdDate;
	}

	public java.util.Date getCreatedDate(){
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

	public void setUpdatedDate(java.util.Date updatedDate){
		this.updatedDate = updatedDate;
	}

	public java.util.Date getUpdatedDate(){
		return this.updatedDate;
	}*/

	
}
