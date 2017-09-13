package com.yh.component.config.service.impl;

import java.util.List;

import com.yh.component.config.service.PropertiesService;
import org.springframework.dao.DataAccessException;

import com.yh.component.config.bo.CfgProperties;
import com.yh.component.config.dto.CfgPropertiesDTO;
import com.yh.component.config.queryhelper.PropertiesQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 系统配置项service实现类
 * @author wangx
 * @created 2016-12-21
 * @version 1.0
 */
public class PropertiesServiceImpl implements PropertiesService {

	/**
	 * 通过主键获取配置项信息
	 * @param cfgPropertiesOid
	 * @return
	 * @throws ServiceException
	 */
	public CfgPropertiesDTO get(Long cfgPropertiesOid) throws ServiceException {
		try
		{
			return BeanHelper.copyProperties(DaoUtil.get(CfgProperties.class, cfgPropertiesOid), CfgPropertiesDTO.class);
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException("get CfgProperties by oid faild", e);
		}
	}

	/**
	 * 查询所有的配置信息
	 * @return
	 * @throws DataAccessFailureException
	 */
	public List<CfgPropertiesDTO> listAllCfgProperties() throws ServiceException {
		try
		{
			return BeanHelper.copyProperties(PropertiesQueryHelper.listAll(), CfgPropertiesDTO.class);
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException(" find list all CfgProperties error !", e);
		}
	}

	/**
	 * 通过所属模块代码查询该模块下的所有配置信息
	 * @param cfgPropertiesType
	 * @return
	 * @throws DataAccessFailureException
	 */
	public List<CfgPropertiesDTO> findCfgPropertiesListByType(
			String cfgPropertiesType) throws ServiceException {
		try
		{
			return BeanHelper.copyProperties(PropertiesQueryHelper.findCfgPropertiesListByType(cfgPropertiesType), CfgPropertiesDTO.class);
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException(" findCfgPropertiesListByType error !", e);
		}
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
		try
		{
			return BeanHelper.copyProperties(PropertiesQueryHelper.findCfgPropertiesByTypeAndCode(cfgPropertiesType, cfgPropertiesCode), CfgPropertiesDTO.class);
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException(" findCfgPropertiesByTypeAndCode error !", e);
		}
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
		try
		{
			return BeanHelper.copyProperties(PropertiesQueryHelper.findCfgPropertiesByTypeAndCode(cfgPropertiesType, cfgPropertiesCode, isActive), CfgPropertiesDTO.class);
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException(" findCfgPropertiesByTypeAndCode error !", e);
		}
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
		try
		{
			CfgProperties cfgProperties = PropertiesQueryHelper.findCfgPropertiesByTypeAndCode(cfgPropertiesType, cfgPropertiesCode);
			if(cfgProperties!=null) {
				return cfgProperties.getCfgPropertiesValue();
			}
			return null;
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException(" findCfgPropertiesValueByTypeAndCode error !", e);
		}
	}

}
