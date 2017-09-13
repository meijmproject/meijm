package com.yh.admin.bo;

import com.yh.platform.core.bo.BaseBo;

/**
 *@description	
 *
 *@author		zhangqp
 *@created		16/09/13
 *@version		1.0
 */
public class RoleDataAuth extends BaseBo {
	private static final long serialVersionUID = -9142396628055243728L;
	
	private Long	roleDataAuthOid;
	private String	authCode;
	private String	authType;
	private String	authLevel;
	private Long	roleId;
	private String	isOnlyOwn;

	public String getIsOnlyOwn() {
		return isOnlyOwn;
	}

	public void setIsOnlyOwn(String isOnlyOwn) {
		this.isOnlyOwn = isOnlyOwn;
	}

	public Long getRoleDataAuthOid() {
		return roleDataAuthOid;
	}

	public void setRoleDataAuthOid(Long roleDataAuthOid) {
		this.roleDataAuthOid = roleDataAuthOid;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(String authLevel) {
		this.authLevel = authLevel;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
