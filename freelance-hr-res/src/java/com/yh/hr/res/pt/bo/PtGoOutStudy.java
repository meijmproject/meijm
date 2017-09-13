package com.yh.hr.res.pt.bo;

import java.util.Date;

import com.yh.platform.core.bo.BaseBo;
/**
 * @description 外出进修业务表BO
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 *
 */
public class PtGoOutStudy extends BaseBo {

	private static final long serialVersionUID = 2667589215624192017L;

	/**
     *主键ID
     **/
	private Long ptGoOutStudyOid;
	/**
	 * 业务人员OID
	 */
	private Long bizPersonOid;
	/**
	 * 基础OID
	 */
	private Long pbGoOutStudyOid;
	/**
	 * 前往进修单位
	 */
	private String goStudyUnit;
	/**
	 * 申请进修专业
	 */
	private String applyStudyMajor;
	/**
     * 开始时间
     **/
	private Date startDate;
    /**
     * 结束时间
     **/
	private Date endDate;
	/**
	 * 开始当天假长
	 */
	private Double startDateDays;
	/**
	 * 结束当天假长
	 */
	private Double endDateDays;
	/**
     * 外出天数
     **/
	private Float dayCount;
	/**
	 * 含节假日天数
	 */
	private Double statutoryHolidayDays;
	/**
     * 经费来源
     **/
	private String budgetFrom;
	/**
	 * 住宿情况
	 */
	private String putUpStatus;
	/**
	 * 本人政治表现
	 */
	private String politicalPerformance;
	/**
	 * 本人专业水平与进修目的或要求
	 */
	private String professionalLevelPurpose;
	/**
     *备注
     **/
	private String remark;
    /**
     *创建人ID
     **/
	private String createBy;
    /**
     *创建人名称
     **/
	private String createName;
    /**
     *创建时间
     **/
	private Date createDate;
    /**
     *修改人ID
     **/
	private String updateBy;
    /**
     *修改人名称
     **/
	private String updateName;
    /**
     *修改时间
     **/
	private Date updateDate;
	public Long getPtGoOutStudyOid() {
		return ptGoOutStudyOid;
	}
	public void setPtGoOutStudyOid(Long ptGoOutStudyOid) {
		this.ptGoOutStudyOid = ptGoOutStudyOid;
	}
	public Long getBizPersonOid() {
		return bizPersonOid;
	}
	public void setBizPersonOid(Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}
	public Long getPbGoOutStudyOid() {
		return pbGoOutStudyOid;
	}
	public void setPbGoOutStudyOid(Long pbGoOutStudyOid) {
		this.pbGoOutStudyOid = pbGoOutStudyOid;
	}
	public String getGoStudyUnit() {
		return goStudyUnit;
	}
	public void setGoStudyUnit(String goStudyUnit) {
		this.goStudyUnit = goStudyUnit;
	}
	public String getApplyStudyMajor() {
		return applyStudyMajor;
	}
	public void setApplyStudyMajor(String applyStudyMajor) {
		this.applyStudyMajor = applyStudyMajor;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getStartDateDays() {
		return startDateDays;
	}
	public void setStartDateDays(Double startDateDays) {
		this.startDateDays = startDateDays;
	}
	public Double getEndDateDays() {
		return endDateDays;
	}
	public void setEndDateDays(Double endDateDays) {
		this.endDateDays = endDateDays;
	}
	public Float getDayCount() {
		return dayCount;
	}
	public void setDayCount(Float dayCount) {
		this.dayCount = dayCount;
	}
	public Double getStatutoryHolidayDays() {
		return statutoryHolidayDays;
	}
	public void setStatutoryHolidayDays(Double statutoryHolidayDays) {
		this.statutoryHolidayDays = statutoryHolidayDays;
	}
	public String getBudgetFrom() {
		return budgetFrom;
	}
	public void setBudgetFrom(String budgetFrom) {
		this.budgetFrom = budgetFrom;
	}
	public String getPutUpStatus() {
		return putUpStatus;
	}
	public void setPutUpStatus(String putUpStatus) {
		this.putUpStatus = putUpStatus;
	}
	public String getPoliticalPerformance() {
		return politicalPerformance;
	}
	public void setPoliticalPerformance(String politicalPerformance) {
		this.politicalPerformance = politicalPerformance;
	}
	public String getProfessionalLevelPurpose() {
		return professionalLevelPurpose;
	}
	public void setProfessionalLevelPurpose(String professionalLevelPurpose) {
		this.professionalLevelPurpose = professionalLevelPurpose;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
