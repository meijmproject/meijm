package com.yh.hr.res.im.service.impl;

import java.util.List;

import com.yh.hr.res.im.bo.ImCheckDefinition;
import com.yh.hr.res.im.dto.ImCheckDefinitionDTO;
import com.yh.hr.res.im.queryhelper.ImCheckDefinitionQueryHelper;
import com.yh.hr.res.im.service.ImCheckDefinitionService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 数据校核检查项定义操作service实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class ImCheckDefinitionServiceImpl implements ImCheckDefinitionService {

	/**
	 * 通过主键获取检查项定义信息列表
	 * @param checkDifinitionOid
	 * @return
	 * @throws ServiceException
	 */
	public ImCheckDefinitionDTO get(Long checkDifinitionOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(ImCheckDefinition.class, checkDifinitionOid), ImCheckDefinitionDTO.class);
	}
	
	/**
	 * 查询指定检查类型的检查项定义信息
	 * @param checkType
	 * @return
	 * @throws ServiceException
	 */
	public List<ImCheckDefinitionDTO> findImCheckDefinitionDTOListByCheckType(String checkType) throws ServiceException {
		return ImCheckDefinitionQueryHelper.findImCheckDefinitionDTOListByCheckType(checkType);
	}
	
	/**
	 * 查询指定检查类型的检查项定义信息
	 * @param checkType
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public ImCheckDefinitionDTO findImCheckDefinitionDTOByCheckTypeAndColumnCode(String checkType, String databaseColumnCode) throws ServiceException {
		return ImCheckDefinitionQueryHelper.findImCheckDefinitionDTOByCheckTypeAndColumnCode(checkType, databaseColumnCode);
	}
}
