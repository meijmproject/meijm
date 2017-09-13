package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtDemissionInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 辞职信息service（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午05:14:07
 */
public interface PtDemissionInfoService
{
	/**
	 * 新增辞职信息
	 * @param ptDemissionInfoDTO
	 * @throws ServiceException
	 */
	public void createPtDemissionInfo(PtDemissionInfoDTO ptDemissionInfoDTO) throws ServiceException;
	
	/**
	 * 获取辞职信息
	 * @param ptDemissionInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public PtDemissionInfoDTO findPtDemissionInfoById(Long ptDemissionInfoOid) throws ServiceException;
	
	/**
	 * 更新辞职信息
	 * @param ptDemissionInfoDTO
	 * @throws ServiceException
	 */
	public void updatePtDemissionInfo(PtDemissionInfoDTO ptDemissionInfoDTO) throws ServiceException;
	
	/**
	 * 根据人员业务ID获取辞职信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public PtDemissionInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
}
