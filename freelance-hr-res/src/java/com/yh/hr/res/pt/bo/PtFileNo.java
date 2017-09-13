package com.yh.hr.res.pt.bo;

import com.yh.platform.core.bo.BaseBo;

/** 
 *@description		文号信息
 *@author           lenghh
 *@created          2016-11-10
 *@version          1.0
 *
 */
public class PtFileNo extends BaseBo 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5499476655665736731L;
	private java.lang.Long		fileNoOid ;		//主键
	private	java.lang.String	fileNo;					//文号
	private java.lang.String	fileNoDesc;				//文号描述
	private java.lang.String	fileNoType;				//文号类别
	private java.lang.String	fileNoYear;				//年度
	private java.lang.String	fileNoPre;				//文号前缀
	private java.lang.Long		maxNo;					//当前最大号
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date  	updatedDate;	//UpdatedDate
	
	public java.lang.Long getFileNoOid() {
		return fileNoOid;
	}
	public void setFileNoOid(java.lang.Long fileNoOid) {
		this.fileNoOid = fileNoOid;
	}
	public java.lang.String getFileNo() {
		return fileNo;
	}
	public void setFileNo(java.lang.String fileNo) {
		this.fileNo = fileNo;
	}
	public java.lang.String getFileNoDesc() {
		return fileNoDesc;
	}
	public void setFileNoDesc(java.lang.String fileNoDesc) {
		this.fileNoDesc = fileNoDesc;
	}
	public java.lang.String getFileNoType() {
		return fileNoType;
	}
	public void setFileNoType(java.lang.String fileNoType) {
		this.fileNoType = fileNoType;
	}
	public java.lang.String getFileNoYear() {
		return fileNoYear;
	}
	public void setFileNoYear(java.lang.String fileNoYear) {
		this.fileNoYear = fileNoYear;
	}
	public java.lang.String getFileNoPre() {
		return fileNoPre;
	}
	public void setFileNoPre(java.lang.String fileNoPre) {
		this.fileNoPre = fileNoPre;
	}
	public java.lang.Long getMaxNo() {
		return maxNo;
	}
	public void setMaxNo(java.lang.Long maxNo) {
		this.maxNo = maxNo;
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
