package com.yh.hr.worktop.dto;
/**
 * 单位信息
 * @author zhengdr
 *
 * 时间:2016-12-22上午09:57:47
 */
public class UnitDTO {

	private Long taskOid;
	private String unitName;//单位名称

	public Long getTaskOid() {
		return taskOid;
	}

	public void setTaskOid(Long taskOid) {
		this.taskOid = taskOid;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}
