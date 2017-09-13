package com.yh.hr.component.task.service.impl;

import com.yh.platform.core.exception.ServiceException;

/**
 * 
 *@description		默认终止业务信息通用实现类
 *
 *@author            liuhw
 *@created           2016-8-30
 *@version           1.0
 *
 */
public class TaskStopDefaultServiceImpl extends TaskStopAbstractService{

	
	/*
	 * (non-Javadoc)
	 * @see gov.gdpos.bizcore.worktop.service.impl.BizStopAbstractService#nextExecute()
	 */
	public void nextExecute() throws ServiceException 
	{
		//默认nextExecute方法什么都不做，如果终止有实现nextExecute方法，则写自己的实现类，并继承BizStopAbstractService
	}
	
}
