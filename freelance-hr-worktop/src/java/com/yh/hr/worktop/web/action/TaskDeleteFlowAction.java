package com.yh.hr.worktop.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.worktop.facade.impl.TaskDeleteFlowFacade;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 
 *@description		默认删除业务公共Action
 *@author            liuhw
 *@created           2016-09-01
 *@version           1.0
 *
 */
public class TaskDeleteFlowAction extends TaskFlowBaseAction
{
	private TaskDeleteFlowFacade taskDeleteFlowFacade =(TaskDeleteFlowFacade) SpringBeanUtil.getBean("taskDeleteFlowFacade");
	
	protected TaskDeleteFlowFacade getTaskDeleteFlowFacade() {
		return taskDeleteFlowFacade;
	}

	protected void setTaskDeleteFlowFacade(TaskDeleteFlowFacade taskDeleteFlowFacade)
	{
		this.taskDeleteFlowFacade = taskDeleteFlowFacade;
	}
	
	/**
	 * 删除业务信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		return super.submitDelete(mapping, form, request, response, taskDeleteFlowFacade);
	}
}
