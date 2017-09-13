package com.yh.hr.res.pb.service;

import com.yh.hr.res.pb.dto.PbWorkInjuryVacationDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 工伤假基础信息service接口
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 */
public interface PbWorkInjuryVacationService {

	/**
	 * 创建工伤假基础信息
	 * @param pbWorkInjuryVacationDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PbWorkInjuryVacationDTO pbWorkInjuryVacationDTO) throws ServiceException;
	
	/**
	 * 修改工伤假基础信息
	 * @param pbWorkInjuryVacationDTO
	 * @throws ServiceException
	 */
	public void update(PbWorkInjuryVacationDTO pbWorkInjuryVacationDTO) throws ServiceException;
	
	/**
	 * 通过主键OID删除工伤假基础信息
	 * @param vacationOid
	 * @throws ServiceException
	 */
	public void delete(Long vacationOid) throws ServiceException;
	
	/**
	 * 通过主键OID获取工伤假基础信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PbWorkInjuryVacationDTO get(Long vacationOid) throws ServiceException;
}
