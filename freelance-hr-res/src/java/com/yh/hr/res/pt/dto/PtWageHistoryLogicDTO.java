package com.yh.hr.res.pt.dto;

public class PtWageHistoryLogicDTO {
	/**
	 * 工资项逻辑信息表
	 */
	private java.lang.Long ptWageHistoryItemOid;//工资历史工资项ID
	private java.lang.String wageItemCode;//工资项编码
	private java.lang.String logicCode;//逻辑属性编码
	private java.lang.String logicName;//逻辑属性名称
	private java.lang.String logicValue;//逻辑属性取值
	
	
	public PtWageHistoryLogicDTO() {
		super();
	}
	public PtWageHistoryLogicDTO(String wageItemCode, String logicCode,
			String logicName, String logicValue) {
		super();
		this.wageItemCode = wageItemCode;
		this.logicCode = logicCode;
		this.logicName = logicName;
		this.logicValue = logicValue;
	}
	public java.lang.Long getPtWageHistoryItemOid() {
		return ptWageHistoryItemOid;
	}
	public void setPtWageHistoryItemOid(java.lang.Long ptWageHistoryItemOid) {
		this.ptWageHistoryItemOid = ptWageHistoryItemOid;
	}
	public java.lang.String getWageItemCode() {
		return wageItemCode;
	}
	public void setWageItemCode(java.lang.String wageItemCode) {
		this.wageItemCode = wageItemCode;
	}
	public java.lang.String getLogicCode() {
		return logicCode;
	}
	public void setLogicCode(java.lang.String logicCode) {
		this.logicCode = logicCode;
	}
	public java.lang.String getLogicName() {
		return logicName;
	}
	public void setLogicName(java.lang.String logicName) {
		this.logicName = logicName;
	}
	public java.lang.String getLogicValue() {
		return logicValue;
	}
	public void setLogicValue(java.lang.String logicValue) {
		this.logicValue = logicValue;
	}
	
	
	
	
	
}
