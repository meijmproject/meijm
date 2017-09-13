package com.yh.component.validate.service;

import com.yh.platform.core.exception.ServiceException;




/**
 *@description 检查服务接口
 *
 *@author      chencr	
 *@created     2013-04-11
 *@version     1.0
 *
 */
public interface YhValidateService 
{

	/**
	 * 检查服务
	 * @param methodName
	 * @param className
	 * @throws ServiceException
	 */
	public void check(String methodName, String className) throws ServiceException;
	
}
