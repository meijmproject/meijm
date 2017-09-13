/**
 * 
 */
package com.yh.component.upload.dto;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/11/08
 */

public class UploadRefAuthDTO {
	private java.lang.String	refRoleCode;	//具体角色（自定义节点、用户类型）
	private java.lang.String	refCode;	//引用代码（自定义代码、环节码）
	private java.lang.String	refRoleName;	//角色名称
	private java.lang.Integer	authority;		//权限：1、查看、9、维护
	
	public java.lang.String getRefCode() {
		return refCode;
	}
	public void setRefCode(java.lang.String refCode) {
		this.refCode = refCode;
	}
	public java.lang.String getRefRoleCode() {
		return refRoleCode;
	}
	public void setRefRoleCode(java.lang.String refRoleCode) {
		this.refRoleCode = refRoleCode;
	}
	public java.lang.String getRefRoleName() {
		return refRoleName;
	}
	public void setRefRoleName(java.lang.String refRoleName) {
		this.refRoleName = refRoleName;
	}
	public java.lang.Integer getAuthority() {
		return authority;
	}
	public void setAuthority(java.lang.Integer authority) {
		this.authority = authority;
	}
}
