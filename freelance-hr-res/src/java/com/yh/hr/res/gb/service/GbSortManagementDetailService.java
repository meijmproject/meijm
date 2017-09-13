package com.yh.hr.res.gb.service;

import java.util.List;

import com.yh.hr.res.gb.dto.GbSortManagementDetailDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * service
 * @author huangyj
 */
public interface GbSortManagementDetailService {

	/**
	 * 新增
	 * @param gbSortManagementDetailDTO
	 * @throws ServiceException
	 */
	public void createGbSortManagementDetail(GbSortManagementDetailDTO gbSortManagementDetailDTO) throws ServiceException;
	
	/**
	 * 查看
	 * @param gbSortManagementDetailOid
	 * @return
	 * @throws ServiceException
	 */
	public GbSortManagementDetailDTO findGbSortManagementDetailById(Long gbSortManagementDetailOid) throws ServiceException;
	
	/**
	 * 查看
	 * @param gbSortManagementDetailOid
	 * @return
	 * @throws ServiceException
	 */
	public  List<GbSortManagementDetailDTO> listGbSortManagementDetailByManageId(Long gbSortManagementOid) throws ServiceException;
	
	
	
	/**
	 * 修改
	 * @param gbSortManagementDetailDTO
	 * @throws ServiceException
	 */
	public void updateGbSortManagementDetail(GbSortManagementDetailDTO gbSortManagementDetailDTO) throws ServiceException;
	
	/**
	 * 删除
	 * @param gbSortManagementDetailOid
	 * @throws ServiceException
	 */
	public void deleteGbSortManagementDetail(Long gbSortManagementDetailOid) throws ServiceException;
}
