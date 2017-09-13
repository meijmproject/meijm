package com.yh.hr.res.hc.service;

import com.yh.hr.res.hc.dto.HcFlowDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 编制资源使用信息服务接口
 * @author liuhw
 * 2016-8-23
 */
public interface HcInfoService
{
	/**
	 * 创建编制资源使用信息
	 * @throws ServiceException
	 */
	public void createHcInfo(HcFlowDTO dto)throws ServiceException;
	
	/**
	 * 更新编制资源使用信息
	 * @param bp
	 * @throws ServiceException
	 */
	public void updateHcInfo(HcFlowDTO bp)throws ServiceException;
	
	/**
	 * 统计数
	 * @param bp
	 * @return
	 * @throws ServiceException
	 */
	public int  countHcInfo(HcFlowDTO bp)throws ServiceException;
}
