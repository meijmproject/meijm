package com.yh.hr.info.warning.bo;

import java.util.Date;

import com.yh.platform.core.bo.BaseBo;


/**
 * @description 聘任制试用期到期预警
 * @author zhangsx
 * @created 2013-7-23
 * @version 1.0
 * 
 */
public class BizFullProbationRule extends BaseBo implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3118794367956348536L;
	
	private Long bizFullProbationOid;
	/**
	 * 预警事项代码
	 */
	private String itemCode;
	
	
	/**
	 * 人员类别
	 */
	private String personType;
	
	/**
	 * 试用期时长（天）
	 */
	private Integer probationDays;
	
	private String createdBy;
	private String updatedBy;
	private Date createdTs;
	private Date updatedTs;
	public Long getBizFullProbationOid() {
		return bizFullProbationOid;
	}
	public void setBizFullProbationOid(Long bizFullProbationOid) {
		this.bizFullProbationOid = bizFullProbationOid;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public Integer getProbationDays() {
		return probationDays;
	}
	public void setProbationDays(Integer probationDays) {
		this.probationDays = probationDays;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	public Date getUpdatedTs() {
		return updatedTs;
	}
	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	
}
