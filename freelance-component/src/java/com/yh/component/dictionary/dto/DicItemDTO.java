package com.yh.component.dictionary.dto;

import java.util.Date;

public class DicItemDTO {

	private Long		dicItemOid;
	private String		dicItemCode;
	private String		dicItemName;
	private String		viewName;
	private Double		displayOrder;
	private String		isActive;
	private String		isGeneral;
	private String		codeResource;
	private Date		disabledDate;
	private String		parentCode;
	private String		createdByCode;
	private String		createdByName;
	private Date		createdDate;
	private String		lastModifierCode;
	private String		lastModifierName;
	private Date		lastModifyDate;

	private Long		dicTypeOid;
	private String		dicTypeCode;
	private String		oldDicItemCode;
	private String		remark;
	private boolean		isleaf;// 是否还有子节点标志
	private String[]	dicItemIdTemp;//临时代码的id
	private boolean		istop;
	private String		isParentNode;
	private String		dicItemCodes;
	
	public DicItemDTO() {
	}

	public DicItemDTO(Long dicItemOid, String dicItemCode, String dicItemName, String parentCode, Long dicTypeOid) {
		this.dicItemOid = dicItemOid;
		this.dicItemCode = dicItemCode;
		this.dicItemName = dicItemName;
		this.parentCode = parentCode;
		this.dicTypeOid = dicTypeOid;
	}

	public String getDicItemCodes() {
		return dicItemCodes;
	}

	public void setDicItemCodes(String dicItemCodes) {
		this.dicItemCodes = dicItemCodes;
	}

	public String getOldDicItemCode() {
		return oldDicItemCode;
	}

	public void setOldDicItemCode(String oldDicItemCode) {
		this.oldDicItemCode = oldDicItemCode;
	}

	public String getIsParentNode() {
		return isParentNode;
	}

	public void setIsParentNode(String isParentNode) {
		this.isParentNode = isParentNode;
	}

	public String getCodeResource() {
		return codeResource;
	}

	public void setCodeResource(String codeResource) {
		this.codeResource = codeResource;
	}

	public String getDicItemCode() {
		return dicItemCode;
	}

	public void setDicItemCode(String dicItemCode) {
		this.dicItemCode = dicItemCode;
	}

	public String getDicItemName() {
		return dicItemName;
	}

	public void setDicItemName(String dicItemName) {
		this.dicItemName = dicItemName;
	}

	public Long getDicItemOid() {
		return dicItemOid;
	}

	public void setDicItemOid(Long dicItemOid) {
		this.dicItemOid = dicItemOid;
	}

	public Long getDicTypeOid() {
		return dicTypeOid;
	}

	public void setDicTypeOid(Long dicTypeOid) {
		this.dicTypeOid = dicTypeOid;
	}

	public Date getDisabledDate() {
		return disabledDate;
	}

	public void setDisabledDate(Date disabledDate) {
		this.disabledDate = disabledDate;
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

	public Double getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Double displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsGeneral() {
		return isGeneral;
	}

	public void setIsGeneral(String isGeneral) {
		this.isGeneral = isGeneral;
	}

	public String getLastModifierCode() {
		return lastModifierCode;
	}

	public void setLastModifierCode(String lastModifierCode) {
		this.lastModifierCode = lastModifierCode;
	}

	public String getLastModifierName() {
		return lastModifierName;
	}

	public void setLastModifierName(String lastModifierName) {
		this.lastModifierName = lastModifierName;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public boolean isIsleaf() {
		return isleaf;
	}

	public void setIsleaf(boolean isleaf) {
		this.isleaf = isleaf;
	}

	public boolean isIstop() {
		return istop;
	}

	public void setIstop(boolean istop) {
		this.istop = istop;
	}

	public String getDicTypeCode() {
		return dicTypeCode;
	}

	public void setDicTypeCode(String dicTypeCode) {
		this.dicTypeCode = dicTypeCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String[] getDicItemIdTemp() {
		return dicItemIdTemp;
	}

	public void setDicItemIdTemp(String[] dicItemIdTemp) {
		this.dicItemIdTemp = dicItemIdTemp;
	}

}
