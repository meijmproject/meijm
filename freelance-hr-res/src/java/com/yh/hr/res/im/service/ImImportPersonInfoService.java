package com.yh.hr.res.im.service;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.im.dto.ImImportPersonInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 导入人员操作service接口
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public interface ImImportPersonInfoService {

	/**
	 * 通过主键获取导入人员信息
	 * @param importPersonInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public ImImportPersonInfoDTO get(Long importPersonInfoOid) throws ServiceException;
	
	/**
	 * 创建导入人员信息
	 * @param imImportPersonInfoDTO
	 * @throws ServiceException
	 */
	public void create(ImImportPersonInfoDTO imImportPersonInfoDTO) throws ServiceException;
	
	/**
	 * 修改导入人员信息
	 * @param imImportPersonInfoDTO
	 * @throws ServiceException
	 */
	public void update(ImImportPersonInfoDTO imImportPersonInfoDTO) throws ServiceException;
	
	/**
	 * 删除导入人员信息
	 * @param importPersonInfoOid
	 * @throws ServiceException
	 */
	public void delete(Long importPersonInfoOid) throws ServiceException;
	
	/**
	 * 通过导入批次OID查询该批次的导入人员
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	public List<ImImportPersonInfoDTO> findImImportPersonInfoDTOListByBatchOid(Long importBatchOid) throws ServiceException;
	
	/**
	 * 通过数据库字段名和导入字典值查询导入人员信息
	 * @param importBatchOid
	 * @param databaseColumnCode
	 * @param importItemName
	 * @return
	 * @throws ServiceException
	 */
	public List<ImImportPersonInfoDTO> findImImportPersonInfoDTOListByCodeAndName(Long importBatchOid, String databaseColumnCode, String importItemName) throws ServiceException;
	
	/**
	 * 通过数据库字段名查询该人员表中该字段的值
	 * @param importBatchOid
	 * @param name
	 * @param birthday
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public Object getColumnValueByColumnCode(Long importBatchOid,String name, Date birthday, String databaseColumnCode) throws ServiceException;
}
