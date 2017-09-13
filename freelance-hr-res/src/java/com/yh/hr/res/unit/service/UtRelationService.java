/**
 * 
 */
package com.yh.hr.res.unit.service;

import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 组织机构对应关系Service
 * @author	lenghh
 * @version	1.0,	16/09/06
 */

public interface UtRelationService {

	/**
	 * 创建内设机构组织机构关系
	 * @param utOrgDTO
	 * @return
	 * @throws ServiceException
	 */
	public void create(UtOrgDTO utOrgDTO) throws ServiceException;

	/**
	 * 修改内设机构组织机构关系
	 * @param utOrgDTO
	 * @return
	 * @throws ServiceException
	 */
	public void update(UtOrgDTO utOrgDTO) throws ServiceException;

	/**
	 * 删除组织机构关系
	 * @param utOrgDTO
	 * @return
	 * @throws ServiceException
	 */
	public void deleteByOrganizationOid(Long organizationOid) throws ServiceException;

	/**
	 * 创建单位组织机构关系
	 * @param utUnitDTO
	 * @return
	 * @throws ServiceException
	 */
	public void create(UtUnitDTO utUnitDTO) throws ServiceException;
	
	/**
	 * 修改单位组织机构关系
	 * @param utOrgDTO
	 * @return
	 * @throws ServiceException
	 */
	public void update(UtUnitDTO utUnitDTO) throws ServiceException;

}
