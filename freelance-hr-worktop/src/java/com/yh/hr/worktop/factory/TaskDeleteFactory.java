package com.yh.hr.worktop.factory;

import com.yh.hr.component.task.service.TaskDeleteService;
import com.yh.hr.component.task.service.impl.TaskDeleteAbstractService;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 *	业务事项删除工厂 - Factory
 *	@author		liuhw
 *	@created	2016-09-01
 */
public class TaskDeleteFactory
{
	private final static String PREFIX_DELETETASK	= "deleteTask";		// 删除
	
	/**
	 *	业务事项删除
	 *	@param	bizLinkDTO					审核信息
	 *	@return bizRejectWorktopService		业务否决工作台
	 *	@throws ServiceException
	 */
	public static TaskDeleteService getBizDeleteWorktopService(LinkDTO linkDTO) throws ServiceException
	{
		String beanId = PREFIX_DELETETASK + linkDTO.getItemCode() + linkDTO.getItemNodeCode();
		TaskDeleteAbstractService TaskDeleteAbstractService = (TaskDeleteAbstractService) SpringBeanUtil.getBean(beanId);
		TaskDeleteAbstractService.setLinkDTO(linkDTO);
		return (TaskDeleteService) TaskDeleteAbstractService;
	}
}