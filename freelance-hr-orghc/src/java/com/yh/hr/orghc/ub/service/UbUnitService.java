package com.yh.hr.orghc.ub.service;

import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.orghc.ub.dto.UbUnitDTO;
import com.yh.platform.core.exception.ServiceException;

public interface UbUnitService {
	
	/**
	 * 获取单位信息
	 * @param unitOid
	 * @return UbUnit
     * @throws ServiceException 
	 */
	public UbUnit get(Long unitOid) throws ServiceException;
	
	/**
	 * 获取单位名称
	 * @param unitOid
	 * @return unitName
     * @throws ServiceException 
	 */
	public String getUnitName(Long unitOid) throws ServiceException;
	
	/**
	 * 获取单位信息
	 * @param unitOid
	 * @return UbUnit
     * @throws ServiceException 
	 */
	public UbUnitDTO getUbUnitDTOById(Long unitOid) throws ServiceException;
	
	/**
	 * 创建单位信息
	 * @param ubUnitDTO
     * @throws ServiceException 
	 */
	public Long create(UbUnitDTO ubUnitDTO) throws ServiceException;
	
	/**
	 * 更新单位信息
	 * @param ubUnitDTO
     * @throws ServiceException 
	 */
	public void update(UbUnitDTO ubUnitDTO) throws ServiceException;
	
	/**
	 * 更新单位信息同时调用组件接口同步机构账信息
	 * @param ubUnitDTO
     * @throws ServiceException 
	 */
	public void update(UbUnit ubUnit) throws ServiceException;
	
}
