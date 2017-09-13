package com.yh.hr.orghc.unit.common.web.form;

import java.util.List;

import com.yh.hr.orghc.unit.common.dto.BizUtHcInfoDTO;
import org.apache.struts.validator.ValidatorForm;

public class BizUtHcInfoForm extends ValidatorForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.Long  	utHcOid;	//UtHcOid
	private java.lang.Long  	utUnitOid;	//UtUnitOid
	private java.lang.String	hcOid;	//HcOid
	private java.lang.String	hcCode;	//HcOid
	private java.lang.String	hcName;	//编制名称
	private java.lang.String	budgetFromCode;	//经费形式代码
	private java.lang.String	budgetFromName;	//BudgetFromName
	private java.lang.Long  	chgCount;	//ChgCount
	private java.lang.Long  	preCount;	//PreCount
	private java.lang.Long  	curCount;	//CurCount
	private java.lang.String	createBy;	//CreateBy
	private java.lang.String	createName;	//CreateName
	private java.util.Date  	createDate;	//CreateDate
	private java.lang.String	updateBy;	//UpdateBy
	private java.lang.String	updateName;	//UpdateName
	private java.util.Date  	updateDate;	//UpdateDate
	private List<BizUtHcInfoDTO>	bizUtHcInfoDTOs;

	public BizUtHcInfoForm() {
		
	}
	
	
	

	public java.lang.Long getUtHcOid() {
		return utHcOid;
	}




	public void setUtHcOid(java.lang.Long utHcOid) {
		this.utHcOid = utHcOid;
	}




	public java.lang.Long getUtUnitOid() {
		return utUnitOid;
	}




	public void setUtUnitOid(java.lang.Long utUnitOid) {
		this.utUnitOid = utUnitOid;
	}




	public java.lang.String getHcOid() {
		return hcOid;
	}




	public void setHcOid(java.lang.String hcOid) {
		this.hcOid = hcOid;
	}




	public java.lang.String getHcName() {
		return hcName;
	}

	public void setHcName(java.lang.String hcName) {
		this.hcName = hcName;
	}

	public java.lang.String getBudgetFromCode() {
		return budgetFromCode;
	}

	public void setBudgetFromCode(java.lang.String budgetFromCode) {
		this.budgetFromCode = budgetFromCode;
	}

	public java.lang.String getBudgetFromName() {
		return budgetFromName;
	}

	public void setBudgetFromName(java.lang.String budgetFromName) {
		this.budgetFromName = budgetFromName;
	}

	public java.lang.Long getChgCount() {
		return chgCount;
	}

	public void setChgCount(java.lang.Long chgCount) {
		this.chgCount = chgCount;
	}

	public java.lang.Long getPreCount() {
		return preCount;
	}

	public void setPreCount(java.lang.Long preCount) {
		this.preCount = preCount;
	}

	public java.lang.Long getCurCount() {
		return curCount;
	}

	public void setCurCount(java.lang.Long curCount) {
		this.curCount = curCount;
	}

	public java.lang.String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}

	public java.lang.String getCreateName() {
		return createName;
	}

	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.lang.String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}

	public java.lang.String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}

	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}




	public List<BizUtHcInfoDTO> getBizUtHcInfoDTOs() {
		return bizUtHcInfoDTOs;
	}




	public void setBizUtHcInfoDTOs(List<BizUtHcInfoDTO> bizUtHcInfoDTOs) {
		this.bizUtHcInfoDTOs = bizUtHcInfoDTOs;
	}




	public java.lang.String getHcCode() {
		return hcCode;
	}




	public void setHcCode(java.lang.String hcCode) {
		this.hcCode = hcCode;
	}


}
