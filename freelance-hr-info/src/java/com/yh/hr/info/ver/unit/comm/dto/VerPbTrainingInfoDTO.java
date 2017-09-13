package com.yh.hr.info.ver.unit.comm.dto;

public class VerPbTrainingInfoDTO {

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
    /**
     *培训终止日期
     **/
	private java.util.Date trainingEndDate;
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
    /**
     *备注
     **/
	private java.lang.String remark;
   
	
	/**
	 * 三年培训情况
	 */
	private java.lang.String pxqk;
	
	public java.lang.String getPxqk() {
		return pxqk;
	}

	public void setPxqk(java.lang.String pxqk) {
		this.pxqk = pxqk;
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
		this.trainingBeginDate = trainingBeginDate;
	}

	public java.util.Date getTrainingBeginDate(){
		return this.trainingBeginDate;
	}

	public void setTrainingEndDate(java.util.Date trainingEndDate){
		this.trainingEndDate = trainingEndDate;
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
		this.approveDate = approveDate;
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

	

}
