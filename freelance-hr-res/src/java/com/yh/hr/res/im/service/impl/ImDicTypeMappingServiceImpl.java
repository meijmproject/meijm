package com.yh.hr.res.im.service.impl;

import java.util.List;

import com.yh.hr.res.im.bo.ImDicTypeMapping;
import com.yh.hr.res.im.dto.ImDicTypeMappingDTO;
import com.yh.hr.res.im.queryhelper.ImDicTypeMappingQueryHelper;
import com.yh.hr.res.im.service.ImDicTypeMappingService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 字典映射操作service实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class ImDicTypeMappingServiceImpl implements ImDicTypeMappingService {
	
	/**
	 * 通过主键获取字段字典类型映射信息
	 * @param dicTypeMappingOid
	 * @return
	 * @throws ServiceException
	 */
	public ImDicTypeMappingDTO get(Long dicTypeMappingOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(ImDicTypeMapping.class, dicTypeMappingOid), ImDicTypeMappingDTO.class);
	}

	/**
	 * 获取所有有效的需映射的字典类型
	 * @return
	 * @throws ServiceException
	 */
	public List<ImDicTypeMappingDTO> findAllImDicTypeMappingDTO() throws ServiceException {
		return ImDicTypeMappingQueryHelper.findAllImDicTypeMappingDTO();
	}
	
	/**
	 * 通过数据库字段代码获取该字段字典类型
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public ImDicTypeMappingDTO findImDicTypeMappingDTOByColumnCode(String databaseColumnCode) throws ServiceException {
		return ImDicTypeMappingQueryHelper.findImDicTypeMappingDTOByColumnCode(databaseColumnCode);
	}

	public ImDicTypeMappingDTO findImDicTypeMappingDTOBydicTypeCode(
			String dicTypeCode) throws ServiceException {
		return ImDicTypeMappingQueryHelper.findImDicTypeMappingDTOBydicTypeCode(dicTypeCode);
	}
}
