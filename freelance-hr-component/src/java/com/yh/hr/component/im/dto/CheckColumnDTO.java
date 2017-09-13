package com.yh.hr.component.im.dto;

/**
 * 导入前数据检查组装DTO
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class CheckColumnDTO {

	private String importCollName;	//导入采集项名称（不能为空）
	private String importCollValue;	//导入采集项值
	private Integer importValueLength;	//导入值的长度
	private String checkType;	//检查类型	1：必填项检查	2：数据项长度检查	3：数据格式检查（不能为空）
	
	public String getImportCollName() {
		return importCollName;
	}
	public void setImportCollName(String importCollName) {
		this.importCollName = importCollName;
	}
	public String getImportCollValue() {
		return importCollValue;
	}
	public void setImportCollValue(String importCollValue) {
		this.importCollValue = importCollValue;
	}
	public Integer getImportValueLength() {
		return importValueLength;
	}
	public void setImportValueLength(Integer importValueLength) {
		this.importValueLength = importValueLength;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	
}
