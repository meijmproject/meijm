package com.yh.hr.info.dataimport.unit.dto;

import com.yh.platform.core.util.DateUtil;

/**
 * 单位机构导入DTO
 * @author chenjl
 * @date 2017-03-23
 * @version 1.0
 */
public class ImportUnitDTO {
	
	private java.lang.Long  	unitOid;	        //UnitOid
	private java.lang.String	unitName;	        //机构名称
	private java.lang.String	unitShortName;	    //单位简称
	private java.lang.String	budgetFromCode;	    //经费来源
	private java.lang.String	budgetFromName;	
	private java.lang.String	corporationCode;	//组织机构代码
	private java.lang.String	unitCreditNo;	    //统一社会信用代码
	private java.lang.String	establishedDateStr;	//成立时间
	private java.util.Date   	establishedDate;	//成立时间
	private java.lang.String	orderOfView;	    //排序号
	private java.lang.String	contacter;	        //联系人
	private java.lang.String	mobilePhone;	    //手机
	private java.lang.String	phone;	            //联系电话
	private java.lang.String	email;	            //电子邮箱
	private java.lang.String	fax;	            //传真
	private java.lang.String	address;	        //单位地址
	private java.lang.String	remark;	            //备注
	private java.lang.String	message;	        //错误信息
	
	private java.lang.Long  	orgUnitOid;	        
	private java.lang.String	orgUnitName;	        
	private java.lang.Long  	orgOid;	            //OrgOid
	private java.lang.String	orgName;	        //OrgName
	private java.lang.String	orgCode;	        //机构编号
	private java.lang.String	orgOrderOfView;	    //排序号
	private java.lang.Long  	parentOrgOid;	    //上级内设机构
	private java.lang.String	parentOrgName;
	private java.lang.String  	orgEstablishedDateStr;//成立时间
	private java.util.Date   	orgEstablishedDate;	//成立时间
	private java.lang.String	orgContacter;	    //联系人
	private java.lang.String	orgMobilePhone;	    //手机
	private java.lang.String	orgEmail;	        //电子邮箱
	private java.lang.String	orgPhone;	        //联系电话
	private java.lang.String	orgFax;	            //传真
	private java.lang.String	orgAddress;	        //单位地址
	private java.lang.String	orgRemark;	        //备注
	private java.lang.String	orgMessage;	        //错误信息
	private java.lang.String	orgType;			//内设机构类型YHRS0102
	private java.lang.String	orgTypeName;
	private java.lang.String    orgCategory;        //所属科室类型YHRS0102

	
	public java.lang.Long getUnitOid() {
		return unitOid;
	}
	public void setUnitOid(java.lang.Long unitOid) {
		this.unitOid = unitOid;
	}
	public java.lang.String getUnitName() {
		return unitName;
	}
	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}
	public java.lang.String getUnitShortName() {
		return unitShortName;
	}
	public void setUnitShortName(java.lang.String unitShortName) {
		this.unitShortName = unitShortName;
	}
	public java.lang.String getBudgetFromCode() {
		return budgetFromCode;
	}
	public void setBudgetFromCode(java.lang.String budgetFromCode) {
		this.budgetFromCode = budgetFromCode;
	}
	public java.lang.String getBudgetFromName() {
		return budgetFromName;
	}
	public void setBudgetFromName(java.lang.String budgetFromName) {
		this.budgetFromName = budgetFromName;
	}
	public java.lang.String getCorporationCode() {
		return corporationCode;
	}
	public void setCorporationCode(java.lang.String corporationCode) {
		this.corporationCode = corporationCode;
	}
	public java.lang.String getUnitCreditNo() {
		return unitCreditNo;
	}
	public void setUnitCreditNo(java.lang.String unitCreditNo) {
		this.unitCreditNo = unitCreditNo;
	}
	public java.lang.String getOrderOfView() {
		return orderOfView;
	}
	public void setOrderOfView(java.lang.String orderOfView) {
		this.orderOfView = orderOfView;
	}
	public java.lang.String getContacter() {
		return contacter;
	}
	public void setContacter(java.lang.String contacter) {
		this.contacter = contacter;
	}
	public java.lang.String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(java.lang.String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public java.lang.String getPhone() {
		return phone;
	}
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	public java.lang.String getEmail() {
		return email;
	}
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	public java.lang.String getFax() {
		return fax;
	}
	public void setFax(java.lang.String fax) {
		this.fax = fax;
	}
	public java.lang.String getAddress() {
		return address;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public java.lang.Long getOrgUnitOid() {
		return orgUnitOid;
	}
	public void setOrgUnitOid(java.lang.Long orgUnitOid) {
		this.orgUnitOid = orgUnitOid;
	}
	public java.lang.String getOrgUnitName() {
		return orgUnitName;
	}
	public void setOrgUnitName(java.lang.String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}
	public java.lang.Long getOrgOid() {
		return orgOid;
	}
	public void setOrgOid(java.lang.Long orgOid) {
		this.orgOid = orgOid;
	}
	public java.lang.String getOrgName() {
		return orgName;
	}
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}
	public java.lang.String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}
	public java.lang.String getOrgOrderOfView() {
		return orgOrderOfView;
	}
	public void setOrgOrderOfView(java.lang.String orgOrderOfView) {
		this.orgOrderOfView = orgOrderOfView;
	}
	public java.lang.Long getParentOrgOid() {
		return parentOrgOid;
	}
	public void setParentOrgOid(java.lang.Long parentOrgOid) {
		this.parentOrgOid = parentOrgOid;
	}
	public java.lang.String getParentOrgName() {
		return parentOrgName;
	}
	public void setParentOrgName(java.lang.String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	public java.lang.String getOrgContacter() {
		return orgContacter;
	}
	public void setOrgContacter(java.lang.String orgContacter) {
		this.orgContacter = orgContacter;
	}
	public java.lang.String getOrgMobilePhone() {
		return orgMobilePhone;
	}
	public void setOrgMobilePhone(java.lang.String orgMobilePhone) {
		this.orgMobilePhone = orgMobilePhone;
	}
	public java.lang.String getOrgEmail() {
		return orgEmail;
	}
	public void setOrgEmail(java.lang.String orgEmail) {
		this.orgEmail = orgEmail;
	}
	public java.lang.String getOrgPhone() {
		return orgPhone;
	}
	public void setOrgPhone(java.lang.String orgPhone) {
		this.orgPhone = orgPhone;
	}
	public java.lang.String getOrgFax() {
		return orgFax;
	}
	public void setOrgFax(java.lang.String orgFax) {
		this.orgFax = orgFax;
	}
	public java.lang.String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(java.lang.String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public java.lang.String getOrgRemark() {
		return orgRemark;
	}
	public void setOrgRemark(java.lang.String orgRemark) {
		this.orgRemark = orgRemark;
	}
	public java.lang.String getEstablishedDateStr() {
		return establishedDateStr;
	}
	public void setEstablishedDateStr(java.lang.String establishedDateStr) {
		this.establishedDateStr = establishedDateStr;
		this.establishedDate    = DateUtil.parseDate(establishedDateStr);
	}
	public java.util.Date getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(java.util.Date establishedDate) {
		this.establishedDate = establishedDate;
		this.establishedDateStr = DateUtil.formatDate(establishedDate);
	}
	public java.lang.String getOrgEstablishedDateStr() {
		return orgEstablishedDateStr;
	}
	public void setOrgEstablishedDateStr(java.lang.String orgEstablishedDateStr) {
		this.orgEstablishedDateStr = orgEstablishedDateStr;
		this.orgEstablishedDate    = DateUtil.parseDate(orgEstablishedDateStr);

	}
	public java.util.Date getOrgEstablishedDate() {
		return orgEstablishedDate;
	}
	public void setOrgEstablishedDate(java.util.Date orgEstablishedDate) {
		this.orgEstablishedDate = orgEstablishedDate;
		this.orgEstablishedDateStr = DateUtil.formatDate(orgEstablishedDate);
	}
	public java.lang.String getMessage() {
		return message;
	}
	public void setMessage(java.lang.String message) {
		this.message = message;
	}
	public java.lang.String getOrgMessage() {
		return orgMessage;
	}
	public void setOrgMessage(java.lang.String orgMessage) {
		this.orgMessage = orgMessage;
	}
	public java.lang.String getOrgType() {
		return orgType;
	}
	public void setOrgType(java.lang.String orgType) {
		this.orgType = orgType;
	}
	public java.lang.String getOrgTypeName() {
		return orgTypeName;
	}
	public void setOrgTypeName(java.lang.String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}
	public java.lang.String getOrgCategory() {
		return orgCategory;
	}
	public void setOrgCategory(java.lang.String orgCategory) {
		this.orgCategory = orgCategory;
	}

}