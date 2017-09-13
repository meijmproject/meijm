package com.yh.hr.res.sao.orghc.service;

import java.util.List;

import com.yh.hr.res.sao.orghc.dto.SaoUbUnitDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 单位信息查询、维护接口
 * @author	lenghh
 * @version	1.0,	16/09/05
 */

public interface SaoUbUnitService {

	/**
	 * 获取单位基本信息
	 * @param unitOid
	 * @return SaoUbUnitDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public SaoUbUnitDTO getSaoUbUnitDTO(Long unitOid) throws ServiceException;
	
	/**
	 * 获取单位综合信息
	 * @param unitOid
	 * @return SaoUbUnitDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public SaoUbUnitDTO viewUnitInfo(Long unitOid) throws ServiceException;
	
	/**
	 * 更新单位信息
	 * @param saoUbUnitDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public void updateUnitInfo(SaoUbUnitDTO saoUbUnitDTO) throws ServiceException;
	
	/**
	 * 得到主管单位以及其下设单位
	 * @param adminUnitOid
	 * @return
	 * @throws ServiceException
	 */
	public List<Long> findUnitOidsByAdminUnitOid(Long adminUnitOid) throws ServiceException;
}
