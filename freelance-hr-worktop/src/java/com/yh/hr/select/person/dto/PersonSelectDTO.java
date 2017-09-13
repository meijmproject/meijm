/**
 * 
 */
package com.yh.hr.select.person.dto;

/**
 * 人员选择DTO
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */

public class PersonSelectDTO {
	private java.lang.Long		personOid;				// 人员主键
	private java.lang.String	name;					// 姓名
	private java.lang.String	idCode;					// 证件类别代码YHRS0002
	private java.lang.String	idNo;					// 证件号码
	private java.lang.String	administrativeDuty;		// 职务
	private java.lang.String	administrativeDutyLevel;// 职务层次YHRS0015
	private java.util.Date		administrativeStartDate;// 职务层次起始时间
	private java.lang.Long		unitOid;				// 人员所在单位
	private java.lang.String	unitName;				// 人员所在单位
	private java.lang.Long		deptOid;				// 人员所在内设机构
	private java.lang.String	deptName;				// 人员所在内设机构
	private java.lang.String	personStatus;			// 人员状态YHRS0009
	private java.lang.String	personType;				// 人员类别YHRS0010

	private java.lang.String	mPositionName;			//主岗位名称
	private java.lang.String	sPositionName;			//辅岗位名称
	private java.lang.String	mPositionType;			//现聘岗位类别（主）YHRS0022
	private java.lang.String	mPositionLevel;			//现聘岗位级别（主）YHRS0023
	private java.lang.String	sPositionType;			//现聘岗位类别（副）YHRS0022
	private java.lang.String	sPositionLevel;			//现聘岗位级别（副）YHRS0023
	private java.util.Date		mPositionLevelDate;		//现聘岗位级别起始时间（主）
	private java.util.Date		sPositionLevelDate;		//现聘岗位级别起始时间（副）
	
	//
	private java.lang.String 	sexCode;			//性别代码YHRS0001
	private java.util.Date 		birthday;			//出生日期
	private java.lang.String 	peopleCode;			//民族代码国籍为中国时必填YHRS0004
	
	private java.lang.String 	ancestorPlaceCode;		//籍贯代码国籍为中国时必填YHRS0006
	
	private java.lang.String	ftSchoolName;			//全日制最高学校名称
	private java.lang.String	ftMajorName;			//全日制最高专业名称
	private java.lang.String	ftEducationLevelCode;	//全日制最高学历代码YHRS0039
	private java.lang.String	ftDegreeCode;			//全日制最高学位代码YHRS0040
	
	private java.lang.String	ojSchoolName;			//在职最高学校名称
	private java.lang.String	ojMajorName;			//在职最高专业名称
	private java.lang.String	ojEducationLevelCode;	//在职最高学历代码YHRS0039
	private java.lang.String	ojDegreeCode;			//在职最高学位代码YHRS0040
	private java.lang.String    administrativeDutyAttribute;
	
	private java.lang.String    isVerified;				//是否校核完成
	/**
	 * 数据迁移标示符（如果为：migration则为迁移数据）
	 * */
	private java.lang.String    createdByCode;				//是否为迁移数据
	
	private java.lang.String    reviewResultType;				//考核结论类别YHRS0070
	private java.lang.String    promoteYear;				//考核年度
	private java.lang.String    isCaculate;				//是否已计算
	
	private java.lang.String personCode;
	private java.lang.String orgName;
	private java.lang.Long hireDeptOid;
	private java.lang.Long goOutOid;
	public java.lang.Long getGoOutOid() {
		return goOutOid;
	}

	public void setGoOutOid(java.lang.Long goOutOid) {
		this.goOutOid = goOutOid;
	}

	private java.lang.String goOutAddress;
	private java.lang.String budgetFrom;
	private java.lang.Float dayCount;
	private java.lang.String 	startDate;
	private java.lang.String 	endDate;
	
	
	
	
	public java.lang.String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(java.lang.String personCode) {
		this.personCode = personCode;
	}

	public java.lang.String getOrgName() {
		return orgName;
	}

	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	public java.lang.Long getHireDeptOid() {
		return hireDeptOid;
	}

	public void setHireDeptOid(java.lang.Long hireDeptOid) {
		this.hireDeptOid = hireDeptOid;
	}

	public java.lang.String getGoOutAddress() {
		return goOutAddress;
	}

	public void setGoOutAddress(java.lang.String goOutAddress) {
		this.goOutAddress = goOutAddress;
	}

	public java.lang.String getBudgetFrom() {
		return budgetFrom;
	}

	public void setBudgetFrom(java.lang.String budgetFrom) {
		this.budgetFrom = budgetFrom;
	}

	public java.lang.Float getDayCount() {
		return dayCount;
	}

	public void setDayCount(java.lang.Float dayCount) {
		this.dayCount = dayCount;
	}


	public java.lang.String getStartDate() {
		return startDate;
	}

	public void setStartDate(java.lang.String startDate) {
		this.startDate = startDate;
	}

	public java.lang.String getEndDate() {
		return endDate;
	}

	public void setEndDate(java.lang.String endDate) {
		this.endDate = endDate;
	}

	public java.lang.String getPromoteYear() {
		return promoteYear;
	}

	public java.lang.String getIsCaculate() {
		return isCaculate;
	}

	public void setIsCaculate(java.lang.String isCaculate) {
		this.isCaculate = isCaculate;
	}

	public void setPromoteYear(java.lang.String promoteYear) {
		this.promoteYear = promoteYear;
	}

	public java.lang.String getReviewResultType() {
		return reviewResultType;
	}

	public void setReviewResultType(java.lang.String reviewResultType) {
		this.reviewResultType = reviewResultType;
	}

	public java.lang.String getAdministrativeDutyAttribute() {
		return administrativeDutyAttribute;
	}

	public void setAdministrativeDutyAttribute(
			java.lang.String administrativeDutyAttribute) {
		this.administrativeDutyAttribute = administrativeDutyAttribute;
	}

	public java.lang.Long getPersonOid() {
		return personOid;
	}

	public void setPersonOid(java.lang.Long personOid) {
		this.personOid = personOid;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getIdCode() {
		return idCode;
	}

	public void setIdCode(java.lang.String idCode) {
		this.idCode = idCode;
	}

	public java.lang.String getIdNo() {
		return idNo;
	}

	public void setIdNo(java.lang.String idNo) {
		this.idNo = idNo;
	}

	public java.lang.String getAdministrativeDuty() {
		return administrativeDuty;
	}

	public void setAdministrativeDuty(java.lang.String administrativeDuty) {
		this.administrativeDuty = administrativeDuty;
	}

	public java.lang.String getAdministrativeDutyLevel() {
		return administrativeDutyLevel;
	}

	public void setAdministrativeDutyLevel(java.lang.String administrativeDutyLevel) {
		this.administrativeDutyLevel = administrativeDutyLevel;
	}

	public java.util.Date getAdministrativeStartDate() {
		return administrativeStartDate;
	}

	public void setAdministrativeStartDate(java.util.Date administrativeStartDate) {
		this.administrativeStartDate = administrativeStartDate;
	}

	public java.lang.Long getUnitOid() {
		return unitOid;
	}

	public void setUnitOid(java.lang.Long unitOid) {
		this.unitOid = unitOid;
	}

	public java.lang.String getUnitName() {
		return unitName;
	}

	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}

	public java.lang.Long getDeptOid() {
		return deptOid;
	}

	public void setDeptOid(java.lang.Long deptOid) {
		this.deptOid = deptOid;
	}

	public java.lang.String getDeptName() {
		return deptName;
	}

	public void setDeptName(java.lang.String deptName) {
		this.deptName = deptName;
	}

	public java.lang.String getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(java.lang.String personStatus) {
		this.personStatus = personStatus;
	}

	public java.lang.String getPersonType() {
		return personType;
	}

	public void setPersonType(java.lang.String personType) {
		this.personType = personType;
	}

	public java.lang.String getmPositionName() {
		return mPositionName;
	}

	public void setmPositionName(java.lang.String mPositionName) {
		this.mPositionName = mPositionName;
	}

	public java.lang.String getsPositionName() {
		return sPositionName;
	}

	public void setsPositionName(java.lang.String sPositionName) {
		this.sPositionName = sPositionName;
	}

	public java.lang.String getmPositionType() {
		return mPositionType;
	}

	public void setmPositionType(java.lang.String mPositionType) {
		this.mPositionType = mPositionType;
	}

	public java.lang.String getmPositionLevel() {
		return mPositionLevel;
	}

	public void setmPositionLevel(java.lang.String mPositionLevel) {
		this.mPositionLevel = mPositionLevel;
	}

	public java.lang.String getsPositionType() {
		return sPositionType;
	}

	public void setsPositionType(java.lang.String sPositionType) {
		this.sPositionType = sPositionType;
	}

	public java.lang.String getsPositionLevel() {
		return sPositionLevel;
	}

	public void setsPositionLevel(java.lang.String sPositionLevel) {
		this.sPositionLevel = sPositionLevel;
	}

	public java.util.Date getmPositionLevelDate() {
		return mPositionLevelDate;
	}

	public void setmPositionLevelDate(java.util.Date mPositionLevelDate) {
		this.mPositionLevelDate = mPositionLevelDate;
	}

	public java.util.Date getsPositionLevelDate() {
		return sPositionLevelDate;
	}

	public void setsPositionLevelDate(java.util.Date sPositionLevelDate) {
		this.sPositionLevelDate = sPositionLevelDate;
	}

	public java.lang.String getSexCode() {
		return sexCode;
	}

	public void setSexCode(java.lang.String sexCode) {
		this.sexCode = sexCode;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public java.lang.String getPeopleCode() {
		return peopleCode;
	}

	public void setPeopleCode(java.lang.String peopleCode) {
		this.peopleCode = peopleCode;
	}

	public java.lang.String getAncestorPlaceCode() {
		return ancestorPlaceCode;
	}

	public void setAncestorPlaceCode(java.lang.String ancestorPlaceCode) {
		this.ancestorPlaceCode = ancestorPlaceCode;
	}

	public java.lang.String getFtSchoolName() {
		return ftSchoolName;
	}

	public void setFtSchoolName(java.lang.String ftSchoolName) {
		this.ftSchoolName = ftSchoolName;
	}

	public java.lang.String getFtMajorName() {
		return ftMajorName;
	}

	public void setFtMajorName(java.lang.String ftMajorName) {
		this.ftMajorName = ftMajorName;
	}

	public java.lang.String getFtEducationLevelCode() {
		return ftEducationLevelCode;
	}

	public void setFtEducationLevelCode(java.lang.String ftEducationLevelCode) {
		this.ftEducationLevelCode = ftEducationLevelCode;
	}

	public java.lang.String getFtDegreeCode() {
		return ftDegreeCode;
	}

	public void setFtDegreeCode(java.lang.String ftDegreeCode) {
		this.ftDegreeCode = ftDegreeCode;
	}

	public java.lang.String getOjSchoolName() {
		return ojSchoolName;
	}

	public void setOjSchoolName(java.lang.String ojSchoolName) {
		this.ojSchoolName = ojSchoolName;
	}

	public java.lang.String getOjMajorName() {
		return ojMajorName;
	}

	public void setOjMajorName(java.lang.String ojMajorName) {
		this.ojMajorName = ojMajorName;
	}

	public java.lang.String getOjEducationLevelCode() {
		return ojEducationLevelCode;
	}

	public void setOjEducationLevelCode(java.lang.String ojEducationLevelCode) {
		this.ojEducationLevelCode = ojEducationLevelCode;
	}

	public java.lang.String getOjDegreeCode() {
		return ojDegreeCode;
	}

	public void setOjDegreeCode(java.lang.String ojDegreeCode) {
		this.ojDegreeCode = ojDegreeCode;
	}

	public java.lang.String getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(java.lang.String isVerified) {
		this.isVerified = isVerified;
	}

	public java.lang.String getCreatedByCode() {
		return createdByCode;
	}

	public void setCreatedByCode(java.lang.String createdByCode) {
		this.createdByCode = createdByCode;
	}
}
