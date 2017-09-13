package com.yh.hr.res.pt.bo;

import com.yh.platform.core.bo.BaseBo;
public class PtWageHistory extends BaseBo{
			 
	/**
	 * 工资历史信息表
	 */
	private static final long serialVersionUID = -3462916638584092571L;
	
	private java.lang.Long ptWageHistoryOid;//业务工资历史ID
	private java.lang.Long bizPersonOid;//业务人员ID
	private java.lang.Long wageHistoryOid;//基础工资历史ID
	private java.lang.Long calWageHistoryOid;//计算值业务工资历史ID
	private java.lang.Integer orderId ;//排序ID
	private java.lang.String wageSeries;//工资系列
	private java.lang.String changeType;//变动类型
	private java.util.Date startDateOfWage;//起薪时间
	private java.lang.String treatmentLevel;//工资级别
	private java.lang.String dutyLevel;//职务级别/岗位级别
	private java.lang.String leaderFlag;//领导标志（Y/N）
	private java.util.Date dutyDate;//任职时间
	private java.lang.Integer dutyBreakMonth;//任职中断月数
	private java.util.Date lowDutyDate;//低一任职时间
	private java.lang.Integer lowDutyBreakMonth;//低一任职中断月数
	private java.lang.String eduLevel;//学历等级
	private java.lang.Double wageTotal;//工资总额
	private java.lang.Double changeAmt;//增减额
	private java.lang.String effectiveFlag;//生效标志
	private java.lang.String correctFlag;//修正标志
	private java.lang.String correctReason;//修正原因
	private java.lang.String remark;//备注
	private java.lang.String calProcessInfo;//计算过程
	private java.lang.String createdByCode;
	private java.lang.String createdByName;
	private java.util.Date createdDate;
	private java.lang.String updatedByCode;
	private java.lang.String updatedByName; 
	private java.util.Date updatedDate;
	
	public PtWageHistory(){
		
	}
	
	public java.lang.String getCalProcessInfo() {
		return calProcessInfo;
	}

	public void setCalProcessInfo(java.lang.String calProcessInfo) {
		this.calProcessInfo = calProcessInfo;
	}

	public java.lang.String getCorrectFlag() {
		return correctFlag;
	}

	public void setCorrectFlag(java.lang.String correctFlag) {
		this.correctFlag = correctFlag;
	}

	public java.lang.String getCorrectReason() {
		return correctReason;
	}

	public void setCorrectReason(java.lang.String correctReason) {
		this.correctReason = correctReason;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public java.lang.Long getPtWageHistoryOid() {
		return ptWageHistoryOid;
	}

	public void setPtWageHistoryOid(java.lang.Long ptWageHistoryOid) {
		this.ptWageHistoryOid = ptWageHistoryOid;
	}

	public java.lang.Long getBizPersonOid() {
		return bizPersonOid;
	}

	public void setBizPersonOid(java.lang.Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}

	public java.lang.Long getWageHistoryOid() {
		return wageHistoryOid;
	}

	public void setWageHistoryOid(java.lang.Long wageHistoryOid) {
		this.wageHistoryOid = wageHistoryOid;
	}

	public java.lang.Long getCalWageHistoryOid() {
		return calWageHistoryOid;
	}

	public void setCalWageHistoryOid(java.lang.Long calWageHistoryOid) {
		this.calWageHistoryOid = calWageHistoryOid;
	}

	public java.lang.Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(java.lang.Integer orderId) {
		this.orderId = orderId;
	}
	public java.lang.String getWageSeries() {
		return wageSeries;
	}
	public void setWageSeries(java.lang.String wageSeries) {
		this.wageSeries = wageSeries;
	}
	public java.lang.String getChangeType() {
		return changeType;
	}
	public void setChangeType(java.lang.String changeType) {
		this.changeType = changeType;
	}
	public java.util.Date getStartDateOfWage() {
		return startDateOfWage;
	}
	public void setStartDateOfWage(java.util.Date startDateOfWage) {
		this.startDateOfWage = startDateOfWage;
	}
	public java.lang.String getTreatmentLevel() {
		return treatmentLevel;
	}
	public void setTreatmentLevel(java.lang.String treatmentLevel) {
		this.treatmentLevel = treatmentLevel;
	}
	public java.lang.String getDutyLevel() {
		return dutyLevel;
	}
	public void setDutyLevel(java.lang.String dutyLevel) {
		this.dutyLevel = dutyLevel;
	}
	public java.lang.String getLeaderFlag() {
		return leaderFlag;
	}
	public void setLeaderFlag(java.lang.String leaderFlag) {
		this.leaderFlag = leaderFlag;
	}
	public java.util.Date getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(java.util.Date dutyDate) {
		this.dutyDate = dutyDate;
	}
	public java.lang.Integer getDutyBreakMonth() {
		return dutyBreakMonth;
	}
	public void setDutyBreakMonth(java.lang.Integer dutyBreakMonth) {
		this.dutyBreakMonth = dutyBreakMonth;
	}
	public java.util.Date getLowDutyDate() {
		return lowDutyDate;
	}
	public void setLowDutyDate(java.util.Date lowDutyDate) {
		this.lowDutyDate = lowDutyDate;
	}
	public java.lang.Integer getLowDutyBreakMonth() {
		return lowDutyBreakMonth;
	}
	public void setLowDutyBreakMonth(java.lang.Integer lowDutyBreakMonth) {
		this.lowDutyBreakMonth = lowDutyBreakMonth;
	}
	public java.lang.String getEduLevel() {
		return eduLevel;
	}
	public void setEduLevel(java.lang.String eduLevel) {
		this.eduLevel = eduLevel;
	}
	
	public java.lang.Double getWageTotal() {
		return wageTotal;
	}

	public void setWageTotal(java.lang.Double wageTotal) {
		this.wageTotal = wageTotal;
	}

	public java.lang.Double getChangeAmt() {
		return changeAmt;
	}

	public void setChangeAmt(java.lang.Double changeAmt) {
		this.changeAmt = changeAmt;
	}

	public java.lang.String getEffectiveFlag() {
		return effectiveFlag;
	}
	public void setEffectiveFlag(java.lang.String effectiveFlag) {
		this.effectiveFlag = effectiveFlag;
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
	
	
}
