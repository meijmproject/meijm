/**
 * @desctiption   This file is generated by  code generation tool version 0.2 ^_^
 * @Created   2017-02-13 
 */
package com.yh.hr.res.pb.dto;

import com.yh.platform.core.util.DateUtil;


public class PbPunishmentInfoDTO {

	private java.lang.Long punishmentOid;        //主键
	private java.lang.Long personOid;        //PersonOid
	private java.lang.String punishmentName;        //处分名称
	private java.lang.String punishmentReason;        //处分原因
	private java.lang.Long punishmentMonth;        //处分期限（月）
	private java.util.Date punishmentEndDate;        //处分截止日期
	private java.lang.String punishmentEndDateStr;        //处分截止日期
	private java.lang.String punishmentApprovalUnit;        //处分批准机关名称
	private java.util.Date punishmentDate;        //处分批准日期
	private java.lang.String punishmentDateStr;        //处分批准日期
	private java.lang.String isCancalPunishment;        //处分是否撤销标识
	private java.lang.String remark;        //备注
	private java.lang.String createBy;        //创建人ID
	private java.lang.String createName;        //创建人名称
	private java.util.Date createDate;        //创建时间
	private java.lang.String updateBy;        //修改人ID
	private java.lang.String updateName;        //修改人名称
	private java.util.Date updateDate;        //修改时间
	public java.lang.Long getPunishmentOid() {
		return punishmentOid;
	}
	public void setPunishmentOid(java.lang.Long punishmentOid) {
		this.punishmentOid = punishmentOid;
	}
	public java.lang.Long getPersonOid() {
		return personOid;
	}
	public void setPersonOid(java.lang.Long personOid) {
		this.personOid = personOid;
	}
	public java.lang.String getPunishmentName() {
		return punishmentName;
	}
	public void setPunishmentName(java.lang.String punishmentName) {
		this.punishmentName = punishmentName;
	}
	public java.lang.String getPunishmentReason() {
		return punishmentReason;
	}
	public void setPunishmentReason(java.lang.String punishmentReason) {
		this.punishmentReason = punishmentReason;
	}
	public java.lang.Long getPunishmentMonth() {
		return punishmentMonth;
	}
	public void setPunishmentMonth(java.lang.Long punishmentMonth) {
		this.punishmentMonth = punishmentMonth;
	}
	public java.util.Date getPunishmentEndDate() {
		return punishmentEndDate;
	}
	public void setPunishmentEndDate(java.util.Date punishmentEndDate) {
		this.punishmentEndDate = punishmentEndDate;
		if(punishmentEndDate!=null){
			this.punishmentEndDateStr = DateUtil.formatDate(punishmentEndDate);
		}
	}
	public java.lang.String getPunishmentEndDateStr() {
		return punishmentEndDateStr;
	}
	public void setPunishmentEndDateStr(java.lang.String punishmentEndDateStr) {
		this.punishmentEndDateStr = punishmentEndDateStr;
		if(punishmentEndDateStr!=null){
			this.punishmentEndDate = DateUtil.parseDate(punishmentEndDateStr);
		}
	}
	public java.lang.String getPunishmentApprovalUnit() {
		return punishmentApprovalUnit;
	}
	public void setPunishmentApprovalUnit(java.lang.String punishmentApprovalUnit) {
		this.punishmentApprovalUnit = punishmentApprovalUnit;
	}
	public java.util.Date getPunishmentDate() {
		return punishmentDate;
	}
	public void setPunishmentDate(java.util.Date punishmentDate) {
		this.punishmentDate = punishmentDate;
		if(punishmentDate!=null){
			this.punishmentDateStr = DateUtil.formatDate(punishmentDate);
		}
	}
	public java.lang.String getPunishmentDateStr() {
		return punishmentDateStr;
	}
	public void setPunishmentDateStr(java.lang.String punishmentDateStr) {
		this.punishmentDateStr = punishmentDateStr;
		if(punishmentDateStr!=null){
			this.punishmentDate = DateUtil.parseDate(punishmentDateStr);
		}
	}
	public java.lang.String getIsCancalPunishment() {
		return isCancalPunishment;
	}
	public void setIsCancalPunishment(java.lang.String isCancalPunishment) {
		this.isCancalPunishment = isCancalPunishment;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public java.lang.String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}
	public java.lang.String getCreateName() {
		return createName;
	}
	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.lang.String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}
	public java.lang.String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}
	public java.util.Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	
}