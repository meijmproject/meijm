package com.yh.hr.orghc.ut.service;

import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 单位信息管理Service
 * @author zhengdr
 *
 * 时间:2016-12-20下午05:27:22
 */
public interface BizUtUnitService
{
	/**
	 * 新增单位信息
	 * @param bizUtUnitDTO
	 * @throws ServiceException
	 */
	public Long createBizUtUnit(BizUtUnitDTO bizUtUnitDTO) throws ServiceException;
	
	/**
	 * 得到单位信息
	 * @param utUnitOid
	 * @return
	 * @throws ServiceException
	 */
	public BizUtUnitDTO findBizUtUnitById(Long utUnitOid) throws ServiceException;
	
	/**
	 * 更新单位信息
	 * @param bizUtUnitDTO
	 * @throws ServiceException
	 */
	public void updateBizUtUnit(BizUtUnitDTO bizUtUnitDTO) throws ServiceException;
	
	/**
	 * 得到单位信息
	 * @param taskOid
	 * @return
	 * @throws ServiceException
	 */
	public BizUtUnitDTO findBizUtUnitByTaskOid(Long taskOid) throws ServiceException;
	
}
