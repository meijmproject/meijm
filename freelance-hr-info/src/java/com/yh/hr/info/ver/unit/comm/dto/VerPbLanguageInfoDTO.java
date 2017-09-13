package com.yh.hr.info.ver.unit.comm.dto;

public class VerPbLanguageInfoDTO {

	/**
     *主键
     **/
	private java.lang.Long languageOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *语种名称YHRS0054
     **/
	private java.lang.String languageCode;
    /**
     *熟练程度YHRS0055
     **/
	private java.lang.String skillLevelCode;
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
* public JhcPbLanguageInfo(java.lang.Long languageOid) {
*  *       this.languageOid = languageOid;
**   }
**/
	public void setLanguageOid(java.lang.Long languageOid){
		this.languageOid = languageOid;
	}

	public java.lang.Long getLanguageOid(){
		return this.languageOid;
	}

	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}

	public void setLanguageCode(java.lang.String languageCode){
		this.languageCode = languageCode;
	}

	public java.lang.String getLanguageCode(){
		return this.languageCode;
	}

	public void setSkillLevelCode(java.lang.String skillLevelCode){
		this.skillLevelCode = skillLevelCode;
	}

	public java.lang.String getSkillLevelCode(){
		return this.skillLevelCode;
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
