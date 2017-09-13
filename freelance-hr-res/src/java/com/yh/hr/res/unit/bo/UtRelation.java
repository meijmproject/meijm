/**
 * This file is generated by  code generation tool version 0.2 ^_^
 * Created at 2016-09-02
**/
package com.yh.hr.res.unit.bo;

import com.yh.platform.core.bo.BaseBo;

public class UtRelation extends BaseBo {
	private static final long serialVersionUID = 1163203825039762287L;

	private java.lang.Long  	relationOid;	//主键
	private java.lang.Long  	parentOrganizationOid;	//ParentOrganizationOid
	private java.lang.Long  	childOrganizationOid;	//ChildOrganizationOid
	private java.lang.String	relationType;	//关系类型
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date  	updatedDate;	//UpdatedDate

	public UtRelation() {
		
	}

	public void setRelationOid(java.lang.Long relationOid){
		this.relationOid = relationOid;
	}

	public java.lang.Long getRelationOid(){
		return this.relationOid;
	}

	public void setParentOrganizationOid(java.lang.Long parentOrganizationOid){
		this.parentOrganizationOid = parentOrganizationOid;
	}

	public java.lang.Long getParentOrganizationOid(){
		return this.parentOrganizationOid;
	}

	public void setChildOrganizationOid(java.lang.Long childOrganizationOid){
		this.childOrganizationOid = childOrganizationOid;
	}

	public java.lang.Long getChildOrganizationOid(){
		return this.childOrganizationOid;
	}

	public void setRelationType(java.lang.String relationType){
		this.relationType = relationType;
	}

	public java.lang.String getRelationType(){
		return this.relationType;
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
