package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtDeathInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 自然减员信息service（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午05:14:07
 */
public interface PtDeathInfoService
{
	/**
	 * 新增自然减员信息
	 * @param ptDeathInfoDTO
	 * @throws ServiceException
	 */
	public void createPtDeathInfo(PtDeathInfoDTO ptDeathInfoDTO) throws ServiceException;
	
	/**
	 * 获取自然减员信息
	 * @param ptDeathInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public PtDeathInfoDTO findPtDeathInfoById(Long ptDeathInfoOid) throws ServiceException;
	
	/**
	 * 更新自然减员信息
	 * @param ptDeathInfoDTO
	 * @throws ServiceException
	 */
	public void updatePtCancelEmploy(PtDeathInfoDTO ptDeathInfoDTO) throws ServiceException;
	
	/**
	 * 根据人员业务ID获取自然减员信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public PtDeathInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
}
