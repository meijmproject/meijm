package com.yh.hr.info.ver.unit.comm.web.form;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

public class VerPbRankInfoForm extends ValidatorForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4772409337511818096L;
	private Long		rankOid;
	private Long		personOid;
	private String 		rankCode;
	private Date 		promoteDate;
	private String 		promoteDateStr;
	private String 		approveUnit;
	private String		promoteNo;
	private Date 		rankEndDate;
	private String 		rankEndDateStr;
	private String 		createdByCode;
	private String 		createdByName;
	private Date 		createdDate;
	private String 		updatedByCode;
	private String 		updatedByName;
	private Date 		updatedDate;
	
	
	public String getRankEndDateStr() {
		return rankEndDateStr;
	}
	public void setRankEndDateStr(String rankEndDateStr) {
		this.rankEndDateStr = rankEndDateStr;
		this.rankEndDate = DateUtil.parseDate(rankEndDateStr);
	}
	public String getPromoteDateStr() {
		return promoteDateStr;
	}
	public void setPromoteDateStr(String promoteDateStr) {
		this.promoteDateStr = promoteDateStr;
		this.promoteDate = DateUtil.parseDate(promoteDateStr);
	}
	public Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
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
	public Long getRankOid() {
		return rankOid;
	}
	public void setRankOid(Long rankOid) {
		this.rankOid = rankOid;
	}
	public String getRankCode() {
		return rankCode;
	}
	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}
	public Date getPromoteDate() {
		return promoteDate;
	}
	public void setPromoteDate(Date promoteDate) {
		if (null != promoteDate) {
			this.promoteDate = promoteDate;
			this.promoteDateStr = DateUtil.formatDate(promoteDate);
		}
	}
	public String getApproveUnit() {
		return approveUnit;
	}
	public void setApproveUnit(String approveUnit) {
		this.approveUnit = approveUnit;
	}
	public String getPromoteNo() {
		return promoteNo;
	}
	public void setPromoteNo(String promoteNo) {
		this.promoteNo = promoteNo;
	}
	public Date getRankEndDate() {
		return rankEndDate;
	}
	public void setRankEndDate(Date rankEndDate) {
		if (null != rankEndDate) {
			this.rankEndDate = rankEndDate;
			this.rankEndDateStr = DateUtil.formatDate(rankEndDate);
		}
	}
	
	
}