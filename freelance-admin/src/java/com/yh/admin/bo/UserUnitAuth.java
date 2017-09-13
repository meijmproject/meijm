package com.yh.admin.bo;

import com.yh.platform.core.bo.BaseBo;

/**
 * 用户单位权限
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/25
 */
public class UserUnitAuth extends BaseBo {
	private static final long	serialVersionUID	= 6411581563805527676L;
	
	private Long				unitOid;
	private String				unitName;
	private String				unitStatus;
	private String				userId;
	private Long				systemPositionOid;	//岗位
	private String				unitKind;			//单位性质
	private String				unitCategoryCode;	//系统类别
//	private String				unitCode;			//单位编码
	
	
	public Long getUnitOid() {
		return unitOid;
	}
	public void setUnitOid(Long unitOid) {
		this.unitOid = unitOid;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitStatus() {
		return unitStatus;
	}
	public void setUnitStatus(String unitStatus) {
		this.unitStatus = unitStatus;
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
	public String getUnitKind() {
		return unitKind;
	}
	public void setUnitKind(String unitKind) {
		this.unitKind = unitKind;
	}
	public String getUnitCategoryCode() {
		return unitCategoryCode;
	}
	public void setUnitCategoryCode(String unitCategoryCode) {
		this.unitCategoryCode = unitCategoryCode;
	}
}
