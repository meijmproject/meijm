package com.yh.hr.component.yngw.bo;

import com.yh.platform.core.bo.BaseBo;

/**
 * 院内岗位管理BO
 * @author liuhw
 * 2016-8-24
 */
public class GwYnInfo extends BaseBo {

	private static final long serialVersionUID = 8359312883387612049L;
	
	/**
	 * 主键id
	 */
	private java.lang.Long positionOid;
	/**
	 * 岗位名称
	 */
	private java.lang.String positionName;
	/**
	 * 上级岗位名称
	 */
	private java.lang.String parentPositionName;
	/**
	 * 上级岗位名称Oid
	 */
	private java.lang.Long parentPositionOid;
	/**
	 * 工作类别
	 */
	private java.lang.String hisWorkType;
	/**
	 * 岗位类别
	 */
	private java.lang.String hisPositionType;
	/**
	 * 岗位职责
	 */
	private java.lang.String hisPositionObligation;
	/**
	 * 岗位任职条件
	 */
	private java.lang.String hisPositionQualifications;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 创建人ID
	 */
	private java.lang.String createdByCode;
	/**
	 * 创建人姓名
	 */
	private java.lang.String createdByName;
	/**
	 * 创建时间
	 */
	private java.util.Date createdDate;
	/**
	 * 修改人ID
	 */
	private java.lang.String updatedByCode;
	/**
	 * 修改人姓名
	 */
	private java.lang.String updatedByName;
	/**
	 * 修改时间
	 */
	private java.util.Date updatedDate;
	
	
	public java.lang.Long getPositionOid() {
		return positionOid;
	}
	public void setPositionOid(java.lang.Long positionOid) {
		this.positionOid = positionOid;
	}
	
	public java.lang.String getPositionName() {
		return positionName;
	}
	public void setPositionName(java.lang.String positionName) {
		this.positionName = positionName;
	}
	public java.lang.String getParentPositionName() {
		return parentPositionName;
	}
	public void setParentPositionName(java.lang.String parentPositionName) {
		this.parentPositionName = parentPositionName;
	}
	public java.lang.Long getParentPositionOid() {
		return parentPositionOid;
	}
	public void setParentPositionOid(java.lang.Long parentPositionOid) {
		this.parentPositionOid = parentPositionOid;
	}
	public java.lang.String getHisWorkType() {
		return hisWorkType;
	}
	public void setHisWorkType(java.lang.String hisWorkType) {
		this.hisWorkType = hisWorkType;
	}
	public java.lang.String getHisPositionType() {
		return hisPositionType;
	}
	public void setHisPositionType(java.lang.String hisPositionType) {
		this.hisPositionType = hisPositionType;
	}
	public java.lang.String getHisPositionObligation() {
		return hisPositionObligation;
	}
	public void setHisPositionObligation(java.lang.String hisPositionObligation) {
		this.hisPositionObligation = hisPositionObligation;
	}
	public java.lang.String getHisPositionQualifications() {
		return hisPositionQualifications;
	}
	public void setHisPositionQualifications(java.lang.String hisPositionQualifications) {
		this.hisPositionQualifications = hisPositionQualifications;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
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
