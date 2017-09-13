/**
 * 
 */
package com.yh.admin.bo;

import com.yh.platform.core.bo.BaseBo;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/23
 */
public class RoleFunc extends BaseBo {
	private static final long serialVersionUID = 3485030427513074614L;
	
	private Long	roleId;
	private Long	funcId;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getFuncId() {
		return funcId;
	}
	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
}
