package com.yh.hr.res.pt.dto;

import java.util.Date;
/**
 * 工伤假业务信息DTO
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 *
 */
public class PtWorkInjuryVacationDTO {

	private Long vacationOid;	//主键ID
	private String isAffirmWorkInjury;	//是否认定工伤
	private String workInjuryAffirmUnit;//工伤认定单位
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
	public String getIsAffirmWorkInjury() {
		return isAffirmWorkInjury;
	}
	public void setIsAffirmWorkInjury(String isAffirmWorkInjury) {
		this.isAffirmWorkInjury = isAffirmWorkInjury;
	}
	public String getWorkInjuryAffirmUnit() {
		return workInjuryAffirmUnit;
	}
	public void setWorkInjuryAffirmUnit(String workInjuryAffirmUnit) {
		this.workInjuryAffirmUnit = workInjuryAffirmUnit;
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
