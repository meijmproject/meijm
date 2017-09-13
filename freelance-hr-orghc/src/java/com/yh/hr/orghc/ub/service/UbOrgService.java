package com.yh.hr.orghc.ub.service;

import java.util.List;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.platform.core.exception.ServiceException;

public interface UbOrgService {

	/**
	 * 获取内设机构信息
	 * @param orgOid
	 * @return UbOrg
     * @throws ServiceException 
	 */
	public UbOrgDTO getUbOrgDTOById(Long orgOid) throws ServiceException;

	/**
	 * 根据单位OID获取内设机构列表
	 * @param unitOid
	 * @return List<UbOrg>
     * @throws ServiceException 
	 */
	public List<UbOrgDTO> listByUnitOid(Long unitOid) throws ServiceException;
	
	/**
	 * 根据单位OID、机构状态获取内设机构列表
	 * @param unitOid
	 * @return List<UbOrg>
     * @throws ServiceException 
	 */
	public List<UbOrgDTO> listByUnitOidAndStatus(Long unitOid,String orgStatus) throws ServiceException;

	/**
	 * 新增内设机构
	 * @param ubOrgDTO
     * @throws ServiceException 
	 */
	public Long createOrgInfo(UbOrgDTO ubOrgDTO) throws ServiceException;

	/**
	 * 修改内设机构
	 * @param ubOrgDTO
     * @throws ServiceException 
	 */
	public void updateOrgInfo(UbOrgDTO ubOrgDTO) throws ServiceException;

	/**
	 * 删除内设机构
	 * @param orgOid
     * @throws ServiceException 
	 */
	public void deleteOrgInfo(Long orgOid) throws ServiceException;

}
