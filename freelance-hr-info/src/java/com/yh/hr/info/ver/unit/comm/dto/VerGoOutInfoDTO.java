package com.yh.hr.info.ver.unit.comm.dto;

public class VerGoOutInfoDTO {

	/**
     *主键ID
     **/
	private java.lang.Long goOutOid;
    /**
     *人员ID
     **/
	private java.lang.Long personOid;
    /**
     *外出类型 1-参会;2-讲课;3-短期;4-培训;5-交流;6-学习
     **/
	private java.lang.String goOutType;
    /**
     *出访机构
     **/
	private java.lang.String visitOrg;
    /**
     *外出地点
     **/
	private java.lang.String goOutAddress;
    /**
     *经费来源
     **/
	private java.lang.String budgetFrom;
    /**
     *经费预算
     **/
	private java.lang.Float fundsBudget;
    /**
     *外出时间
     **/
	private java.util.Date startDate;
    /**
     *返回时间
     **/
	private java.util.Date endDate;
    /**
     *外出天数
     **/
	private java.lang.Float dayCount;
    /**
     *工作交接人
     **/
	private java.lang.String handoverMan;
    /**
     *外出事由
     **/
	private java.lang.String reason;
    /**
     *备注
     **/
	private java.lang.String remark;
    /**
     *创建人ID
     **/
	private java.lang.String createBy;
    /**
     *创建人名称
     **/
	private java.lang.String createName;
    /**
     *创建时间
     **/
	private java.util.Date createDate;
    /**
     *修改人ID
     **/
	private java.lang.String updateBy;
    /**
     *修改人名称
     **/
	private java.lang.String updateName;
    /**
     *修改时间
     **/
	private java.util.Date updateDate;
	
	/**
     *紧急联系
     **/
	private java.lang.String emergContact;
    /**
     *紧急联系人电话
     **/
	private java.lang.String emergPhone;
	
	public VerGoOutInfoDTO(){}
	
	public java.lang.Long getGoOutOid() {
		return goOutOid;
	}
	public void setGoOutOid(java.lang.Long goOutOid) {
		this.goOutOid = goOutOid;
	}
	public java.lang.Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(java.lang.Long personOid) {
		this.personOid = personOid;
	}
	public java.lang.String getGoOutType() {
		return goOutType;
	}
	public void setGoOutType(java.lang.String goOutType) {
		this.goOutType = goOutType;
	}
	public java.lang.String getVisitOrg() {
		return visitOrg;
	}
	public void setVisitOrg(java.lang.String visitOrg) {
		this.visitOrg = visitOrg;
	}
	public java.lang.String getGoOutAddress() {
		return goOutAddress;
	}
	public void setGoOutAddress(java.lang.String goOutAddress) {
		this.goOutAddress = goOutAddress;
	}
	public java.lang.String getBudgetFrom() {
		return budgetFrom;
	}
	public void setBudgetFrom(java.lang.String budgetFrom) {
		this.budgetFrom = budgetFrom;
	}
	public java.lang.Float getFundsBudget() {
		return fundsBudget;
	}
	public void setFundsBudget(java.lang.Float fundsBudget) {
		this.fundsBudget = fundsBudget;
	}
	public java.util.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	public java.util.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	public java.lang.String getHandoverMan() {
		return handoverMan;
	}
	public void setHandoverMan(java.lang.String handoverMan) {
		this.handoverMan = handoverMan;
	}
	public java.lang.String getReason() {
		return reason;
	}
	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public java.lang.String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}
	public java.lang.String getCreateName() {
		return createName;
	}
	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.lang.String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}
	public java.lang.String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}
	public java.util.Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public java.lang.Float getDayCount() {
		return dayCount;
	}

	public void setDayCount(java.lang.Float dayCount) {
		this.dayCount = dayCount;
	}

	public java.lang.String getEmergContact() {
		return emergContact;
	}

	public void setEmergContact(java.lang.String emergContact) {
		this.emergContact = emergContact;
	}

	public java.lang.String getEmergPhone() {
		return emergPhone;
	}

	public void setEmergPhone(java.lang.String emergPhone) {
		this.emergPhone = emergPhone;
	}
	

}
