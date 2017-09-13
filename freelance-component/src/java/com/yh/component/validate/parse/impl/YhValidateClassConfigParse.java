package com.yh.component.validate.parse.impl;


import java.util.List;
import java.util.Map;

import com.yh.component.validate.BaseValidateService;
import com.yh.component.validate.config.YhValidateClassConfig;
import com.yh.component.validate.parse.YhValidateConfigParse;
import com.yh.platform.core.exception.ServiceException;


/**
 * @author 基于类、方法名配置解析器
 *@author            zhangyx
 *@created           2013-8-30
 *@version           1.0
 *
 */
public class YhValidateClassConfigParse implements YhValidateConfigParse
{
	private List<YhValidateClassConfig> yhBizCheckClassConfigs;
	
	public List<YhValidateClassConfig> getYhBizCheckClassConfigs() {
		return yhBizCheckClassConfigs;
	}


	public void setYhBizCheckClassConfigs(
			List<YhValidateClassConfig> yhBizCheckClassConfigs) {
		this.yhBizCheckClassConfigs = yhBizCheckClassConfigs;
	}

	/**
	 * 拦截解析检查
	 * @param methodName
	 * @param className
	 * @return
	 * @throws ServiceException 
	 */
	public List<BaseValidateService> listValidateService(String methodName, String className) throws ServiceException
	{
		if(null != yhBizCheckClassConfigs && yhBizCheckClassConfigs.size() > 0)
		{
			//循环检查配置
			for(YhValidateClassConfig yhBizCheckClassConfig : yhBizCheckClassConfigs)
			{
				//根据类名获取map
				Map<String, List<BaseValidateService>> map = yhBizCheckClassConfig.getBizCheckClassConfigs(className);
				if(map != null && map.size() > 0)
				{
					//根据方法名获取map
					return map.get(methodName);
					
				}
			}
		}
		return null;
	}
}