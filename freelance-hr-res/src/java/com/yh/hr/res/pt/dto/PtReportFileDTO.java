package com.yh.hr.res.pt.dto;

/** 
 *@description		报表信息
 *@author           lenghh
 *@created          2016-11-10
 *@version          1.0
 *
 */
public class PtReportFileDTO
{
	private java.lang.Long		reportFileOid ;		//主键
	private	java.lang.String	reportFileDesc;		//报表描述
	private	java.lang.String	reportFileType;		//报表类型
	private java.lang.String	fileNoType;			//文号类别
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date  	updatedDate;	//UpdatedDate
	
	public java.lang.Long getReportFileOid() {
		return reportFileOid;
	}
	public void setReportFileOid(java.lang.Long reportFileOid) {
		this.reportFileOid = reportFileOid;
	}
	public java.lang.String getReportFileDesc() {
		return reportFileDesc;
	}
	public void setReportFileDesc(java.lang.String reportFileDesc) {
		this.reportFileDesc = reportFileDesc;
	}
	public java.lang.String getReportFileType() {
		return reportFileType;
	}
	public void setReportFileType(java.lang.String reportFileType) {
		this.reportFileType = reportFileType;
	}
	public java.lang.String getFileNoType() {
		return fileNoType;
	}
	public void setFileNoType(java.lang.String fileNoType) {
		this.fileNoType = fileNoType;
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
