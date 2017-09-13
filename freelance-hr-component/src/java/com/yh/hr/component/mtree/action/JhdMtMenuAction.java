package com.yh.hr.component.mtree.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.yh.hr.component.mtree.dto.MtMenuDto;
import com.yh.hr.component.mtree.facade.JhdMtMenuFacade;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class JhdMtMenuAction extends BaseAction {
	private JhdMtMenuFacade jhdMtMenuFacade = (JhdMtMenuFacade) SpringBeanUtil
			.getBean("jhdMtMenuFacade");
	

	/**
	 * 跳转到业务办理待办/已办页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goBizViewport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			return mapping.findForward("success"+request.getParameter("dbflag"));
		} catch (Exception e) {
			handleException(request, e, "跳转到业务办理待办/已办页面失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 加载右边工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goBusinessWorktop(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到业务办理页面失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 事项环节树
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findJhdMtMenu(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		try {
			JSONArray ary = new JSONArray();
			String menuCode = request.getParameter("menuCode");
			String menuType = request.getParameter("menuType");
			List<MtMenuDto> list = jhdMtMenuFacade.findJhdMtMenu(menuCode,menuType);
			JSONObject json = new JSONObject();
			if (!CollectionUtils.isEmpty(list))
			{
				for (MtMenuDto dto : list)
				{
					JSONObject obj = new JSONObject();
					obj.put("menuCode", dto.getMenuCode());
					obj.put("menuTitle", dto.getMenuTitle());
					obj.put("viewMenuTitle",dto.getMenuTitle().length()>14?(dto.getMenuTitle().substring(0, 14)+"..."):dto.getMenuTitle());
					if(!"#".equals(dto.getLocation())){
						obj.put("leaf", true);
					}else{
						obj.put("leaf", false);
					}
					obj.put("count", dto.getCount());
					obj.put("menuType", dto.getMenuType());
					obj.put("location", dto.getLocation());
					obj.put("image", dto.getImage());
					ary.element(obj);
				}
			}
			json.put("list", ary);
			response.getWriter().write(json.toString());
		} catch (Exception se) {
			handleException(request, se, null);
			return mapping.findForward("fail");
		}
		return null;
	}
}
