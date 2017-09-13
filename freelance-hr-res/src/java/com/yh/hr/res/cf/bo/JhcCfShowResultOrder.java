package com.yh.hr.res.cf.bo;

import java.util.Date;

import com.yh.platform.core.bo.BaseBo;

public class JhcCfShowResultOrder extends BaseBo {

	private static final long serialVersionUID = 19876543599876L;

	private Long resultOrderOid;
	private String userId;
	private Long resultOid;
	private String resultOrder;
	private String createdByCode;
	private String createdByName;
	private Date createdDate;
	private String updatedByCode;
	private String updatedByName;
	private Date updatedDate;
	private Long fieldOrder;
	public Long getResultOrderOid() {
		return resultOrderOid;
	}
	public void setResultOrderOid(Long resultOrderOid) {
		this.resultOrderOid = resultOrderOid;
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
	public String getResultOrder() {
		return resultOrder;
	}
	public void setResultOrder(String resultOrder) {
		this.resultOrder = resultOrder;
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
	public Long getFieldOrder() {
		return fieldOrder;
	}
	public void setFieldOrder(Long fieldOrder) {
		this.fieldOrder = fieldOrder;
	}
}
