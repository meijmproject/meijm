package com.yh.hr.worktop.factory;

import com.yh.hr.component.task.service.TaskCreateService;
import com.yh.hr.component.task.service.impl.TaskCreateAbstractService;
import com.yh.hr.res.bt.dto.FlowDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 *	业务事项创建工厂- Factory
 *	@author		liuhw
 *	@created	2016-08-31
 */
public class TaskCreateFactory
{
	private final static String PREFIX_CREATETASK = "createTask";	// 创建
	
	/**
	 *	业务事项创建
	 *	@param	bizFlowDTO					事项信息
	 *	@return bizCreateWorktopService		业务创建工作台
	 *	@throws ServiceException
	 */
	public static TaskCreateService getBizCreateWorktopService(FlowDTO flowDTO) throws ServiceException
	{
		String beanId = PREFIX_CREATETASK + flowDTO.getItemCode() + flowDTO.getItemNodeCode();
		TaskCreateAbstractService TaskCreateAbstractService = (TaskCreateAbstractService) SpringBeanUtil.getBean(beanId);
		TaskCreateAbstractService.setFlowDTO(flowDTO);
		return (TaskCreateService) TaskCreateAbstractService;
	}
}