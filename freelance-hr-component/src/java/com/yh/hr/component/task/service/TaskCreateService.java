package com.yh.hr.component.task.service;

import com.yh.platform.core.exception.ServiceException;

/**
 *	业务创建接口- Service
 *	@author		liuhw
 *	@created	2016-08-29
 */
public interface TaskCreateService
{
	/**
	 *	创建
	 *	@param	itemCode		事项代码
	 *	@param	bizTaskOid		业务OID
	 *	@throws ServiceException
	 */
	public Long create() throws ServiceException;
}