package com.yh.hr.component.gb.bo;

import java.util.Date;

import com.yh.platform.core.bo.BaseBo;

public class GBPlan extends BaseBo {
	private static final long serialVersionUID = 8415772645360700409L;
	
	private Long jhgGbPlanDetailOid;
	private Long unitOid;
	private String postType;
	private String postLevel;
	private Integer postLevelCount;
	private String createdByCode;
	private String createdByName;
	private Date createdDate;
	private String updatedByCode;
	private String updatedByName;
	private Date updatedDate;
	public Long getJhgGbPlanDetailOid() {
		return jhgGbPlanDetailOid;
	}
	public void setJhgGbPlanDetailOid(Long jhgGbPlanDetailOid) {
		this.jhgGbPlanDetailOid = jhgGbPlanDetailOid;
	}
	public Long getUnitOid() {
		return unitOid;
	}
	public void setUnitOid(Long unitOid) {
		this.unitOid = unitOid;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public String getPostLevel() {
		return postLevel;
	}
	public void setPostLevel(String postLevel) {
		this.postLevel = postLevel;
	}
	public Integer getPostLevelCount() {
		return postLevelCount;
	}
	public void setPostLevelCount(Integer postLevelCount) {
		this.postLevelCount = postLevelCount;
	}
	public String getCreatedByCode() {
		return createdByCode;
	}
	public void setCreatedByCode(String createdByCode) {
		this.createdByCode = createdByCode;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedByCode() {
		return updatedByCode;
	}
	public void setUpdatedByCode(String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}
	public String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
