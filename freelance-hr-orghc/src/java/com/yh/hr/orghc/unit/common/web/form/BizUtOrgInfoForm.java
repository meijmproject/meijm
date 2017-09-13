package com.yh.hr.orghc.unit.common.web.form;

import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;


public class BizUtOrgInfoForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.Long  	utOrgOid;	//UtOrgOid
	private java.lang.Long  	utUnitOid;	//UtUnitOid
	private java.lang.String	unitName;	
	private java.lang.Long  	orgOid;	//创建时由基础库回写
	private java.lang.String	orgName;	//OrgName
	private java.lang.Long  	parentOrgOid;	//上级内设机构
	private java.lang.String	parentOrgName;
	private java.lang.String	orgType;	//内设机构类型YHRS0102
	private java.lang.String	orgStatus;	//机构状态
	private java.lang.String	secondNameWork;	//第二名称(合署办公)
	private java.lang.String	secondNameShow;	//第二名称（挂牌）
	private java.lang.String	branchTypeCode;	//内设机构编码（中编办）YHRS0094
	private java.lang.String	levelCode;	//内设机构级别YHRS0093
	private java.util.Date  	establishedDate;	//成立时间
	private java.lang.String  	establishedDateStr;	
	private java.util.Date  	cancelDate;	//撤销日期
	private java.lang.String	orgFunction;	//机构职能
	private java.lang.String	orderOfView;	//排序号
	private java.lang.String	orderOfAll;	//全局排序
	private java.lang.String	remark;	//备注
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date  	updatedDate;	//UpdatedDate

	
	
	public java.lang.Long getUtOrgOid() {
		return utOrgOid;
	}
	public void setUtOrgOid(java.lang.Long utOrgOid) {
		this.utOrgOid = utOrgOid;
	}
	public java.lang.Long getUtUnitOid() {
		return utUnitOid;
	}
	public void setUtUnitOid(java.lang.Long utUnitOid) {
		this.utUnitOid = utUnitOid;
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
	public java.lang.Long getParentOrgOid() {
		return parentOrgOid;
	}
	public void setParentOrgOid(java.lang.Long parentOrgOid) {
		this.parentOrgOid = parentOrgOid;
	}
	public java.lang.String getOrgType() {
		return orgType;
	}
	public void setOrgType(java.lang.String orgType) {
		this.orgType = orgType;
	}
	public java.lang.String getSecondNameWork() {
		return secondNameWork;
	}
	public void setSecondNameWork(java.lang.String secondNameWork) {
		this.secondNameWork = secondNameWork;
	}
	public java.lang.String getSecondNameShow() {
		return secondNameShow;
	}
	public void setSecondNameShow(java.lang.String secondNameShow) {
		this.secondNameShow = secondNameShow;
	}
	public java.lang.String getBranchTypeCode() {
		return branchTypeCode;
	}
	public void setBranchTypeCode(java.lang.String branchTypeCode) {
		this.branchTypeCode = branchTypeCode;
	}
	public java.lang.String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(java.lang.String levelCode) {
		this.levelCode = levelCode;
	}
	public java.util.Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(java.util.Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	public java.lang.String getOrgFunction() {
		return orgFunction;
	}
	public void setOrgFunction(java.lang.String orgFunction) {
		this.orgFunction = orgFunction;
	}
	
	public java.lang.String getOrderOfView() {
		return orderOfView;
	}
	public void setOrderOfView(java.lang.String orderOfView) {
		this.orderOfView = orderOfView;
	}
	public java.lang.String getOrderOfAll() {
		return orderOfAll;
	}
	public void setOrderOfAll(java.lang.String orderOfAll) {
		this.orderOfAll = orderOfAll;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	public java.util.Date getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(java.util.Date establishedDate) {
		if (null != establishedDate) {
			this.establishedDate = establishedDate;
			this.establishedDateStr = DateUtil.formatDate(establishedDate);
		}
	}
	public String getEstablishedDateStr() {
		return establishedDateStr;
	}
	public void setEstablishedDateStr(String establishedDateStr) {
		this.establishedDateStr = establishedDateStr;
		this.establishedDate = DateUtil.parseDate(establishedDateStr);
	}
	public java.lang.String getParentOrgName() {
		return parentOrgName;
	}
	public void setParentOrgName(java.lang.String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	public java.lang.String getOrgStatus() {
		return orgStatus;
	}
	public void setOrgStatus(java.lang.String orgStatus) {
		this.orgStatus = orgStatus;
	}
	public java.lang.String getUnitName() {
		return unitName;
	}
	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}


}
