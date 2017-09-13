package com.yh.hr.report.dto;

/**
 * 管理人员明细DTO
 * @author duxw
 * @date 2017-3-1
 *
 */
public class ManagerDetailsReportDTO {
	
	private Long personOid;//人员oid
	private java.lang.String hisPositionName;//岗位
	private java.lang.String name;	//姓名
	private java.lang.String age;	//年龄
	private java.lang.String ftEducationLevelCode;	//学历
	private java.lang.String profTechName;	//职称
	private java.lang.Integer rowCount; //一共有多少行,用于页面合并行
	
	
	public Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}
	public java.lang.String getHisPositionName() {
		return hisPositionName;
	}
	public void setHisPositionName(java.lang.String hisPositionName) {
		this.hisPositionName = hisPositionName;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getAge() {
		return age;
	}
	public void setAge(java.lang.String age) {
		this.age = age;
	}
	public java.lang.String getProfTechName() {
		return profTechName;
	}
	public void setProfTechName(java.lang.String profTechName) {
		this.profTechName = profTechName;
	}
	public void setFtEducationLevelCode(java.lang.String ftEducationLevelCode) {
		this.ftEducationLevelCode = ftEducationLevelCode;
	}
	public java.lang.String getFtEducationLevelCode() {
		return ftEducationLevelCode;
	}
	public void setRowCount(java.lang.Integer rowCount) {
		this.rowCount = rowCount;
	}
	public java.lang.Integer getRowCount() {
		return rowCount;
	}
	
}
