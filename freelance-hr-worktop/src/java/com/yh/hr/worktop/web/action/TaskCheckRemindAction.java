package com.yh.hr.worktop.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.worktop.facade.impl.TaskCheckFacade;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 
 *@description		默认提示信息检查公共Action
 *@author            liuhw
 *@created           2016-09-01
 *@version           1.0
 *
 */
public class TaskCheckRemindAction extends TaskFlowBaseAction
{
	private TaskCheckFacade taskCheckFacade =(TaskCheckFacade) SpringBeanUtil.getBean("taskCheckFacade");
	
	/**
	 * 上报业务信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkRemind(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		return super.checkRemind(mapping, form, request, response, taskCheckFacade);
	}
}
