/**
 * 
 */
package com.yh.hr.leader.validate.dto;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/10/31
 */

public class LdCashCheckDTO {
	
	private Long	unitOid;
	private String	unitName;
	private String	dutyAttribute;
	private String	dutyAttributeName;
	private String	dutyLevel;
	private String	dutyLevelName;
	private Integer	checkCount = 0;
	
	public Long getUnitOid() {
		return unitOid;
	}
	public void setUnitOid(Long unitOid) {
		this.unitOid = unitOid;
	}
	public String getDutyAttribute() {
		return dutyAttribute;
	}
	public void setDutyAttribute(String dutyAttribute) {
		this.dutyAttribute = dutyAttribute;
	}
	public String getDutyLevel() {
		return dutyLevel;
	}
	public void setDutyLevel(String dutyLevel) {
		this.dutyLevel = dutyLevel;
	}
	public Integer getCheckCount() {
		return checkCount;
	}
	public void setCheckCount(Integer checkCount) {
		this.checkCount = checkCount;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getDutyAttributeName() {
		return dutyAttributeName;
	}
	public void setDutyAttributeName(String dutyAttributeName) {
		this.dutyAttributeName = dutyAttributeName;
	}
	public String getDutyLevelName() {
		return dutyLevelName;
	}
	public void setDutyLevelName(String dutyLevelName) {
		this.dutyLevelName = dutyLevelName;
	}
}
