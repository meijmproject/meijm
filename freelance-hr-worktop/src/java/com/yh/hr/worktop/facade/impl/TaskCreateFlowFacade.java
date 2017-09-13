package com.yh.hr.worktop.facade.impl;

import com.yh.hr.component.task.service.TaskCreateService;
import com.yh.hr.res.bt.dto.FlowDTO;
import com.yh.hr.worktop.factory.TaskCreateFactory;
import com.yh.platform.core.exception.ServiceException;


/**
 * 
 *@description		默认创建业务工作台Facade实现
 *@author            liuhw
 *@created           2016-08-31
 *@version           1.0
 *
 */
public class TaskCreateFlowFacade
{
	/**
	 * 创建业务信息
	 * @param bizFlowDTO
	 * @throws ServiceException
	 */
	public void submitCreate(FlowDTO flowDTO)throws ServiceException
	{
		TaskCreateService TaskCreateService = TaskCreateFactory.getBizCreateWorktopService(flowDTO);
		TaskCreateService.create();
	}
}
