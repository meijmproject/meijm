package com.yh.hr.component.task.service;

import com.yh.platform.core.exception.ServiceException;

/**
 *	业务撤回工作台 - Service
 *	@author		liuhw
 *	@created	2016-08-30
 */
public interface TaskRevokeService
{
	/**
	 *	撤回
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	public void revoke() throws ServiceException;
}