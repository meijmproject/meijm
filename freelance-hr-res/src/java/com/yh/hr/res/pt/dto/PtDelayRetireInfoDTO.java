package com.yh.hr.res.pt.dto;

/*
 * 延迟退休信息
 * huanglm
 */
public class PtDelayRetireInfoDTO {

	private java.lang.Long bizPersonOid;//bizPersonOid
	private java.lang.Long personOid;//personOid
	private java.util.Date originalRetrieDate;//原退休日期YHRS0057
	private java.util.Date delayretireDate;//延迟退休日期
	private java.lang.String originalRetrieDateStr;//原退休日期YHRS0057	
	private java.lang.String delayretireDateStr;//延迟退休日期
	private java.lang.String delayretireFileno;//延迟退休文号
	private java.lang.String delayretireCause;//延迟退休原因
	private java.lang.String remark;//备注
		
	private java.lang.String createdByCode;//CreatedByCode
	private java.lang.String createdByName;//CreatedByName
	private java.util.Date createdDate;//CreatedDate
	private java.lang.String updatedByCode;//UpdatedByCode
	private java.lang.String updatedByName;//UpdatedByName
	private java.util.Date updatedDate;//UpdatedDate
	
	
	public java.lang.String getOriginalRetrieDateStr() {
		return originalRetrieDateStr;
	}
	public void setOriginalRetrieDateStr(java.lang.String originalRetrieDateStr) {
		this.originalRetrieDateStr = originalRetrieDateStr;
	}
	public java.lang.String getDelayretireDateStr() {
		return delayretireDateStr;
	}
	public void setDelayretireDateStr(java.lang.String delayretireDateStr) {
		this.delayretireDateStr = delayretireDateStr;
	}
	public java.lang.Long getBizPersonOid() {
		return bizPersonOid;
	}
	public void setBizPersonOid(java.lang.Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}
	public java.lang.Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(java.lang.Long personOid) {
		this.personOid = personOid;
	}
	public java.util.Date getOriginalRetrieDate() {
		return originalRetrieDate;
	}
	public void setOriginalRetrieDate(java.util.Date originalRetrieDate) {
		this.originalRetrieDate = originalRetrieDate;
	}
	public java.util.Date getDelayretireDate() {
		return delayretireDate;
	}
	public void setDelayretireDate(java.util.Date delayretireDate) {
		this.delayretireDate = delayretireDate;
	}
	public java.lang.String getDelayretireFileno() {
		return delayretireFileno;
	}
	public void setDelayretireFileno(java.lang.String delayretireFileno) {
		this.delayretireFileno = delayretireFileno;
	}
	public java.lang.String getDelayretireCause() {
		return delayretireCause;
	}
	public void setDelayretireCause(java.lang.String delayretireCause) {
		this.delayretireCause = delayretireCause;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
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
	
     
}
