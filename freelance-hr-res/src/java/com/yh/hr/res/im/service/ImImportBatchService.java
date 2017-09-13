package com.yh.hr.res.im.service;

import java.util.List;

import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 导入批次操作service接口
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public interface ImImportBatchService {

	/**
	 * 通过主键获取导入批次信息
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	public ImImportBatchDTO get(Long importBatchOid) throws ServiceException;
	
	/**
	 * 创建导入批次信息
	 * @param imImportBatchDTO
	 * @throws ServiceException
	 */
	public Long create(ImImportBatchDTO imImportBatchDTO) throws ServiceException;
	
	/**
	 * 修改导入批次信息
	 * @param imImportBatchDTO
	 * @throws ServiceException
	 */
	public void update(ImImportBatchDTO imImportBatchDTO) throws ServiceException;
	
	/**
	 * 删除导入批次信息
	 * @param importBatchOid
	 * @throws ServiceException
	 */
	public void delete(Long importBatchOid) throws ServiceException;
	
	/**
	 * 查询所有的导入批次
	 * @return
	 * @throws ServiceException
	 */
	public List<ImImportBatchDTO> findAllImImportBatchDTO() throws ServiceException;
	
	/**
	 * 获取当前最新的导入批次
	 * @return
	 * @throws ServiceException
	 */
	public ImImportBatchDTO getCurrentImportBatchDTO() throws ServiceException;
}
