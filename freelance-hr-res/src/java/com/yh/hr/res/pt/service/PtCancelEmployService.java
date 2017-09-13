package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtCancelEmployDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 取消聘用信息service（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午05:14:07
 */
public interface PtCancelEmployService
{
	/**
	 * 新增取消聘用信息
	 * @param ptCancelEmployDTO
	 * @throws ServiceException
	 */
	public void createPtCancelEmploy(PtCancelEmployDTO ptCancelEmployDTO) throws ServiceException;
	
	/**
	 * 获取取消聘用信息
	 * @param ptCancelEmployOid
	 * @return
	 * @throws ServiceException
	 */
	public PtCancelEmployDTO findPtCancelEmployById(Long ptCancelEmployOid) throws ServiceException;
	
	/**
	 * 更新取消聘用信息
	 * @param ptCancelEmployDTO
	 * @throws ServiceException
	 */
	public void updatePtCancelEmploy(PtCancelEmployDTO ptCancelEmployDTO) throws ServiceException;
	
	/**
	 * 根据人员业务ID获取取消聘用信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public PtCancelEmployDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
}
