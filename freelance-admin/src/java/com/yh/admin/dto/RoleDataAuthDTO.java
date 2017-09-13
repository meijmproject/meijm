package com.yh.admin.dto;

import java.util.List;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/09/13
 */
public class RoleDataAuthDTO {
	private Long	roleDataAuthOid;
	private String	authCode;
	private String	authType;
	private String	authLevel;
	private Long	roleId;
	private String	isOnlyOwn;
	
	private String	authCodeName;//authCode 对应的名称
	
	
//	private java.lang.Long		organizationOid;	// OrganizationOid
	private java.lang.Long		unitOid;			// UnitOid		
	private java.lang.Long		orgOid;
	public java.lang.Long getOrgOid() {
		return orgOid;
	}
	public void setOrgOid(java.lang.Long orgOid) {
		this.orgOid = orgOid;
	}
	public java.lang.String getOrgStatus() {
		return orgStatus;
	}
	public void setOrgStatus(java.lang.String orgStatus) {
		this.orgStatus = orgStatus;
	}
	public java.lang.Long getUnitOid() {
		return unitOid;
	}
	public void setUnitOid(java.lang.Long unitOid) {
		this.unitOid = unitOid;
	}
	//	private java.lang.String	unitName;			// 单位名称		对应authCodeName
//	private java.lang.String	unitKind;			// 单位性质YHRS0090
//	private java.lang.String	unitCategoryCode;	// 系统类别YHRS0091
//	private java.lang.String	unitLevelCode;		// 机构级别YHRS0093
//	private java.lang.String	unitAreaCode;		// 区域代码YHRS0006
	private java.lang.String	unitStatus;			// 单位状态YHRS0101
	private java.lang.String	orgStatus;	
//	private java.lang.String	orderOfAll;			// 单位全局排序
	private java.lang.String	unitCode;			// 用于权限查询    对应authCode
	
	
	public java.lang.String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(java.lang.String unitCode) {
		this.unitCode = unitCode;
	}
	public List<String> getPersonTypeList() {
		return personTypeList;
	}
	public void setPersonTypeList(List<String> personTypeList) {
		this.personTypeList = personTypeList;
	}
	private List<String> personTypeList;//人员类别节点list
	
	private List<String> orgOidList;//单位节点list
	public List<String> getOrgOidList() {
		return orgOidList;
	}
	public void setOrgOidList(List<String> orgOidList) {
		this.orgOidList = orgOidList;
	}
	private List<String> onlyOwnList;//是否包含本身单位节点List
	
	private boolean checked;//是否选中
	private boolean authAllUnitCode;//授予所有单位权限
	
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
	public String getIsOnlyOwn() {
		return isOnlyOwn;
	}
	public void setIsOnlyOwn(String isOnlyOwn) {
		this.isOnlyOwn = isOnlyOwn;
	}
	public String getAuthCodeName() {
		return authCodeName;
	}
	public void setAuthCodeName(String authCodeName) {
		this.authCodeName = authCodeName;
	}
	public List<String> getOnlyOwnList() {
		return onlyOwnList;
	}
	public void setOnlyOwnList(List<String> onlyOwnList) {
		this.onlyOwnList = onlyOwnList;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public java.lang.String getUnitStatus() {
		return unitStatus;
	}
	public void setUnitStatus(java.lang.String unitStatus) {
		this.unitStatus = unitStatus;
	}
	public boolean isAuthAllUnitCode() {
		return authAllUnitCode;
	}
	public void setAuthAllUnitCode(boolean authAllUnitCode) {
		this.authAllUnitCode = authAllUnitCode;
	}
}
