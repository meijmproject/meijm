package com.yh.hr.report.web.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.report.dto.ManagerDetailsReportDTO;
import com.yh.hr.report.facade.impl.ManagerDetailsReportFacadeImpl;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 报表-管理人员明细
 * @author duxw
 * @date 2017-3-1
 *
 */
public class ManagerDetailsReportAction extends BaseAction{
	
	private ManagerDetailsReportFacadeImpl managerDetailsReportFacade=(ManagerDetailsReportFacadeImpl)SpringBeanUtil.getBean("managerDetailsReportFacade");
	
	/**
	 * 跳转到管理人员明细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goviewManagePersonInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取管理人员明细
		try {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			TableTagBean ttb = TableTagBean.getFromRequest(request);
			
			//获取相关的值
			String exportExcel = request.getParameter("exportExcel");
			
			//获取管理人员明细
			Map<String,List<ManagerDetailsReportDTO>> details = managerDetailsReportFacade.getManagerDetails(ttb);
			if(!MapUtils.isEmpty(details)){
		        	 for(Map.Entry<String, List<ManagerDetailsReportDTO>> me:details.entrySet()){
		        		 request.setAttribute(me.getKey(), me.getValue());
		        	 }
			   }
			
			//判断是指向导出,还是页面
			return StringUtils.isNotEmpty(exportExcel)?mapping.findForward("excel"):mapping.findForward("success");
		} catch (ServiceException e) {
			this.handleException(request, e, null);
			return mapping.findForward("error");
		}
	}
	
}
