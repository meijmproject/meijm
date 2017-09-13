package com.yh.hr.info.ver.unit.comm.dto;

import java.util.Date;

import com.yh.platform.core.util.DateUtil;




public class VerPbWageHistoryDTO {
	
	private java.lang.Long wageHistoryOid; //工资历史ID
	private java.lang.Long personOid;     //人员ID
	
	private Integer orderId ;//排序ID
	private String wageSeries;//工资系列
	private String changeType;//变动类型
	private Date startDateOfWage;//起薪时间
	private String treatmentLevel;//工资级别
	private String dutyLevel;//职务级别/岗位级别
	private String leaderFlag;//领导标志（Y/N）
	private Date dutyDate;//任职时间
	private Integer dutyBreakMonth;//任职中断月数
	private Date lowDutyDate;//低一任职时间
	private Integer lowDutyBreakMonth;//低一任职中断月数
	private String eduLevel;//学历等级
	private Double wageTotal;//工资总额
	private Double changeAmt;//增减额
	private String effectiveFlag;//生效标志
	private String correctFlag;//修正标志
	private String correctReason;//修正原因
	private String remark;//备注
	private String calProcessInfo;//计算过程	
	private String createdByCode;
	private String createdByName;
	private Date createdDate;
	private String updatedByCode;
	private String updatedByName; 
	private Date updatedDate;

	private Double dutyWage;//职务工资
	private String hasDutyWage;//是否有职务工资
	
	private Integer rank;//级别
	private Integer grade;//档次
	private Double levelWage;//级别工资
	private Integer promoteRankCount;//级别晋升累计数
	private Integer promoteGradeCount;//级别晋升累计数
	private Integer higeSetRank;//高定级别
	private Integer higeSetGrade;//高定档次
	private String hasLevelWage;//是否有级别工资
	
	private Double techGradeWage;//技术工人等级工资
	private String hasTechGradeWage;//是否显示技术工人等级工资
	
	private Double lifeAllowance;//生活性补贴（在职）
	private String hasLifeAllowance;//是否生活性补贴
	
	private Double jobAllowance;//工作性津贴（在职）
	private String hasJobAllowance;//是否有工作性津贴
	
	private Double probationWage;//见习期工资
	private String hasProbationWage;//是否有见习期工资
	private Double primeWage;//初期工资(机关事业通用)
	private String hasPrimeWage;//是否有初期工资
	
	private Double probationLifeAllowance;//见习期生活性津贴
	private String hasProbationLifeAllowance;//是否有见习期生活性津贴
	private Double probationJobAllowance;//见习期工作性津贴
	private String hasProbationJobAllowance;//是否有见习期工作性津贴
	
	//起薪时间Str
	private String startDateOfWageStr;
	private String dutyDateStr;
	private String lowDutyDateStr;

	private Double workerPositionWage;//岗位工资(机关)
	private String hasWorkerPositionWage;//是否有岗位工资	
	private String positionLevel;//岗位级别
	private Integer promotePositionCount;//岗位晋升累计数	
	
	private Double positionWage;//岗位工资(事业)
	private String hasPositionWage;//是否有岗位工资
	private String instPositionLevel;//事业单位岗位级别
	private String positionKind;//岗位类别
	private String isSpecialDuty;//是否特殊岗位
	
	
	private String isEducationUnit;//义务教育事业单位
	private Integer salaryLevel;//薪级
	
	private Double instPositionWage;//事业单位岗位工资
	private String hasInstPositionWage;//是否有事业单位岗位工资
	
	private Double salaryLevelWage;//薪级工资
	private String hasSalaryLevelWage;//是否有薪级工资
	
	private Double positionAllowance;//岗位津贴
	private String hasPositionAllowance;//是否有岗位津贴
	
	private Double livingSubsidy;//生活性补贴
	private String hasLivingSubsidy;//是否有生活性补贴
	
	private Double workYearWage;//工龄补贴
	private String hasWorkYearWage;//是否有工龄补贴
	
	//新增字段
	private Date 	floatedSalaryLevelDateStr;//浮动日期
	private String floatedSalaryLevelDate;
	private Integer floatedSalaryLevel;//浮动薪级
	private Double floatedSalaryWage;//浮动薪级工资
	private String hasFloatedSalaryWage;//是否有浮动薪级工资
	
	private Double floatedRatio; //上浮比例
	private Double teacheNurseAllowance;//教师护士上浮工资
	private String hasTeacheNurseAllowance;//是否有教师护士上浮工资
	
	private Double  proPositionAllowance;  //事业单位见习期岗位津贴
	private String hasProPositionAllowance;//是否有事业单位见习期岗位津贴
	
	
	
	public Double getProPositionAllowance() {
		return proPositionAllowance;
	}
	public void setProPositionAllowance(Double proPositionAllowance) {
		this.proPositionAllowance = proPositionAllowance;
	}
	public String getHasProPositionAllowance() {
		return hasProPositionAllowance;
	}
	public void setHasProPositionAllowance(String hasProPositionAllowance) {
		this.hasProPositionAllowance = hasProPositionAllowance;
	}
	public Double getWorkerPositionWage() {
		return workerPositionWage;
	}
	public void setWorkerPositionWage(Double workerPositionWage) {
		this.workerPositionWage = workerPositionWage;
	}
	public String getHasWorkerPositionWage() {
		return hasWorkerPositionWage;
	}
	public void setHasWorkerPositionWage(String hasWorkerPositionWage) {
		this.hasWorkerPositionWage = hasWorkerPositionWage;
	}
	public Integer getFloatedSalaryLevel() {
		return floatedSalaryLevel;
	}
	public void setFloatedSalaryLevel(Integer floatedSalaryLevel) {
		this.floatedSalaryLevel = floatedSalaryLevel;
	}
	public Double getFloatedRatio() {
		return floatedRatio;
	}
	public void setFloatedRatio(Double floatedRatio) {
		this.floatedRatio = floatedRatio;
	}
	public String getFloatedSalaryLevelDate() {
		return floatedSalaryLevelDate;
	}
	public void setFloatedSalaryLevelDate(String floatedSalaryLevelDate) {
		this.floatedSalaryLevelDate = floatedSalaryLevelDate;
		this.floatedSalaryLevelDateStr = DateUtil.parseDate(floatedSalaryLevelDate+"-01-01");
	}
	
	public Date getFloatedSalaryLevelDateStr() {
		return floatedSalaryLevelDateStr;
	}
	public void setFloatedSalaryLevelDateStr(Date floatedSalaryLevelDateStr) {
		if (null != floatedSalaryLevelDateStr) {
			this.floatedSalaryLevelDateStr = floatedSalaryLevelDateStr;
			this.floatedSalaryLevelDate = DateUtil.formatDate(floatedSalaryLevelDateStr);
		}
	}
	public String getLowDutyDateStr() {
		return lowDutyDateStr;
	}
	public void setLowDutyDateStr(String lowDutyDateStr) {
		this.lowDutyDateStr = lowDutyDateStr;
		this.lowDutyDate = DateUtil.parseDate(lowDutyDateStr+"-01-01");
	}	
	
	public Double getFloatedSalaryWage() {
		return floatedSalaryWage;
	}
	public void setFloatedSalaryWage(Double floatedSalaryWage) {
		this.floatedSalaryWage = floatedSalaryWage;
	}
	public String getHasFloatedSalaryWage() {
		return hasFloatedSalaryWage;
	}
	public void setHasFloatedSalaryWage(String hasFloatedSalaryWage) {
		this.hasFloatedSalaryWage = hasFloatedSalaryWage;
	}
	public Double getTeacheNurseAllowance() {
		return teacheNurseAllowance;
	}
	public void setTeacheNurseAllowance(Double teacheNurseAllowance) {
		this.teacheNurseAllowance = teacheNurseAllowance;
	}
	public String getHasTeacheNurseAllowance() {
		return hasTeacheNurseAllowance;
	}
	public void setHasTeacheNurseAllowance(String hasTeacheNurseAllowance) {
		this.hasTeacheNurseAllowance = hasTeacheNurseAllowance;
	}
	public Date getLowDutyDate() {
		return lowDutyDate;
	}

	public void setLowDutyDate(Date lowDutyDate) {
		if (null != lowDutyDate) {
			this.lowDutyDate = lowDutyDate;
			this.lowDutyDateStr = DateUtil.formatDate(lowDutyDate);
		}
		this.lowDutyDate = lowDutyDate;
	}
	public Date getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(Date dutyDate) {
		if (null != dutyDate) {
			this.dutyDate = dutyDate;
			this.dutyDateStr = DateUtil.formatDate(dutyDate);
		}
	}
	
	public String getDutyDateStr() {
		return dutyDateStr;
	}
	public void setDutyDateStr(String dutyDateStr) {
		this.dutyDateStr = dutyDateStr;
		this.dutyDate = DateUtil.parseDate(dutyDateStr+"-01-01");
	}
	

	public java.lang.Long getWageHistoryOid() {
		return wageHistoryOid;
	}
	public void setWageHistoryOid(java.lang.Long wageHistoryOid) {
		this.wageHistoryOid = wageHistoryOid;
	}
	public java.lang.Long getPersonOid() {
		return personOid;
	}

	public void setPersonOid(java.lang.Long personOid) {
		this.personOid = personOid;
	}

	public String getInstPositionLevel() {
		return instPositionLevel;
	}

	public void setInstPositionLevel(String instPositionLevel) {
		this.instPositionLevel = instPositionLevel;
	}

	public String getPositionKind() {
		return positionKind;
	}

	public void setPositionKind(String positionKind) {
		this.positionKind = positionKind;
	}

	public String getIsSpecialDuty() {
		return isSpecialDuty;
	}

	public void setIsSpecialDuty(String isSpecialDuty) {
		this.isSpecialDuty = isSpecialDuty;
	}

	public String getIsEducationUnit() {
		return isEducationUnit;
	}

	public void setIsEducationUnit(String isEducationUnit) {
		this.isEducationUnit = isEducationUnit;
	}

	public Integer getSalaryLevel() {
		return salaryLevel;
	}

	public void setSalaryLevel(Integer salaryLevel) {
		this.salaryLevel = salaryLevel;
	}

	public Double getInstPositionWage() {
		return instPositionWage;
	}

	public void setInstPositionWage(Double instPositionWage) {
		this.instPositionWage = instPositionWage;
	}

	public String getHasInstPositionWage() {
		return hasInstPositionWage;
	}

	public void setHasInstPositionWage(String hasInstPositionWage) {
		this.hasInstPositionWage = hasInstPositionWage;
	}

	public Double getSalaryLevelWage() {
		return salaryLevelWage;
	}

	public void setSalaryLevelWage(Double salaryLevelWage) {
		this.salaryLevelWage = salaryLevelWage;
	}

	public String getHasSalaryLevelWage() {
		return hasSalaryLevelWage;
	}

	public void setHasSalaryLevelWage(String hasSalaryLevelWage) {
		this.hasSalaryLevelWage = hasSalaryLevelWage;
	}

	public Double getPositionAllowance() {
		return positionAllowance;
	}

	public void setPositionAllowance(Double positionAllowance) {
		this.positionAllowance = positionAllowance;
	}

	public String getHasPositionAllowance() {
		return hasPositionAllowance;
	}

	public void setHasPositionAllowance(String hasPositionAllowance) {
		this.hasPositionAllowance = hasPositionAllowance;
	}

	public Double getLifeAllowance() {
		return lifeAllowance;
	}
	public void setLifeAllowance(Double lifeAllowance) {
		this.lifeAllowance = lifeAllowance;
	}
	public String getHasLifeAllowance() {
		return hasLifeAllowance;
	}
	public void setHasLifeAllowance(String hasLifeAllowance) {
		this.hasLifeAllowance = hasLifeAllowance;
	}
	public Double getWorkYearWage() {
		return workYearWage;
	}
	public void setWorkYearWage(Double workYearWage) {
		this.workYearWage = workYearWage;
	}
	public String getHasWorkYearWage() {
		return hasWorkYearWage;
	}
	public void setHasWorkYearWage(String hasWorkYearWage) {
		this.hasWorkYearWage = hasWorkYearWage;
	}
	public String getCalProcessInfo() {
		return calProcessInfo;
	}

	public void setCalProcessInfo(String calProcessInfo) {
		this.calProcessInfo = calProcessInfo;
	}

	public String getCorrectFlag() {
		return correctFlag;
	}

	public void setCorrectFlag(String correctFlag) {
		this.correctFlag = correctFlag;
	}

	public String getCorrectReason() {
		return correctReason;
	}

	public void setCorrectReason(String correctReason) {
		this.correctReason = correctReason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	public Date getStartDateOfWage() {
		return startDateOfWage;
	}
	public void setStartDateOfWage(Date startDateOfWage) {
		if (null != startDateOfWage) {
			this.startDateOfWage = startDateOfWage;
			this.startDateOfWageStr = DateUtil.formatDate(startDateOfWage);
		}
	}

	public String getStartDateOfWageStr() {
		return startDateOfWageStr;
	}
	public void setStartDateOfWageStr(String startDateOfWageStr) {
		this.startDateOfWageStr = startDateOfWageStr;
		this.startDateOfWage = DateUtil.parseDate(startDateOfWageStr+"-01-01");
	}


	public String getWageSeries() {
		return wageSeries;
	}

	public void setWageSeries(String wageSeries) {
		this.wageSeries = wageSeries;
	}

	public String getTreatmentLevel() {
		return treatmentLevel;
	}

	public void setTreatmentLevel(String treatmentLevel) {
		this.treatmentLevel = treatmentLevel;
	}

	public String getDutyLevel() {
		return dutyLevel;
	}

	public void setDutyLevel(String dutyLevel) {
		this.dutyLevel = dutyLevel;
	}

	public String getLeaderFlag() {
		return leaderFlag;
	}

	public void setLeaderFlag(String leaderFlag) {
		this.leaderFlag = leaderFlag;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getDutyBreakMonth() {
		return dutyBreakMonth;
	}

	public void setDutyBreakMonth(Integer dutyBreakMonth) {
		this.dutyBreakMonth = dutyBreakMonth;
	}

	public Integer getLowDutyBreakMonth() {
		return lowDutyBreakMonth;
	}

	public void setLowDutyBreakMonth(Integer lowDutyBreakMonth) {
		this.lowDutyBreakMonth = lowDutyBreakMonth;
	}

	public String getEduLevel() {
		return eduLevel;
	}

	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}

	public Double getWageTotal() {
		return wageTotal;
	}

	public void setWageTotal(Double wageTotal) {
		this.wageTotal = wageTotal;
	}

	public Double getChangeAmt() {
		return changeAmt;
	}

	public void setChangeAmt(Double changeAmt) {
		this.changeAmt = changeAmt;
	}

	public String getEffectiveFlag() {
		return effectiveFlag;
	}

	public void setEffectiveFlag(String effectiveFlag) {
		this.effectiveFlag = effectiveFlag;
	}

	public String getCreatedByCode() {
		return createdByCode;
	}

	public void setCreatedByCode(String createdByCode) {
		this.createdByCode = createdByCode;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedByCode() {
		return updatedByCode;
	}

	public void setUpdatedByCode(String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Double getDutyWage() {
		return dutyWage;
	}

	public void setDutyWage(Double dutyWage) {
		this.dutyWage = dutyWage;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Double getLevelWage() {
		return levelWage;
	}

	public void setLevelWage(Double levelWage) {
		this.levelWage = levelWage;
	}

	public Integer getPromoteRankCount() {
		return promoteRankCount;
	}

	public void setPromoteRankCount(Integer promoteRankCount) {
		this.promoteRankCount = promoteRankCount;
	}

	public Integer getPromoteGradeCount() {
		return promoteGradeCount;
	}

	public void setPromoteGradeCount(Integer promoteGradeCount) {
		this.promoteGradeCount = promoteGradeCount;
	}

	public Integer getHigeSetRank() {
		return higeSetRank;
	}

	public void setHigeSetRank(Integer higeSetRank) {
		this.higeSetRank = higeSetRank;
	}

	public Integer getHigeSetGrade() {
		return higeSetGrade;
	}

	public void setHigeSetGrade(Integer higeSetGrade) {
		this.higeSetGrade = higeSetGrade;
	}


	public String getPositionLevel() {
		return positionLevel;
	}
	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}
	public Double getPositionWage() {
		return positionWage;
	}

	public void setPositionWage(Double positionWage) {
		this.positionWage = positionWage;
	}

	public Integer getPromotePositionCount() {
		return promotePositionCount;
	}

	public void setPromotePositionCount(Integer promotePositionCount) {
		this.promotePositionCount = promotePositionCount;
	}


	public Double getTechGradeWage() {
		return techGradeWage;
	}

	public void setTechGradeWage(Double techGradeWage) {
		this.techGradeWage = techGradeWage;
	}


	public Double getProbationWage() {
		return probationWage;
	}

	public void setProbationWage(Double probationWage) {
		this.probationWage = probationWage;
	}


	public String getHasDutyWage() {
		return hasDutyWage;
	}

	public void setHasDutyWage(String hasDutyWage) {
		this.hasDutyWage = hasDutyWage;
	}

	public String getHasLevelWage() {
		return hasLevelWage;
	}

	public void setHasLevelWage(String hasLevelWage) {
		this.hasLevelWage = hasLevelWage;
	}

	public String getHasPositionWage() {
		return hasPositionWage;
	}

	public void setHasPositionWage(String hasPositionWage) {
		this.hasPositionWage = hasPositionWage;
	}

	public String getHasTechGradeWage() {
		return hasTechGradeWage;
	}

	public void setHasTechGradeWage(String hasTechGradeWage) {
		this.hasTechGradeWage = hasTechGradeWage;
	}

	public Double getLivingSubsidy() {
		return livingSubsidy;
	}
	public void setLivingSubsidy(Double livingSubsidy) {
		this.livingSubsidy = livingSubsidy;
	}
	public String getHasLivingSubsidy() {
		return hasLivingSubsidy;
	}
	public void setHasLivingSubsidy(String hasLivingSubsidy) {
		this.hasLivingSubsidy = hasLivingSubsidy;
	}

	public Double getJobAllowance() {
		return jobAllowance;
	}
	public void setJobAllowance(Double jobAllowance) {
		this.jobAllowance = jobAllowance;
	}
	public String getHasJobAllowance() {
		return hasJobAllowance;
	}
	public void setHasJobAllowance(String hasJobAllowance) {
		this.hasJobAllowance = hasJobAllowance;
	}
	public String getHasProbationWage() {
		return hasProbationWage;
	}

	public void setHasProbationWage(String hasProbationWage) {
		this.hasProbationWage = hasProbationWage;
	}
	public Double getPrimeWage() {
		return primeWage;
	}
	public void setPrimeWage(Double primeWage) {
		this.primeWage = primeWage;
	}
	public String getHasPrimeWage() {
		return hasPrimeWage;
	}
	public void setHasPrimeWage(String hasPrimeWage) {
		this.hasPrimeWage = hasPrimeWage;
	}
	public Double getProbationLifeAllowance() {
		return probationLifeAllowance;
	}
	public void setProbationLifeAllowance(Double probationLifeAllowance) {
		this.probationLifeAllowance = probationLifeAllowance;
	}
	public String getHasProbationLifeAllowance() {
		return hasProbationLifeAllowance;
	}
	public void setHasProbationLifeAllowance(String hasProbationLifeAllowance) {
		this.hasProbationLifeAllowance = hasProbationLifeAllowance;
	}
	public Double getProbationJobAllowance() {
		return probationJobAllowance;
	}
	public void setProbationJobAllowance(Double probationJobAllowance) {
		this.probationJobAllowance = probationJobAllowance;
	}
	public String getHasProbationJobAllowance() {
		return hasProbationJobAllowance;
	}
	public void setHasProbationJobAllowance(String hasProbationJobAllowance) {
		this.hasProbationJobAllowance = hasProbationJobAllowance;
	}

}
