package com.yh.hr.report.web.action;



import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.report.dto.AllPersonStatisticsDTO;
import com.yh.hr.report.facade.impl.AllPersonStatisticsFacadeImpl;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;
/**
 * 全院员工汇总统计 action
 * @author liul
 * @date 2017-3-2
 *
 */
public class AllPersonStatisticsAction extends BaseAction{
	
	private AllPersonStatisticsFacadeImpl allPersonStatisticsFacade=(AllPersonStatisticsFacadeImpl)SpringBeanUtil.getBean("allPersonStatisticsFacade");
	/**
	 * 跳转至全院员工统计显示页面 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ServiceException
	 * @throws UnsupportedEncodingException
	 */
	public ActionForward goviewEveryOfficeSummaryInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceException, UnsupportedEncodingException
	{
		try {
			response.setContentType("text/html;charset=gb2312");
			request.setCharacterEncoding("gb2312");
			TableTagBean ttb = TableTagBean.getFromRequest(request);
			String exportExcel = request.getParameter("exportExcel");
			Map<String,List<AllPersonStatisticsDTO>> postInfo = allPersonStatisticsFacade.getOrgUnitNameCount(ttb);
			if(!MapUtils.isEmpty(postInfo)){
		        	 for(Map.Entry<String, List<AllPersonStatisticsDTO>> me:postInfo.entrySet()){
		        		 request.setAttribute(me.getKey(), me.getValue());
		        	 }
			   }
			//request.setAttribute("map", postInfo);
			request.setAttribute("nkbqLength", postInfo.get("nkbqList").size()-1);
			request.setAttribute("wkbqLength", postInfo.get("wkbqList").size()-2);
			request.setAttribute("mzksLength", postInfo.get("mzksList").size()-2);
			request.setAttribute("yjksLength", postInfo.get("yjksList").size()-2);
			request.setAttribute("xzhqLength", postInfo.get("xzhqList").size()-2);
			return StringUtils.isNotEmpty(exportExcel)?mapping.findForward("excel"):mapping.findForward("success");
		} catch (ServiceException e) {
			this.handleException(request, e, null);
			return mapping.findForward("error");
		}
    }
}
