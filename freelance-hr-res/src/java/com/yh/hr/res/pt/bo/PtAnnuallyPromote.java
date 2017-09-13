package com.yh.hr.res.pt.bo;

import com.yh.platform.core.bo.BaseBo;
public class PtAnnuallyPromote extends BaseBo{
	/**
	 * 年度考核工资信息表
	 */

	private static final long serialVersionUID = 8487044110979845336L;
	private java.lang.Long  	ptAnnuallyPromoteInfoOid;	//ptAnnuallyPromoteInfoOid主键
	private java.lang.Long  	bizPersonOid;	//BizPersonOid
	private java.lang.Integer   promoteYear;//考核年度
	private java.lang.String    promoteResult;//考核计算结果
	private java.lang.String    status;//状态
	private java.lang.String    isCaculate;//是否已计算
	private java.lang.String createdByCode;
	private java.lang.String createdByName;
	private java.util.Date createdDate;
	private java.lang.String updatedByCode;
	private java.lang.String updatedByName; 
	private java.util.Date updatedDate;
	
	public PtAnnuallyPromote(){
		
	}
	
	public java.lang.String getIsCaculate() {
		return isCaculate;
	}

	public void setIsCaculate(java.lang.String isCaculate) {
		this.isCaculate = isCaculate;
	}

	public java.lang.Long getPtAnnuallyPromoteInfoOid() {
		return ptAnnuallyPromoteInfoOid;
	}

	public void setPtAnnuallyPromoteInfoOid(java.lang.Long ptAnnuallyPromoteInfoOid) {
		this.ptAnnuallyPromoteInfoOid = ptAnnuallyPromoteInfoOid;
	}

	public java.lang.Long getBizPersonOid() {
		return bizPersonOid;
	}

	public void setBizPersonOid(java.lang.Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}

	public java.lang.Integer getPromoteYear() {
		return promoteYear;
	}

	public void setPromoteYear(java.lang.Integer promoteYear) {
		this.promoteYear = promoteYear;
	}

	public java.lang.String getPromoteResult() {
		return promoteResult;
	}

	public void setPromoteResult(java.lang.String promoteResult) {
		this.promoteResult = promoteResult;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
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
