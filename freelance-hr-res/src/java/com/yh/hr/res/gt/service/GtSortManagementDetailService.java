package com.yh.hr.res.gt.service;

import java.util.List;

import com.yh.hr.res.gt.dto.GtSortManagementDetailDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * service（业务）
 * @author huangyj
 */
public interface GtSortManagementDetailService {

	/**
	 * 新增
	 * @param gtSortManagementDetailDTO
	 * @throws ServiceException
	 */
	public void createGtSortManagementDetail(GtSortManagementDetailDTO gtSortManagementDetailDTO) throws ServiceException;
	
	/**
	 * 查看
	 * @param gtSortManagementDetailOid
	 * @return
	 * @throws ServiceException
	 */
	public GtSortManagementDetailDTO findGtSortManagementDetailById(Long gtSortManagementDetailOid) throws ServiceException;
	
	/**
	 * 查询
	 * @param gtSortManagementOid
	 * @return
	 * @throws ServiceException
	 */
	public List<GtSortManagementDetailDTO> listGtSortManagementDetailByManageId(Long gtSortManagementOid) throws ServiceException;
	/**
	 * 修改
	 * @param gtSortManagementDetailDTO
	 * @throws ServiceException
	 */
	public void updateGtSortManagementDetail(GtSortManagementDetailDTO gtSortManagementDetailDTO) throws ServiceException;
	
	/**
	 * 删除
	 * @param gtSortManagementDetailOid
	 * @throws ServiceException
	 */
	public void deleteGtSortManagementDetail(Long gtSortManagementDetailOid) throws ServiceException;
}
