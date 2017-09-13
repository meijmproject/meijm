package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtWorkInjuryVacationDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 工伤假业务信息service接口
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 */
public interface PtWorkInjuryVacationService {

	/**
	 * 创建工伤假业务信息
	 * @param ptWorkInjuryVacationDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtWorkInjuryVacationDTO ptWorkInjuryVacationDTO) throws ServiceException;
	
	/**
	 * 修改工伤假业务信息
	 * @param ptWorkInjuryVacationDTO
	 * @throws ServiceException
	 */
	public void update(PtWorkInjuryVacationDTO ptWorkInjuryVacationDTO) throws ServiceException;
	
	/**
	 * 通过主键OID删除工伤假业务信息
	 * @param vacationOid
	 * @throws ServiceException
	 */
	public void delete(Long vacationOid) throws ServiceException;
	
	/**
	 * 通过主键OID获取工伤假业务信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PtWorkInjuryVacationDTO get(Long vacationOid) throws ServiceException;
}
