package com.yh.hr.res.gt.service;

import java.util.List;

import com.yh.hr.res.gt.dto.GtSortManagementDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * service（业务）
 * @author huangyj
 */
public interface GtSortManagementService {

	/**
	 * 新增
	 * @param gtSortManagementDTO
	 * @throws ServiceException
	 */
	public void createGtSortManagement(GtSortManagementDTO gtSortManagementDTO) throws ServiceException;
	
	/**
	 * 查看
	 * @param gtSortManagementOid
	 * @return
	 * @throws ServiceException
	 */
	public GtSortManagementDTO findGtSortManagementById(Long gtSortManagementOid) throws ServiceException;
	

	/**根据单位OID查询
	 * @param UnitOId
	 * @return
	 * @throws ServiceException
	 */
	public List<GtSortManagementDTO> getGtSortManagementByUnitOId(Long UnitOId) throws ServiceException;
	
	/**
	 * 根据单位TaskOId查询
	 * @param TaskOId
	 * @return
	 * @throws ServiceException
	 */
	public List<GtSortManagementDTO> getGtSortManagementByTaskOId(Long TaskOId) throws ServiceException;
	/**
	 * 修改
	 * @param gtSortManagementDTO
	 * @throws ServiceException
	 */
	public void updateGtSortManagement(GtSortManagementDTO gtSortManagementDTO) throws ServiceException;
	
	/**
	 * 删除
	 * @param gtSortManagementOid
	 * @throws ServiceException
	 */
	public void deleteGtSortManagement(Long gtSortManagementOid) throws ServiceException;
}
