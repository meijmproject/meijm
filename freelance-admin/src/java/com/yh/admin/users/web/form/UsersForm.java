package com.yh.admin.users.web.form;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

/**
 * @author wangj
 * 
 * 
 */
public class UsersForm extends ValidatorForm {

	private static final long	serialVersionUID	= 135313535360334027L;

	private java.lang.Long		userOid;			// 主键
	private java.lang.String	userId;				// 用户登陆ID
	private java.lang.String	password;			// 登陆密码
	private java.lang.String	userName;			// 用户姓名
	private java.lang.String	userSex;			// 性别
	private java.lang.String	userSexDesc;		// 性别
	private java.lang.String	address;			// 用户地址
	private java.lang.String	contactPhone;		// 联系电话
	private java.util.Date		registDate;			// 注册日期
	private java.lang.String	registDateStr;		// 注册日期
	private java.util.Date		effectiveDate;		// 生效日期
	private java.lang.String	effectiveDateStr;	// 生效日期
	private java.util.Date		expiredDate;		// 失效日期
	private java.lang.String	expiredDateStr;		// 失效日期
	private java.lang.String	userType;			// 用户类型
	private java.lang.String	userStatus;			// 用户状态
	private java.lang.Long		unitId;				// 所属单位OID
	private java.lang.String	unitName;			// 所属单位名称
	private java.lang.Long		deptId;				// 所属部门OID
	private java.lang.String	deptName;			// 所属部门名称
	private java.lang.String	updatedByCode;		// 修改人代码
	private java.lang.String	updatedByName;		// 修改人姓名
	private java.util.Date		updatedDate;		// 修改时间
	private java.lang.String	defaultLoginSystem;	// 默认登录系统
	
	private Long systemPositionOid;                 //用户岗位信息
	
	private Long refOid;							//来源OID
	private String refType;							//来源类型

	public void setUserOid(java.lang.Long userOid) {
		this.userOid = userOid;
	}

	public java.lang.Long getUserOid() {
		return this.userOid;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setUserSex(java.lang.String userSex) {
		this.userSex = userSex;
	}

	public java.lang.String getUserSex() {
		return this.userSex;
	}

	public java.lang.String getUserSexDesc() {
		return userSexDesc;
	}

	public void setUserSexDesc(java.lang.String userSexDesc) {
		this.userSexDesc = userSexDesc;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setContactPhone(java.lang.String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public java.lang.String getContactPhone() {
		return this.contactPhone;
	}

	public void setRegistDate(java.util.Date registDate) {
		this.registDate = registDate;
		if (registDate != null) {
			this.registDateStr = DateUtil.formatDate(registDate);
		}
	}

	public java.util.Date getRegistDate() {
		return this.registDate;
	}

	public void setEffectiveDate(java.util.Date effectiveDate) {
		this.effectiveDate = effectiveDate;
		if (effectiveDate != null) {
			this.effectiveDateStr = DateUtil.formatDate(effectiveDate);
		}
	}

	public java.util.Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setExpiredDate(java.util.Date expiredDate) {
		this.expiredDate = expiredDate;
		if (expiredDate != null) {
			this.expiredDateStr = DateUtil.formatDate(expiredDate);
		}
	}

	public java.util.Date getExpiredDate() {
		return this.expiredDate;
	}

	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	public java.lang.String getUserType() {
		return this.userType;
	}

	public void setUserStatus(java.lang.String userStatus) {
		this.userStatus = userStatus;
	}

	public java.lang.String getUserStatus() {
		return this.userStatus;
	}

	public void setUnitId(java.lang.Long unitId) {
		this.unitId = unitId;
	}

	public java.lang.Long getUnitId() {
		return this.unitId;
	}

	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}

	public java.lang.String getUnitName() {
		return this.unitName;
	}

	public void setDeptId(java.lang.Long deptId) {
		this.deptId = deptId;
	}

	public java.lang.Long getDeptId() {
		return this.deptId;
	}

	public void setDeptName(java.lang.String deptName) {
		this.deptName = deptName;
	}

	public java.lang.String getDeptName() {
		return this.deptName;
	}

	public void setUpdatedByCode(java.lang.String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}

	public java.lang.String getUpdatedByCode() {
		return this.updatedByCode;
	}

	public void setUpdatedByName(java.lang.String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public java.lang.String getUpdatedByName() {
		return this.updatedByName;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public java.util.Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setDefaultLoginSystem(java.lang.String defaultLoginSystem) {
		this.defaultLoginSystem = defaultLoginSystem;
	}

	public java.lang.String getDefaultLoginSystem() {
		return this.defaultLoginSystem;
	}

	public java.lang.String getEffectiveDateStr() {
		return effectiveDateStr;
	}

	public void setEffectiveDateStr(java.lang.String effectiveDateStr) {
		this.effectiveDateStr = effectiveDateStr;
		if (StringUtils.isNotEmpty(effectiveDateStr)) {
			this.effectiveDate = DateUtil.parseDate(effectiveDateStr);
		}
	}

	public java.lang.String getExpiredDateStr() {
		return expiredDateStr;
	}

	public void setExpiredDateStr(java.lang.String expiredDateStr) {
		this.expiredDateStr = expiredDateStr;
		if (StringUtils.isNotEmpty(expiredDateStr)) {
			this.expiredDate = DateUtil.parseDate(expiredDateStr);
		}
	}

	public java.lang.String getRegistDateStr() {
		return registDateStr;
	}

	public void setRegistDateStr(java.lang.String registDateStr) {
		this.registDateStr = registDateStr;
		if (StringUtils.isNotEmpty(registDateStr)) {
			this.registDate = DateUtil.parseDate(registDateStr);
		}
	}

	public Long getSystemPositionOid() {
		return systemPositionOid;
	}

	public void setSystemPositionOid(Long systemPositionOid) {
		this.systemPositionOid = systemPositionOid;
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
