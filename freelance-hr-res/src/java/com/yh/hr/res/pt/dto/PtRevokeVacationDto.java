package com.yh.hr.res.pt.dto;

import java.util.Date;

public class PtRevokeVacationDto {

	private Long bizRevokeVacationOid;	//主键OID
	private Long vacationOid;	//关联请休假业务表主键ID的外键
	private Long baseRevokeVacationOid;	//基础OID
	private Date revokeStartDate;	//销假开始时间
	private Date revokeEndDate;	//销假结束时间
	private Double startDateDays; //开始当天假长
	private Double endDateDays; //结束当天假长
	private Double revokeVacationDays;	//销假天数
	private Double statutoryHolidayDays; //含节假日天数
	private String remark;	//备注
	private String createBy;	//创建人ID
	private String createName;	//创建人名称
	private Date createDate;	//创建时间
	private String updateBy;	//修改人ID
	private String updateName;	//修改人名称
	private Date updateDate;	//修改时间
	public Long getVacationOid() {
		return vacationOid;
	}
	public void setVacationOid(Long vacationOid) {
		this.vacationOid = vacationOid;
	}
	public Long getBizRevokeVacationOid() {
		return bizRevokeVacationOid;
	}
	public void setBizRevokeVacationOid(Long bizRevokeVacationOid) {
		this.bizRevokeVacationOid = bizRevokeVacationOid;
	}
	public Long getBaseRevokeVacationOid() {
		return baseRevokeVacationOid;
	}
	public void setBaseRevokeVacationOid(Long baseRevokeVacationOid) {
		this.baseRevokeVacationOid = baseRevokeVacationOid;
	}
	public Date getRevokeStartDate() {
		return revokeStartDate;
	}
	public void setRevokeStartDate(Date revokeStartDate) {
		this.revokeStartDate = revokeStartDate;
	}
	public Date getRevokeEndDate() {
		return revokeEndDate;
	}
	public void setRevokeEndDate(Date revokeEndDate) {
		this.revokeEndDate = revokeEndDate;
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
	public Double getRevokeVacationDays() {
		return revokeVacationDays;
	}
	public void setRevokeVacationDays(Double revokeVacationDays) {
		this.revokeVacationDays = revokeVacationDays;
	}
	public Double getStatutoryHolidayDays() {
		return statutoryHolidayDays;
	}
	public void setStatutoryHolidayDays(Double statutoryHolidayDays) {
		this.statutoryHolidayDays = statutoryHolidayDays;
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
