package com.yh.hr.res.pt.dto;

import java.util.Date;

public class PtTalentPostInfoOperateDTO {


	//private static final long serialVersionUID = 6729039159784425719L;
	private java.lang.Long  	ptPositioningHistroyOid;	//主键
	private java.lang.Long  	bizPersonOid;	//BizPersonOid
//	private java.lang.Long  	positioningOid;	//基础库记录OID
//	private java.lang.String	positioningStatus;	//任(聘)状态YHRS0026
//	private java.lang.String	dutyUnitName;	//任职(聘任)单位名称
//	private java.lang.Long  	dutyUnitOid;	//任职单位OID
//	private java.lang.String	deptName;	//任职(聘任)内设机构
//	private java.lang.Long  	deptOid;	//任职内设机构OID
//	private java.lang.String	dutyType;	//职务类别YHRS0027
//	private java.lang.String	dutyAttribute;	//职务属性YHRS0028
//	private java.lang.String	dutyName;	//任职(聘任)职务名称
//	private java.lang.String	dutyLevel;	//任职(聘任)职务级别YHRS0015
//	private java.lang.String	profTechCode;	//专业技术资格编码 YHRS0030
//	private java.lang.String	qualificationsCode;	//职业资格编码 YHRS9004
//	private java.lang.String	wageDutyLevel;	//工资职级，用于兑现工资待遇事业单位依据岗位级别转职务级别，可修改YHRS0015
//	private java.lang.String	positioningReason;	//任职(聘任)原因YHRS0033
//	private java.lang.String	positioningTypeCode;	//任职方式YHRS0032
//	private java.lang.String	dutyChangeType;	//任职(聘任)变动类型YHRS0034
//	private java.lang.String	dutyChangeTypeName;	//任职(聘任)变动类型描述
//	private java.lang.String	positioningFileno;	//任职(聘任)文号
//	private java.lang.String	positioningUnitDesc;	//任命(聘任)单位名称
//	private java.util.Date  	dutyDate;	//任职(聘任)开始日期
//	private java.util.Date  	endDate;	//任职(聘任)拟终止日期
//	private java.util.Date  	endDateActual;	//任职(聘任)终止日期
//	private java.lang.String	disposalReason;	//免职(解聘)原因类别YHRS0035
//	private java.lang.String	disposalReasonDesc;	//免职(解聘)原因类别描述
//	private java.lang.String	disposalFileno;	//免职(解聘)文号
//	private java.lang.String	disposalUnit;	//免职(解聘)单位名称
//	private java.lang.String	dutyRecordType;	//职务类型YHRS0036
	private java.lang.String	createdByCode;	//CreatedByCode
	private java.lang.String	createdByName;	//CreatedByName
	private java.util.Date  	createdDate;	//CreatedDate
	private java.lang.String	updatedByCode;	//UpdatedByCode
	private java.lang.String	updatedByName;	//UpdatedByName
	private java.util.Date  	updatedDate;	//UpdatedDate

	private java.lang.Long  	ptPostOid;	//主键
//	private java.lang.Long  	bizPersonOid;	//BizPersonOid
//	private java.lang.Long  	dutyUnitOid;	//聘任单位OID
//	private java.lang.String	dutyUnitName;	//聘任单位名称
//	private java.lang.Long  	deptOid;	//内设机构OID
//	private java.lang.String	deptName;	//内设机构名称
//	private java.lang.String	positionStatus;	//岗位聘任状态YHRS0026
//	private java.lang.String	positionType;	//岗位类别YHRS0022
//	private java.lang.String	positionLevel;	//岗位级别YHRS0023
//	private java.lang.String	positionName;	//岗位名称
//	private java.util.Date  	beginDate;	//岗位聘任开始时间
//	private java.util.Date  	endDate;	//岗位聘任拟截止时间
//	private java.util.Date  	endDateActual;	//岗位聘任实际截止时间
//	private java.lang.String	specialPositionType;	//特殊岗位类别 YHRS0111 如：教师、护士
//	private java.lang.String	isMPosition;	//是否主岗位 YHRS0003
//	private java.lang.String	remark;	//备注
//	private java.lang.Long  	positioningOid;	//任职聘任信息OID，外键关联任职聘任信息表
//	private java.lang.String	dutyRecordType;	//职务类型YHRS0036
//	private java.lang.String	createdByCode;	//CreatedByCode
//	private java.lang.String	createdByName;	//CreatedByName
//	private java.util.Date  	createdDate;	//CreatedDate
//	private java.lang.String	updatedByCode;	//UpdatedByCode
//	private java.lang.String	updatedByName;	//UpdatedByName
//	private java.util.Date  	updatedDate;	//UpdatedDate
	
//	private static final long serialVersionUID = 6729039159784425719L;
	private java.lang.Long	positioningOid;	
    private java.lang.Long	personOid;	//PersonOid
    private java.lang.String	positioningStatus;	//任(聘)状态YHRS0026
    private java.lang.String	dutyUnitName;	//任职(聘任)单位名称
    private java.lang.Long	dutyUnitOid;	//任职单位OID
    private java.lang.String	deptName;	//任职(聘任)内设机构
    private java.lang.Long	deptOid;	//任职内设机构OID
    private java.lang.String	dutyType;	//职务类别YHRS0027
    private java.lang.String	dutyAttribute;	//职务属性YHRS0028
    private java.lang.String	dutyName;	//任职(聘任)职务名称
    private java.lang.String	profTechCode;	//专业技术资格编码
    private java.lang.String	qualificationsCode;	//职业资格编码
    private java.lang.String	dutyLevel;	//任职(聘任)职务级别YHRS0015
    private java.lang.String	wageDutyLevel;	//工资职级，用于兑现工资待遇事业单位依据岗位级别转职务级别，可修改YHRS0015
    private java.lang.String	positioningReason;	//任职(聘任)原因YHRS0033
    private java.lang.String	positioningTypeCode;	//任职方式YHRS0032
    private java.lang.String	dutyChangeType;	//任职(聘任)变动类型YHRS0034
    private java.lang.String	dutyChangeTypeName;	//任职(聘任)变动类型描述
    private java.lang.String	positioningFileno;	//任职(聘任)文号
    private java.lang.String	positioningUnitDesc;	//任命(聘任)单位名称
    private java.util.Date	dutyDate;	//任职(聘任)开始日期
    private java.lang.String	dutyDateStr;	//任职(聘任)开始日期
    private java.lang.String	disposalReason;	//免职(解聘)原因类别YHRS0035
    private java.lang.String	disposalReasonDesc;	//免职(解聘)原因类别描述
    private java.lang.String	disposalFileno;	//免职(解聘)文号
    private java.lang.String	disposalUnit;	//免职(解聘)单位名称
    private java.lang.Long	postOid;	//主键
    private java.lang.String	positionStatus;	//岗位聘任状态YHRS0026
    private java.lang.String	positionType;	//岗位类别YHRS0022
    private java.lang.String	positionLevel;	//岗位级别YHRS0023
    private java.lang.String	positionName;	//岗位名称
    private java.util.Date	beginDate;	//岗位聘任开始时间
    private java.util.Date	endDate;	//岗位聘任拟截止时间
    private java.util.Date	endDateActual;	//岗位聘任实际截止时间
    private java.lang.String	beginDateStr;	//岗位聘任开始时间
    private java.lang.String	endDateStr;	//岗位聘任拟截止时间
    private java.lang.String	endDateActualStr;	//岗位聘任实际截止时间
    private java.lang.String	specialPositionType;	//特殊岗位类别 YHRS0111 如：教师、护士
    private java.lang.String	isMPosition;	//是否主岗位 YHRS0003
    private java.lang.String	remark;	//备注
    private java.lang.String	dutyRecordType;	//职务类型YHRS0036

    
	private java.lang.Long  	ptPostHistoryOid;	//主键

	private java.lang.Long  	ptPositioningHistoryOid;	//任职聘任信息OID，外键关联任职聘任信息表


    
	public java.lang.Long getPtPostHistoryOid() {
		return ptPostHistoryOid;
	}

	public void setPtPostHistoryOid(java.lang.Long ptPostHistoryOid) {
		this.ptPostHistoryOid = ptPostHistoryOid;
	}

	public java.lang.Long getPtPositioningHistoryOid() {
		return ptPositioningHistoryOid;
	}

	public void setPtPositioningHistoryOid(java.lang.Long ptPositioningHistoryOid) {
		this.ptPositioningHistoryOid = ptPositioningHistoryOid;
	}

	public Long getPositioningOid() {
		return positioningOid;
	}
	
	public void setPositioningOid(Long positioningOid) {
		this.positioningOid = positioningOid;
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

	public String getPositionStatus() {
		return positionStatus;
	}
	public void setPositionStatus(String positionStatus) {
		this.positionStatus = positionStatus;
	}
	public String getPositionType() {
		return positionType;
	}
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	public String getPositionLevel() {
		return positionLevel;
	}
	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
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

	public String getSpecialPositionType() {
		return specialPositionType;
	}
	public void setSpecialPositionType(String specialPositionType) {
		this.specialPositionType = specialPositionType;
	}
	public String getIsMPosition() {
		return isMPosition;
	}
	public void setIsMPosition(String isMPosition) {
		this.isMPosition = isMPosition;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDutyRecordType() {
		return dutyRecordType;
	}
	public void setDutyRecordType(String dutyRecordType) {
		this.dutyRecordType = dutyRecordType;
	}

	public java.lang.Long getPtPositioningHistroyOid() {
		return ptPositioningHistroyOid;
	}

	public void setPtPositioningHistroyOid(java.lang.Long ptPositioningHistroyOid) {
		this.ptPositioningHistroyOid = ptPositioningHistroyOid;
	}

	public java.lang.Long getBizPersonOid() {
		return bizPersonOid;
	}

	public void setBizPersonOid(java.lang.Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}

	public java.lang.String getCreatedByCode() {
		return createdByCode;
	}

	public void setCreatedByCode(java.lang.String createdByCode) {
		this.createdByCode = createdByCode;
	}

	public java.lang.String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(java.lang.String createdByName) {
		this.createdByName = createdByName;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.lang.String getUpdatedByCode() {
		return updatedByCode;
	}

	public void setUpdatedByCode(java.lang.String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}

	public java.lang.String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(java.lang.String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public java.lang.Long getPtPostOid() {
		return ptPostOid;
	}

	public void setPtPostOid(java.lang.Long ptPostOid) {
		this.ptPostOid = ptPostOid;
	}

	public Long getPersonOid() {
		return personOid;
	}

	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}

	public String getDutyDateStr() {
		return dutyDateStr;
	}

	public void setDutyDateStr(String dutyDateStr) {
		this.dutyDateStr = dutyDateStr;
	}

	public Long getPostOid() {
		return postOid;
	}

	public void setPostOid(Long postOid) {
		this.postOid = postOid;
	}

	public String getBeginDateStr() {
		return beginDateStr;
	}

	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public String getEndDateActualStr() {
		return endDateActualStr;
	}

	public void setEndDateActualStr(String endDateActualStr) {
		this.endDateActualStr = endDateActualStr;
	}

}
