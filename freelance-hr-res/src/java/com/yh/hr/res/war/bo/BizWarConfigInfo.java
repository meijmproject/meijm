package com.yh.hr.res.war.bo;

import java.util.Date;

import com.yh.platform.core.bo.BaseBo;


/**
 * @description 业务预警
 * @author xuhj
 * @created 2011-3-7
 * @version 1.0
 * 
 */
public class BizWarConfigInfo extends BaseBo implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3118794367956348536L;
	
	private Long warConfigOid;
	/**
	 * 预警事项代码
	 */
	private String itemCode;
	/**
	 * 预警事项名称
	 */
	private String itemName;
	/**
	 * 预警天数
	 */
	private Integer warningDays;
	/**
	 * 预警执行BEAN
	 */
	private String warningBean;
	/**
	 * 预警操作路径
	 */
	private String warningUrl;
	/**
	 * Y:执行预警；N:不预警
	 */
	private String warningFlag;
	/**
	 * 是否是执行日终任务标识，空则是执行日终，否则不是
	 */
	/*private String schedulerFlag;*/
	/**
	 * 所属系统
	 */
	private String systemId;
	
	private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;
	
	public Long getWarConfigOid() {
		return warConfigOid;
	}
	public void setWarConfigOid(Long warConfigOid) {
		this.warConfigOid = warConfigOid;
	}
	/**
	 * @return the itemCode
	 */
	public String getItemCode()
	{
		return itemCode;
	}
	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode)
	{
		this.itemCode = itemCode;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName()
	{
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	/**
	 * @return the warningDays
	 */
	public Integer getWarningDays()
	{
		return warningDays;
	}
	/**
	 * @param warningDays the warningDays to set
	 */
	public void setWarningDays(Integer warningDays)
	{
		this.warningDays = warningDays;
	}
	/**
	 * @return the warningBean
	 */
	public String getWarningBean()
	{
		return warningBean;
	}
	/**
	 * @param warningBean the warningBean to set
	 */
	public void setWarningBean(String warningBean)
	{
		this.warningBean = warningBean;
	}
	/**
	 * @return the warningUrl
	 */
	public String getWarningUrl()
	{
		return warningUrl;
	}
	/**
	 * @param warningUrl the warningUrl to set
	 */
	public void setWarningUrl(String warningUrl)
	{
		this.warningUrl = warningUrl;
	}
	/**
	 * @return the warningFlag
	 */
	public String getWarningFlag()
	{
		return warningFlag;
	}
	/**
	 * @param warningFlag the warningFlag to set
	 */
	public void setWarningFlag(String warningFlag)
	{
		this.warningFlag = warningFlag;
	}
	/**
	 * @return the systemId
	 */
	public String getSystemId()
	{
		return systemId;
	}
	/**
	 * @param systemId the systemId to set
	 */
	public void setSystemId(String systemId)
	{
		this.systemId = systemId;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy()
	{
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate()
	{
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}
}
