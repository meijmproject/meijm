package com.yh.hr.info.ver.unit.comm.web.form;


import org.apache.struts.validator.ValidatorForm;



public class VerPbWageHistoryItemsForm extends ValidatorForm {
	private static final long serialVersionUID = 2813715227720830868L;
	/**
     *主键
     **/
	private java.lang.Long wagehistoryItemOid;
	/**
     *工资历史id **/
	private java.lang.Long wagehistoryOid;

	/**
     *工资项编码
     **/
	private java.lang.String wageItemCode;
    /**
     *工资项名称
     **/
	private java.lang.String wageItemName;
    /**
     *工资项子属性1名称
     **/
	private java.lang.String propertyOneName;
    /**
     *工资项子属性2名称
     **/
	private java.lang.String propertyTwoName;	
    /**
     *工资项子属性3名称
     **/
	private java.lang.String propertyThreeName;	
    /**
     *工资项子属性4名称
     **/
	private java.lang.String propertyFourName;	
    /**
     *工资项子属性1取值
     **/
	private java.lang.String propertyOneValue;
    /**
     *工资项子属性2取值
     **/
	private java.lang.String propertyTwoValue;	
    /**
     *工资项子属性3取值
     **/
	private java.lang.String propertyThreeValue;	
    /**
     *工资项子属性4取值
     **/
	private java.lang.String propertyFourValue;	
	/**
     *金额
     **/
	private java.lang.Double wageAmount;
    /**
     *计算过程
     **/
	private java.lang.String calProcessInfo;	
    /**
     *显示顺序
     **/
	private java.lang.Integer viewOrderId;

	public VerPbWageHistoryItemsForm() {
	}
		
	public java.lang.Long getWagehistoryOid() {
		return wagehistoryOid;
	}
	public void setWagehistoryOid(java.lang.Long wagehistoryOid) {
		this.wagehistoryOid = wagehistoryOid;
	}

	public java.lang.String getWageItemCode() {
		return wageItemCode;
	}

	public void setWageItemCode(java.lang.String wageItemCode) {
		this.wageItemCode = wageItemCode;
	}

	public java.lang.String getWageItemName() {
		return wageItemName;
	}

	public void setWageItemName(java.lang.String wageItemName) {
		this.wageItemName = wageItemName;
	}

	public java.lang.String getPropertyOneName() {
		return propertyOneName;
	}

	public void setPropertyOneName(java.lang.String propertyOneName) {
		this.propertyOneName = propertyOneName;
	}

	public java.lang.String getPropertyTwoName() {
		return propertyTwoName;
	}

	public void setPropertyTwoName(java.lang.String propertyTwoName) {
		this.propertyTwoName = propertyTwoName;
	}

	public java.lang.String getPropertyThreeName() {
		return propertyThreeName;
	}

	public void setPropertyThreeName(java.lang.String propertyThreeName) {
		this.propertyThreeName = propertyThreeName;
	}

	public java.lang.String getPropertyFourName() {
		return propertyFourName;
	}

	public void setPropertyFourName(java.lang.String propertyFourName) {
		this.propertyFourName = propertyFourName;
	}

	public java.lang.String getPropertyOneValue() {
		return propertyOneValue;
	}

	public void setPropertyOneValue(java.lang.String propertyOneValue) {
		this.propertyOneValue = propertyOneValue;
	}

	public java.lang.String getPropertyTwoValue() {
		return propertyTwoValue;
	}

	public void setPropertyTwoValue(java.lang.String propertyTwoValue) {
		this.propertyTwoValue = propertyTwoValue;
	}

	public java.lang.String getPropertyThreeValue() {
		return propertyThreeValue;
	}

	public void setPropertyThreeValue(java.lang.String propertyThreeValue) {
		this.propertyThreeValue = propertyThreeValue;
	}

	public java.lang.String getPropertyFourValue() {
		return propertyFourValue;
	}

	public void setPropertyFourValue(java.lang.String propertyFourValue) {
		this.propertyFourValue = propertyFourValue;
	}

	public java.lang.Double getWageAmount() {
		return wageAmount;
	}

	public void setWageAmount(java.lang.Double wageAmount) {
		this.wageAmount = wageAmount;
	}

	public java.lang.String getCalProcessInfo() {
		return calProcessInfo;
	}

	public void setCalProcessInfo(java.lang.String calProcessInfo) {
		this.calProcessInfo = calProcessInfo;
	}

	public java.lang.Integer getViewOrderId() {
		return viewOrderId;
	}

	public void setViewOrderId(java.lang.Integer viewOrderId) {
		this.viewOrderId = viewOrderId;
	}

	public java.lang.Long getWagehistoryItemOid() {
		return wagehistoryItemOid;
	}

	public void setWagehistoryItemOid(java.lang.Long wagehistoryItemOid) {
		this.wagehistoryItemOid = wagehistoryItemOid;
	}
	
}
