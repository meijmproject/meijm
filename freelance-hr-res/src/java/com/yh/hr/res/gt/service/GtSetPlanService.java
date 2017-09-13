package com.yh.hr.res.gt.service;

import com.yh.hr.res.gt.dto.GtSetPlanDTO;
import com.yh.platform.core.exception.ServiceException;

public interface GtSetPlanService {

	/**
	 * 新增岗位设置方案信息
	 * @param gtSetPlanDTO
	 * @return 
	 * @throws ServiceException
	 */
	public Long createGtSetPlan(GtSetPlanDTO gtSetPlanDTO) throws ServiceException;
	
	/**
	 * 获取岗位设置方案信息
	 * @param gtSetPlanOid
	 * @return
	 * @throws ServiceException
	 */
	public GtSetPlanDTO findGtSetPlanById(Long gtSetPlanOid) throws ServiceException;
	
	/**
	 * 更新岗位设置方案信息
	 * @param gtSetPlanDTO
	 * @throws ServiceException
	 */
	public void updateGtSetPlan(GtSetPlanDTO gtSetPlanDTO) throws ServiceException;
	
	/**
	 * 删除岗位设置方案信息
	 * @param gtSetPlanOid
	 * @throws ServiceException
	 */
	public void deleteGtSetPlan(Long gtSetPlanOid) throws ServiceException;
}
