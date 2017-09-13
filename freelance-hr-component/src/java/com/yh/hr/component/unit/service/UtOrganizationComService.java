package com.yh.hr.component.unit.service;

import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;

public interface UtOrganizationComService {

	/**
	 * 同步创建机构信息
	 * @param utUnitDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public void synchroniseCreateUnitInfo(UtUnitDTO utUnitDTO) throws ServiceException;
	
	/**
	 * 同步更新机构信息
	 * @param utUnitDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public void synchroniseUpdateUnitInfo(UtUnitDTO utUnitDTO) throws ServiceException;

	/**
	 * 同步创建内设机构信息
	 * @param utUnitDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public void synchroniseCreateOrgInfo(UtOrgDTO utOrgDTO) throws ServiceException;

	/**
	 * 同步更新内设机构信息
	 * @param utUnitDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public void synchroniseUpdateOrgInfo(UtOrgDTO utOrgDTO) throws ServiceException;

	/**
	 * 同步删除内设机构信息
	 * @param utUnitDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public void synchroniseDeleteOrgInfo(Long orgOid) throws ServiceException;
	
	/**
	 * 查找单位主管单位
	 * @param unitOid
	 * @return UtUnitDTO
	 * @throws ServiceException
	 */
	public UtUnitDTO getAdminUnit(Long unitOid) throws ServiceException;

}
