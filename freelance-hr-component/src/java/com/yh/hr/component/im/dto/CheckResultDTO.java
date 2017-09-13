package com.yh.hr.component.im.dto;

/**
 * 检查结果组装DTO
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class CheckResultDTO {

	private Long checkPersonInfoOid;	//校核人员OID 检查类型为4,5,6需给该值
	private Long importBatchOid;		//导入批次OID
	private String databaseColumnCode;	//数据库字段代码（不能为空）
	private String importCollValue;	//导入采集项值
	private String checkType;	//检查类型（不能为空）	1：必填项检查	2：数据项长度检查	3：数据格式检查	4：字典项检查	5：关联性检查	6：完整性检查
	private String checkMessage;	//检查结果
	private String checkStatus;	//检查状态	1：通过	2：未通过
	
	public Long getCheckPersonInfoOid() {
		return checkPersonInfoOid;
	}
	public void setCheckPersonInfoOid(Long checkPersonInfoOid) {
		this.checkPersonInfoOid = checkPersonInfoOid;
	}
	public Long getImportBatchOid() {
		return importBatchOid;
	}
	public void setImportBatchOid(Long importBatchOid) {
		this.importBatchOid = importBatchOid;
	}
	public String getDatabaseColumnCode() {
		return databaseColumnCode;
	}
	public void setDatabaseColumnCode(String databaseColumnCode) {
		this.databaseColumnCode = databaseColumnCode;
	}
	public String getImportCollValue() {
		return importCollValue;
	}
	public void setImportCollValue(String importCollValue) {
		this.importCollValue = importCollValue;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getCheckMessage() {
		return checkMessage;
	}
	public void setCheckMessage(String checkMessage) {
		this.checkMessage = checkMessage;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}
