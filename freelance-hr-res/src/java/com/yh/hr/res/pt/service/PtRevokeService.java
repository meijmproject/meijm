package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtRevokeVacationDto;
import com.yh.platform.core.exception.ServiceException;

/**
 * 销假信息Service
 * @author chenjl
 * @date 2017-04-26
 * @version 1.0
 */
public interface PtRevokeService {

	/**
	 * 新增
	 */
	public Long create(PtRevokeVacationDto serdto)throws ServiceException;

	/**
	 * 查询
	 */	
	public PtRevokeVacationDto get(Long bizRevokeVacationOid) throws ServiceException;

	/**
	 * 更新
	 */
	public void update(PtRevokeVacationDto serdto) throws ServiceException;

	/**
	 * 删除
	 */	
	public void delete(Long bizRevokeVacationOid) throws ServiceException;

	/**
	 * 根据vacationOid查询业务销假信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PtRevokeVacationDto getPtRevokeByVacationOid(Long vacationOid) throws ServiceException;
	
}
