package com.yh.hr.res.cf.dto;

import java.util.Date;

public class JhcCfShowFieldOrderDTO {

	private Long fieldOrderOid;
	private String userId;
	private Long resultOid;
	private Long fieldOrder;
	private String createdByCode;
	private String createdByName;
	private Date createdDate;
	private String updatedByCode;
	private String updatedByName;
	private Date updatedDate;
	private String isShow;
	public Long getFieldOrderOid() {
		return fieldOrderOid;
	}
	public void setFieldOrderOid(Long fieldOrderOid) {
		this.fieldOrderOid = fieldOrderOid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getResultOid() {
		return resultOid;
	}
	public void setResultOid(Long resultOid) {
		this.resultOid = resultOid;
	}
	public Long getFieldOrder() {
		return fieldOrder;
	}
	public void setFieldOrder(Long fieldOrder) {
		this.fieldOrder = fieldOrder;
	}
	public String getCreatedByCode() {
		return createdByCode;
	}
	public void setCreatedByCode(String createdByCode) {
		this.createdByCode = createdByCode;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedByCode() {
		return updatedByCode;
	}
	public void setUpdatedByCode(String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}
	public String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
}
