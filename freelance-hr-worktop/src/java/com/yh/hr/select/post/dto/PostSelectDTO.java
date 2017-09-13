/**
 * 
 */
package com.yh.hr.select.post.dto;

/**
 * 人员选择DTO
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */

public class PostSelectDTO {

	private java.lang.Long  	personOid;	//personOid
	private java.lang.Long  	ptPostOid;	//ptPostOid
	private java.lang.Long  	postOid;	//PostOid
	private java.lang.Long  	dutyUnitOid;	//聘任单位OID
	private java.lang.String	dutyUnitName;	//聘任单位名称
	private java.lang.Long  	deptOid;	//内设机构OID
	private java.lang.String	deptName;	//内设机构名称
	private java.lang.String	positionStatus;	//岗位聘任状态YHRS0026
	private java.lang.String	positionType;	//岗位类别YHRS0022
	private java.lang.String	positionLevel;	//岗位级别YHRS0023
	private java.lang.String	positionName;	//岗位名称
	private java.util.Date  	beginDate;	//岗位聘任开始时间
	private java.util.Date  	endDate;	//岗位聘任拟截止时间
	private java.util.Date  	endDateActual;	//岗位聘任实际截止时间
	private java.lang.String	specialPositionType;	//特殊岗位类别 YHRS0111 如：教师、护士
	private java.lang.String	isMPosition;	//是否主岗位 YHRS0003
	private java.lang.String	remark;	//备注
	private java.lang.Long  	positioningOid;	//任职聘任信息OID，外键关联免职聘任信息表
	private java.lang.String	dutyRecordType;	//职务类型YHRS0036
	private java.lang.String    dutyLevel;      //职务级别YHRS0015
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date  	updatedDate;	//UpdatedDate
	
	public java.lang.Long getPostOid() {
		return postOid;
	}
	public java.lang.Long getDutyUnitOid() {
		return dutyUnitOid;
	}
	public java.lang.String getDutyUnitName() {
		return dutyUnitName;
	}
	public java.lang.Long getDeptOid() {
		return deptOid;
	}
	public java.lang.String getDeptName() {
		return deptName;
	}
	public java.lang.String getPositionStatus() {
		return positionStatus;
	}
	public java.lang.String getPositionType() {
		return positionType;
	}
	public java.lang.String getPositionLevel() {
		return positionLevel;
	}
	public java.lang.String getPositionName() {
		return positionName;
	}
	public java.util.Date getBeginDate() {
		return beginDate;
	}
	public java.util.Date getEndDate() {
		return endDate;
	}
	public java.util.Date getEndDateActual() {
		return endDateActual;
	}
	public java.lang.String getSpecialPositionType() {
		return specialPositionType;
	}
	public java.lang.String getIsMPosition() {
		return isMPosition;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public java.lang.String getDutyRecordType() {
		return dutyRecordType;
	}
	public java.lang.String getDutyLevel() {
		return dutyLevel;
	}
	public java.lang.String getCreatedByCode() {
		return createdByCode;
	}
	public java.lang.String getCreatedByName() {
		return createdByName;
	}
	public java.util.Date getCreatedDate() {
		return createdDate;
	}
	public java.lang.Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(java.lang.Long personOid) {
		this.personOid = personOid;
	}
	public java.lang.String getUpdatedByCode() {
		return updatedByCode;
	}
	public java.lang.String getUpdatedByName() {
		return updatedByName;
	}
	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}
	public void setPostOid(java.lang.Long postOid) {
		this.postOid = postOid;
	}
	public void setDutyUnitOid(java.lang.Long dutyUnitOid) {
		this.dutyUnitOid = dutyUnitOid;
	}
	public void setDutyUnitName(java.lang.String dutyUnitName) {
		this.dutyUnitName = dutyUnitName;
	}
	public void setDeptOid(java.lang.Long deptOid) {
		this.deptOid = deptOid;
	}
	public void setDeptName(java.lang.String deptName) {
		this.deptName = deptName;
	}
	public void setPositionStatus(java.lang.String positionStatus) {
		this.positionStatus = positionStatus;
	}
	public void setPositionType(java.lang.String positionType) {
		this.positionType = positionType;
	}
	public void setPositionLevel(java.lang.String positionLevel) {
		this.positionLevel = positionLevel;
	}
	public void setPositionName(java.lang.String positionName) {
		this.positionName = positionName;
	}
	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	public void setEndDateActual(java.util.Date endDateActual) {
		this.endDateActual = endDateActual;
	}
	public void setSpecialPositionType(java.lang.String specialPositionType) {
		this.specialPositionType = specialPositionType;
	}
	public void setIsMPosition(java.lang.String isMPosition) {
		this.isMPosition = isMPosition;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public void setDutyRecordType(java.lang.String dutyRecordType) {
		this.dutyRecordType = dutyRecordType;
	}
	public void setDutyLevel(java.lang.String dutyLevel) {
		this.dutyLevel = dutyLevel;
	}
	public void setCreatedByCode(java.lang.String createdByCode) {
		this.createdByCode = createdByCode;
	}
	public void setCreatedByName(java.lang.String createdByName) {
		this.createdByName = createdByName;
	}
	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setUpdatedByCode(java.lang.String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}
	public void setUpdatedByName(java.lang.String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public java.lang.Long getPositioningOid() {
		return positioningOid;
	}
	public void setPositioningOid(java.lang.Long positioningOid) {
		this.positioningOid = positioningOid;
	}
	public java.lang.Long getPtPostOid() {
		return ptPostOid;
	}
	public void setPtPostOid(java.lang.Long ptPostOid) {
		this.ptPostOid = ptPostOid;
	}
}
