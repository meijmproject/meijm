/**
 * 
 */
package com.yh.hr.select.unit.dto;

/**
 * 单位选择DTO
 * @author	xiongyx
 * @version	1.0,	16/12/21
 */

public class UnitSelectDTO {
	private java.lang.Long  	organizationOid;	//OrganizationOid
	private java.lang.Long  	unitOid;	//UnitOid
	private java.lang.String	unitName;	//单位名称
	private java.lang.String	unitKind;	//单位性质YHRS0090
	private java.lang.String	unitCategoryCode;	//系统类别YHRS0091
	private java.lang.String	unitLevelCode;	//机构级别YHRS0093
	private java.lang.String	unitAreaCode;	//区域代码YHRS0006
	private java.lang.String	unitStatus;	//单位状态YHRS0101
	private java.lang.String	orderOfAll;	//单位全局排序
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date  	updatedDate;	//UpdatedDate
	private java.lang.String	unitCode;	//用于权限查询
	
	
	
	public java.lang.Long getOrganizationOid() {
		return organizationOid;
	}

	public void setOrganizationOid(java.lang.Long organizationOid) {
		this.organizationOid = organizationOid;
	}

	public java.lang.Long getUnitOid() {
		return unitOid;
	}

	public void setUnitOid(java.lang.Long unitOid) {
		this.unitOid = unitOid;
	}

	public java.lang.String getUnitName() {
		return unitName;
	}

	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}

	public java.lang.String getUnitKind() {
		return unitKind;
	}

	public void setUnitKind(java.lang.String unitKind) {
		this.unitKind = unitKind;
	}

	public java.lang.String getUnitCategoryCode() {
		return unitCategoryCode;
	}

	public void setUnitCategoryCode(java.lang.String unitCategoryCode) {
		this.unitCategoryCode = unitCategoryCode;
	}

	public java.lang.String getUnitLevelCode() {
		return unitLevelCode;
	}

	public void setUnitLevelCode(java.lang.String unitLevelCode) {
		this.unitLevelCode = unitLevelCode;
	}

	public java.lang.String getUnitAreaCode() {
		return unitAreaCode;
	}

	public void setUnitAreaCode(java.lang.String unitAreaCode) {
		this.unitAreaCode = unitAreaCode;
	}

	public java.lang.String getUnitStatus() {
		return unitStatus;
	}

	public void setUnitStatus(java.lang.String unitStatus) {
		this.unitStatus = unitStatus;
	}

	public java.lang.String getOrderOfAll() {
		return orderOfAll;
	}

	public void setOrderOfAll(java.lang.String orderOfAll) {
		this.orderOfAll = orderOfAll;
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

	public java.lang.String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(java.lang.String unitCode) {
		this.unitCode = unitCode;
	}

	public java.lang.String getCreatedByCode() {
		return createdByCode;
	}

	public void setCreatedByCode(java.lang.String createdByCode) {
		this.createdByCode = createdByCode;
	}
}
