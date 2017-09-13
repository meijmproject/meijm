/**
 * This file is generated by  code generation tool version 0.2 ^_^
 * Created at 2016-11-04
 **/
package com.yh.hr.res.pt.bo;

import com.yh.platform.core.bo.BaseBo;

public class PtPersonExchange extends BaseBo {
	private static final long serialVersionUID = -5528968832044028667L;

	private java.lang.Long ptPersonExchangeOid; // PtPersonExchangeOid
	private java.lang.Long bizPersonOid; // BizPersonOid
	private java.lang.String preAuthStatusCode; // 前置授权状态
	private java.lang.String authStatusCode; // 当前授权状态
	private java.lang.Long unitOid; // 现任单位ID
	private java.lang.String unitName; // 现任单位名称
	private java.lang.Long adminUnitOid; // 现任主管单位ID
	private java.lang.String adminUnitName; // 现任主管单位ID
	private java.lang.Long exchangeUnitOid; // 交流的单位ID
	private java.lang.String exchangeUnitName; // 交流的单位名称
	private java.lang.Long exchangeAdminUnitOid; // 交流的主管单位ID
	private java.lang.String exchangeAdminUnitName; // 交流的主管单位名称
	private java.lang.String exchangeType; // 交流类型
	private java.lang.Long prePersonOid; // 原基础人员ID
	private java.lang.String createdByCode; // CreatedByCode
	private java.lang.String createdByName; // CreatedByName
	private java.util.Date createdDate; // CreatedDate
	private java.lang.String updatedByCode; // UpdatedByCode
	private java.lang.String updatedByName; // UpdatedByName
	private java.util.Date updatedDate; // UpdatedDate
	private java.lang.String itemCode; // 业务事项
	private java.lang.String remark;

	public PtPersonExchange() {

	}

	public void setPtPersonExchangeOid(java.lang.Long ptPersonExchangeOid) {
		this.ptPersonExchangeOid = ptPersonExchangeOid;
	}

	public java.lang.Long getPtPersonExchangeOid() {
		return this.ptPersonExchangeOid;
	}

	public void setBizPersonOid(java.lang.Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}

	public java.lang.Long getBizPersonOid() {
		return this.bizPersonOid;
	}

	public void setPreAuthStatusCode(java.lang.String preAuthStatusCode) {
		this.preAuthStatusCode = preAuthStatusCode;
	}

	public java.lang.String getPreAuthStatusCode() {
		return this.preAuthStatusCode;
	}

	public void setAuthStatusCode(java.lang.String authStatusCode) {
		this.authStatusCode = authStatusCode;
	}

	public java.lang.String getAuthStatusCode() {
		return this.authStatusCode;
	}

	public void setUnitOid(java.lang.Long unitOid) {
		this.unitOid = unitOid;
	}

	public java.lang.Long getUnitOid() {
		return this.unitOid;
	}

	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}

	public java.lang.String getUnitName() {
		return this.unitName;
	}

	public void setAdminUnitOid(java.lang.Long adminUnitOid) {
		this.adminUnitOid = adminUnitOid;
	}

	public java.lang.Long getAdminUnitOid() {
		return this.adminUnitOid;
	}

	public void setAdminUnitName(java.lang.String adminUnitName) {
		this.adminUnitName = adminUnitName;
	}

	public java.lang.String getAdminUnitName() {
		return this.adminUnitName;
	}

	public void setExchangeUnitOid(java.lang.Long exchangeUnitOid) {
		this.exchangeUnitOid = exchangeUnitOid;
	}

	public java.lang.Long getExchangeUnitOid() {
		return this.exchangeUnitOid;
	}

	public void setExchangeUnitName(java.lang.String exchangeUnitName) {
		this.exchangeUnitName = exchangeUnitName;
	}

	public java.lang.String getExchangeUnitName() {
		return this.exchangeUnitName;
	}

	public void setExchangeAdminUnitOid(java.lang.Long exchangeAdminUnitOid) {
		this.exchangeAdminUnitOid = exchangeAdminUnitOid;
	}

	public java.lang.Long getExchangeAdminUnitOid() {
		return this.exchangeAdminUnitOid;
	}

	public void setExchangeAdminUnitName(java.lang.String exchangeAdminUnitName) {
		this.exchangeAdminUnitName = exchangeAdminUnitName;
	}

	public java.lang.String getExchangeAdminUnitName() {
		return this.exchangeAdminUnitName;
	}

	public void setExchangeType(java.lang.String exchangeType) {
		this.exchangeType = exchangeType;
	}

	public java.lang.String getExchangeType() {
		return this.exchangeType;
	}

	public void setPrePersonOid(java.lang.Long prePersonOid) {
		this.prePersonOid = prePersonOid;
	}

	public java.lang.Long getPrePersonOid() {
		return this.prePersonOid;
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

	public java.lang.String getItemCode() {
		return itemCode;
	}

	public void setItemCode(java.lang.String itemCode) {
		this.itemCode = itemCode;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

}
