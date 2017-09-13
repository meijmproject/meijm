package com.yh.hr.info.warning.dto;

/**
 * 预警信息-DTO
 * @author	Xing.Liu
 * @created 2011-03-09
 */
public class BizWarningInfoDTO 
{
	// fields
	private Long        bizWarningInfoOid;
	private String 		itemCode; 			// 预警事项代码
	private String 		itemName; 			// 预警事项名称
	private int 		warningDays; 		// 预警天数
    private String 		warningUrl;  		// 预警操作路径
	private int 		itemCount; 			// 事项预警总数
	private String      warningBean;		//预警执行BEAN
	private String      systemId;
	
	// constructor
	public BizWarningInfoDTO(){}

	// get set
	
	public String getItemCode() 
	{
		return itemCode;
	}
	public Long getBizWarningInfoOid() {
		return bizWarningInfoOid;
	}

	public void setBizWarningInfoOid(Long bizWarningInfoOid) {
		this.bizWarningInfoOid = bizWarningInfoOid;
	}

	public void setItemCode(String itemCode) 
	{
		this.itemCode = itemCode;
	}

	public String getItemName() 
	{
		return itemName;
	}
	public void setItemName(String itemName) 
	{
		this.itemName = itemName;
	}

	public int getWarningDays() 
	{
		return warningDays;
	}
	public void setWarningDays(int warningDays) 
	{
		this.warningDays = warningDays;
	}

	public String getWarningUrl() 
	{
		return warningUrl;
	}
	public void setWarningUrl(String warningUrl) 
	{
		this.warningUrl = warningUrl;
	}

	public int getItemCount() 
	{
		return itemCount;
	}
	public void setItemCount(int itemCount) 
	{
		this.itemCount = itemCount;
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
}
