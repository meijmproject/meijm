package com.yh.hr.res.pb.dto;

public class PbEducationInfoDTO
{
	/**
     *主键OID
     **/
	private java.lang.Long educationOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *学校
     **/
	private java.lang.String schoolName;
    /**
     *所在院系
     **/
	private java.lang.String academyName;
    /**
     *院系描述
     **/
	private java.lang.String academyDesc;
	/**
     *院校类别
     **/
	private java.lang.String schoolType;
    /**
     *专业名称
     **/
	private java.lang.String majorName;
    /**
     *入学日期
     **/
	private java.util.Date schoolEnrollDate;
    /**
     *毕（肄）业日期
     **/
	private java.util.Date graduateDate;
    /**
     *学制（年）
     **/
	private java.lang.String eductionalSystem;
    /**
     *学习形式YHRS0042
     **/
	private java.lang.String studyTypeCode;
    /**
     *教育类别YHRS0043
     **/
	private java.lang.String eduTypeCode;
    /**
     *学历YHRS0039
     **/
	private java.lang.String educationCode;
    /**
     *学历证书号码
     **/
	private java.lang.String educationCertificate;
    /**
     *学历等级YHRS0041
     **/
	private java.lang.String educationLevelCode;
    /**
     *是否当前最高学历标识YHRS0003
     **/
	private java.lang.String isHighestEducationLevel;
    /**
     *学位YHRS0040
     **/
	private java.lang.String degreeCode;
    /**
     *学位证书号码
     **/
	private java.lang.String degreeCertificateNo;
    /**
     *学位授予日期
     **/
	private java.util.Date degreeGrantDate;
    /**
     *学位授予单位
     **/
	private java.lang.String degreeGrantUnit;
    /**
     *学位授予国家YHRS0005
     **/
	private java.lang.String degreeGrantCountryCode;
    /**
     *是否最高学位YHRS0003
     **/
	private java.lang.String isHighestDegree;
    /**
     *备注
     **/
	private java.lang.String remark;
	
	/**
	 * 全日制教育-毕业院校系及专业
	 */
	private String qrzXxzy;
	
	/**
	 * 在职教育-毕业院校系及专业
	 */
	private String zzXxzy;
	
	public java.lang.Long getEducationOid() {
		return educationOid;
	}
	public void setEducationOid(java.lang.Long educationOid) {
		this.educationOid = educationOid;
	}
	public java.lang.Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(java.lang.Long personOid) {
		this.personOid = personOid;
	}
	public java.lang.String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(java.lang.String schoolName) {
		this.schoolName = schoolName;
	}
	public java.lang.String getAcademyName() {
		return academyName;
	}
	public void setAcademyName(java.lang.String academyName) {
		this.academyName = academyName;
	}
	public java.lang.String getAcademyDesc() {
		return academyDesc;
	}
	public void setAcademyDesc(java.lang.String academyDesc) {
		this.academyDesc = academyDesc;
	}
	public java.lang.String getMajorName() {
		return majorName;
	}
	public void setMajorName(java.lang.String majorName) {
		this.majorName = majorName;
	}
	public java.util.Date getSchoolEnrollDate() {
		return schoolEnrollDate;
	}
	public void setSchoolEnrollDate(java.util.Date schoolEnrollDate) {
		this.schoolEnrollDate = schoolEnrollDate;
	}
	public java.util.Date getGraduateDate() {
		return graduateDate;
	}
	public void setGraduateDate(java.util.Date graduateDate) {
		this.graduateDate = graduateDate;
	}
	public java.lang.String getEductionalSystem() {
		return eductionalSystem;
	}
	public void setEductionalSystem(java.lang.String eductionalSystem) {
		this.eductionalSystem = eductionalSystem;
	}
	public java.lang.String getStudyTypeCode() {
		return studyTypeCode;
	}
	public void setStudyTypeCode(java.lang.String studyTypeCode) {
		this.studyTypeCode = studyTypeCode;
	}
	public java.lang.String getEduTypeCode() {
		return eduTypeCode;
	}
	public void setEduTypeCode(java.lang.String eduTypeCode) {
		this.eduTypeCode = eduTypeCode;
	}
	public java.lang.String getEducationCode() {
		return educationCode;
	}
	public void setEducationCode(java.lang.String educationCode) {
		this.educationCode = educationCode;
	}
	public java.lang.String getEducationCertificate() {
		return educationCertificate;
	}
	public void setEducationCertificate(java.lang.String educationCertificate) {
		this.educationCertificate = educationCertificate;
	}
	public java.lang.String getEducationLevelCode() {
		return educationLevelCode;
	}
	public void setEducationLevelCode(java.lang.String educationLevelCode) {
		this.educationLevelCode = educationLevelCode;
	}
	public java.lang.String getIsHighestEducationLevel() {
		return isHighestEducationLevel;
	}
	public void setIsHighestEducationLevel(java.lang.String isHighestEducationLevel) {
		this.isHighestEducationLevel = isHighestEducationLevel;
	}
	public java.lang.String getDegreeCode() {
		return degreeCode;
	}
	public void setDegreeCode(java.lang.String degreeCode) {
		this.degreeCode = degreeCode;
	}
	public java.lang.String getDegreeCertificateNo() {
		return degreeCertificateNo;
	}
	public void setDegreeCertificateNo(java.lang.String degreeCertificateNo) {
		this.degreeCertificateNo = degreeCertificateNo;
	}
	public java.util.Date getDegreeGrantDate() {
		return degreeGrantDate;
	}
	public void setDegreeGrantDate(java.util.Date degreeGrantDate) {
		this.degreeGrantDate = degreeGrantDate;
	}
	public java.lang.String getDegreeGrantUnit() {
		return degreeGrantUnit;
	}
	public void setDegreeGrantUnit(java.lang.String degreeGrantUnit) {
		this.degreeGrantUnit = degreeGrantUnit;
	}
	public java.lang.String getDegreeGrantCountryCode() {
		return degreeGrantCountryCode;
	}
	public void setDegreeGrantCountryCode(java.lang.String degreeGrantCountryCode) {
		this.degreeGrantCountryCode = degreeGrantCountryCode;
	}
	public java.lang.String getIsHighestDegree() {
		return isHighestDegree;
	}
	public void setIsHighestDegree(java.lang.String isHighestDegree) {
		this.isHighestDegree = isHighestDegree;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public String getQrzXxzy() {
		return qrzXxzy;
	}
	public void setQrzXxzy(String qrzXxzy) {
		this.qrzXxzy = qrzXxzy;
	}
	public String getZzXxzy() {
		return zzXxzy;
	}
	public void setZzXxzy(String zzXxzy) {
		this.zzXxzy = zzXxzy;
	}
	public java.lang.String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(java.lang.String schoolType) {
		this.schoolType = schoolType;
	}
}
