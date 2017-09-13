package com.yh.hr.res.im.service;

import java.util.List;

import com.yh.hr.res.im.dto.ImDicTypeMappingDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 字典映射操作service接口
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public interface ImDicTypeMappingService {
	
	/**
	 * 通过主键获取字段字典类型映射信息
	 * @param dicTypeMappingOid
	 * @return
	 * @throws ServiceException
	 */
	public ImDicTypeMappingDTO get(Long dicTypeMappingOid) throws ServiceException;

	/**
	 * 获取所有有效的需映射的字典类型
	 * @return
	 * @throws ServiceException
	 */
	public List<ImDicTypeMappingDTO> findAllImDicTypeMappingDTO() throws ServiceException;
	
	/**
	 * 通过数据库字段代码获取该字段字典类型
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public ImDicTypeMappingDTO findImDicTypeMappingDTOByColumnCode(String databaseColumnCode) throws ServiceException;
	/**
	 * 通过dicTypeCode获取该字段字典
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public ImDicTypeMappingDTO findImDicTypeMappingDTOBydicTypeCode(String dicTypeCode) throws ServiceException;
}
