package com.yh.hr.res.gb.service;

import com.yh.hr.res.gb.dto.GbSortManagementDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * service
 * @author huangyj
 */
public interface GbSortManagementService {

	/**
	 * 新增
	 * @param gbSortManagementDTO
	 * @throws ServiceException
	 */
	public void createGbSortManagement(GbSortManagementDTO gbSortManagementDTO) throws ServiceException;
	
	/**
	 * 查看
	 * @param gbSortManagementOid
	 * @return
	 * @throws ServiceException
	 */
	public GbSortManagementDTO findGbSortManagementById(Long gbSortManagementOid) throws ServiceException;
	
	/**
	 * 修改
	 * @param gbSortManagementDTO
	 * @throws ServiceException
	 */
	public void updateGbSortManagement(GbSortManagementDTO gbSortManagementDTO) throws ServiceException;
	
	/**
	 * 删除
	 * @param gbSortManagementOid
	 * @throws ServiceException
	 */
	public void deleteGbSortManagement(Long gbSortManagementOid) throws ServiceException;
}
