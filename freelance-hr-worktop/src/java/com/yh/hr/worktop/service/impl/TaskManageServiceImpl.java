package com.yh.hr.worktop.service.impl;

import com.yh.hr.worktop.factory.TaskBackFactory;
import com.yh.hr.worktop.factory.TaskStopFactory;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.component.task.service.TaskCreateService;
import com.yh.hr.component.task.service.TaskDeleteService;
import com.yh.hr.component.task.service.TaskNextService;
import com.yh.hr.component.task.service.TaskRevokeService;
import com.yh.hr.component.task.service.TaskStopService;
import com.yh.hr.res.bt.dto.FlowDTO;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.hr.worktop.factory.TaskCreateFactory;
import com.yh.hr.worktop.factory.TaskDeleteFactory;
import com.yh.hr.worktop.factory.TaskRecheckFactory;
import com.yh.hr.worktop.factory.TaskReportFactory;
import com.yh.hr.worktop.factory.TaskRevokeFactory;
import com.yh.hr.worktop.util.TaskWorkTopConstants;
import com.yh.platform.core.exception.ServiceException;


public class TaskManageServiceImpl 
{
	/**
	 * 创建业务
	 * @param flowDTO
	 * @throws ServiceException
	 */
	public static void submitCreate(FlowDTO flowDTO)throws ServiceException
	{
		TaskCreateService TaskCreateService = TaskCreateFactory.getBizCreateWorktopService(flowDTO);
		TaskCreateService.create();
	}
	
	/**
	 * 删除业务
	 * @param linkDTO
	 * @throws ServiceException
	 */
	public static void submitDelete(LinkDTO linkDTO) throws ServiceException 
	{
		TaskDeleteService TaskDeleteService = TaskDeleteFactory.getBizDeleteWorktopService(linkDTO);
		TaskDeleteService.delete();
	}
	
	/**
	 * 上报业务
	 * @param linkDTO
	 * @throws ServiceException
	 */
	public static void submitReported(LinkDTO linkDTO)  throws ServiceException 
	{
		linkDTO.setDefFlowExpressName(TaskWorkTopConstants.FLOW_EXPRESSION_KEY_REPORT);
		if(StringUtils.isEmpty(linkDTO.getDefFlowExpress()))
		{
			linkDTO.setDefFlowExpress(TaskWorkTopConstants.FLOW_EXPRESSION_VALUE_REPORT_Y);
		}
		TaskNextService TaskNextService = TaskReportFactory.getBizNextWorktopService(linkDTO);
		TaskNextService.next();
	}
	
	/**
	 * 撤回业务
	 * @param linkDTO
	 * @throws ServiceException
	 */
	public static void submitRevoke(LinkDTO linkDTO) throws ServiceException 
	{
		linkDTO.setDefFlowExpressName(TaskWorkTopConstants.FLOW_EXPRESSION_KEY_BIZRECALL);
		linkDTO.setDefFlowExpress(TaskWorkTopConstants.FLOW_EXPRESSION_VALUE_BIZRECALL_Y);
		TaskRevokeService TaskRevokeService = TaskRevokeFactory.getBizRevokeWorktopService(linkDTO);
		TaskRevokeService.revoke();
	}
	
	/**
	 * 终止业务
	 * @param linkDTO
	 * @throws ServiceException
	 */
	public static void submitStop(LinkDTO linkDTO) throws ServiceException 
	{
		linkDTO.setDefFlowExpressName(TaskWorkTopConstants.FLOW_EXPRESSION_KEY_INTERRUPT);
		linkDTO.setDefFlowExpress(TaskWorkTopConstants.FLOW_EXPRESSION_VALUE_INTERRUPT_N);
		TaskStopService TaskStopService = TaskStopFactory.getBizStopWorktopService(linkDTO);
		TaskStopService.stop();
	}
	
	/**
	 * 复核同意
	 * @param linkDTO
	 * @throws ServiceException
	 */
	public static void submitRecheckAgree(LinkDTO linkDTO) throws ServiceException 
	{
		linkDTO.setDefFlowExpressName(TaskWorkTopConstants.FLOW_EXPRESSION_KEY_CHECK);
		linkDTO.setDefFlowExpress(TaskWorkTopConstants.FLOW_EXPRESSION_VALUE_CHECK_Y);
		TaskNextService TaskNextService = TaskRecheckFactory.getBizNextWorktopService(linkDTO);
		TaskNextService.next();
	}
	
	/**
	 * 复核退回
	 * @param linkDTO
	 * @throws ServiceException
	 */
	public static void submitRecheckBack(LinkDTO linkDTO) throws ServiceException 
	{
		linkDTO.setDefFlowExpressName(TaskWorkTopConstants.FLOW_EXPRESSION_KEY_CHECK);
		linkDTO.setDefFlowExpress(TaskWorkTopConstants.FLOW_EXPRESSION_VALUE_CHECK_T);
		TaskNextService TaskNextService = TaskBackFactory.getBizNextWorktopService(linkDTO);
		TaskNextService.next();
	}
}
