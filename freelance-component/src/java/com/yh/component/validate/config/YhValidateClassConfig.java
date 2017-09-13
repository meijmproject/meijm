package com.yh.component.validate.config;

import java.util.List;
import java.util.Map;

import com.yh.component.validate.BaseValidateService;

/**
 *  @description	通过类和方法的业务检查配置
 *	@author			chencr	
 *	@created		2013-07-29
 */
public class YhValidateClassConfig {

	private Map<String, Map<String, List<BaseValidateService>>> bizCheckClassConfigs;
	
	public void setBizCheckClassConfigs(Map<String, Map<String, List<BaseValidateService>>> bizCheckClassConfigs)
	{
		this.bizCheckClassConfigs = bizCheckClassConfigs;
	}
	
	public Map<String, List<BaseValidateService>> getBizCheckClassConfigs(String key)
	{
		return bizCheckClassConfigs != null ? bizCheckClassConfigs.get(key): null;
	}
}
