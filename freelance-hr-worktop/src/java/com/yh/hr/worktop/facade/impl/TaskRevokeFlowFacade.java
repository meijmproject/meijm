package com.yh.hr.worktop.facade.impl;

import com.yh.hr.worktop.factory.TaskRevokeFactory;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.component.task.service.TaskRevokeService;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.hr.worktop.util.TaskWorkTopConstants;
import com.yh.platform.core.exception.ServiceException;


/**
 * 
 *@description		默认撤回业务工作台Facade实现
 *@author            liuhw
 *@created           2016-08-31
 *@version           1.0
 *
 */
public class TaskRevokeFlowFacade
{
	
	/**
	 * 撤回
	 * @param LinkDTO
	 * @throws ServiceException
	 */
	public void submitRevoke(LinkDTO linkDTO) throws ServiceException 
	{
		linkDTO.setDefFlowExpressName(TaskWorkTopConstants.FLOW_EXPRESSION_KEY_BIZRECALL);
		if(StringUtils.isEmpty(linkDTO.getDefFlowExpress()))
		{
			linkDTO.setDefFlowExpress(TaskWorkTopConstants.FLOW_EXPRESSION_VALUE_BIZRECALL_Y);
		}
		TaskRevokeService TaskRevokeService = TaskRevokeFactory.getBizRevokeWorktopService(linkDTO);
		TaskRevokeService.revoke();
	}
}
