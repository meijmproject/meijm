package com.yh.hr.res.ld.service;

import com.yh.hr.res.ld.dto.LdFlowDTO;
import com.yh.hr.res.ld.dto.LdInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 领导职数资源使用信息服务接口
 * @author liuhw
 * 2016-8-23
 */
public interface LdInfoService
{
	/**
	 * 创建领导职数资源使用信息
	 * @throws ServiceException
	 */
	public void createLdInfo(LdFlowDTO dto)throws ServiceException;
	
	/**
	 * 更新领导职数资源使用信息
	 * @param bp
	 * @throws ServiceException
	 */
	public void updateLdInfo(LdFlowDTO bp)throws ServiceException;
	
	/**
	 * 领导职数资源使用情况查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public int countLdInfo(LdInfoDTO dto) throws ServiceException;
}
