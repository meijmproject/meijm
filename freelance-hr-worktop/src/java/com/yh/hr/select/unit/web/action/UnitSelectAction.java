package com.yh.hr.select.unit.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.select.unit.facade.UnitSelectFacade;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.action.BaseAction;

public class UnitSelectAction extends BaseAction {

	private UnitSelectFacade unitSelectFacade = (UnitSelectFacade) SpringBeanUtil
			.getBean("unitSelectFacade");

	public ActionForward selectUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TableTagBean ttb = new TableTagBean(request);
		
		ttb.getCondition().put("unitStatus", StringUtil.joinWrap(StringUtils.split(request.getParameter("unitStatus"),",")) );
		ttb.getCondition().put("itemCode", StringUtil.joinWrap(StringUtils.split(request.getParameter("itemCode"),",")) );
		ttb.getCondition().put("itemCodeNode", StringUtil.joinWrap(StringUtils.split(request.getParameter("itemCodeNode"),",")) );
	    //是主管单位还是下设单位
		ttb.getCondition().put("isParentUnit", request.getParameter("isParentUnit"));
		//单位性质
		ttb.getCondition().put("unitKind", request.getParameter("unitKind"));
		
		try {
			List<JSONObject> list = unitSelectFacade.listUnitInfo(ttb);
			JSONObject obj = new JSONObject();
			obj.put("total", ttb.getTotal());
			obj.put("rows", null != list ? list : new ArrayList<Object>());
			response.getWriter().print(obj.toString());
		} catch (Exception e) {
			this.handleException(request, e, "查询单位选择列表失败!");
			response.getWriter().print(
					JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(
							e.getMessage(), "查询单位信息失败")));
		}
		return null;
	}

	public ActionForward goSelectUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("success");
	}

}
