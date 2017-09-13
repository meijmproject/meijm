package com.yh.component.config.facade.impl;

import java.util.List;

import com.yh.component.config.dto.CfgPropertiesDTO;
import com.yh.component.config.facade.PropertiesFacade;
import com.yh.component.config.service.PropertiesService;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;

/**
 * 系统配置项facade实现类
 * @author wangx
 * @created 2016-12-21
 * @version 1.0
 */
public class PropertiesFacadeImpl implements PropertiesFacade {
	
	private PropertiesService propertiesService;

	public void setPropertiesService(PropertiesService propertiesService) {
		this.propertiesService = propertiesService;
	}

	/**
	 * 通过主键获取配置项信息
	 * @param cfgPropertiesOid
	 * @return
	 * @throws ServiceException
	 */
	public CfgPropertiesDTO get(Long cfgPropertiesOid) throws ServiceException {
		return propertiesService.get(cfgPropertiesOid);
	}

	/**
	 * 查询所有的配置信息
	 * @return
	 * @throws DataAccessFailureException
	 */
	public List<CfgPropertiesDTO> listAllCfgProperties()
			throws ServiceException {
		return propertiesService.listAllCfgProperties();
	}

	/**
	 * 通过所属模块代码查询该模块下的所有配置信息
	 * @param cfgPropertiesType
	 * @return
	 * @throws DataAccessFailureException
	 */
	public List<CfgPropertiesDTO> findCfgPropertiesListByType(
			String cfgPropertiesType) throws ServiceException {
		return propertiesService.findCfgPropertiesListByType(cfgPropertiesType);
	}

	/**
	 * 通过所属模块代码和配置项的KEY值查询配置信息
	 * @param cfgPropertiesType
	 * @param cfgPropertiesCode
	 * @return
	 * @throws DataAccessFailureException
	 */
	public CfgPropertiesDTO findCfgPropertiesByTypeAndCode(
			String cfgPropertiesType, String cfgPropertiesCode)
			throws ServiceException {
		return propertiesService.findCfgPropertiesByTypeAndCode(cfgPropertiesType, cfgPropertiesCode);
	}

	/**
	 * 通过所属模块代码、配置项的KEY值和是否有效查询配置信息
	 * @param cfgPropertiesType
	 * @param cfgPropertiesCode
	 * @param isActive
	 * @return
	 * @throws DataAccessFailureException
	 */
	public CfgPropertiesDTO findCfgPropertiesByTypeAndCode(
			String cfgPropertiesType, String cfgPropertiesCode, String isActive)
			throws ServiceException {
		return propertiesService.findCfgPropertiesByTypeAndCode(cfgPropertiesType, cfgPropertiesCode, isActive);
	}

	/**
	 * 通过所属模块代码、配置项的KEY值查询配置项的value值
	 * @param cfgPropertiesType
	 * @param cfgPropertiesCode
	 * @return
	 * @throws DataAccessFailureException
	 */
	public String findCfgPropertiesValueByTypeAndCode(String cfgPropertiesType,
			String cfgPropertiesCode) throws ServiceException {
		return propertiesService.findCfgPropertiesValueByTypeAndCode(cfgPropertiesType, cfgPropertiesCode);
	}

}
