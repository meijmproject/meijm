package com.yh.admin.bo;

import java.util.Date;

import com.yh.platform.core.bo.BaseBo;

public class UserAgent extends BaseBo {

	private static final long	serialVersionUID	= 2715416587055228708L;

	private Long				userAgentOid;
	private Long				systemPositionOid;
	private String				userId;										// 被代理人
	private String				agentUserId;								// 指定的代理人
	private Date				effectiveDate;								// 有效开始期
	private Date				expiredDt;
	private String				isActive;

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

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
