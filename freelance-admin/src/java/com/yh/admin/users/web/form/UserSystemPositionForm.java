package com.yh.admin.users.web.form;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

public class UserSystemPositionForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8583436696218794256L;

	private Long	systemPositionOid;
	private String	userId;
	private Date	effectiveDate;
	private Date	expiredDate;
	
	private java.lang.String	effectiveDateStr;			// 生效日期
	private java.lang.String	expiredDateStr;			// 失效日期
	private java.lang.String	createdByCode;	//创建人ID
	private java.lang.String	createdByName;	//创建人姓名
	private java.util.Date		createdDate;	//创建时间
	private java.lang.String	updatedByCode;	//修改人ID
	private java.lang.String	updatedByName;	//修改人姓名
	private java.util.Date	updatedDate;	//修改时间
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
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
		if (effectiveDate != null) {
			this.effectiveDateStr = DateUtil.formatDate(effectiveDate);
		}
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
		if (expiredDate != null) {
			this.expiredDateStr = DateUtil.formatDate(expiredDate);
		}
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
	public java.lang.String getEffectiveDateStr() {
		return effectiveDateStr;
	}
	public void setEffectiveDateStr(java.lang.String effectiveDateStr) {
		this.effectiveDateStr = effectiveDateStr;
		if (StringUtils.isNotEmpty(effectiveDateStr)) {
			this.effectiveDate = DateUtil.parseDate(effectiveDateStr);
		}
	}
	public java.lang.String getExpiredDateStr() {
		return expiredDateStr;
	}
	public void setExpiredDateStr(java.lang.String expiredDateStr) {
		this.expiredDateStr = expiredDateStr;
		if (StringUtils.isNotEmpty(expiredDateStr)) {
			this.expiredDate = DateUtil.parseDate(expiredDateStr);
		}
	}
	
}
