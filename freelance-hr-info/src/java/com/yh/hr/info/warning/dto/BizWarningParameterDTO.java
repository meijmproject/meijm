package com.yh.hr.info.warning.dto;


/**
 * 预警基础信息查询-DTO
 * @author	liuhw
 * @created 204-03-31
 */
public class BizWarningParameterDTO implements ActionParameter
{
	// fields
	protected int 			warningDays; 		// 预警天数
	protected String 		name; 				// 姓名
	
	// constructor
	public BizWarningParameterDTO(){}

	// get set
	public int getWarningDays() 
	{
		return warningDays;
	}
	public void setWarningDays(int warningDays) 
	{
		this.warningDays = warningDays;
	}

	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}	
}