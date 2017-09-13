package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtRefuseInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 辞退信息service（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午05:14:07
 */
public interface PtRefuseInfoService
{
	/**
	 * 新增辞退信息
	 * @param ptRefuseInfoDTO
	 * @throws ServiceException
	 */
	public void createPtRefuseInfo(PtRefuseInfoDTO ptRefuseInfoDTO) throws ServiceException;
	
	/**
	 * 获取辞退信息
	 * @param ptRefuseInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public PtRefuseInfoDTO findPtRefuseInfoById(Long ptRefuseInfoOid) throws ServiceException;
	
	/**
	 * 更新辞退信息
	 * @param ptRefuseInfoDTO
	 * @throws ServiceException
	 */
	public void updatePtRefuseInfo(PtRefuseInfoDTO ptRefuseInfoDTO) throws ServiceException;
	
	/**
	 * 根据人员业务ID获取辞退信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public PtRefuseInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
}
