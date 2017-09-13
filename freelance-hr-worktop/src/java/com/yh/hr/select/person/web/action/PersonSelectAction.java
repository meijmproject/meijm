package com.yh.hr.select.person.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.select.person.facade.PersonSelectFacade;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.action.BaseAction;

public class PersonSelectAction extends BaseAction {

	private PersonSelectFacade personSelectFacade = (PersonSelectFacade) SpringBeanUtil
			.getBean("personSelectFacade");

	public ActionForward selectPseron(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TableTagBean ttb = new TableTagBean(request);
		
		ttb.getCondition().put("personType", StringUtil.joinWrap(StringUtils.split(request.getParameter("personType"),",")));
		ttb.getCondition().put("personStatus", StringUtil.joinWrap(StringUtils.split(request.getParameter("personStatus"),",")) );
		ttb.getCondition().put("itemCode", StringUtil.joinWrap(StringUtils.split(request.getParameter("itemCode"),",")) );
		ttb.getCondition().put("itemCodeNode", StringUtil.joinWrap(StringUtils.split(request.getParameter("itemCodeNode"),",")) );
		
		try {
			List<JSONObject> list = personSelectFacade.listPbpersonInfo(ttb);
			JSONObject obj = new JSONObject();
			obj.put("total", ttb.getTotal());
			obj.put("rows", null != list ? list : new ArrayList<Object>());
			response.getWriter().print(obj.toString());
		} catch (Exception e) {
			this.handleException(request, e, "查询人员选择列表失败!");
			response.getWriter().print(
					JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(
							e.getMessage(), "查询人员信息失败")));
		}
		return null;
	}

	public ActionForward goSelectPseron(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("success");
	}

}
