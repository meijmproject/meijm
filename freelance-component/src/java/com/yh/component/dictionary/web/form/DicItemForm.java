package com.yh.component.dictionary.web.form;

import org.apache.struts.validator.ValidatorForm;

public class DicItemForm extends ValidatorForm {

	private static final long serialVersionUID = -4575340405495470642L;
	
	private java.lang.Long		dicItemOid;			// DIC_ITEM_OID
	private java.lang.String	dicItemCode;		// 字典代码_CODE
	private java.lang.String	dicItemName;		// 字典代码_NAME
	private java.lang.String	viewName;			// 简写
	private java.lang.Double	displayOrder;		// 显示序号
	private java.lang.String	isActive;			// 是否激活
	private java.lang.String	isGeneral;			// IS_GENERAL
	private java.lang.String	codeResource;		// 代码来源
	private java.util.Date		disabledDate;		// 失效日期
	private java.lang.String	parentCode;			// 父编码
	private java.lang.String	createdByCode;		// 创建人CODE
	private java.lang.String	createdByName;		// 创建人
	private java.util.Date		createdDate;		// 创建日期
	private java.lang.String	lastModifierCode;	// 修改人CODE
	private java.lang.String	lastModifierName;	// 修改人姓名
	private java.util.Date		lastModifyDate;		// 修改日期
	private java.lang.String	remark;				// 备注
	private java.lang.Long		dicTypeOid;			// 外键
	public java.lang.Long getDicItemOid() {
		return dicItemOid;
	}
	public void setDicItemOid(java.lang.Long dicItemOid) {
		this.dicItemOid = dicItemOid;
	}
	public java.lang.String getDicItemCode() {
		return dicItemCode;
	}
	public void setDicItemCode(java.lang.String dicItemCode) {
		this.dicItemCode = dicItemCode;
	}
	public java.lang.String getDicItemName() {
		return dicItemName;
	}
	public void setDicItemName(java.lang.String dicItemName) {
		this.dicItemName = dicItemName;
	}
	public java.lang.String getViewName() {
		return viewName;
	}
	public void setViewName(java.lang.String viewName) {
		this.viewName = viewName;
	}
	public java.lang.Double getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(java.lang.Double displayOrder) {
		this.displayOrder = displayOrder;
	}
	public java.lang.String getIsActive() {
		return isActive;
	}
	public void setIsActive(java.lang.String isActive) {
		this.isActive = isActive;
	}
	public java.lang.String getIsGeneral() {
		return isGeneral;
	}
	public void setIsGeneral(java.lang.String isGeneral) {
		this.isGeneral = isGeneral;
	}
	public java.lang.String getCodeResource() {
		return codeResource;
	}
	public void setCodeResource(java.lang.String codeResource) {
		this.codeResource = codeResource;
	}
	public java.util.Date getDisabledDate() {
		return disabledDate;
	}
	public void setDisabledDate(java.util.Date disabledDate) {
		this.disabledDate = disabledDate;
	}
	public java.lang.String getParentCode() {
		return parentCode;
	}
	public void setParentCode(java.lang.String parentCode) {
		this.parentCode = parentCode;
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
	public java.lang.String getLastModifierCode() {
		return lastModifierCode;
	}
	public void setLastModifierCode(java.lang.String lastModifierCode) {
		this.lastModifierCode = lastModifierCode;
	}
	public java.lang.String getLastModifierName() {
		return lastModifierName;
	}
	public void setLastModifierName(java.lang.String lastModifierName) {
		this.lastModifierName = lastModifierName;
	}
	public java.util.Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(java.util.Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public java.lang.Long getDicTypeOid() {
		return dicTypeOid;
	}
	public void setDicTypeOid(java.lang.Long dicTypeOid) {
		this.dicTypeOid = dicTypeOid;
	}
}
