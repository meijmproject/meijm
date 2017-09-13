package com.yh.admin.bo;

import com.yh.platform.core.bo.BaseBo;

/**
 * @description 账号用户信息BO
 * @author	wangx
 * @date 2017-05-15
 * @version	1.0
 */
public class UserPerson extends BaseBo {
	private static final long	serialVersionUID	= 6411581563805527676L;
	
	private String				userId;		//登录ID
	private Long				personOid;	//用户OID
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}
	
}
