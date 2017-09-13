package com.yh.hr.report.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.report.dto.QsReportDTO;
import com.yh.hr.report.facade.impl.ReportFacadeImpl;
import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.TreeNode;
import com.yh.platform.core.web.action.BaseAction;

public class ReportAction extends BaseAction {
	
	private ReportFacadeImpl reportFacade = (ReportFacadeImpl)SpringBeanUtil.getBean("reportFacade");

	/**
	 * 跳转到常用查询统计报表管理工作台
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToQueryStasCommReportWorkbench(ActionMapping mapping, ActionForm actionform, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	
	/**
	 * 跳转到考勤业务查询统计报表管理工作台
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToQueryStasAttendanceReportWorkbench(ActionMapping mapping, ActionForm actionform, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	
	/**
	 * 跳转到图形类查询统计报表管理工作台
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToQueryStasMappingReportWorkbench(ActionMapping mapping, ActionForm actionform, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	
	public ActionForward findReportsTree(ActionMapping mapping, ActionForm actionform, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String statType = request.getParameter("statType");
		List<TreeNode<QsReportDTO>> list = reportFacade.findReportList(null,statType);
		response.getWriter().print(JSONArray.fromObject(list).toString());
		return null;
	}
}
