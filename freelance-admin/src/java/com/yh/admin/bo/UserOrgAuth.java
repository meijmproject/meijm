package com.yh.admin.bo;

import com.yh.platform.core.bo.BaseBo;

/**
 * 用户单位权限
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/25
 */
public class UserOrgAuth extends BaseBo {
	private static final long	serialVersionUID	= 6411581563805527676L;
	
	private Long				orgOid;
	private String				orgName;
	private String				orgStatus;
	private String				userId;
	private Long				systemPositionOid;	//岗位
	public Long getOrgOid() {
		return orgOid;
	}
	public void setOrgOid(Long orgOid) {
		this.orgOid = orgOid;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgStatus() {
		return orgStatus;
	}
	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getSystemPositionOid() {
		return systemPositionOid;
	}
	public void setSystemPositionOid(Long systemPositionOid) {
		this.systemPositionOid = systemPositionOid;
	}
	
	
}
