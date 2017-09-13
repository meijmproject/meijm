package com.yh.component.config.queryhelper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.config.bo.CfgProperties;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 系统配置项查询类
 * @author wangx
 * @created 2016-12-21
 * @version 1.0
 */
public class PropertiesQueryHelper {

	/**
	 * 查询所有的配置信息
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<CfgProperties> listAll() throws DataAccessFailureException {
		return DaoUtil.find("from CfgProperties cp where cp.isActive='"+Constant.YES+"'");
	}
	
	/**
	 * 通过所属模块代码查询该模块下的所有配置信息
	 * @param cfgPropertiesType
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<CfgProperties> findCfgPropertiesListByType(String cfgPropertiesType) throws DataAccessFailureException {
		StringBuffer hql = new StringBuffer();
		hql.append("from CfgProperties cp where cp.isActive='"+Constant.YES+"' and cp.cfgPropertiesType='"+cfgPropertiesType+"'");
		hql.append(" order by cp.cfgPropertiesCode asc");
		return DaoUtil.find(hql.toString());
	}
	
	/**
	 * 通过所属模块代码和配置项的KEY值查询配置信息
	 * @param cfgPropertiesType
	 * @param cfgPropertiesCode
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static CfgProperties findCfgPropertiesByTypeAndCode(String cfgPropertiesType,String cfgPropertiesCode) throws DataAccessFailureException {
		StringBuffer hql = new StringBuffer();
		hql.append("from CfgProperties cp where cp.isActive='"+Constant.YES+"' and cp.cfgPropertiesType='"+cfgPropertiesType+"' and cp.cfgPropertiesCode='"+cfgPropertiesCode+"'");
		List<CfgProperties> list = DaoUtil.find(hql.toString());
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 通过所属模块代码、配置项的KEY值和是否有效查询配置信息
	 * @param cfgPropertiesType
	 * @param cfgPropertiesCode
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static CfgProperties findCfgPropertiesByTypeAndCode(String cfgPropertiesType,String cfgPropertiesCode,String isActive) throws DataAccessFailureException {
		StringBuffer hql = new StringBuffer();
		hql.append("from CfgProperties cp where cp.isActive='"+isActive+"' and cp.cfgPropertiesType='"+cfgPropertiesType+"' and cp.cfgPropertiesCode='"+cfgPropertiesCode+"'");
		List<CfgProperties> list = DaoUtil.find(hql.toString());
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
}
