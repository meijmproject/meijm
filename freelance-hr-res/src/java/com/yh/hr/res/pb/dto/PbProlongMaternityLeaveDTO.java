package com.yh.hr.res.pb.dto;

import java.util.Date;
/**
 * 延长产假基础信息DTO
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 *
 */
public class PbProlongMaternityLeaveDTO {

	private Long vacationOid;	//主键ID
	private Date proLongStartDate;//延长产假开始日期
	private Date proLOngEndDate;//延长产假结束日期
	private Double startDateDays; //开始当天假长
	private Double endDateDays; //结束当天假长
	private Double vacationDays;//请假天数
	private String remark;		//备注
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
	
	public Date getProLongStartDate() {
		return proLongStartDate;
	}
	public void setProLongStartDate(Date proLongStartDate) {
		this.proLongStartDate = proLongStartDate;
	}
	public Date getProLOngEndDate() {
		return proLOngEndDate;
	}
	public void setProLOngEndDate(Date proLOngEndDate) {
		this.proLOngEndDate = proLOngEndDate;
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
	public Double getVacationDays() {
		return vacationDays;
	}
	public void setVacationDays(Double vacationDays) {
		this.vacationDays = vacationDays;
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
