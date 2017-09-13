package com.yh.hr.info.ver.unit.comm.web.form;

import org.apache.struts.validator.ValidatorForm;


public class VerPbWageHistoryLogicForm  extends ValidatorForm{
	private static final long serialVersionUID = -9150606276437728335L;
	/**
     *主键
     **/
	private java.lang.Long wagehistoryItemOid;
	/**
     *工资项编码
     **/
	private java.lang.String wageItemCode;
    /**
     *逻辑属性编码
     **/
	private java.lang.String logicCode;
    /**
     *逻辑属性名称
     **/
	private java.lang.String logicName;
    /**
     *逻辑属性取值
     **/
	private java.lang.String logicValue;	

	public VerPbWageHistoryLogicForm() {
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

	public java.lang.Long getWagehistoryItemOid() {
		return wagehistoryItemOid;
	}

	public void setWagehistoryItemOid(java.lang.Long wagehistoryItemOid) {
		this.wagehistoryItemOid = wagehistoryItemOid;
	}


	}
	
