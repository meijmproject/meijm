package com.yh.hr.orghc.unit.unitmanger.dto;

/** 
 *@description		科室操作提示 - DTO
 *@author           chenjl
 *@created          2017-04-18
 *@version          1.0
 */
public class OrgResponseDTO
{
	/**
	 * Fields
	 */
	private String		applyName;		// 申请名称（如：方案名称、人员姓名等）
	private String		isSuccess;		// 是否成功（成功：Y；失败：N）
	private String		resultDesc;		// 结果说明（注：成功或者失败的说明）
	
	/**
	 * Constructors
	 */
	public OrgResponseDTO() {}
	
	/**
	 * Get、Set
	 */
	public String getApplyName() 
	{
		return applyName;
	}
	public void setApplyName(String applyName) 
	{
		this.applyName = applyName;
	}
	public String getIsSuccess() 
	{
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) 
	{
		this.isSuccess = isSuccess;
	}
	public String getResultDesc() 
	{
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) 
	{
		this.resultDesc = resultDesc;
	}	
}