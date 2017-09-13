package com.yh.hr.component.task.service;

import com.yh.platform.core.exception.ServiceException;

/**
 *	业务流程推动工作台 - Service
 *	@author		liuhw
 *	@created	2016-08-30
 */
public interface TaskNextService
{
	/**
	 *  推动
	 *	@param	bizTaskOid		业务事项OID
	 *	@throws ServiceException
	 */
	public void next() throws ServiceException;
}