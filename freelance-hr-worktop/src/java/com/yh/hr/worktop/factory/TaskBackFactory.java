package com.yh.hr.worktop.factory;

import com.yh.hr.component.task.service.TaskNextService;
import com.yh.hr.component.task.service.impl.TaskNextAbstractService;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 *	业务事项退回工厂 - Factory
 *	@author		liuhw
 *	@created	2016-08-31
 */
public class TaskBackFactory
{
	private final static String PREFIX_REPORTTASK = "backTask";	// 退回
	
	/**
	 *	业务事项退回
	 *	@param	bizLinkDTO					审核信息
	 *	@return bizReportWorktopService		业务退回工作台
	 *	@throws ServiceException
	 */
	public static TaskNextService getBizNextWorktopService(LinkDTO linkDTO) throws ServiceException
	{
		String beanId = PREFIX_REPORTTASK + linkDTO.getItemCode() + linkDTO.getItemNodeCode();
		TaskNextAbstractService TaskNextAbstractService = (TaskNextAbstractService) SpringBeanUtil.getBean(beanId);
		TaskNextAbstractService.setLinkDTO(linkDTO);
		return (TaskNextService) TaskNextAbstractService;
	}

}