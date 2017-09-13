package com.yh.hr.res.im.service;

import java.util.List;

import com.yh.hr.res.im.dto.ImCheckDefinitionDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 数据校核检查项定义操作service接口
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public interface ImCheckDefinitionService {

	/**
	 * 通过主键获取检查项定义信息列表
	 * @param checkDifinitionOid
	 * @return
	 * @throws ServiceException
	 */
	public ImCheckDefinitionDTO get(Long checkDifinitionOid) throws ServiceException;
	
	/**
	 * 查询指定检查类型的检查项定义信息
	 * @param checkType
	 * @return
	 * @throws ServiceException
	 */
	public List<ImCheckDefinitionDTO> findImCheckDefinitionDTOListByCheckType(String checkType) throws ServiceException;
	
	/**
	 * 查询指定检查类型的检查项定义信息
	 * @param checkType
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public ImCheckDefinitionDTO findImCheckDefinitionDTOByCheckTypeAndColumnCode(String checkType, String databaseColumnCode) throws ServiceException;
}
