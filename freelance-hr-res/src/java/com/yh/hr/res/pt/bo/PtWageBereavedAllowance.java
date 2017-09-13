package com.yh.hr.res.pt.bo;

import com.yh.platform.core.bo.BaseBo;
/**
 * 遗属生活困难补助do
 * @author huanglm
 * 时间:2016-11-30
 */
public class PtWageBereavedAllowance extends BaseBo {
	
	private static final long serialVersionUID = -1500970057813116291L;
	private java.lang.Long		ptBereavedAllowanceInfoOid; // ptBereavedAllowanceInfoOid
	private java.lang.Long		bizPersonOid;				// BizPersonOid
	private java.lang.Long		personOid;					// 人员主键	
	private java.lang.String	bereavedName;						// 姓名
	private java.lang.String	bereavedSex;					// 性别代码YHRS0001	
	private java.util.Date  	birthday;	//出生年月（精确到月，如YYYYMM）
	private java.lang.String      relationshipBetweenDead;    //与死者关系YHRS0024
	private java.lang.String      householdType;    //户籍类型YHRS0118
	private java.lang.String      address;    //居住地点
	private java.util.Date  	allowanceStartDate;	//补助开始时间
	
	private java.util.Date  	allowanceEndDate;	//补助截止时间
	private java.lang.Long      monthlyAllowanceAmount;    //每月补助金额
	private java.lang.String      remark;    //备注
	
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date  	updatedDate;	//UpdatedDate
	
	
	public java.util.Date getBirthday() {
		return birthday;
	}
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}
	public java.lang.Long getPtBereavedAllowanceInfoOid() {
		return ptBereavedAllowanceInfoOid;
	}
	public void setPtBereavedAllowanceInfoOid(
			java.lang.Long ptBereavedAllowanceInfoOid) {
		this.ptBereavedAllowanceInfoOid = ptBereavedAllowanceInfoOid;
	}
	public java.lang.Long getBizPersonOid() {
		return bizPersonOid;
	}
	public void setBizPersonOid(java.lang.Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}
	public java.lang.Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(java.lang.Long personOid) {
		this.personOid = personOid;
	}
	public java.lang.String getBereavedName() {
		return bereavedName;
	}
	public void setBereavedName(java.lang.String bereavedName) {
		this.bereavedName = bereavedName;
	}
	public java.lang.String getBereavedSex() {
		return bereavedSex;
	}
	public void setBereavedSex(java.lang.String bereavedSex) {
		this.bereavedSex = bereavedSex;
	}
	public java.lang.String getRelationshipBetweenDead() {
		return relationshipBetweenDead;
	}
	public void setRelationshipBetweenDead(java.lang.String relationshipBetweenDead) {
		this.relationshipBetweenDead = relationshipBetweenDead;
	}
	public java.lang.String getHouseholdType() {
		return householdType;
	}
	public void setHouseholdType(java.lang.String householdType) {
		this.householdType = householdType;
	}
	public java.lang.String getAddress() {
		return address;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	public java.util.Date getAllowanceStartDate() {
		return allowanceStartDate;
	}
	public void setAllowanceStartDate(java.util.Date allowanceStartDate) {
		this.allowanceStartDate = allowanceStartDate;
	}
	public java.util.Date getAllowanceEndDate() {
		return allowanceEndDate;
	}
	public void setAllowanceEndDate(java.util.Date allowanceEndDate) {
		this.allowanceEndDate = allowanceEndDate;
	}
	public java.lang.Long getMonthlyAllowanceAmount() {
		return monthlyAllowanceAmount;
	}
	public void setMonthlyAllowanceAmount(java.lang.Long monthlyAllowanceAmount) {
		this.monthlyAllowanceAmount = monthlyAllowanceAmount;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public java.lang.String getCreatedByCode() {
		return createdByCode;
	}
	public void setCreatedByCode(java.lang.String createdByCode) {
		this.createdByCode = createdByCode;
	}
	public java.lang.String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(java.lang.String createdByName) {
		this.createdByName = createdByName;
	}
	public java.util.Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}
	public java.lang.String getUpdatedByCode() {
		return updatedByCode;
	}
	public void setUpdatedByCode(java.lang.String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}
	public java.lang.String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(java.lang.String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
