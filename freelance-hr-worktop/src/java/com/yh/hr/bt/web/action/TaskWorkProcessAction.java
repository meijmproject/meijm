package com.yh.hr.bt.web.action;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.yh.hr.bt.facade.TaskWorkProcessFacade;
import com.yh.hr.component.mtree.dto.MtMenuDto;
import com.yh.hr.component.mtree.facade.JhdMtMenuFacade;
import com.yh.hr.res.bt.dto.BtLogDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 *@description		默认 退回业务公共Action
 *@author            liuhw
 *@created           2016-09-01
 *@version           1.0
 *
 */
public class TaskWorkProcessAction extends BaseAction
{
	private  TaskWorkProcessFacade taskWorkProcessFacade =(TaskWorkProcessFacade) SpringBeanUtil.getBean("taskWorkProcessFacade");
	private JhdMtMenuFacade jhdMtMenuFacade = (JhdMtMenuFacade) SpringBeanUtil.getBean("jhdMtMenuFacade");
	public TaskWorkProcessFacade getTaskWorkProcessFacade() {
		return taskWorkProcessFacade;
	}
	public void setTaskWorkProcessFacade(TaskWorkProcessFacade taskWorkProcessFacade) {
		this.taskWorkProcessFacade = taskWorkProcessFacade;
	}
	/**
	 * 
	 *@description		查看单位版业务办理过程
	 *@author            liuhw
	 *@created           2016-09-01
	 *@version           1.0
	 *
	 */
	public ActionForward findBizWorkProcess(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			Long taskOid = Long.valueOf(request.getParameter("taskOid"));
			List<BtLogDTO> list = taskWorkProcessFacade.findBizWorkProcess(taskOid);
			request.setAttribute("list", list);
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到查看业务办理过程页面");
			return mapping.findForward("error");
		}
	}
	/**
	 * 
	 *@description		跳转到查看单位信息页面
	 *@author            liuhw
	 *@created           2016-09-01
	 *@version           1.0
	 *
	 */
	public ActionForward goToViewUnit(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			String unitOid = request.getParameter("unitOid");
			request.setAttribute("unitOid", unitOid);
			String menuCode = request.getParameter("menuCode");
			request.setAttribute("menuCode", menuCode);
			String index = request.getParameter("index");
			request.setAttribute("index", index);
			String pageNo = request.getParameter("pageNo");
			if(null != pageNo){				
				request.setAttribute("pageNo", pageNo);
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到查看单位信息页面");
			return mapping.findForward("error");
		}
	}
	/**
	 * 
	 *@description		查看管理版业务办理过程
	 *@author            liuhw
	 *@created           2016-09-01
	 *@version           1.0
	 *
	 */
	public ActionForward findAuditWorkProcess(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			Long taskOid = Long.valueOf(request.getParameter("taskOid"));
			List<BtLogDTO> list = taskWorkProcessFacade.findAuditWorkProcess(taskOid);
			request.setAttribute("list", list);
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到查看业务办理过程页面");
			return mapping.findForward("error");
		}
	}
	/**
	 * 
	 *@description		查看代办业务下拉单位
	 *@author            liuhw
	 *@created           2016-09-01
	 *@version           1.0
	 *
	 */
	public ActionForward findSelectUnit(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			String menuCode = request.getParameter("menuCode");
			List<JSONObject> list = taskWorkProcessFacade.findSelectUnit(menuCode);
			request.setAttribute("list", list);
			response.getWriter().print(list.toString());
			return null;
		} catch (Exception e) {
			handleException(request, e, "查看已办查询单位列表");
			return null;
		}
	}
	/**
	 * 
	 *@description		查看已办业务下拉单位
	 *@author            liuhw
	 *@created           2016-09-01
	 *@version           1.0
	 *
	 */
	public ActionForward findAllSelectUnit(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			List<UtUnitDTO> list = taskWorkProcessFacade.findAllSelectUnit();
			if(CollectionUtils.isEmpty(list)) return null;
			JSONArray arr= new JSONArray();
			for(UtUnitDTO utUnitDTO:list){
				JSONObject json=new JSONObject();
				json.put("unitOid", utUnitDTO.getUnitOid());
				json.put("unitName", utUnitDTO.getUnitName());
				arr.element(json);
			}
			response.getWriter().print(arr.toString());
			return null;
		} catch (Exception e) {
			handleException(request, e, "查看已办查询单位列表");
			return null;
		}
	}
	/**
	 * 
	 *@description		查看事项下拉
	 *@author           cheny
	 *@created           2016-09-01
	 *@version           1.0
	 *
	 */
	public ActionForward findAllMenuTitle(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			List<MtMenuDto> list = jhdMtMenuFacade.findAllMenuTitle();
			if(CollectionUtils.isEmpty(list)) return null;
			JSONArray arr= new JSONArray();
			for(MtMenuDto mtMenuDto:list){
				JSONObject json=new JSONObject();
				json.put("menuCode", mtMenuDto.getMenuCode());
				json.put("menuTitle", mtMenuDto.getMenuTitle());
				arr.element(json);
			}
			response.getWriter().print(arr.toString());
			return null;
		} catch (Exception e) {
			handleException(request, e, "查看事项下拉列表");
			return null;
		}
	}
}
