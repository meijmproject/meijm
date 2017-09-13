package com.yh.hr.res.gt.service;

import com.yh.hr.res.gt.dto.GtOtherInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public interface GtOtherInfoService {

	/**
	 * 新增信息
	 * @param gtOtherInfoDTO
	 * @throws ServiceException
	 */
	public void createGtOtherInfo(GtOtherInfoDTO gtOtherInfoDTO) throws ServiceException;
	
	/**
	 * 获取信息
	 * @param jhgGtOtherInfo
	 * @return
	 * @throws ServiceException
	 */
	public GtOtherInfoDTO findGtOtherInfoById(Long jhgGtOtherInfo) throws ServiceException;
	
	/**
	 * 更新信息
	 * @param gtOtherInfoDTO
	 * @throws ServiceException
	 */
	public void updateGtOtherInfo(GtOtherInfoDTO gtOtherInfoDTO) throws ServiceException;
	
	/**
	 * 删除信息
	 * @param jhgGtOtherInfo
	 * @throws ServiceException
	 */
	public void deleteGtOtherInfo(Long jhgGtOtherInfo) throws ServiceException;
}
