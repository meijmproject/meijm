package com.yh.admin.dto;

public class UserPositionInfoDTO {

	private java.lang.Long	systemPositionOid;	//系统岗位主键
	private java.lang.String	systemPositionName;	//系统岗位名称
	private java.lang.String	systemPositionDesc;	//系统岗位描述
	private java.lang.String dataRoleName;
	private java.lang.String functionRoleName;
	private java.util.Date		expiredDt;			// 失效日期
	private java.util.Date		effectiveDt;		// 生效日期
	private java.lang.String  userId;   			//用户ID
	public java.lang.Long getSystemPositionOid() {
		return systemPositionOid;
	}
	public void setSystemPositionOid(java.lang.Long systemPositionOid) {
		this.systemPositionOid = systemPositionOid;
	}
	public java.lang.String getSystemPositionName() {
		return systemPositionName;
	}
	public void setSystemPositionName(java.lang.String systemPositionName) {
		this.systemPositionName = systemPositionName;
	}
	public java.lang.String getSystemPositionDesc() {
		return systemPositionDesc;
	}
	public void setSystemPositionDesc(java.lang.String systemPositionDesc) {
		this.systemPositionDesc = systemPositionDesc;
	}
	
	
	public java.util.Date getEffectiveDt() {
		return effectiveDt;
	}
	public void setEffectiveDt(java.util.Date effectiveDt) {
		this.effectiveDt = effectiveDt;
	}
	public java.util.Date getExpiredDt() {
		return expiredDt;
	}
	public void setExpiredDt(java.util.Date expiredDt) {
		this.expiredDt = expiredDt;
	}
	public java.lang.String getUserId() {
		return userId;
	}
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	public java.lang.String getDataRoleName() {
		return dataRoleName;
	}
	public void setDataRoleName(java.lang.String dataRoleName) {
		this.dataRoleName = dataRoleName;
	}
	public java.lang.String getFunctionRoleName() {
		return functionRoleName;
	}
	public void setFunctionRoleName(java.lang.String functionRoleName) {
		this.functionRoleName = functionRoleName;
	}
}
