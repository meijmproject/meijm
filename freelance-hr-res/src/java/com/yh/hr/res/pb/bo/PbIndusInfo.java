/**
 * This file is generated by  code generation tool version 0.2 ^_^
 * Created at 2016-08-15
**/
package com.yh.hr.res.pb.bo;

import com.yh.platform.core.bo.BaseBo;

public class PbIndusInfo extends BaseBo {
	private static final long serialVersionUID = 3793627916515891476L;
	/**
     *IndusOid
     **/
	private java.lang.Long indusOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *受伤日期
     **/
	private java.util.Date hurtDate;
    /**
     *伤残等级YHRS0087
     **/
	private java.lang.String hurtLevel;
    /**
     *认定公函
     **/
	private java.lang.String congizanceLetter;
    /**
     *认定单位
     **/
	private java.lang.String judgeOrgan;
    /**
     *CreatedByCode
     **/
	private java.lang.String createdByCode;
    /**
     *CreatedByName
     **/
	private java.lang.String createdByName;
    /**
     *CreatedDate
     **/
	private java.util.Date createdDate;
    /**
     *UpdatedByCode
     **/
	private java.lang.String updatedByCode;
    /**
     *UpdatedByName
     **/
	private java.lang.String updatedByName;
    /**
     *UpdatedDate
     **/
	private java.util.Date updatedDate;

	public PbIndusInfo() {
		
	}
/**
* public JhcPbIndusInfo(java.lang.Long indusOid) {
*  *       this.indusOid = indusOid;
**   }
**/
	public void setIndusOid(java.lang.Long indusOid){
		this.indusOid = indusOid;
	}

	public java.lang.Long getIndusOid(){
		return this.indusOid;
	}

	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}

	public void setHurtDate(java.util.Date hurtDate){
		this.hurtDate = hurtDate;
	}

	public java.util.Date getHurtDate(){
		return this.hurtDate;
	}

	public void setHurtLevel(java.lang.String hurtLevel){
		this.hurtLevel = hurtLevel;
	}

	public java.lang.String getHurtLevel(){
		return this.hurtLevel;
	}

	public void setCongizanceLetter(java.lang.String congizanceLetter){
		this.congizanceLetter = congizanceLetter;
	}

	public java.lang.String getCongizanceLetter(){
		return this.congizanceLetter;
	}

	public void setJudgeOrgan(java.lang.String judgeOrgan){
		this.judgeOrgan = judgeOrgan;
	}

	public java.lang.String getJudgeOrgan(){
		return this.judgeOrgan;
	}

	public void setCreatedByCode(java.lang.String createdByCode){
		this.createdByCode = createdByCode;
	}

	public java.lang.String getCreatedByCode(){
		return this.createdByCode;
	}

	public void setCreatedByName(java.lang.String createdByName){
		this.createdByName = createdByName;
	}

	public java.lang.String getCreatedByName(){
		return this.createdByName;
	}

	public void setCreatedDate(java.util.Date createdDate){
		this.createdDate = createdDate;
	}

	public java.util.Date getCreatedDate(){
		return this.createdDate;
	}

	public void setUpdatedByCode(java.lang.String updatedByCode){
		this.updatedByCode = updatedByCode;
	}

	public java.lang.String getUpdatedByCode(){
		return this.updatedByCode;
	}

	public void setUpdatedByName(java.lang.String updatedByName){
		this.updatedByName = updatedByName;
	}

	public java.lang.String getUpdatedByName(){
		return this.updatedByName;
	}

	public void setUpdatedDate(java.util.Date updatedDate){
		this.updatedDate = updatedDate;
	}

	public java.util.Date getUpdatedDate(){
		return this.updatedDate;
	}

}
