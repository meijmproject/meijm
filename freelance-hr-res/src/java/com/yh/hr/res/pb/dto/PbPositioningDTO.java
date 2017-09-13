package com.yh.hr.res.pb.dto;

import java.util.Date;

/**
 * Created by weizh on 2016/8/15.
 */
public class PbPositioningDTO {
	private Long	positioningOid;	//主键
	private Long	personOid;	//PersonOid
	private String	positioningStatus;	//任(聘)状态YHRS0026
	private String	dutyUnitName;	//任职(聘任)单位名称
	private Long	dutyUnitOid;	//任职单位OID
	private String	deptName;	//任职(聘任)内设机构
	private Long	deptOid;	//任职内设机构OID
	private String	dutyType;	//职务类别YHRS0027
	private String	dutyAttribute;	//职务属性YHRS0028
	private String	dutyName;	//任职(聘任)职务名称
	private String	profTechCode;	//专业技术资格编码
	private String	qualificationsCode;	//职业资格编码
	private String	dutyLevel;	//任职(聘任)职务级别YHRS0015
	private String	wageDutyLevel;	//工资职级，用于兑现工资待遇事业单位依据岗位级别转职务级别，可修改YHRS0015
	private String	positioningReason;	//任职(聘任)原因YHRS0033
	private String	positioningTypeCode;	//任职方式YHRS0032
	private String	dutyChangeType;	//任职(聘任)变动类型YHRS0034
	private String	dutyChangeTypeName;	//任职(聘任)变动类型描述
	private String	positioningFileno;	//任职(聘任)文号
	private String	positioningUnitDesc;	//任命(聘任)单位名称
	private java.util.Date	dutyDate;	//任职(聘任)开始日期
	private java.util.Date	endDate;	//任职(聘任)拟终止日期
	private java.util.Date	endDateActual;	//任职(聘任)终止日期
	private String	disposalReason;	//免职(解聘)原因类别YHRS0035
	private String	disposalReasonDesc;	//免职(解聘)原因类别描述
	private String	disposalFileno;	//免职(解聘)文号
	private String	disposalUnit;	//免职(解聘)单位名称
	private String	dutyRecordType;	//职务类型YHRS0036
	private Long	mappingLevel;				// 用来比较职务层次高低
	private String             positioningReasonDesc; //任职原因描述
	
	private java.lang.String wageDutyAttribute; // 工资职务属性
	private String     isVirtual;//是否虚拟任职

	public java.lang.String getWageDutyAttribute() {
		return wageDutyAttribute;
	}

	public void setWageDutyAttribute(java.lang.String wageDutyAttribute) {
		this.wageDutyAttribute = wageDutyAttribute;
	}

	public Long getMappingLevel() {
		return mappingLevel;
	}

	public void setMappingLevel(Long mappingLevel) {
		this.mappingLevel = mappingLevel;
	}

	public Long getPositioningOid() {
		return positioningOid;
	}

	public void setPositioningOid(Long positioningOid) {
		this.positioningOid = positioningOid;
	}

	public Long getPersonOid() {
		return personOid;
	}

	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}

	public String getPositioningStatus() {
		return positioningStatus;
	}

	public void setPositioningStatus(String positioningStatus) {
		this.positioningStatus = positioningStatus;
	}

	public String getDutyUnitName() {
		return dutyUnitName;
	}

	public void setDutyUnitName(String dutyUnitName) {
		this.dutyUnitName = dutyUnitName;
	}

	public Long getDutyUnitOid() {
		return dutyUnitOid;
	}

	public void setDutyUnitOid(Long dutyUnitOid) {
		this.dutyUnitOid = dutyUnitOid;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getDeptOid() {
		return deptOid;
	}

	public void setDeptOid(Long deptOid) {
		this.deptOid = deptOid;
	}

	public String getDutyType() {
		return dutyType;
	}

	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}

	public String getDutyAttribute() {
		return dutyAttribute;
	}

	public void setDutyAttribute(String dutyAttribute) {
		this.dutyAttribute = dutyAttribute;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getProfTechCode() {
		return profTechCode;
	}

	public void setProfTechCode(String profTechCode) {
		this.profTechCode = profTechCode;
	}

	public String getQualificationsCode() {
		return qualificationsCode;
	}

	public void setQualificationsCode(String qualificationsCode) {
		this.qualificationsCode = qualificationsCode;
	}

	public String getDutyLevel() {
		return dutyLevel;
	}

	public void setDutyLevel(String dutyLevel) {
		this.dutyLevel = dutyLevel;
	}

	public String getWageDutyLevel() {
		return wageDutyLevel;
	}

	public void setWageDutyLevel(String wageDutyLevel) {
		this.wageDutyLevel = wageDutyLevel;
	}

	public String getPositioningReason() {
		return positioningReason;
	}

	public void setPositioningReason(String positioningReason) {
		this.positioningReason = positioningReason;
	}

	public String getPositioningTypeCode() {
		return positioningTypeCode;
	}

	public void setPositioningTypeCode(String positioningTypeCode) {
		this.positioningTypeCode = positioningTypeCode;
	}

	public String getDutyChangeType() {
		return dutyChangeType;
	}

	public void setDutyChangeType(String dutyChangeType) {
		this.dutyChangeType = dutyChangeType;
	}

	public String getDutyChangeTypeName() {
		return dutyChangeTypeName;
	}

	public void setDutyChangeTypeName(String dutyChangeTypeName) {
		this.dutyChangeTypeName = dutyChangeTypeName;
	}

	public String getPositioningFileno() {
		return positioningFileno;
	}

	public void setPositioningFileno(String positioningFileno) {
		this.positioningFileno = positioningFileno;
	}

	public String getPositioningUnitDesc() {
		return positioningUnitDesc;
	}

	public void setPositioningUnitDesc(String positioningUnitDesc) {
		this.positioningUnitDesc = positioningUnitDesc;
	}

	public Date getDutyDate() {
		return dutyDate;
	}

	public void setDutyDate(Date dutyDate) {
		this.dutyDate = dutyDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDateActual() {
		return endDateActual;
	}

	public void setEndDateActual(Date endDateActual) {
		this.endDateActual = endDateActual;
	}

	public String getDisposalReason() {
		return disposalReason;
	}

	public void setDisposalReason(String disposalReason) {
		this.disposalReason = disposalReason;
	}

	public String getDisposalReasonDesc() {
		return disposalReasonDesc;
	}

	public void setDisposalReasonDesc(String disposalReasonDesc) {
		this.disposalReasonDesc = disposalReasonDesc;
	}

	public String getDisposalFileno() {
		return disposalFileno;
	}

	public void setDisposalFileno(String disposalFileno) {
		this.disposalFileno = disposalFileno;
	}

	public String getDisposalUnit() {
		return disposalUnit;
	}

	public void setDisposalUnit(String disposalUnit) {
		this.disposalUnit = disposalUnit;
	}

	public String getDutyRecordType() {
		return dutyRecordType;
	}

	public void setDutyRecordType(String dutyRecordType) {
		this.dutyRecordType = dutyRecordType;
	}

	public String getPositioningReasonDesc() {
		return positioningReasonDesc;
	}

	public void setPositioningReasonDesc(String positioningReasonDesc) {
		this.positioningReasonDesc = positioningReasonDesc;
	}
	
	public String getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(String isVirtual) {
		this.isVirtual = isVirtual;
	}
	
}
