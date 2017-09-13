package com.yh.hr.info.warning.web.form;

import java.util.Date;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;


/**
 * @description 聘任制试用期到期预警
 * @author zhangsx
 * @created 2013-7-23
 * @version 1.0
 * 
 */
public class BizFullProbationRuleForm extends ValidatorForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
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
	 * 人员类别
	 */
	private String personTypeName;
	/**
	 * 试用期时长（天）
	 */
	private Integer probationDays;
	
	private String createdBy;
	private String updatedBy;
	private Date createdTs;
	private Date updatedTs;
	private String createdTsStr;
	private String updatedTsStr;
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
	
	public String getPersonTypeName() {
		return personTypeName;
	}
	public void setPersonTypeName(String personTypeName) {
		this.personTypeName = personTypeName;
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
		if(createdTs != null){
			this.createdTs = createdTs;
			this.createdTsStr = DateUtil.formatDate(createdTs);
		}
	}
	public String getCreatedTsStr() {
		return createdTsStr;
	}
	public void setCreatedTsStr(String createdTsStr) {
		if(!GenericValidator.isBlankOrNull(createdTsStr)){
			this.createdTsStr = createdTsStr;
			this.createdTs = DateUtil.parseDate(createdTsStr);
		}
	}
	public Date getUpdatedTs() {
		return updatedTs;
	}
	public void setUpdatedTs(Date updatedTs) {
		if(updatedTs != null){
			this.updatedTs = updatedTs;
			this.updatedTsStr = DateUtil.formatDate(updatedTs);
		}
	}
	public String getUpdateTsStr() {
		return updatedTsStr;
	}
	public void setUpdateTsStr(String updatedTsStr) {
		if(!GenericValidator.isBlankOrNull(updatedTsStr)){
			this.updatedTsStr = updatedTsStr;
			this.updatedTs = DateUtil.parseDate(updatedTsStr);
		}
	}
	
}
