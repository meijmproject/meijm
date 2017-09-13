package com.yh.hr.worktop.factory;

import com.yh.hr.component.task.service.TaskRevokeService;
import com.yh.hr.component.task.service.impl.TaskRevokeAbstractService;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 *	业务事项撤回工厂 - Factory
 *	@author		liuhw
 *	@created	2016-08-31
 */
public class TaskRevokeFactory
{
	private final static String PREFIX_REVOKETASK = "revokeTask";	// 撤回
	
	/**
	 *	业务事项撤回
	 *	@param	bizLinkDTO					审核信息
	 *	@return bizRecallWorktopService		业务上报工作台
	 *	@throws ServiceException
	 */
	public static TaskRevokeService getBizRevokeWorktopService(LinkDTO linkDTO) throws ServiceException
	{
		String beanId = PREFIX_REVOKETASK + linkDTO.getItemCode() + linkDTO.getItemNodeCode();
		TaskRevokeAbstractService TaskRevokeAbstractService = (TaskRevokeAbstractService) SpringBeanUtil.getBean(beanId);
		TaskRevokeAbstractService.setLinkDTO(linkDTO);
		return (TaskRevokeService) TaskRevokeAbstractService;
	}
}