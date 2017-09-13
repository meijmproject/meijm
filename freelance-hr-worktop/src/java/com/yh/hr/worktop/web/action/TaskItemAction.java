package com.yh.hr.worktop.web.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.worktop.facade.impl.TaskItemFacade;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.web.action.BaseAction;

import jade.workflow.utils.SpringBeanUtil;
import net.sf.json.JSONObject;

/**
 * 查询办事项列表（工作台右边查询列表） Action
 * @author huw
 * @time 2016-09-28
 */
public class TaskItemAction extends BaseAction
{
	protected TaskItemFacade taskItemFacade = (TaskItemFacade)SpringBeanUtil.getBean("taskItemFacade");
	
	/**
	 * 查询待办业务信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listJhcBtTaskItem(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		log.debug("-------------进入TaskItemAction的listJhcBtTaskItem方法--------------------------");
		try {
			JSONObject obj = new JSONObject();
			TableTagBean ttb = new TableTagBean(request);
			Object list = taskItemFacade.listJhcBtTaskItem(ttb);
			obj.put("total", ttb.getTotal());
			obj.put("rows", null!=list?list:new ArrayList<Object>());
			response.getWriter().print(obj.toString());
		} catch (Exception se) {
			this.handleException(request, se, null);
			return null;
		}
		return null;
	}
}
