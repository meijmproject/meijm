package com.yh.hr.component.task.service;

import com.yh.platform.core.exception.ServiceException;

/**
 *	业务删除工作台 - Service
 *	@author		liuhw
 *	@created	2016-08-30
 */
public interface TaskDeleteService
{
	/**
	 *	删除
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	public void delete() throws ServiceException;
}