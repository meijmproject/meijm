package com.yh.hr.component.im.dto;

/**
 * 字段定义DTO
 * @author wangx
 * @date 2017-07-12
 * @version 1.0
 */
public class ColumnDefDTO {

	private String columnCode;	//字段代码
	private String columnName;	//字段名称
	private String columnValue;	//字段值
	private String columnType;	//字段类型	VARCHAR,DATE,DECIMAL,BIGINT
	private String columnLength;//字段长度
	private String isPrimaryKey;//是否主键
	private String isRequired;	//是否必填
	
	public String getColumnCode() {
		return columnCode;
	}
	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnValue() {
		return columnValue;
	}
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getColumnLength() {
		return columnLength;
	}
	public void setColumnLength(String columnLength) {
		this.columnLength = columnLength;
	}
	public String getIsPrimaryKey() {
		return isPrimaryKey;
	}
	public void setIsPrimaryKey(String isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public String getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}
	
}
