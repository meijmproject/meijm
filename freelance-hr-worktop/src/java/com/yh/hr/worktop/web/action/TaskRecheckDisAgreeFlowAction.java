package com.yh.hr.worktop.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.worktop.facade.impl.TaskRecheckDisAgreeFlowFacade;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 复核不同意业务公共Action
 * @author	zhangqp
 * @version	1.0,	16/11/01
 */
public class TaskRecheckDisAgreeFlowAction extends TaskFlowBaseAction
{
	private  TaskRecheckDisAgreeFlowFacade taskRecheckDisAgreeFlowFacade =(TaskRecheckDisAgreeFlowFacade) SpringBeanUtil.getBean("taskRecheckDisAgreeFlowFacade");
	
	/**
	 * 复核不同意业务信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitRecheckDisAgree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		return super.submitRecheckDisAgree(mapping, form, request, response, taskRecheckDisAgreeFlowFacade);
	}
}
