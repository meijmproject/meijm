package com.yh.hr.report.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.report.facade.impl.ZgEmployedPersonFacadeImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 在岗人员总表
 * @author Administrator
 *
 */
public class ZgEmployedPersonAction extends BaseAction {
	
	private ZgEmployedPersonFacadeImpl zgEmployedPersonFacade = (ZgEmployedPersonFacadeImpl) SpringBeanUtil.getBean("zgEmployedPersonFacade");

	/**
	 * 跳转或导出在岗人员总表界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goviewEmployedPersonInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取需要的数据
		request.setAttribute("zgzg", zgEmployedPersonFacade.findZgzg());
		request.setAttribute("wsjs", zgEmployedPersonFacade.findWsjs());
		request.setAttribute("zyys", zgEmployedPersonFacade.findZyys());
		request.setAttribute("zyysZylb", zgEmployedPersonFacade.findZyysZylb());
		request.setAttribute("zyzlys", zgEmployedPersonFacade.findZyzlys());
		request.setAttribute("zyzlysZylb", zgEmployedPersonFacade.findZyzlysZylb());
		request.setAttribute("zyyaos", zgEmployedPersonFacade.findZyyaos());
		request.setAttribute("zyyaosxys", zgEmployedPersonFacade.findZyyaosxys());
		request.setAttribute("zyyaoszys", zgEmployedPersonFacade.findZyyaoszys());
		request.setAttribute("jyjs", zgEmployedPersonFacade.findJyjs());
		request.setAttribute("yxjs", zgEmployedPersonFacade.findYxjs());
		//request装配数据以用于jsp页面获取
		request.setAttribute("currentYear", DateUtil.format(DateUtil.now(), "yyyy"));
		//导出标志
		if(!StringUtils.isBlank(request.getParameter("flag"))&&request.getParameter("flag").equals("export")) {
			return mapping.findForward("export");
		}else{
			return mapping.findForward("success");
		}
	}
}
