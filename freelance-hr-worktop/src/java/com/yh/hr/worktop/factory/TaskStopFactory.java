package com.yh.hr.worktop.factory;

import com.yh.hr.component.task.service.TaskStopService;
import com.yh.hr.component.task.service.impl.TaskStopAbstractService;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 *	业务事项终止工厂- Factory
 *	@author		liuhw
 *	@created	2016-08-31
 */
public class TaskStopFactory
{
	private final static String PREFIX_STOPTASK		= "stopTask";		//终止
	
	/**
	 *	业务事项终止
	 *	@param	bizLinkDTO					审核信息
	 *	@return bizRejectWorktopService		业务否决工作台
	 *	@throws ServiceException
	 */
	public static TaskStopService getBizStopWorktopService(LinkDTO linkDTO) throws ServiceException
	{
		String beanId = PREFIX_STOPTASK + linkDTO.getItemCode() + linkDTO.getItemNodeCode();
		TaskStopAbstractService TaskStopAbstractService = (TaskStopAbstractService) SpringBeanUtil.getBean(beanId);
		TaskStopAbstractService.setLinkDTO(linkDTO);
		return (TaskStopService) TaskStopAbstractService;
	}
}