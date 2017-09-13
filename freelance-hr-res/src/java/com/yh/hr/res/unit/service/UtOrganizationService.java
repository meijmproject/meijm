/**
 * 
 */
package com.yh.hr.res.unit.service;

import com.yh.platform.core.exception.ServiceException;

/**
 * 组织机构信息Service
 * @author	lenghh
 * @version	1.0,	16/09/06
 */

public interface UtOrganizationService {
	
	/**
	 * 创建组织信息
	 * @param utUnitDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(String organizationName,String organizationType) throws ServiceException;

	/**
	 * 修改组织信息
	 * @param organizationOid
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public void update(Long organizationOid, String organizationName) throws ServiceException;

	/**
	 * 删除组织信息
	 * @param organizationOid
	 * @return
	 * @throws ServiceException
	 */
	public void delete(Long organizationOid) throws ServiceException;

}
