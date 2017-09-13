package com.yh.hr.res.gb.service;

import com.yh.hr.res.gb.dto.GbPlanDetailDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * service
 * @author huangyj
 */
public interface GbPlanDetailService {

	/**
	 * 新增
	 * @param gbPlanDetailDTO
	 * @throws ServiceException
	 */
	public void createGbPlanDetail(GbPlanDetailDTO gbPlanDetailDTO) throws ServiceException;
	
	/**
	 * 查看
	 * @param jhgGbPlanDetailOid
	 * @return
	 * @throws ServiceException
	 */
	public GbPlanDetailDTO findGbPlanDetailById(Long jhgGbPlanDetailOid) throws ServiceException;
	
	/**
	 * 修改
	 * @param gbPlanDetailDTO
	 * @throws ServiceException
	 */
	public void updateGbPlanDetail(GbPlanDetailDTO gbPlanDetailDTO) throws ServiceException;
	
	/**
	 * 删除
	 * @param jhgGbPlanDetailOid
	 * @throws ServiceException
	 */
	public void deleteGbPlanDetail(Long jhgGbPlanDetailOid) throws ServiceException;
}
