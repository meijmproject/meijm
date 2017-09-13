package com.yh.hr.res.im.service.impl;

import com.yh.hr.res.im.service.ImDicItemMappingService;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.bo.ImDicItemMapping;
import com.yh.hr.res.im.dto.ImDicItemMappingDTO;
import com.yh.hr.res.im.queryhelper.ImDicItemMappingQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

/**
 * 字典值映射操作service实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class ImDicItemMappingServiceImpl implements ImDicItemMappingService {

	/**
	 * 通过主键获取字典值映射信息
	 * @param dicItemMappingOid
	 * @return
	 * @throws ServiceException
	 */
	public ImDicItemMappingDTO get(Long dicItemMappingOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(ImDicItemMapping.class, dicItemMappingOid), ImDicItemMappingDTO.class);
	}
	
	/**
	 * 创建字典值映射信息
	 * @param imDicItemMappingDTO
	 * @throws ServiceException
	 */
	public void create(ImDicItemMappingDTO imDicItemMappingDTO) throws ServiceException {
		if(imDicItemMappingDTO!=null) {
			ImDicItemMapping imDicItemMapping = new ImDicItemMapping();
			BeanHelper.copyProperties(imDicItemMappingDTO, imDicItemMapping);
			imDicItemMapping.setEffectiveFlag(DicConstants.YHRS0003_1);
			imDicItemMapping.setCreatedByCode(UserContext.getLoginUserID());
			imDicItemMapping.setCreatedByName(UserContext.getLoginUserName());
			imDicItemMapping.setCreatedDate(DateUtil.now());
			imDicItemMapping.setUpdatedByCode(UserContext.getLoginUserID());
			imDicItemMapping.setUpdatedByName(UserContext.getLoginUserName());
			imDicItemMapping.setUpdatedDate(DateUtil.now());
			imDicItemMapping.save();
		}
	}
	
	/**
	 * 修改字典值映射信息
	 * @param imDicItemMappingDTO
	 * @throws ServiceException
	 */
	public void update(ImDicItemMappingDTO imDicItemMappingDTO) throws ServiceException {
		if(imDicItemMappingDTO!=null) {
			ImDicItemMapping imDicItemMapping = DaoUtil.get(ImDicItemMapping.class, imDicItemMappingDTO.getDicItemMappingOid());
			if(imDicItemMapping!=null) {
				BeanHelper.copyProperties(imDicItemMappingDTO, imDicItemMapping, BeanHelper.getNullPropertyNames(imDicItemMappingDTO));
				imDicItemMapping.setUpdatedByCode(UserContext.getLoginUserID());
				imDicItemMapping.setUpdatedByName(UserContext.getLoginUserName());
				imDicItemMapping.setUpdatedDate(DateUtil.now());
				imDicItemMapping.update();
			}
		}
	}
	
	/**
	 * 删除字典值映射信息
	 * @param dicTypeMappingOid
	 * @throws ServiceException
	 */
	public void delete(Long dicItemMappingOid) throws ServiceException {
		if(dicItemMappingOid!=null) {
			ImDicItemMapping imDicItemMapping = DaoUtil.get(ImDicItemMapping.class, dicItemMappingOid);
			if(imDicItemMapping!=null) {
				imDicItemMapping.delete();
			}
		}
	}
	
	/**
	 * 通过字典类型映射OID查询所有字典值映射信息
	 * @param dicTypeMappingOid
	 * @return
	 * @throws ServiceException
	 */
	public List<ImDicItemMappingDTO> findImDicItemMappingListByTypeOid(Long dicTypeMappingOid) throws ServiceException {
		return ImDicItemMappingQueryHelper.findImDicItemMappingListByTypeOid(dicTypeMappingOid);
	}
	
	/**
	 * 通过字典类型映射OID和是否已建立映射关系查询字典值映射信息
	 * @param dicTypeMappingOid
	 * @param isCreateMapping
	 * @return
	 * @throws ServiceException
	 */
	public List<ImDicItemMappingDTO> findImDicItemMappingListByTypeOid(Long dicTypeMappingOid, String isCreateMapping) throws ServiceException {
		return ImDicItemMappingQueryHelper.findImDicItemMappingListByTypeOid(dicTypeMappingOid, isCreateMapping);
	}
	
	/**
	 * 通过字典类型映射OID和标准字典值编码查询字典值映射信息
	 * @param dicTypeMappingOid
	 * @param dicItemCode
	 * @return
	 * @throws ServiceException
	 */
	public ImDicItemMappingDTO getImDicItemMappingDTOByOidAndCode(Long dicTypeMappingOid, String dicItemCode) throws ServiceException {
		return ImDicItemMappingQueryHelper.getImDicItemMappingDTOByOidAndCode(dicTypeMappingOid, dicItemCode);
	}
	
	/**
	 * 通过标准字典类型编码和标准字典值编码查询字典值映射信息
	 * @param dicTypeCode
	 * @param dicItemCode
	 * @return
	 * @throws ServiceException
	 */
	public ImDicItemMappingDTO getImDicItemMappingDTOByTypeCodeAndItemCode(Long dicTypeCode, String dicItemCode) throws ServiceException {
		return ImDicItemMappingQueryHelper.getImDicItemMappingDTOByTypeCodeAndItemCode(dicTypeCode, dicItemCode);
	}
	
	/**
	 * 检查该采集项是否已字典映射
	 * @param databaseColumnCode 数据库字段代码
	 * @param importItemName 字典采集项的值
	 * @return
	 * @throws ServiceException
	 */
	public Boolean checkDicMappingForColumnAndName(String databaseColumnCode, String importItemName) throws ServiceException {
		return ImDicItemMappingQueryHelper.checkDicMappingForColumnAndName(databaseColumnCode, importItemName);
	}
	
	/**
	 * 通过该字典项的值查询该字典值映射信息
	 * @param databaseColumnCode 数据库字段代码
	 * @param importItemName 字典采集项的值
	 * @return
	 * @throws ServiceException
	 */
	public ImDicItemMappingDTO getDicMappingDTOByColumnAndName(String databaseColumnCode, String importItemName) throws ServiceException {
		return ImDicItemMappingQueryHelper.getDicMappingDTOByColumnAndName(databaseColumnCode, importItemName);
	}
}
