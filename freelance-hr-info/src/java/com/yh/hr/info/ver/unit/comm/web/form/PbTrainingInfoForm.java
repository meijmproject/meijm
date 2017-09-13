package com.yh.hr.info.ver.unit.comm.web.form;

import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

public class PbTrainingInfoForm extends ValidatorForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7275471628717253504L;
	/**
     *主键
     **/
	private java.lang.Long trainingOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *教育培训性质YHRS0050
     **/
	private java.lang.String trainingKinkCode;
    /**
     *培训离岗状态YHRS0051
     **/
	private java.lang.String trainingStatus;
    /**
     *培训主办机构
     **/
	private java.lang.String organizedOrgCode;
    /**
     *从学单位名称
     **/
	private java.lang.String trainingUnitName;
    /**
     *从学单位类别YHRS0052
     **/
	private java.lang.String trainingUnitTypeCode;
    /**
     *培训类别YHRS0053
     **/
	private java.lang.String trainingType;
    /**
     *培训内容
     **/
	private java.lang.String trainingContent;
    /**
     *培训起始日期
     **/
	private java.util.Date trainingBeginDate;
	//培训起始日期转换
	private String trainingBeginDateStr;
    /**
     *培训终止日期
     **/
	private java.util.Date trainingEndDate;
	//培训终止日期转换
	private String trainingEndDateStr;
    /**
     *是否出国（出境）培训YHRS0003
     **/
	private java.lang.String isAbroadStatus;
    /**
     *是否列入主要经历YHRS0003
     **/
	private java.lang.String isMainExperience;
    /**
     *批准机构名称
     **/
	private java.lang.String approveOfficeName;
    /**
     *批准日期
     **/
	private java.util.Date approveDate;
	//批准日期转换
	private String approveDateStr;
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

	
/**
* public JhcPbTrainingInfo(java.lang.Long trainingOid) {
*  *       this.trainingOid = trainingOid;
**   }
**/
	public String getApproveDateStr() {
		return approveDateStr;
	}
	
	public void setApproveDateStr(String approveDateStr) {
		this.approveDateStr = approveDateStr;
		this.approveDate = DateUtil.parseDate(approveDateStr);
	}
	public String getTrainingBeginDateStr() {
		return trainingBeginDateStr;
	}
	
	public void setTrainingBeginDateStr(String trainingBeginDateStr) {
		this.trainingBeginDateStr = trainingBeginDateStr;
		this.trainingBeginDate = DateUtil.parseDate(trainingBeginDateStr);
	}
	public String getTrainingEndDateStr() {
		return trainingEndDateStr;
	}
	
	public void setTrainingEndDateStr(String trainingEndDateStr) {
		this.trainingEndDateStr = trainingEndDateStr;
		this.trainingEndDate = DateUtil.parseDate(trainingEndDateStr);
	}
	public void setTrainingOid(java.lang.Long trainingOid){
		this.trainingOid = trainingOid;
	}

	public java.lang.Long getTrainingOid(){
		return this.trainingOid;
	}

	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}

	public void setTrainingKinkCode(java.lang.String trainingKinkCode){
		this.trainingKinkCode = trainingKinkCode;
	}

	public java.lang.String getTrainingKinkCode(){
		return this.trainingKinkCode;
	}

	public void setTrainingStatus(java.lang.String trainingStatus){
		this.trainingStatus = trainingStatus;
	}

	public java.lang.String getTrainingStatus(){
		return this.trainingStatus;
	}

	public void setOrganizedOrgCode(java.lang.String organizedOrgCode){
		this.organizedOrgCode = organizedOrgCode;
	}

	public java.lang.String getOrganizedOrgCode(){
		return this.organizedOrgCode;
	}

	public void setTrainingUnitName(java.lang.String trainingUnitName){
		this.trainingUnitName = trainingUnitName;
	}

	public java.lang.String getTrainingUnitName(){
		return this.trainingUnitName;
	}

	public void setTrainingUnitTypeCode(java.lang.String trainingUnitTypeCode){
		this.trainingUnitTypeCode = trainingUnitTypeCode;
	}

	public java.lang.String getTrainingUnitTypeCode(){
		return this.trainingUnitTypeCode;
	}

	public void setTrainingType(java.lang.String trainingType){
		this.trainingType = trainingType;
	}

	public java.lang.String getTrainingType(){
		return this.trainingType;
	}

	public void setTrainingContent(java.lang.String trainingContent){
		this.trainingContent = trainingContent;
	}

	public java.lang.String getTrainingContent(){
		return this.trainingContent;
	}

	public void setTrainingBeginDate(java.util.Date trainingBeginDate){
		if(null!=trainingBeginDate){
		this.trainingBeginDate = trainingBeginDate;
		this.trainingBeginDateStr = DateUtil.formatDate(trainingBeginDate);
		}
	}

	public java.util.Date getTrainingBeginDate(){
		return this.trainingBeginDate;
	}

	

	public void setTrainingEndDate(java.util.Date trainingEndDate){
		if(null!=trainingEndDate){
			this.trainingEndDate = trainingEndDate;
			this.trainingEndDateStr = DateUtil.formatDate(trainingEndDate);
		}
	}
	public java.util.Date getTrainingEndDate(){
		return this.trainingEndDate;
	}

	public void setIsAbroadStatus(java.lang.String isAbroadStatus){
		this.isAbroadStatus = isAbroadStatus;
	}

	public java.lang.String getIsAbroadStatus(){
		return this.isAbroadStatus;
	}

	public void setIsMainExperience(java.lang.String isMainExperience){
		this.isMainExperience = isMainExperience;
	}

	public java.lang.String getIsMainExperience(){
		return this.isMainExperience;
	}

	public void setApproveOfficeName(java.lang.String approveOfficeName){
		this.approveOfficeName = approveOfficeName;
	}

	public java.lang.String getApproveOfficeName(){
		return this.approveOfficeName;
	}

	public void setApproveDate(java.util.Date approveDate){
		if (null != approveDate) {
			this.approveDate = approveDate;
			this.approveDateStr = DateUtil.formatDate(approveDate);
		}
	}

	public java.util.Date getApproveDate(){
		return this.approveDate;
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
}
