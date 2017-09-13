package com.yh.hr.worktop.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.worktop.facade.impl.TaskReportFlowFacade;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.approvalnode.bo.ApprovalNodeDetail;
import com.yh.component.approvalnode.facade.ApprovalNodeFacade;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 
 *@description		默认上报业务公共Action
 *@author            liuhw
 *@created           2016-09-01
 *@version           1.0
 *
 */
public class TaskReportFlowAction extends TaskFlowBaseAction
{
	private TaskReportFlowFacade taskReportFlowFacade =(TaskReportFlowFacade) SpringBeanUtil.getBean("taskReportFlowFacade");
	private  ApprovalNodeFacade approvalNodeFacade = (ApprovalNodeFacade) SpringBeanUtil.getBean("approvalNodeFacade");
	
	protected TaskReportFlowFacade getTaskReportFlowFacade() {
		return taskReportFlowFacade;
	}

	protected void setTaskReportFlowFacade(TaskReportFlowFacade taskReportFlowFacade)
	{
		this.taskReportFlowFacade = taskReportFlowFacade;
	}


	/**
	 * 跳转业务上报审批部门选择页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 **/
	public ActionForward goBizDefaultReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String applyName = request.getParameter("applyName");
			String bizTaskOid = request.getParameter("bizTaskOid");
			String itemCode = request.getParameter("itemCode");
			String itemNodeCode = request.getParameter("itemNodeCode");
			List<ApprovalNodeDetail> nodeList = approvalNodeFacade.getApprovalNodeListByItemCodeAndItemNodeCode(itemCode, itemNodeCode);
			request.setAttribute("bizTaskOids", bizTaskOid);
			request.setAttribute("itemCodes", itemCode);
			request.setAttribute("applyNames", applyName==null?"":"undefined".equals(applyName)?"":applyName);
			request.setAttribute("itemNodeCodes", itemNodeCode);
			request.setAttribute("nodeList", nodeList);
		} catch (Exception se) {
			handleException(request, se, "");
			return mapping.getInputForward();
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 上报业务信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitReported(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		return super.submitReported(mapping, form, request, response, taskReportFlowFacade);
	}
}
