package com.yh.hr.info.ver.unit.comm.web.form;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

/**
 * @author Administrator
 *
 */
public class VerPbPoliticInfoForm extends ValidatorForm
{
	private static final long serialVersionUID = -9125044043899887326L;
	
	private Long politicOid;
    
	private Long personOid;
    
	private String politicStatusCode;
    
	private Date joinPoliticDate;
	
	private String joinPoliticDateStr;
    
	private String unitOfJoinParty;
    
	private String introducer;
    
	private Date probationDate;
	
	private String probationDateStr;
    
	private String politicDutyCode;
    
	private Date outPartyDate;
	
	private String outPartyDateStr;
    
	private String abnormityType;
    
	private String abnormityReason;
    
	private String remark;
    
	private String createdByCode;
    
	private String createdByName;
    
	private Date createdDate;
    
	private String updatedByCode;
    
	private String updatedByName;
    
	private Date updatedDate;

	public Long getPoliticOid() {
		return politicOid;
	}

	public void setPoliticOid(Long politicOid) {
		this.politicOid = politicOid;
	}

	public Long getPersonOid() {
		return personOid;
	}

	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}

	public String getPoliticStatusCode() {
		return politicStatusCode;
	}

	public void setPoliticStatusCode(String politicStatusCode) {
		this.politicStatusCode = politicStatusCode;
	}

	public Date getJoinPoliticDate() {
		return joinPoliticDate;
	}

	public void setJoinPoliticDate(Date joinPoliticDate) {
		this.joinPoliticDate = joinPoliticDate;
	}

	public String getUnitOfJoinParty() {
		return unitOfJoinParty;
	}

	public void setUnitOfJoinParty(String unitOfJoinParty) {
		this.unitOfJoinParty = unitOfJoinParty;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	public Date getProbationDate() {
		return probationDate;
	}

	public void setProbationDate(Date probationDate) {
		this.probationDate = probationDate;
	}

	public String getPoliticDutyCode() {
		return politicDutyCode;
	}

	public void setPoliticDutyCode(String politicDutyCode) {
		this.politicDutyCode = politicDutyCode;
	}

	public Date getOutPartyDate() {
		return outPartyDate;
	}

	public void setOutPartyDate(Date outPartyDate) {
		this.outPartyDate = outPartyDate;
	}

	public String getAbnormityType() {
		return abnormityType;
	}

	public void setAbnormityType(String abnormityType) {
		this.abnormityType = abnormityType;
	}

	public String getAbnormityReason() {
		return abnormityReason;
	}

	public void setAbnormityReason(String abnormityReason) {
		this.abnormityReason = abnormityReason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getJoinPoliticDateStr() {
		return joinPoliticDateStr;
	}

	public void setJoinPoliticDateStr(String joinPoliticDateStr) {
		if(StringUtils.isNotEmpty(joinPoliticDateStr))
		{
			this.joinPoliticDateStr = joinPoliticDateStr.trim();
			this.joinPoliticDate = DateUtil.parseDate(joinPoliticDateStr);
		}
	}

	public String getProbationDateStr() {
		return probationDateStr;
	}

	public void setProbationDateStr(String probationDateStr) {
		if(StringUtils.isNotEmpty(probationDateStr))
		{
			this.probationDateStr = probationDateStr.trim();
			this.probationDate = DateUtil.parseDate(probationDateStr);
		}
	}

	public String getOutPartyDateStr() {
		return outPartyDateStr;
	}

	public void setOutPartyDateStr(String outPartyDateStr) {
		if(StringUtils.isNotEmpty(outPartyDateStr))
		{
			this.outPartyDateStr = outPartyDateStr;
			this.outPartyDate = DateUtil.parseDate(outPartyDateStr);
		}
	}
}
