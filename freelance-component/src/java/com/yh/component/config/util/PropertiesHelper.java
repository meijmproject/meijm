package com.yh.component.config.util;

import com.yh.component.config.facade.PropertiesFacade;
import jade.workflow.utils.SpringBeanUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yh.component.config.dto.CfgPropertiesDTO;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.ConfigUtil;

/**
 * 系统配置项工具类
 * @author wangx
 * @created 2016-12-21
 * @version 1.0
 */
public class PropertiesHelper {

	private static PropertiesFacade propertiesFacade = (PropertiesFacade)SpringBeanUtil.getBean("propertiesFacade");
	
	/**
	 * 保存时效起始时间
	 */
	private static HashMap<String, Long> periods = new HashMap<String, Long>();
	
	/**
	 * 缓存代码
	 */
	private static Map<String, Map<String, CfgPropertiesDTO>> parameters = new HashMap<String, Map<String, CfgPropertiesDTO>>();
	
	/**
	 * 取得设定的时效
	 */
	private static final Long effperiod = new Long(ConfigUtil.getProperty("parameter.active.period") == null ? "0" : ConfigUtil.getProperty("parameter.active.period"));
	
	/**
	 * List转换成Map
	 * @param cfgPropertiesList
	 * @return
	 */
	private static Map<String, CfgPropertiesDTO> cfgListToMap(List<CfgPropertiesDTO> cfgPropertiesList) {
		if (cfgPropertiesList == null || cfgPropertiesList.size() <= 0)
			return null;
		Map<String, CfgPropertiesDTO> cfgPropertiesMap = new HashMap<String, CfgPropertiesDTO>();
		for (CfgPropertiesDTO dto : cfgPropertiesList) {
			cfgPropertiesMap.put(dto.getCfgPropertiesCode(), dto);
		}
		return cfgPropertiesMap;
	}

	/**
	 * 从数据库 加载/重新加载 配置信息到缓存（重新加载缓存）
	 * @param cfgPropertiesType
	 * @throws ServiceException
	 */
	private static void setEffect(String cfgPropertiesType) throws ServiceException {
		List<CfgPropertiesDTO> cfgPropertiesDTOList = findCfgPropertiesListByType(cfgPropertiesType);
		if (cfgPropertiesDTOList != null) {
			parameters.put(cfgPropertiesType, cfgListToMap(cfgPropertiesDTOList));
			periods.put(cfgPropertiesType,  Long.valueOf((System.currentTimeMillis())));
		}
	}
	
	/**
	 * 通过所属模块代码查询该模块下的所有配置信息
	 * @param cfgPropertiesType
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<CfgPropertiesDTO> findCfgPropertiesListByType(String cfgPropertiesType) throws ServiceException {
		return propertiesFacade.findCfgPropertiesListByType(cfgPropertiesType);
	}

	/**
	 * 判断时效性
	 * @param cfgPropertiesType
	 * @return
	 */
	private static boolean isEffect(String cfgPropertiesType) {
		if (effperiod == 0)
			return true;
		if (!periods.containsKey(cfgPropertiesType))
			return false;
		return System.currentTimeMillis() - (periods.get(cfgPropertiesType)).longValue() < effperiod;
	}
	
	/**
	 * 加载指定模块的配置信息到缓存
	 * @param cfgPropertiesType
	 * @throws ServiceException
	 */
	private static void loadCfg(String cfgPropertiesType) throws ServiceException {
		List<CfgPropertiesDTO> cfgPropertiesDTOList = findCfgPropertiesListByType(cfgPropertiesType);
		if (cfgPropertiesDTOList != null) {
			parameters.put(cfgPropertiesType, cfgListToMap(cfgPropertiesDTOList));
		}
	}
	
	/**
	 * 通过所属模块代码和配置项的KEY值查询配置信息（已缓存）
	 * @param cfgPropertiesType
	 * @param cfgPropertiesCode
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static CfgPropertiesDTO findCfgPropertiesByTypeAndCode(String cfgPropertiesType,String cfgPropertiesCode) throws ServiceException {
		if (StringUtils.isEmpty(cfgPropertiesCode) || "null".equalsIgnoreCase(cfgPropertiesCode)) {
			return null;
		}
		if (!isEffect(cfgPropertiesType)) {
			setEffect(cfgPropertiesType);
		}
		if (!parameters.containsKey(cfgPropertiesType)) {
			loadCfg(cfgPropertiesType);
		}
		if (parameters.containsKey(cfgPropertiesType)) {
			Map<String, CfgPropertiesDTO> mapList = parameters.get(cfgPropertiesType);
			if (mapList != null && mapList.containsKey(cfgPropertiesCode)) {
				return mapList.get(cfgPropertiesCode);
			}
		}
		return null;
	}
	
	/**
	 * 通过所属模块代码、配置项的KEY值查询配置项的value值（已缓存）
	 * @param cfgPropertiesType
	 * @param cfgPropertiesCode
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static String findCfgPropertiesValueByTypeAndCode(String cfgPropertiesType,String cfgPropertiesCode) throws ServiceException {
		if (StringUtils.isEmpty(cfgPropertiesCode) || "null".equalsIgnoreCase(cfgPropertiesCode)) {
			return null;
		}
		if (!isEffect(cfgPropertiesType)) {
			setEffect(cfgPropertiesType);
		}
		if (!parameters.containsKey(cfgPropertiesType)) {
			loadCfg(cfgPropertiesType);
		}
		if (parameters.containsKey(cfgPropertiesType)) {
			Map<String, CfgPropertiesDTO> mapList = parameters.get(cfgPropertiesType);
			if (mapList != null && mapList.containsKey(cfgPropertiesCode)) {
				return mapList.get(cfgPropertiesCode).getCfgPropertiesValue();
			}
		}
		return null;
	}
}
