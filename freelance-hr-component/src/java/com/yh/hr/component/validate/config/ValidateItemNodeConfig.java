package com.yh.hr.component.validate.config;

import java.util.List;
import java.util.Map;

import com.yh.component.validate.BaseValidateService;


/**
 *@description 检查配置，基于事项环节
 *
 *@author      chencr	
 *@created     2013-04-11
 *@version     1.0
 *
 */
public class ValidateItemNodeConfig 
{

	private Map<String, Map<String, Map<String, List<BaseValidateService>>>> validateConfigs;
	
	/**
	 * @param bizCheckConfigs the bizCheckConfigs to set
	 */
	public void setValidateConfigs(Map<String, Map<String, Map<String, List<BaseValidateService>>>> validateConfigs)
	{
		this.validateConfigs = validateConfigs;
	}
	
	/**
	 * @param key 
	 * @return the bizCheckConfigs
	 */
	public Map<String, Map<String, List<BaseValidateService>>> getValidateConfigs(String key)
	{
		return validateConfigs != null ? validateConfigs.get(key): null;
	}
	
}
