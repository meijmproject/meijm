package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtPersonOutDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员调离信息service（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午05:14:07
 */
public interface PtPersonOutService
{
	/**
	 * 新增人员调离信息
	 * @param ptPersonOutDTO
	 * @throws ServiceException
	 */
	public void createPtPersonOut(PtPersonOutDTO ptPersonOutDTO) throws ServiceException;
	
	/**
	 * 获取人员调离信息
	 * @param ptPersonOutOid
	 * @return
	 * @throws ServiceException
	 */
	public PtPersonOutDTO findPtPersonOutById(Long ptPersonOutOid) throws ServiceException;
	
	/**
	 * 更新人员调离信息
	 * @param ptPersonOutDTO
	 * @throws ServiceException
	 */
	public void updatePtPersonOut(PtPersonOutDTO ptPersonOutDTO) throws ServiceException;
	
	/**
	 * 根据人员业务ID获取人员调离信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public PtPersonOutDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
}
