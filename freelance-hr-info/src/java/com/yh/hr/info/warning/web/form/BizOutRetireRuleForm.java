package com.yh.hr.info.warning.web.form;


import java.util.Date;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;


/**
 * @description 离退休预警规则
 * @author zhangsx
 * @created 2013-7-23
 * @version 1.0
 * 
 */
public class BizOutRetireRuleForm extends ValidatorForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long bizOutRetireOid;
	/**
	 * 预警事项代码
	 */
	private String itemCode;
	
	/**
	 * 性别
	 */
	private String sexCode;
	/**
	 * 性别
	 */
	private String sexCodeName;
	/**
	 * 人员类别
	 */
	private String personType;
	private String personTypeName;
	
	/**
	 * 退休年龄（岁）
	 */
	private Integer retrieDays;
	
	private String createdBy;
	private String updatedBy;
	private Date createdTs;
	private Date updatedTs;
	private String createdTsStr;
	private String updatedTsStr;
	public Long getBizOutRetireOid() {
		return bizOutRetireOid;
	}
	public void setBizOutRetireOid(Long bizOutRetireOid) {
		this.bizOutRetireOid = bizOutRetireOid;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getSexCode() {
		return sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	
	public String getSexCodeName() {
		return sexCodeName;
	}
	public void setSexCodeName(String sexCodeName) {
		this.sexCodeName = sexCodeName;
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
	public Integer getRetrieDays() {
		return retrieDays;
	}
	public void setRetrieDays(Integer retrieDays) {
		this.retrieDays = retrieDays;
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
