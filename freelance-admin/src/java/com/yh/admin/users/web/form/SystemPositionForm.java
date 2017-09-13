package com.yh.admin.users.web.form;

import org.apache.struts.validator.ValidatorForm;

public class SystemPositionForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private java.lang.Long	systemPositionOid;	//系统岗位主键
	private java.lang.String	systemPositionName;	//系统岗位名称
	private java.lang.String	systemPositionDesc;	//系统岗位描述
	private java.lang.Long	dataRoleId;	//数据权限角色ID
	private java.lang.Long	functionRoleId;	//功能权限角色ID
	private java.lang.String	deptCode;	//岗位管理部门代码
	private java.lang.String	createdByCode;	//创建人ID
	private java.lang.String	createdByName;	//创建人姓名
	private java.util.Date	createdDate;	//创建时间
	private java.lang.String	updatedByCode;	//修改人ID
	private java.lang.String	updatedByName;	//修改人姓名
	private java.util.Date	updatedDate;	//修改时间

	public SystemPositionForm() {
		
	}
	public void setSystemPositionOid(java.lang.Long systemPositionOid){
		this.systemPositionOid = systemPositionOid;
	}

	public java.lang.Long getSystemPositionOid(){
		return this.systemPositionOid;
	}

	public void setSystemPositionName(java.lang.String systemPositionName){
		this.systemPositionName = systemPositionName;
	}

	public java.lang.String getSystemPositionName(){
		return this.systemPositionName;
	}

	public void setSystemPositionDesc(java.lang.String systemPositionDesc){
		this.systemPositionDesc = systemPositionDesc;
	}

	public java.lang.String getSystemPositionDesc(){
		return this.systemPositionDesc;
	}

	public void setDataRoleId(java.lang.Long dataRoleId){
		this.dataRoleId = dataRoleId;
	}

	public java.lang.Long getDataRoleId(){
		return this.dataRoleId;
	}

	public void setFunctionRoleId(java.lang.Long functionRoleId){
		this.functionRoleId = functionRoleId;
	}

	public java.lang.Long getFunctionRoleId(){
		return this.functionRoleId;
	}

	public void setDeptCode(java.lang.String deptCode){
		this.deptCode = deptCode;
	}

	public java.lang.String getDeptCode(){
		return this.deptCode;
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
