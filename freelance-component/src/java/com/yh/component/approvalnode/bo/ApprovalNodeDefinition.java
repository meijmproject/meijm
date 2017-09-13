package com.yh.component.approvalnode.bo;

import com.yh.platform.core.bo.BaseBo;
/**
 * 审批环节定义表BO
 * @author wangx
 *
 */
public class ApprovalNodeDefinition extends BaseBo {
	
	private static final long serialVersionUID = 3702490850726928156L;
	
	private java.lang.Long		approvalNodeDefinitionOid;	// 审批环节定义OID
	private java.lang.String	itemCode;					// 事项编码
	private java.lang.String	itemName;					// 事项名称
	private java.lang.String	itemNodeCode;				// 事项环节编码
	private java.lang.String	itemNodeName;				// 事项环节名称
	private java.lang.String	isActive;					// 是否激活
	private java.lang.String	remark;						// 备注
	private java.lang.String	createdByCode;				// 创建人CODE
	private java.lang.String	createdByName;				// 创建人姓名
	private java.util.Date		createdDate;				// 创建时间
	private java.lang.String	lastModifierCode;			// 修改人CODE
	private java.lang.String	lastModifierName;			// 修改人姓名
	private java.util.Date		lastModifyDate;				// 修改日期
	
	public java.lang.Long getApprovalNodeDefinitionOid() {
		return approvalNodeDefinitionOid;
	}
	public void setApprovalNodeDefinitionOid(
			java.lang.Long approvalNodeDefinitionOid) {
		this.approvalNodeDefinitionOid = approvalNodeDefinitionOid;
	}
	public java.lang.String getItemCode() {
		return itemCode;
	}
	public void setItemCode(java.lang.String itemCode) {
		this.itemCode = itemCode;
	}
	public java.lang.String getItemName() {
		return itemName;
	}
	public void setItemName(java.lang.String itemName) {
		this.itemName = itemName;
	}
	public java.lang.String getItemNodeCode() {
		return itemNodeCode;
	}
	public void setItemNodeCode(java.lang.String itemNodeCode) {
		this.itemNodeCode = itemNodeCode;
	}
	public java.lang.String getItemNodeName() {
		return itemNodeName;
	}
	public void setItemNodeName(java.lang.String itemNodeName) {
		this.itemNodeName = itemNodeName;
	}
	public java.lang.String getIsActive() {
		return isActive;
	}
	public void setIsActive(java.lang.String isActive) {
		this.isActive = isActive;
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
	
}
