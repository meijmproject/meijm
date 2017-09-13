package com.yh.hr.res.sao.orghc.service;

import java.util.List;
import com.yh.hr.res.sao.orghc.dto.SaoUbOrgDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 内设机构信息查询、维护接口
 * @author	lenghh
 * @version	1.0,	16/09/05
 */

public interface SaoUbOrgService {
	
	/**
	 * 获取内设机构基本信息
	 * @param orgOid
	 * @return SaoUbOrgDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public SaoUbOrgDTO getSaoUbOrgDTO(Long orgOid) throws ServiceException;
	
	/**
	 * 根据单位获取内设机构列表
	 * @param unitOid
	 * @return List<SaoUbOrgDTO>
     * @throws ServiceException 
     * @author lenghh
	 */
	public List<SaoUbOrgDTO> listSaoUbOrgDTOByUnitOid(Long unitOid) throws ServiceException;
	
	/**
	 * 创建内设机构
	 * @param saoUbOrgDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public void createOrgInfo(SaoUbOrgDTO saoUbOrgDTO) throws ServiceException;
	
	/**
	 * 修改内设机构
	 * @param saoUbOrgDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public void updateOrgInfo(SaoUbOrgDTO saoUbOrgDTO) throws ServiceException;
	
	/**
	 * 删除内设机构
	 * @param saoUbOrgDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public void deleteOrgInfo(Long orgOid) throws ServiceException;
}
