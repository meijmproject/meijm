package com.yh.hr.worktop.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.worktop.facade.impl.TaskRevokeFlowFacade;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 
 *@description		默认撤回业务公共Action
 *@author            liuhw
 *@created           2016-09-01
 *@version           1.0
 *
 */
public class TaskRevokeFlowAction extends TaskFlowBaseAction
{
	private  TaskRevokeFlowFacade taskRevokeFlowFacade =(TaskRevokeFlowFacade) SpringBeanUtil.getBean("taskRevokeFlowFacade");
	
	protected TaskRevokeFlowFacade getTaskRevokeFlowFacade() {
		return taskRevokeFlowFacade;
	}
	protected void setTaskRevokeFlowFacade(TaskRevokeFlowFacade taskRevokeFlowFacade)
	{
		this.taskRevokeFlowFacade = taskRevokeFlowFacade;
	}
	/**
	 * 撤回业务信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		return super.submitRevoke(mapping, form, request, response, taskRevokeFlowFacade);
	}
}
