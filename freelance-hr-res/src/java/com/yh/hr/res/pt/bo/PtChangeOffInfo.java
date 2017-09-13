/**
 * This file is generated by  code generation tool version 0.2 ^_^
 * Created at 2016-11-15
**/
package com.yh.hr.res.pt.bo;

import com.yh.platform.core.bo.BaseBo;

import java.util.Date;

public class PtChangeOffInfo extends BaseBo {

	private static final long serialVersionUID = 2427492513585615819L;

	public PtChangeOffInfo() {

	}

	private Long changeOffOid;//主键ID
	private Long bizPersonOid;//bizPersonOid
	private Long baseChangeOffOid;	//基础OID
	private String changeOffType;//调休类型
	private Date startDate;//开始时间
	private Date endDate;//结束时间
	private Float changeOffDays;//调休天数
	private String reason;//调休事由
	private String workHandover;//工作交接事项
	private String workAgent;//工作代理人
	private String emergContact;//紧急联络人
	private String emergPhone;//紧急联络人电话
	private String remark;//备注
	private String createBy;//创建人Id
	private String createName;//创建人名称
	private Date createDate;//创建时间
	private String updateBy;//修改人Id
	private String updateName;//修改人名称
	private Date updateDate;//修改时间

	public Long getChangeOffOid() {
		return changeOffOid;
	}

	public void setChangeOffOid(Long changeOffOid) {
		this.changeOffOid = changeOffOid;
	}

	public Long getBizPersonOid() {
		return bizPersonOid;
	}

	public void setBizPersonOid(Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}

	public String getChangeOffType() {
		return changeOffType;
	}

	public void setChangeOffType(String changeOffType) {
		this.changeOffType = changeOffType;
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

	public Float getChangeOffDays() {
		return changeOffDays;
	}

	public void setChangeOffDays(Float changeOffDays) {
		this.changeOffDays = changeOffDays;
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

	public Long getBaseChangeOffOid() {
		return baseChangeOffOid;
	}

	public void setBaseChangeOffOid(Long baseChangeOffOid) {
		this.baseChangeOffOid = baseChangeOffOid;
	}
}