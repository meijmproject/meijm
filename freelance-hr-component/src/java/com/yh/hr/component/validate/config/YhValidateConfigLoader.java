
package com.yh.hr.component.validate.config;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.yh.component.validate.config.YhValidateClassConfig;
import com.yh.component.validate.parse.YhValidateConfigParse;
import com.yh.component.validate.parse.impl.YhValidateClassConfigParse;
import com.yh.component.validate.service.impl.YhValidateServiceImpl;
import com.yh.hr.component.validate.parse.impl.ValidateItemNodeConfigParse;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 自动加载验证配置（Spring容器初始化完成后触发）
 * @author	zhangqp
 * @version	1.0,	16/10/11
 */
public class YhValidateConfigLoader implements ApplicationListener {
	private static Log log = LogFactory.getLog(YhValidateConfigLoader.class);
	
	public void onApplicationEvent(ApplicationEvent e) {
		
		if (e instanceof ContextRefreshedEvent 
				&& ((ContextRefreshedEvent) e).getApplicationContext().getParent() == null) {
			
			YhValidateServiceImpl yhValidateService = (YhValidateServiceImpl)SpringBeanUtil.getBean("yhValidateService");
			
			YhValidateClassConfigParse yhValidateClassConfigParse = getYhValidateClassConfigParse();
			ValidateItemNodeConfigParse validateItemNodeConfigParse = getValidateItemNodeConfigParse();
			
			List<YhValidateConfigParse> list = new ArrayList<YhValidateConfigParse>();
			
			if (yhValidateClassConfigParse != null) {
				list.add(yhValidateClassConfigParse);
			}
			
			if (validateItemNodeConfigParse != null) {
				list.add(validateItemNodeConfigParse);
			}
			
			if (list.size() > 0) {
				if (yhValidateService.getYhValidateConfigParseLs() == null) {
					yhValidateService.setYhValidateConfigParseLs(list);
				} else {
					List<YhValidateConfigParse> yhValidateConfigParseLs = yhValidateService.getYhValidateConfigParseLs();
					
					if (yhValidateConfigParseLs.indexOf(yhValidateClassConfigParse) != -1) {
						yhValidateConfigParseLs.add(yhValidateClassConfigParse);
					}
					
					if (yhValidateConfigParseLs.indexOf(validateItemNodeConfigParse) != -1) {
						yhValidateConfigParseLs.add(validateItemNodeConfigParse);
					}
				}
			}
		}
	}

	/**
	 * 自动加载yhValidateClassConfig配置项
	 * @return
	 */
	private YhValidateClassConfigParse getYhValidateClassConfigParse(){
		
		YhValidateClassConfigParse yhValidateClassConfigParse = null;
		
		if (SpringBeanUtil.getContext().containsBean("yhValidateClassConfigParse")) {
			
			yhValidateClassConfigParse = (YhValidateClassConfigParse)SpringBeanUtil.getBean("yhValidateClassConfigParse");
			
			String[] valdateConfigBeans = SpringBeanUtil.getContext().getBeanNamesForType(YhValidateClassConfig.class);
			
			if (!ArrayUtils.isEmpty(valdateConfigBeans)) {
				List<YhValidateClassConfig> configBeans = yhValidateClassConfigParse.getYhBizCheckClassConfigs();
				
				if (configBeans == null) {
					
					configBeans = new ArrayList<YhValidateClassConfig>(valdateConfigBeans.length);
					
					yhValidateClassConfigParse.setYhBizCheckClassConfigs(configBeans);
				}
				
				YhValidateClassConfig instance = null;
				for (String beans: valdateConfigBeans) {
					instance = (YhValidateClassConfig)SpringBeanUtil.getBean(beans);
					
					if (configBeans.indexOf(instance) != -1) {
						log.info("[JadeValidateClassConfig]：已丢弃重复的实例：" + beans);
						continue;
					}
					
					configBeans.add(instance);
					
					log.info("[JadeValidateClassConfig]：" + beans);
				}
			}
			
		}
		
		return yhValidateClassConfigParse;
	}
	
	/**
	 * 自动加载ValidateItemNodeConfig配置项
	 * @return
	 */
	private ValidateItemNodeConfigParse getValidateItemNodeConfigParse(){
		
		ValidateItemNodeConfigParse validateItemNodeConfigParse = null;
		
		if (SpringBeanUtil.getContext().containsBean("validateItemNodeConfigParse")) {
			
			validateItemNodeConfigParse = (ValidateItemNodeConfigParse)SpringBeanUtil.getBean("validateItemNodeConfigParse");
			
			String[] valdateConfigBeans = SpringBeanUtil.getContext().getBeanNamesForType(ValidateItemNodeConfig.class);
			
			if (!ArrayUtils.isEmpty(valdateConfigBeans)) {
				List<ValidateItemNodeConfig> configBeans = validateItemNodeConfigParse.getJadeValidateConfig();
				
				if (configBeans == null) {
					
					configBeans = new ArrayList<ValidateItemNodeConfig>(valdateConfigBeans.length);
					
					validateItemNodeConfigParse.setJadeValidateConfig(configBeans);
				}
				
				ValidateItemNodeConfig instance = null;
				for (String beans: valdateConfigBeans) {
					instance = (ValidateItemNodeConfig)SpringBeanUtil.getBean(beans);
					
					if (configBeans.indexOf(instance) != -1) {
						log.info("[ValidateItemNodeConfig]：已丢弃重复的实例：" + beans);
						continue;
					}
					
					configBeans.add(instance);
					
					log.info("[ValidateItemNodeConfig]：" + beans);
				}
			}
		}
		
		return validateItemNodeConfigParse;
	}
}
