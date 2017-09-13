package com.yh.hr.component.worktop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.component.worktop.facade.WbWorkBenchFacade;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.component.worktop.dto.WbWorkBenchForwardDTO;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 事项树跳转工作台
 * @author huw
 * @time 2016-09-29
 */
public class WbWorkBenchAction extends BaseAction
{
	private WbWorkBenchFacade wbWorkBenchFacade = (WbWorkBenchFacade) SpringBeanUtil.getBean("wbWorkBenchFacade");
	/**
	 * 跳转到业务办理工作台中转页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToMainTabPanel(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			String menuCode = request.getParameter("menuCode");
			String itemNodeCode = wbWorkBenchFacade.getItemNodeCode(menuCode);
			request.setAttribute("itemNodeCode", itemNodeCode);
			request.setAttribute("menuCode", menuCode);
			String pageNo = request.getParameter("pageNo");
			if(null != pageNo){				
				request.setAttribute("pageNo", pageNo);
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到业务办理工作台中转页面");
			return mapping.findForward("error");
		}
	}
	
	/**
	 * 跳转到业务办理待办/已办页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goBizWorkViewport(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		String dbflag = request.getParameter("dbflag");
		String itemNodeCode = request.getParameter("itemNodeCode");
		String menuCode = request.getParameter("menuCode");
		String pageNo = request.getParameter("pageNo");
		try {
			if(StringUtils.isNotEmpty(itemNodeCode))
			{
				WbWorkBenchForwardDTO dto = wbWorkBenchFacade.findBizWorkTopDTOByNodeCode(itemNodeCode);
				if(null != dto)
				{
					request.setAttribute("dto", dto);
				}
			}
			if(null != pageNo){				
				request.setAttribute("pageNo", pageNo);
			}
			request.setAttribute("itemNodeCode", itemNodeCode);
			request.setAttribute("menuCode", menuCode);
			request.setAttribute("dbflag", dbflag);
			return mapping.findForward("success"+dbflag);
		} catch (Exception e) {
			handleException(request, e, "跳转到业务办理待办/已办页面失败");
			return mapping.findForward("error");
		}
	}
}
