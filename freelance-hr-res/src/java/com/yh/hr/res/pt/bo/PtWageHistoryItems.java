package com.yh.hr.res.pt.bo;

import com.yh.platform.core.bo.BaseBo;

public class PtWageHistoryItems extends BaseBo{

	/**
	 * 工资历史工资项信息表
	 */
	private static final long serialVersionUID = -1681215235885167040L;
	private java.lang.Long ptWageHistoryItemOid;//工资历史工资项ID
	private java.lang.Long ptWageHistoryOid;//工资历史ID
	private java.lang.Long wageHistoryItemOid;//基础库工资历史工资项ID
	private java.lang.String wageItemCode;//工资项编码
	private java.lang.String wageItemName;//工资项名称
	private java.lang.String propertyOneName;//工资项子属性1名称
	private java.lang.String propertyTwoName;//工资项子属性2名称
	private java.lang.String propertyThreeName;//工资项子属性3名称
	private java.lang.String propertyFourName;//工资项子属性4名称
	private java.lang.String propertyOneValue;//工资项子属性1取值
	private java.lang.String propertyTwoValue;//工资项子属性2取值
	private java.lang.String propertyThreeValue;//工资项子属性3取值
	private java.lang.String propertyFourValue;//工资项子属性4取值
	private java.lang.Double wageAmount;//金额 
	private java.lang.String calProcessInfo;//计算过程
	private java.lang.Long viewOrderId;//显示顺序
	
	public PtWageHistoryItems(){
		
	}

	public PtWageHistoryItems(String wageItemCode,String wageItemName) {
		super();
		this.wageItemCode = wageItemCode;
		this.wageItemName = wageItemName;
	}

	public java.lang.Long getPtWageHistoryItemOid() {
		return ptWageHistoryItemOid;
	}

	public void setPtWageHistoryItemOid(java.lang.Long ptWageHistoryItemOid) {
		this.ptWageHistoryItemOid = ptWageHistoryItemOid;
	}

	public java.lang.Long getPtWageHistoryOid() {
		return ptWageHistoryOid;
	}

	public void setPtWageHistoryOid(java.lang.Long ptWageHistoryOid) {
		this.ptWageHistoryOid = ptWageHistoryOid;
	}

	public java.lang.Long getWageHistoryItemOid() {
		return wageHistoryItemOid;
	}

	public void setWageHistoryItemOid(java.lang.Long wageHistoryItemOid) {
		this.wageHistoryItemOid = wageHistoryItemOid;
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

	public java.lang.Long getViewOrderId() {
		return viewOrderId;
	}

	public void setViewOrderId(java.lang.Long viewOrderId) {
		this.viewOrderId = viewOrderId;
	}
	
	
	
}
