/**
 * This file is generated by  code generation tool version 0.2 ^_^
 * Created at 2017-07-13
**/
package com.yh.hr.res.im.dto;

public class ImCollectTemplateDTO {

	private java.lang.Long  	templateOid;	//主键OID
	private java.lang.String	templateCollNo;	//模板采集项序号
	private java.lang.String	databaseColumnCode;	//数据库字段代码
	private java.lang.String	databaseColumnCodeName;	//数据库字段代码名称
	private java.lang.String	templateCollName;	//模板采集项名称
	private java.lang.String	databaseColumnType;	//数据库字段类型	1：VARCHAR	2：DATE
	private java.lang.String	importCollName;	//导入采集项名称
	private java.lang.Integer	importCollMaxlength;	//导入采集项最大长度
	private java.lang.Integer	importHeaderMaxlength;	//导入采集项列头最大长度
	private java.lang.String	isDicColumn;	//是否字典项	1：是	0：否
	private java.lang.String	isRequired;	//字段是否必填	1：是	0：否
	private java.lang.String	effectiveFlag;	//生效标识	1：生效	0：失效
	private java.lang.String	remark;	//备注
	private java.lang.String	updatedByCode;	//修改人ID
	private java.lang.String	updatedByName;	//修改人名称
	private java.util.Date  	updatedDate;	//修改时间

	public ImCollectTemplateDTO() {
		
	}

	public void setTemplateOid(java.lang.Long templateOid){
		this.templateOid = templateOid;
	}

	public java.lang.Long getTemplateOid(){
		return this.templateOid;
	}

	public void setTemplateCollNo(java.lang.String templateCollNo){
		this.templateCollNo = templateCollNo;
	}

	public java.lang.String getTemplateCollNo(){
		return this.templateCollNo;
	}

	public void setDatabaseColumnCode(java.lang.String databaseColumnCode){
		this.databaseColumnCode = databaseColumnCode;
	}

	public java.lang.String getDatabaseColumnCode(){
		return this.databaseColumnCode;
	}

	public void setDatabaseColumnCodeName(java.lang.String databaseColumnCodeName){
		this.databaseColumnCodeName = databaseColumnCodeName;
	}

	public java.lang.String getDatabaseColumnCodeName(){
		return this.databaseColumnCodeName;
	}

	public void setTemplateCollName(java.lang.String templateCollName){
		this.templateCollName = templateCollName;
	}

	public java.lang.String getTemplateCollName(){
		return this.templateCollName;
	}

	public void setDatabaseColumnType(java.lang.String databaseColumnType){
		this.databaseColumnType = databaseColumnType;
	}

	public java.lang.String getDatabaseColumnType(){
		return this.databaseColumnType;
	}

	public void setImportCollName(java.lang.String importCollName){
		this.importCollName = importCollName;
	}

	public java.lang.String getImportCollName(){
		return this.importCollName;
	}

	public void setImportCollMaxlength(java.lang.Integer importCollMaxlength){
		this.importCollMaxlength = importCollMaxlength;
	}

	public java.lang.Integer getImportCollMaxlength(){
		return this.importCollMaxlength;
	}

	public void setImportHeaderMaxlength(java.lang.Integer importHeaderMaxlength){
		this.importHeaderMaxlength = importHeaderMaxlength;
	}

	public java.lang.Integer getImportHeaderMaxlength(){
		return this.importHeaderMaxlength;
	}

	public void setIsDicColumn(java.lang.String isDicColumn){
		this.isDicColumn = isDicColumn;
	}

	public java.lang.String getIsDicColumn(){
		return this.isDicColumn;
	}

	public void setIsRequired(java.lang.String isRequired){
		this.isRequired = isRequired;
	}

	public java.lang.String getIsRequired(){
		return this.isRequired;
	}

	public void setEffectiveFlag(java.lang.String effectiveFlag){
		this.effectiveFlag = effectiveFlag;
	}

	public java.lang.String getEffectiveFlag(){
		return this.effectiveFlag;
	}

	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

	public java.lang.String getRemark(){
		return this.remark;
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
