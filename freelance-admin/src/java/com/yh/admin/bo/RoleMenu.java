package com.yh.admin.bo;

import com.yh.platform.core.bo.BaseBo;

public class RoleMenu extends BaseBo {

	private static final long serialVersionUID = 1688542799335998103L;
	
	private Long	roleId;	//功能角色ID
	private String	MenuCode;//事项环节
	
	private java.lang.String	createdByCode;	//创建人ID
	private java.lang.String	createdByName;	//创建人姓名
	private java.util.Date	createdDate;	//创建时间
	private java.lang.String	updatedByCode;	//修改人ID
	private java.lang.String	updatedByName;	//修改人姓名
	private java.util.Date	updatedDate;	//修改时间

	public RoleMenu() {
	}
	
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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

	public java.lang.String getUpdatedByCode() {
		return updatedByCode;
	}

	public void setUpdatedByCode(java.lang.String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}

	public java.lang.String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(java.lang.String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getMenuCode() {
		return MenuCode;
	}

	public void setMenuCode(String menuCode) {
		MenuCode = menuCode;
	}

}
