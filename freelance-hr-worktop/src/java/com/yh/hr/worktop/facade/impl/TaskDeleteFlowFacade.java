package com.yh.hr.worktop.facade.impl;

import com.yh.hr.component.task.service.TaskDeleteService;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.hr.worktop.factory.TaskDeleteFactory;
import com.yh.platform.core.exception.ServiceException;


/**
 * 
 *@description		默认删除业务工作台Facade实现
 *@author            liuhw
 *@created           2016-08-31
 *@version           1.0
 *
 */
public class TaskDeleteFlowFacade
{
	/**
	 * 删除
	 * @param LinkDTO
	 * @throws ServiceException
	 */
	public void submitDelete(LinkDTO linkDTO) throws ServiceException
	{
		TaskDeleteService TaskDeleteService = TaskDeleteFactory.getBizDeleteWorktopService(linkDTO);
		TaskDeleteService.delete();
	}
}
