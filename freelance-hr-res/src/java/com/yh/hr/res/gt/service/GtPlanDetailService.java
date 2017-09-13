package com.yh.hr.res.gt.service;

import com.yh.hr.res.gt.dto.GtPlanDetailDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * service（业务）
 * @author huangyj
 */
public interface GtPlanDetailService {

	/**
	 * 新增信息
	 * @param gtPlanDetailDTO
	 * @throws ServiceException
	 */
	public void createGtPlanDetail(GtPlanDetailDTO gtPlanDetailDTO) throws ServiceException;
	
	/**
	 * 获取信息
	 * @param jhgGtPlanDetailOid
	 * @return
	 * @throws ServiceException
	 */
	public GtPlanDetailDTO findGtPlanDetailById(Long jhgGtPlanDetailOid) throws ServiceException;
	
	/**
	 * 更新信息
	 * @param gtPlanDetailDTO
	 * @throws ServiceException
	 */
	public void updateGtPlanDetail(GtPlanDetailDTO gtPlanDetailDTO) throws ServiceException;
	
	/**
	 * 删除信息
	 * @param jhgGtPlanDetailOid
	 * @throws ServiceException
	 */
	public void deleteGtPlanDetail(Long jhgGtPlanDetailOid) throws ServiceException;
}
