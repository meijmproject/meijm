package com.yh.component.approvalnode.bo;

import com.yh.platform.core.bo.BaseBo;

public class ApprovalNodeDetail extends BaseBo {

	private static final long serialVersionUID = 5795864610425206201L;
	
	private java.lang.Long		approvalNodeDetailOid;	// 审批环节明细OID
	private java.lang.String	nodeCode;					// 审批环节CODE
	private java.lang.String	nodeName;					// 审批环节名称
	private java.lang.String	nodeNameEN;					// 审批环节英文名称
	private java.lang.Double	displayOrder;				// 显示序号
	private java.lang.String	isActive;					// 是否激活
	private java.lang.String	remark;						// 备注
	private java.lang.Long		approvalNodeDefinitionOid;	// 审批环节定义OID
	private java.lang.String	createdByCode;				// 创建人CODE
	private java.lang.String	createdByName;				// 创建人姓名
	private java.util.Date		createdDate;				// 创建时间
	private java.lang.String	lastModifierCode;			// 修改人CODE
	private java.lang.String	lastModifierName;			// 修改人姓名
	private java.util.Date		lastModifyDate;				// 修改日期
	public java.lang.Long getApprovalNodeDetailOid() {
		return approvalNodeDetailOid;
	}
	public void setApprovalNodeDetailOid(java.lang.Long approvalNodeDetailOid) {
		this.approvalNodeDetailOid = approvalNodeDetailOid;
	}
	public java.lang.String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(java.lang.String nodeCode) {
		this.nodeCode = nodeCode;
	}
	public java.lang.String getNodeName() {
		return nodeName;
	}
	public void setNodeName(java.lang.String nodeName) {
		this.nodeName = nodeName;
	}
	public java.lang.String getNodeNameEN() {
		return nodeNameEN;
	}
	public void setNodeNameEN(java.lang.String nodeNameEN) {
		this.nodeNameEN = nodeNameEN;
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
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public java.lang.Long getApprovalNodeDefinitionOid() {
		return approvalNodeDefinitionOid;
	}
	public void setApprovalNodeDefinitionOid(
			java.lang.Long approvalNodeDefinitionOid) {
		this.approvalNodeDefinitionOid = approvalNodeDefinitionOid;
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
