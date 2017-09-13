package com.yh.hr.res.im.queryhelper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.im.bo.ImDicItemMapping;
import com.yh.hr.res.im.dto.ImDicItemMappingDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class ImDicItemMappingQueryHelper {

	/**
	 * 通过字典类型映射OID查询所有字典值映射信息
	 * @param dicTypeMappingOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImDicItemMappingDTO> findImDicItemMappingListByTypeOid(Long dicTypeMappingOid) throws ServiceException {
		String hql = "from ImDicItemMapping iim where iim.effectiveFlag = '1' and iim.dicTypeMappingOid=?";
		return BeanHelper.copyProperties(DaoUtil.find(hql, dicTypeMappingOid), ImDicItemMappingDTO.class);
	}

	/**
	 * 通过字典类型映射OID和是否已建立映射关系查询字典值映射信息
	 * @param dicTypeMappingOid
	 * @param isCreateMapping
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImDicItemMappingDTO> findImDicItemMappingListByTypeOid(Long dicTypeMappingOid, String isCreateMapping) throws ServiceException {
		String hql = "from ImDicItemMapping iim where iim.effectiveFlag = '1' and iim.dicTypeMappingOid=? and iim.isCreateMapping=?";
		return BeanHelper.copyProperties(DaoUtil.find(hql, dicTypeMappingOid, isCreateMapping), ImDicItemMappingDTO.class);
	}
	
	/**
	 * 通过字典类型映射OID和标准字典值编码查询字典值映射信息
	 * @param dicTypeMappingOid
	 * @param dicItemCode
	 * @return
	 * @throws ServiceException
	 */
	public static ImDicItemMappingDTO getImDicItemMappingDTOByOidAndCode(Long dicTypeMappingOid, String dicItemCode) throws ServiceException {
		String hql = "from ImDicItemMapping iim where iim.effectiveFlag = '1' and iim.dicTypeMappingOid=? and iim.dicItemCode=?";
		List<ImDicItemMapping> list = DaoUtil.find(hql, dicTypeMappingOid, dicItemCode);
		if(CollectionUtils.isNotEmpty(list)) {
			return BeanHelper.copyProperties(list.get(0), ImDicItemMappingDTO.class);
		}
		return null;
	}
	
	/**
	 * 通过标准字典类型编码和标准字典值编码查询字典值映射信息
	 * @param dicTypeCode
	 * @param dicItemCode
	 * @return
	 * @throws ServiceException
	 */
	public static ImDicItemMappingDTO getImDicItemMappingDTOByTypeCodeAndItemCode(Long dicTypeCode, String dicItemCode) throws ServiceException {
		String hql = "select iim from ImDicItemMapping iim,ImDicTypeMapping itm where iim.dicTypeMappingOid=itm.dicTypeMappingOid " +
				"and iim.effectiveFlag = '1' and itm.effectiveFlag = '1' and itm.dicTypeCode=? and iim.dicItemCode=?";
		List<ImDicItemMapping> list = DaoUtil.find(hql, dicTypeCode, dicItemCode);
		if(CollectionUtils.isNotEmpty(list)) {
			return BeanHelper.copyProperties(list.get(0), ImDicItemMappingDTO.class);
		}
		return null;
	}
	
	/**
	 * 通过该字典项的值查询该字典值映射信息
	 * @param databaseColumnCode 数据库字段代码
	 * @param importItemName 字典采集项的值
	 * @return
	 * @throws ServiceException
	 */
	public static ImDicItemMappingDTO getDicMappingDTOByColumnAndName(String databaseColumnCode, String importItemName) throws ServiceException {
		String hql = "select iim from ImDicItemMapping iim,ImDicTypeMapping itm where iim.dicTypeMappingOid=itm.dicTypeMappingOid " +
				"and iim.effectiveFlag = '1' and itm.effectiveFlag = '1' and itm.databaseColumnCode=? and iim.importItemName=?";
		List<ImDicItemMapping> list = DaoUtil.find(hql, databaseColumnCode, importItemName);
		if(CollectionUtils.isNotEmpty(list)) {
			return BeanHelper.copyProperties(list.get(0), ImDicItemMappingDTO.class);
		}
		return null;
	}

	/**
	 * 检查该采集项是否已字典映射
	 * @param databaseColumnCode 数据库字段代码
	 * @param importItemName 字典采集项的值
	 * @return
	 * @throws ServiceException
	 */
	public static Boolean checkDicMappingForColumnAndName(String databaseColumnCode, String importItemName) throws ServiceException {
		String hql = "select iim from ImDicItemMapping iim,ImDicTypeMapping itm where iim.dicTypeMappingOid=itm.dicTypeMappingOid " +
				"and iim.effectiveFlag = '1' and itm.effectiveFlag = '1' and itm.databaseColumnCode=? and iim.importItemName=? and iim.isCreateMapping='1'";
		List<ImDicItemMapping> list = DaoUtil.find(hql, databaseColumnCode, importItemName);
		if(CollectionUtils.isNotEmpty(list)) {
			return true;
		}
		return false;
	}
}
