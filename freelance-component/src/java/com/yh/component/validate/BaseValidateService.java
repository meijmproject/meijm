package com.yh.component.validate;

import com.yh.platform.core.exception.ServiceException;




/**
 * 
 *@description 检查接口
 *
 *@author            chencr
 *@created           2013-03-08
 *@version           1.0
 *
 */
public interface BaseValidateService 
{

	/**
	 * 规则检查
	 * @throws ServiceException
	 * 
	 * */
	public void validate() throws ServiceException; 
}
