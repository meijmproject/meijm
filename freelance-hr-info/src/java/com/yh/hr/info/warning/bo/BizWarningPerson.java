package com.yh.hr.info.warning.bo;

import java.util.Date;

import com.yh.platform.core.bo.BaseBo;


public class BizWarningPerson extends BaseBo implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8722979501503784570L;
	
	private Long bizWarningPersonOid;
	private Long personOid;
	private String warningItemCode;
	private String systemId;
	private Date estimateEndDate;
	private int estimateEndDays;
	private Date createdTs;
	
	public Long getBizWarningPersonOid() {
		return bizWarningPersonOid;
	}
	public void setBizWarningPersonOid(Long bizWarningPersonOid) {
		this.bizWarningPersonOid = bizWarningPersonOid;
	}
	public Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}
	public String getWarningItemCode() {
		return warningItemCode;
	}
	public void setWarningItemCode(String warningItemCode) {
		this.warningItemCode = warningItemCode;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public Date getEstimateEndDate() {
		return estimateEndDate;
	}
	public void setEstimateEndDate(Date estimateEndDate) {
		this.estimateEndDate = estimateEndDate;
	}
	public int getEstimateEndDays() {
		return estimateEndDays;
	}
	public void setEstimateEndDays(int estimateEndDays) {
		this.estimateEndDays = estimateEndDays;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	
}
