package com.yh.hr.res.im.queryhelper;

import java.util.List;

import com.yh.hr.res.im.dto.ImDicTypeMappingDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class ImDicTypeMappingQueryHelper {

	/**
	 * 获取所有有效的需映射的字典类型
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImDicTypeMappingDTO> findAllImDicTypeMappingDTO() throws ServiceException {
		String hql = "from ImDicTypeMapping itm where itm.effectiveFlag = '1'";
		return BeanHelper.copyProperties(DaoUtil.find(hql), ImDicTypeMappingDTO.class);
	}
	
	/**
	 * 通过数据库字段代码获取该字段字典类型
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public static ImDicTypeMappingDTO findImDicTypeMappingDTOByColumnCode(String databaseColumnCode) throws ServiceException {
		String hql = "from ImDicTypeMapping itm where itm.effectiveFlag = '1' and itm.databaseColumnCode='" + databaseColumnCode + "'";
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(hql), ImDicTypeMappingDTO.class);
	}
	/**
	 * 通过数据库字段代码获取该字段字典类型
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public static ImDicTypeMappingDTO findImDicTypeMappingDTOBydicTypeCode(String dicTypeCode) throws ServiceException {
		String hql = "from ImDicTypeMapping itm where itm.effectiveFlag = '1' and itm.dicTypeCode='" + dicTypeCode + "'";
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(hql), ImDicTypeMappingDTO.class);
	}
}
