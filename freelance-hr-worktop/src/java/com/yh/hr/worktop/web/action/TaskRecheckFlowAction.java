package com.yh.hr.worktop.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.approvalnode.bo.ApprovalNodeDetail;
import com.yh.component.approvalnode.facade.ApprovalNodeFacade;
import com.yh.hr.worktop.dto.UnitDTO;
import com.yh.hr.worktop.facade.impl.TaskRecheckFlowFacade;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;

/**
 * 
 *@description		默认复核业务公共Action
 *@author            liuhw
 *@created           2016-09-01
 *@version           1.0
 *
 */
public class TaskRecheckFlowAction extends TaskFlowBaseAction
{
	private  TaskRecheckFlowFacade taskRecheckFlowFacade =(TaskRecheckFlowFacade) SpringBeanUtil.getBean("taskRecheckFlowFacade");
	private  ApprovalNodeFacade approvalNodeFacade = (ApprovalNodeFacade) SpringBeanUtil.getBean("approvalNodeFacade");
	
	protected TaskRecheckFlowFacade getTaskRecheckFlowFacade() {
		return taskRecheckFlowFacade;
	}

	protected void setTaskRecheckFlowFacade(TaskRecheckFlowFacade taskRecheckFlowFacade)
	{
		this.taskRecheckFlowFacade = taskRecheckFlowFacade;
	}
	
	/**
	 * 复核业务信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitRecheckAgree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		return super.submitRecheckAgree(mapping, form, request, response, taskRecheckFlowFacade);
	}
	
	/**
	 * 跳转录入审批意见
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 **/
	public ActionForward goBizDefaultRecheckAgree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String applyName = request.getParameter("applyName");
			String bizTaskOid = request.getParameter("bizTaskOid");
			String itemCode = request.getParameter("itemCode");
			String itemNodeCode = request.getParameter("itemNodeCode");
			request.setAttribute("bizTaskOids", bizTaskOid);
			request.setAttribute("itemCodes", itemCode);
			request.setAttribute("applyNames", applyName==null?"":"undefined".equals(applyName)?"":applyName);
			request.setAttribute("itemNodeCodes", itemNodeCode);
		} catch (Exception se) {
			handleException(request, se, "");
			return mapping.getInputForward();
		}
		return mapping.findForward("success");
	}
	

	/**
	 * 跳转录入审批意见（需选择审批环节）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 **/
	public ActionForward goBizDefaultRecheckAgreeForNodeSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * 跳转到单位录入审核意见
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 **/
	public ActionForward goBizDefaultCheckAgreeUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//接收单位名称信息
			String unitNames = request.getParameter("unitNames");
			//列表
			List<String> unitName = (List<String>)StringUtil.strToObj(StringUtils.split(unitNames, StringUtil.COMMA), String.class);
			String bizTaskOids = request.getParameter("bizTaskOids");
			
			List<Long> bizTaskOid = (List<Long>)StringUtil.strToObj(StringUtils.split(bizTaskOids, StringUtil.COMMA), Long.class);
			//暂存放单位信息
			List<UnitDTO> unitDTOs = new ArrayList<UnitDTO>();
			if(CollectionUtils.isNotEmpty(unitName)||CollectionUtils.isNotEmpty(bizTaskOid))
				{
					for(int i=0;i<bizTaskOid.size();i++)
					{
						UnitDTO data = new UnitDTO();
						data.setTaskOid(bizTaskOid.get(i));
						data.setUnitName(unitName.get(i));
						unitDTOs.add(data);
					}
	
				}
			
			request.setAttribute("unitDTOs", unitDTOs);
			request.setAttribute("itemCodes", request.getParameter("itemCodes"));
			request.setAttribute("itemNodeCodes", request.getParameter("itemNodeCodes"));
		} catch (Exception se) {
			handleException(request, se, "");
			return mapping.getInputForward();
		}
		
		return mapping.findForward("success");
	}
}
