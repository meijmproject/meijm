package com.yh.hr.res.im.service;

import java.util.List;

import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 采集项映射操作service接口
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public interface ImCollectTemplateService {
	
	/**
	 * 获取所有的采集项映射模板
	 * @return
	 * @throws ServiceException
	 */
	public List<ImCollectTemplateDTO> findAllCollTemplates() throws ServiceException;
	
	/**
	 * 获取所有有效的的采集项映射模板
	 * @return
	 * @throws ServiceException
	 */
	public List<ImCollectTemplateDTO> findEffectiveCollTemplates() throws ServiceException;
	
	/**
	 * 通过主键获取采集项映射模板信息
	 * @param templateOid
	 * @return
	 * @throws ServiceException
	 */
	public ImCollectTemplateDTO get(Long templateOid) throws ServiceException;
	
	/**
	 * 获取所有已被映射的采集项映射模板
	 * @return
	 * @throws ServiceExceptin
	 */
	public List<ImCollectTemplateDTO> findCollTemplateForMapped() throws ServiceException;
	
	/**
	 * 通过数据库字段代码获取模板信息
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public ImCollectTemplateDTO findCollTemplateByColumnCode(String databaseColumnCode) throws ServiceException;
	
	/**
	 * 通过数据库字段代码获取模板采集项名称
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public String findTemplateCollNameByColumnCode(String databaseColumnCode) throws ServiceException;

	/**
	 * 更新采集项映射模板信息
	 * @param imCollectTemplateDTO
	 * @throws ServiceException
	 */
	public void update(ImCollectTemplateDTO imCollectTemplateDTO) throws ServiceException;
	
	/**
	 * 通过导入采集项名称获取模板信息
	 * @param importCollName
	 * @return
	 * @throws ServiceException
	 */
	public ImCollectTemplateDTO findCollTemplateByImportCollName(String importCollName) throws ServiceException;
	
	/**
	 * 通过字段名获取字段名描述字段
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public String getColumnCodeNameByColumnCode(String databaseColumnCode) throws ServiceException;
	
}
