package com.yh.hr.info.ver.unit.comm.web.form;

import com.yh.platform.core.util.DateUtil;
import org.apache.struts.validator.ValidatorForm;


public class VerPbProfessionalInfoForm extends ValidatorForm{
	
	private static final long serialVersionUID = -6754026563170361750L;
	/**
     *主键
     **/
	private java.lang.Long professionalOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *职业资格证书编号
     **/
	private java.lang.String certificateNo;
    /**
     *职业资格名称
     **/
	private java.lang.String qualificationsName;
    /**
     *职业资格等级YHRS0049
     **/
	private java.lang.String qualificationsLevelCode;
    /**
     *职业（工种）名称YHRS0031
     **/
	private java.lang.String skillWorkCode;
    /**
     *取得职业资格的日期
     **/
	private java.util.Date procureDate;
	
	private String procureDateStr;
    /**
     *资格截止日期
     **/
	private java.util.Date validityDate;
	
	private String validityDateStr;
    /**
     *取得资格途径
     **/
	private java.lang.String acquireApproachCode;
    /**
     *其他取得途径
     **/
	private java.lang.String acquireApproachOther;
    /**
     *职业资格证书核发单位名称
     **/
	private java.lang.String issueOrgan;
    /**
     *是否本人当前最高技术工人等级YHRS0003
     **/
	private java.lang.String isHighestLevel;
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

	public void setProfessionalOid(java.lang.Long professionalOid){
		this.professionalOid = professionalOid;
	}

	public java.lang.Long getProfessionalOid(){
		return this.professionalOid;
	}

	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}

	public void setCertificateNo(java.lang.String certificateNo){
		this.certificateNo = certificateNo;
	}

	public java.lang.String getCertificateNo(){
		return this.certificateNo;
	}

	public void setQualificationsName(java.lang.String qualificationsName){
		this.qualificationsName = qualificationsName;
	}

	public java.lang.String getQualificationsName(){
		return this.qualificationsName;
	}

	public void setQualificationsLevelCode(java.lang.String qualificationsLevelCode){
		this.qualificationsLevelCode = qualificationsLevelCode;
	}

	public java.lang.String getQualificationsLevelCode(){
		return this.qualificationsLevelCode;
	}

	public void setSkillWorkCode(java.lang.String skillWorkCode){
		this.skillWorkCode = skillWorkCode;
	}

	public java.lang.String getSkillWorkCode(){
		return this.skillWorkCode;
	}

	public void setProcureDate(java.util.Date procureDate){
		if (null != procureDate) {
			this.procureDate = procureDate;
			this.procureDateStr = DateUtil.formatDate(procureDate);
				}
	}

	public java.util.Date getProcureDate(){
		return this.procureDate;
	}

	public String getProcureDateStr() {
		return procureDateStr;
	}

	public void setProcureDateStr(String procureDateStr) {
		if (procureDateStr != null && !"".equals(procureDateStr.trim())) {
			this.procureDateStr = procureDateStr.trim();
			this.procureDate = DateUtil.parseDate(procureDateStr.trim());
				}
	}

	public void setValidityDate(java.util.Date validityDate){
		if (null != validityDate) {
			this.validityDate = validityDate;
			this.validityDateStr = DateUtil.formatDate(validityDate);
				}
	}

	public java.util.Date getValidityDate(){
		return this.validityDate;
	}

	public String getValidityDateStr() {
		return validityDateStr;
	}

	public void setValidityDateStr(String validityDateStr) {
		if (validityDateStr != null && !"".equals(validityDateStr.trim())) {
			this.validityDateStr = validityDateStr.trim();
			this.validityDate = DateUtil.parseDate(validityDateStr.trim());
				}
	}

	public void setAcquireApproachCode(java.lang.String acquireApproachCode){
		this.acquireApproachCode = acquireApproachCode;
	}

	public java.lang.String getAcquireApproachCode(){
		return this.acquireApproachCode;
	}

	public void setAcquireApproachOther(java.lang.String acquireApproachOther){
		this.acquireApproachOther = acquireApproachOther;
	}

	public java.lang.String getAcquireApproachOther(){
		return this.acquireApproachOther;
	}

	public void setIssueOrgan(java.lang.String issueOrgan){
		this.issueOrgan = issueOrgan;
	}

	public java.lang.String getIssueOrgan(){
		return this.issueOrgan;
	}

	public void setIsHighestLevel(java.lang.String isHighestLevel){
		this.isHighestLevel = isHighestLevel;
	}

	public java.lang.String getIsHighestLevel(){
		return this.isHighestLevel;
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