package com.yh.hr.info.warning.bo;

import java.util.Date;

import com.yh.platform.core.bo.BaseBo;


/**
 * @description 离退休预警规则
 * @author zhangsx
 * @created 2013-7-23
 * @version 1.0
 * 
 */
public class BizOutRetireRule extends BaseBo implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3118794367956348536L;
	
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
	 * 人员类别
	 */
	private String personType;
	
	/**
	 * 退休年龄（岁）
	 */
	private Integer retrieDays;
	
	private String createdBy;
	private String updatedBy;
	private Date createdTs;
	private Date updatedTs;
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
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
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
		this.createdTs = createdTs;
	}
	public Date getUpdatedTs() {
		return updatedTs;
	}
	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	
}
