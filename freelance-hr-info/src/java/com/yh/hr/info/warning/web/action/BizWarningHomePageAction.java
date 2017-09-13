package com.yh.hr.info.warning.web.action;


import com.yh.component.print.util.ExporterUtil;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.dto.BizWarConfigInfoDTO;
import com.yh.hr.info.warning.facade.impl.BizWarningInfoFacadeImpl;
import com.yh.hr.info.warning.util.BizWarningConstants;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 *@description 预警首页
 *
 *@author      chencr
 *@created     2012-11-06
 *@version     1.0
 *
 */
public class BizWarningHomePageAction extends BaseAction 
{
	private BizWarningInfoFacadeImpl bizWarningInfoFacade = (BizWarningInfoFacadeImpl)SpringBeanUtil.getBean("bizWarningInfoFacade");
	
	/**
	 * 加载预警列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listBizWarningInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			TableTagBean ttb = new TableTagBean(request);
			List<BizWarConfigInfoDTO> list = bizWarningInfoFacade.listBizWarningInfo(ttb);
			JSONObject json = new JSONObject();
			json.put("total", ttb.getTotal());
			json.put("rows", list);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "查询预警列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询预警列表失败")));
		}
        return null;
	}
	
	
	/**
	 * 跳转至预警事项工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToBizWarningInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String itemCode = request.getParameter("itemCode");
		request.setAttribute("itemCode", itemCode);
		String bizWarningDays = bizWarningInfoFacade.getBizWarningDays(itemCode);
		request.setAttribute("bizWarningDays",bizWarningDays);
		return mapping.findForward(itemCode);
	}
	
	/**
	 * 查询预警列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBizWarningInfo(ActionMapping mapping, 
    		   ActionForm form,
    		   HttpServletRequest request, 
    		   HttpServletResponse response) throws Exception
	{
		try{
			String itemCode = request.getParameter("itemCode");
			String warningDays = request.getParameter("warningDays");
			String name = request.getParameter("name");
			TableTagBean ttb = new TableTagBean(request);
			ttb.getCondition().put("itemCode", itemCode);
			ttb.getCondition().put("warningDays", warningDays);
			ttb.getCondition().put("name", name);
			List<JSONObject> list = bizWarningInfoFacade.getBizWarningInfo(ttb);
			JSONObject obj = new JSONObject();
			obj.put("total", ttb.getTotal());
			obj.put("rows", list);
			response.getWriter().print(obj.toString());
			return null;
		}catch(Exception e)
		{
			handleException(request, e, "加载预警列表出错!");
			response.getWriter().print("{'fail':'fail','msg':'" + (GenericValidator.isBlankOrNull(e.getMessage())?"加载预警列表出错!":e.getMessage()) + "'}");
			return null;
		}
	}
	
	public ActionForward exportWarningList(ActionMapping mapping, 
 		   ActionForm form,
 		   HttpServletRequest request, 
 		   HttpServletResponse response) throws Exception
	{
		try{
			String itemCode = request.getParameter("itemCode");
			TableTagBean ttb = new TableTagBean(request);
			List<JSONObject> list = bizWarningInfoFacade.getBizWarningInfo(ttb);
			for(int i=0; i<list.size(); i++) {
				list.get(i).put("number", i+1+"");
			}
			String[][] columns = null;
			String filename = "";
			if(itemCode.equals(BizWarningConstants.BIZ_WARNING_YJHTDQ)) {
				columns = new String[][]{
						{"number", "序号"},
						{"name", "人员姓名"},
						{"sexCodeDesc", "人员性别"},
						{"contractNo", "合同编号"},
						{"contractEndStr", "合同截止时间"},
						{"hireDeptName", "所在科室"},
						{"difference", "剩余天数"}
				};
				filename = "合同到期预警列表";
			}else if(itemCode.equals(BizWarningConstants.BIZ_WARNING_YJLTX)) {
				columns = new String[][]{
						{"number", "序号"},
						{"name", "人员姓名"},
						{"sexCodeDesc", "人员性别"},
						{"retireTime", "退休时间"},
						{"hireDeptName", "所在科室"},
						{"difference", "剩余天数"}
				};
				filename = "离退休预警列表";
			}
			ExporterUtil.export("excel", columns, list, response, filename);
			return null;
		}catch(Exception e)
		{
			handleException(request, e, "导出预警列表出错!");
			response.getWriter().print("{'fail':'fail','msg':'" + (GenericValidator.isBlankOrNull(e.getMessage())?"导出预警列表出错!":e.getMessage()) + "'}");
			return null;
		}
	}
}
