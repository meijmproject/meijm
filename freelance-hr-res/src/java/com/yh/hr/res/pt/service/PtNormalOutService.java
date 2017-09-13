package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.bo.PtNormalOut;
import com.yh.platform.core.exception.ServiceException;

/**
 * 减员信息service（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午05:14:07
 */
public interface PtNormalOutService
{
	
	/**
	 * 根据人员业务ID获取人员减员信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public PtNormalOut findByBizPersonOid(Long bizPersonOid) throws ServiceException;
	/**
	 * 计数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPtNormalOutByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
}
