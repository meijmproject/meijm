package com.yh.hr.worktop.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.worktop.facade.impl.TaskStopFlowFacade;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 
 *@description		默认 终止业务公共Action
 *@author            liuhw
 *@created           2016-09-01
 *@version           1.0
 *
 */
public class TaskStopFlowAction extends TaskFlowBaseAction
{
	private TaskStopFlowFacade taskStopFlowFacade =(TaskStopFlowFacade) SpringBeanUtil.getBean("taskStopFlowFacade");
	
	protected TaskStopFlowFacade getTaskStopFlowFacade() {
		return taskStopFlowFacade;
	}


	protected void setTaskStopFlowFacade(TaskStopFlowFacade taskStopFlowFacade)
	{
		this.taskStopFlowFacade = taskStopFlowFacade;
	}

	
	/**
	 * 业务终止
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitStop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return super.submitStop(mapping, form, request, response, taskStopFlowFacade);
	}
}
