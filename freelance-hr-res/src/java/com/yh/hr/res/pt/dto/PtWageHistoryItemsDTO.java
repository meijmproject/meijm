package com.yh.hr.res.pt.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

public class PtWageHistoryItemsDTO {
	/**
	 * 工资历史工资项信息表
	 */
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

	//工资项集合
	private List<PtWageHistoryLogicDTO> ptWageHistoryLogiclist;
	//工资项Map
	private Map<String,PtWageHistoryLogicDTO> ptWageHistoryLogicMap;
	
	public PtWageHistoryItemsDTO() {
		super();
	}
	public PtWageHistoryItemsDTO(String wageItemCode) {
		super();
		this.wageItemCode = wageItemCode;
	}
	public PtWageHistoryItemsDTO(String wageItemCode, String wageItemName) {
		super();
		this.wageItemCode = wageItemCode;
		this.wageItemName = wageItemName;
	}
	
	public PtWageHistoryItemsDTO(String wageItemCode, String wageItemName,
			Double wageAmount) {
		super();
		this.wageItemCode = wageItemCode;
		this.wageItemName = wageItemName;
		this.wageAmount = wageAmount;
	}

	
	/**
	 * 构建工资项MAP
	 * @param wageItemList
	 */
	private void buildptWHistoryLogicMap(List<PtWageHistoryLogicDTO> ptWageHistoryLogiclist)
	{
		//先清空wageItemMap，或者初始化
		if(null != ptWageHistoryLogicMap) 
		{
			ptWageHistoryLogicMap.clear();	
		}
		else
		{
			ptWageHistoryLogicMap = new HashMap<String, PtWageHistoryLogicDTO>();
		}
		//设置工资项Map
		if(CollectionUtils.isNotEmpty(ptWageHistoryLogiclist))
		{
			for (PtWageHistoryLogicDTO ptWHistoryLogic : ptWageHistoryLogiclist)
			{
				ptWageHistoryLogicMap.put(ptWHistoryLogic.getLogicCode(), ptWHistoryLogic);
			}
		}
	}
	//根据工资项编码从工资项Map中获取到工资项DTO
	public PtWageHistoryLogicDTO getPtWHistoryLogic(String LogicCode)
	{
		if(null != ptWageHistoryLogicMap)
		{
			return ptWageHistoryLogicMap.get(LogicCode);
		}
		return null;
	}
	/**
	 * 往工资项集合中新增一个工资项
	 * @param wageItem
	 */
	public void addPtWagehistoryLogic(PtWageHistoryLogicDTO ptWHistoryLogic)
	{
		if(CollectionUtils.isEmpty(ptWageHistoryLogiclist))
		{
			ptWageHistoryLogiclist = new ArrayList<PtWageHistoryLogicDTO>();
		}
		ptWageHistoryLogiclist.add(ptWHistoryLogic);
		addptWageHistoryLogicMap(ptWHistoryLogic);
	}
	/**
	 * 往工资项MAP中新增工资项
	 * @param wageItem
	 */
	private void addptWageHistoryLogicMap(PtWageHistoryLogicDTO ptWHistoryLogic)
	{
		if(null == ptWageHistoryLogicMap)
		{
			ptWageHistoryLogicMap = new HashMap<String, PtWageHistoryLogicDTO>();
		}
		ptWageHistoryLogicMap.put(ptWHistoryLogic.getWageItemCode(), ptWHistoryLogic);
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

	public List<PtWageHistoryLogicDTO> getPtWageHistoryLogiclist() {
		return ptWageHistoryLogiclist;
	}
	public void setPtWageHistoryLogiclist(
			List<PtWageHistoryLogicDTO> ptWageHistoryLogiclist) {
		this.ptWageHistoryLogiclist = ptWageHistoryLogiclist;
		buildptWHistoryLogicMap(ptWageHistoryLogiclist);
	}
	
	
}
