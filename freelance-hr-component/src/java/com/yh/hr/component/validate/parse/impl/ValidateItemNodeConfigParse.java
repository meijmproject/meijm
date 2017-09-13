package com.yh.hr.component.validate.parse.impl;


import java.util.List;
import java.util.Map;

import com.yh.hr.component.validate.config.ValidateItemNodeConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.component.validate.parse.YhValidateConfigParse;
import com.yh.platform.core.exception.ServiceException;


/**
 *@description 基于事项、环节配置解析器
 *
 *@author      chencr	
 *@created     2013-04-11
 *@version     1.0
 *
 */
public class ValidateItemNodeConfigParse implements YhValidateConfigParse
{
	private static Log log = LogFactory.getLog(ValidateItemNodeConfigParse.class);
	private List<ValidateItemNodeConfig> yhValidateConfigLs;
	
	public void setJadeValidateConfig(List<ValidateItemNodeConfig> yhValidateConfigLs) 
	{
		this.yhValidateConfigLs = yhValidateConfigLs;
	}

	public List<ValidateItemNodeConfig> getJadeValidateConfig() {
		return yhValidateConfigLs;
	}

	/**
	 * 拦截解析并检查
	 * @param methodName
	 * @param className
	 * @return
	 * @throws ServiceException 
	 */
	public List<BaseValidateService> listValidateService(String methodName, String className) throws ServiceException
	{
		if(null != yhValidateConfigLs && yhValidateConfigLs.size() > 0)
		{
			//循环检查配置
			for(ValidateItemNodeConfig validateItemNodeConfig : yhValidateConfigLs)
			{
				//获取数据托盘的事项环节
				BaseHandleData data =  BaseHandler.get();
					if(null != data)
					{
						//根据事项和环节获取检查List
						String itemCode = (String)data.get("itemCode") ;
						String itemNodeCode = (String)data.get("itemNodeCode") ;
						log.debug("ItemCode****ItemNodeCode:" + itemCode + "****" + itemNodeCode);
						Map<String, Map<String, List<BaseValidateService>>> maps = validateItemNodeConfig.getValidateConfigs(itemCode+itemNodeCode);
						if(maps != null && maps.size() > 0)
						{
							//根据类名获取map
							Map<String, List<BaseValidateService>> map = maps.get(className);
							if(map != null && map.size() > 0)
							{
								//根据方法名获取map
								return map.get(methodName);
							}
						}
					}
				}
			}
		return null;
	}

}
