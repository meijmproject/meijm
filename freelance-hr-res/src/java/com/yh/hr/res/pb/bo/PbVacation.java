package com.yh.hr.res.pb.bo;

import java.util.Date;

import com.yh.platform.core.bo.BaseBo;

public class PbVacation extends BaseBo {

	private static final long serialVersionUID = -794236900442393133L;
	
	private Long vacationOid;	//主键ID
	private Long personOid;	//人员ID
	private String vacationType;//休假类型
	private Date startDate;	//休假开始时间
	private Date endDate;	//休假结束时间
	private Double vacationDays;	//请假天数
	private Double statutoryHolidayDays;	//包含法定假天数
	private String reason;	//休假原因
	private String workHandover;	//工作交接事项
	private String workAgent;	//工作代理人
	private String emergContact;	//紧急联络人
	private String emergPhone;	//紧急联系人电话
	private String remark;	//备注
	private String vacationStatus;	//请销假状态 1：请假	2：销假
	private Double startDateDays;	//开始当天假长
	private Double endDateDays;		//结束当天假长
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
	public Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}
	public String getVacationType() {
		return vacationType;
	}
	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
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
	public Double getVacationDays() {
		return vacationDays;
	}
	public void setVacationDays(Double vacationDays) {
		this.vacationDays = vacationDays;
	}
	public Double getStatutoryHolidayDays() {
		return statutoryHolidayDays;
	}
	public void setStatutoryHolidayDays(Double statutoryHolidayDays) {
		this.statutoryHolidayDays = statutoryHolidayDays;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getWorkHandover() {
		return workHandover;
	}
	public void setWorkHandover(String workHandover) {
		this.workHandover = workHandover;
	}
	public String getWorkAgent() {
		return workAgent;
	}
	public void setWorkAgent(String workAgent) {
		this.workAgent = workAgent;
	}
	public String getEmergContact() {
		return emergContact;
	}
	public void setEmergContact(String emergContact) {
		this.emergContact = emergContact;
	}
	public String getEmergPhone() {
		return emergPhone;
	}
	public void setEmergPhone(String emergPhone) {
		this.emergPhone = emergPhone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getVacationStatus() {
		return vacationStatus;
	}
	public void setVacationStatus(String vacationStatus) {
		this.vacationStatus = vacationStatus;
	}

}
