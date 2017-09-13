package com.yh.hr.worktop.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.worktop.facade.impl.TaskBackFlowFacade;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 
 *@description		默认 退回业务公共Action
 *@author            liuhw
 *@created           2016-09-01
 *@version           1.0
 *
 */
public class TaskBackFlowAction extends TaskFlowBaseAction
{
	private TaskBackFlowFacade taskBackFlowFacade =(TaskBackFlowFacade) SpringBeanUtil.getBean("taskBackFlowFacade");
	
	protected TaskBackFlowFacade getTaskBackFlowFacade() {
		return taskBackFlowFacade;
	}

	protected void setTaskBackFlowFacade(TaskBackFlowFacade taskBackFlowFacade)
	{
		this.taskBackFlowFacade = taskBackFlowFacade;
	}

	/**
	 * 复核退回
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitRecheckBack(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return super.submitRecheckBack(mapping, form, request, response, taskBackFlowFacade);
	}
}
