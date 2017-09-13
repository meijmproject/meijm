package com.yh.admin.bo;

import com.yh.platform.core.bo.BaseBo;

/**
 * @description 用户信息关系表BO
 * @author	wangx
 * @date 2017-05-15
 * @version	1.0
 */
public class UserRelation extends BaseBo {
	
	private static final long serialVersionUID = 2045408543899038458L;
	
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
