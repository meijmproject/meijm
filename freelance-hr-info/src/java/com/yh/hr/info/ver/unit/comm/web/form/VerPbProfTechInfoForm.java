/**
 * This file is generated by  code generation tool version 0.2 ^_^
 * Created at 2016-08-15
**/
package com.yh.hr.info.ver.unit.comm.web.form;

import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

public class VerPbProfTechInfoForm extends ValidatorForm {
	
	private static final long serialVersionUID = 7253124853841718506L;
	/**
     *主键
     **/
	private java.lang.Long profTechOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *资格证书编号
     **/
	private java.lang.String certificateNo;
    /**
     *专业技术资格名称
     **/
	private java.lang.String profTechName;
    /**
     *专业技术资格等级YHRS0047
     **/
	private java.lang.String profTechLevel;
    /**
     *专业名称
     **/
	private java.lang.String specialityName;
    /**
     *取得资格日期
     **/
	private java.util.Date procureDate;
	
	private String procureDateStr;
    /**
     *资格截止日期
     **/
	private java.util.Date validityDate;
	
	private String validityDateStr;
    /**
     *取得资格途径YHRS0048
     **/
	private java.lang.String acquireApproachCode;
    /**
     *其他取得途径
     **/
	private java.lang.String acquireApproachOther;
    /**
     *发证机构名称
     **/
	private java.lang.String issueOrgan;
    /**
     *是否经过本省/市人力资源（人事）部门核准或认定YHRS0003
     **/
	private java.lang.String isApprove;
    /**
     *资格审批机构名称
     **/
	private java.lang.String approveOrganName;
    /**
     *是否当前最高专业技术资格等级YHRS0003
     **/
	private java.lang.String isHighestLevel;

	public void setProfTechOid(java.lang.Long profTechOid){
		this.profTechOid = profTechOid;
	}

	public java.lang.Long getProfTechOid(){
		return this.profTechOid;
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

	public void setProfTechName(java.lang.String profTechName){
		this.profTechName = profTechName;
	}

	public java.lang.String getProfTechName(){
		return this.profTechName;
	}

	public void setProfTechLevel(java.lang.String profTechLevel){
		this.profTechLevel = profTechLevel;
	}

	public java.lang.String getProfTechLevel(){
		return this.profTechLevel;
	}

	public void setSpecialityName(java.lang.String specialityName){
		this.specialityName = specialityName;
	}

	public java.lang.String getSpecialityName(){
		return this.specialityName;
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

	public void setIsApprove(java.lang.String isApprove){
		this.isApprove = isApprove;
	}

	public java.lang.String getIsApprove(){
		return this.isApprove;
	}

	public void setApproveOrganName(java.lang.String approveOrganName){
		this.approveOrganName = approveOrganName;
	}

	public java.lang.String getApproveOrganName(){
		return this.approveOrganName;
	}

	public void setIsHighestLevel(java.lang.String isHighestLevel){
		this.isHighestLevel = isHighestLevel;
	}

	public java.lang.String getIsHighestLevel(){
		return this.isHighestLevel;
	}

}
