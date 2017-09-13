package com.yh.hr.res.pt.bo;

import com.yh.platform.core.bo.BaseBo;

/**
 * 报表打印采集信息
 * @author huangyj
 * 2016-11-17
 */
public class PtReportData extends BaseBo
{
	private static final long serialVersionUID = 8378498860463828761L;

	private Long ptReportDataOid;
	
	private Long taskOid;
	
	private String reportCode;
	
	private String fieldName;
	
	private String fieldCode;
	
	private String fieldValue;
	
	private String createdByCode;
	
	private String createdByName;
	
	private java.util.Date createdDate;
	
	private String updatedByCode;
	
	private String updatedByName;
	
	private java.util.Date updatedDate;

	public PtReportData(){}
	
	public PtReportData(String fieldName, String fieldCode) {
		this.fieldName = fieldName;
		this.fieldCode = fieldCode;
	}

	public Long getPtReportDataOid() {
		return ptReportDataOid;
	}

	public void setPtReportDataOid(Long ptReportDataOid) {
		this.ptReportDataOid = ptReportDataOid;
	}

	public Long getTaskOid() {
		return taskOid;
	}

	public void setTaskOid(Long taskOid) {
		this.taskOid = taskOid;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedByCode() {
		return createdByCode;
	}

	public void setCreatedByCode(String createdByCode) {
		this.createdByCode = createdByCode;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getUpdatedByCode() {
		return updatedByCode;
	}

	public void setUpdatedByCode(String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
}
