package com.yh.admin.dto;

import java.util.Date;

public class UserSystemPositionDTO {

	private Long systemPositionOid;
	private String userId;
	private Date effectiveDate;
	private Date expiredDate;
	
	private java.lang.String	createdByCode;	//创建人ID
	private java.lang.String	createdByName;	//创建人姓名
	private java.util.Date	createdDate;	//创建时间
	private java.lang.String	updatedByCode;	//修改人ID
	private java.lang.String	updatedByName;	//修改人姓名
	private java.util.Date	updatedDate;	//修改时间
	
	private String systemPositionName;
	private String functionRoleName;
	private String dataRoleName;
	
	
	public String getSystemPositionName() {
		return systemPositionName;
	}

	public void setSystemPositionName(String systemPositionName) {
		this.systemPositionName = systemPositionName;
	}

	public String getFunctionRoleName() {
		return functionRoleName;
	}

	public void setFunctionRoleName(String functionRoleName) {
		this.functionRoleName = functionRoleName;
	}

	public String getDataRoleName() {
		return dataRoleName;
	}

	public void setDataRoleName(String dataRoleName) {
		this.dataRoleName = dataRoleName;
	}

	public String getSystemPositionDesc() {
		return systemPositionDesc;
	}

	public void setSystemPositionDesc(String systemPositionDesc) {
		this.systemPositionDesc = systemPositionDesc;
	}

	private String systemPositionDesc;

	public Long getSystemPositionOid() {
		return systemPositionOid;
	}

	public void setSystemPositionOid(Long systemPositionOid) {
		this.systemPositionOid = systemPositionOid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
}
