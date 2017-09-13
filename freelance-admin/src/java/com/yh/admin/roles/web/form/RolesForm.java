/**
 * 
 */
package com.yh.admin.roles.web.form;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/09/08
 */

public class RolesForm extends ValidatorForm {
	private static final long serialVersionUID = 3957662178887547145L;

	private java.lang.Long		roleId;		//角色主键
	private java.lang.String	roleName;	//角色名称
	private java.lang.String	roleDesc;	//角色描述
	private java.lang.String	roleType;	//角色类型
	private java.lang.String	createdByCode;	//创建人代码
	private java.util.Date		createdDate;	//创建时间
	private java.lang.String 	createdDateStr;
	private java.lang.String	updatedByCode;	//修改人代码
	private java.util.Date		updatedDate;	//修改时间
	
	
	public java.lang.Long getRoleId() {
		return roleId;
	}
	public void setRoleId(java.lang.Long roleId) {
		this.roleId = roleId;
	}
	public java.lang.String getRoleName() {
		return roleName;
	}
	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
	}
	public java.lang.String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(java.lang.String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public java.lang.String getRoleType() {
		return roleType;
	}
	public void setRoleType(java.lang.String roleType) {
		this.roleType = roleType;
	}
	public java.lang.String getCreatedByCode() {
		return createdByCode;
	}
	public void setCreatedByCode(java.lang.String createdByCode) {
		this.createdByCode = createdByCode;
	}
	public java.util.Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
		if (createdDate != null) {
			this.createdDateStr = DateUtil.formatDate(createdDate);
		}
	}
	public java.lang.String getUpdatedByCode() {
		return updatedByCode;
	}
	public void setUpdatedByCode(java.lang.String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}
	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public java.lang.String getCreatedDateStr() {
		return createdDateStr;
	}
	public void setCreatedDateStr(java.lang.String createdDateStr) {
		this.createdDateStr = createdDateStr;
		if (StringUtils.isNotEmpty(createdDateStr)) {
			this.createdDate = DateUtil.parseDate(createdDateStr);
		}
	}
}
