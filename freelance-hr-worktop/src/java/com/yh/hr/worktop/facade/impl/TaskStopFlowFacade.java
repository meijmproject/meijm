package com.yh.hr.worktop.facade.impl;

import org.apache.commons.lang.StringUtils;

import com.yh.hr.component.task.service.TaskStopService;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.hr.worktop.factory.TaskStopFactory;
import com.yh.hr.worktop.util.TaskWorkTopConstants;
import com.yh.platform.core.exception.ServiceException;


/**
 * 
 *@description		默认终止业务工作台Facade实现
 *@author            liuhw
 *@created           2016-08-31
 *@version           1.0
 *
 */
public class TaskStopFlowFacade
{
	/**
	 * 终止
	 * @param LinkDTO
	 * @throws ServiceException
	 */
	public void submitStop(LinkDTO linkDTO) throws ServiceException 
	{
		linkDTO.setDefFlowExpressName(TaskWorkTopConstants.FLOW_EXPRESSION_KEY_INTERRUPT);
		if(StringUtils.isEmpty(linkDTO.getDefFlowExpress()))
		{
			linkDTO.setDefFlowExpress(TaskWorkTopConstants.FLOW_EXPRESSION_VALUE_INTERRUPT_N);
		}
		TaskStopService TaskStopService = TaskStopFactory.getBizStopWorktopService(linkDTO);
		TaskStopService.stop();
	}
}
