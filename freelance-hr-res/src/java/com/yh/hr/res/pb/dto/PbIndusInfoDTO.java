package com.yh.hr.res.pb.dto;

import java.util.Date;

public class PbIndusInfoDTO{
	private Long			indusOid;
	private Long 			personOid;
	private Date 			hurtDate;
	private String 			hurtLevel;
	private String 			congizanceLetter;
	private String 			judgeOrgan;
	private String 			createdByCode;
	private String 			createdByName;
	private Date 			createdDate;
	private String 			updatedByCode;
	private String 			updatedByName;
	private Date 			updatedDate;
	
	
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
	public Long getIndusOid() {
		return indusOid;
	}
	public void setIndusOid(Long indusOid) {
		this.indusOid = indusOid;
	}
	public Date getHurtDate() {
		return hurtDate;
	}
	public void setHurtDate(Date hurtDate) {
		this.hurtDate = hurtDate;
	}
	public String getHurtLevel() {
		return hurtLevel;
	}
	public void setHurtLevel(String hurtLevel) {
		this.hurtLevel = hurtLevel;
	}
	public String getCongizanceLetter() {
		return congizanceLetter;
	}
	public void setCongizanceLetter(String congizanceLetter) {
		this.congizanceLetter = congizanceLetter;
	}
	public String getJudgeOrgan() {
		return judgeOrgan;
	}
	public void setJudgeOrgan(String judgeOrgan) {
		this.judgeOrgan = judgeOrgan;
	}
	public Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}
	
	
}