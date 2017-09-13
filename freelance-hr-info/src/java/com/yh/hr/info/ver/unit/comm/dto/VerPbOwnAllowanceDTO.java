package com.yh.hr.info.ver.unit.comm.dto;

public class VerPbOwnAllowanceDTO{
	/**
     *主键
     **/
	private java.lang.Long ownAllowanceOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *津贴类别编码
     **/
	private java.lang.String allowanceCategoryCode;
    /**
     *津贴类别名称
     **/
	private java.lang.String allowanceCategoryName;
    /**
     *津贴编码
     **/
	private java.lang.String allowanceCode;
    /**
     *津贴名称
     **/
	private java.lang.String allowanceName;
    /**
     *金额
     **/
	private java.lang.Double allowanceAmount;	
    /**
     *开始享受时间
     **/
	private java.util.Date startDate;	
	private java.lang.String startDateStr;	
    /**
     *截止享受时间
     **/
	private java.util.Date endDate;
	private java.lang.String endDateStr;	
    /**
     *备注
     **/
	private java.lang.String remark;	

	public VerPbOwnAllowanceDTO() {
		
	}
	
	public java.lang.String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(java.lang.String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public java.lang.String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(java.lang.String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public java.lang.Long getOwnAllowanceOid() {
		return ownAllowanceOid;
	}

	public void setOwnAllowanceOid(java.lang.Long ownAllowanceOid) {
		this.ownAllowanceOid = ownAllowanceOid;
	}

	public java.lang.String getAllowanceCategoryCode() {
		return allowanceCategoryCode;
	}

	public void setAllowanceCategoryCode(java.lang.String allowanceCategoryCode) {
		this.allowanceCategoryCode = allowanceCategoryCode;
	}

	public java.lang.String getAllowanceCategoryName() {
		return allowanceCategoryName;
	}

	public void setAllowanceCategoryName(java.lang.String allowanceCategoryName) {
		this.allowanceCategoryName = allowanceCategoryName;
	}

	public java.lang.String getAllowanceCode() {
		return allowanceCode;
	}

	public void setAllowanceCode(java.lang.String allowanceCode) {
		this.allowanceCode = allowanceCode;
	}

	public java.lang.String getAllowanceName() {
		return allowanceName;
	}

	public void setAllowanceName(java.lang.String allowanceName) {
		this.allowanceName = allowanceName;
	}

	public java.lang.Double getAllowanceAmount() {
		return allowanceAmount;
	}

	public void setAllowanceAmount(java.lang.Double allowanceAmount) {
		this.allowanceAmount = allowanceAmount;
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

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}
}
