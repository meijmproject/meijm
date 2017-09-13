package com.yh.hr.info.ver.unit.comm.web.form;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class PbIndusInfoForm extends ValidatorForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7825644076185673754L;
	private Long			 indusOid;
	private Date 			 hurtDate;
	private String 			 hurtLevel;
	private String			 hurtLevelName;
	private String 			 congizanceLetter;
	private String 			 judgeOrgan;
	
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
	public String getHurtLevelName() {
		return hurtLevelName;
	}
	public void setHurtLevelName(String hurtLevelName) {
		this.hurtLevelName = hurtLevelName;
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
	
	
}