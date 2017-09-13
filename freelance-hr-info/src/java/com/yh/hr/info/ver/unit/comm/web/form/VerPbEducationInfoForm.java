package com.yh.hr.info.ver.unit.comm.web.form;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

public class VerPbEducationInfoForm extends ValidatorForm
{
	private static final long serialVersionUID = -7439018202008504496L;
	
	private Long educationOid;
    
	private Long personOid;
    
	private String schoolName;
    
	private String academyName;
    
	private String academyDesc;
	
	private String schoolType;
    
	private String majorName;
    
	private Date schoolEnrollDate;
	
	private String schoolEnrollDateStr;
    
	private Date graduateDate;
	
	private String graduateDateStr;
    
	private String eductionalSystem;
    
	private String studyTypeCode;
    
	private String eduTypeCode;
    
	private String educationCode;
    
	private String educationCertificate;
    
	private String educationLevelCode;
    
	private String isHighestEducationLevel;
    
	private String degreeCode;
    
	private String degreeCertificateNo;
    
	private Date degreeGrantDate;
	
	private String degreeGrantDateStr;
    
	private String degreeGrantUnit;
    
	private String degreeGrantCountryCode;
    
	private String isHighestDegree;
    
	private String remark;

	public Long getEducationOid() {
		return educationOid;
	}

	public void setEducationOid(Long educationOid) {
		this.educationOid = educationOid;
	}

	public Long getPersonOid() {
		return personOid;
	}

	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getAcademyDesc() {
		return academyDesc;
	}

	public void setAcademyDesc(String academyDesc) {
		this.academyDesc = academyDesc;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Date getSchoolEnrollDate() {
		return schoolEnrollDate;
	}

	public void setSchoolEnrollDate(Date schoolEnrollDate) {
		if(null != schoolEnrollDate)
		{
			this.schoolEnrollDate = schoolEnrollDate;
			this.schoolEnrollDateStr = DateUtil.format(schoolEnrollDate, "yyyy-MM");
		}
	}

	public Date getGraduateDate() {
		return graduateDate;
	}

	public void setGraduateDate(Date graduateDate) {
		if(null != graduateDate)
		{
			this.graduateDate = graduateDate;
			this.graduateDateStr = DateUtil.format(graduateDate, "yyyy-MM");
		}
	}

	public String getEductionalSystem() {
		return eductionalSystem;
	}

	public void setEductionalSystem(String eductionalSystem) {
		this.eductionalSystem = eductionalSystem;
	}

	public String getStudyTypeCode() {
		return studyTypeCode;
	}

	public void setStudyTypeCode(String studyTypeCode) {
		this.studyTypeCode = studyTypeCode;
	}

	public String getEduTypeCode() {
		return eduTypeCode;
	}

	public void setEduTypeCode(String eduTypeCode) {
		this.eduTypeCode = eduTypeCode;
	}

	public String getEducationCode() {
		return educationCode;
	}

	public void setEducationCode(String educationCode) {
		this.educationCode = educationCode;
	}

	public String getEducationCertificate() {
		return educationCertificate;
	}

	public void setEducationCertificate(String educationCertificate) {
		this.educationCertificate = educationCertificate;
	}

	public String getEducationLevelCode() {
		return educationLevelCode;
	}

	public void setEducationLevelCode(String educationLevelCode) {
		this.educationLevelCode = educationLevelCode;
	}

	public String getIsHighestEducationLevel() {
		return isHighestEducationLevel;
	}

	public void setIsHighestEducationLevel(String isHighestEducationLevel) {
		this.isHighestEducationLevel = isHighestEducationLevel;
	}

	public String getDegreeCode() {
		return degreeCode;
	}

	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}

	public String getDegreeCertificateNo() {
		return degreeCertificateNo;
	}

	public void setDegreeCertificateNo(String degreeCertificateNo) {
		this.degreeCertificateNo = degreeCertificateNo;
	}

	public Date getDegreeGrantDate() {
		return degreeGrantDate;
	}

	public void setDegreeGrantDate(Date degreeGrantDate) {
		if(null != degreeGrantDate)
		{
			this.degreeGrantDate = degreeGrantDate;
			this.degreeGrantDateStr = DateUtil.formatDate(degreeGrantDate);
		}
	}

	public String getDegreeGrantUnit() {
		return degreeGrantUnit;
	}

	public void setDegreeGrantUnit(String degreeGrantUnit) {
		this.degreeGrantUnit = degreeGrantUnit;
	}

	public String getDegreeGrantCountryCode() {
		return degreeGrantCountryCode;
	}

	public void setDegreeGrantCountryCode(String degreeGrantCountryCode) {
		this.degreeGrantCountryCode = degreeGrantCountryCode;
	}

	public String getIsHighestDegree() {
		return isHighestDegree;
	}

	public void setIsHighestDegree(String isHighestDegree) {
		this.isHighestDegree = isHighestDegree;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSchoolEnrollDateStr() {
		return schoolEnrollDateStr;
	}

	public void setSchoolEnrollDateStr(String schoolEnrollDateStr) {
		if(StringUtils.isNotEmpty(schoolEnrollDateStr))
		{
			this.schoolEnrollDateStr = schoolEnrollDateStr.trim();
			this.schoolEnrollDate = DateUtil.parse(schoolEnrollDateStr, "yyyy-MM");
		}
	}

	public String getGraduateDateStr() {
		return graduateDateStr;
	}

	public void setGraduateDateStr(String graduateDateStr) {
		if(StringUtils.isNotEmpty(graduateDateStr))
		{
			this.graduateDateStr = graduateDateStr.trim();
			this.graduateDate = DateUtil.parse(graduateDateStr, "yyyy-MM");
		}
	}

	public String getDegreeGrantDateStr() {
		return degreeGrantDateStr;
	}

	public void setDegreeGrantDateStr(String degreeGrantDateStr) {
		if(StringUtils.isNotEmpty(degreeGrantDateStr))
		{
			this.degreeGrantDateStr = degreeGrantDateStr.trim();
			this.degreeGrantDate = DateUtil.parseDate(degreeGrantDateStr);
		}
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
}
