package com.yh.hr.report.web.action;



import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.yh.hr.report.dto.MatronStatisticsDTO;
import com.yh.hr.report.facade.impl.MatronStatisticsFacadeImpl;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;
/**
 * 科主任及护士长系列人员汇总 action
 * @author liul
 * @date 2017-3-7
 *
 */
public class MatronStatisticsAction extends BaseAction{
	
	private MatronStatisticsFacadeImpl matronStatisticsFacade=(MatronStatisticsFacadeImpl)SpringBeanUtil.getBean("matronStatisticsFacade");
	/**
	 * 跳转至科主任及护士长系列人员汇总显示页面 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ServiceException
	 * @throws UnsupportedEncodingException
	 */
	public ActionForward goviewMatronInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServiceException, UnsupportedEncodingException
	{
		try {
			response.setContentType("text/html;charset=gb2312");
			request.setCharacterEncoding("gb2312");
			TableTagBean ttb = TableTagBean.getFromRequest(request);
			String exportExcel = request.getParameter("exportExcel");
			Map<String,List<MatronStatisticsDTO>> postInfo = matronStatisticsFacade.getOrgUnitNameCount(ttb);
			if(!MapUtils.isEmpty(postInfo)){
		        	 for(Map.Entry<String, List<MatronStatisticsDTO>> me:postInfo.entrySet()){
		        		 request.setAttribute(me.getKey(), me.getValue());
		        	 }
			   }
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");//可以方便地修改日期格式
			request.setAttribute("createDate", dateFormat.format(new Date()));
			request.setAttribute("nkbqLength", postInfo.get("nkbqList").size());
			request.setAttribute("wkbqLength", postInfo.get("wkbqList").size());
			request.setAttribute("mzksLength", postInfo.get("mzksList").size());
			request.setAttribute("yjksLength", postInfo.get("yjksList").size()-1);
			return StringUtils.isNotEmpty(exportExcel)?mapping.findForward("excel"):mapping.findForward("success");
		} catch (ServiceException e) {
			this.handleException(request, e, null);
			return mapping.findForward("error");
		}
    }
}
