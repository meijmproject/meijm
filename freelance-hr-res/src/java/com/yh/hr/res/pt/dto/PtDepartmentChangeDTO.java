package com.yh.hr.res.pt.dto;

import java.util.Date;

/**
 * 人员科室变动业务信息DTO
 * @author wangx
 * @date 2017-06-26
 */
public class PtDepartmentChangeDTO {

	/**
	 * 主键OID
	 */
	private Long ptDepartmentChangeOid;
	
	/**
     * 业务人员OID
     **/
	private Long bizPersonOid;
	
	/**
	 * 人员变动前科室OID
	 */
	private Long departmentFromOid;
	
	/**
	 * 人员变动后科室OID
	 */
	private Long departmentToOid;
	
	/**
	 * 进入科室时间
	 */
	private Date entryDepartmentDate;
	
	/**
	 * 基础表OID
	 */
	private Long pbDepartmentChangeOid;
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
	
	public Long getPtDepartmentChangeOid() {
		return ptDepartmentChangeOid;
	}
	public void setPtDepartmentChangeOid(Long ptDepartmentChangeOid) {
		this.ptDepartmentChangeOid = ptDepartmentChangeOid;
	}
	public Long getBizPersonOid() {
		return bizPersonOid;
	}
	public void setBizPersonOid(Long bizPersonOid) {
		this.bizPersonOid = bizPersonOid;
	}
	public Long getDepartmentFromOid() {
		return departmentFromOid;
	}
	public void setDepartmentFromOid(Long departmentFromOid) {
		this.departmentFromOid = departmentFromOid;
	}
	public Long getDepartmentToOid() {
		return departmentToOid;
	}
	public void setDepartmentToOid(Long departmentToOid) {
		this.departmentToOid = departmentToOid;
	}
	public Date getEntryDepartmentDate() {
		return entryDepartmentDate;
	}
	public void setEntryDepartmentDate(Date entryDepartmentDate) {
		this.entryDepartmentDate = entryDepartmentDate;
	}
	public Long getPbDepartmentChangeOid() {
		return pbDepartmentChangeOid;
	}
	public void setPbDepartmentChangeOid(Long pbDepartmentChangeOid) {
		this.pbDepartmentChangeOid = pbDepartmentChangeOid;
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
