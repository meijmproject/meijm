package com.yh.hr.res.cf.dto;


public class JhcCfShowResultSetDTO {
	
	private Long resultOid;
	private String functionCode;
	private String labelName;
	private String labelValue;
	private Long labelWidth;
	private Long ordrrNo;
	private String databaseField;
	private String isShow;
	public Long getResultOid() {
		return resultOid;
	}
	public void setResultOid(Long resultOid) {
		this.resultOid = resultOid;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getLabelValue() {
		return labelValue;
	}
	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}
	public Long getLabelWidth() {
		return labelWidth;
	}
	public void setLabelWidth(Long labelWidth) {
		this.labelWidth = labelWidth;
	}
	public Long getOrdrrNo() {
		return ordrrNo;
	}
	public void setOrdrrNo(Long ordrrNo) {
		this.ordrrNo = ordrrNo;
	}
	public String getDatabaseField() {
		return databaseField;
	}
	public void setDatabaseField(String databaseField) {
		this.databaseField = databaseField;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
}
