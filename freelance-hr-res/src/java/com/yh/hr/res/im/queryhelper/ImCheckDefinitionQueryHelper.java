package com.yh.hr.res.im.queryhelper;

import java.util.List;

import com.yh.hr.res.im.dto.ImCheckDefinitionDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class ImCheckDefinitionQueryHelper {

	/**
	 * 查询指定检查类型的检查项定义信息列表
	 * @param checkType
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImCheckDefinitionDTO> findImCheckDefinitionDTOListByCheckType(String checkType) throws ServiceException {
		String hql = "from ImCheckDefinition cd where cd.effectiveFlag='1' and cd.checkType=?";
		return BeanHelper.copyProperties(DaoUtil.find(hql, checkType), ImCheckDefinitionDTO.class);
	}
	
	/**
	 * 查询指定检查类型的检查项定义信息
	 * @param checkType
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public static ImCheckDefinitionDTO findImCheckDefinitionDTOByCheckTypeAndColumnCode(String checkType, String databaseColumnCode) throws ServiceException {
		String hql = "from ImCheckDefinition cd where cd.effectiveFlag='1' and cd.checkType=? and cd.databaseColumnCode=?";
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(hql, checkType, databaseColumnCode), ImCheckDefinitionDTO.class);
	}
}
