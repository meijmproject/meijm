/**
 * This file is generated by  code generation tool version 0.2 ^_^
 * Created at 2016-09-01
 **/
package com.yh.hr.component.worktop.bo;

import com.yh.platform.core.bo.BaseBo;

public class WbWorkbenchRef extends BaseBo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5397809195720139535L;
	/**
	 * 
	 */
	private java.lang.String refCode; //
	private java.lang.String refType; //
	private java.lang.String detailOid;
	private java.lang.String createdByCode; // CreatedByCode
	private java.lang.String createdByName; // CreatedByName
	private java.util.Date createdDate; // CreatedDate
	private java.lang.String updatedByCode; // UpdatedByCode
	private java.lang.String updatedByName; // UpdatedByName
	private java.util.Date updatedDate; // UpdatedDate

	public WbWorkbenchRef() {

	}

	public java.lang.String getRefCode() {
		return refCode;
	}

	public void setRefCode(java.lang.String refCode) {
		this.refCode = refCode;
	}

	public java.lang.String getRefType() {
		return refType;
	}

	public void setRefType(java.lang.String refType) {
		this.refType = refType;
	}

	public java.lang.String getDetailOid() {
		return detailOid;
	}

	public void setDetailOid(java.lang.String detailOid) {
		this.detailOid = detailOid;
	}

	public void setCreatedByCode(java.lang.String createdByCode) {
		this.createdByCode = createdByCode;
	}

	public java.lang.String getCreatedByCode() {
		return this.createdByCode;
	}

	public void setCreatedByName(java.lang.String createdByName) {
		this.createdByName = createdByName;
	}

	public java.lang.String getCreatedByName() {
		return this.createdByName;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.util.Date getCreatedDate() {
		return this.createdDate;
	}

	public void setUpdatedByCode(java.lang.String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}

	public java.lang.String getUpdatedByCode() {
		return this.updatedByCode;
	}

	public void setUpdatedByName(java.lang.String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public java.lang.String getUpdatedByName() {
		return this.updatedByName;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public java.util.Date getUpdatedDate() {
		return this.updatedDate;
	}

}
