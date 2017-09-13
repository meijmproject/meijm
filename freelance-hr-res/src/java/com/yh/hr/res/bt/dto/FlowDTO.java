package com.yh.hr.res.bt.dto;

/**
 *	事项信息 - DTO	
 *	@author		liuhw
 *	@created	2016-08-29
 */
public class FlowDTO
{
	private String itemCode;		// 事项代码
	private String itemNodeCode;	// 环节代码
	private String itemName;
	private String itemNodeName;
	private String createByCode;//创建人ID
	private String createByName;//创建人姓名
	private String updateByCode;//修改人ID
	private String updateByName;//修改人姓名
	private Long unitOid; //业务发起单位
	private Long orgOid; //业务发起科室
	private String auditStatus;//审批状态
	private String processResult;  //事项办理结果（最终结果）
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemNodeName() {
		return itemNodeName;
	}

	public void setItemNodeName(String itemNodeName) {
		this.itemNodeName = itemNodeName;
	}

	public FlowDTO(){};
	
	public FlowDTO(String itemCode, String itemNodeCode)
	{
		this.itemCode=itemCode;
		this.itemNodeCode=itemNodeCode;
	}
	public String getItemCode()
	{
		return itemCode;
	}
	public void setItemCode(String itemCode)
	{
		this.itemCode = itemCode;
	}
	public String getItemNodeCode()
	{
		return itemNodeCode;
	}
	public void setItemNodeCode(String itemNodeCode)
	{
		this.itemNodeCode = itemNodeCode;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public String getUpdateByCode() {
		return updateByCode;
	}

	public void setUpdateByCode(String updateByCode) {
		this.updateByCode = updateByCode;
	}

	public String getUpdateByName() {
		return updateByName;
	}

	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}

	public String getCreateByCode() {
		return createByCode;
	}

	public void setCreateByCode(String createByCode) {
		this.createByCode = createByCode;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public Long getUnitOid() {
		return unitOid;
	}

	public void setUnitOid(Long unitOid) {
		this.unitOid = unitOid;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Long getOrgOid() {
		return orgOid;
	}

	public void setOrgOid(Long orgOid) {
		this.orgOid = orgOid;
	}

}