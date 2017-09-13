package com.yh.admin.dto;

import java.util.Date;

public class UserAgentDTO {
	private Long	userAgentOid;
	private Long	systemPositionOid;
	private String	userId;				// 被代理人
	private String	agentUserId;		// 指定的代理人
	private Date	effectiveDt;		// 有效开始期
	private Date	expiredDt;
	private String	isActive;

	public Long getSystemPositionOid() {
		return systemPositionOid;
	}

	public void setSystemPositionOid(Long systemPositionOid) {
		this.systemPositionOid = systemPositionOid;
	}


	public Long getUserAgentOid() {
		return userAgentOid;
	}

	public void setUserAgentOid(Long userAgentOid) {
		this.userAgentOid = userAgentOid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAgentUserId() {
		return agentUserId;
	}

	public void setAgentUserId(String agentUserId) {
		this.agentUserId = agentUserId;
	}

	public Date getEffectiveDt() {
		return effectiveDt;
	}

	public void setEffectiveDt(Date effectiveDt) {
		this.effectiveDt = effectiveDt;
	}

	public Date getExpiredDt() {
		return expiredDt;
	}

	public void setExpiredDt(Date expiredDt) {
		this.expiredDt = expiredDt;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
