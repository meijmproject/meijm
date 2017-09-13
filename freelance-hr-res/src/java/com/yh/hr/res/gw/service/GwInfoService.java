package com.yh.hr.res.gw.service;

import com.yh.hr.res.gw.dto.GwFlowDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 岗位资源使用信息服务接口
 * @author liuhw
 * 2016-8-23
 */
public interface GwInfoService
{
	/**
	 * 创建岗位资源使用信息
	 * @throws ServiceException
	 */
	public void createGwInfo(GwFlowDTO dto)throws ServiceException;
	
	/**
	 * 更新岗位资源使用信息
	 * @param bp
	 * @throws ServiceException
	 */
	public void updateGwInfo(GwFlowDTO bp)throws ServiceException;
}
