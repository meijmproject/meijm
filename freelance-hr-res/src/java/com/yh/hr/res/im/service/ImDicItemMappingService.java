package com.yh.hr.res.im.service;

import java.util.List;

import com.yh.hr.res.im.dto.ImDicItemMappingDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 字典值映射操作service接口
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public interface ImDicItemMappingService {

	/**
	 * 通过主键获取字典值映射信息
	 * @param dicItemMappingOid
	 * @return
	 * @throws ServiceException
	 */
	public ImDicItemMappingDTO get(Long dicItemMappingOid) throws ServiceException;
	
	/**
	 * 创建字典值映射信息
	 * @param imDicItemMappingDTO
	 * @throws ServiceException
	 */
	public void create(ImDicItemMappingDTO imDicItemMappingDTO) throws ServiceException;
	
	/**
	 * 修改字典值映射信息
	 * @param imDicItemMappingDTO
	 * @throws ServiceException
	 */
	public void update(ImDicItemMappingDTO imDicItemMappingDTO) throws ServiceException;
	
	/**
	 * 删除字典值映射信息
	 * @param dicTypeMappingOid
	 * @throws ServiceException
	 */
	public void delete(Long dicItemMappingOid) throws ServiceException;
	
	/**
	 * 通过字典类型映射OID查询所有字典值映射信息
	 * @param dicTypeMappingOid
	 * @return
	 * @throws ServiceException
	 */
	public List<ImDicItemMappingDTO> findImDicItemMappingListByTypeOid(Long dicTypeMappingOid) throws ServiceException;
	
	/**
	 * 通过字典类型映射OID和是否已建立映射关系查询字典值映射信息
	 * @param dicTypeMappingOid
	 * @param isCreateMapping
	 * @return
	 * @throws ServiceException
	 */
	public List<ImDicItemMappingDTO> findImDicItemMappingListByTypeOid(Long dicTypeMappingOid, String isCreateMapping) throws ServiceException;
	
	/**
	 * 通过字典类型映射OID和标准字典值编码查询字典值映射信息
	 * @param dicTypeMappingOid
	 * @param dicItemCode
	 * @return
	 * @throws ServiceException
	 */
	public ImDicItemMappingDTO getImDicItemMappingDTOByOidAndCode(Long dicTypeMappingOid, String dicItemCode) throws ServiceException;
	
	/**
	 * 通过标准字典类型编码和标准字典值编码查询字典值映射信息
	 * @param dicTypeCode
	 * @param dicItemCode
	 * @return
	 * @throws ServiceException
	 */
	public ImDicItemMappingDTO getImDicItemMappingDTOByTypeCodeAndItemCode(Long dicTypeCode, String dicItemCode) throws ServiceException;
	
	/**
	 * 检查该采集项是否已字典映射
	 * @param databaseColumnCode 数据库字段代码
	 * @param importItemName 字典采集项的值
	 * @return
	 * @throws ServiceException
	 */
	public Boolean checkDicMappingForColumnAndName(String databaseColumnCode, String importItemName) throws ServiceException;
	
	/**
	 * 通过该字典项的值查询该字典值映射信息
	 * @param databaseColumnCode 数据库字段代码
	 * @param importItemName 字典采集项的值
	 * @return
	 * @throws ServiceException
	 */
	public ImDicItemMappingDTO getDicMappingDTOByColumnAndName(String databaseColumnCode, String importItemName) throws ServiceException;
}
