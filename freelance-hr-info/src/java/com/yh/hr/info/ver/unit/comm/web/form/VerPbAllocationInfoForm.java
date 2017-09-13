package com.yh.hr.info.ver.unit.comm.web.form;

import java.text.ParseException;

import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

/**
 * 军转干部安置信息Form
 * @author went
 *
 */
public class VerPbAllocationInfoForm extends ValidatorForm{
	private static final long serialVersionUID = 1666603922026473747L;
	/**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *安置类别YHRS0068
     **/
	private java.lang.String allocationType;
    /**
     *安置批准机关
     **/
	private java.lang.String allocationApproveUnit;
    /**
     *安置批准文号
     **/
	private java.lang.String allocationApproveFileno;


	/**
     *安置批准日期
     **/
	private java.util.Date allocationApproveDate;
	private String allocationApproveDateStr;
    public String getAllocationApproveDateStr() {
		return allocationApproveDateStr;
	}


	public void setAllocationApproveDateStr(String allocationApproveDateStr) throws ParseException {
		
		this.allocationApproveDateStr = allocationApproveDateStr;
		this.allocationApproveDate = DateUtil.parseDate(allocationApproveDateStr);
		
	}
    /**
     *安置地区YHRS0006
     **/
	private java.lang.String allocationAddress;
    /**
     *安置单位
     **/
	private java.lang.String allocationUnit;
    /**
     *安置职务YHRS0029
     **/
	private java.lang.String allocationDuty;
    /**
     *安置职务属性YHRS0028
     **/
	private java.lang.String allocationDutyAttribute;
    /**
     *安置职务层次YHRS0015
     **/
	private java.lang.String allocationDutyLevel;
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

/**
* public JhcPbAllocationInfo(java.lang.Long personOid) {
*  *       this.personOid = personOid;
**   }
**/
	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}

	public void setAllocationType(java.lang.String allocationType){
		this.allocationType = allocationType;
	}

	public java.lang.String getAllocationType(){
		return this.allocationType;
	}

	public void setAllocationApproveUnit(java.lang.String allocationApproveUnit){
		this.allocationApproveUnit = allocationApproveUnit;
	}

	public java.lang.String getAllocationApproveUnit(){
		return this.allocationApproveUnit;
	}

	public void setAllocationApproveFileno(java.lang.String allocationApproveFileno){
		this.allocationApproveFileno = allocationApproveFileno;
	}

	public java.lang.String getAllocationApproveFileno(){
		return this.allocationApproveFileno;
	}



	public void setAllocationApproveDate(java.util.Date allocationApproveDate) {
		if (null != allocationApproveDate) {
			this.allocationApproveDate = allocationApproveDate;
			this.allocationApproveDateStr = DateUtil.formatDate(allocationApproveDate);
		}
	}


	public java.util.Date getAllocationApproveDate(){
		return this.allocationApproveDate;
	}

	public void setAllocationAddress(java.lang.String allocationAddress){
		this.allocationAddress = allocationAddress;
	}

	public java.lang.String getAllocationAddress(){
		return this.allocationAddress;
	}

	public void setAllocationUnit(java.lang.String allocationUnit){
		this.allocationUnit = allocationUnit;
	}

	public java.lang.String getAllocationUnit(){
		return this.allocationUnit;
	}

	public void setAllocationDuty(java.lang.String allocationDuty){
		this.allocationDuty = allocationDuty;
	}

	public java.lang.String getAllocationDuty(){
		return this.allocationDuty;
	}

	public void setAllocationDutyAttribute(java.lang.String allocationDutyAttribute){
		this.allocationDutyAttribute = allocationDutyAttribute;
	}

	public java.lang.String getAllocationDutyAttribute(){
		return this.allocationDutyAttribute;
	}

	public void setAllocationDutyLevel(java.lang.String allocationDutyLevel){
		this.allocationDutyLevel = allocationDutyLevel;
	}

	public java.lang.String getAllocationDutyLevel(){
		return this.allocationDutyLevel;
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
