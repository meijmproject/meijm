package com.yh.hr.res.sao.users.dto;


/**
 * @description 账号用户信息DTO(freelance-hr-res模块使用)
 * @author	wangx
 * @date 2017-05-22
 * @version	1.0
 */
public class SaoUserRelationDTO {
	
	private String				userId;		//登录ID
	private Long				refOid;		//来源OID
	private String				refType;	//来源类型 01：业务人员OID	02：基础人员OID	03：科室OID	04：单位OID
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getRefOid() {
		return refOid;
	}
	public void setRefOid(Long refOid) {
		this.refOid = refOid;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
}
