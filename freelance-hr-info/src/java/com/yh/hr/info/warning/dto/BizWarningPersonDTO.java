package com.yh.hr.info.warning.dto;

import java.util.Date;


public class BizWarningPersonDTO 
{
	private Long bizWarningPersonOid;
	private Long personOid;
	private String warningItemCode;
	private String systemId;
	private Date estimateEndDate;
	private int estimateEndDays;
	
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
}
