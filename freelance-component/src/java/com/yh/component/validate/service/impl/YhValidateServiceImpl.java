package com.yh.component.validate.service.impl;




import java.util.List;

import com.yh.component.validate.BaseValidateService;
import com.yh.component.validate.parse.YhValidateConfigParse;
import com.yh.component.validate.service.YhValidateService;
import com.yh.platform.core.exception.ServiceException;



/**
 *@description  业务检查实现
 *
 *@author      chencr	
 *@created     2013-04-11
 *@version     1.0
 *
 */
public class YhValidateServiceImpl implements YhValidateService 
{	
		
	private List<YhValidateConfigParse> yhValidateConfigParseLs;

	 
	public List<YhValidateConfigParse> getYhValidateConfigParseLs() {
		return yhValidateConfigParseLs;
	}

	public void setYhValidateConfigParseLs(
			List<YhValidateConfigParse> yhValidateConfigParseLs) {
		this.yhValidateConfigParseLs = yhValidateConfigParseLs;
	}
	/*
	 * (non-Javadoc)
	 * @see gov.gdpos.basecore.validate.service.JadeBizCheckService#check(java.lang.String, java.lang.String)
	 */
	public void check(String methodName, String className) throws ServiceException
	{
		
		if(null != yhValidateConfigParseLs && yhValidateConfigParseLs.size() > 0)
		{
			for(YhValidateConfigParse yhValidateConfigParse : yhValidateConfigParseLs)
			{
				List<BaseValidateService> checkLs = yhValidateConfigParse.listValidateService(methodName, className);
				//循环检查
				if(checkLs !=null && checkLs.size()>0)
				{
					for(BaseValidateService baseValidateService : checkLs)
					{
						baseValidateService.validate();
					}
				}
			}
			
		}
	}

}
