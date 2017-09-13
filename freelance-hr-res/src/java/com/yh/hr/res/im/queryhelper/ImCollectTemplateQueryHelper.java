package com.yh.hr.res.im.queryhelper;


import java.util.List;

import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class ImCollectTemplateQueryHelper {

	/**
	 * 获取所有的采集项映射模板
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImCollectTemplateDTO> findAllCollTemplates() throws ServiceException {
		String hql = "from ImCollectTemplate";
		return BeanHelper.copyProperties(DaoUtil.find(hql), ImCollectTemplateDTO.class);
	}

	/**
	 * 获取所有有效的的采集项映射模板
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImCollectTemplateDTO> findEffectiveCollTemplates() throws ServiceException {
		String hql = "from ImCollectTemplate ict where ict.effectiveFlag = '1'";
		return BeanHelper.copyProperties(DaoUtil.find(hql), ImCollectTemplateDTO.class);
	}

	/**
	 * 获取所有已被映射的采集项映射模板
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImCollectTemplateDTO> findCollTemplateForMapped() throws ServiceException {
		String hql = "from ImCollectTemplate ict where ict.effectiveFlag = '1' and ict.importCollName is not null and ict.importCollName <> ''";
		return BeanHelper.copyProperties(DaoUtil.find(hql), ImCollectTemplateDTO.class);
	}
	
	/**
	 * 获取所有已被映射的必填采集项映射模板
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImCollectTemplateDTO> findRequiredCollTemplateForMapped() throws ServiceException {
		String hql = "from ImCollectTemplate ict where ict.effectiveFlag = '1' and ict.importCollName is not null and ict.importCollName <> '' and ict.isRequired='1'";
		return BeanHelper.copyProperties(DaoUtil.find(hql), ImCollectTemplateDTO.class);
	}
	
	/**
	 * 获取所有已被映射的日期格式采集项映射模板
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImCollectTemplateDTO> findDateCheckCollTemplateForMapped() throws ServiceException {
		String hql = "from ImCollectTemplate ict where ict.effectiveFlag = '1' and ict.importCollName is not null and ict.importCollName <> '' and ict.databaseColumnType='2'";
		return BeanHelper.copyProperties(DaoUtil.find(hql), ImCollectTemplateDTO.class);
	}
	
	/**
	 * 获取所有已被映射的字典采集项映射模板
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImCollectTemplateDTO> findDickCollTemplateForMapped() throws ServiceException {
		String hql = "from ImCollectTemplate ict where ict.effectiveFlag = '1' and ict.importCollName is not null and ict.importCollName <> '' and ict.isDicColumn='1'";
		return BeanHelper.copyProperties(DaoUtil.find(hql), ImCollectTemplateDTO.class);
	}
	
	/**
	 * 通过数据库字段代码获取模板信息
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public static ImCollectTemplateDTO findCollTemplateByColumnCode(String databaseColumnCode) throws ServiceException {
		String hql = "from ImCollectTemplate ict where ict.effectiveFlag = '1' and ict.databaseColumnCode='" + databaseColumnCode + "'";
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(hql), ImCollectTemplateDTO.class);
	}

	/**
	 * 通过数据库字段代码获取模板采集项名称
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public static String findTemplateCollNameByColumnCode(String databaseColumnCode) throws ServiceException {
		String hql = "select ict.templateCollName from ImCollectTemplate ict where ict.effectiveFlag = '1' and ict.databaseColumnCode='" + databaseColumnCode + "'";
		return DaoUtil.uniqueResult(hql);
	}
	
	/**
	 * 通过导入采集项名称获取模板信息
	 * @param importCollName
	 * @return
	 * @throws ServiceException
	 */
	public static ImCollectTemplateDTO findCollTemplateByImportCollName(String importCollName) throws ServiceException {
		String hql = "from ImCollectTemplate ict where ict.effectiveFlag = '1' and ict.importCollName='" + importCollName + "'";
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(hql), ImCollectTemplateDTO.class);
	}
	
	/**
	 * 通过字段名获取字段名描述字段
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public static String getColumnCodeNameByColumnCode(String databaseColumnCode) throws ServiceException {
		String hql = "select ict.databaseColumnCodeName from ImCollectTemplate ict where ict.effectiveFlag = '1' and ict.databaseColumnCodeName is not null and ict.databaseColumnCode='" + databaseColumnCode + "'";
		return DaoUtil.uniqueResult(hql);
	}
}
