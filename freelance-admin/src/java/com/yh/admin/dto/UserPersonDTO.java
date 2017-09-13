package com.yh.admin.dto;


/**
 * @description 账号用户信息DTO
 * @author	wangx
 * @date 2017-05-15
 * @version	1.0
 */
public class UserPersonDTO {
	
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
