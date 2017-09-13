/**
 * This file is generated by  code generation tool version 0.2 ^_^
 * Created at 2016-09-01
**/
package com.yh.admin.dto;

import com.yh.platform.core.bo.BaseBo;

public class MtMenuDto extends BaseBo {
	private static final long serialVersionUID = 1677215456310856003L;
	
	private java.lang.String	menuCode;	//菜单代码
	private java.lang.String	parentMenuCode;	//上级菜单代码
	private java.lang.String	menuTitle;	//菜单名称
	private java.lang.String	menuDesc;	//菜单描述
	private java.lang.String	menuType;	//菜单类型
	private java.lang.String	status;	//菜单状态
	private java.lang.Long	orderSeq;	//菜单序号
	private java.lang.String	location;	//菜单URL
	private java.lang.String	action;	//ACTION
	private java.lang.String	image;	//图标
	private java.lang.String	systemId;	//所属子系统
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date	updatedDate;	//UpdatedDate
	
	private int count;//代办数量
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private java.lang.String viewMenuTitle;

	public java.lang.String getViewMenuTitle() {
		return viewMenuTitle;
	}

	public void setViewMenuTitle(java.lang.String viewMenuTitle) {
		this.viewMenuTitle = viewMenuTitle;
	}

	public MtMenuDto() {
		
	}

	public void setMenuCode(java.lang.String menuCode){
		this.menuCode = menuCode;
	}

	public java.lang.String getMenuCode(){
		return this.menuCode;
	}

	public void setParentMenuCode(java.lang.String parentMenuCode){
		this.parentMenuCode = parentMenuCode;
	}

	public java.lang.String getParentMenuCode(){
		return this.parentMenuCode;
	}

	public void setMenuTitle(java.lang.String menuTitle){
		this.menuTitle = menuTitle;
	}

	public java.lang.String getMenuTitle(){
		return this.menuTitle;
	}

	public void setMenuDesc(java.lang.String menuDesc){
		this.menuDesc = menuDesc;
	}

	public java.lang.String getMenuDesc(){
		return this.menuDesc;
	}

	public void setMenuType(java.lang.String menuType){
		this.menuType = menuType;
	}

	public java.lang.String getMenuType(){
		return this.menuType;
	}

	public void setStatus(java.lang.String status){
		this.status = status;
	}

	public java.lang.String getStatus(){
		return this.status;
	}

	public void setOrderSeq(java.lang.Long orderSeq){
		this.orderSeq = orderSeq;
	}

	public java.lang.Long getOrderSeq(){
		return this.orderSeq;
	}

	public void setLocation(java.lang.String location){
		this.location = location;
	}

	public java.lang.String getLocation(){
		return this.location;
	}

	public void setAction(java.lang.String action){
		this.action = action;
	}

	public java.lang.String getAction(){
		return this.action;
	}

	public void setImage(java.lang.String image){
		this.image = image;
	}

	public java.lang.String getImage(){
		return this.image;
	}

	public void setSystemId(java.lang.String systemId){
		this.systemId = systemId;
	}

	public java.lang.String getSystemId(){
		return this.systemId;
	}

	public void setCreatedByCode(java.lang.String createdByCode){
		this.createdByCode = createdByCode;
	}

	public java.lang.String getCreatedByCode(){
		return this.createdByCode;
	}

	public void setCreatedByName(java.lang.String createdByName){
		this.createdByName = createdByName;
	}

	public java.lang.String getCreatedByName(){
		return this.createdByName;
	}

	public void setCreatedDate(java.util.Date createdDate){
		this.createdDate = createdDate;
	}

	public java.util.Date getCreatedDate(){
		return this.createdDate;
	}

	public void setUpdatedByCode(java.lang.String updatedByCode){
		this.updatedByCode = updatedByCode;
	}

	public java.lang.String getUpdatedByCode(){
		return this.updatedByCode;
	}

	public void setUpdatedByName(java.lang.String updatedByName){
		this.updatedByName = updatedByName;
	}

	public java.lang.String getUpdatedByName(){
		return this.updatedByName;
	}

	public void setUpdatedDate(java.util.Date updatedDate){
		this.updatedDate = updatedDate;
	}

	public java.util.Date getUpdatedDate(){
		return this.updatedDate;
	}

}