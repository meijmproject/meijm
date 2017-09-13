package com.yh.component.validate.facade;

import java.util.List;

import com.yh.component.validate.service.YhValidateService;
import com.yh.platform.core.exception.ServiceException;

/**
 *@description规则检查facade
 *
 *@author      chencr	
 *@created     2013-04-11
 *@version     1.0
 *
 */
public class YhValidateFacade 
{
	private List<YhValidateService> yhValidateService;
	
	
	public void setYhValidateService(List<YhValidateService> yhValidateService) {
		this.yhValidateService = yhValidateService;
	}


	/**
	 *	检查
	 *	@param	methodName
	 *	@param 	className
	 *	@throws ServiceException
	 */
	public void check(String methodName, String className) throws ServiceException
	{
		if(null != yhValidateService && yhValidateService.size() > 0)
		{
			for(YhValidateService jadeValidateSService : yhValidateService)
			{
				jadeValidateSService.check(methodName, className);
			}
		}
	}
}
