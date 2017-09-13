
package com.yh.admin.dto;

public class SubSystemDTO {
	
	private java.lang.String	subSystemCode;	//系统名称
	private java.lang.String	subSystemName;	//子系统名称
	private java.lang.String	subSystemDesc;	//子系统描述
	private java.lang.String	subSystemLocation;	//子系统路径
	private java.lang.Long		orderOfView;	//显示顺序

	public SubSystemDTO() {
		
	}

	public void setSubSystemCode(java.lang.String subSystemCode){
		this.subSystemCode = subSystemCode;
	}

	public java.lang.String getSubSystemCode(){
		return this.subSystemCode;
	}

	public void setSubSystemName(java.lang.String subSystemName){
		this.subSystemName = subSystemName;
	}

	public java.lang.String getSubSystemName(){
		return this.subSystemName;
	}

	public void setSubSystemDesc(java.lang.String subSystemDesc){
		this.subSystemDesc = subSystemDesc;
	}

	public java.lang.String getSubSystemDesc(){
		return this.subSystemDesc;
	}

	public void setSubSystemLocation(java.lang.String subSystemLocation){
		this.subSystemLocation = subSystemLocation;
	}

	public java.lang.String getSubSystemLocation(){
		return this.subSystemLocation;
	}

	public void setOrderOfView(java.lang.Long orderOfView){
		this.orderOfView = orderOfView;
	}

	public java.lang.Long getOrderOfView(){
		return this.orderOfView;
	}

}
