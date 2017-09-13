package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtDismissInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 开除信息service（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午05:14:07
 */
public interface PtDismissInfoService
{
	/**
	 * 新增开除信息
	 * @param ptDismissInfoDTO
	 * @throws ServiceException
	 */
	public void createPtDismissInfo(PtDismissInfoDTO ptDismissInfoDTO) throws ServiceException;
	
	/**
	 * 获取开除信息
	 * @param ptDismissInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public PtDismissInfoDTO findPtDismissInfoById(Long ptDismissInfoOid) throws ServiceException;
	
	/**
	 * 更新开除信息
	 * @param ptDismissInfoDTO
	 * @throws ServiceException
	 */
	public void updatePtDismissInfo(PtDismissInfoDTO ptDismissInfoDTO) throws ServiceException;
	
	/**
	 * 根据人员业务ID获取开除信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public PtDismissInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
}
