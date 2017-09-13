package com.yh.hr.worktop.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.worktop.facade.impl.TaskCreateFlowFacade;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 
 *@description		默认 创建业务公共Action
 *@author            liuhw
 *@created           2016-09-01
 *@version           1.0
 *
 */
public class TaskCreateFlowAction extends TaskFlowBaseAction
{
	private  TaskCreateFlowFacade taskCreateFlowFacade =(TaskCreateFlowFacade) SpringBeanUtil.getBean("taskCreateFlowFacade");
	
	
	protected TaskCreateFlowFacade getTaskCreateFlowFacade() {
		return taskCreateFlowFacade;
	}

	protected void setTaskCreateFlowFacade(TaskCreateFlowFacade taskCreateFlowFacade)
	{
		this.taskCreateFlowFacade = taskCreateFlowFacade;
	}

	/**
	 * 创建业务信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitCreate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		return super.submitCreate(mapping, form, request, response, taskCreateFlowFacade);
	}
}
